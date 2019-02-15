package com.yishenxiao.commons.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DigitalCurrencyInfo implements Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String digitalcurrencyname;

    private String digitalcurrencyaddr;

    private Long parentid;

    private BigDecimal fee;

    private Integer tokendecimal;

    private BigDecimal realbalancelimit;

    private Date createtime;

    private Date updatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDigitalcurrencyname() {
        return digitalcurrencyname;
    }

    public void setDigitalcurrencyname(String digitalcurrencyname) {
        this.digitalcurrencyname = digitalcurrencyname == null ? null : digitalcurrencyname.trim();
    }

    public String getDigitalcurrencyaddr() {
        return digitalcurrencyaddr;
    }

    public void setDigitalcurrencyaddr(String digitalcurrencyaddr) {
        this.digitalcurrencyaddr = digitalcurrencyaddr == null ? null : digitalcurrencyaddr.trim();
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Integer getTokendecimal() {
        return tokendecimal;
    }

    public void setTokendecimal(Integer tokendecimal) {
        this.tokendecimal = tokendecimal;
    }

    public BigDecimal getRealbalancelimit() {
        return realbalancelimit;
    }

    public void setRealbalancelimit(BigDecimal realbalancelimit) {
        this.realbalancelimit = realbalancelimit;
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