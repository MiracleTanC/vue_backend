package com.shop.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.SysConfig;
import com.shop.admin.mapper.SysConfigMapper;
import com.shop.admin.service.SysConfigService;
import org.springframework.stereotype.Service;

/**
 * 系统配置服务实现
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

    @Override
    public SysConfig getConfig() {
        return this.getById(1L);
    }

    @Override
    public void saveConfig(SysConfig config) {
        config.setId(1L);
        this.saveOrUpdate(config);
    }
}
