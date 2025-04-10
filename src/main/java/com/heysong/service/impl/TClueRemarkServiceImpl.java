package com.heysong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heysong.domain.TClueRemark;
import com.heysong.mapper.TClueRemarkMapper;
import com.heysong.model.constant.Constants;
import com.heysong.service.TClueRemarkService;
import com.heysong.util.JWTUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/4/7 13:45
 * @Version: v1.0.0
 * @Description:
 **/
@Service
public class TClueRemarkServiceImpl implements TClueRemarkService {
    @Resource
    private TClueRemarkMapper tClueRemarkMapper;

    @Override
    public PageInfo<TClueRemark> loadClueRemarkByPage(Integer currentPage,Integer clueId) {
        PageHelper.startPage(currentPage, Constants.MARK_PAGE_SIZE);
        List<TClueRemark> remarkList = tClueRemarkMapper.selectAll(clueId);
        return new PageInfo<>(remarkList);
    }

    @Override
    public int add(TClueRemark tClueRemark, String token) {
        Integer id = JWTUtils.parseTokenUserId(token);
        tClueRemark.setCreateTime(new Date());
        tClueRemark.setEditTime(new Date());
        tClueRemark.setEditBy(id);
        tClueRemark.setCreateBy(id);
        tClueRemark.setDeleted(0);
        return tClueRemarkMapper.insert(tClueRemark);
    }
}