package com.yishenxiao.commons.service.impl.quzrtz;

import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yishenxiao.commons.beans.PhoneSMS;
import com.yishenxiao.commons.service.PhoneSMSService;
import com.yishenxiao.commons.utils.SpringContextUtils;

public class UnLockPhoneSMSMyCallable implements Callable<Integer> {

	private static Logger logger = LoggerFactory.getLogger(UnLockPhoneSMSMyCallable.class);
	
	private List<PhoneSMS> phoneSMSList;
	
	private PhoneSMSService phoneSMSService;
	
	private void getBean(){
		if(phoneSMSService==null){
		    phoneSMSService=(PhoneSMSService) SpringContextUtils.getBean("phoneSMSService");
		}
	}
	
	public UnLockPhoneSMSMyCallable(List<PhoneSMS> phoneSMSList){
		this.phoneSMSList = phoneSMSList;
	}
	
	@Override
	public Integer call() throws Exception {
	   getBean();
	   List<PhoneSMS> phoneSMSTempList = this.phoneSMSList;
	   for(int i=0;i<phoneSMSTempList.size();i++){
		 int cou = phoneSMSService.unLockPhoneSMS(phoneSMSTempList.get(i).getId(),0);
		 if(cou!=1){
			 logger.error(phoneSMSTempList.get(i).getPhone()+" , 该手机午夜重置短信访问失败！");
			 return 0;
		 }
	   }
	   return 1;
	}

}
