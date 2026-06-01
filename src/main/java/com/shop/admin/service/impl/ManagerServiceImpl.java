package com.shop.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.Manager;
import com.shop.admin.entity.Role;
import com.shop.admin.entity.Rule;
import com.shop.admin.exception.BusinessException;
import com.shop.admin.mapper.ManagerMapper;
import com.shop.admin.mapper.RoleMapper;
import com.shop.admin.mapper.RuleMapper;
import com.shop.admin.service.ManagerService;
import com.shop.admin.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理员服务实现
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RuleMapper ruleMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public Manager login(String username, String password) {
        // 查询用户
        Manager manager = this.getOne(new LambdaQueryWrapper<Manager>()
                .eq(Manager::getUsername, username));
        if (manager == null) {
            throw new BusinessException("用户不存在");
        }
        // 验证密码
        String md5Password = DigestUtil.md5Hex(password);
        if (!md5Password.equals(manager.getPassword())) {
            throw new BusinessException("密码错误");
        }
        // 检查状态
        if (manager.getStatus() != 1) {
            throw new BusinessException("账号已被禁用");
        }
        // 生成Token
        String token = tokenUtil.generateToken(manager.getId());
        manager.setToken(token);
        this.updateById(manager);
        return manager;
    }

    @Override
    public Manager getByToken(String token) {
        Manager manager = this.getOne(new LambdaQueryWrapper<Manager>()
                .eq(Manager::getToken, token));
        if (manager != null) {
            // 查询角色
            Role role = roleMapper.selectById(manager.getRoleId());
            manager.setRole(role);
            // 查询菜单
            List<Rule> menus = ruleMapper.selectMenusByRoleId(manager.getRoleId());
            manager.setMenus(menus);
            // 查询权限标识
            List<String> ruleNames = ruleMapper.selectRuleNamesByRoleId(manager.getRoleId());
            manager.setRuleNames(ruleNames);
        }
        return manager;
    }

    @Override
    public Page<Manager> getPage(Integer page, Integer pageSize, String keyword) {
        LambdaQueryWrapper<Manager> wrapper = new LambdaQueryWrapper<>();
        if (StrUtil.isNotBlank(keyword)) {
            wrapper.like(Manager::getUsername, keyword);
        }
        wrapper.orderByDesc(Manager::getCreateTime);
        Page<Manager> pageResult = this.page(new Page<>(page, pageSize), wrapper);
        // 填充角色信息
        pageResult.getRecords().forEach(manager -> {
            if (manager.getRoleId() != null) {
                Role role = roleMapper.selectById(manager.getRoleId());
                manager.setRole(role);
            }
        });
        return pageResult;
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Manager manager = this.getById(id);
        if (manager == null) {
            throw new BusinessException("管理员不存在");
        }
        manager.setStatus(status);
        this.updateById(manager);
    }

    @Override
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        Manager manager = this.getById(id);
        if (manager == null) {
            throw new BusinessException("用户不存在");
        }
        String md5OldPassword = DigestUtil.md5Hex(oldPassword);
        if (!md5OldPassword.equals(manager.getPassword())) {
            throw new BusinessException("原密码错误");
        }
        manager.setPassword(DigestUtil.md5Hex(newPassword));
        this.updateById(manager);
    }
}
