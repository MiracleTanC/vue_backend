package com.shop.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限关联实体
 */
@Data
@TableName("sys_role_rule")
public class RoleRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 规则ID
     */
    private Long ruleId;
}