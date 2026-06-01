package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.UserLevel;

/**
 * 会员等级服务接口
 */
public interface UserLevelService extends IService<UserLevel> {

    /**
     * 分页查询会员等级列表
     */
    Page<UserLevel> getPage(Integer page, Integer pageSize);

    /**
     * 更新会员等级状态
     */
    void updateStatus(Long id, Integer status);
}
