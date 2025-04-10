package com.heysong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heysong.domain.TActivityRemark;
import com.heysong.mapper.TActivityRemarkMapper;
import com.heysong.model.constant.Constants;
import com.heysong.model.query.ActivityRemarkQuery;
import com.heysong.service.ActivityRemarkService;
import com.heysong.util.JWTUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/3/31 22:29
 * @Version: v1.0.0
 * @Description:
 **/
@Service
public class ActivityRemarkImpl implements ActivityRemarkService {
    @Resource
    private TActivityRemarkMapper tActivityRemarkMapper;

    @Override
    public Integer addNoteContentForActivityByActivityId(ActivityRemarkQuery activityRemarkQuery) {
        Integer id = JWTUtils.parseTokenUserId(activityRemarkQuery.getJwt());
        TActivityRemark tActivityRemark = new TActivityRemark();
        BeanUtils.copyProperties(activityRemarkQuery, tActivityRemark);
        tActivityRemark.setCreateTime(new Date());
        tActivityRemark.setCreateBy(id);
        tActivityRemark.setEditTime(new Date());
        tActivityRemark.setEditBy(id);
        tActivityRemark.setDeleted(0);
        return tActivityRemarkMapper.insert(tActivityRemark);
    }

    @Override
    public PageInfo<TActivityRemark> selectActivityRemarkByPage(Integer pageNum, Integer activityId) {
        PageHelper.startPage(pageNum, Constants.MARK_PAGE_SIZE);
        List<TActivityRemark> remarks = tActivityRemarkMapper.selectByActivityId(activityId);
        PageInfo<TActivityRemark> pageInfo = new PageInfo<>(remarks);
        // new PgeInfo<>()
        // pageInfo.setList(remarks);这样就不行  有问题 必须使用有参构造
        return pageInfo;
    }

    @Override
    public Integer delNoteContentForActivityByRemarkId(Integer id) {
        return tActivityRemarkMapper.updateById(id);
    }

    @Override
    public Integer updateRemarksNoteContent(ActivityRemarkQuery activityRemarkQuery) {
        Integer id = JWTUtils.parseTokenUserId(activityRemarkQuery.getJwt());
        TActivityRemark tActivityRemark = new TActivityRemark();
        tActivityRemark.setNoteContent(activityRemarkQuery.getNoteContent());
        tActivityRemark.setEditBy(id);
        tActivityRemark.setId(activityRemarkQuery.getId());
        return tActivityRemarkMapper.updateRemarkNote(tActivityRemark);
    }
}