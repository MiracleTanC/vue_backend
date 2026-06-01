package com.shop.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.Coupon;
import com.shop.admin.exception.BusinessException;
import com.shop.admin.mapper.CouponMapper;
import com.shop.admin.service.CouponService;
import org.springframework.stereotype.Service;

/**
 * 优惠券服务实现
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {

    @Override
    public Page<Coupon> getPage(Integer page, Integer pageSize) {
        LambdaQueryWrapper<Coupon> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Coupon::getCreateTime);
        return this.page(new Page<>(page, pageSize), wrapper);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Coupon coupon = this.getById(id);
        if (coupon == null) {
            throw new BusinessException("优惠券不存在");
        }
        coupon.setStatus(status);
        this.updateById(coupon);
    }
}
