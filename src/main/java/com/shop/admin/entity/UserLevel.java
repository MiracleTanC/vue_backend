package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会员等级实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_level")
public class UserLevel extends BaseEntity {

    /**
     * 等级名称
     */
    private String name;

    /**
     * 等级图标
     */
    private String icon;

    /**
     * 等级背景图
     */
    private String bgImage;

    /**
     * 所需经验值
     */
    private Integer exp;

    /**
     * 折扣比例
     */
    private java.math.BigDecimal discount;

    /**
     * 状态 0-禁用 1-启用
     */
    private Integer status;
}