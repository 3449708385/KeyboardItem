package com.yishenxiao.usermanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Producer;
import com.yishenxiao.commons.beans.NativeCode;
import com.yishenxiao.commons.beans.PhoneSMS;
import com.yishenxiao.commons.beans.ProtertiesData;
import com.yishenxiao.commons.service.NativeCodeService;
import com.yishenxiao.commons.service.PhoneSMSService;
import com.yishenxiao.commons.service.ProtertiesDataService;
import com.yishenxiao.commons.utils.ChuangLanSmsUtil;
import com.yishenxiao.commons.utils.IpCheckUtils;
import com.yishenxiao.commons.utils.ReturnInfo;
import com.yishenxiao.commons.utils.StringUtils;
import com.yishenxiao.usermanager.beans.SignUpBean;
import com.yishenxiao.usermanager.beans.User;
import com.yishenxiao.usermanager.beans.UserAutoLogin;
import com.yishenxiao.usermanager.beans.UserInvitationcode;
import com.yishenxiao.usermanager.beans.UserLoginTransmission;
import com.yishenxiao.usermanager.beans.UserRegisterTransmission;
import com.yishenxiao.usermanager.beans.UserSchedule;
import com.yishenxiao.usermanager.service.LoginService;
import com.yishenxiao.usermanager.service.UserInvitationCodeService;
import com.yishenxiao.usermanager.service.UserScheduleService;
import com.yishenxiao.usermanager.service.UserService;

@Controller
@RequestMapping("login")
public class LoginController {

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired(required = false)
	@Qualifier("producer")
	private Producer captchaProducer;

	@Autowired(required = false)
	@Qualifier("redisTemplate")
	private RedisTemplate<String, Object> redisService;

	@Autowired(required = false)
	@Qualifier("userService")
	private UserService userService;

	@Autowired(required = false)
	@Qualifier("phoneSMSService")
	private PhoneSMSService phoneSMSService;

	@Autowired(required = false)
	@Qualifier("nativeCodeService")
	private NativeCodeService nativeCodeService;

	@Autowired(required = false)
	@Qualifier("userScheduleService")
	private UserScheduleService userScheduleService;

	@Autowired(required = false)
	@Qualifier("protertiesDataService")
	private ProtertiesDataService protertiesDataService;

	@Autowired(required = false)
	@Qualifier("loginService")
	private LoginService loginService;

	@Autowired(required = false)
	@Qualifier("userInvitationCodeService")
	private UserInvitationCodeService userInvitationCodeService;

	/**
	 * @info 用于注册发送手机验证码
	 * @param userId
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "userSendSMS", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo userSendSMS(HttpServletRequest req, @RequestBody UserRegisterTransmission urt) {
		ReturnInfo rInfo = new ReturnInfo();
		// 检查用户
		if (StringUtils.judgeBlank(urt.getNativecode()) || StringUtils.judgeBlank(urt.getNoNativeCodePhone())) {
			return ReturnInfo.error("参数错误！");
		}

		if (urt.getNativecode().equals("0086") && !urt.getNoNativeCodePhone().matches("^1[34578]\\d{9}$")) {
			return ReturnInfo.error("参数错误！");
		}

		List<ProtertiesData> protertiesDataList = null;
		/*
		 * // 判断手机是否注册 List<User> userlist =
		 * userService.queryByPhone(urt.getNativecode() +
		 * urt.getNoNativeCodePhone()); if (userlist.size() == 0) { // 判断要不要邀请码
		 * protertiesDataList =
		 * protertiesDataService.queryByDataKeyList("invitationCode"); int
		 * invitationCodeISBoolean =
		 * Integer.parseInt(protertiesDataList.get(0).getDatavalue()); if
		 * (invitationCodeISBoolean == 0) { // 手机邀请码限制检查 if
		 * (StringUtils.judgeBlank(urt.getInvitationCode())) { return
		 * ReturnInfo.error("参数错误！"); } List<UserInvitationcode>
		 * userInvitationCodeList = userInvitationCodeService
		 * .queryByInvitationCodeList(urt.getInvitationCode()); if
		 * (userInvitationCodeList.size() == 0) { return
		 * ReturnInfo.error("您获取的邀请码不存在!！"); } List<User> userList =
		 * userService.queryByUserId(userInvitationCodeList.get(0).getUserid());
		 * protertiesDataList = null; protertiesDataList =
		 * protertiesDataService.queryByDataKeyList("invitationCodeCount"); int
		 * invitationCodeCount =
		 * Integer.parseInt(protertiesDataList.get(0).getDatavalue()); //
		 * 邀请码限制,先检查无限邀请的手机号码 protertiesDataList = null; protertiesDataList =
		 * protertiesDataService.queryByDataKeyList("invitationExemptionPhone");
		 * if
		 * (!protertiesDataList.get(0).getDatavalue().contains(userList.get(0).
		 * getPhone())) { if (userList.get(0).getInvitationcount() >=
		 * invitationCodeCount) { return
		 * ReturnInfo.error("您获得的邀请码已经无法使用，请更换邀请码!！"); } } } }
		 */
		String phonecode = captchaProducer.createText();
		// 发送短信, 10分钟短信认证频率设置
		Object sessionCode = redisService.opsForValue().get(urt.getNativecode() + urt.getNoNativeCodePhone());
		if (sessionCode != null) {
			return ReturnInfo.error("10分钟以内验证码有效！");
		}
		// 手机号限制
		String ipAdd = IpCheckUtils.getIpAddr(req);
		List<PhoneSMS> phoneSMSList = phoneSMSService.queryByPhone(urt.getNativecode() + urt.getNoNativeCodePhone());
		if (phoneSMSList.size() != 0) {
			protertiesDataList = null;// 清空数据
			protertiesDataList = protertiesDataService.queryByDataKeyList("phoneCodeCount");
			if (phoneSMSList.get(0).getCount() >= Integer.parseInt(protertiesDataList.get(0).getDatavalue())) {
				return ReturnInfo.error("该手机号码今日短信数量已达限制！");
			}
			try {
				int result = ChuangLanSmsUtil.pushDomesticInfo(req, urt.getNoNativeCodePhone(), phonecode,
						urt.getNativecode());
				if (result == 0) {
					redisService.opsForValue().set(urt.getNativecode() + urt.getNoNativeCodePhone(), phonecode, 10,
							TimeUnit.MINUTES);
					rInfo = ReturnInfo.ok();
					// ip 地址计数加1,并记录ip下的手机号码
					if (!phoneSMSList.get(0).getIpaddr().contains(ipAdd)) {
						phoneSMSList.get(0).setIpaddr(phoneSMSList.get(0).getIpaddr() + "," + ipAdd);
						;
					}
					phoneSMSService.updateByCountAndData(phoneSMSList.get(0));
				} else {
					rInfo = ReturnInfo.error("短信发送失败！");
				}
			} catch (Exception e) {
				logger.error(urt.getNativecode() + urt.getNoNativeCodePhone() + "登陆验证码短信发送失败！");
				rInfo = ReturnInfo.error("登陆验证码短信发送失败");
			}
		} else {
			try {
				int result = ChuangLanSmsUtil.pushDomesticInfo(req, urt.getNoNativeCodePhone(), phonecode,
						urt.getNativecode());
				if (result == 0) {
					redisService.opsForValue().set(urt.getNativecode() + urt.getNoNativeCodePhone(), phonecode, 10,
							TimeUnit.MINUTES);
					rInfo = ReturnInfo.ok();
					PhoneSMS phoneSMS = new PhoneSMS();
					phoneSMS.setCount(1);
					phoneSMS.setIpaddr(ipAdd);
					phoneSMS.setPhone(urt.getNativecode() + urt.getNoNativeCodePhone());
					int cou = phoneSMSService.insertData(phoneSMS);
					if (cou != 1) {
						logger.error("用户手机短信计数+1失败，请检查原因！");
					}
				} else {
					rInfo = ReturnInfo.error("短信发送失败！");
				}
			} catch (Exception e) {
				logger.error(urt.getNativecode() + urt.getNoNativeCodePhone() + "登陆验证码短信发送失败！");
				rInfo = ReturnInfo.error("登陆验证码短信发送失败");
			}
		}
		return rInfo;
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "userLogin", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo userLogin(HttpServletRequest req, HttpServletResponse res,
			@RequestBody UserLoginTransmission ult) {
		if (StringUtils.judgeBlank(ult.getNativecode()) || StringUtils.judgeBlank(ult.getNoNativeCodePhone())
				|| StringUtils.judgeBlank(ult.getDeviceIdentificationCode())
				|| StringUtils.judgeBlank(ult.getPhoneCode())) {
			return ReturnInfo.error("参数错误！");
		}

		if (ult.getNativecode().equals("0086") && !ult.getNoNativeCodePhone().matches("^1[34578]\\d{9}$")) {
			return ReturnInfo.error("参数错误！");
		}

		// 检查验证码
		Object sessionCode = redisService.opsForValue().get(ult.getNativecode() + ult.getNoNativeCodePhone());
		if (sessionCode == null) {
			return ReturnInfo.error("手机验证码已经过期！");
		}
		if (!ult.getPhoneCode().equals(sessionCode.toString())) {
			return ReturnInfo.error("手机验证码错误！");
		} else {
			redisService.opsForValue().set(ult.getNativecode() + ult.getNoNativeCodePhone(), "", 1, TimeUnit.SECONDS);
		}

		// 判断手机是否注册
		List<User> userlist = userService.queryByPhone(ult.getNativecode() + ult.getNoNativeCodePhone());
		if (userlist.size() != 0) {
			// 走登陆流程， 更新数据库
			userlist.get(0).setLoginstate(1);
			int cou = userService.updateUser(userlist.get(0));// 登陆状态改变
			if (cou != 1) {
				return ReturnInfo.error("登陆失败,请稍后再试！");
			}
			// 拼装数据返回
			SignUpBean signUpBean = new SignUpBean();
			User user = userlist.get(0);
			List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(user.getId());
			UserSchedule userSchedule = userScheduleList.get(0);
			List<UserInvitationcode> userInvitationCodeList = userInvitationCodeService.queryByUserId(user.getId());
			List<ProtertiesData> protertiesDataList = protertiesDataService.queryByDataKeyList("invitationCodeCount");
			List<UserSchedule> userScheduleListPwd = userScheduleService.queryBypasswd(user.getId());
			// 32位token使用UUID来表示
			String accessToken = handleToken(user.getId());
			signUpBean.setUser(user);
			if (StringUtils.judgeBlank(userScheduleListPwd.get(0).getPypasswd())) {
				signUpBean.setSureType(0);
			} else {
				signUpBean.setSureType(1);
			}
			signUpBean.setAccessToken(accessToken);
			signUpBean.setUserInvitationCodeTotal(protertiesDataList.get(0).getDatavalue());
			signUpBean.setUserSchedule(userSchedule);
			signUpBean.setUserInvitationCode(userInvitationCodeList.get(0).getInvitationcode());
			signUpBean.setUserLogin(false);
			return ReturnInfo.info(200, signUpBean);
		}
		// 走注册流程
		// 判断邀请码
		List<ProtertiesData> protertiesDataList = protertiesDataService.queryByDataKeyList("invitationCode");
		int invitationCodeISBoolean = Integer.parseInt(protertiesDataList.get(0).getDatavalue());
		if (invitationCodeISBoolean == 0) {
			if (StringUtils.judgeBlank(ult.getInvitationCode())) {
				return ReturnInfo.error("参数错误！");
			}
		}
		Map<String, Object> map = loginService.userLogin(ult.getInvitationCode(), ult.getNoNativeCodePhone(),
				ult.getNativecode(), ult.getDeviceIdentificationCode(), invitationCodeISBoolean);
		int loginState = (int) map.get("loginState");
		if (loginState == 1) {
			return ReturnInfo.error("注册失败，请重试!");
		} else if (loginState == 0) {
			// 返回数据到前端
			User user = (User) map.get("user");
			UserSchedule userSchedule = (UserSchedule) map.get("userSchedule");
			String userInvitationCode = (String) map.get("userInvitationCode");
			protertiesDataList = null;
			protertiesDataList = protertiesDataService.queryByDataKeyList("invitationCodeCount");
			List<UserSchedule> userScheduleListPwd = userScheduleService.queryBypasswd(user.getId());
			// token
			String accessToken = handleToken(user.getId());
			SignUpBean signUpBean = new SignUpBean();
			signUpBean.setUser(user);
			if (StringUtils.judgeBlank(userScheduleListPwd.get(0).getPypasswd())) {
				signUpBean.setSureType(0);
			} else {
				signUpBean.setSureType(1);
			}
			signUpBean.setUserLogin(true);
			signUpBean.setUserInvitationCodeTotal(protertiesDataList.get(0).getDatavalue());
			signUpBean.setUserInvitationCode(userInvitationCode);
			signUpBean.setAccessToken(accessToken);
			signUpBean.setUserSchedule(userSchedule);
			return ReturnInfo.info(200, signUpBean);
		} else if (loginState == 2) {
			return ReturnInfo.error("您获得的邀请码已经无法注册，请更换邀请码!");
		} else if (loginState == 3) {
			return ReturnInfo.error("您获得的邀请码并不存在，请更换邀请码!");
		} else {
			return ReturnInfo.error("注册失败，请联系客服!");
		}

	}

	/**
	 * @info 用户自动登录
	 * @param userAutoLoginBean
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "userAutoLogin", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo userAutoLogin(@RequestBody UserAutoLogin userAutoLogin) {
		if (userAutoLogin.getUserId()==null) {
			return ReturnInfo.error("参数错误！");
		}
		int cou = userService.updateLoginstate(userAutoLogin.getUserId(), 2); // 0：离线；1：在线；2：活跃
	    if(cou==0){
	    	logger.error(userAutoLogin.getUserId()+"user登陆失败！");
	    	return ReturnInfo.error("自动登录失败！");
	    }
		List<User> userList = userService.queryByUserId(userAutoLogin.getUserId());
		List<ProtertiesData> protertiesDataList = protertiesDataService.queryByDataKeyList("invitationCodeCount");
		List<UserSchedule> userScheduleListPwd = userScheduleService.queryBypasswd(userAutoLogin.getUserId());
		List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(userList.get(0).getId());
		// token
		String accessToken = handleToken(userAutoLogin.getUserId());
		String userInvitationCode = userInvitationCodeService.queryByUserId(userList.get(0).getId()).get(0).getInvitationcode();
		SignUpBean signUpBean = new SignUpBean();
		signUpBean.setUser(userList.get(0));
		if (StringUtils.judgeBlank(userScheduleListPwd.get(0).getPypasswd())) {
			signUpBean.setSureType(0);
		} else {
			signUpBean.setSureType(1);
		}
		signUpBean.setUserLogin(true);
		signUpBean.setUserInvitationCodeTotal(protertiesDataList.get(0).getDatavalue());
		signUpBean.setUserInvitationCode(userInvitationCode);
		signUpBean.setAccessToken(accessToken);
		signUpBean.setUserSchedule(userScheduleList.get(0));
		return ReturnInfo.info(200, signUpBean);
	}

	/**
	 * @info 用户退出
	 * @param userLogoutBean
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "userLogout", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo userLogout(@RequestBody UserAutoLogin userAutoLogin) {
		if (userAutoLogin.getUserId()==null) {
			return ReturnInfo.error("参数错误！");
		} // 修改用户为离线状态 User user =
		int cou = userService.updateLoginstate(userAutoLogin.getUserId(), 0); // 0：离线；1：在线；2：活跃
	    if(cou==0){
	    	logger.error(userAutoLogin.getUserId()+"user登出失败！");
	    	return ReturnInfo.error("退出登录失败！");
	    }
		
		return ReturnInfo.ok();
	}

	private String handleToken(Long userId) {
		String accessToken = UUID.randomUUID().toString().replace("-", "");
		String oldToken = (String) redisService.opsForValue().get(String.valueOf(userId));
		if (!StringUtils.judgeBlank(oldToken)) {
			redisService.delete(oldToken);
		}
		redisService.opsForValue().set(accessToken, String.valueOf(userId));
		redisService.opsForValue().set(String.valueOf(userId), accessToken);
		return accessToken;
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("getNativeCode")
	public @ResponseBody ReturnInfo getNativeCode() {
		List<NativeCode> nativeCodeList = nativeCodeService.queryByAllData();
		for (NativeCode nativeCode : nativeCodeList) {
			nativeCode.setNativeCodeStr(nativeCode.getNativecode());
			nativeCode
					.setNativecode("+" + nativeCode.getNativecode().substring(2, nativeCode.getNativecode().length()));
		}
		List<ProtertiesData> protertiesDataList = protertiesDataService.queryByDataKeyList("invitationCode");
		int invitationCodeISBoolean = Integer.parseInt(protertiesDataList.get(0).getDatavalue());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("invitationCodeISBoolean", invitationCodeISBoolean);
		map.put("nativeCodeList", nativeCodeList);
		return ReturnInfo.info(200, map);
	}
}
