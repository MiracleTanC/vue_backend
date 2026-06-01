package com.shop.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop.admin.entity.Goods;
import com.shop.admin.entity.GoodsComment;
import com.shop.admin.entity.User;
import com.shop.admin.exception.BusinessException;
import com.shop.admin.mapper.GoodsCommentMapper;
import com.shop.admin.mapper.GoodsMapper;
import com.shop.admin.mapper.UserMapper;
import com.shop.admin.service.GoodsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 商品评价服务实现
 */
@Service
public class GoodsCommentServiceImpl extends ServiceImpl<GoodsCommentMapper, GoodsComment> implements GoodsCommentService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<GoodsComment> getPage(Integer page, Integer pageSize, Long goodsId, Integer status) {
        LambdaQueryWrapper<GoodsComment> wrapper = new LambdaQueryWrapper<>();
        if (goodsId != null) {
            wrapper.eq(GoodsComment::getGoodsId, goodsId);
        }
        if (status != null) {
            wrapper.eq(GoodsComment::getStatus, status);
        }
        wrapper.orderByDesc(GoodsComment::getCreateTime);
        Page<GoodsComment> pageResult = this.page(new Page<>(page, pageSize), wrapper);
        // 填充商品和用户信息
        pageResult.getRecords().forEach(comment -> {
            Goods goods = goodsMapper.selectById(comment.getGoodsId());
            comment.setGoods(goods);
            User user = userMapper.selectById(comment.getUserId());
            comment.setUser(user);
        });
        return pageResult;
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        GoodsComment comment = this.getById(id);
        if (comment == null) {
            throw new BusinessException("评价不存在");
        }
        comment.setStatus(status);
        this.updateById(comment);
    }

    @Override
    public void review(Long id, String reply) {
        GoodsComment comment = this.getById(id);
        if (comment == null) {
            throw new BusinessException("评价不存在");
        }
        comment.setStatus(1); // 已审核
        comment.setReply(reply);
        comment.setReplyTime(LocalDateTime.now());
        this.updateById(comment);
    }
}
