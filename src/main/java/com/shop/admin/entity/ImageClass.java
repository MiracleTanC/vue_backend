package com.shop.admin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 图库分类实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("image_class")
public class ImageClass extends BaseEntity {

    /**
     * 分类名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 图片列表（非数据库字段）
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private java.util.List<Image> images;
}