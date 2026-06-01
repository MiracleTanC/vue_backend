package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.GoodsComment;

/**
 * 商品评价服务接口
 */
public interface GoodsCommentService extends IService<GoodsComment> {

    /**
     * 分页查询评价列表
     */
    Page<GoodsComment> getPage(Integer page, Integer pageSize, Long goodsId, Integer status);

    /**
     * 更新评价状态
     */
    void updateStatus(Long id, Integer status);

    /**
     * 审核回复评价
     */
    void review(Long id, String reply);
}
