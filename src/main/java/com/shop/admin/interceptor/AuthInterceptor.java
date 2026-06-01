package com.shop.admin.interceptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.shop.admin.common.Result;
import com.shop.admin.entity.Manager;
import com.shop.admin.service.ManagerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Token认证拦截器
 */
@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private ManagerService managerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 放行OPTIONS请求
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // 获取Token
        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            return unauthorized(response, "请先登录");
        }

        // 从数据库验证Token
        Manager manager = managerService.getByToken(token);
        if (manager == null) {
            return unauthorized(response, "非法token，请先登录！");
        }

        if (manager.getStatus() != 1) {
            return unauthorized(response, "账号已被禁用");
        }

        // 将用户信息存入请求属性
        request.setAttribute("manager", manager);
        return true;
    }

    private boolean unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        Result<?> result = Result.error(401, message);
        response.getWriter().write(JSON.toJSONString(result));
        return false;
    }
}
