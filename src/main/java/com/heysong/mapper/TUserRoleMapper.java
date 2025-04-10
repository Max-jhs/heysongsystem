package com.heysong.mapper;

import com.heysong.domain.TUserRole;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

public interface TUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUserRole record);

    int insertSelective(TUserRole record);

    TUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUserRole record);

    int updateByPrimaryKey(TUserRole record);
}