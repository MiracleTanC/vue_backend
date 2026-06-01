package com.shop.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.Role;
import com.shop.admin.entity.RoleRule;
import com.shop.admin.exception.BusinessException;
import com.shop.admin.mapper.RoleMapper;
import com.shop.admin.mapper.RoleRuleMapper;
import com.shop.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色服务实现
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleRuleMapper roleRuleMapper;

    @Override
    public Page<Role> getPage(Integer page, Integer pageSize) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Role::getCreateTime);
        return this.page(new Page<>(page, pageSize), wrapper);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Role role = this.getById(id);
        if (role == null) {
            throw new BusinessException("角色不存在");
        }
        role.setStatus(status);
        this.updateById(role);
    }

    @Override
    @Transactional
    public void setRules(Long id, List<Long> ruleIds) {
        // 删除原有关联
        roleRuleMapper.delete(new LambdaQueryWrapper<RoleRule>()
                .eq(RoleRule::getRoleId, id));
        // 添加新关联
        if (ruleIds != null && !ruleIds.isEmpty()) {
            for (Long ruleId : ruleIds) {
                RoleRule roleRule = new RoleRule();
                roleRule.setRoleId(id);
                roleRule.setRuleId(ruleId);
                roleRuleMapper.insert(roleRule);
            }
        }
    }
}
