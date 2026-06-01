package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.UserBill;

/**
 * 用户账单服务接口
 */
public interface UserBillService extends IService<UserBill> {

    /**
     * 分页查询账单列表
     */
    Page<UserBill> getPage(Integer page, Integer pageSize, Long userId);
}
