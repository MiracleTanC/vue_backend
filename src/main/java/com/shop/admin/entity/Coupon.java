package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("coupon")
public class Coupon extends BaseEntity {

    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 类型 1-满减券 2-折扣券
     */
    private Integer type;

    /**
     * 优惠金额（满减券）
     */
    private BigDecimal amount;

    /**
     * 折扣比例（折扣券）
     */
    private BigDecimal discount;

    /**
     * 最低消费金额
     */
    private BigDecimal minAmount;

    /**
     * 发放数量
     */
    private Integer totalCount;

    /**
     * 已使用数量
     */
    private Integer usedCount;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 状态 0-禁用 1-启用
     */
    private Integer status;
}