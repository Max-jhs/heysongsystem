package com.heysong.service.impl;

import com.heysong.domain.LoginUser;
import com.heysong.domain.TPermission;
import com.heysong.mapper.TPermissionMapper;
import com.heysong.mapper.TUserMapper;
import io.swagger.models.auth.In;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/2/27 0:23
 * @Version: v1.0.0
 * @Description:
 **/
@Service
public class LoginUserServiceImpl implements UserDetailsService {
    @Resource
    private TUserMapper userMapper;

    @Resource
    private TPermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String loginAct) throws UsernameNotFoundException {
        LoginUser loginUser = userMapper.selectByAct(loginAct);
        if (ObjectUtils.isEmpty(loginUser)) {
            throw new UsernameNotFoundException("该用户不存在：" + loginAct);
        }

        // ‘管理员’
        List<String> roleCode = userMapper.selectUserRole(loginUser.getId());

        // ‘clue:view’
        List<String> perMission = userMapper.selectPermission(loginUser.getId());

        // 父菜单（子菜单）  应该递归调用获取所有的菜单

        List<TPermission> Menus = getMenus(loginUser.getId(),0);

        loginUser.setRoleList(roleCode);

        loginUser.setPermissionList(perMission);

        loginUser.setMenuList(Menus);


        return loginUser;
    }

    // // 递归获取菜单列表
    // private List<TPermission> getMenus(Integer loginUserId, Integer TPermission_Parent_id) {
    //     // 规定一级菜单的parentId=0
    //     // 根据菜单父id = 0 属性获取一级菜单集合
    //     List<TPermission> firstMenus = permissionMapper.selectMenuList(loginUserId, TPermission_Parent_id);
    //     // 遍历一级菜单集合 传入一级菜单的id 获取 它的子
    //     firstMenus.forEach(permission -> {
    //         List<TPermission> child = permissionMapper.selectMenuList(loginUserId, permission.getId());
    //         if (!child.isEmpty()) {
    //             // 封装父子
    //             permission.setChildren(child);
    //         }
    //         child.forEach(permissionChild -> {
    //             List<TPermission> cchild = getMenus(loginUserId, permissionChild.getId());
    //             if (!cchild.isEmpty()) {
    //                 permissionChild.setChildren(cchild);
    //             }
    //         });
    //     });
    //     return firstMenus;
    // }
    private List<TPermission> getMenus(Integer loginUserId, Integer TPermission_Parent_id){
        // 根据菜单父id属性获取子菜单集合
        List<TPermission> menus = permissionMapper.selectMenuList(loginUserId, TPermission_Parent_id);
        // 遍历子菜单集合
        for (TPermission permission : menus) {
            // 递归获取子菜单
            List<TPermission> child = getMenus(loginUserId, permission.getId());
            if (!child.isEmpty()) {
                // 设置子菜单
                permission.setChildren(child);
            }
        }
        return menus;
    }
}