package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 用户账单实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_bill")
public class UserBill extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 关联订单号
     */
    private String orderNo;

    /**
     * 类型 1-分销佣金
     */
    private Integer type;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户信息（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private User user;
}