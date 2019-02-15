package com.yishenxiao.commons.service.impl.quzrtz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.yishenxiao.commons.beans.ClientUpdateData;
import com.yishenxiao.commons.beans.PaginationData;
import com.yishenxiao.commons.utils.DateUtils;
import com.yishenxiao.commons.utils.GeneratingRandomNumber;
import com.yishenxiao.commons.utils.PropertiesUtils;
import com.yishenxiao.commons.utils.SpringContextUtils;
import com.yishenxiao.commons.utils.WeiboShortLink;
import com.yishenxiao.commons.utils.filter.AESUtil;
import com.yishenxiao.usermanager.beans.UserArticle;
import com.yishenxiao.usermanager.service.UserScheduleService;

public class ClientUpdateDataMyCallable implements Callable<List<ClientUpdateData>> {

	private static Logger logger = LoggerFactory.getLogger(ClientUpdateDataMyCallable.class);
	
	private Map<String, Object> map;
	
	private UserScheduleService userScheduleService;
	
	private MongoTemplate mongoService;
	
	private void getBean(){
		if(userScheduleService==null){
			userScheduleService = (UserScheduleService)SpringContextUtils.getBean("userScheduleService");
			mongoService = (MongoTemplate)SpringContextUtils.getBean("mongoService");
		}
	}
	
	public ClientUpdateDataMyCallable(Map<String, Object> map){
		this.map = map;
	}
	
	@Override
	public List<ClientUpdateData> call() throws Exception {
	   getBean();
	   List<ClientUpdateData> clientUpdateDataList = new ArrayList<ClientUpdateData>();
	   List<UserArticle> userArticleTempList = (List<UserArticle>) map.get("userArticleList");
	   PaginationData paginationData = (PaginationData) map.get("paginationData");
	   for (int i = 0; i < userArticleTempList.size(); i++) {
			UserArticle userArticle = userArticleTempList.get(i);
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
				clientUpdateData.setState(userArticle.getState());
				String dataId = new Sha256Hash(userArticle.getId() + paginationData.getUserId()
						+ DateUtils.getNowTime().getTime() + GeneratingRandomNumber.getRandomNumAndStr(8)).toHex();
				String param = "userArticleId=" + userArticle.getId()
						+ "&userId=" + paginationData.getUserId() + "&shareUserId=" + userArticle.getUserid()
						+ "&dataId=" + dataId;
				String asepawd=PropertiesUtils.getInfoConfigProperties().getProperty("ase.pawd");
				String paramter="";
				try {
					paramter = AESUtil.encryptAES(param, asepawd);
				} catch (Exception e) {
					logger.error("url aes Encryption failed!");
					return null;
				}
				String networkUrl = "http://www.mol.one/KeyboardItem/index.html?" + paramter;
				String urlTemp = WeiboShortLink.sinaShortUrl(networkUrl);
				String content = map.get("articleContext").toString();
				clientUpdateData.setNetworkUrl(urlTemp);
				clientUpdateData.setContext(content);
				clientUpdateData.setForward(userArticle.getForwardcount());
				String dataCount = PropertiesUtils.getInfoConfigProperties().get("dataCount").toString();
				if (userArticle.getExtensionmonery().compareTo(new BigDecimal(dataCount)) == -1) {
					clientUpdateData.setMoldata("仅自己可见");
				} else {
					clientUpdateData.setMoldata("总量：" + userArticle.getExtensionmonery() + " / "
							+ new BigDecimal(map.get("StartMol").toString()).setScale(2, BigDecimal.ROUND_DOWN)
							+ "MOL");
				}
				clientUpdateData.setReadCount(userArticle.getClickurlcount());
				String temp = map.get("picture").toString();
				if(!temp.equals("[]")){							
					temp = temp.substring(1, temp.length() - 1);
					String[] strTemp = temp.split(", http");
					List<String> pictureList = Arrays.asList(strTemp);
					if(pictureList.size()!=0){
						for(int t=0;t<pictureList.size();t++){
							if(t>0){
								pictureList.set(t, "http"+pictureList.get(t));
							}
						}
						clientUpdateData.setPictureList(pictureList);
					}	
				}
				clientUpdateData.setId(userArticle.getId());
				clientUpdateDataList.add(clientUpdateData);
			}
		}
	   return clientUpdateDataList;
	}
}
