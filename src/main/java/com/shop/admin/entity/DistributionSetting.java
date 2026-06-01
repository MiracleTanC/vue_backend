package com.shop.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 分销设置实体
 */
@Data
@TableName("distribution_setting")
public class DistributionSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 是否开启分销 0-否 1-是
     */
    private Integer enabled;

    /**
     * 分销层级 1-一级 2-二级
     */
    private Integer level;

    /**
     * 一级分销佣金比例
     */
    private BigDecimal firstRate;

    /**
     * 二级分销佣金比例
     */
    private BigDecimal secondRate;

    /**
     * 结算方式 1-订单完成 2-订单支付
     */
    private Integer settleType;

    /**
     * 是否需要审核 0-否 1-是
     */
    private Integer needAudit;
}