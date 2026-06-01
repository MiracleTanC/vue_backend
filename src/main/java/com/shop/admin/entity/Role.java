package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    private String name;

    /**
     * 状态 0-禁用 1-启用
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;
}