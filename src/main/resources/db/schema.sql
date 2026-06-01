-- ============================================
-- 电商后台管理系统数据库初始化脚本
-- ============================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS shop_admin DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE shop_admin;

-- ============================================
-- 1. 管理员表
-- ============================================
DROP TABLE IF EXISTS sys_manager;
CREATE TABLE sys_manager (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    username VARCHAR(50) NOT NULL COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    avatar VARCHAR(255) COMMENT '头像',
    role_id BIGINT COMMENT '角色ID',
    status TINYINT DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
    token VARCHAR(100) COMMENT '登录Token',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志',
    UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ============================================
-- 2. 角色表
-- ============================================
DROP TABLE IF EXISTS sys_role;
CREATE TABLE sys_role (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(50) NOT NULL COMMENT '角色名称',
    status TINYINT DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
    remark VARCHAR(255) COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ============================================
-- 3. 权限规则表（菜单）
-- ============================================
DROP TABLE IF EXISTS sys_rule;
CREATE TABLE sys_rule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    pid BIGINT DEFAULT 0 COMMENT '上级ID',
    name VARCHAR(50) NOT NULL COMMENT '规则名称',
    icon VARCHAR(50) COMMENT '菜单图标',
    frontpath VARCHAR(100) COMMENT '前端路径',
    `condition` VARCHAR(100) COMMENT '条件字段',
    method VARCHAR(10) COMMENT '请求方式',
    status TINYINT DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
    order_num INT DEFAULT 0 COMMENT '排序',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限规则表';

-- ============================================
-- 4. 角色权限关联表
-- ============================================
DROP TABLE IF EXISTS sys_role_rule;
CREATE TABLE sys_role_rule (
    role_id BIGINT NOT NULL COMMENT '角色ID',
    rule_id BIGINT NOT NULL COMMENT '规则ID',
    PRIMARY KEY (role_id, rule_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关联表';

-- ============================================
-- 5. 用户表
-- ============================================
DROP TABLE IF EXISTS user;
CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    username VARCHAR(50) NOT NULL COMMENT '用户名',
    password VARCHAR(100) NOT NULL COMMENT '密码',
    avatar VARCHAR(255) COMMENT '头像',
    nickname VARCHAR(50) COMMENT '昵称',
    phone VARCHAR(20) COMMENT '手机号',
    email VARCHAR(50) COMMENT '邮箱',
    level_id BIGINT COMMENT '会员等级ID',
    status TINYINT DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志',
    UNIQUE KEY uk_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ============================================
-- 6. 会员等级表
-- ============================================
DROP TABLE IF EXISTS user_level;
CREATE TABLE user_level (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(50) NOT NULL COMMENT '等级名称',
    icon VARCHAR(255) COMMENT '等级图标',
    bg_image VARCHAR(255) COMMENT '等级背景图',
    exp INT DEFAULT 0 COMMENT '所需经验值',
    discount DECIMAL(5,2) DEFAULT 1.00 COMMENT '折扣比例',
    status TINYINT DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='会员等级表';

-- ============================================
-- 7. 商品分类表
-- ============================================
DROP TABLE IF EXISTS category;
CREATE TABLE category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(50) NOT NULL COMMENT '分类名称',
    pid BIGINT DEFAULT 0 COMMENT '上级ID',
    icon VARCHAR(255) COMMENT '分类图标',
    order_num INT DEFAULT 0 COMMENT '排序',
    status TINYINT DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品分类表';

-- ============================================
-- 8. 商品表
-- ============================================
DROP TABLE IF EXISTS goods;
CREATE TABLE goods (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(100) NOT NULL COMMENT '商品名称',
    category_id BIGINT COMMENT '分类ID',
    cover VARCHAR(255) COMMENT '商品封面',
    images TEXT COMMENT '商品图片JSON',
    content TEXT COMMENT '商品详情',
    `desc` VARCHAR(500) COMMENT '商品简介',
    unit VARCHAR(20) DEFAULT '件' COMMENT '单位',
    stock INT DEFAULT 0 COMMENT '库存',
    sales INT DEFAULT 0 COMMENT '销量',
    min_num INT DEFAULT 1 COMMENT '最小购买数量',
    is_skus TINYINT DEFAULT 0 COMMENT '是否开启规格',
    original_price DECIMAL(10,2) COMMENT '原价',
    price DECIMAL(10,2) COMMENT '现价',
    min_price DECIMAL(10,2) COMMENT '最低价',
    max_price DECIMAL(10,2) COMMENT '最高价',
    status TINYINT DEFAULT 0 COMMENT '状态 0-仓库 1-上架',
    order_num INT DEFAULT 0 COMMENT '排序',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品表';

-- ============================================
-- 9. 商品轮播图表
-- ============================================
DROP TABLE IF EXISTS goods_banner;
CREATE TABLE goods_banner (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    goods_id BIGINT NOT NULL COMMENT '商品ID',
    url VARCHAR(255) NOT NULL COMMENT '图片URL',
    order_num INT DEFAULT 0 COMMENT '排序',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品轮播图表';

-- ============================================
-- 10. 商品SKU规格卡片表
-- ============================================
DROP TABLE IF EXISTS goods_skus_card;
CREATE TABLE goods_skus_card (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    goods_id BIGINT NOT NULL COMMENT '商品ID',
    name VARCHAR(50) NOT NULL COMMENT '规格名称',
    order_num INT DEFAULT 0 COMMENT '排序',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品SKU规格卡片表';

-- ============================================
-- 11. 商品SKU规格值表
-- ============================================
DROP TABLE IF EXISTS goods_skus_card_value;
CREATE TABLE goods_skus_card_value (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    skus_card_id BIGINT NOT NULL COMMENT 'SKU卡片ID',
    name VARCHAR(50) NOT NULL COMMENT '规格值名称',
    image VARCHAR(255) COMMENT '规格值图片',
    order_num INT DEFAULT 0 COMMENT '排序',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品SKU规格值表';

-- ============================================
-- 12. 订单表
-- ============================================
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    order_no VARCHAR(50) NOT NULL COMMENT '订单号',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    address VARCHAR(500) COMMENT '收货地址JSON',
    receiver VARCHAR(50) COMMENT '收货人',
    phone VARCHAR(20) COMMENT '联系电话',
    total_price DECIMAL(10,2) COMMENT '商品总价',
    pay_price DECIMAL(10,2) COMMENT '实付金额',
    freight DECIMAL(10,2) DEFAULT 0 COMMENT '运费',
    coupon_id BIGINT COMMENT '优惠券ID',
    discount_amount DECIMAL(10,2) DEFAULT 0 COMMENT '优惠金额',
    pay_type TINYINT COMMENT '支付方式 1-微信 2-支付宝',
    pay_time DATETIME COMMENT '支付时间',
    status TINYINT DEFAULT 0 COMMENT '订单状态 0-待支付 1-待发货 2-待收货 3-已完成 4-已取消 5-已退款',
    ship_status TINYINT DEFAULT 0 COMMENT '发货状态',
    express_company VARCHAR(50) COMMENT '物流公司',
    express_no VARCHAR(50) COMMENT '物流单号',
    ship_time DATETIME COMMENT '发货时间',
    remark VARCHAR(255) COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志',
    UNIQUE KEY uk_order_no (order_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ============================================
-- 13. 订单商品明细表
-- ============================================
DROP TABLE IF EXISTS order_item;
CREATE TABLE order_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    goods_id BIGINT NOT NULL COMMENT '商品ID',
    goods_name VARCHAR(100) COMMENT '商品名称',
    goods_cover VARCHAR(255) COMMENT '商品封面',
    skus VARCHAR(500) COMMENT '规格值JSON',
    price DECIMAL(10,2) COMMENT '单价',
    quantity INT COMMENT '数量',
    total DECIMAL(10,2) COMMENT '小计',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单商品明细表';

-- ============================================
-- 14. 优惠券表
-- ============================================
DROP TABLE IF EXISTS coupon;
CREATE TABLE coupon (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(50) NOT NULL COMMENT '优惠券名称',
    type TINYINT DEFAULT 1 COMMENT '类型 1-满减券 2-折扣券',
    amount DECIMAL(10,2) COMMENT '优惠金额',
    discount DECIMAL(5,2) COMMENT '折扣比例',
    min_amount DECIMAL(10,2) DEFAULT 0 COMMENT '最低消费金额',
    total_count INT DEFAULT 100 COMMENT '发放数量',
    used_count INT DEFAULT 0 COMMENT '已使用数量',
    start_time DATETIME COMMENT '开始时间',
    end_time DATETIME COMMENT '结束时间',
    status TINYINT DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='优惠券表';

-- ============================================
-- 15. 图库分类表
-- ============================================
DROP TABLE IF EXISTS image_class;
CREATE TABLE image_class (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(50) NOT NULL COMMENT '分类名称',
    order_num INT DEFAULT 0 COMMENT '排序',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图库分类表';

-- ============================================
-- 16. 图片表
-- ============================================
DROP TABLE IF EXISTS image;
CREATE TABLE image (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    image_class_id BIGINT NOT NULL COMMENT '图片分类ID',
    name VARCHAR(100) COMMENT '图片名称',
    url VARCHAR(255) NOT NULL COMMENT '图片URL',
    order_num INT DEFAULT 0 COMMENT '排序',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='图片表';

-- ============================================
-- 17. 公告表
-- ============================================
DROP TABLE IF EXISTS notice;
CREATE TABLE notice (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    title VARCHAR(100) NOT NULL COMMENT '公告标题',
    content TEXT COMMENT '公告内容',
    status TINYINT DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='公告表';

-- ============================================
-- 18. 规格模板表
-- ============================================
DROP TABLE IF EXISTS skus;
CREATE TABLE skus (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    name VARCHAR(50) NOT NULL COMMENT '规格名称',
    values TEXT COMMENT '规格值JSON',
    status TINYINT DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='规格模板表';

-- ============================================
-- 19. 商品评价表
-- ============================================
DROP TABLE IF EXISTS goods_comment;
CREATE TABLE goods_comment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    goods_id BIGINT NOT NULL COMMENT '商品ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    content VARCHAR(500) COMMENT '评价内容',
    images TEXT COMMENT '评价图片JSON',
    rating TINYINT DEFAULT 5 COMMENT '评分',
    status TINYINT DEFAULT 0 COMMENT '状态 0-待审核 1-已审核 2-已拒绝',
    reply VARCHAR(500) COMMENT '回复内容',
    reply_time DATETIME COMMENT '回复时间',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品评价表';

-- ============================================
-- 20. 分销员表
-- ============================================
DROP TABLE IF EXISTS agent;
CREATE TABLE agent (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    parent_id BIGINT COMMENT '推荐人ID',
    level TINYINT DEFAULT 1 COMMENT '分销层级',
    status TINYINT DEFAULT 1 COMMENT '状态 0-禁用 1-启用',
    total_commission DECIMAL(10,2) DEFAULT 0 COMMENT '累计佣金',
    withdraw_commission DECIMAL(10,2) DEFAULT 0 COMMENT '已提现佣金',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分销员表';

-- ============================================
-- 21. 用户账单表
-- ============================================
DROP TABLE IF EXISTS user_bill;
CREATE TABLE user_bill (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    order_no VARCHAR(50) COMMENT '关联订单号',
    type TINYINT COMMENT '类型 1-分销佣金',
    amount DECIMAL(10,2) COMMENT '金额',
    balance DECIMAL(10,2) COMMENT '余额',
    remark VARCHAR(255) COMMENT '备注',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted TINYINT DEFAULT 0 COMMENT '删除标志'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户账单表';

-- ============================================
-- 22. 分销设置表
-- ============================================
DROP TABLE IF EXISTS distribution_setting;
CREATE TABLE distribution_setting (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    enabled TINYINT DEFAULT 1 COMMENT '是否开启分销',
    level TINYINT DEFAULT 2 COMMENT '分销层级',
    first_rate DECIMAL(5,2) DEFAULT 0.10 COMMENT '一级佣金比例',
    second_rate DECIMAL(5,2) DEFAULT 0.05 COMMENT '二级佣金比例',
    settle_type TINYINT DEFAULT 1 COMMENT '结算方式',
    need_audit TINYINT DEFAULT 0 COMMENT '是否需要审核'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分销设置表';

-- ============================================
-- 23. 系统配置表
-- ============================================
DROP TABLE IF EXISTS sys_config;
CREATE TABLE sys_config (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键',
    site_name VARCHAR(100) DEFAULT '电商后台' COMMENT '网站名称',
    logo VARCHAR(255) COMMENT '网站Logo',
    description VARCHAR(255) COMMENT '网站描述',
    keywords VARCHAR(255) COMMENT '网站关键词',
    phone VARCHAR(20) COMMENT '联系电话',
    email VARCHAR(50) COMMENT '联系邮箱',
    address VARCHAR(255) COMMENT '联系地址',
    icp VARCHAR(50) COMMENT '备案号',
    wepay_mch_id VARCHAR(50) COMMENT '微信支付商户号',
    wepay_secret VARCHAR(100) COMMENT '微信支付密钥',
    alipay_app_id VARCHAR(50) COMMENT '支付宝应用ID',
    alipay_private_key TEXT COMMENT '支付宝私钥',
    express_api VARCHAR(255) COMMENT '物流查询接口',
    express_key VARCHAR(100) COMMENT '物流查询密钥',
    default_freight DECIMAL(10,2) DEFAULT 10.00 COMMENT '默认运费',
    free_freight_amount DECIMAL(10,2) DEFAULT 99.00 COMMENT '免运费金额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统配置表';
