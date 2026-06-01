package com.shop.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.admin.common.PageResult;
import com.shop.admin.common.Result;
import com.shop.admin.entity.Role;
import com.shop.admin.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色管理控制器
 */
@Tag(name = "角色管理")
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 分页列表
     */
    @Operation(summary = "分页列表")
    @GetMapping("/{page}")
    public Result<?> getPage(@PathVariable Integer page,
                             @RequestParam(defaultValue = "10") Integer limit) {
        Page<Role> pageResult = roleService.getPage(page, limit);
        return Result.success(PageResult.of(pageResult.getCurrent(), pageResult.getSize(),
                pageResult.getTotal(), pageResult.getRecords()));
    }

    /**
     * 新增
     */
    @Operation(summary = "新增")
    @PostMapping
    public Result<?> create(@RequestBody Role role) {
        roleService.save(role);
        return Result.success();
    }

    /**
     * 修改
     */
    @Operation(summary = "修改")
    @PostMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Role role) {
        role.setId(id);
        roleService.updateById(role);
        return Result.success();
    }

    /**
     * 更新状态
     */
    @Operation(summary = "更新状态")
    @PostMapping("/{id}/update_status")
    public Result<?> updateStatus(@PathVariable Long id, @RequestBody Map<String, Integer> params) {
        Integer status = params.get("status");
        roleService.updateStatus(id, status);
        return Result.success();
    }

    /**
     * 删除
     */
    @Operation(summary = "删除")
    @PostMapping("/{id}/delete")
    public Result<?> delete(@PathVariable Long id) {
        roleService.removeById(id);
        return Result.success();
    }

    /**
     * 设置权限
     */
    @Operation(summary = "设置权限")
    @PostMapping("/set_rules")
    public Result<?> setRules(@RequestBody Map<String, Object> params) {
        Long id = Long.valueOf(params.get("id").toString());
        @SuppressWarnings("unchecked")
        List<Long> ruleIds = (List<Long>) params.get("rule_ids");
        roleService.setRules(id, ruleIds);
        return Result.success();
    }
}
