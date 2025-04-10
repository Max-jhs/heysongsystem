package com.heysong.model.query;

import io.swagger.models.auth.In;
import lombok.Data;

import javax.management.Query;

/**
 * @Author: 8912
 * @Date: 2025/3/31 22:23
 * @Version: v1.0.0
 * @Description:
 **/
@Data
public class ActivityRemarkQuery extends BaseQuery {
    private Integer id;
    private Integer activityId;
    private String noteContent;
}