package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.ImageClass;

/**
 * 图库分类服务接口
 */
public interface ImageClassService extends IService<ImageClass> {

    /**
     * 分页查询图库分类列表
     */
    Page<ImageClass> getPage(Integer page, Integer pageSize);
}
