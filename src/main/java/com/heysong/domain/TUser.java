package com.heysong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

/**
 * 用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TUser {
    /**
    * 主键，自动增长，用户ID
    */
    private Integer id;

    /**
    * 登录账号
    */
    private String loginAct;

    /**
    * 登录密码
    */
    private String loginPwd;

    /**
    * 用户姓名
    */
    private String name;

    /**
    * 用户手机
    */
    private String phone;

    /**
    * 用户邮箱
    */
    private String email;

    /**
    * 账户是否没有过期，0已过期 1正常
    */
    private Integer accountNoExpired;

    /**
    * 密码是否没有过期，0已过期 1正常
    */
    private Integer credentialsNoExpired;

    /**
    * 账号是否没有锁定，0已锁定 1正常
    */
    private Integer accountNoLocked;

    /**
    * 账号是否启用，0禁用 1启用
    */
    private Integer accountEnabled;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 创建人
    */
    private Integer createBy;

    /**
    * 编辑时间
    */
    private Date editTime;

    /**
    * 编辑人
    */
    private Integer editBy;

    /**
    * 最近登录时间
    */
    private Date lastLoginTime;

    private TUser editByPO;

    private TUser createByPO;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginAct() {
        return loginAct;
    }

    public void setLoginAct(String loginAct) {
        this.loginAct = loginAct;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAccountNoExpired() {
        return accountNoExpired;
    }

    public void setAccountNoExpired(Integer accountNoExpired) {
        this.accountNoExpired = accountNoExpired;
    }

    public Integer getCredentialsNoExpired() {
        return credentialsNoExpired;
    }

    public void setCredentialsNoExpired(Integer credentialsNoExpired) {
        this.credentialsNoExpired = credentialsNoExpired;
    }

    public Integer getAccountNoLocked() {
        return accountNoLocked;
    }

    public void setAccountNoLocked(Integer accountNoLocked) {
        this.accountNoLocked = accountNoLocked;
    }

    public Integer getAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(Integer accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Integer getEditBy() {
        return editBy;
    }

    public void setEditBy(Integer editBy) {
        this.editBy = editBy;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}