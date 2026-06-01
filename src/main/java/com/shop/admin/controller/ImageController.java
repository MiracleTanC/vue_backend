package com.shop.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.admin.common.PageResult;
import com.shop.admin.common.Result;
import com.shop.admin.entity.Image;
import com.shop.admin.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 图片管理控制器
 */
@Tag(name = "图片管理")
@RestController
@RequestMapping("/admin/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Value("${file.upload-path}")
    private String uploadPath;

    /**
     * 分页列表
     */
    @Operation(summary = "分页列表")
    @GetMapping("/class/{imageClassId}/image/{page}")
    public Result<?> getPage(@PathVariable Long imageClassId,
                             @PathVariable Integer page,
                             @RequestParam(defaultValue = "10") Integer limit) {
        Page<Image> pageResult = imageService.getPage(imageClassId, page, limit);
        return Result.success(PageResult.of(pageResult.getCurrent(), pageResult.getSize(),
                pageResult.getTotal(), pageResult.getRecords()));
    }

    /**
     * 修改
     */
    @Operation(summary = "修改")
    @PostMapping("/{id}")
    public Result<?> update(@PathVariable Long id, @RequestBody Map<String, String> params) {
        String name = params.get("name");
        Image image = imageService.getById(id);
        if (image != null) {
            image.setName(name);
            imageService.updateById(image);
        }
        return Result.success();
    }

    /**
     * 批量删除
     */
    @Operation(summary = "批量删除")
    @PostMapping("/delete_all")
    public Result<?> deleteBatch(@RequestBody Map<String, List<Long>> params) {
        List<Long> ids = params.get("ids");
        imageService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 上传图片
     */
    @Operation(summary = "上传图片")
    @PostMapping("/upload")
    public Result<?> upload(@RequestParam("file") MultipartFile file,
                           @RequestParam("image_class_id") Long imageClassId) throws IOException {
        // 创建上传目录
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 生成文件名
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : ".jpg";
        String filename = UUID.randomUUID().toString() + extension;

        // 保存文件
        File dest = new File(uploadPath + filename);
        file.transferTo(dest);

        // 保存记录
        Image image = new Image();
        image.setImageClassId(imageClassId);
        image.setName(originalFilename);
        image.setUrl("/uploads/" + filename);
        imageService.save(image);

        Map<String, Object> data = new HashMap<>();
        data.put("id", image.getId());
        data.put("url", image.getUrl());
        return Result.success(data);
    }
}
