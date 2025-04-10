package com.heysong.mapper;

import com.heysong.domain.TClue;
import com.heysong.model.vo.TClueVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

public interface TClueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TClue record);

    int insertSelective(TClue record);

    TClue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TClue record);

    int updateByPrimaryKey(TClue record);

    List<TClueVo> selectAll();

    int selectAllTclue();

    void saveBatch(List<TClue> cachedDataList);
}