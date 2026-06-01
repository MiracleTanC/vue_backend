package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.Coupon;

/**
 * 优惠券服务接口
 */
public interface CouponService extends IService<Coupon> {

    /**
     * 分页查询优惠券列表
     */
    Page<Coupon> getPage(Integer page, Integer pageSize);

    /**
     * 更新优惠券状态
     */
    void updateStatus(Long id, Integer status);
}
