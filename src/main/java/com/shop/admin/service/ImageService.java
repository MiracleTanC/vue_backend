package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.Image;

import java.util.List;

/**
 * 图片服务接口
 */
public interface ImageService extends IService<Image> {

    /**
     * 分页查询图片列表
     */
    Page<Image> getPage(Long imageClassId, Integer page, Integer pageSize);

    /**
     * 批量删除图片
     */
    void deleteBatch(List<Long> ids);
}
