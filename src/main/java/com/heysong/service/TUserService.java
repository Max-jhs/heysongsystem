package com.heysong.service;

import com.github.pagehelper.PageInfo;
import com.heysong.aspect.DynamicSql;
import com.heysong.domain.TUser;
import com.heysong.model.query.UserQuery;
import com.heysong.model.vo.OwnerPO;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author: 8912
 * @Date: 2025/3/12 0:19
 * @Version: v1.0.0
 * @Description:  业务接口就是要干的事（可能需要多个基础xxmapper查询来共同完成）
 **/
public interface TUserService {

    PageInfo<TUser> selectUsersByPage(Integer pageNum);

    TUser selectUserById(Integer uid);

    Integer updateUserByUserInfo(UserQuery userQuery);

    UserQuery EncodePassword(UserQuery userQuery);

    Integer addUser(UserQuery userQuery);

    <T> Integer deleteByIds(List<T> idList);

    List<OwnerPO> selectAll();

}