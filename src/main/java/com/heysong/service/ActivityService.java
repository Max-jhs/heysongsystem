package com.heysong.service;

import com.github.pagehelper.PageInfo;
import com.heysong.domain.TActivity;
import com.heysong.model.query.ActivityQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/3/17 18:29
 * @Version: v1.0.0
 * @Description:
 **/

public interface ActivityService {
    PageInfo<TActivity> loadActivitiesByPage(ActivityQuery activityQuery);
    // 新增活动
    Integer addActivity(ActivityQuery activityQuery);

    // 查询具体id的详情
    TActivity loadActivityDetail(Integer activityId);
    // 编辑具体活动详情
    Integer editActivityDetails(ActivityQuery activityQuery);

    int delActivityByIds(String[] splitIds);

    List<TActivity> loadAll();
}