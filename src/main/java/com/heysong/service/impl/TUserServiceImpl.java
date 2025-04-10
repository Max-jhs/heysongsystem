package com.heysong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heysong.domain.LoginUser;
import com.heysong.domain.TUser;
import com.heysong.manager.RedisManager;
import com.heysong.mapper.TUserMapper;
import com.heysong.model.constant.Constants;
import com.heysong.model.query.BaseQuery;
import com.heysong.model.query.UserQuery;
import com.heysong.model.vo.OwnerPO;
import com.heysong.service.RedisService;
import com.heysong.service.TUserService;
import com.heysong.util.CacheUtils;
import com.heysong.util.JWTUtils;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author: 8912
 * @Date: 2025/3/12 0:22
 * @Version: v1.0.0
 * @Description:
 **/
@Slf4j
@Service
@Data
public class TUserServiceImpl implements TUserService {
    @Resource
    private PasswordEncoder bCryptPasswordEncoder;
    @Resource
    private TUserMapper tUserMapper;
    @Resource
    private RedisService redisService;
    @Resource
    private RedisManager redisManager;

    @Override
    public PageInfo<TUser> selectUsersByPage(Integer pageNum) {
        PageHelper.startPage(pageNum, Constants.PAGE_SIZE);
        List<TUser> tUsers = tUserMapper.selectUsers(new BaseQuery());
        return new PageInfo<>(tUsers);
    }

    @Override
    public TUser selectUserById(Integer uid) {
        return tUserMapper.selectByPrimaryKey(uid);
    }

    @Override
    public Integer updateUserByUserInfo(UserQuery userQuery) {
        userQuery = EncodePassword(userQuery);
        userQuery.setEditTime(new Date());
        LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 一  直接从security中获取   二  每次请求头中都有 jwt
        userQuery.setEditBy(loginUser.getId());
        // 对象和（query）dto 之间的转换
        TUser tUser = new TUser();
        BeanUtils.copyProperties(userQuery,tUser);
        log.info(tUser.toString());
        return tUserMapper.updateByUserInfo(tUser);
    }

    @Override
    public UserQuery EncodePassword(UserQuery userQuery) {
        String encodedPassword = bCryptPasswordEncoder.encode(userQuery.getLoginPwd());
        userQuery.setLoginPwd(encodedPassword);
        return userQuery;
    }

    @Override
    public Integer addUser(UserQuery userQuery) {
        userQuery = EncodePassword(userQuery);
        Date now = new Date();
        userQuery.setCreateTime(now);
        userQuery.setEditTime(now);
        // LoginUser loginUser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = JWTUtils.parseTokenUserId(userQuery.getJwt());
        userQuery.setCreateBy(id);
        userQuery.setEditBy(id);
        TUser tUser = new TUser();
        BeanUtils.copyProperties(userQuery,tUser);
        log.info(tUser.toString());
        return tUserMapper.insert(tUser);
    }

    @Override
    public <T> Integer deleteByIds(List<T> idList) {
        return tUserMapper.deleteByIds(idList);
    }

    @Override
    public List<OwnerPO> selectAll() {
        return CacheUtils.getCacheData(()->{
            return redisManager.getValue(Constants.OWNER_ID_KEY);
        },()->{
            List<OwnerPO> ownerPOS = tUserMapper.selectAll();
            System.out.println(ownerPOS);
            // [OwnerPO(id=1, name=管理员), OwnerPO(id=2, name=于嫣),
            return ownerPOS;
        },(data) -> {
            redisManager.setValue(Constants.OWNER_ID_KEY,data);
        });
    }


    // 正常思路可以完成
    // @Override
    // public List<OwnerPO> selectAll() {
    //     List<OwnerPO> ownerPOS = redisService.getListDataByKey(Constants.OWNER_ID_KEY);
    //     if (CollectionUtils.isEmpty(ownerPOS)) {
    //         ownerPOS = tUserMapper.selectAll();
    //         redisService.leftPushAll(Constants.OWNER_ID_KEY,ownerPOS);
    //         redisService.setExpired(Constants.OWNER_ID_KEY);
    //     }
    //     // 从缓存中获取避免频繁查库
    //     // 如果有直接返回，没有查库，返回并设置缓存
    //     return ownerPOS;
    // }

}