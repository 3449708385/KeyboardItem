package com.yishenxiao.commons.service.impl.quzrtz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.yishenxiao.commons.utils.DateUtils;
import com.yishenxiao.commons.utils.PropertiesUtils;
import com.yishenxiao.commons.utils.SpringContextUtils;
import com.yishenxiao.usermanager.beans.UserSchedule;
import com.yishenxiao.usermanager.service.UserScheduleService;

@DisallowConcurrentExecution
public class ResetLoginServiceImpl  extends QuartzJobBean {
	
	private Logger logger = LoggerFactory.getLogger(ResetLoginServiceImpl.class);
	
	private UserScheduleService userScheduleService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		getBean();
		unLockUserPhoneSMS();
	}

	public void getBean(){
		if(userScheduleService==null){
			userScheduleService = (UserScheduleService)SpringContextUtils.getBean("userScheduleService");
		}
	}
	
	private void unLockUserPhoneSMS() {
		int eventCounts=1500;
		//群成员
		int dataCounts=userScheduleService.queryByAllDataCount();
		int bCount= dataCounts/eventCounts+1;
        for(int m=0;m<bCount;m++){
        	List<UserSchedule> userScheduleList = userScheduleService.queryPartData(m*eventCounts,eventCounts);
			if(userScheduleList.size()!=0){
				int poolSize=Integer.parseInt(PropertiesUtils.getInfoConfigProperties().getProperty("myUserPhoneSMSCallSize"));
				if(userScheduleList.size()<poolSize){
					poolSize=userScheduleList.size();
				}
				//为线程分配数据
				int listSize = (int) Math.ceil((double)userScheduleList.size()/poolSize);
				List<List<UserSchedule>> listFP = new ArrayList<List<UserSchedule>>(poolSize);
				for(int j=0;j<poolSize;j++){
					List<UserSchedule> tempList = new ArrayList<UserSchedule>();
					for(int i=j*listSize;i<(listSize+listSize*j);i++){
						if(i < userScheduleList.size()){
						   tempList.add(userScheduleList.get(i));
						}
					}
					listFP.add(tempList);
				}
				ExecutorService executorService= Executors.newFixedThreadPool(poolSize);  
				List<ResetLoginMyCallable> myCallableExec = new ArrayList<ResetLoginMyCallable>();		
				for(int i=0;i<poolSize;i++){
					myCallableExec.add(new ResetLoginMyCallable(listFP.get(i)));
				}
				try {
					executorService.invokeAll(myCallableExec);
				} catch (InterruptedException e) {
					logger.error("重置用户登陆，预处理用户进程执行出现异常！");
				}

	    }else{
	    	logger.error(DateUtils.getNowTime()+" ,重置用户登陆完成！");
	    }
     }
  }
}
