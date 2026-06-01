package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品分类实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("category")
public class Category extends BaseEntity {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 上级ID 0-顶级
     */
    private Long pid;

    /**
     * 分类图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 状态 0-禁用 1-启用
     */
    private Integer status;

    /**
     * 子分类（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private java.util.List<Category> child;
}