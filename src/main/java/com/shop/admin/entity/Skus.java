package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 规格模板实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("skus")
public class Skus extends BaseEntity {

    /**
     * 规格名称
     */
    private String name;

    /**
     * 规格值（JSON数组）
     */
    private String values;

    /**
     * 状态 0-禁用 1-启用
     */
    private Integer status;
}