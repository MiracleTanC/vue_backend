package com.shop.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.admin.common.PageResult;
import com.shop.admin.common.Result;
import com.shop.admin.entity.Agent;
import com.shop.admin.entity.UserBill;
import com.shop.admin.service.AgentService;
import com.shop.admin.service.UserBillService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 分销管理控制器
 */
@Tag(name = "分销管理")
@RestController
@RequestMapping("/admin")
public class DistributionController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private UserBillService userBillService;

    /**
     * 分销员分页列表
     */
    @Operation(summary = "分销员分页列表")
    @GetMapping("/agent/{page}")
    public Result<?> getAgentPage(@PathVariable Integer page,
                                  @RequestParam(defaultValue = "10") Integer limit,
                                  @RequestParam(required = false) String keyword) {
        Page<Agent> pageResult = agentService.getPage(page, limit, keyword);
        return Result.success(PageResult.of(pageResult.getCurrent(), pageResult.getSize(),
                pageResult.getTotal(), pageResult.getRecords()));
    }

    /**
     * 分销统计
     */
    @Operation(summary = "分销统计")
    @GetMapping("/agent/statistics")
    public Result<?> getStatistics() {
        Map<String, Object> statistics = agentService.getStatistics();
        return Result.success(statistics);
    }

    /**
     * 分销账单分页列表
     */
    @Operation(summary = "分销账单分页列表")
    @GetMapping("/user_bill/{page}")
    public Result<?> getUserBillPage(@PathVariable Integer page,
                                     @RequestParam(defaultValue = "10") Integer limit,
                                     @RequestParam(required = false) Long user_id) {
        Page<UserBill> pageResult = userBillService.getPage(page, limit, user_id);
        return Result.success(PageResult.of(pageResult.getCurrent(), pageResult.getSize(),
                pageResult.getTotal(), pageResult.getRecords()));
    }
}
