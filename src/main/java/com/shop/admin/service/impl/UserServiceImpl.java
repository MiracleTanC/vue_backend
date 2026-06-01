package com.shop.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.User;
import com.shop.admin.entity.UserLevel;
import com.shop.admin.exception.BusinessException;
import com.shop.admin.mapper.UserLevelMapper;
import com.shop.admin.mapper.UserMapper;
import com.shop.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserLevelMapper userLevelMapper;

    @Override
    public Page<User> getPage(Integer page, Integer pageSize, String keyword, Integer status) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(keyword)) {
            wrapper.like(User::getUsername, keyword)
                    .or().like(User::getNickname, keyword)
                    .or().like(User::getPhone, keyword);
        }
        if (status != null) {
            wrapper.eq(User::getStatus, status);
        }
        wrapper.orderByDesc(User::getCreateTime);
        Page<User> pageResult = this.page(new Page<>(page, pageSize), wrapper);
        // 填充会员等级信息
        pageResult.getRecords().forEach(user -> {
            if (user.getLevelId() != null) {
                UserLevel level = userLevelMapper.selectById(user.getLevelId());
                user.setUserLevel(level);
            }
        });
        return pageResult;
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        User user = this.getById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        user.setStatus(status);
        this.updateById(user);
    }
}
