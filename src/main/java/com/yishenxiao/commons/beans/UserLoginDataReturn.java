package com.yishenxiao.commons.beans;

import java.io.Serializable;
import java.util.Properties;

import com.yishenxiao.commons.utils.PropertiesUtils;

public class UserLoginDataReturn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int state;//1代表已领取，2代表可领取，3代表不能领取
	private int MolCount;
	private int dayCount;
	private int orderId;
	
	public UserLoginDataReturn(int orderId, int dayCount, int today, int loginstate){	
		Properties properties=PropertiesUtils.getInfoConfigProperties();
		int LoginmolCount=0;
		this.orderId=orderId;
		this.dayCount=dayCount;
		if(dayCount<today){
			this.state=1;
		}else if(dayCount==today){
			if(loginstate==0){
			  this.state=2;
			}else{
			  this.state=1;
			}
		}else{
			this.state=3;
		}
		switch(dayCount){
		    case 1:
		    	LoginmolCount=Integer.parseInt(properties.getProperty("oneMolCount"));
		    	this.MolCount=LoginmolCount;	    	
			    break;
			case 2:
				LoginmolCount=Integer.parseInt(properties.getProperty("twoMolCount"));
				this.MolCount=LoginmolCount;
				break;
			case 3:
				LoginmolCount=Integer.parseInt(properties.getProperty("threeMolCount"));
				this.MolCount=LoginmolCount;
				break;
			case 4:
				LoginmolCount=Integer.parseInt(properties.getProperty("fourMolCount"));
				this.MolCount=LoginmolCount;
				break;
			default:				
				LoginmolCount=Integer.parseInt(properties.getProperty("fourMolCount"));
				this.MolCount=LoginmolCount;		
		}
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getMolCount() {
		return MolCount;
	}
	public void setMolCount(int molCount) {
		MolCount = molCount;
	}
	public int getDayCount() {
		return dayCount;
	}
	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "UserLoginDataReturn [state=" + state + ", MolCount=" + MolCount + ", dayCount=" + dayCount
				+ ", orderId=" + orderId + "]";
	}
}
