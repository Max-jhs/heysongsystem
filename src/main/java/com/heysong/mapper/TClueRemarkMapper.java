package com.heysong.mapper;

import com.heysong.domain.TClueRemark;

import java.util.List;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

public interface TClueRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TClueRemark record);

    int insertSelective(TClueRemark record);

    TClueRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TClueRemark record);

    int updateByPrimaryKey(TClueRemark record);

    List<TClueRemark> selectAll(Integer clueId);
}