package com.heysong.mapper;

import com.heysong.domain.TPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

public interface TPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPermission record);

    int insertSelective(TPermission record);

    TPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPermission record);

    int updateByPrimaryKey(TPermission record);

    List<TPermission> selectMenuList(@Param("loginUserId") Integer loginUserId, @Param("TPermission_Parent_id") Integer TPermission_Parent_id);
}