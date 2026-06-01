-- ============================================
-- 测试数据初始化脚本
-- ============================================

USE shop_admin;

-- ============================================
-- 1. 角色数据
-- ============================================
INSERT INTO sys_role (id, name, status, remark) VALUES
(1, '超级管理员', 1, '拥有所有权限'),
(2, '商品管理员', 1, '管理商品相关'),
(3, '订单管理员', 1, '管理订单相关');

-- ============================================
-- 2. 权限规则数据（菜单）
-- ============================================
INSERT INTO sys_rule (id, pid, name, icon, frontpath, `condition`, method, status, order_num) VALUES
-- 一级菜单
(1, 0, '后台首页', 'HomeFilled', '/', NULL, NULL, 1, 1),
(2, 0, '商品管理', 'ShoppingCartFull', NULL, NULL, NULL, 1, 2),
(3, 0, '订单管理', 'List', NULL, NULL, NULL, 1, 3),
(4, 0, '用户管理', 'User', NULL, NULL, NULL, 1, 4),
(5, 0, '系统设置', 'Setting', NULL, NULL, NULL, 1, 5),
(6, 0, '分销管理', 'Share', NULL, NULL, NULL, 1, 6),
-- 二级菜单 - 商品管理
(21, 2, '商品列表', NULL, '/goods/list', NULL, NULL, 1, 1),
(22, 2, '分类列表', NULL, '/category/list', NULL, NULL, 1, 2),
(23, 2, '规格管理', NULL, '/skus/list', NULL, NULL, 1, 3),
(24, 2, '图库列表', NULL, '/image/list', NULL, NULL, 1, 4),
(25, 2, '评价列表', NULL, '/comment/list', NULL, NULL, 1, 5),
-- 二级菜单 - 订单管理
(31, 3, '订单列表', NULL, '/order/list', NULL, NULL, 1, 1),
(32, 3, '优惠券列表', NULL, '/coupon/list', NULL, NULL, 1, 2),
-- 二级菜单 - 用户管理
(41, 4, '用户列表', NULL, '/user/list', NULL, NULL, 1, 1),
(42, 4, '会员等级', NULL, '/level/list', NULL, NULL, 1, 2),
-- 二级菜单 - 系统设置
(51, 5, '基础配置', NULL, '/setting/base', NULL, NULL, 1, 1),
(52, 5, '支付设置', NULL, '/setting/buy', NULL, NULL, 1, 2),
(53, 5, '物流设置', NULL, '/setting/ship', NULL, NULL, 1, 3),
(54, 5, '管理员管理', NULL, '/manager/list', NULL, NULL, 1, 4),
(55, 5, '角色管理', NULL, '/role/list', NULL, NULL, 1, 5),
(56, 5, '菜单权限', NULL, '/access/list', NULL, NULL, 1, 6),
(57, 5, '公告列表', NULL, '/notice/list', NULL, NULL, 1, 7),
-- 二级菜单 - 分销管理
(61, 6, '分销员管理', NULL, '/distribution/index', NULL, NULL, 1, 1),
(62, 6, '分销设置', NULL, '/distribution/setting', NULL, NULL, 1, 2),
-- API权限规则
(101, 0, '获取统计数据1', NULL, NULL, 'getStatistics1,GET', 'GET', 1, 0),
(102, 0, '获取统计数据2', NULL, NULL, 'getStatistics2,GET', 'GET', 1, 0),
(103, 0, '获取统计数据3', NULL, NULL, 'getStatistics3,GET', 'GET', 1, 0);

-- ============================================
-- 3. 角色权限关联
-- ============================================
INSERT INTO sys_role_rule (role_id, rule_id) VALUES
-- 超级管理员拥有所有权限
(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6),
(1, 21), (1, 22), (1, 23), (1, 24), (1, 25),
(1, 31), (1, 32),
(1, 41), (1, 42),
(1, 51), (1, 52), (1, 53), (1, 54), (1, 55), (1, 56), (1, 57),
(1, 61), (1, 62),
(1, 101), (1, 102), (1, 103),
-- 商品管理员权限
(2, 1), (2, 2),
(2, 21), (2, 22), (2, 23), (2, 24), (2, 25),
(2, 101), (2, 102), (2, 103),
-- 订单管理员权限
(3, 1), (3, 3),
(3, 31), (3, 32),
(3, 101), (3, 102), (3, 103);

-- ============================================
-- 4. 管理员数据（密码都是123456）
-- ============================================
INSERT INTO sys_manager (id, username, password, avatar, role_id, status) VALUES
(1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', NULL, 1, 1),
(2, 'goods_admin', 'e10adc3949ba59abbe56e057f20f883e', NULL, 2, 1),
(3, 'order_admin', 'e10adc3949ba59abbe56e057f20f883e', NULL, 3, 1);

-- ============================================
-- 5. 会员等级数据
-- ============================================
INSERT INTO user_level (id, name, icon, bg_image, exp, discount, status) VALUES
(1, '普通会员', NULL, NULL, 0, 1.00, 1),
(2, '青铜会员', NULL, NULL, 100, 0.98, 1),
(3, '白银会员', NULL, NULL, 500, 0.95, 1),
(4, '黄金会员', NULL, NULL, 1000, 0.90, 1),
(5, '钻石会员', NULL, NULL, 5000, 0.85, 1);

-- ============================================
-- 6. 用户数据
-- ============================================
INSERT INTO user (id, username, password, avatar, nickname, phone, email, level_id, status) VALUES
(1, 'user001', 'e10adc3949ba59abbe56e057f20f883e', NULL, '张三', '13800138001', 'user001@test.com', 2, 1),
(2, 'user002', 'e10adc3949ba59abbe56e057f20f883e', NULL, '李四', '13800138002', 'user002@test.com', 3, 1),
(3, 'user003', 'e10adc3949ba59abbe56e057f20f883e', NULL, '王五', '13800138003', 'user003@test.com', 4, 1),
(4, 'user004', 'e10adc3949ba59abbe56e057f20f883e', NULL, '赵六', '13800138004', 'user004@test.com', 1, 1),
(5, 'user005', 'e10adc3949ba59abbe56e057f20f883e', NULL, '钱七', '13800138005', 'user005@test.com', 5, 1);

-- ============================================
-- 7. 商品分类数据
-- ============================================
INSERT INTO category (id, name, pid, icon, order_num, status) VALUES
-- 一级分类
(1, '电子产品', 0, 'Monitor', 1, 1),
(2, '服装鞋帽', 0, 'Shirt', 2, 1),
(3, '家居用品', 0, 'House', 3, 1),
(4, '食品饮料', 0, 'Coffee', 4, 1),
(5, '图书文具', 0, 'Notebook', 5, 1),
-- 二级分类 - 电子产品
(11, '手机通讯', 1, NULL, 1, 1),
(12, '电脑办公', 1, NULL, 2, 1),
(13, '数码配件', 1, NULL, 3, 1),
-- 二级分类 - 服装鞋帽
(21, '男装', 2, NULL, 1, 1),
(22, '女装', 2, NULL, 2, 1),
(23, '鞋靴', 2, NULL, 3, 1),
-- 二级分类 - 家居用品
(31, '厨房用品', 3, NULL, 1, 1),
(32, '卫浴用品', 3, NULL, 2, 1);

-- ============================================
-- 8. 规格模板数据
-- ============================================
INSERT INTO skus (id, name, values, status) VALUES
(1, '颜色', '["黑色","白色","蓝色","红色","金色"]', 1),
(2, '尺寸', '["S","M","L","XL","XXL"]', 1),
(3, '内存', '["64GB","128GB","256GB","512GB"]', 1),
(4, '版本', '["标准版","高配版","旗舰版"]', 1);

-- ============================================
-- 9. 商品数据
-- ============================================
INSERT INTO goods (id, name, category_id, cover, images, content, `desc`, unit, stock, sales, min_num, is_skus, original_price, price, min_price, max_price, status, order_num) VALUES
(1, 'iPhone 15 Pro Max 256GB', 11, '/uploads/goods/iphone.jpg', '[]', '<p>全新iPhone 15 Pro Max，A17 Pro芯片，钛金属设计</p>', '全新iPhone 15 Pro Max', '台', 100, 58, 1, 0, 10999.00, 9999.00, 9999.00, 9999.00, 1, 1),
(2, 'MacBook Pro 14英寸 M3', 12, '/uploads/goods/macbook.jpg', '[]', '<p>MacBook Pro 14英寸，M3芯片，18小时续航</p>', 'MacBook Pro 14英寸', '台', 50, 32, 1, 0, 16999.00, 14999.00, 14999.00, 14999.00, 1, 2),
(3, 'AirPods Pro 2', 13, '/uploads/goods/airpods.jpg', '[]', '<p>AirPods Pro 第二代，主动降噪，自适应通透模式</p>', 'AirPods Pro 2', '副', 200, 156, 1, 0, 1899.00, 1699.00, 1699.00, 1699.00, 1, 3),
(4, '男士纯棉T恤', 21, '/uploads/goods/tshirt.jpg', '[]', '<p>100%纯棉，舒适透气，多色可选</p>', '男士纯棉T恤', '件', 500, 289, 1, 1, 199.00, 99.00, 99.00, 199.00, 1, 4),
(5, '女士连衣裙', 22, '/uploads/goods/dress.jpg', '[]', '<p>优雅气质，多款花色可选</p>', '女士连衣裙', '件', 300, 178, 1, 1, 399.00, 299.00, 299.00, 399.00, 1, 5),
(6, '智能电饭煲', 31, '/uploads/goods/cooker.jpg', '[]', '<p>智能预约，多种烹饪模式</p>', '智能电饭煲', '台', 80, 45, 1, 0, 599.00, 499.00, 499.00, 499.00, 1, 6),
(7, '机械键盘', 13, '/uploads/goods/keyboard.jpg', '[]', '<p>RGB背光，Cherry轴体</p>', '机械键盘', '把', 150, 67, 1, 1, 599.00, 399.00, 399.00, 599.00, 1, 7),
(8, '无线鼠标', 13, '/uploads/goods/mouse.jpg', '[]', '<p>静音设计，长续航</p>', '无线鼠标', '个', 300, 123, 1, 0, 129.00, 79.00, 79.00, 79.00, 1, 8);

-- ============================================
-- 10. 订单数据
-- ============================================
INSERT INTO `order` (id, order_no, user_id, address, receiver, phone, total_price, pay_price, freight, coupon_id, discount_amount, pay_type, pay_time, status, ship_status, express_company, express_no, ship_time, remark) VALUES
(1, 'ORD202401010001', 1, '{"province":"北京市","city":"北京市","district":"朝阳区","address":"xxx街道xxx号"}', '张三', '13800138001', 9999.00, 9999.00, 0.00, NULL, 0.00, 1, '2024-01-01 10:00:00', 3, 1, '顺丰速运', 'SF1234567890', '2024-01-01 14:00:00', NULL),
(2, 'ORD202401010002', 2, '{"province":"上海市","city":"上海市","district":"浦东新区","address":"xxx路xxx号"}', '李四', '13800138002', 14999.00, 14999.00, 0.00, NULL, 0.00, 2, '2024-01-01 11:00:00', 2, 1, '顺丰速运', 'SF1234567891', '2024-01-02 09:00:00', NULL),
(3, 'ORD202401020001', 3, '{"province":"广州市","city":"广州市","district":"天河区","address":"xxx街xxx号"}', '王五', '13800138003', 1699.00, 1699.00, 0.00, NULL, 0.00, 1, '2024-01-02 09:30:00', 1, 0, NULL, NULL, NULL, NULL),
(4, 'ORD202401020002', 1, '{"province":"深圳市","city":"深圳市","district":"南山区","address":"xxx大道xxx号"}', '张三', '13800138001', 398.00, 398.00, 0.00, NULL, 0.00, 1, '2024-01-02 15:00:00', 1, 0, NULL, NULL, NULL, NULL),
(5, 'ORD202401030001', 4, '{"province":"杭州市","city":"杭州市","district":"西湖区","address":"xxx路xxx号"}', '赵六', '13800138004', 99.00, 99.00, 10.00, NULL, 0.00, 1, '2024-01-03 10:00:00', 0, 0, NULL, NULL, NULL, NULL);

-- ============================================
-- 11. 订单商品明细
-- ============================================
INSERT INTO order_item (id, order_id, goods_id, goods_name, goods_cover, skus, price, quantity, total) VALUES
(1, 1, 1, 'iPhone 15 Pro Max 256GB', '/uploads/goods/iphone.jpg', NULL, 9999.00, 1, 9999.00),
(2, 2, 2, 'MacBook Pro 14英寸 M3', '/uploads/goods/macbook.jpg', NULL, 14999.00, 1, 14999.00),
(3, 3, 3, 'AirPods Pro 2', '/uploads/goods/airpods.jpg', NULL, 1699.00, 1, 1699.00),
(4, 4, 7, '机械键盘', '/uploads/goods/keyboard.jpg', '{"颜色":"黑色"}', 399.00, 1, 399.00),
(5, 5, 4, '男士纯棉T恤', '/uploads/goods/tshirt.jpg', '{"颜色":"白色","尺寸":"L"}', 99.00, 1, 99.00);

-- ============================================
-- 12. 优惠券数据
-- ============================================
INSERT INTO coupon (id, name, type, amount, discount, min_amount, total_count, used_count, start_time, end_time, status) VALUES
(1, '新人专享券', 1, 50.00, NULL, 100.00, 1000, 156, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 1),
(2, '满200减30', 1, 30.00, NULL, 200.00, 500, 89, '2024-01-01 00:00:00', '2024-06-30 23:59:59', 1),
(3, '满500减100', 1, 100.00, NULL, 500.00, 200, 45, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 1),
(4, '9折优惠券', 2, NULL, 0.90, 50.00, 300, 67, '2024-01-01 00:00:00', '2024-12-31 23:59:59', 1);

-- ============================================
-- 13. 图库分类数据
-- ============================================
INSERT INTO image_class (id, name, order_num) VALUES
(1, '商品图片', 1),
(2, '轮播图片', 2),
(3, '分类图片', 3),
(4, '其他图片', 4);

-- ============================================
-- 14. 公告数据
-- ============================================
INSERT INTO notice (id, title, content, status) VALUES
(1, '系统升级公告', '系统将于2024年1月15日凌晨2:00-6:00进行升级维护，届时系统将暂停服务，请提前做好准备。', 1),
(2, '春节放假通知', '春节假期期间（2024年2月9日-2月17日）订单将于节后统一发货，祝您新春快乐！', 1),
(3, '新用户注册送优惠券', '新用户注册即送50元优惠券，满100元可用，快来注册吧！', 1);

-- ============================================
-- 15. 商品评价数据
-- ============================================
INSERT INTO goods_comment (id, goods_id, user_id, order_id, content, images, rating, status, reply, reply_time) VALUES
(1, 1, 1, 1, '非常好用，物流也很快，包装完好无损！', '[]', 5, 1, '感谢您的支持与认可！', '2024-01-05 10:00:00'),
(2, 2, 2, 2, '电脑性能很强，屏幕显示效果好，值得购买', '[]', 5, 1, NULL, NULL),
(3, 3, 3, 3, '音质清晰，降噪效果明显', '[]', 4, 1, NULL, NULL);

-- ============================================
-- 16. 分销员数据
-- ============================================
INSERT INTO agent (id, user_id, parent_id, level, status, total_commission, withdraw_commission) VALUES
(1, 2, NULL, 1, 1, 500.00, 200.00),
(2, 3, 2, 2, 1, 150.00, 0.00),
(3, 5, NULL, 1, 1, 300.00, 100.00);

-- ============================================
-- 17. 用户账单数据
-- ============================================
INSERT INTO user_bill (id, user_id, order_no, type, amount, balance, remark) VALUES
(1, 2, 'ORD202401010001', 1, 100.00, 300.00, '一级分销佣金'),
(2, 2, 'ORD202401010002', 1, 150.00, 450.00, '一级分销佣金'),
(3, 3, 'ORD202401010001', 1, 50.00, 100.00, '二级分销佣金');

-- ============================================
-- 18. 分销设置数据
-- ============================================
INSERT INTO distribution_setting (id, enabled, level, first_rate, second_rate, settle_type, need_audit) VALUES
(1, 1, 2, 0.10, 0.05, 1, 0);

-- ============================================
-- 19. 系统配置数据
-- ============================================
INSERT INTO sys_config (id, site_name, logo, description, keywords, phone, email, address, icp, default_freight, free_freight_amount) VALUES
(1, '帝莎编程商城', '/uploads/logo.png', '专业的电商平台，提供优质商品和服务', '电商,商城,在线购物', '400-888-8888', 'service@shop.com', '北京市朝阳区xxx街道', '京ICP备xxxxxxxx号', 10.00, 99.00);
