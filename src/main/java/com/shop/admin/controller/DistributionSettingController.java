package com.shop.admin.controller;

import com.shop.admin.common.Result;
import com.shop.admin.entity.DistributionSetting;
import com.shop.admin.service.DistributionSettingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 分销设置控制器
 */
@Tag(name = "分销设置")
@RestController
@RequestMapping("/admin/distribution_setting")
public class DistributionSettingController {

    @Autowired
    private DistributionSettingService distributionSettingService;

    /**
     * 获取分销设置
     */
    @Operation(summary = "获取分销设置")
    @GetMapping("/get")
    public Result<?> get() {
        DistributionSetting setting = distributionSettingService.getConfig();
        return Result.success(setting);
    }

    /**
     * 保存分销设置
     */
    @Operation(summary = "保存分销设置")
    @PostMapping("/set")
    public Result<?> set(@RequestBody DistributionSetting setting) {
        distributionSettingService.saveConfig(setting);
        return Result.success();
    }
}
