package com.shop.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.admin.common.PageResult;
import com.shop.admin.common.Result;
import com.shop.admin.entity.Manager;
import com.shop.admin.service.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 管理员管理控制器
 */
@Tag(name = "管理员管理")
@RestController
@RequestMapping("/admin/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    /**
     * 分页列表
     */
    @Operation(summary = "分页列表")
    @GetMapping("/{page}")
    public Result<?> getPage(@PathVariable Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @RequestParam(required = false) String keyword) {
        Page<Manager> pageResult = managerService.getPage(page, limit, keyword);
        return Result.success(PageResult.of(pageResult.getCurrent(), pageResult.getSize(),
                pageResult.getTotal(), pageResult.getRecords()));
    }

    /**
     * 新增
     */
    @Operation(summary = "新增")
    @PostMapping
    public Result<?> create(@RequestBody Manager manager) {
        // 密码加密
        String md5Password = cn.hutool.crypto.digest.DigestUtil.md5Hex(manager.getPassword());
        manager.setPassword(md5Password);
        managerService.save(manager);
        return Result.success();
    }

    /**
     * 修改
     */
    @Operation(summary = "修改")
    @PostMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Manager manager) {
        manager.setId(id);
        managerService.updateById(manager);
        return Result.success();
    }

    /**
     * 更新状态
     */
    @Operation(summary = "更新状态")
    @PostMapping("/{id}/update_status")
    public Result<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, Integer> params) {
        Integer status = params.get("status");
        managerService.updateStatus(id, status);
        return Result.success();
    }

    /**
     * 删除
     */
    @Operation(summary = "删除")
    @PostMapping("/{id}/delete")
    public Result<?> delete(@PathVariable Long id) {
        managerService.removeById(id);
        return Result.success();
    }
}
