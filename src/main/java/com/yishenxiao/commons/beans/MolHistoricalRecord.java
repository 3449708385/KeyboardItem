package com.yishenxiao.commons.beans;

import java.io.Serializable;
import java.util.Date;

public class MolHistoricalRecord implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Date createtime;

    private Date updatetime;

    private Long userid;

    private Long userarticleid;

    private String molcount;

    private String showcontext;

    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getUserarticleid() {
        return userarticleid;
    }

    public void setUserarticleid(Long userarticleid) {
        this.userarticleid = userarticleid;
    }

    public String getMolcount() {
        return molcount;
    }

    public void setMolcount(String molcount) {
        this.molcount = molcount == null ? null : molcount.trim();
    }

    public String getShowcontext() {
        return showcontext;
    }

    public void setShowcontext(String showcontext) {
        this.showcontext = showcontext == null ? null : showcontext.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

	@Override
	public String toString() {
		return "MolHistoricalRecord [id=" + id + ", createtime=" + createtime + ", updatetime=" + updatetime
				+ ", userid=" + userid + ", userarticleid=" + userarticleid + ", molcount=" + molcount
				+ ", showcontext=" + showcontext + ", type=" + type + "]";
	}
}