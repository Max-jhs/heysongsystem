package com.heysong.domain;

import com.heysong.model.vo.CreatePO;
import com.heysong.model.vo.EditPO;
import lombok.Data;

import java.util.Date;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

/**
 * 市场活动备注表
 */
@Data
public class TActivityRemark {
    /**
    * 主键，自动增长，活动备注ID
    */
    private Integer id;

    /**
    * 活动ID
    */
    private Integer activityId;

    /**
    * 备注内容
    */
    private String noteContent;

    /**
    * 备注创建时间
    */
    private Date createTime;

    /**
    * 备注创建人
    */
    private Integer createBy;

    /**
    * 备注编辑时间
    */
    private Date editTime;

    /**
    * 备注编辑人
    */
    private Integer editBy;

    /**
    * 删除状态（0正常，1删除）
    */
    private Integer deleted;

    private CreatePO createByPo;

    private EditPO editByPo;
}