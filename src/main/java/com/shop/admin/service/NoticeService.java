package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.Notice;

/**
 * 公告服务接口
 */
public interface NoticeService extends IService<Notice> {

    /**
     * 分页查询公告列表
     */
    Page<Notice> getPage(Integer page, Integer pageSize);
}
