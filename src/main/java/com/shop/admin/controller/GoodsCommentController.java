package com.shop.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.admin.common.PageResult;
import com.shop.admin.common.Result;
import com.shop.admin.entity.GoodsComment;
import com.shop.admin.service.GoodsCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 商品评价控制器
 */
@Tag(name = "商品评价管理")
@RestController
@RequestMapping("/admin/goods_comment")
public class GoodsCommentController {

    @Autowired
    private GoodsCommentService goodsCommentService;

    /**
     * 分页列表
     */
    @Operation(summary = "分页列表")
    @GetMapping("/{page}")
    public Result<?> getPage(@PathVariable Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @RequestParam(required = false) Long goods_id,
                             @RequestParam(required = false) Integer status) {
        Page<GoodsComment> pageResult = goodsCommentService.getPage(page, limit, goods_id, status);
        return Result.success(PageResult.of(pageResult.getCurrent(), pageResult.getSize(),
                pageResult.getTotal(), pageResult.getRecords()));
    }

    /**
     * 更新状态
     */
    @Operation(summary = "更新状态")
    @PostMapping("/{id}/update_status")
    public Result<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, Integer> params) {
        Integer status = params.get("status");
        goodsCommentService.updateStatus(id, status);
        return Result.success();
    }

    /**
     * 审核回复
     */
    @Operation(summary = "审核回复")
    @PostMapping("/review/{id}")
    public Result<?> review(@PathVariable Long id, @RequestBody Map<String, String> params) {
        String reply = params.get("reply");
        goodsCommentService.review(id, reply);
        return Result.success();
    }
}
