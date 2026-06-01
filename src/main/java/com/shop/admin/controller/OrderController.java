package com.shop.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop.admin.common.PageResult;
import com.shop.admin.common.Result;
import com.shop.admin.entity.Order;
import com.shop.admin.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 订单管理控制器
 */
@Tag(name = "订单管理")
@RestController
@RequestMapping("/admin/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 分页列表
     */
    @Operation(summary = "分页列表")
    @GetMapping("/{page}")
    public Result<?> getPage(@PathVariable Integer page,
                             @RequestParam(defaultValue = "10") Integer limit,
                             @RequestParam(required = false) String order_no,
                             @RequestParam(required = false) Integer status) {
        Page<Order> pageResult = orderService.getPage(page, limit, order_no, status);
        return Result.success(PageResult.of(pageResult.getCurrent(), pageResult.getSize(),
                pageResult.getTotal(), pageResult.getRecords()));
    }

    /**
     * 批量删除
     */
    @Operation(summary = "批量删除")
    @PostMapping("/delete_all")
    public Result<?> deleteBatch(@RequestBody Map<String, List<Long>> params) {
        List<Long> ids = params.get("ids");
        orderService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 导出Excel
     */
    @Operation(summary = "导出Excel")
    @PostMapping("/excelexport")
    public void exportExcel(HttpServletResponse response,
                          @RequestParam(required = false) String order_no,
                          @RequestParam(required = false) Integer status) throws IOException {
        // 查询所有订单
        List<Order> orders = orderService.list();

        // 创建Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("订单列表");

        // 表头
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("订单号");
        header.createCell(1).setCellValue("收货人");
        header.createCell(2).setCellValue("联系电话");
        header.createCell(3).setCellValue("订单金额");
        header.createCell(4).setCellValue("订单状态");
        header.createCell(5).setCellValue("创建时间");

        // 数据
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(order.getOrderNo());
            row.createCell(1).setCellValue(order.getReceiver());
            row.createCell(2).setCellValue(order.getPhone());
            row.createCell(3).setCellValue(order.getPayPrice() != null ? order.getPayPrice().toString() : "0");
            row.createCell(4).setCellValue(getStatusText(order.getStatus()));
            row.createCell(5).setCellValue(order.getCreateTime() != null ? order.getCreateTime().toString() : "");
        }

        // 输出
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment;filename=orders.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    private String getStatusText(Integer status) {
        if (status == null) return "";
        switch (status) {
            case 0: return "待支付";
            case 1: return "待发货";
            case 2: return "待收货";
            case 3: return "已完成";
            case 4: return "已取消";
            case 5: return "已退款";
            default: return "";
        }
    }

    /**
     * 获取发货信息
     */
    @Operation(summary = "获取发货信息")
    @GetMapping("/{id}/get_ship_info")
    public Result<?> getShipInfo(@PathVariable Long id) {
        Order order = orderService.getById(id);
        return Result.success(order);
    }

    /**
     * 处理退款
     */
    @Operation(summary = "处理退款")
    @PostMapping("/{id}/handle_refund")
    public Result<?> refund(@PathVariable Long id, @RequestBody Map<String, String> params) {
        String reason = params.get("reason");
        orderService.refund(id, reason);
        return Result.success();
    }
}
