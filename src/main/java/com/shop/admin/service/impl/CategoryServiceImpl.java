package com.shop.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.Category;
import com.shop.admin.exception.BusinessException;
import com.shop.admin.mapper.CategoryMapper;
import com.shop.admin.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 分类服务实现
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public List<Category> getTree() {
        List<Category> all = this.list(new LambdaQueryWrapper<Category>()
                .orderByAsc(Category::getOrderNum));
        return buildTree(all, 0L);
    }

    private List<Category> buildTree(List<Category> list, Long pid) {
        List<Category> tree = new ArrayList<>();
        for (Category category : list) {
            if (pid.equals(category.getPid())) {
                List<Category> child = buildTree(list, category.getId());
                category.setChild(child);
                tree.add(category);
            }
        }
        return tree;
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Category category = this.getById(id);
        if (category == null) {
            throw new BusinessException("分类不存在");
        }
        category.setStatus(status);
        this.updateById(category);
    }
}
