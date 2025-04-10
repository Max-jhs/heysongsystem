package com.heysong.domain;

import com.heysong.model.vo.CreatePO;
import com.heysong.model.vo.EditPO;
import com.heysong.model.vo.NoteWayPO;
import lombok.Data;

import java.util.Date;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

/**
 * 线索跟踪记录表
 */
@Data
public class TClueRemark {
    private CreatePO createByPO;
    private EditPO editByPO;
    private NoteWayPO noteWayPO;


    /**
    * 主键，自动增长，线索备注ID
    */
    private Integer id;

    /**
    * 线索ID
    */
    private Integer clueId;

    /**
    * 跟踪方式
    */
    private Integer noteWay;

    /**
    * 跟踪内容
    */
    private String noteContent;

    /**
    * 跟踪时间
    */
    private Date createTime;

    /**
    * 跟踪人
    */
    private Integer createBy;

    /**
    * 编辑时间
    */
    private Date editTime;

    /**
    * 编辑人
    */
    private Integer editBy;

    /**
    * 删除状态（0正常，1删除）
    */
    private Integer deleted;
}