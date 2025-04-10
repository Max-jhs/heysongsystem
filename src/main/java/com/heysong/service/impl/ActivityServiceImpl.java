package com.heysong.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heysong.domain.TActivity;
import com.heysong.mapper.TActivityMapper;
import com.heysong.model.constant.Constants;
import com.heysong.model.query.ActivityQuery;
import com.heysong.service.ActivityService;
import com.heysong.util.JWTUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.beans.BeanProperty;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @Author: 8912
 * @Date: 2025/3/17 18:38
 * @Version: v1.0.0
 * @Description:
 **/
@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private TActivityMapper activityMapper;

    @Override
    public PageInfo<TActivity> loadActivitiesByPage(ActivityQuery activityQuery) {
        PageHelper.startPage(activityQuery.getCurrentPage(), Constants.PAGE_SIZE);
        List<TActivity> activityList = activityMapper.selectAll(activityQuery);
        return new PageInfo<>(activityList);
    }

    @Override
    public Integer addActivity(ActivityQuery activityQuery) {
        //query视图和实体属性有差别
        TActivity tActivity = new TActivity();
        // 创建人和创建时间 怎么拿   每次请求都有jwt 中包含当前登录人的信息  security
        Integer id = JWTUtils.parseTokenUserId(activityQuery.getJwt());
        // tActivity.setCreateBy(id);
        // tActivity.setCreateTime(new Date());
        // tActivity.setEditBy(id);
        // tActivity.setEditTime(new Date());
        // BeanUtils.copyProperties(activityQuery,tActivity);  顺序很重要，刚开始有值，new了个对象属性是null copy过去所以也是null
        BeanUtils.copyProperties(activityQuery, tActivity);
        tActivity.setCreateBy(id);
        tActivity.setCreateTime(new Date());
        tActivity.setEditBy(id);
        tActivity.setEditTime(new Date());
        log.info(tActivity.toString());
        return activityMapper.insert(tActivity);
    }

    @Override
    public TActivity loadActivityDetail(Integer activityId) {
        return activityMapper.selectByPrimaryKey(activityId);
    }

    @Override
    public Integer editActivityDetails(ActivityQuery activityQuery) {
        TActivity tActivity = new TActivity();
        BeanUtils.copyProperties(activityQuery, tActivity);
        tActivity.setEditBy(activityQuery.getEditBy());
        tActivity.setEditTime(new Date());
        return activityMapper.updateByPrimaryKey(tActivity);
    }

    @Override
    public int delActivityByIds(String[] splitIds) {
        return activityMapper.deleteByPrimaryKeys(splitIds);
    }

    @Override
    public List<TActivity> loadAll() {
        return activityMapper.selectOnGoingActivity();
    }
}