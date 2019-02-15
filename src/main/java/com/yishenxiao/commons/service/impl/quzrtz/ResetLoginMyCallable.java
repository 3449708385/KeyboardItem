package com.yishenxiao.commons.service.impl.quzrtz;

import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yishenxiao.commons.utils.SpringContextUtils;
import com.yishenxiao.usermanager.beans.UserSchedule;
import com.yishenxiao.usermanager.service.UserScheduleService;

public class ResetLoginMyCallable implements Callable<Integer> {

	private static Logger logger = LoggerFactory.getLogger(ResetLoginMyCallable.class);
	
	List<UserSchedule> userScheduleList;
	
	private UserScheduleService userScheduleService;
	
	private void getBean(){
		if(userScheduleService==null){
			userScheduleService = (UserScheduleService)SpringContextUtils.getBean("userScheduleService");
		}
	}
	
	public ResetLoginMyCallable(List<UserSchedule> userScheduleList){
		this.userScheduleList = userScheduleList;
	}
	
	@Override
	public Integer call() throws Exception {
	   getBean();
	   List<UserSchedule> userScheduleTempList = this.userScheduleList;
	   for(int i=0;i<userScheduleTempList.size();i++){
		 int cou = userScheduleService.updateUserLoginState(userScheduleTempList.get(i).getId(),0);
		 if(cou!=1){
			 logger.error(userScheduleTempList.get(i).getUserid()+" , 该userId午夜重置登陆状态失败！");
			 return 0;
		 }
	   }
	   return 1;
	}

}
