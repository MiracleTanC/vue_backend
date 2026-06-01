package com.shop.admin.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Token工具类
 */
@Component
public class TokenUtil {

    @Value("${token.secret}")
    private String secret;

    @Value("${token.expire}")
    private Long expire;

    /**
     * 生成Token
     */
    public String generateToken(Long userId) {
        String raw = userId + "-" + System.currentTimeMillis() + "-" + UUID.randomUUID().toString();
        return DigestUtil.md5Hex(raw + secret);
    }

    /**
     * 验证Token格式
     */
    public boolean isValidToken(String token) {
        return StrUtil.isNotBlank(token) && token.length() == 32;
    }
}
