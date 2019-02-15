package com.yishenxiao.commons.beans;

import java.io.Serializable;

public class BackResponseBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String config_str;
    private String start_date;
    private String end_date;
    private String issue;
    private String request_id;
    private boolean success;
	public String getConfig_str() {
		return config_str;
	}
	public void setConfig_str(String config_str) {
		this.config_str = config_str;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getRequest_id() {
		return request_id;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	@Override
	public String toString() {
		return "BackResponseBody [config_str=" + config_str + ", start_date=" + start_date + ", end_date=" + end_date
				+ ", issue=" + issue + ", request_id=" + request_id + ", success=" + success + "]";
	}
}
