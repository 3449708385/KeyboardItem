package com.yishenxiao.commons.beans;

import java.io.Serializable;
import java.util.Date;

public class NativeCode implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String nativecode;
    
    private String nativeCodeStr;

    private String nativename;

    private Date createtime;

    private Date updatetime;

    private Integer natvecodesort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNativecode() {
        return nativecode;
    }

    public void setNativecode(String nativecode) {
        this.nativecode = nativecode == null ? null : nativecode.trim();
    }

    public String getNativename() {
        return nativename;
    }

    public void setNativename(String nativename) {
        this.nativename = nativename == null ? null : nativename.trim();
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

    public Integer getNatvecodesort() {
        return natvecodesort;
    }

    public void setNatvecodesort(Integer natvecodesort) {
        this.natvecodesort = natvecodesort;
    }

	public String getNativeCodeStr() {
		return nativeCodeStr;
	}

	public void setNativeCodeStr(String nativeCodeStr) {
		this.nativeCodeStr = nativeCodeStr;
	}

	@Override
	public String toString() {
		return "NativeCode [id=" + id + ", nativecode=" + nativecode + ", nativeCodeStr=" + nativeCodeStr
				+ ", nativename=" + nativename + ", createtime=" + createtime + ", updatetime=" + updatetime
				+ ", natvecodesort=" + natvecodesort + "]";
	}
}