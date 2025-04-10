package com.heysong.mapper;

import com.heysong.domain.TTran;

import java.math.BigDecimal;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

public interface TTranMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTran record);

    int insertSelective(TTran record);

    TTran selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTran record);

    int updateByPrimaryKey(TTran record);

    BigDecimal selectSuccessTran();

    int selectTranCountTotal();

    int selectTranCountSeccessTotal();


    BigDecimal selectTotalTran();
}