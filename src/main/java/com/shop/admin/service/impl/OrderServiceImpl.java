package com.shop.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.Order;
import com.shop.admin.entity.OrderItem;
import com.shop.admin.entity.User;
import com.shop.admin.exception.BusinessException;
import com.shop.admin.mapper.OrderItemMapper;
import com.shop.admin.mapper.OrderMapper;
import com.shop.admin.mapper.UserMapper;
import com.shop.admin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单服务实现
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public Page<Order> getPage(Integer page, Integer pageSize, String orderNo, Integer status) {
        LambdaQueryWrapper<Order> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(orderNo)) {
            wrapper.like(Order::getOrderNo, orderNo);
        }
        if (status != null) {
            wrapper.eq(Order::getStatus, status);
        }
        wrapper.orderByDesc(Order::getCreateTime);
        Page<Order> pageResult = this.page(new Page<>(page, pageSize), wrapper);
        // 填充用户信息
        pageResult.getRecords().forEach(order -> {
            if (order.getUserId() != null) {
                User user = userMapper.selectById(order.getUserId());
                order.setUser(user);
            }
            // 填充订单商品
            List<OrderItem> items = orderItemMapper.selectList(
                    new LambdaQueryWrapper<OrderItem>().eq(OrderItem::getOrderId, order.getId()));
            order.setOrderItems(items);
        });
        return pageResult;
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.removeByIds(ids);
    }

    @Override
    public void ship(Long id, String expressCompany, String expressNo) {
        Order order = this.getById(id);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        if (order.getStatus() != 1) {
            throw new BusinessException("订单状态不允许发货");
        }
        order.setShipStatus(1);
        order.setExpressCompany(expressCompany);
        order.setExpressNo(expressNo);
        order.setShipTime(LocalDateTime.now());
        order.setStatus(2); // 待收货
        this.updateById(order);
    }

    @Override
    public void refund(Long id, String reason) {
        Order order = this.getById(id);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        order.setStatus(5); // 已退款
        order.setRemark(reason);
        this.updateById(order);
    }
}
