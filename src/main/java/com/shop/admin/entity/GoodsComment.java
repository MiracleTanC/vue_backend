package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 商品评价实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("goods_comment")
public class GoodsComment extends BaseEntity {

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 评价内容
     */
    private String content;

    /**
     * 评价图片（JSON数组）
     */
    private String images;

    /**
     * 评分 1-5
     */
    private Integer rating;

    /**
     * 状态 0-待审核 1-已审核 2-已拒绝
     */
    private Integer status;

    /**
     * 回复内容
     */
    private String reply;

    /**
     * 回复时间
     */
    private LocalDateTime replyTime;

    /**
     * 商品信息（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private Goods goods;

    /**
     * 用户信息（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private User user;
}