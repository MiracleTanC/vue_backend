package com.shop.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.UserLevel;
import com.shop.admin.exception.BusinessException;
import com.shop.admin.mapper.UserLevelMapper;
import com.shop.admin.service.UserLevelService;
import org.springframework.stereotype.Service;

/**
 * 会员等级服务实现
 */
@Service
public class UserLevelServiceImpl extends ServiceImpl<UserLevelMapper, UserLevel> implements UserLevelService {

    @Override
    public Page<UserLevel> getPage(Integer page, Integer pageSize) {
        LambdaQueryWrapper<UserLevel> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(UserLevel::getExp);
        return this.page(new Page<>(page, pageSize), wrapper);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        UserLevel level = this.getById(id);
        if (level == null) {
            throw new BusinessException("会员等级不存在");
        }
        level.setStatus(status);
        this.updateById(level);
    }
}
