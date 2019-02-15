package com.yishenxiao.commons.service.impl.quzrtz;

import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yishenxiao.commons.utils.SpringContextUtils;
import com.yishenxiao.usermanager.beans.UserArticleMol;
import com.yishenxiao.usermanager.beans.UserSchedule;
import com.yishenxiao.usermanager.service.UserArticleMolService;
import com.yishenxiao.usermanager.service.UserScheduleService;

public class ResetTodayMolMyCallable implements Callable<Integer> {

	private static Logger logger = LoggerFactory.getLogger(ResetTodayMolMyCallable.class);
	
	List<UserArticleMol> userArticleMolList;
	
	private UserArticleMolService userArticleMolService;
	
	private void getBean(){
		if(userArticleMolService==null){
			userArticleMolService = (UserArticleMolService)SpringContextUtils.getBean("userArticleMolService");
		}
	}
	
	public ResetTodayMolMyCallable(List<UserArticleMol> userArticleMolList){
		this.userArticleMolList = userArticleMolList;
	}
	
	@Override
	public Integer call() throws Exception {
	   getBean();
	   List<UserArticleMol> userArticleMolTempList = this.userArticleMolList;
	   for(int i=0;i<userArticleMolTempList.size();i++){
		 int cou = userArticleMolService.updateTodayMolCount(userArticleMolTempList.get(i).getId(),0.0);
		 if(cou!=1){
			 logger.error(userArticleMolTempList.get(i).getUserid()+" , 该userId午夜重置登陆状态失败！");
			 return 0;
		 }
	   }
	   return 1;
	}

}
