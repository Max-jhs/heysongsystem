package com.heysong.controller;

import com.github.pagehelper.PageInfo;
import com.heysong.domain.LoginUser;
import com.heysong.domain.TUser;
import com.heysong.model.query.UserQuery;
import com.heysong.model.result.R;
import com.heysong.service.TUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/2/28 10:38
 * @Version: v1.0.0
 * @Description:
 **/
@Api("用户控制层")
// 用户生成接口文档时会用到
@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    private TUserService userService;
    @ApiOperation("获取用户名")
    // 用户生成日志时会用到
    @GetMapping("/api/loadUsername")
    public R loadUserInfo() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return R.OK(loginUser.getUsername());
    }

    @ApiOperation("获取用户菜单")
    @GetMapping("/api/user/menu")
    public R lodUserMenu() {
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return R.OK(loginUser.getMenuList());
    }

    @ApiOperation("用户免登录")
    @GetMapping("/api/freeLogin")
    public R freeLogin() {
        return R.OK();
    }

    @ApiOperation("分页获取用户详情")
    @GetMapping("/api/users")
    // api/users/currentPage=1
    public R getUsersInfo(@RequestParam(required = false, name = "currentPage",defaultValue = "1") Integer pageNum) {
        PageInfo<TUser> tUserPageInfo = userService.selectUsersByPage(pageNum);
        R ok = R.OK(tUserPageInfo);
        return ok;
    }

    @ApiOperation("根据用户Id获取用户详情")
    @GetMapping("/api/user/detail/{uid}")
    // api/user/detail/1
    public R getUserInfo(@PathVariable(name = "uid",required = true) Integer uid) {
        TUser tUser = userService.selectUserById(uid);
        R ok = R.OK(tUser);
        return ok;
    }



    @ApiOperation("根据用户对象修改用户详情")
    @PutMapping("/api/user/edit")
    public R updateUserByUserInfo(@RequestBody UserQuery  userQuery) {
        Integer count = userService.updateUserByUserInfo(userQuery);
        R r ;
        if (count > 0) {
           r =  R.OK();
        }else{
            r = R.builder().code(400).msg("修改失败了").build();
        }
        return r;
    }

    @ApiOperation("根据用户json添加用户")
    @PostMapping("/api/user/add")
    public R addUser(@RequestBody UserQuery userQuery, @RequestHeader(value = "Authorization") String jwt) {
        userQuery.setJwt(jwt);
        Integer count = userService.addUser(userQuery);
        R r;
        if (count > 0) {
            r = R.OK();
        } else {
            r = R.builder().code(400).msg("添加失败").build();
        }
        return r;
    }


    @ApiOperation("根据id批量删除用户")
    @DeleteMapping("/api/user/del/{ids}")
    // 中文 1，2，3 请求错误 apifox更本没发出请求 所以说自己controller 验证一下较好
    public R removeUserByIds(@PathVariable(name = "ids") String ids) {
        List<String> idList = Arrays.stream(ids.split(",")).toList();
        log.info(idList.toString());
        Integer count = userService.deleteByIds(idList);
        R r ;
        if (count == idList.size()) {
            r =  R.OK();
        }else{
            r = R.builder().code(400).msg("批量删除失败").build();
        }
        return r;
    }


}