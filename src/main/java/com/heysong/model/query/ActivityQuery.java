package com.heysong.model.query;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 8912
 * @Date: 2025/3/17 18:30
 * @Version: v1.0.0
 * @Description:
 **/
@Data
public class ActivityQuery extends BaseQuery {
    private Integer currentPage;
    /**
     * 主键，自动增长，活动ID
     */
    private Integer id;

    /**
     * 活动所属人ID
     */
    private Integer ownerId;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动开始时间
     */

    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 活动预算
     */
    private BigDecimal cost;

    /**
     * 活动描述
     */
    private String description;

    /**
     * 活动创建时间
     */
    private Date createTime;

    /**
     * 活动创建人
     */
    private Integer createBy;

    /**
     * 活动编辑时间
     */
    private Date editTime;

    /**
     * 活动编辑人
     */
    private Integer editBy;
}