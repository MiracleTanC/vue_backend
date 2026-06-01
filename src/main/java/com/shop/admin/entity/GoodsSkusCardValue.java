package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品SKU规格值
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("goods_skus_card_value")
public class GoodsSkusCardValue extends BaseEntity {

    /**
     * SKU卡片ID
     */
    private Long skusCardId;

    /**
     * 规格值名称
     */
    private String name;

    /**
     * 规格值图片
     */
    private String image;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 价格（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private BigDecimal price;

    /**
     * 库存（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private Integer stock;
}