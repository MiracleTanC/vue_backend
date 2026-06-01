package com.shop.admin.controller;

import com.shop.admin.common.Result;
import com.shop.admin.entity.SysConfig;
import com.shop.admin.service.SysConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 系统配置控制器
 */
@Tag(name = "系统配置")
@RestController
@RequestMapping("/admin/sysconfig")
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    @Value("${file.upload-path}")
    private String uploadPath;

    /**
     * 获取系统配置
     */
    @Operation(summary = "获取系统配置")
    @GetMapping
    public Result<?> get() {
        SysConfig config = sysConfigService.getConfig();
        return Result.success(config);
    }

    /**
     * 保存系统配置
     */
    @Operation(summary = "保存系统配置")
    @PostMapping
    public Result<?> save(@RequestBody SysConfig config) {
        sysConfigService.saveConfig(config);
        return Result.success();
    }

    /**
     * 上传文件
     */
    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public Result<?> upload(@RequestParam("file") MultipartFile file) throws IOException {
        // 创建上传目录
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 生成文件名
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : "";
        String filename = UUID.randomUUID().toString() + extension;

        // 保存文件
        File dest = new File(uploadPath + filename);
        file.transferTo(dest);

        Map<String, Object> data = new HashMap<>();
        data.put("url", "/uploads/" + filename);
        return Result.success(data);
    }
}
