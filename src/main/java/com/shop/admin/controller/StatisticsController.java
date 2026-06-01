package com.shop.admin.controller;

import com.shop.admin.common.Result;
import com.shop.admin.entity.Goods;
import com.shop.admin.entity.Order;
import com.shop.admin.service.GoodsService;
import com.shop.admin.service.OrderService;
import com.shop.admin.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计数据控制器
 */
@Tag(name = "统计数据")
@RestController
@RequestMapping("/admin")
public class StatisticsController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    /**
     * 统计数据1（面板数据）
     */
    @Operation(summary = "统计数据1")
    @GetMapping("/statistics1")
    public Result<?> getStatistics1() {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> panels = new ArrayList<>();

        // 用户总数
        long userCount = userService.count();
        Map<String, Object> panel1 = new HashMap<>();
        panel1.put("title", "用户总数");
        panel1.put("value", userCount);
        panel1.put("unit", "人");
        panel1.put("unitColor", "primary");
        panel1.put("subTitle", "较上月");
        panel1.put("subValue", "+10%");
        panels.add(panel1);

        // 商品总数
        long goodsCount = goodsService.count();
        Map<String, Object> panel2 = new HashMap<>();
        panel2.put("title", "商品总数");
        panel2.put("value", goodsCount);
        panel2.put("unit", "个");
        panel2.put("unitColor", "success");
        panel2.put("subTitle", "较上月");
        panel2.put("subValue", "+5%");
        panels.add(panel2);

        // 订单总数
        long orderCount = orderService.count();
        Map<String, Object> panel3 = new HashMap<>();
        panel3.put("title", "订单总数");
        panel3.put("value", orderCount);
        panel3.put("unit", "单");
        panel3.put("unitColor", "warning");
        panel3.put("subTitle", "较上月");
        panel3.put("subValue", "+15%");
        panels.add(panel3);

        // 销售额
        BigDecimal totalSales = orderService.list().stream()
                .filter(o -> o.getPayPrice() != null)
                .map(Order::getPayPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Map<String, Object> panel4 = new HashMap<>();
        panel4.put("title", "销售额");
        panel4.put("value", totalSales.intValue());
        panel4.put("unit", "元");
        panel4.put("unitColor", "danger");
        panel4.put("subTitle", "较上月");
        panel4.put("subValue", "+20%");
        panels.add(panel4);

        result.put("panels", panels);
        return Result.success(result);
    }

    /**
     * 统计数据2（提示数据）
     */
    @Operation(summary = "统计数据2")
    @GetMapping("/statistics2")
    public Result<?> getStatistics2() {
        Map<String, Object> result = new HashMap<>();

        // 商品提示
        List<Map<String, Object>> goods = new ArrayList<>();
        Map<String, Object> goods1 = new HashMap<>();
        goods1.put("title", "待上架");
        goods1.put("value", goodsService.lambdaQuery().eq(Goods::getStatus, 0).count());
        goods.add(goods1);
        Map<String, Object> goods2 = new HashMap<>();
        goods2.put("title", "已上架");
        goods2.put("value", goodsService.lambdaQuery().eq(Goods::getStatus, 1).count());
        goods.add(goods2);
        result.put("goods", goods);

        // 订单提示
        List<Map<String, Object>> order = new ArrayList<>();
        Map<String, Object> order1 = new HashMap<>();
        order1.put("title", "待支付");
        order1.put("value", orderService.lambdaQuery().eq(Order::getStatus, 0).count());
        order.add(order1);
        Map<String, Object> order2 = new HashMap<>();
        order2.put("title", "待发货");
        order2.put("value", orderService.lambdaQuery().eq(Order::getStatus, 1).count());
        order.add(order2);
        Map<String, Object> order3 = new HashMap<>();
        order3.put("title", "待收货");
        order3.put("value", orderService.lambdaQuery().eq(Order::getStatus, 2).count());
        order.add(order3);
        result.put("order", order);

        return Result.success(result);
    }

    /**
     * 统计数据3（图表数据）
     */
    @Operation(summary = "统计数据3")
    @GetMapping("/statistics3")
    public Result<?> getStatistics3(@RequestParam(defaultValue = "month") String type) {
        Map<String, Object> result = new HashMap<>();

        // 模拟图表数据
        List<String> xLabels = new ArrayList<>();
        List<Integer> data = new ArrayList<>();

        if ("month".equals(type)) {
            for (int i = 1; i <= 12; i++) {
                xLabels.add(i + "月");
                data.add((int) (Math.random() * 10000));
            }
        } else if ("week".equals(type)) {
            String[] weeks = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
            for (String week : weeks) {
                xLabels.add(week);
                data.add((int) (Math.random() * 1000));
            }
        } else {
            for (int i = 0; i < 24; i++) {
                xLabels.add(i + ":00");
                data.add((int) (Math.random() * 100));
            }
        }

        result.put("x", xLabels);
        result.put("data", data);
        return Result.success(result);
    }
}
