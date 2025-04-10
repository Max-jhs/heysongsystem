package com.heysong.service.impl;

import com.heysong.domain.TDicType;
import com.heysong.mapper.TDicTypeMapper;
import com.heysong.service.TDicTypeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 8912
 * @Date: 2025/4/5 18:14
 * @Version: v1.0.0
 * @Description:
 **/
@Service
public class TDicTypeServiceImpl implements TDicTypeService {

    @Resource
    private TDicTypeMapper tDicTypeMapper;
    @Override
    public List<TDicType> selectDicType() {
        return tDicTypeMapper.selectAll();
    }
}