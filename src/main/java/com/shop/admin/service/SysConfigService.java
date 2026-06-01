package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.SysConfig;

/**
 * 系统配置服务接口
 */
public interface SysConfigService extends IService<SysConfig> {

    /**
     * 获取系统配置
     */
    SysConfig getConfig();

    /**
     * 保存系统配置
     */
    void saveConfig(SysConfig config);
}
