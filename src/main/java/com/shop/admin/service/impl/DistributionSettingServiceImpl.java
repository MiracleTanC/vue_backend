package com.shop.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.DistributionSetting;
import com.shop.admin.mapper.DistributionSettingMapper;
import com.shop.admin.service.DistributionSettingService;
import org.springframework.stereotype.Service;

/**
 * 分销设置服务实现
 */
@Service
public class DistributionSettingServiceImpl extends ServiceImpl<DistributionSettingMapper, DistributionSetting> implements DistributionSettingService {

    @Override
    public DistributionSetting getConfig() {
        return this.getById(1L);
    }

    @Override
    public void saveConfig(DistributionSetting setting) {
        setting.setId(1L);
        this.saveOrUpdate(setting);
    }
}
