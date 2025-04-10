package com.heysong.mapper;

import com.heysong.aspect.DynamicSql;
import com.heysong.domain.TActivity;
import com.heysong.model.query.ActivityQuery;

import java.util.List;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

public interface TActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TActivity record);

    int insertSelective(TActivity record);

    TActivity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TActivity record);

    int updateByPrimaryKey(TActivity record);

    @DynamicSql(tableName = "a",idColumn = "owner_id")
    List<TActivity> selectAll(ActivityQuery activityQuery);


    int deleteByPrimaryKeys(String[] splitIds);


    List<TActivity> selectOnGoingActivity();
    List<TActivity> selectAllAct();
}