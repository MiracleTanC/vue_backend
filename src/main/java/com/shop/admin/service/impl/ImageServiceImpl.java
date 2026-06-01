package com.shop.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.Image;
import com.shop.admin.mapper.ImageMapper;
import com.shop.admin.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 图片服务实现
 */
@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

    @Override
    public Page<Image> getPage(Long imageClassId, Integer page, Integer pageSize) {
        LambdaQueryWrapper<Image> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Image::getImageClassId, imageClassId);
        wrapper.orderByDesc(Image::getCreateTime);
        return this.page(new Page<>(page, pageSize), wrapper);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.removeByIds(ids);
    }
}
