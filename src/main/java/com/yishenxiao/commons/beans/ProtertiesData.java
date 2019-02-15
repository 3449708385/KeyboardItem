package com.yishenxiao.commons.beans;

import java.io.Serializable;
import java.util.Date;

public class ProtertiesData implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String datakey;

    private String datavalue;

    private String datadesc;

    private Date createtime;

    private Date updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatakey() {
        return datakey;
    }

    public void setDatakey(String datakey) {
        this.datakey = datakey == null ? null : datakey.trim();
    }

    public String getDatavalue() {
        return datavalue;
    }

    public void setDatavalue(String datavalue) {
        this.datavalue = datavalue == null ? null : datavalue.trim();
    }

    public String getDatadesc() {
        return datadesc;
    }

    public void setDatadesc(String datadesc) {
        this.datadesc = datadesc == null ? null : datadesc.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

	@Override
	public String toString() {
		return "ProtertiesData [id=" + id + ", datakey=" + datakey + ", datavalue=" + datavalue + ", datadesc="
				+ datadesc + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
}