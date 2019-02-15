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

import com.yishenxiao.commons.beans.PhoneSMS;
import com.yishenxiao.commons.service.PhoneSMSService;
import com.yishenxiao.commons.utils.DateUtils;
import com.yishenxiao.commons.utils.PropertiesUtils;
import com.yishenxiao.commons.utils.SpringContextUtils;

@DisallowConcurrentExecution
public class UnLockPhoneSMSServiceImpl  extends QuartzJobBean {
	
	private Logger logger = LoggerFactory.getLogger(UnLockPhoneSMSServiceImpl.class);
	
	private PhoneSMSService phoneSMSService;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		getBean();
		unLockUserPhoneSMS();
	}

	public void getBean(){
		if(phoneSMSService==null){
		  phoneSMSService = (PhoneSMSService)SpringContextUtils.getBean("phoneSMSService");
		}
	}
	
	private void unLockUserPhoneSMS() {
		int eventCounts=1500;
		//群成员
		int dataCounts=phoneSMSService.queryByAllDataCount();
		int bCount= dataCounts/eventCounts+1;
        for(int m=0;m<bCount;m++){
        	List<PhoneSMS> phoneSMSList = phoneSMSService.queryPartData(m*eventCounts,eventCounts);
			if(phoneSMSList.size()!=0){
				int poolSize=Integer.parseInt(PropertiesUtils.getInfoConfigProperties().getProperty("myUserPhoneSMSCallSize"));
				if(phoneSMSList.size()<poolSize){
					poolSize=phoneSMSList.size();
				}
				//为线程分配数据
				int listSize = (int) Math.ceil((double)phoneSMSList.size()/poolSize);
				List<List<PhoneSMS>> listFP = new ArrayList<List<PhoneSMS>>(poolSize);
				for(int j=0;j<poolSize;j++){
					List<PhoneSMS> tempList = new ArrayList<PhoneSMS>();
					for(int i=j*listSize;i<(listSize+listSize*j);i++){
						if(i < phoneSMSList.size()){
						   tempList.add(phoneSMSList.get(i));
						}
					}
					listFP.add(tempList);
				}
				ExecutorService executorService= Executors.newFixedThreadPool(poolSize);  
				List<UnLockPhoneSMSMyCallable> myCallableExec = new ArrayList<UnLockPhoneSMSMyCallable>();		
				for(int i=0;i<poolSize;i++){
					myCallableExec.add(new UnLockPhoneSMSMyCallable(listFP.get(i)));
				}
				try {
					executorService.invokeAll(myCallableExec);
				} catch (InterruptedException e) {
					logger.error("解锁用户短信，预处理用户进程执行出现异常！");
				}

	    }else{
	    	logger.error(DateUtils.getNowTime()+" ,解锁用户短信完成！");
	    }
     }
  }
}
