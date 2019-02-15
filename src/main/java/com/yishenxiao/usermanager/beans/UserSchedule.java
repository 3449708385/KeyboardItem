package com.yishenxiao.usermanager.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserSchedule implements Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long userid;

    private String pypasswd;

    private String equipmentidentification;

    private BigDecimal molcount;

    private String articletype;

    private String headicon;

    private Integer userlogincount;

    private Integer userloginstate;

    private Date createtime;

    private Date updatetime;

    private Integer authenticationstate;

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

    public String getPypasswd() {
        return pypasswd;
    }

    public void setPypasswd(String pypasswd) {
        this.pypasswd = pypasswd == null ? null : pypasswd.trim();
    }

    public String getEquipmentidentification() {
        return equipmentidentification;
    }

    public void setEquipmentidentification(String equipmentidentification) {
        this.equipmentidentification = equipmentidentification == null ? null : equipmentidentification.trim();
    }

    public BigDecimal getMolcount() {
        return molcount;
    }

    public void setMolcount(BigDecimal molcount) {
        this.molcount = molcount;
    }

    public String getArticletype() {
        return articletype;
    }

    public void setArticletype(String articletype) {
        this.articletype = articletype == null ? null : articletype.trim();
    }

    public String getHeadicon() {
        return headicon;
    }

    public void setHeadicon(String headicon) {
        this.headicon = headicon == null ? null : headicon.trim();
    }

    public Integer getUserlogincount() {
        return userlogincount;
    }

    public void setUserlogincount(Integer userlogincount) {
        this.userlogincount = userlogincount;
    }

    public Integer getUserloginstate() {
        return userloginstate;
    }

    public void setUserloginstate(Integer userloginstate) {
        this.userloginstate = userloginstate;
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

    public Integer getAuthenticationstate() {
        return authenticationstate;
    }

    public void setAuthenticationstate(Integer authenticationstate) {
        this.authenticationstate = authenticationstate;
    }

	@Override
	public String toString() {
		return "UserSchedule [id=" + id + ", userid=" + userid + ", pypasswd=" + pypasswd + ", equipmentidentification="
				+ equipmentidentification + ", molcount=" + molcount + ", articletype=" + articletype + ", headicon="
				+ headicon + ", userlogincount=" + userlogincount + ", userloginstate=" + userloginstate
				+ ", createtime=" + createtime + ", updatetime=" + updatetime + ", authenticationstate="
				+ authenticationstate + "]";
	}
}