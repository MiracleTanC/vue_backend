package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user")
public class User extends BaseEntity {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 会员等级ID
     */
    private Long levelId;

    /**
     * 状态 0-禁用 1-启用
     */
    private Integer status;

    /**
     * 会员等级（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private UserLevel userLevel;
}