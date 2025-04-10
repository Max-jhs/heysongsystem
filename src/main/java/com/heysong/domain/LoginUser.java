package com.heysong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.heysong.model.constant.Constants;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/2/28 8:22
 * @Version: v1.0.0
 * @Description:
 **/
@Data
public class LoginUser implements UserDetails {
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
    @JsonIgnore
    private String loginPwd;


    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户手机
     */
    @JsonIgnore
    private String phone;

    /**
     * 用户邮箱
     */
    @JsonIgnore
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
    @JsonIgnore
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

    private List<String> roleList; // 角色标识

    private List<String> permissionList; // 权限标识

    private List<TPermission> menuList; // 菜单列表信息

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //将用户权限角色信息保存到securitycontext中
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (!CollectionUtils.isEmpty(roleList)) {
            roleList.forEach(role -> {
                if (StringUtils.hasText(role)) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE_PREFIX + role));
                }
            });
        }
        if (!CollectionUtils.isEmpty(permissionList)) {
            permissionList.forEach(permissionCode -> {
                if (StringUtils.hasText(permissionCode)) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(permissionCode));
                }
            });
        }
        return grantedAuthorities;
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return loginPwd;
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return loginAct;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return accountNoExpired == 1;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return accountNoLocked == 1;
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNoExpired == 1;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return accountEnabled == 1;
    }
}