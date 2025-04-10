package com.heysong.mapper;

import com.heysong.domain.TActivityRemark;

import java.util.List;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

public interface TActivityRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TActivityRemark record);

    int insertSelective(TActivityRemark record);

    TActivityRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TActivityRemark record);

    int updateByPrimaryKey(TActivityRemark record);

    List<TActivityRemark> selectByActivityId(Integer activityId);

    Integer updateById(Integer id);

    Integer updateRemarkNote(TActivityRemark tActivityRemark);
}