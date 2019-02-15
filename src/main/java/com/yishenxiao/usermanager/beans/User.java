package com.yishenxiao.usermanager.beans;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String nativecode;

    private String account;

    private Date createtime;

    private String nickname;

    private String passwd;

    private String email;

    private String phone;

    private Integer type;

    private Date updatetime;

    private Integer loginstate;

    private Integer state;

    private Long parentextensionid;

    private Integer invitationcount;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getLoginstate() {
        return loginstate;
    }

    public void setLoginstate(Integer loginstate) {
        this.loginstate = loginstate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getParentextensionid() {
        return parentextensionid;
    }

    public void setParentextensionid(Long parentextensionid) {
        this.parentextensionid = parentextensionid;
    }

    public Integer getInvitationcount() {
        return invitationcount;
    }

    public void setInvitationcount(Integer invitationcount) {
        this.invitationcount = invitationcount;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", nativecode=" + nativecode + ", account=" + account + ", createtime=" + createtime
				+ ", nickname=" + nickname + ", passwd=" + passwd + ", email=" + email + ", phone=" + phone + ", type="
				+ type + ", updatetime=" + updatetime + ", loginstate=" + loginstate + ", state=" + state
				+ ", parentextensionid=" + parentextensionid + ", invitationcount=" + invitationcount + "]";
	}
}