package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图片实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("image")
public class Image extends BaseEntity {

    /**
     * 图片分类ID
     */
    private Long imageClassId;

    /**
     * 图片名称
     */
    private String name;

    /**
     * 图片URL
     */
    private String url;

    /**
     * 排序
     */
    private Integer orderNum;
}