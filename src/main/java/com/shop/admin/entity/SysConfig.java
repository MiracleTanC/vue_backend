package com.shop.admin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统配置实体
 */
@Data
@TableName("sys_config")
public class SysConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 网站名称
     */
    private String siteName;

    /**
     * 网站Logo
     */
    private String logo;

    /**
     * 网站描述
     */
    private String description;

    /**
     * 网站关键词
     */
    private String keywords;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 联系邮箱
     */
    private String email;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 备案号
     */
    private String icp;

    /**
     * 微信支付商户号
     */
    private String wepayMchId;

    /**
     * 微信支付密钥
     */
    private String wepaySecret;

    /**
     * 支付宝应用ID
     */
    private String alipayAppId;

    /**
     * 支付宝私钥
     */
    private String alipayPrivateKey;

    /**
     * 物流查询接口
     */
    private String expressApi;

    /**
     * 物流查询密钥
     */
    private String expressKey;

    /**
     * 默认运费
     */
    private java.math.BigDecimal defaultFreight;

    /**
     * 免运费金额
     */
    private java.math.BigDecimal freeFreightAmount;
}