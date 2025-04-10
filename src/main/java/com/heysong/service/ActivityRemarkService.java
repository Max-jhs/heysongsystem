package com.heysong.service;

import com.github.pagehelper.PageInfo;
import com.heysong.domain.TActivityRemark;
import com.heysong.model.query.ActivityRemarkQuery;

/**
 * @Author: 8912
 * @Date: 2025/3/31 22:29
 * @Version: v1.0.0
 * @Description:
 **/
public interface ActivityRemarkService {
    Integer addNoteContentForActivityByActivityId(ActivityRemarkQuery activityRemarkQuery);

    PageInfo<TActivityRemark> selectActivityRemarkByPage(Integer pageNum, Integer activityId);

    Integer delNoteContentForActivityByRemarkId(Integer id);

    Integer updateRemarksNoteContent(ActivityRemarkQuery activityRemarkQuery);
}