package com.yishenxiao.commons.beans;

import java.io.Serializable;
import java.util.Date;

public class ArticleType implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String articlepicture;

    private String articletypename;

    private String articletypedesc;

    private Integer state;

    private Date createtime;

    private Date updatetime;
    
    private boolean hasSelect;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArticlepicture() {
        return articlepicture;
    }

    public void setArticlepicture(String articlepicture) {
        this.articlepicture = articlepicture == null ? null : articlepicture.trim();
    }

    public String getArticletypename() {
        return articletypename;
    }

    public void setArticletypename(String articletypename) {
        this.articletypename = articletypename == null ? null : articletypename.trim();
    }

    public String getArticletypedesc() {
        return articletypedesc;
    }

    public void setArticletypedesc(String articletypedesc) {
        this.articletypedesc = articletypedesc == null ? null : articletypedesc.trim();
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

    public boolean isHasSelect() {
		return hasSelect;
	}

	public void setHasSelect(boolean hasSelect) {
		this.hasSelect = hasSelect;
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
		return "ArticleType [id=" + id + ", articlepicture=" + articlepicture + ", articletypename=" + articletypename
				+ ", articletypedesc=" + articletypedesc + ", state=" + state + ", createtime=" + createtime
				+ ", updatetime=" + updatetime + ", hasSelect=" + hasSelect + "]";
	}
}