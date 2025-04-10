package com.heysong.domain;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

import lombok.Data;

import java.util.List;

/**
 * 字典类型表
 */
@Data
public class TDicType {
    /**
    * 主键，自动增长，字典类型ID
    */
    private Integer id;

    /**
    * 字典类型代码
    */
    private String typeCode;

    /**
    * 字典类型名称
    */
    private String typeName;

    /**
    * 备注
    */
    private String remark;

    private List<TDicValue> dicValueList;
}