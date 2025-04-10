package com.heysong.domain;

import java.util.Date;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

/**
 * 交易跟踪记录表
 */
public class TTranRemark {
    /**
    * 主键，自动增长，交易备注ID
    */
    private Integer id;

    /**
    * 交易ID
    */
    private Integer tranId;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTranId() {
        return tranId;
    }

    public void setTranId(Integer tranId) {
        this.tranId = tranId;
    }

    public Integer getNoteWay() {
        return noteWay;
    }

    public void setNoteWay(Integer noteWay) {
        this.noteWay = noteWay;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Integer getEditBy() {
        return editBy;
    }

    public void setEditBy(Integer editBy) {
        this.editBy = editBy;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}