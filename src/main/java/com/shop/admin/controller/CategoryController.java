package com.shop.admin.controller;

import com.shop.admin.common.Result;
import com.shop.admin.entity.Category;
import com.shop.admin.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 分类管理控制器
 */
@Tag(name = "分类管理")
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取分类树
     */
    @Operation(summary = "获取分类树")
    @GetMapping
    public Result<?> list() {
        List<Category> tree = categoryService.getTree();
        return Result.success(tree);
    }

    /**
     * 新增
     */
    @Operation(summary = "新增")
    @PostMapping
    public Result<?> create(@RequestBody Category category) {
        categoryService.save(category);
        return Result.success();
    }

    /**
     * 修改
     */
    @Operation(summary = "修改")
    @PostMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Category category) {
        category.setId(id);
        categoryService.updateById(category);
        return Result.success();
    }

    /**
     * 更新状态
     */
    @Operation(summary = "更新状态")
    @PostMapping("/{id}/update_status")
    public Result<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, Integer> params) {
        Integer status = params.get("status");
        categoryService.updateStatus(id, status);
        return Result.success();
    }

    /**
     * 删除
     */
    @Operation(summary = "删除")
    @PostMapping("/{id}/delete")
    public Result<?> delete(@PathVariable Long id) {
        categoryService.removeById(id);
        return Result.success();
    }
}
