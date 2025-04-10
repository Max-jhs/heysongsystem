package com.heysong.service;

import com.github.pagehelper.PageInfo;
import com.heysong.domain.TClueRemark;

/**
 * @Author: 8912
 * @Date: 2025/4/7 13:44
 * @Version: v1.0.0
 * @Description:
 **/
public interface TClueRemarkService {
    PageInfo<TClueRemark> loadClueRemarkByPage(Integer currentPage,Integer clueId);

    int add(TClueRemark tClueRemark, String token);
}