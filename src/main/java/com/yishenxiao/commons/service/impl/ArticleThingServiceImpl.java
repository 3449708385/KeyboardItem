package com.yishenxiao.commons.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.yishenxiao.commons.beans.ClickRead;
import com.yishenxiao.commons.beans.ClientUpdateData;
import com.yishenxiao.commons.beans.IdentityRealNameAuthentication;
import com.yishenxiao.commons.beans.MolHistoricalRecord;
import com.yishenxiao.commons.beans.PaginationData;
import com.yishenxiao.commons.beans.PublishArticle;
import com.yishenxiao.commons.service.ArticleThingService;
import com.yishenxiao.commons.service.MolHistoricalRecordService;
import com.yishenxiao.commons.service.impl.quzrtz.ClientUpdateDataMyCallable;
import com.yishenxiao.commons.utils.DateUtils;
import com.yishenxiao.commons.utils.GeneratingRandomNumber;
import com.yishenxiao.commons.utils.PropertiesUtils;
import com.yishenxiao.commons.utils.WeiboShortLink;
import com.yishenxiao.commons.utils.filter.AESUtil;
import com.yishenxiao.usermanager.beans.UserArticle;
import com.yishenxiao.usermanager.beans.UserArticleMol;
import com.yishenxiao.usermanager.beans.UserIdentification;
import com.yishenxiao.usermanager.beans.UserSchedule;
import com.yishenxiao.usermanager.beans.UserShare;
import com.yishenxiao.usermanager.service.UserArticleMolService;
import com.yishenxiao.usermanager.service.UserArticleService;
import com.yishenxiao.usermanager.service.UserIdentificationService;
import com.yishenxiao.usermanager.service.UserScheduleService;
import com.yishenxiao.usermanager.service.UserShareService;

@Service("articleThingService")
public class ArticleThingServiceImpl implements ArticleThingService {

	Logger logger = LoggerFactory.getLogger(ArticleThingServiceImpl.class);

	@Autowired(required = false)
	@Qualifier("userArticleService")
	private UserArticleService userArticleService;

	@Autowired(required = false)
	@Qualifier("mongoService")
	private MongoTemplate mongoService;

	@Autowired(required = false)
	@Qualifier("userScheduleService")
	private UserScheduleService userScheduleService;

	@Autowired(required = false)
	@Qualifier("userIdentificationService")
	private UserIdentificationService userIdentificationService;

	@Autowired(required = false)
	@Qualifier("userShareService")
	private UserShareService userShareService;

	@Autowired(required = false)
	@Qualifier("userArticleMolService")
	private UserArticleMolService userArticleMolService;

	@Autowired(required = false)
	@Qualifier("molHistoricalRecordService")
	private MolHistoricalRecordService molHistoricalRecordService;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {
			Exception.class })
	public int userReleaseArticle(PublishArticle publishArticle) {
		for (int i = 0; i < publishArticle.getPictureList().size(); i++) {
			String temp = "http://gosmallapp.com/" + publishArticle.getPictureList().get(i)
					+ "?x-oss-process=style/ysx_66";
			publishArticle.getPictureList().set(i, temp);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("articleContext", publishArticle.getArticleContext());
		map.put("StartMol", publishArticle.getRewardMolCount().toString());
		map.put("userId", publishArticle.getUserId().toString());
		map.put("picture", publishArticle.getPictureList().toString());
		map.put("createTime", DateUtils.getNowTimeStr());
		map.put("updateTime", DateUtils.getNowTimeStr());
		DBObject dbObject = new BasicDBObject();
		dbObject.put("userArticleData", map);
		WriteResult wr = null;
		try {
			DBCollection dbCollection = mongoService.getCollection("articleContext");
			wr = dbCollection.insert(dbObject);
		} catch (com.mongodb.MongoTimeoutException e) {
			logger.error("mongo 连接失败！");
			return 0;
		}
		if (wr != null) {
			Double dataCount = Double.parseDouble(PropertiesUtils.getInfoConfigProperties().get("dataCount").toString());
			UserArticle userArticle = new UserArticle();
			userArticle.setArticlemongoid(dbObject.get("_id").toString());
			userArticle.setClickurlcount(0);
			userArticle.setForwardcount(0);
			userArticle.setCreatetime(DateUtils.getNowTime());
			userArticle.setExtensionmonery(
					new BigDecimal(publishArticle.getRewardMolCount()).setScale(2, BigDecimal.ROUND_DOWN));
			if(publishArticle.getRewardMolCount()<dataCount){
				userArticle.setState(1);
			}else{
			    userArticle.setState(0);
			}
			userArticle.setArticletype(publishArticle.getArticType());// id为1，默认是我发布的
			userArticle.setType(0);
			userArticle.setUpdatetime(DateUtils.getNowTime());
			userArticle.setUserid(publishArticle.getUserId());
			int cou = userArticleService.insertOneData(userArticle);
			if (cou != 1) {
				return 0;
			} else {
				List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(publishArticle.getUserId());
				cou = userScheduleService.updateSubtractMolCount(userScheduleList.get(0).getId(),
						publishArticle.getRewardMolCount());
				if (cou != 1) {
					return 0;
				} else {
					// 插入发布数据
					List<UserArticle> userArticleList = userArticleService
							.queryByArticlemongoid(dbObject.get("_id").toString());
					MolHistoricalRecord molHistoricalRecord = new MolHistoricalRecord();
					molHistoricalRecord.setCreatetime(DateUtils.getNowTime());
					molHistoricalRecord.setMolcount("-" + publishArticle.getRewardMolCount().toString());
					
					if(publishArticle.getArticleContext().length()>20){
					   molHistoricalRecord.setShowcontext("发布内容：" + publishArticle.getArticleContext().substring(0, 20));
					}else{
					   molHistoricalRecord.setShowcontext("发布内容：" + publishArticle.getArticleContext());
					}
					molHistoricalRecord.setType(4);
					molHistoricalRecord.setUserid(publishArticle.getUserId());
					molHistoricalRecord.setUpdatetime(DateUtils.getNowTime());
					molHistoricalRecord.setUserarticleid(userArticleList.get(0).getId());
					cou = molHistoricalRecordService.insertOneData(molHistoricalRecord);
					if (cou != 1) {
						return 0;
					} else {
						return 1;
					}
				}
			}
		} else {
			return 0;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {
			Exception.class })
	public int userCancelRelease(PublishArticle publishArticle) {
		List<UserArticle> userArticleList = userArticleService.queryById(publishArticle.getUserArticleId());
		if(publishArticle.getType()==2){
			Double dataCount = Double.parseDouble(PropertiesUtils.getInfoConfigProperties().get("dataCount").toString());
			if(userArticleList.get(0).getState()==1 && userArticleList.get(0).getExtensionmonery().compareTo(new BigDecimal(dataCount))==-1){
				int cou = userArticleService.updateArticleStateAndExtensionMonery(publishArticle.getUserArticleId(), publishArticle.getType(),
						publishArticle.getRewardMolCount());
				if (cou != 1) {
					return 0;
				}else{
					return 1;
				}
			}		
		}else if(publishArticle.getType()==1){
			int cou = userArticleService.updateArticleStateAndExtensionMonery(publishArticle.getUserArticleId(), publishArticle.getType(),
					publishArticle.getRewardMolCount());
			if (cou != 1) {
				return 0;
			} else {// 归还币
				List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(publishArticle.getUserId());
				cou = userScheduleService.updateIncreaseMolCount(userScheduleList.get(0).getId(),
						userArticleList.get(0).getExtensionmonery().doubleValue());
				if (cou != 1) {
					return 0;
				} else {
					MolHistoricalRecord molHistoricalRecord = new MolHistoricalRecord();
					molHistoricalRecord.setCreatetime(DateUtils.getNowTime());
					molHistoricalRecord.setMolcount("-" + publishArticle.getRewardMolCount().toString());
					molHistoricalRecord.setShowcontext("取消推广：" + publishArticle.getArticleContext().substring(0, 20));
					molHistoricalRecord.setType(3);
					molHistoricalRecord.setUserid(publishArticle.getUserId());
					molHistoricalRecord.setUpdatetime(DateUtils.getNowTime());
					molHistoricalRecord.setUserarticleid(userArticleList.get(0).getId());
					cou = molHistoricalRecordService.insertOneData(molHistoricalRecord);
					if (cou != 1) {
						return 0;
					} else {
						return 1;
					}
				}
			}
		}else{
		   return 0;
		}
		return 0;	
	}

	@Override
	public List<ClientUpdateData> clientUpdateData(PaginationData paginationData) {
		List<ClientUpdateData> clientUpdateDataList = new ArrayList<ClientUpdateData>();
		List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(paginationData.getUserId());
		String[] userType = userScheduleList.get(0).getArticletype().split(",");
		List<UserArticle> userArticleListTemp2 = new ArrayList<UserArticle>();
		Double dataCount = Double.parseDouble(PropertiesUtils.getInfoConfigProperties().get("dataCount").toString());
		for (int i = 0; i < userType.length; i++) {
			userArticleListTemp2.addAll(userArticleService.queryByMolAndLimit(userType[i], paginationData.getLimitCount(), dataCount, paginationData.getUserId()));
		}
		//去重
		List<UserArticle> userArticleListTemp = new ArrayList<UserArticle>();
		for(int i =0;i<userArticleListTemp2.size();i++){
			if(!userArticleListTemp.toString().contains(userArticleListTemp2.get(i).toString())){
				userArticleListTemp.add(userArticleListTemp2.get(i));
			}
		}
		//排序
		for (int i = 0; i < userArticleListTemp.size() - 1; i++) {
			for (int j = 0; j < userArticleListTemp.size() - i - 1; j++) {
				if (userArticleListTemp.get(j).getExtensionmonery()
						.compareTo(userArticleListTemp.get(j + 1).getExtensionmonery()) == -1) {
					UserArticle userArticle = userArticleListTemp.get(j);
					userArticleListTemp.set(j, userArticleListTemp.get(j + 1));
					userArticleListTemp.set(j + 1, userArticle);
				}
			}
		}
		//截取
		List<UserArticle> userArticleList = new ArrayList<UserArticle>();
		int count = paginationData.getLimitCount();
		if(count>userArticleListTemp.size()){
			count = userArticleListTemp.size();
		}
		for (int i = 0; i < count; i++) {
			userArticleList.add(userArticleListTemp.get(i));
		}
		//准备返回数据
		for (int i = 0; i < userArticleList.size(); i++) {
			UserArticle userArticle = userArticleList.get(i);
			DBCollection dbCollection = mongoService.getCollection("articleContext");
			DBObject dbObject = new BasicDBObject();
			dbObject.put("_id", new ObjectId(userArticle.getArticlemongoid()));
			DBCursor dbCursor = dbCollection.find(dbObject);
			DBObject dbObjectResult = new BasicDBObject();
			while (dbCursor.hasNext()) {
				dbObjectResult = dbCursor.next();
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) dbObjectResult.get("userArticleData");
				ClientUpdateData clientUpdateData = new ClientUpdateData();
				clientUpdateData.setCreateTime(map.get("createTime").toString());
				clientUpdateData.setArticleShareUserId(userArticle.getUserid());
				clientUpdateData.setUserId(paginationData.getUserId());
				String dataId = new Sha256Hash(userArticle.getId() + paginationData.getUserId()
						+ DateUtils.getNowTime().getTime() + GeneratingRandomNumber.getRandomNumAndStr(8)).toHex();
				String param = "userArticleId=" + userArticle.getId() + "&userId=" + paginationData.getUserId()
						+ "&shareUserId=" + userArticle.getUserid() + "&dataId=" + dataId;
				String asepawd = PropertiesUtils.getInfoConfigProperties().getProperty("ase.pawd");
				String paramter = "";
				try {
					paramter = AESUtil.encryptAES(param, asepawd);
				} catch (Exception e) {
					logger.error("URL link encryption failed!");
					return null;
				}
				String networkUrl = "http://www.mol.one/KeyboardItem/index.html?" + paramter;
				String urlTemp = WeiboShortLink.sinaShortUrl(networkUrl);
				String content = map.get("articleContext").toString();
				String regex = "(((https|http|ftp|rtsp|mms)?:|www.|\\w+.(one|im|net|com|cn|org|top|xyz|tech|gov|edu|ink|int|pub|tv|biz|name|pro))[^\u4e00-\u9fa5|\\s]*)";
				content = content.replaceAll(regex, urlTemp);
				if(!content.contains(urlTemp)){
					content=content+urlTemp;
				}
				clientUpdateData.setNetworkUrl(urlTemp);
				clientUpdateData.setContext(content);
				clientUpdateData.setForward(userArticle.getForwardcount());
				if (userArticle.getExtensionmonery().compareTo(new BigDecimal(dataCount)) == -1) {
					clientUpdateData.setMoldata("仅自己可见");
				} else {
					clientUpdateData.setMoldata("总量：" + userArticle.getExtensionmonery() + " / "
							+ new BigDecimal(map.get("StartMol").toString()).setScale(2, BigDecimal.ROUND_DOWN)
							+ "MOL");
				}
				clientUpdateData.setReadCount(userArticle.getClickurlcount());
				clientUpdateData.setId(userArticle.getId());
				clientUpdateDataList.add(clientUpdateData);
			}
		}
		return clientUpdateDataList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {
			Exception.class })
	public int advertisingRecharge(Long id, Double molCount) {
		List<UserArticle> userArticleList = userArticleService.queryById(id);
		DBCollection dbCollection = mongoService.getCollection("articleContext");
		DBObject dbObject = new BasicDBObject();
		dbObject.put("_id", new ObjectId(userArticleList.get(0).getArticlemongoid()));
		DBCursor dbCursor = dbCollection.find(dbObject);
		DBObject dbObjectResult = dbCursor.next();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) dbObjectResult.get("userArticleData");
		map.put("StartMol", Double.parseDouble(map.get("StartMol").toString()) + molCount);
		map.put("updateTime", DateUtils.getNowTimeStr());
		DBObject updateDBObject = new BasicDBObject();
		updateDBObject.put("userArticleData", map);
		WriteResult wr = dbCollection.update(dbObject, updateDBObject);
		if (wr != null) {
			int cou = userArticleService.updateArticleStateAndExtensionMoneryAndType(userArticleList.get(0).getId(), 0,
					molCount);
			if (cou != 1) {
				return 0;
			}
		} else {
			return 0;
		}
		return 1;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {
			Exception.class })
	public int identityRealNameAuthentication(IdentityRealNameAuthentication identityRealNameAuthentication) {
		UserIdentification userIdentification = new UserIdentification();
		userIdentification.setCreatetime(DateUtils.getNowTime());
		userIdentification.setIdentificationcard(identityRealNameAuthentication.getIdentityNum());
		userIdentification.setPictureafterurl(identityRealNameAuthentication.getIdentityPicture().get(1));
		userIdentification.setPictureprecedingurl(identityRealNameAuthentication.getIdentityPicture().get(0));
		userIdentification.setRealname(identityRealNameAuthentication.getRealName());
		userIdentification.setUpdatetime(DateUtils.getNowTime());
		userIdentification.setUserid(identityRealNameAuthentication.getUserId());
		int cou = userIdentificationService.insertOneData(userIdentification);
		if (cou != 1) {
			return 0;
		} else {
			List<UserSchedule> userScheduleList = userScheduleService
					.queryByUIdList(identityRealNameAuthentication.getUserId());
			cou = userScheduleService.updateUserAuthenticationState(userScheduleList.get(0).getId(), 1);
			if (cou != 1) {
				return 0;
			} else {
				return 1;
			}
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {
			Exception.class })
	public int clickReadData(ClickRead clickRead) {
		Double dataCount = Double.parseDouble(PropertiesUtils.getInfoConfigProperties().get("dataCount").toString());
		List<UserShare> userShareList = userShareService.queryByDataId(clickRead.getDataId());
		if (userShareList.size() > 0) {
			if (userShareList.get(0).getUsecount() < 10) {// 分享用户mol增加，发布用户mol持续减少
				// 用户mol币
				List<UserArticle> userArticleList = userArticleService.queryById(clickRead.getUserArticleId());
				if (userArticleList.get(0).getExtensionmonery().compareTo(new BigDecimal(dataCount)) != -1) {
					int cou = userArticleService
							.updateArticleExtensionMoneryAndClickurlcount(clickRead.getUserArticleId(), dataCount, 1);
					if (cou != 1) {
						return 0;
					} else {
						List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(clickRead.getUserId());
						cou = userScheduleService.updateIncreaseMolCount(userScheduleList.get(0).getId(), dataCount);
						if (cou != 1) {
							return 0;
						} else {
							List<UserArticleMol> userArticleMolList = userArticleMolService
									.queryByUserId(clickRead.getUserId());
							if (userArticleMolList.size() == 0) {
								UserArticleMol userArticleMol = new UserArticleMol();
								userArticleMol.setCreatetime(DateUtils.getNowTime());
								userArticleMol.setTodaymol(new BigDecimal(dataCount));
								userArticleMol.setTotalmol(new BigDecimal(dataCount));
								userArticleMol.setUpdatetime(DateUtils.getNowTime());
								userArticleMol.setUserid(clickRead.getUserId());
								cou = userArticleMolService.insertOneData(userArticleMol);
								if (cou != 1) {
									return 0;
								} else {
									return 1;
								}
							} else {
								cou = userArticleMolService.updateTotalAndToday(userArticleMolList.get(0).getId(),
										dataCount);
								if (cou != 1) {
									return 0;
								} else {
									return 1;
								}
							}
						}
					}
				} else {
					return 2;
				}
			} else {// 分享用户mol不增加，发布用户mol持续减少
				int cou = userArticleService.updateArticleExtensionMonery(clickRead.getUserArticleId(), dataCount);
				if (cou != 1) {
					return 0;
				}
			}
		} else {
			UserShare userShare = new UserShare();
			userShare.setUsecount(1);
			userShare.setUserarticleid(clickRead.getUserArticleId());
			userShare.setUserid(clickRead.getUserId());
			userShare.setDataid(clickRead.getDataId());
			userShare.setCreatetime(DateUtils.getNowTime());
			userShare.setUpdatetime(DateUtils.getNowTime());
			int cou = userShareService.insertOneData(userShare);
			if (cou != 1) {
				return 0;
			} else {
				cou = userArticleService.updateArticleExtensionMonery(clickRead.getUserArticleId(), dataCount);
				if (cou != 1) {
					return 0;
				} else {
					List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(clickRead.getUserId());
					cou = userScheduleService.updateIncreaseMolCount(userScheduleList.get(0).getId(), dataCount);
					if (cou != 1) {
						return 0;
					} else {
						List<UserArticleMol> userArticleMolList = userArticleMolService
								.queryByUserId(clickRead.getUserId());
						if (userArticleMolList.size() == 0) {
							UserArticleMol userArticleMol = new UserArticleMol();
							userArticleMol.setCreatetime(DateUtils.getNowTime());
							userArticleMol.setTodaymol(new BigDecimal(dataCount));
							userArticleMol.setTotalmol(new BigDecimal(dataCount));
							userArticleMol.setUpdatetime(DateUtils.getNowTime());
							userArticleMol.setUserid(clickRead.getUserId());
							cou = userArticleMolService.insertOneData(userArticleMol);
							if (cou != 1) {
								return 0;
							} else {
								return 1;
							}
						} else {
							cou = userArticleMolService.updateTotalAndToday(userArticleMolList.get(0).getId(),
									dataCount);
							if (cou != 1) {
								return 0;
							} else {
								return 1;
							}
						}
					}
				}
			}
		}
		return 1;
	}

	@Override
	public List<ClientUpdateData> userSelfRelease(PaginationData paginationData) {
		List<ClientUpdateData> clientUpdateDataList = new ArrayList<ClientUpdateData>();
		List<UserArticle> userArticleList = userArticleService.queryByUserIdAndPagination(paginationData.getUserId(),
				paginationData.getId(), paginationData.getLimitCount());
		if (userArticleList.size() != 0) {
			int poolSize = Integer.parseInt(PropertiesUtils.getInfoConfigProperties().getProperty("myUserPhoneSMSCallSize"));
			if (userArticleList.size() < poolSize) {
				poolSize = userArticleList.size();
			}
			// 为线程分配数据
			int listSize = (int) Math.ceil((double) userArticleList.size() / poolSize);
			List<List<UserArticle>> listFP = new ArrayList<List<UserArticle>>(poolSize);
			for (int j = 0; j < poolSize; j++) {
				List<UserArticle> tempList = new ArrayList<UserArticle>();
				for (int i = j * listSize; i < (listSize + listSize * j); i++) {
					if (i < userArticleList.size()) {
						tempList.add(userArticleList.get(i));
					}
				}
				listFP.add(tempList);
			}
			ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
			List<ClientUpdateDataMyCallable> myCallableExec = new ArrayList<ClientUpdateDataMyCallable>();
			for (int i = 0; i < poolSize; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userArticleList", listFP.get(i));
				map.put("paginationData", paginationData);
				myCallableExec.add(new ClientUpdateDataMyCallable(map));
			}
			List<Future<List<ClientUpdateData>>> futureList = new ArrayList<Future<List<ClientUpdateData>>>();
			try {
				futureList = executorService.invokeAll(myCallableExec);
			} catch (InterruptedException e) {
				logger.error("The client returns an exception to the execution of the data process.");
			}
			for(int i=0;i<futureList.size();i++){
				Future<List<ClientUpdateData>> futureTemp = futureList.get(i);
				try {
					List<ClientUpdateData> clientUpdateDatatempList = futureTemp.get();
					clientUpdateDataList.addAll(clientUpdateDatatempList);
				} catch (Exception e) {
					logger.error("Client return data exception!");
				} 			
			}

		} else {
			logger.info(DateUtils.getNowTime() + " ,The client gets the data interface, and there is no data!");
		}
		return clientUpdateDataList;
	}
}
