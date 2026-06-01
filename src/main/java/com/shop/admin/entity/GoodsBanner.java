package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品轮播图实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("goods_banner")
public class GoodsBanner extends BaseEntity {

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 图片URL
     */
    private String url;

    /**
     * 排序
     */
    private Integer orderNum;
}