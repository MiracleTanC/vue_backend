package com.shop.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shop.admin.entity.Rule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 权限规则Mapper
 */
@Mapper
public interface RuleMapper extends BaseMapper<Rule> {

    /**
     * 根据角色ID获取菜单列表
     */
    List<Rule> selectMenusByRoleId(Long roleId);

    /**
     * 根据角色ID获取权限标识列表
     */
    List<String> selectRuleNamesByRoleId(Long roleId);
}
