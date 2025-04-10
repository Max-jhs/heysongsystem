package com.heysong.service.impl;

import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heysong.HeysongApplication;
import com.heysong.config.listener.UploadDatListener;
import com.heysong.domain.TClue;
import com.heysong.domain.TClueRemark;
import com.heysong.domain.TProduct;
import com.heysong.mapper.TClueMapper;
import com.heysong.mapper.TClueRemarkMapper;
import com.heysong.model.constant.Constants;
import com.heysong.model.vo.TClueVo;
import com.heysong.service.ClueService;
import com.heysong.util.JWTUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/4/1 22:42
 * @Version: v1.0.0
 * @Description:
 **/
@Service
public class ClueServiceImpl implements ClueService {
    @Resource
    private TClueMapper tClueMapper;
    @Resource
    private TClueRemarkMapper tClueRemarkMapper;
    @Override
    public PageInfo<TClueVo> loadCluesByPage(Integer currentPage) {
        PageHelper.startPage(currentPage, Constants.PAGE_SIZE);
        List<TClueVo> clueList = tClueMapper.selectAll();
        return new PageInfo<>(clueList);
    }

    @Override
    public Boolean uploadClueExcel(MultipartFile file,String token) throws IOException {
        EasyExcel.read(file.getInputStream(), TClue.class, new UploadDatListener(tClueMapper,token))
                .sheet()
                .doRead();
        return true;
    }

    @Override
    public TClue loadClueDetailById(Integer clueId) {
        return tClueMapper.selectByPrimaryKey(clueId);
    }

    @Override
    public Integer addClue(TClue tClue, String token) {
        tClue.setCreateBy(JWTUtils.parseTokenUserId(token));
        tClue.setEditBy(JWTUtils.parseTokenUserId(token));
        tClue.setEditTime(new Date());
        tClue.setCreateTime(new Date());
        return tClueMapper.insert(tClue);
    }

    @Override
    public Integer editClue(TClue tClue, String token) {
        tClue.setEditBy(JWTUtils.parseTokenUserId(token));
        tClue.setEditTime(new Date());
        return tClueMapper.updateByPrimaryKeySelective(tClue);
    }

}