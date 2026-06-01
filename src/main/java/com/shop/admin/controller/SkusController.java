package com.shop.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.admin.common.PageResult;
import com.shop.admin.common.Result;
import com.shop.admin.entity.Skus;
import com.shop.admin.service.SkusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 规格模板控制器
 */
@Tag(name = "规格模板管理")
@RestController
@RequestMapping("/admin/skus")
public class SkusController {

    @Autowired
    private SkusService skusService;

    /**
     * 分页列表
     */
    @Operation(summary = "分页列表")
    @GetMapping("/{page}")
    public Result<?> getPage(@PathVariable Integer page,
                             @RequestParam(defaultValue = "10") Integer limit) {
        Page<Skus> pageResult = skusService.getPage(page, limit);
        return Result.success(PageResult.of(pageResult.getCurrent(), pageResult.getSize(),
                pageResult.getTotal(), pageResult.getRecords()));
    }

    /**
     * 新增
     */
    @Operation(summary = "新增")
    @PostMapping
    public Result<?> create(@RequestBody Skus skus) {
        skusService.save(skus);
        return Result.success();
    }

    /**
     * 修改
     */
    @Operation(summary = "修改")
    @PostMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Skus skus) {
        skus.setId(id);
        skusService.updateById(skus);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @Operation(summary = "批量删除")
    @PostMapping("/delete_all")
    public Result<?> deleteBatch(@RequestBody Map<String, List<Long>> params) {
        List<Long> ids = params.get("ids");
        skusService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新状态
     */
    @Operation(summary = "更新状态")
    @PostMapping("/{id}/update_status")
    public Result<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, Integer> params) {
        Integer status = params.get("status");
        skusService.updateStatus(id, status);
        return Result.success();
    }
}
