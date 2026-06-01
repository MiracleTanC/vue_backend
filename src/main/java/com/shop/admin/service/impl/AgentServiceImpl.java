package com.shop.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.Agent;
import com.shop.admin.entity.User;
import com.shop.admin.mapper.AgentMapper;
import com.shop.admin.mapper.UserMapper;
import com.shop.admin.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 分销员服务实现
 */
@Service
public class AgentServiceImpl extends ServiceImpl<AgentMapper, Agent> implements AgentService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<Agent> getPage(Integer page, Integer pageSize, String keyword) {
        LambdaQueryWrapper<Agent> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Agent::getCreateTime);
        Page<Agent> pageResult = this.page(new Page<>(page, pageSize), wrapper);
        // 填充用户信息
        pageResult.getRecords().forEach(agent -> {
            User user = userMapper.selectById(agent.getUserId());
            agent.setUser(user);
        });
        return pageResult;
    }

    @Override
    public Map<String, Object> getStatistics() {
        Map<String, Object> result = new HashMap<>();
        // 分销员总数
        long total = this.count();
        result.put("totalAgent", total);
        // 累计佣金
        BigDecimal totalCommission = this.baseMapper.selectList(null).stream()
                .map(Agent::getTotalCommission)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        result.put("totalCommission", totalCommission);
        return result;
    }
}
