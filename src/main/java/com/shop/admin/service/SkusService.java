package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.Skus;

import java.util.List;

/**
 * 规格模板服务接口
 */
public interface SkusService extends IService<Skus> {

    /**
     * 分页查询规格列表
     */
    Page<Skus> getPage(Integer page, Integer pageSize);

    /**
     * 批量删除规格
     */
    void deleteBatch(List<Long> ids);

    /**
     * 更新规格状态
     */
    void updateStatus(Long id, Integer status);
}
