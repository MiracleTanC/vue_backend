package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.Role;

import java.util.List;

/**
 * 角色服务接口
 */
public interface RoleService extends IService<Role> {

    /**
     * 分页查询角色列表
     */
    Page<Role> getPage(Integer page, Integer pageSize);

    /**
     * 更新角色状态
     */
    void updateStatus(Long id, Integer status);

    /**
     * 设置角色权限
     */
    void setRules(Long id, List<Long> ruleIds);
}
