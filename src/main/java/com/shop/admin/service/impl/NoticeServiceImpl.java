package com.shop.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.Notice;
import com.shop.admin.mapper.NoticeMapper;
import com.shop.admin.service.NoticeService;
import org.springframework.stereotype.Service;

/**
 * 公告服务实现
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Override
    public Page<Notice> getPage(Integer page, Integer pageSize) {
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Notice::getCreateTime);
        return this.page(new Page<>(page, pageSize), wrapper);
    }
}
