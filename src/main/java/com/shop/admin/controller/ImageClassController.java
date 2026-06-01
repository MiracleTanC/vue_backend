package com.shop.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.admin.common.PageResult;
import com.shop.admin.common.Result;
import com.shop.admin.entity.ImageClass;
import com.shop.admin.service.ImageClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 图库分类控制器
 */
@Tag(name = "图库分类管理")
@RestController
@RequestMapping("/admin/image_class")
public class ImageClassController {

    @Autowired
    private ImageClassService imageClassService;

    /**
     * 分页列表
     */
    @Operation(summary = "分页列表")
    @GetMapping("/{page}")
    public Result<?> getPage(@PathVariable Integer page,
                             @RequestParam(defaultValue = "10") Integer limit) {
        Page<ImageClass> pageResult = imageClassService.getPage(page, limit);
        return Result.success(PageResult.of(pageResult.getCurrent(), pageResult.getSize(),
                pageResult.getTotal(), pageResult.getRecords()));
    }

    /**
     * 新增
     */
    @Operation(summary = "新增")
    @PostMapping
    public Result<?> create(@RequestBody ImageClass imageClass) {
        imageClassService.save(imageClass);
        return Result.success();
    }

    /**
     * 修改
     */
    @Operation(summary = "修改")
    @PostMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody ImageClass imageClass) {
        imageClass.setId(id);
        imageClassService.updateById(imageClass);
        return Result.success();
    }

    /**
     * 删除
     */
    @Operation(summary = "删除")
    @PostMapping("/{id}/delete")
    public Result<?> delete(@PathVariable Long id) {
        imageClassService.removeById(id);
        return Result.success();
    }
}
