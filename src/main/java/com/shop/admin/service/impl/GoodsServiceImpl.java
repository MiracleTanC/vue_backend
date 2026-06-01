package com.shop.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.Category;
import com.shop.admin.entity.Goods;
import com.shop.admin.mapper.CategoryMapper;
import com.shop.admin.mapper.GoodsMapper;
import com.shop.admin.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务实现
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Page<Goods> getPage(Integer page, Integer pageSize, String keyword, Integer status, Long categoryId) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(keyword)) {
            wrapper.like(Goods::getName, keyword);
        }
        if (status != null) {
            wrapper.eq(Goods::getStatus, status);
        }
        if (categoryId != null) {
            wrapper.eq(Goods::getCategoryId, categoryId);
        }
        wrapper.orderByDesc(Goods::getCreateTime);
        Page<Goods> pageResult = this.page(new Page<>(page, pageSize), wrapper);
        // 填充分类信息
        pageResult.getRecords().forEach(goods -> {
            if (goods.getCategoryId() != null) {
                Category category = categoryMapper.selectById(goods.getCategoryId());
                goods.setCategory(category);
            }
        });
        return pageResult;
    }

    @Override
    public void updateStatus(List<Long> ids, Integer status) {
        this.update(new LambdaUpdateWrapper<Goods>()
                .in(Goods::getId, ids)
                .set(Goods::getStatus, status));
    }

    @Override
    public void deleteBatch(List<Long> ids) {
        this.removeByIds(ids);
    }

    @Override
    public void restoreBatch(List<Long> ids) {
        // 软删除恢复需要手动更新deleted字段
        this.update(new LambdaUpdateWrapper<Goods>()
                .in(Goods::getId, ids)
                .set(Goods::getDeleted, 0));
    }

    @Override
    public void destroyBatch(List<Long> ids) {
        // 彻底删除
        this.removeByIds(ids);
    }
}
