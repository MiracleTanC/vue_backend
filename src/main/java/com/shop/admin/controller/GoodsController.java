package com.shop.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.admin.common.PageResult;
import com.shop.admin.common.Result;
import com.shop.admin.entity.Goods;
import com.shop.admin.service.GoodsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 商品管理控制器
 */
@Tag(name = "商品管理")
@RestController
@RequestMapping("/admin/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 分页列表
     */
    @Operation(summary = "分页列表")
    @GetMapping("/{page}")
    public Result<?> getPage(@PathVariable Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @RequestParam(required = false) String keyword,
                             @RequestParam(required = false) Integer status,
                             @RequestParam(required = false) Long category_id) {
        Page<Goods> pageResult = goodsService.getPage(page, limit, keyword, status, category_id);
        return Result.success(PageResult.of(pageResult.getCurrent(), pageResult.getSize(),
                pageResult.getTotal(), pageResult.getRecords()));
    }

    /**
     * 新增
     */
    @Operation(summary = "新增")
    @PostMapping
    public Result<?> create(@RequestBody Goods goods) {
        goodsService.save(goods);
        return Result.success();
    }

    /**
     * 修改
     */
    @Operation(summary = "修改")
    @PostMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Goods goods) {
        goods.setId(id);
        goodsService.updateById(goods);
        return Result.success();
    }

    /**
     * 获取详情
     */
    @Operation(summary = "获取详情")
    @GetMapping("/read/{id}")
    public Result<?> read(@PathVariable Long id) {
        Goods goods = goodsService.getById(id);
        return Result.success(goods);
    }

    /**
     * 批量更新状态（上下架）
     */
    @Operation(summary = "批量更新状态")
    @PostMapping("/changestatus")
    public Result<?> changeStatus(@RequestBody Map<String, Object> params) {
        @SuppressWarnings("unchecked")
        List<Long> ids = (List<Long>) params.get("ids");
        Integer status = (Integer) params.get("status");
        goodsService.updateStatus(ids, status);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @Operation(summary = "批量删除")
    @PostMapping("/delete_all")
    public Result<?> deleteBatch(@RequestBody Map<String, List<Long>> params) {
        List<Long> ids = params.get("ids");
        goodsService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 恢复商品
     */
    @Operation(summary = "恢复商品")
    @PostMapping("/restore")
    public Result<?> restore(@RequestBody Map<String, List<Long>> params) {
        List<Long> ids = params.get("ids");
        goodsService.restoreBatch(ids);
        return Result.success();
    }

    /**
     * 彻底删除
     */
    @Operation(summary = "彻底删除")
    @PostMapping("/destroy")
    public Result<?> destroy(@RequestBody Map<String, List<Long>> params) {
        List<Long> ids = params.get("ids");
        goodsService.destroyBatch(ids);
        return Result.success();
    }
}
