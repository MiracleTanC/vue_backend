package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.Goods;

/**
 * 商品服务接口
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 分页查询商品列表
     */
    Page<Goods> getPage(Integer page, Integer pageSize, String keyword, Integer status, Long categoryId);

    /**
     * 批量更新商品状态
     */
    void updateStatus(List<Long> ids, Integer status);

    /**
     * 批量删除商品（软删除）
     */
    void deleteBatch(List<Long> ids);

    /**
     * 恢复商品
     */
    void restoreBatch(List<Long> ids);

    /**
     * 彻底删除商品
     */
    void destroyBatch(List<Long> ids);
}
