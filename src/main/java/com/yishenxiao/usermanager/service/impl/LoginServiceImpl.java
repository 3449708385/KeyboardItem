package com.yishenxiao.usermanager.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yishenxiao.commons.beans.ArticleType;
import com.yishenxiao.commons.beans.ProtertiesData;
import com.yishenxiao.commons.service.ArticleTypeService;
import com.yishenxiao.commons.service.ProtertiesDataService;
import com.yishenxiao.commons.utils.DateUtils;
import com.yishenxiao.commons.utils.GeneratingRandomNumber;
import com.yishenxiao.usermanager.beans.User;
import com.yishenxiao.usermanager.beans.UserInvitationcode;
import com.yishenxiao.usermanager.beans.UserSchedule;
import com.yishenxiao.usermanager.service.LoginService;
import com.yishenxiao.usermanager.service.UserInvitationCodeService;
import com.yishenxiao.usermanager.service.UserScheduleService;
import com.yishenxiao.usermanager.service.UserService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired(required = false)
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired(required = false)
	@Qualifier("userScheduleService")
	private UserScheduleService userScheduleService;
		
	@Autowired(required = false)
	@Qualifier("userInvitationCodeService")
	private UserInvitationCodeService userInvitationCodeService;
	
	@Autowired(required = false)
	@Qualifier("protertiesDataService")
	private ProtertiesDataService protertiesDataService;
	
	@Autowired(required = false)
	@Qualifier("articleTypeService")
	private ArticleTypeService articleTypeService;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.READ_COMMITTED, rollbackFor={Exception.class})
	public Map<String, Object> userLogin(String invitationCode, String noNativeCodePhone,
			String nativecode, String deviceIdentificationCode, int invitationCodeISBoolean) {
		Map<String, Object> map = new HashMap<String, Object>();
		//插入用户数据	
		User user = new User();
		user.setAccount(getUserStrId());
		user.setCreatetime(DateUtils.getNowTime());
		user.setEmail("");
		user.setInvitationcount(0);
		user.setLoginstate(1);
		user.setNativecode(nativecode);
		user.setNickname("");
		if(invitationCodeISBoolean==0){
		  List<UserInvitationcode> userInvitationCodeList = userInvitationCodeService.queryByInvitationCodeList(invitationCode);
		  if(userInvitationCodeList.size()==0){
			  map.put("loginState", 3);
			  return map;
		  }
		  List<User> userList = userService.queryByUserId(userInvitationCodeList.get(0).getUserid());
		  List<ProtertiesData> protertiesDataList = protertiesDataService.queryByDataKeyList("invitationCodeCount");
		  int invitationCodeCount=Integer.parseInt(protertiesDataList.get(0).getDatavalue());
		  //邀请码限制
		  if(!protertiesDataList.get(0).getDatavalue().contains(userList.get(0).getPhone())){
		      if(userList.get(0).getInvitationcount()>=invitationCodeCount){
		    	  map.put("loginState", 2);
				  return map;
			  }		
	      }	
		  //更新用户邀请码的使用个数
		  int cou = userService.updateUserInvitationCodeCount(userInvitationCodeList.get(0).getUserid());
		  if(cou!=1){
			  map.put("loginState", 1);
			  return map;
		  }
		  user.setParentextensionid(userInvitationCodeList.get(0).getUserid());
		}else{
		  user.setParentextensionid(0L);
		}
		user.setPasswd("");
		user.setPhone(nativecode+noNativeCodePhone);
        user.setState(0);
        user.setType(0);
        user.setUpdatetime(DateUtils.getNowTime());
        int cou=userService.insertOneData(user);
        if(cou==1){
        	List<ArticleType> articleTypeList = articleTypeService.queryBySelf();
			UserSchedule userSchedule = new UserSchedule();
			userSchedule.setArticletype(articleTypeList.get(0).getId().toString());
			userSchedule.setAuthenticationstate(0);
			userSchedule.setCreatetime(DateUtils.getNowTime());
			userSchedule.setEquipmentidentification(deviceIdentificationCode);
			userSchedule.setHeadicon(getHeadIcon());
			userSchedule.setMolcount(new BigDecimal("0"));
			userSchedule.setPypasswd("");
			userSchedule.setUpdatetime(DateUtils.getNowTime());
			userSchedule.setUserid(user.getId());
			userSchedule.setUserlogincount(1);
			userSchedule.setUserloginstate(1);
			cou=userScheduleService.insertOneData(userSchedule);
			if(cou!=1){
				map.put("loginState", 1);
			}else{
				String userInvitationCode = userInvitationCodeService.getInvitationCode(user.getId());
				map.put("loginState", 0);
				map.put("userInvitationCode", userInvitationCode);
				map.put("user", user);
				map.put("userSchedule", userSchedule);
			}
		}else{
			map.put("loginState", 1);
		}
		return map;
	}
	
	
	private String getUserStrId(){
	  String userStrId = GeneratingRandomNumber.getRandomSingleNum(8);
	  List<User> userList = userService.queryByaccount(userStrId);
	  if(userList.size()!=0){
		  getUserStrId();
	  }else{
		  return userStrId;
	  }
	  return userStrId;
	}
	
	private String getHeadIcon(){
		 Random rad = new Random();
		   int temp =rad.nextInt(15);
		   if(temp==0){
			 temp = 15;
		   }
		   String headicon="http://13.125.122.136:8011/currencyLogo/"+temp+".png";
		return headicon;	
	}

}
