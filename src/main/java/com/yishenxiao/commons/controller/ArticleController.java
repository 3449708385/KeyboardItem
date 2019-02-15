package com.yishenxiao.commons.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.Sha512Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yishenxiao.commons.beans.ArticleType;
import com.yishenxiao.commons.beans.ArticleTypeData;
import com.yishenxiao.commons.beans.ClientUpdateData;
import com.yishenxiao.commons.beans.PaginationData;
import com.yishenxiao.commons.beans.PublishArticle;
import com.yishenxiao.commons.beans.UpdateArticleTypeData;
import com.yishenxiao.commons.service.ArticleThingService;
import com.yishenxiao.commons.service.ArticleTypeService;
import com.yishenxiao.commons.utils.ReturnInfo;
import com.yishenxiao.commons.utils.StringUtils;
import com.yishenxiao.usermanager.beans.UserSchedule;
import com.yishenxiao.usermanager.service.UserArticleService;
import com.yishenxiao.usermanager.service.UserScheduleService;

@Controller
@RequestMapping("article")
public class ArticleController {

	@Autowired(required = false)
	@Qualifier("articleTypeService")
	private ArticleTypeService articleTypeService;

	@Autowired(required = false)
	@Qualifier("userScheduleService")
	private UserScheduleService userScheduleService;

	@Autowired(required = false)
	@Qualifier("articleThingService")
	private ArticleThingService articleThingService;

	@Autowired(required = false)
	@Qualifier("userArticleService")
	private UserArticleService userArticleService;

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "getArticleTypeData", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo getArticleTypeData(@RequestBody ArticleTypeData articleTypeData) {
		if (articleTypeData.getUserId() == null) {
			return ReturnInfo.error("参数错误！");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<ArticleType> articleTypeList = articleTypeService.getarticleTypeData();
		List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(articleTypeData.getUserId());
		String[] tempStr = userScheduleList.get(0).getArticletype().split(",");
		for (int i = 0; i < tempStr.length; i++) {
			for (int t = 0; t < articleTypeList.size(); t++) {
				if (Long.parseLong(tempStr[i]) == articleTypeList.get(t).getId()) {
					articleTypeList.get(t).setHasSelect(true);
				}
			}
		}
		map.put("articleType", articleTypeList);
		map.put("mol", userScheduleList.get(0).getMolcount());
		return ReturnInfo.info(200, map);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "updateArticleTypeData", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo updateArticleTypeData(@RequestBody UpdateArticleTypeData updateArticleTypeData) {
		if (updateArticleTypeData.getArticleTypeId() == null || updateArticleTypeData.getUserId() == null || updateArticleTypeData.getType()==null || updateArticleTypeData.getType()!=0 &&updateArticleTypeData.getType()!=1) {
			return ReturnInfo.error("参数错误！");
		}
		List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(updateArticleTypeData.getUserId());
		int cou = userScheduleService.updateArticleType(userScheduleList.get(0).getId(),
				updateArticleTypeData.getArticleTypeId(), updateArticleTypeData.getType());
		if(cou!=1){
			return ReturnInfo.error("加载失败！");
		}
		return ReturnInfo.ok();
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping("getUserArticleTypeData")
	public @ResponseBody ReturnInfo getUserArticleTypeData() {
		List<ArticleType> articleTypeList = articleTypeService.getarticleTypeData();
		return ReturnInfo.info(200, articleTypeList);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "userReleaseArticle", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo userReleaseArticle(@RequestBody PublishArticle publishArticle) {
		if (StringUtils.judgeBlank(publishArticle.getArticleContext()) || publishArticle.getRewardMolCount() == null
				|| publishArticle.getRewardMolCount() < 0 || publishArticle.getUserId() == null
				|| StringUtils.judgeBlank(publishArticle.getArticType())
				|| StringUtils.judgeBlank(publishArticle.getPwpass())) {
			return ReturnInfo.error("参数错误！");
		}
		if(publishArticle.getRewardMolCount()!=0 && publishArticle.getRewardMolCount()<1){
			return ReturnInfo.error("不能输入小于1的发布mol数！");
		}
		List<UserSchedule> userScheduleList = userScheduleService.queryByUIdList(publishArticle.getUserId());
		if (!userScheduleList.get(0).getPypasswd().equals(new Sha512Hash(publishArticle.getPwpass()).toHex())) {
			return ReturnInfo.error("参数错误！");
		}
		if (userScheduleList.get(0).getMolcount().doubleValue() < publishArticle.getRewardMolCount()) {
			return ReturnInfo.info(500, "您的余额不足以支付奖励金额！");
		}
		int cou = articleThingService.userReleaseArticle(publishArticle);
		if (cou == 0) {
			return ReturnInfo.info(500, "发布失败，请到查看页面重新尝试！");
		}
		return ReturnInfo.ok();
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "userCancelRelease", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo userCancelRelease(@RequestBody PublishArticle publishArticle) {
		if (publishArticle.getUserArticleId() == null || publishArticle.getUserId() == null || publishArticle.getType()==null) {
			return ReturnInfo.error("参数错误！");
		}
		int cou = articleThingService.userCancelRelease(publishArticle);
		if (cou == 0) {
			return ReturnInfo.error("操作失败，请稍后再试！");
		}
		return ReturnInfo.ok();
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "clientUpdateData", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo clientUpdateData(@RequestBody PaginationData paginationData) {
		if (paginationData.getUserId() == null || paginationData.getLimitCount() == null) {
			return ReturnInfo.error("参数错误！");
		}
		List<ClientUpdateData> clientUpdateDataList = articleThingService.clientUpdateData(paginationData);
		if(clientUpdateDataList==null){
			return ReturnInfo.error("加载失败，请重试！");
		}
		return ReturnInfo.info(200, clientUpdateDataList);
	}
	
	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "userSelfRelease", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo userSelfRelease(@RequestBody PaginationData paginationData) {
		if (paginationData.getUserId() == null || paginationData.getLimitCount() == null || paginationData.getId()==null) {
			return ReturnInfo.error("参数错误！");
		}
		List<ClientUpdateData> clientUpdateDataList = articleThingService.userSelfRelease(paginationData);
		if(clientUpdateDataList==null){
			return ReturnInfo.error("加载失败，请重试！");
		}
		return ReturnInfo.info(200, clientUpdateDataList);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@RequestMapping(value = "advertisingRecharge", method = RequestMethod.POST, consumes = "application/json")
	public @ResponseBody ReturnInfo advertisingRecharge(@RequestBody ArticleTypeData articleTypeData) {
		if (articleTypeData.getId() == null || articleTypeData.getMolCount() == null
				|| articleTypeData.getMolCount() <= 0) {
			return ReturnInfo.error("参数错误！");
		}
		int cou = articleThingService.advertisingRecharge(articleTypeData.getId(), articleTypeData.getMolCount());
		if (cou == 0) {
			return ReturnInfo.error("充值失败，请重试！");
		}
		return ReturnInfo.ok();
	}

}
