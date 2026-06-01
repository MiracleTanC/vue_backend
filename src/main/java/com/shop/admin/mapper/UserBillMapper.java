package com.shop.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.admin.entity.UserBill;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户账单Mapper
 */
@Mapper
public interface UserBillMapper extends BaseMapper<UserBill> {
}