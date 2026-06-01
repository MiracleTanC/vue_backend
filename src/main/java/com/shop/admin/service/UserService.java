package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.User;

/**
 * 用户服务接口
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询用户列表
     */
    Page<User> getPage(Integer page, Integer pageSize, String keyword, Integer status);

    /**
     * 更新用户状态
     */
    void updateStatus(Long id, Integer status);
}
