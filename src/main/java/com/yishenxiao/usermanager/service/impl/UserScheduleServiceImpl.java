package com.yishenxiao.usermanager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yishenxiao.commons.beans.MolHistoricalRecord;
import com.yishenxiao.commons.service.MolHistoricalRecordService;
import com.yishenxiao.commons.utils.DateUtils;
import com.yishenxiao.commons.utils.PropertiesUtils;
import com.yishenxiao.commons.utils.StringUtils;
import com.yishenxiao.usermanager.beans.UserSchedule;
import com.yishenxiao.usermanager.dao.UserScheduleMapper;
import com.yishenxiao.usermanager.service.UserScheduleService;

@Service("userScheduleService")
public class UserScheduleServiceImpl implements UserScheduleService {
	
	private Logger logger = LoggerFactory.getLogger(UserScheduleServiceImpl.class);

	@Autowired(required = false)
	@Qualifier("userScheduleMapper")
	private UserScheduleMapper userScheduleMapper;
	
	@Autowired(required = false)
	@Qualifier("molHistoricalRecordService")
	private MolHistoricalRecordService molHistoricalRecordService;
	
	@Override
	public List<UserSchedule> queryByUIdList(Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		return userScheduleMapper.queryByUIdList(map);
	}

	@Override
	public int insertOneData(UserSchedule userSchedule) {
		return userScheduleMapper.insert(userSchedule);
	}

	@Override
	public int updateArticleType(Long id, Long articleTypeId, Integer type) {
		UserSchedule userSchedule = userScheduleMapper.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		if(type==1){
			if(StringUtils.judgeBlank(userSchedule.getArticletype())){
		   	  map.put("articleTypeStr", articleTypeId);
			}else{
			  map.put("articleTypeStr", ","+articleTypeId);
			}			
		}else{
			List<String> strlist = new ArrayList<String>();
			String[] str = userSchedule.getArticletype().split(",");
			for(int i=0;i<str.length;i++){
				strlist.add(str[i]);
			}
			strlist.remove(articleTypeId.toString());
			String temp = "";
			for(int i=0;i<strlist.size();i++){
				temp=temp+strlist.get(i);
			}
			map.put("articleTypeStr", temp);
		}
		return userScheduleMapper.updateArticleType(map);
	}

	@Override
	public int updateUserLoginCount(Long userId) {
		List<UserSchedule> userScheduleList = queryByUIdList(userId);
		Integer LoginmolCount=0;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", userScheduleList.get(0).getId());
		Properties properties=PropertiesUtils.getInfoConfigProperties();
		if(userScheduleList.get(0).getUserlogincount()>=0 && userScheduleList.get(0).getUserloginstate()==0){		
			int x=userScheduleList.get(0).getUserlogincount();			
			switch(x){
			    case 0:
			    	LoginmolCount=Integer.parseInt(properties.getProperty("oneMolCount"));
			    	map.put("molCount", LoginmolCount);
				    break;
				case 1:
					LoginmolCount=Integer.parseInt(properties.getProperty("twoMolCount"));
					map.put("molCount", LoginmolCount);
					break;
				case 2:
					LoginmolCount=Integer.parseInt(properties.getProperty("threeMolCount"));
					map.put("molCount", LoginmolCount);
					break;
				case 3:
					LoginmolCount=Integer.parseInt(properties.getProperty("fourMolCount"));
					map.put("molCount", LoginmolCount);
					break;
				default:				
					LoginmolCount=Integer.parseInt(properties.getProperty("fourMolCount"));
					map.put("molCount", LoginmolCount);			
			}
			int cou=userScheduleMapper.updateUserLoginCount(map);//更新用户使用条数,更新用户币的数量
			if(cou!=1){
				return 0;
			}else{
				//插入记录
				MolHistoricalRecord molHistoricalRecord = new MolHistoricalRecord();
				molHistoricalRecord.setCreatetime(DateUtils.getNowTime());
				molHistoricalRecord.setMolcount("-"+LoginmolCount.toString());
				molHistoricalRecord.setShowcontext("签到奖励");
				molHistoricalRecord.setType(2);
				molHistoricalRecord.setUserid(userId);
				molHistoricalRecord.setUpdatetime(DateUtils.getNowTime());
				molHistoricalRecord.setUserarticleid(0L);
				cou = molHistoricalRecordService.insertOneData(molHistoricalRecord);
				if(cou!=1){
					return 0;
				}else{
				   return 1;
				}
			}
		}else{
			logger.error(userId+" , The userId landing data exception");
			return 0;
		}	
	}

	@Override
	public int queryByAllDataCount() {
		return userScheduleMapper.queryByAllDataCount();
	}

	@Override
	public List<UserSchedule> queryPartData(int startCount, int eventCounts) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startCount", startCount);
		map.put("eventCounts", eventCounts);
		return userScheduleMapper.queryPartData(map);
	}

	@Override
	public int updateUserLoginState(Long id, int state) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("state", state);
		return userScheduleMapper.updateUserLoginState(map);
	}

	@Override
	public int updateUserHead(String headicon,Long id) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("headicon", headicon);
		map.put("id", id);
		return userScheduleMapper.updateUserHead(map);
	}

	@Override
	public int updateSubtractMolCount(Long id, Double rewardMolCount) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rewardMolCount", rewardMolCount);
		map.put("id", id);
		return userScheduleMapper.updateSubtractMolCount(map);
	}

	@Override
	public int updateIncreaseMolCount(Long id, Double extensionmonery) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("extensionmonery", extensionmonery);
		map.put("id", id);
		return userScheduleMapper.updateIncreaseMolCount(map);
	}

	@Override
	public List<UserSchedule> queryByPypass(Long userid, String pypasswd) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pypasswd", pypasswd);
		map.put("userId", userid);
		return userScheduleMapper.queryByPypass(map);
	}

	@Override
	public int updateUserAuthenticationState(Long id, int state) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", state);
		map.put("id", id);
		return userScheduleMapper.updateUserAuthenticationState(map);
	}

	@Override
	public List<UserSchedule> queryBypasswd(Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		return userScheduleMapper.queryBypasswd(map);
	}

	@Override
	public int setPyPwSetup(Long id, String hex) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pypass", hex);
		map.put("id", id);
		return userScheduleMapper.setPyPwSetup(map);
	}
}
