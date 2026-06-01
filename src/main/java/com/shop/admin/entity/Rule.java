package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限规则实体（菜单）
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_rule")
public class Rule extends BaseEntity {

    /**
     * 上级ID 0-顶级
     */
    private Long pid;

    /**
     * 规则名称/菜单名称
     */
    private String name;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 前端路径
     */
    private String frontpath;

    /**
     * 条件字段
     */
    private String condition;

    /**
     * 请求方式 GET/POST/PUT/DELETE
     */
    private String method;

    /**
     * 状态 0-禁用 1-启用
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 子菜单（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private java.util.List<Rule> child;
}