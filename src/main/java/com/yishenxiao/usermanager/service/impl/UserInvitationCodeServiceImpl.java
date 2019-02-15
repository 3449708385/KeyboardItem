package com.yishenxiao.usermanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.yishenxiao.commons.utils.DateUtils;
import com.yishenxiao.commons.utils.GeneratingRandomNumber;
import com.yishenxiao.usermanager.beans.UserInvitationcode;
import com.yishenxiao.usermanager.dao.UserInvitationcodeMapper;
import com.yishenxiao.usermanager.service.UserInvitationCodeService;
import com.yishenxiao.usermanager.service.UserService;

@Service("userInvitationCodeService")
public class UserInvitationCodeServiceImpl implements UserInvitationCodeService {

	private Logger logger = LoggerFactory.getLogger(UserInvitationCodeServiceImpl.class);
	
	@Autowired(required = false)
	@Qualifier("userInvitationcodeMapper")
	private UserInvitationcodeMapper userInvitationcodeMapper;
	
	@Autowired(required = false)
	@Qualifier("userService")
	private UserService userService;
	
	@Override
	public List<UserInvitationcode> queryByInvitationCodeList(String invitationCode) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("invitationCode", invitationCode);
		return userInvitationcodeMapper.queryByInvitationCodeList(map);
	}

	@Override
	public String getInvitationCode(Long userId) {	
		return getInvitationCodeData(userId);
	}
	
	private String getInvitationCodeData(Long userId){
	  String invitationCode = GeneratingRandomNumber.getRandomNumAndStr(8);
	  List<UserInvitationcode> userInvitationcodeList = queryByInvitationCodeList(invitationCode);
	  if(userInvitationcodeList.size()!=0){
		  getInvitationCodeData(userId);
	  }else{
		  UserInvitationcode userInvitationcode = new UserInvitationcode();
		  userInvitationcode.setCreatetime(DateUtils.getNowTime());
		  userInvitationcode.setInvitationcode(invitationCode);
		  userInvitationcode.setUpdatetime(DateUtils.getNowTime());
		  userInvitationcode.setUserid(userId);
		 int cou = userInvitationcodeMapper.insert(userInvitationcode);
		 if(cou!=1){
			 logger.error("userId:"+userId+" ,插入用户生成的邀请码失败！");
		 }else{
			 return invitationCode;
		 }
	  }
	  return invitationCode;
	}

	@Override
	public List<UserInvitationcode> queryByUserId(Long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		return userInvitationcodeMapper.queryByUserId(map);
	}

}