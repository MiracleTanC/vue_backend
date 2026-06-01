package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.Manager;

import java.util.List;

/**
 * 管理员服务接口
 */
public interface ManagerService extends IService<Manager> {

    /**
     * 登录
     */
    Manager login(String username, String password);

    /**
     * 根据Token获取管理员信息
     */
    Manager getByToken(String token);

    /**
     * 分页查询管理员列表
     */
    Page<Manager> getPage(Integer page, Integer pageSize, String keyword);

    /**
     * 更新管理员状态
     */
    void updateStatus(Long id, Integer status);

    /**
     * 修改密码
     */
    void updatePassword(Long id, String oldPassword, String newPassword);
}
