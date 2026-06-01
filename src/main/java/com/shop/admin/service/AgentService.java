package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.Agent;

import java.util.Map;

/**
 * 分销员服务接口
 */
public interface AgentService extends IService<Agent> {

    /**
     * 分页查询分销员列表
     */
    Page<Agent> getPage(Integer page, Integer pageSize, String keyword);

    /**
     * 获取分销统计
     */
    Map<String, Object> getStatistics();
}
