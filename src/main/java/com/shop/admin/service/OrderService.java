package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.Order;

import java.util.List;

/**
 * 订单服务接口
 */
public interface OrderService extends IService<Order> {

    /**
     * 分页查询订单列表
     */
    Page<Order> getPage(Integer page, Integer pageSize, String orderNo, Integer status);

    /**
     * 批量删除订单
     */
    void deleteBatch(List<Long> ids);

    /**
     * 发货
     */
    void ship(Long id, String expressCompany, String expressNo);

    /**
     * 处理退款
     */
    void refund(Long id, String reason);
}
