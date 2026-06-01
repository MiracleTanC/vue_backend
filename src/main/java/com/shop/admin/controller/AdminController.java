package com.shop.admin.controller;

import com.shop.admin.common.Result;
import com.shop.admin.entity.Manager;
import com.shop.admin.service.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员控制器（登录认证）
 */
@Tag(name = "管理员认证")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ManagerService managerService;

    /**
     * 登录
     */
    @Operation(summary = "登录")
    @PostMapping("/login")
    public Result<?> login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        Manager manager = managerService.login(username, password);
        Map<String, Object> data = new HashMap<>();
        data.put("token", manager.getToken());
        return Result.success(data);
    }

    /**
     * 获取当前用户信息
     */
    @Operation(summary = "获取当前用户信息")
    @PostMapping("/getinfo")
    public Result<?> getinfo(HttpServletRequest request) {
        Manager manager = (Manager) request.getAttribute("manager");
        Map<String, Object> data = new HashMap<>();
        data.put("id", manager.getId());
        data.put("username", manager.getUsername());
        data.put("avatar", manager.getAvatar());
        data.put("menus", manager.getMenus());
        data.put("ruleNames", manager.getRuleNames());
        return Result.success(data);
    }

    /**
     * 退出登录
     */
    @Operation(summary = "退出登录")
    @PostMapping("/logout")
    public Result<?> logout(HttpServletRequest request) {
        Manager manager = (Manager) request.getAttribute("manager");
        manager.setToken(null);
        managerService.updateById(manager);
        return Result.success();
    }

    /**
     * 修改密码
     */
    @Operation(summary = "修改密码")
    @PostMapping("/updatepassword")
    public Result<?> updatePassword(HttpServletRequest request, @RequestBody Map<String, String> params) {
        Manager manager = (Manager) request.getAttribute("manager");
        String oldPassword = params.get("oldpassword");
        String newPassword = params.get("password");
        managerService.updatePassword(manager.getId(), oldPassword, newPassword);
        return Result.success();
    }
}
