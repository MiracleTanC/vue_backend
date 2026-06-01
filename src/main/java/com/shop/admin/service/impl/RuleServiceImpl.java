package com.shop.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.Rule;
import com.shop.admin.exception.BusinessException;
import com.shop.admin.mapper.RuleMapper;
import com.shop.admin.service.RuleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 权限规则服务实现
 */
@Service
public class RuleServiceImpl extends ServiceImpl<RuleMapper, Rule> implements RuleService {

    @Override
    public Page<Rule> getPage(Integer page, Integer pageSize) {
        LambdaQueryWrapper<Rule> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Rule::getOrderNum);
        return this.page(new Page<>(page, pageSize), wrapper);
    }

    @Override
    public List<Rule> getMenuTree() {
        // 查询所有菜单
        List<Rule> all = this.list(new LambdaQueryWrapper<Rule>()
                .isNotNull(Rule::getFrontpath)
                .orderByAsc(Rule::getOrderNum));
        // 构建树形结构
        return buildTree(all, 0L);
    }

    private List<Rule> buildTree(List<Rule> list, Long pid) {
        List<Rule> tree = new ArrayList<>();
        for (Rule rule : list) {
            if (pid.equals(rule.getPid())) {
                List<Rule> child = buildTree(list, rule.getId());
                rule.setChild(child);
                tree.add(rule);
            }
        }
        return tree;
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Rule rule = this.getById(id);
        if (rule == null) {
            throw new BusinessException("规则不存在");
        }
        rule.setStatus(status);
        this.updateById(rule);
    }
}
