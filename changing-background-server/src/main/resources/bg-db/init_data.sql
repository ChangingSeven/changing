INSERT INTO changing_bg_user(user_name, nick_name, password, user_status, last_login_ip, last_login_time, record_status,create_time, modify_time)
VALUES('admin','超管','admin',1,'',now(),0,now(),now());

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', 'ROLE_OAUTH_ADMIN');
INSERT INTO `authority` VALUES ('2', 'ROLE_ADMIN_PRODUCT');
INSERT INTO `authority` VALUES ('3', 'ROLE_RESOURCE_ADMIN');

-- ----------------------------
-- Records of credentials
-- 密码明文：123456
-- ----------------------------
INSERT INTO `credentials` VALUES ('1', '1', 'oauth_admin', '$2a$10$EJea3wH8qZNORpPvPRxEbuDjiIrtoOw376vFrB4evlYHlSvJJLM2i', '0');
INSERT INTO `credentials` VALUES ('2', '1', 'resource_admin', '$2a$10$EJea3wH8qZNORpPvPRxEbuDjiIrtoOw376vFrB4evlYHlSvJJLM2i', '0');
INSERT INTO `credentials` VALUES ('3', '1', 'user', '$2a$10$EJea3wH8qZNORpPvPRxEbuDjiIrtoOw376vFrB4evlYHlSvJJLM2i', '0');

-- ----------------------------
-- Records of oauth_client_details
-- 密码明文：123456
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('read-write-client', 'product_api', '$2a$10$EJea3wH8qZNORpPvPRxEbuDjiIrtoOw376vFrB4evlYHlSvJJLM2i', 'read,write', 'client_credentials', null, 'ROLE_PRODUCT_ADMIN', '10800', '2592000', null, null);