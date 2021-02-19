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

-- ----------------------------
-- Table structure for `changing_bg_user_permission`
-- ----------------------------
DROP TABLE IF EXISTS `changing_bg_user_permission`;
CREATE TABLE `changing_bg_user_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `permission_id` bigint(20) NOT NULL COMMENT '权限id',
  `record_status` tinyint(2) NOT NULL COMMENT '数据记录状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime NOT NULL COMMENT '修改时间'
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `changing_bg_cookbook` 菜品表
-- ----------------------------
DROP TABLE IF EXISTS `changing_bg_cookbook`;
CREATE TABLE `changing_bg_cookbook` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dish_name` varchar(64) NOT NULL COMMENT '菜名',
  `dish_tag` varchar(16) DEFAULT NULL COMMENT '标签',
  `dish_weight` double(10,2) DEFAULT NULL COMMENT '权重',
  `pungency_degree` double(10,2) DEFAULT NULL COMMENT '辣度',
  `dish_select_status` tinyint(2) NOT NULL COMMENT '是否已被选择(0:否，1:是)',
  `dish_selected_serial` int(10) DEFAULT NULL COMMENT '被选择的批次',
  `plan_date` datetime DEFAULT NULL COMMENT '执行日期',
  `record_status` tinyint(2) NOT NULL COMMENT '数据状态',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `changing_bg_dict` 字典表
-- ----------------------------
DROP TABLE IF EXISTS `changing_bg_dict`;
CREATE TABLE `changing_bg_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_group` varchar(32) NOT NULL COMMENT '字典分组',
  `dict_group_name` varchar(32) NOT NULL COMMENT '字典分组名',
  `dict_type` varchar(32) NOT NULL COMMENT '字典值',
  `dict_type_name` varchar(128) NOT NULL COMMENT '字典中文名',
  `record_status` tinyint(2) NOT NULL COMMENT '数据状态',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `authority` 角色表
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL,
  `authority` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `credentials` 授权表
-- ----------------------------
DROP TABLE IF EXISTS `credentials`;
CREATE TABLE `credentials` (
  `id` int(11) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `oauth_access_token` 授权token存储表
-- ----------------------------
DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `token_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'token id',
  `token` longblob COMMENT '会话token',
  `authentication_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `client_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '客户端id',
  `authentication` longblob COMMENT '会话',
  `refresh_token` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '再刷新token',
  `record_status` tinyint(2) NOT NULL COMMENT '数据记录',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `oauth_client_details` 授权客户端信息表
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `client_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '客户端id',
  `resource_ids` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资源id',
  `client_secret` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '客户端秘钥',
  `scope` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '权限点',
  `authorized_grant_types` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '授权方式(多个使用英文逗号分隔)',
  `web_server_redirect_uri` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '重定向地址',
  `authorities` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色权限',
  `access_token_validity` int(11) DEFAULT NULL COMMENT '会话token',
  `refresh_token_validity` int(11) DEFAULT NULL COMMENT '再刷新token',
  `additional_information` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '扩展信息',
  `autoapprove` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `record_status` tinyint(2) NOT NULL COMMENT '数据状态(0:正常,1:删除)',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `oauth_client_token` 客户端token存储表
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_token`;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `token` longblob,
  `authentication_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `client_id` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `oauth_code` 授权code表
-- ----------------------------
DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '授权码',
  `authentication` varbinary(255) DEFAULT NULL COMMENT '权限',
  `record_status` tinyint(2) NOT NULL COMMENT '数据状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `oauth_refresh_token` 再刷新token存储表
-- ----------------------------
DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `token_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'token id',
  `token` longblob COMMENT '会话token',
  `authentication` longblob COMMENT '会话',
  `record_status` tinyint(2) NOT NULL COMMENT '数据状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
