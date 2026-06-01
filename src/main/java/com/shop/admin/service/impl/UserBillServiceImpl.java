package com.shop.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.User;
import com.shop.admin.entity.UserBill;
import com.shop.admin.mapper.UserBillMapper;
import com.shop.admin.mapper.UserMapper;
import com.shop.admin.service.UserBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户账单服务实现
 */
@Service
public class UserBillServiceImpl extends ServiceImpl<UserBillMapper, UserBill> implements UserBillService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<UserBill> getPage(Integer page, Integer pageSize, Long userId) {
        LambdaQueryWrapper<UserBill> wrapper = new LambdaQueryWrapper<>();
        if (userId != null) {
            wrapper.eq(UserBill::getUserId, userId);
        }
        wrapper.orderByDesc(UserBill::getCreateTime);
        Page<UserBill> pageResult = this.page(new Page<>(page, pageSize), wrapper);
        // 填充用户信息
        pageResult.getRecords().forEach(bill -> {
            User user = userMapper.selectById(bill.getUserId());
            bill.setUser(user);
        });
        return pageResult;
    }
}
