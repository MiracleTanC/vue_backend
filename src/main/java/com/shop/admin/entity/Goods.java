package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("goods")
public class Goods extends BaseEntity {

    /**
     * 商品名称
     */
    private String name;

    /**
     * 分类ID
     */
    private Long categoryId;

    /**
     * 商品封面
     */
    private String cover;

    /**
     * 商品图片（JSON数组）
     */
    private String images;

    /**
     * 商品详情（富文本）
     */
    private String content;

    /**
     * 商品简介
     */
    private String desc;

    /**
     * 单位（件/个/斤等）
     */
    private String unit;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 最小购买数量
     */
    private Integer minNum;

    /**
     * 是否开启规格 0-否 1-是
     */
    private Integer isSkus;

    /**
     * 原价
     */
    private BigDecimal originalPrice;

    /**
     * 现价
     */
    private BigDecimal price;

    /**
     * 最低价（SKU最小价格）
     */
    private BigDecimal minPrice;

    /**
     * 最高价（SKU最大价格）
     */
    private BigDecimal maxPrice;

    /**
     * 状态 0-仓库 1-上架
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 分类信息（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private Category category;

    /**
     * SKU列表（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private java.util.List<GoodsSkusCard> skusCards;

    /**
     * 轮播图列表（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private java.util.List<GoodsBanner> banners;
}