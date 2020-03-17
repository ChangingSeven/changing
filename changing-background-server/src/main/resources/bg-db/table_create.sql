-- ----------------------------
--  Table structure for `changing_bg_user`
-- ----------------------------
DROP TABLE IF EXISTS `changing_bg_user`;
CREATE TABLE `changing_bg_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `user_status` int(2) DEFAULT NULL COMMENT '用户状态',
  `last_login_ip` varchar(16) DEFAULT NULL COMMENT '最后登录IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `record_status` int(2) DEFAULT NULL COMMENT '数据状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NULL DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;