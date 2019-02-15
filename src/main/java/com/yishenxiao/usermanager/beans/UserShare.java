package com.yishenxiao.usermanager.beans;

import java.io.Serializable;
import java.util.Date;

public class UserShare implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String dataid;

    private Long userarticleid;

    private Long userid;

    private Integer usecount;

    private Date createtime;

    private Date updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid == null ? null : dataid.trim();
    }

    public Long getUserarticleid() {
        return userarticleid;
    }

    public void setUserarticleid(Long userarticleid) {
        this.userarticleid = userarticleid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getUsecount() {
        return usecount;
    }

    public void setUsecount(Integer usecount) {
        this.usecount = usecount;
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
		return "UserShare [id=" + id + ", dataid=" + dataid + ", userarticleid=" + userarticleid + ", userid=" + userid
				+ ", usecount=" + usecount + ", createtime=" + createtime + ", updatetime=" + updatetime + "]";
	}
}