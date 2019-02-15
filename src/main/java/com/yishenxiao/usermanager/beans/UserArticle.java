package com.yishenxiao.usermanager.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserArticle implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long userid;

    private String articlemongoid;

    private Integer forwardcount;

    private Integer clickurlcount;

    private Integer type;

    private String articletype;

    private BigDecimal extensionmonery;

    private Integer state;

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

    public String getArticlemongoid() {
        return articlemongoid;
    }

    public void setArticlemongoid(String articlemongoid) {
        this.articlemongoid = articlemongoid == null ? null : articlemongoid.trim();
    }

    public Integer getForwardcount() {
        return forwardcount;
    }

    public void setForwardcount(Integer forwardcount) {
        this.forwardcount = forwardcount;
    }

    public Integer getClickurlcount() {
        return clickurlcount;
    }

    public void setClickurlcount(Integer clickurlcount) {
        this.clickurlcount = clickurlcount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getArticletype() {
        return articletype;
    }

    public void setArticletype(String articletype) {
        this.articletype = articletype == null ? null : articletype.trim();
    }

    public BigDecimal getExtensionmonery() {
        return extensionmonery;
    }

    public void setExtensionmonery(BigDecimal extensionmonery) {
        this.extensionmonery = extensionmonery;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
		return "UserArticle [id=" + id + ", userid=" + userid + ", articlemongoid=" + articlemongoid + ", forwardcount="
				+ forwardcount + ", clickurlcount=" + clickurlcount + ", type=" + type + ", articletype=" + articletype
				+ ", extensionmonery=" + extensionmonery + ", state=" + state + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + "]";
	}
}