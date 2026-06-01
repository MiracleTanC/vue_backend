package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("order")
public class Order extends BaseEntity {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 收货地址JSON
     */
    private String address;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 商品总价
     */
    private BigDecimal totalPrice;

    /**
     * 实付金额
     */
    private BigDecimal payPrice;

    /**
     * 运费
     */
    private BigDecimal freight;

    /**
     * 优惠券ID
     */
    private Long couponId;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 支付方式 1-微信 2-支付宝
     */
    private Integer payType;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;

    /**
     * 订单状态 0-待支付 1-待发货 2-待收货 3-已完成 4-已取消 5-已退款
     */
    private Integer status;

    /**
     * 发货状态 0-未发货 1-已发货
     */
    private Integer shipStatus;

    /**
     * 物流公司
     */
    private String expressCompany;

    /**
     * 物流单号
     */
    private String expressNo;

    /**
     * 发货时间
     */
    private LocalDateTime shipTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户信息（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private User user;

    /**
     * 订单商品（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private java.util.List<OrderItem> orderItems;
}