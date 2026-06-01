package com.shop.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.ImageClass;
import com.shop.admin.mapper.ImageClassMapper;
import com.shop.admin.service.ImageClassService;
import org.springframework.stereotype.Service;

/**
 * 图库分类服务实现
 */
@Service
public class ImageClassServiceImpl extends ServiceImpl<ImageClassMapper, ImageClass> implements ImageClassService {

    @Override
    public Page<ImageClass> getPage(Integer page, Integer pageSize) {
        LambdaQueryWrapper<ImageClass> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(ImageClass::getOrderNum);
        return this.page(new Page<>(page, pageSize), wrapper);
    }
}
