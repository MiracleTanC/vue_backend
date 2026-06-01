package com.shop.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.admin.common.PageResult;
import com.shop.admin.common.Result;
import com.shop.admin.entity.Coupon;
import com.shop.admin.service.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 优惠券管理控制器
 */
@Tag(name = "优惠券管理")
@RestController
@RequestMapping("/admin/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 分页列表
     */
    @Operation(summary = "分页列表")
    @GetMapping("/{page}")
    public Result<?> getPage(@PathVariable Integer page,
                             @RequestParam(defaultValue = "10") Integer limit) {
        Page<Coupon> pageResult = couponService.getPage(page, limit);
        return Result.success(PageResult.of(pageResult.getCurrent(), pageResult.getSize(),
                pageResult.getTotal(), pageResult.getRecords()));
    }

    /**
     * 新增
     */
    @Operation(summary = "新增")
    @PostMapping
    public Result<?> create(@RequestBody Coupon coupon) {
        couponService.save(coupon);
        return Result.success();
    }

    /**
     * 修改
     */
    @Operation(summary = "修改")
    @PostMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Coupon coupon) {
        coupon.setId(id);
        couponService.updateById(coupon);
        return Result.success();
    }

    /**
     * 更新状态
     */
    @Operation(summary = "更新状态")
    @PostMapping("/{id}/update_status")
    public Result<?> updateStatus(@PathVariable Long id) {
        couponService.updateStatus(id, 0);
        return Result.success();
    }

    /**
     * 删除
     */
    @Operation(summary = "删除")
    @PostMapping("/{id}/delete")
    public Result<?> delete(@PathVariable Long id) {
        couponService.removeById(id);
        return Result.success();
    }
}
