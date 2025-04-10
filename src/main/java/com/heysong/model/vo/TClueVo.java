package com.heysong.model.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: 8912
 * @Date: 2025/4/2 17:28
 * @Version: v1.0.0
 * @Description:
 **/
@Data
public class TClueVo {
    private OwnerPO ownerPO;
    private ActivityPO activityPO;
    private AppellationPO appellationPO;
    private NeedLoanPO needLoanPO;
    private IntentionStatePO intentionStatePO;
    private IntentionProductPO intentionProductPO;
    private StatePO statePO;
    private SourcePO sourcePO;

    /**
     * 主键，自动增长，会员id
     */
    private Integer customerId;

    private Integer id;


    /**
     * 手机号
     */
    private String phone;

    /**
     * 微信号
     */
    private String weixin;

    /**
     * QQ号
     */
    private String qq;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 职业
     */
    private String job;

    /**
     * 年收入
     */
    private BigDecimal yearIncome;

    /**
     * 地址
     */
    private String address;



    /**
     * 线索描述
     */
    private String description;

    /**
     * 下次联系时间
     */
    private Date nextContactTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 编辑人
     */
    private Integer editBy;

    private String fullName;
}