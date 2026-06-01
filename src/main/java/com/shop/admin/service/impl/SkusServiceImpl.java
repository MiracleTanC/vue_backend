package com.shop.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.Skus;
import com.shop.admin.exception.BusinessException;
import com.shop.admin.mapper.SkusMapper;
import com.shop.admin.service.SkusService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 规格模板服务实现
 */
@Service
public class SkusServiceImpl extends ServiceImpl<SkusMapper, Skus> implements SkusService {

    @Override
    public Page<Skus> getPage(Integer page, Integer pageSize) {
        LambdaQueryWrapper<Skus> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Skus::getCreateTime);
        return this.page(new Page<>(page, pageSize), wrapper);
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.removeByIds(ids);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Skus skus = this.getById(id);
        if (skus == null) {
            throw new BusinessException("规格不存在");
        }
        skus.setStatus(status);
        this.updateById(skus);
    }
}
