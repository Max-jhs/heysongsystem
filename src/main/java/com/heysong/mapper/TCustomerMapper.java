package com.heysong.mapper;

import com.heysong.domain.TCustomer;
import com.heysong.model.result.NameValue;
import com.heysong.model.vo.CustomerExport;
import com.heysong.model.vo.TCustomerVo;

import java.util.List;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

public interface TCustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCustomer record);

    int insertSelective(TCustomer record);

    TCustomer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCustomer record);

    int updateByPrimaryKey(TCustomer record);

    List<TCustomerVo> selectAll();

    int  selectAllTcustomer();

    List<TCustomerVo> selectSelectAll(String[] split);

    List<NameValue> selectSourceGroup();
}