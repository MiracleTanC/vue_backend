package com.shop.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 管理员实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_manager")
public class Manager extends BaseEntity {

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
     * 角色ID
     */
    private Long roleId;

    /**
     * 状态 0-禁用 1-启用
     */
    private Integer status;

    /**
     * 登录Token
     */
    private String token;

    /**
     * 角色信息（非数据库字段）
     */
    @TableField(exist = false)
    private Role role;

    /**
     * 菜单列表（非数据库字段）
     */
    @TableField(exist = false)
    private java.util.List<Rule> menus;

    /**
     * 权限标识列表（非数据库字段）
     */
    @TableField(exist = false)
    private java.util.List<String> ruleNames;
}