package com.yishenxiao.usermanager.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserArticleMol implements Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long userid;

    private BigDecimal totalmol;

    private BigDecimal todaymol;

    private Date createtime;

    private Date updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public BigDecimal getTotalmol() {
        return totalmol;
    }

    public void setTotalmol(BigDecimal totalmol) {
        this.totalmol = totalmol;
    }

    public BigDecimal getTodaymol() {
        return todaymol;
    }

    public void setTodaymol(BigDecimal todaymol) {
        this.todaymol = todaymol;
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
}