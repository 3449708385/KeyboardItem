package com.yishenxiao.usermanager.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.Sha512Hash;
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
import com.yishenxiao.commons.beans.PhoneSMS;
import com.yishenxiao.commons.beans.ProtertiesData;
import com.yishenxiao.commons.beans.UserLoginDataReturn;
import com.yishenxiao.commons.service.PhoneSMSService;
import com.yishenxiao.commons.service.ProtertiesDataService;
import com.yishenxiao.commons.utils.ChuangLanSmsUtil;
import com.yishenxiao.commons.utils.IpCheckUtils;
import com.yishenxiao.commons.utils.ReturnInfo;
import com.yishenxiao.commons.utils.StringUtils;
import com.yishenxiao.usermanager.beans.PypwSetup;
import com.yishenxiao.usermanager.beans.SignUpBean;
import com.yishenxiao.usermanager.beans.User;
import com.yishenxiao.usermanager.beans.UserIdentification;
import com.yishenxiao.usermanager.beans.UserLoginCount;
import com.yishenxiao.usermanager.beans.UserPhoneCode;
import com.yishenxiao.usermanager.beans.UserSchedule;
import com.yishenxiao.usermanager.service.UserIdentificationService;
import com.yishenxiao.usermanager.service.UserInvitationCodeService;
import com.yishenxiao.usermanager.service.UserScheduleService;
import com.yishenxiao.usermanager.service.UserService;

import jodd.util.URLDecoder;

@Controller
@RequestMapping("user")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired(required = false)
	@Qualifier("userInvitationcodeService")
	private UserInvitationCodeService userInvitationcodeService;

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
	@Qualifier("producer")
	private Producer captchaProducer;

	@Autowired(required = false)
	@Qualifier("phoneSMSService")
	private PhoneSMSService phoneSMSService;

	@Autowired(required = false)
	@Qualifier("protertiesDataService")
	private ProtertiesDataService protertiesDataService;

	@Autowired(required = false)
	@Qualifier("userInvitationCodeService")
	private UserInvitationCodeService userInvitationCodeService;

	@Autowired(required = false)
	@Qualifier("userIdentificationService")
	private UserIdentificationService userIdentificationService;

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "userLoginCount", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo userLoginCount(@RequestBody UserLoginCount userLoginCount) {
		if (userLoginCount.getUserId() == null) {
			return ReturnInfo.error("参数错误！");
		}
		int cou = userScheduleService.updateUserLoginCount(userLoginCount.getUserId());// 判断状态，在原有的基础上+1
		if (cou == 0) {
			return ReturnInfo.info(500, "fail！");
		}
		return ReturnInfo.info(200, "success！");
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "userUpdateHead", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo userUpdateHead(@RequestBody UserSchedule userSchedule) {
		if (StringUtils.judgeBlank(userSchedule.getHeadicon()) || userSchedule.getUserid() == null) {
			return ReturnInfo.error("参数错误！");
		}
		String headIcon = "http://gosmallapp.com/" + userSchedule.getHeadicon() + "?x-oss-process=style/ysx_66";
		List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(userSchedule.getUserid());
		int cou = userScheduleService.updateUserHead(headIcon, userScheduleList.get(0).getId());// 判断状态，在原有的基础上+1
		if (cou == 0) {
			return ReturnInfo.info(500, "fail！");
		}
		List<User> userList = userService.queryByUserId(userSchedule.getUserid());
		List<UserSchedule> userScheduleList2 = userScheduleService.queryByUIdList(userSchedule.getUserid());
		String userInvitationCode = userInvitationCodeService.queryByUserId(userList.get(0).getId()).get(0).getInvitationcode();
		List<ProtertiesData> protertiesDataList = protertiesDataService.queryByDataKeyList("invitationCodeCount");
		List<UserSchedule> userScheduleListPwd = userScheduleService.queryBypasswd(userList.get(0).getId());
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
		signUpBean.setAccessToken(handleToken(userList.get(0).getId()));
		signUpBean.setUserSchedule(userScheduleList2.get(0));
		return ReturnInfo.info(200, signUpBean);
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
	@RequestMapping(value = "userUpdateNickName", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo userUpdateNickName(@RequestBody User user) {// 在这里，type=1是安卓，type=2是ios
		if (StringUtils.judgeBlank(user.getNickname()) || user.getId() == null || user.getType() == null
				|| user.getType() != 1 && user.getType() != 2) {
			return ReturnInfo.error("参数错误！");
		}
		if (user.getType() == 1) {
			try {
				user.setNickname(URLDecoder.decode(user.getNickname(), "UTF-8"));
			} catch (Exception e) {
				logger.error("安卓昵称解码失败！");
			}
		}
		int cou = userService.userUpdateNickName(user.getNickname(), user.getId());
		if (cou == 0) {
			return ReturnInfo.info(500, "fail！");
		}
		List<User> userList = userService.queryByUserId(user.getId());
		List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(user.getId());
		String userInvitationCode = userInvitationCodeService.queryByUserId(userList.get(0).getId()).get(0).getInvitationcode();
		List<ProtertiesData> protertiesDataList = protertiesDataService.queryByDataKeyList("invitationCodeCount");
		List<UserSchedule> userScheduleListPwd = userScheduleService.queryBypasswd(user.getId());
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
		signUpBean.setAccessToken(handleToken(userList.get(0).getId()));
		signUpBean.setUserSchedule(userScheduleList.get(0));
		return ReturnInfo.info(200, signUpBean);
	}

	/**
	 * @info 用于更改支付密码发送手机验证码
	 * @param userId
	 * @return
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "userPhoneCode", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo userPhoneCode(HttpServletRequest req, @RequestBody UserPhoneCode userPhoneCode) {	
		// 检查用户
		if (StringUtils.judgeBlank(userPhoneCode.getNativecode())
				|| StringUtils.judgeBlank(userPhoneCode.getNoNativeCodePhone()) || userPhoneCode.getType() == null || userPhoneCode.getType()!=0 && userPhoneCode.getType()!=1) {
			return ReturnInfo.error("参数错误！");
		}
		if (userPhoneCode.getNativecode().equals("0086")
				&& !userPhoneCode.getNoNativeCodePhone().matches("^1[34578]\\d{9}$")) {
			return ReturnInfo.error("参数错误！");
		}
		ReturnInfo rInfo = new ReturnInfo();
		String temp = "";
		if (userPhoneCode.getType() == 0) {
			temp = "setThePassword";
		} else {
			temp = "forgetThePassword";
		}
		String phonecode = captchaProducer.createText();
		// 发送短信
		// 10分钟短信认证频率设置
		Object sessionCode = redisService.opsForValue()
				.get(userPhoneCode.getNativecode() + userPhoneCode.getNoNativeCodePhone() + temp);
		if (sessionCode != null) {
			return ReturnInfo.error("10分钟以内验证码有效！");
		}

		// 手机号限制
		List<ProtertiesData> protertiesDataList = null;
		String ipAdd = IpCheckUtils.getIpAddr(req);
		List<PhoneSMS> phoneSMSList = phoneSMSService
				.queryByPhone(userPhoneCode.getNativecode() + userPhoneCode.getNoNativeCodePhone());
		if (phoneSMSList.size() != 0) {
			protertiesDataList = protertiesDataService.queryByDataKeyList("phoneCodeCount");
			if (phoneSMSList.get(0).getCount() >= Integer.parseInt(protertiesDataList.get(0).getDatavalue())) {
				return ReturnInfo.error("该手机号码今日短信数量已达限制！");
			}
			try {
				int result = ChuangLanSmsUtil.pushDomesticInfo(req, userPhoneCode.getNoNativeCodePhone(), phonecode,
						userPhoneCode.getNativecode());
				if (result == 0) {
					redisService.opsForValue().set(
							userPhoneCode.getNativecode() + userPhoneCode.getNoNativeCodePhone() + temp, phonecode, 10,
							TimeUnit.MINUTES);
					rInfo = ReturnInfo.ok();
					// ip 地址计数加1,并记录ip下的手机号码
					if (!phoneSMSList.get(0).getIpaddr().contains(ipAdd)) {
						phoneSMSList.get(0).setIpaddr(phoneSMSList.get(0).getIpaddr() + "," + ipAdd);
					}
					phoneSMSService.updateByCountAndData(phoneSMSList.get(0));
				} else {
					rInfo = ReturnInfo.error("短信发送失败！");
				}
			} catch (Exception e) {
				logger.error(userPhoneCode.getNativecode() + userPhoneCode.getNoNativeCodePhone() + "登陆验证码短信发送失败！");
				rInfo = ReturnInfo.error("登陆验证码短信发送失败");
			}
		} else {
			try {
				int result = ChuangLanSmsUtil.pushDomesticInfo(req, userPhoneCode.getNoNativeCodePhone(), phonecode,
						userPhoneCode.getNativecode());
				if (result == 0) {
					redisService.opsForValue().set(
							userPhoneCode.getNativecode() + userPhoneCode.getNoNativeCodePhone() + temp, phonecode, 10,
							TimeUnit.MINUTES);
					rInfo = ReturnInfo.ok();
					PhoneSMS phoneSMS = new PhoneSMS();
					phoneSMS.setCount(1);
					phoneSMS.setIpaddr(ipAdd);
					phoneSMS.setPhone(userPhoneCode.getNativecode() + userPhoneCode.getNoNativeCodePhone());
					int cou = phoneSMSService.insertData(phoneSMS);
					if (cou != 1) {
						logger.error("用户手机短信计数+1失败，请检查原因！");
					}
				} else {
					rInfo = ReturnInfo.error("短信发送失败！");
				}
			} catch (Exception e) {
				logger.error(userPhoneCode.getNativecode() + userPhoneCode.getNoNativeCodePhone() + "登陆验证码短信发送失败！");
				rInfo = ReturnInfo.error("登陆验证码短信发送失败");
			}
		}
		return rInfo;
	}

	/*
	 * 支付验证码验证
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "userPhoneCodeCheck", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo userPhoneCodeCheck(@RequestBody UserPhoneCode userPhoneCode) {
		// 检查用户
		if (StringUtils.judgeBlank(userPhoneCode.getNativecode())
				|| StringUtils.judgeBlank(userPhoneCode.getNoNativeCodePhone())
				|| StringUtils.judgeBlank(userPhoneCode.getPhoneCode()) || userPhoneCode.getType() == null || userPhoneCode.getType()!=0 && userPhoneCode.getType()!=1) {
			return ReturnInfo.error("参数错误！");
		}
		if (userPhoneCode.getNativecode().equals("0086")
				&& !userPhoneCode.getNoNativeCodePhone().matches("^1[34578]\\d{9}$")) {
			return ReturnInfo.error("参数错误！");
		}

		String temp = "";
		if (userPhoneCode.getType() == 0) {
			temp = "setThePassword";
		} else {
			temp = "forgetThePassword";
		}
		// 检查验证码
		Object sessionCode = redisService.opsForValue()
				.get(userPhoneCode.getNativecode() + userPhoneCode.getNoNativeCodePhone() + temp);
		if (sessionCode == null) {
			return ReturnInfo.error("支付验证码错误或者已经过期！");
		}
		if (!userPhoneCode.getPhoneCode().equals(sessionCode.toString())) {
			return ReturnInfo.error("手机验证码错误或者已经过期！");
		} else {
			redisService.opsForValue().set(userPhoneCode.getNativecode() + userPhoneCode.getNativecode() + temp, "", 1,
					TimeUnit.SECONDS);
		}
		return ReturnInfo.ok();
	}

	/*
	 * 重置支付密码设置
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "pypwSetup", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo pypwSetup(@RequestBody PypwSetup pypwSetup) {
		// 检查用户
		if (StringUtils.judgeBlank(pypwSetup.getPypasswd()) || pypwSetup.getUserId() == null) {
			return ReturnInfo.error("参数错误！");
		}
		List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(pypwSetup.getUserId());
		int cou = userScheduleService.setPyPwSetup(userScheduleList.get(0).getId(),
				new Sha512Hash(pypwSetup.getPypasswd()).toHex());
		if (cou != 1) {
			return ReturnInfo.error("设置支付密码失败！");
		}
		return ReturnInfo.ok();
	}

	/*
	 * 忘记支付密码设置
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "checkUserIdentification", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo checkUserIdentification(@RequestBody PypwSetup pypwSetup) {
		// 检查用户
		if (pypwSetup.getUserId() == null || StringUtils.judgeBlank(pypwSetup.getIdentificationCard())) {
			return ReturnInfo.error("参数错误！");
		}
		List<UserIdentification> userIdentificationList = userIdentificationService
				.queryByUserIdAndIdentification(pypwSetup.getUserId(), pypwSetup.getIdentificationCard());
		if (userIdentificationList.size() > 0) {
			return ReturnInfo.ok();
		} else {
			return ReturnInfo.error("身份证输入错误！");
		}
	}

	/*
	 * 支付密码验证
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "pypwCheck", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo pypwCheck(@RequestBody UserSchedule userSchedule) {
		// 检查用户
		if (StringUtils.judgeBlank(userSchedule.getPypasswd()) || userSchedule.getUserid() == null) {
			return ReturnInfo.error("参数错误！");
		}
		List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(userSchedule.getUserid());
		List<UserSchedule> uslist = userScheduleService.queryByPypass(userScheduleList.get(0).getUserid(),
				new Sha512Hash(userSchedule.getPypasswd()).toHex());
		if (uslist.size() == 1) {
			return ReturnInfo.ok();
		}
		return ReturnInfo.error("支付密码不正确！");
	}

	/*
	 * 邀请数据
	 */
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "userInvitationCodeData", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo userInvitationCodeData(@RequestBody UserSchedule userSchedule) {
		// 检查用户
		if (userSchedule.getUserid() == null) {
			return ReturnInfo.error("参数错误！");
		}
		List<User> userList = userService.queryByUserId(userSchedule.getUserid());
		List<ProtertiesData> protertiesDataList = protertiesDataService.queryByDataKeyList("invitationCodeCount");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("todayCount", userList.get(0).getInvitationcount());
		map.put("totalCount", protertiesDataList.get(0).getDatavalue());
		return ReturnInfo.info(200, map);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "userLoginData", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo userLoginData(@RequestBody UserLoginCount userLoginCount) {
		if (userLoginCount.getUserId() == null) {
			return ReturnInfo.error("参数错误！");
		}
		List<UserLoginDataReturn> list = new LinkedList<UserLoginDataReturn>();
		List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(userLoginCount.getUserId());
		List<Integer> days = getList(userScheduleList.get(0).getUserlogincount() + 1);
		Collections.sort(days);
		list.add(new UserLoginDataReturn(1, days.get(0), userScheduleList.get(0).getUserlogincount() + 1,
				userScheduleList.get(0).getUserloginstate()));
		list.add(new UserLoginDataReturn(2, days.get(1), userScheduleList.get(0).getUserlogincount() + 1,
				userScheduleList.get(0).getUserloginstate()));
		list.add(new UserLoginDataReturn(3, days.get(2), userScheduleList.get(0).getUserlogincount() + 1,
				userScheduleList.get(0).getUserloginstate()));
		list.add(new UserLoginDataReturn(4, days.get(3), userScheduleList.get(0).getUserlogincount() + 1,
				userScheduleList.get(0).getUserloginstate()));
		list.add(new UserLoginDataReturn(5, days.get(4), userScheduleList.get(0).getUserlogincount() + 1,
				userScheduleList.get(0).getUserloginstate()));
		return ReturnInfo.info(200, list);
	}

	private List<Integer> getDayData(int today, List<Integer> str) {
		if (today > 0) {
			if (str.size() < 5) {
				str.add(today--);
			} else {
				return str;
			}
			return getDayData(today, str);
		} else {
			return str;
		}
	}

	private List<Integer> getList(int today) {
		List<Integer> str = new ArrayList<Integer>();
		str = getDayData(today + 2, str);
		if (str.size() < 5) {
			int c = 5 - str.size();
			for (int i = 0; i < c; i++) {
				Collections.sort(str);
				str.add(str.get(str.size() - 1) + 1);
			}
		}
		return str;
	}
}
