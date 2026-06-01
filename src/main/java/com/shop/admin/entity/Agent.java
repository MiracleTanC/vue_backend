package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 分销员实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("agent")
public class Agent extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 推荐人ID
     */
    private Long parentId;

    /**
     * 分销层级 1-一级 2-二级
     */
    private Integer level;

    /**
     * 状态 0-禁用 1-启用
     */
    private Integer status;

    /**
     * 累计佣金
     */
    private BigDecimal totalCommission;

    /**
     * 已提现佣金
     */
    private BigDecimal withdrawCommission;

    /**
     * 用户信息（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private User user;
}