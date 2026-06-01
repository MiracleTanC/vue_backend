package com.shop.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop.admin.entity.DistributionSetting;

/**
 * 分销设置服务接口
 */
public interface DistributionSettingService extends IService<DistributionSetting> {

    /**
     * 获取分销设置
     */
    DistributionSetting getConfig();

    /**
     * 保存分销设置
     */
    void saveConfig(DistributionSetting setting);
}
