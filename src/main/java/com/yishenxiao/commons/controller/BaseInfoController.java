package com.yishenxiao.commons.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.yishenxiao.commons.beans.ArticleTypeData;
import com.yishenxiao.commons.beans.BackResponseBody;
import com.yishenxiao.commons.beans.ClickRead;
import com.yishenxiao.commons.beans.ClientUpdateData;
import com.yishenxiao.commons.beans.FaceResponseBody;
import com.yishenxiao.commons.beans.IdentityRealNameAuthentication;
import com.yishenxiao.commons.beans.MolHistoricalRecord;
import com.yishenxiao.commons.beans.PaginationData;
import com.yishenxiao.commons.beans.Version;
import com.yishenxiao.commons.beans.VersionInfo;
import com.yishenxiao.commons.service.ArticleThingService;
import com.yishenxiao.commons.service.MolHistoricalRecordService;
import com.yishenxiao.commons.service.VersionService;
import com.yishenxiao.commons.utils.AliyunRealNameAuthentication;
import com.yishenxiao.commons.utils.JsonUtils;
import com.yishenxiao.commons.utils.PropertiesUtils;
import com.yishenxiao.commons.utils.ReturnInfo;
import com.yishenxiao.commons.utils.StringUtils;
import com.yishenxiao.usermanager.beans.User;
import com.yishenxiao.usermanager.beans.UserArticle;
import com.yishenxiao.usermanager.beans.UserArticleMol;
import com.yishenxiao.usermanager.beans.UserIdentification;
import com.yishenxiao.usermanager.beans.UserInvitationcode;
import com.yishenxiao.usermanager.beans.UserSchedule;
import com.yishenxiao.usermanager.beans.UserShare;
import com.yishenxiao.usermanager.service.UserArticleMolService;
import com.yishenxiao.usermanager.service.UserArticleService;
import com.yishenxiao.usermanager.service.UserIdentificationService;
import com.yishenxiao.usermanager.service.UserInvitationCodeService;
import com.yishenxiao.usermanager.service.UserScheduleService;
import com.yishenxiao.usermanager.service.UserService;
import com.yishenxiao.usermanager.service.UserShareService;

@Controller
@RequestMapping("baseInfo")
public class BaseInfoController {

	Logger logger = LoggerFactory.getLogger(BaseInfoController.class);

	@Autowired(required = false)
	@Qualifier("versionService")
	private VersionService versionService;

	@Autowired(required = false)
	@Qualifier("userInvitationCodeService")
	private UserInvitationCodeService userInvitationCodeService;

	@Autowired(required = false)
	@Qualifier("userIdentificationService")
	private UserIdentificationService userIdentificationService;

	@Autowired(required = false)
	@Qualifier("userArticleService")
	private UserArticleService userArticleService;

	@Autowired(required = false)
	@Qualifier("articleThingService")
	private ArticleThingService articleThingService;

	@Autowired(required = false)
	@Qualifier("mongoService")
	private MongoTemplate mongoService;

	@Autowired(required = false)
	@Qualifier("userScheduleService")
	private UserScheduleService userScheduleService;

	@Autowired(required = false)
	@Qualifier("userService")
	private UserService userService;

	@Autowired(required = false)
	@Qualifier("redisTemplate")
	private RedisTemplate<String, Object> redisService;
	
	@Autowired(required = false)
	@Qualifier("molHistoricalRecordService")
	private MolHistoricalRecordService molHistoricalRecordService;
	
	@Autowired(required = false)
	@Qualifier("userShareService")
	private UserShareService userShareService;
	
	@Autowired(required = false)
	@Qualifier("userArticleMolService")
	private UserArticleMolService userArticleMolService;

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "getVersion", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo getVersion(@RequestBody VersionInfo versionInfo) {
		if(StringUtils.judgeBlank(versionInfo.getVersionNum()) || versionInfo.getType()==null || versionInfo.getType()!=0 && versionInfo.getType()!=1){
			return ReturnInfo.error("参数错误！");
		}	
		
		List<Version> versionList = versionService.queryByLastOneData();
        if(versionInfo.getVersionNum().equals(versionList.get(0).getNowversionnum())){
        	return ReturnInfo.info(501,"当前已是最新版本！");
		}
		if(versionList.get(0).getType()==0){//强制
			if(versionInfo.getType()==0 && versionList.get(0).getAndroidstate()==0){//安卓需要更新
				if(versionList.get(0).getVersionnum().contains(versionInfo.getVersionNum())){
					return ReturnInfo.info(201,versionList.get(0));
				}
			}else if(versionInfo.getType()==1 && versionList.get(0).getIosstate()==0){//ios
				if(versionList.get(0).getVersionnum().contains(versionInfo.getVersionNum())){
					Version version = versionList.get(0);
					return ReturnInfo.info(201,version);
				}
			}
		}else{
			if(versionInfo.getType()==0 && versionList.get(0).getAndroidstate()==0){//安卓需要更新
				if(versionList.get(0).getVersionnum().contains(versionInfo.getVersionNum())){
					return ReturnInfo.ok(versionList.get(0));
				}
			}else if(versionInfo.getType()==1 && versionList.get(0).getIosstate()==0){//ios
				if(versionList.get(0).getVersionnum().contains(versionInfo.getVersionNum())){
					Version version = versionList.get(0);
					return ReturnInfo.ok(version);
				}
			}
		}		
		return ReturnInfo.info(501,"当前已是最新版本！");
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "getVersionInvitationInfo", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo getVersionInvitationInfo(@RequestBody ArticleTypeData articleTypeData) {
		if (articleTypeData.getUserId() == null) {
			return ReturnInfo.error("参数错误！");
		}
		List<UserInvitationcode> userInvitationcodeList = userInvitationCodeService
				.queryByUserId(articleTypeData.getUserId());
		String str = "邀请你一起使用秒发键盘，用键盘挖矿赚钱。\n下载地址：www.baidu.com  \n" + "我的邀请码是："
				+ userInvitationcodeList.get(0).getInvitationcode();
		return ReturnInfo.info(200, str);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "identityRealNameAuthentication", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo identityRealNameAuthentication(
			@RequestBody IdentityRealNameAuthentication identityRealNameAuthentication) {
		if (identityRealNameAuthentication.getUserId() == null
				|| StringUtils.judgeBlank(identityRealNameAuthentication.getIdentityNum())
				|| identityRealNameAuthentication.getIdentityPicture().size() != 2
				|| StringUtils.judgeBlank(identityRealNameAuthentication.getRealName())) {
			return ReturnInfo.error("参数错误！");
		}
		List<String> list = new ArrayList<String>();
		for(int i=0;i<identityRealNameAuthentication.getIdentityPicture().size();i++){			
			String icon = "http://gosmallapp.com/" + identityRealNameAuthentication.getIdentityPicture().get(i) + "?x-oss-process=style/ysx_66";
			list.add(icon);
		}
		identityRealNameAuthentication.setIdentityPicture(list);
		if(identityRealNameAuthentication.getIdentityNum().length()!=18){
			return ReturnInfo.error("请联系客服！");
		}
		
		List<UserIdentification> userIdentificationList = userIdentificationService
				.queryByUserId(identityRealNameAuthentication.getUserId());
		if (userIdentificationList.size() > 0) {
			return ReturnInfo.error("请联系客服！");
		}
		Map<String, Object> map = AliyunRealNameAuthentication
				.getIdentityRealNameAuthentication(identityRealNameAuthentication.getIdentityPicture());
		if (Integer.parseInt(map.get("code").toString()) == 1) {
			FaceResponseBody faceResponseBody = JsonUtils.jsonToObj(map.get("resultface").toString(),
					FaceResponseBody.class);
			BackResponseBody backResponseBody = JsonUtils.jsonToObj(map.get("resultback").toString(),
					BackResponseBody.class);
			if (faceResponseBody.getSuccess() && backResponseBody.getSuccess()) {
				if (faceResponseBody.getNum().equals(identityRealNameAuthentication.getIdentityNum())
						&& faceResponseBody.getName().equals(identityRealNameAuthentication.getRealName())) {
					// 插入数据，更改状态
					int cou = articleThingService.identityRealNameAuthentication(identityRealNameAuthentication);
					if (cou == 1) {
						return ReturnInfo.ok();
					} else {
						return ReturnInfo.error("认证失败，请重试！");
					}
				}
			}
		} else {
			return ReturnInfo.error("认证失败，请重试！");
		}
		return ReturnInfo.error("认证失败，请重试！");
	}

	@SuppressWarnings("unchecked")
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "getNetworkData", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo getNetworkData(@RequestBody ClickRead clickRead, HttpServletRequest req,
			HttpServletResponse resp) {
		if (StringUtils.judgeBlank(clickRead.getDataId()) || clickRead.getUserId() == null
				|| clickRead.getArticleShareUserId() == null || clickRead.getUserArticleId() == null) {
			return ReturnInfo.error("参数错误！");
		}
		if (clickRead.getArticleShareUserId() == clickRead.getUserId()) {
			return ReturnInfo.ok();
		}
		Map<String, Object> map2 = new HashMap<String, Object>();
		String cookieValue = clickRead.getDataId();
		List<String> cookieList = new ArrayList<String>();
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieValue+"redisId")) {
					Object obj = redisService.opsForValue().get(cookie.getValue());
					if (obj != null) {
						cookieList = JsonUtils.jsonToObj(obj.toString(), List.class);
						if (cookieList.contains(cookie.getValue())) {
							map2.put("clientUpdateData", getClientUpdateData(clickRead));
							return ReturnInfo.info(200, map2);
						} else {
							cookieList.add(cookie.getValue());
							redisService.opsForValue().set(cookieValue, JsonUtils.toJson(cookieList));
							map2.put("clientUpdateData", getClientUpdateData(clickRead));
							return ReturnInfo.info(200, map2);
						}
					} else {
						cookieList.add(cookie.getValue());
						redisService.opsForValue().set(cookieValue, JsonUtils.toJson(cookieList));
						map2.put("clientUpdateData", getClientUpdateData(clickRead));
						return ReturnInfo.info(200, map2);
					}
				}
			}
			Cookie cookie = new Cookie(cookieValue+"redisId", cookieValue);
			cookie.setMaxAge(30 * 60);// 设置为30min
			resp.addCookie(cookie);
			cookieList.add(cookie.getValue());
			redisService.opsForValue().set(cookieValue, JsonUtils.toJson(cookieList));
			int cou = articleThingService.clickReadData(clickRead);
			if (cou == 0) {
				logger.error("客户端点击事件失败！");
			} else {
				map2.put("clientUpdateData", getClientUpdateData(clickRead));
				return ReturnInfo.info(200, map2);
			}
		} else {
			Cookie cookie = new Cookie(cookieValue+"redisId", cookieValue);
			cookie.setMaxAge(30 * 60);// 设置为30min
			resp.addCookie(cookie);
			cookieList.add(cookie.getValue());
			redisService.opsForValue().set(cookieValue, JsonUtils.toJson(cookieList));
			int cou = articleThingService.clickReadData(clickRead);
			if (cou == 0) {
				logger.error("客户端点击事件失败！");
			} else {
				map2.put("clientUpdateData", getClientUpdateData(clickRead));
				return ReturnInfo.info(200, map2);
			}
		}
		map2.put("clientUpdateData", getClientUpdateData(clickRead));
		return ReturnInfo.info(200, map2);
	}

	private ClientUpdateData getClientUpdateData(ClickRead clickRead) {
		List<UserArticle> userArticleList = userArticleService.queryById(clickRead.getUserArticleId());
		UserArticle userArticle = userArticleList.get(0);
		DBCollection dbCollection = mongoService.getCollection("articleContext");
		DBObject dbObject = new BasicDBObject();
		dbObject.put("_id", new ObjectId(userArticle.getArticlemongoid()));
		DBCursor dbCursor = dbCollection.find(dbObject);
		DBObject dbObjectResult = new BasicDBObject();
		ClientUpdateData clientUpdateData = new ClientUpdateData();
		while (dbCursor.hasNext()) {
			dbObjectResult = dbCursor.next();
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) dbObjectResult.get("userArticleData");
			clientUpdateData.setContext(map.get("articleContext").toString());
			clientUpdateData.setCreateTime(map.get("createTime").toString());
			if (clickRead.getArticleShareUserId() == null) {
				clientUpdateData.setArticleShareUserId(userArticle.getUserid());
			}
			clientUpdateData.setUserId(clickRead.getUserId());
			clientUpdateData.setState(userArticle.getState());
			clientUpdateData.setForward(userArticle.getForwardcount());
			if (userArticle.getExtensionmonery().compareTo(new BigDecimal("0")) <= 0) {
				clientUpdateData.setMoldata("仅自己可见");
			} else {
				clientUpdateData.setMoldata("总量：" + userArticle.getExtensionmonery() + " / "
						+ new BigDecimal(map.get("StartMol").toString()).setScale(2, BigDecimal.ROUND_DOWN) + "MOL");
			}
			clientUpdateData.setReadCount(userArticle.getClickurlcount());
			String temp = map.get("picture").toString();
			temp = temp.substring(1, temp.length() - 1);
			String[] strTemp = temp.split(", http");
			List<String> pictureList = Arrays.asList(strTemp);
			for(int t=0;t<pictureList.size();t++){
				if(t>0){
					pictureList.set(t, "http"+pictureList.get(t));
				}
			}
			clientUpdateData.setPictureList(pictureList);
			clientUpdateData.setId(userArticle.getId());
		}
		List<User> userList = userService.queryByUserId(clickRead.getUserId());
		List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(clickRead.getUserId());
		clientUpdateData.setNickName(userList.get(0).getNickname());
		clientUpdateData.setPhone(userList.get(0).getPhone());
		clientUpdateData.setHeadIcon(userScheduleList.get(0).getHeadicon());
		return clientUpdateData;
	}
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "getHistoricalRecord", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo getHistoricalRecord(@RequestBody PaginationData paginationData) {
		if(paginationData.getUserId()==null || paginationData.getStartId()==null || paginationData.getLimitCount()==null){
			return ReturnInfo.error("参数错误！");
		}
		Double dataCount = Double.parseDouble(PropertiesUtils.getInfoConfigProperties().get("dataCount").toString());
		List<MolHistoricalRecord> molHistoricalRecordList = molHistoricalRecordService.queryByUserId(paginationData.getUserId());
		List<UserShare> userShareList = userShareService.queryByUserId(paginationData.getUserId());
		for(int i=0;i<userShareList.size();i++){
			UserShare userShare = userShareList.get(i);
			List<UserArticle> userArticleList = userArticleService.queryById(userShare.getUserarticleid());
			DBCollection dbCollection = mongoService.getCollection("articleContext");
			DBObject dbObject = new BasicDBObject();
			dbObject.put("_id", new ObjectId(userArticleList.get(0).getArticlemongoid()));
			DBCursor dbCursor = dbCollection.find(dbObject);
			DBObject dbObjectResult = dbCursor.next();
			Map<String, Object> map = (Map<String, Object>) dbObjectResult.get("userArticleData");			
			MolHistoricalRecord molHistoricalRecord = new MolHistoricalRecord();
			molHistoricalRecord.setCreatetime(userShare.getCreatetime());
			molHistoricalRecord.setMolcount("+"+userShare.getUsecount()*dataCount);			
			molHistoricalRecord.setShowcontext("分享挖矿："+map.get("articleContext").toString().substring(0,20));
			molHistoricalRecord.setType(1);
			molHistoricalRecord.setUpdatetime(userShare.getUpdatetime());
			molHistoricalRecord.setUserarticleid(userShare.getUserarticleid());
			molHistoricalRecord.setUserid(userShare.getUserid());
			molHistoricalRecordList.add(molHistoricalRecord);
		}
		List<UserArticleMol> userArticleMolList = userArticleMolService.queryByUserId(paginationData.getUserId());
		List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(paginationData.getUserId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("todayMol", userArticleMolList.get(0).getTodaymol());
		map.put("totalMiningMol", userArticleMolList.get(0).getTotalmol());
		map.put("totalMol", userScheduleList.get(0).getMolcount());	
		if(molHistoricalRecordList.size()==0){
			map.put("molHistoricalRecordList", molHistoricalRecordList);
			return ReturnInfo.info(200, map);
		}
		//时间降序
		for(int i=0;i<molHistoricalRecordList.size()-1;i++){
			for(int j=0;j<molHistoricalRecordList.size()-i-1;j++){
				if(molHistoricalRecordList.get(j).getUpdatetime().getTime()<molHistoricalRecordList.get(j+1).getUpdatetime().getTime()){
					MolHistoricalRecord temp=molHistoricalRecordList.get(j);
					molHistoricalRecordList.set(j, molHistoricalRecordList.get(j+1));
					molHistoricalRecordList.set(j+1, temp);
				}
			}
		}
		//分页
		List<MolHistoricalRecord> returnMolHistoricalRecordList = new ArrayList<MolHistoricalRecord>();
		int count=paginationData.getStartId()+paginationData.getLimitCount();
		if(molHistoricalRecordList.size()<count){
			count=molHistoricalRecordList.size();
		}
		for(int i=paginationData.getStartId();i<count;i++){
			returnMolHistoricalRecordList.add(molHistoricalRecordList.get(i));
		}
		map.put("molHistoricalRecordList", returnMolHistoricalRecordList);
		return ReturnInfo.info(200, map);
	}
}
