package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品SKU规格卡片
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("goods_skus_card")
public class GoodsSkusCard extends BaseEntity {

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 规格名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * SKU值列表（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private java.util.List<GoodsSkusCardValue> skusCardValues;
}