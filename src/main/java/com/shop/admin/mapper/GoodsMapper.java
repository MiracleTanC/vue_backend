package com.shop.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.admin.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品Mapper
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
}
