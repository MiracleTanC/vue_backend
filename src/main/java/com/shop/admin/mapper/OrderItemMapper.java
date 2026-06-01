package com.shop.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.admin.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单商品明细Mapper
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
}
