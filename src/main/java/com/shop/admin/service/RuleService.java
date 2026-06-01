package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.Rule;

import java.util.List;

/**
 * 权限规则服务接口
 */
public interface RuleService extends IService<Rule> {

    /**
     * 分页查询规则列表
     */
    Page<Rule> getPage(Integer page, Integer pageSize);

    /**
     * 获取菜单树
     */
    List<Rule> getMenuTree();

    /**
     * 更新规则状态
     */
    void updateStatus(Long id, Integer status);
}
