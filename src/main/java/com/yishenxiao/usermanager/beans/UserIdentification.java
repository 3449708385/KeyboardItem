package com.yishenxiao.usermanager.beans;

import java.io.Serializable;
import java.util.Date;

public class UserIdentification implements Serializable  {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long userid;

    private String realname;

    private String identificationcard;

    private String pictureprecedingurl;

    private String pictureafterurl;

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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getIdentificationcard() {
        return identificationcard;
    }

    public void setIdentificationcard(String identificationcard) {
        this.identificationcard = identificationcard == null ? null : identificationcard.trim();
    }

    public String getPictureprecedingurl() {
        return pictureprecedingurl;
    }

    public void setPictureprecedingurl(String pictureprecedingurl) {
        this.pictureprecedingurl = pictureprecedingurl == null ? null : pictureprecedingurl.trim();
    }

    public String getPictureafterurl() {
        return pictureafterurl;
    }

    public void setPictureafterurl(String pictureafterurl) {
        this.pictureafterurl = pictureafterurl == null ? null : pictureafterurl.trim();
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