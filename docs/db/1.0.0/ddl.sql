
SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_alarm`
-- ----------------------------
DROP TABLE IF EXISTS `t_alarm`;
CREATE TABLE `t_alarm` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
                           `cluster_code` varchar(50) DEFAULT NULL COMMENT '集群编码',
                           `instance_host` varchar(255) NOT NULL COMMENT '网关实例的ip:port',
                           `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           `alarm_info` varchar(3000) DEFAULT NULL,
                           `status` tinyint DEFAULT NULL COMMENT '告警的处理状态：0 未处理 1 已处理 2 忽略',
                           `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `t_alarm`
-- ----------------------------
BEGIN;
INSERT INTO `t_alarm` VALUES ('1', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('2', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:53:33', '2020-06-24 17:05:01', null, '0', '0'), ('3', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('4', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('5', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('6', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('7', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('8', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('9', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('10', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('11', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('12', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('13', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('14', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('15', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('16', 'Janus_Test', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 17:04:55', null, '0', '0'), ('17', 'JANUS_ORDER', '127.0.0.1:8081', '2020-06-19 15:03:42', '2020-06-24 20:01:34', null, '0', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_api_draft`
-- ----------------------------
DROP TABLE IF EXISTS `t_api_draft`;
CREATE TABLE `t_api_draft` (
                               `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                               `api_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT 't_api_release.id',
                               `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'API名称，同一集群内唯一',
                               `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'API描述',
                               `cluster_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所属集群编码',
                               `protocol` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '协议，例：HTTP',
                               `path` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请求路径',
                               `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'http请求方法',
                               `param_trans_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '入参传递模式',
                               `param_trans_config` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '入参传递映射配置',
                               `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人用户id',
                               `update_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '修改人用户id',
                               `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除0否 1 删除',
                               `version` int NOT NULL DEFAULT '1' COMMENT '数据版本号',
                               `operate_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '操作类型',
                               `status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态',
                               `apply_id` bigint NOT NULL DEFAULT '0' COMMENT '审批申请ID',
                               PRIMARY KEY (`id`),
                               KEY `idx_clusterCode` (`cluster_code`(20)),
                               KEY `idx_name` (`name`(20)),
                               KEY `idx_path` (`path`(20)),
                               KEY `idx_create_user` (`create_user`(20)),
                               KEY `idx_update_user` (`update_user`(20)),
                               KEY `idx_status` (`status`(20)),
                               KEY `idx_update_time` (`create_time`),
                               KEY `idx_create_time` (`update_time`),
                               KEY `idx_is_deleted` (`is_deleted`),
                               KEY `idx_apply_id` (`apply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='api inbound 配置变更表';

-- ----------------------------
--  Records of `t_api_draft`
-- ----------------------------
BEGIN;
INSERT INTO `t_api_draft` VALUES ('1', '1', 'Janus_Admin查询集群分页', 'Janus_Admin查询集群分页', 'Janus_Test', 'HTTP', '/admin/cluster/page', 'GET', 'TRANSPARENT', '{\n	\"params\":[]\n}', 'jin.xu', 'jin.xu', '2020-06-22 20:17:08', '2020-06-24 20:06:27', '0', '1', 'NEW', 'RELEASED', '1'), ('2', '2', 'URL转URL到指定实例', 'URL精准匹配到URL，但是不走LB，通过指定一个IP和端口转发', 'Janus_Test', 'HTTP', '/admin2/menu/manage/allMenu2', 'GET', 'TRANSPARENT', '{\n	\"params\":[]\n}', 'jin.xu', 'jin.xu', '2020-07-04 10:35:12', '2020-07-04 10:36:34', '0', '1', 'NEW', 'RELEASED', '2'), ('3', '3', '测试通过Hosts进行LB', '配置多个后端地址的URL，进行LB，非通过注册中心进行LB', 'Janus_Test', 'HTTP', '/admin7/menu/manage/allMenu2', 'GET', 'TRANSPARENT', '{\n	\"params\":[]\n}', 'jin.xu', 'jin.xu', '2020-07-14 13:45:52', '2020-07-14 16:11:12', '0', '1', 'NEW', 'RELEASED', '3');
COMMIT;

-- ----------------------------
--  Table structure for `t_api_filter`
-- ----------------------------
DROP TABLE IF EXISTS `t_api_filter`;
CREATE TABLE `t_api_filter` (
                                `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
                                `api_id` varchar(50) NOT NULL COMMENT 'api Id',
                                `filter_id` varchar(50) NOT NULL COMMENT '该表逻辑主键',
                                `cluster_code` varchar(50) DEFAULT NULL COMMENT '集群编码',
                                `filter_name` varchar(255) NOT NULL COMMENT 'Filter中文名',
                                `filter_code` varchar(255) NOT NULL COMMENT 'Filter类名',
                                `filter_param` text COMMENT 'filter参数,json类型',
                                `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
                                `modifier` varchar(64) DEFAULT NULL COMMENT '修改者',
                                `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='API和Filter关联表';

-- ----------------------------
--  Table structure for `t_api_release`
-- ----------------------------
DROP TABLE IF EXISTS `t_api_release`;
CREATE TABLE `t_api_release` (
                                 `id` bigint unsigned NOT NULL COMMENT 'API唯一编号',
                                 `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'API名称，同一集群内唯一',
                                 `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'API描述',
                                 `cluster_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所属集群编码',
                                 `protocol` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '协议，例：HTTP',
                                 `path` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请求路径',
                                 `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'http请求方法',
                                 `param_trans_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '入参传递模式',
                                 `param_trans_config` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '入参传递配置',
                                 `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人用户id',
                                 `update_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '修改人用户id',
                                 `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                 `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `version` int NOT NULL DEFAULT '1' COMMENT '数据版本号',
                                 `lock_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '乐观锁：操作类型',
                                 `status` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态',
                                 PRIMARY KEY (`id`),
                                 UNIQUE KEY `uk_clusterCode_path_method` (`cluster_code`,`path`,`method`),
                                 UNIQUE KEY `uk_clusterCode_name` (`cluster_code`,`name`),
                                 KEY `idx_name` (`name`(20)),
                                 KEY `idx_path` (`path`(20)),
                                 KEY `idx_create_user` (`create_user`(20)),
                                 KEY `idx_update_user` (`update_user`(20)),
                                 KEY `idx_status` (`status`(20)),
                                 KEY `idx_update_time` (`create_time`),
                                 KEY `idx_create_time` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='api inbound 配置表';

-- ----------------------------
--  Records of `t_api_release`
-- ----------------------------
BEGIN;
INSERT INTO `t_api_release` VALUES ('1', 'Janus_Admin查询集群分页', 'Janus_Admin查询集群分页', 'Janus_Test', 'HTTP', '/api/getAllMenu', 'GET', 'TRANSPARENT', '{\n	\"params\":[]\n}', '', '', '2020-06-24 20:06:27', '2020-07-04 10:38:38', '1', 'ONLINE', 'ONLINE'), ('2', 'URL转URL到指定实例', 'URL精准匹配到URL，但是不走LB，通过指定一个IP和端口转发', 'Janus_Test', 'HTTP', '/admin2/menu/manage/allMenu2', 'GET', 'TRANSPARENT', '{\n	\"params\":[]\n}', '', '', '2020-07-04 10:36:33', '2020-07-04 10:39:03', '1', 'ONLINE', 'ONLINE'), ('3', '测试通过Hosts进行LB', '配置多个后端地址的URL，进行LB，非通过注册中心进行LB', 'Janus_Test', 'HTTP', '/admin7/menu/manage/allMenu2', 'GET', 'TRANSPARENT', '{\n	\"params\":[]\n}', '', '', '2020-07-14 16:11:12', '2020-07-14 16:11:12', '1', '', 'ONLINE');
COMMIT;

-- ----------------------------
--  Table structure for `t_apply`
-- ----------------------------
DROP TABLE IF EXISTS `t_apply`;
CREATE TABLE `t_apply` (
                           `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一id',
                           `cluster_code` varchar(50) NOT NULL COMMENT '集群编码',
                           `description` varchar(200) NOT NULL DEFAULT '' COMMENT '申请说明',
                           `request` mediumtext,
                           `changes` mediumtext,
                           `creator` varchar(64) DEFAULT NULL COMMENT '申请人',
                           `approver` varchar(64) DEFAULT '' COMMENT '审批人',
                           `publisher` varchar(64) DEFAULT '' COMMENT '下发人',
                           `status` varchar(20) NOT NULL COMMENT '状态,1:审核中 2:审核通过 3:已下发  4:拒绝',
                           `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
                           PRIMARY KEY (`id`),
                           KEY `idx_status` (`status`),
                           KEY `idx_cluster_code` (`cluster_code`(20)),
                           KEY `idx_creator` (`creator`(20)),
                           KEY `idx_approver` (`approver`(20)),
                           KEY `idx_publisher` (`publisher`(20)),
                           KEY `idx_gmt_create` (`gmt_create`),
                           KEY `idx_gmt_modified` (`gmt_modified`),
                           KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='网关配置下发申请表';

-- ----------------------------
--  Records of `t_apply`
-- ----------------------------
BEGIN;
INSERT INTO `t_apply` VALUES ('1', 'Janus_Test', '下发路由', '{\"clusterCode\":\"Janus_Test\",\"description\":\"下发路由\",\"submitChanges\":[{\"resourceIdList\":[1],\"sourceCode\":\"API\"}]}', '{\"changeInfoList\":[{\"addCount\":1,\"changeItems\":[{\"changeType\":\"ADD\",\"changeTypeDesc\":\"新增\",\"newData\":{\"apiId\":1,\"clusterCode\":\"Janus_Test\",\"createTime\":\"2020-06-23 09:17:08\",\"createUser\":\"jin.xu\",\"description\":\"Janus_Admin查询集群分页\",\"id\":1,\"isDeleted\":false,\"method\":\"GET\",\"name\":\"Janus_Admin查询集群分页\",\"operateType\":\"NEW\",\"operateTypeDesc\":\"新增\",\"outServiceConfig\":{\"outServices\":[{\"method\":\"GET\",\"name\":\"janus-admin\",\"paramTransConfig\":{\"paramMappings\":[]},\"path\":\"/admin/cluster/page\",\"protocol\":\"REST\",\"type\":\"DISCOVERY\",\"uri\":\"lb://janus-admin\"}]},\"paramTransConfig\":{\"params\":[]},\"paramTransType\":\"TRANSPARENT\",\"path\":\"/admin/cluster/page\",\"protocol\":\"HTTP\",\"status\":\"WAIT_AUDIT\",\"statusDesc\":\"待审核\",\"updateTime\":\"2020-06-25 09:03:06\",\"updateUser\":\"jin.xu\",\"version\":1},\"resourceId\":1}],\"deleteCount\":0,\"sourceCode\":\"API\",\"sourceName\":\"服务路由\",\"totalCount\":1,\"updateCount\":0}],\"clusterCode\":\"Janus_Test\"}', 'jin.xu', 'jin.xu', '', 'RELEASED', '2020-06-24 20:03:06', '2020-06-24 20:06:26', '0'), ('2', 'Janus_Test', '通过', '{\"clusterCode\":\"Janus_Test\",\"description\":\"通过\",\"submitChanges\":[{\"resourceIdList\":[2],\"sourceCode\":\"API\"}]}', '{\"changeInfoList\":[{\"addCount\":1,\"changeItems\":[{\"changeType\":\"ADD\",\"changeTypeDesc\":\"新增\",\"newData\":{\"apiId\":2,\"clusterCode\":\"Janus_Test\",\"createTime\":\"2020-07-04 23:35:12\",\"createUser\":\"jin.xu\",\"description\":\"URL精准匹配到URL，但是不走LB，通过指定一个IP和端口转发\",\"id\":2,\"isDeleted\":false,\"method\":\"GET\",\"name\":\"URL转URL到指定实例\",\"operateType\":\"NEW\",\"operateTypeDesc\":\"新增\",\"outServiceConfig\":{\"outServices\":[{\"method\":\"GET\",\"name\":\"后端转发服务\",\"paramTransConfig\":{\"paramMappings\":[]},\"path\":\"/admin/menu/manage/allMenu\",\"protocol\":\"REST\",\"type\":\"STRAIGHT_FORWARD\",\"uri\":\"[{\\\"host\\\":\\\"127.0.0.1\\\",\\\"port\\\":\\\"8084\\\"}]\"}]},\"paramTransConfig\":{\"params\":[]},\"paramTransType\":\"TRANSPARENT\",\"path\":\"/admin2/menu/manage/allMenu2\",\"protocol\":\"HTTP\",\"status\":\"WAIT_AUDIT\",\"statusDesc\":\"待审核\",\"updateTime\":\"2020-07-04 23:35:39\",\"updateUser\":\"jin.xu\",\"version\":1},\"resourceId\":2}],\"deleteCount\":0,\"sourceCode\":\"API\",\"sourceName\":\"服务路由\",\"totalCount\":1,\"updateCount\":0}],\"clusterCode\":\"Janus_Test\"}', 'jin.xu', 'jin.xu', '', 'RELEASED', '2020-07-04 10:35:39', '2020-07-04 10:36:33', '0'), ('3', 'Janus_Test', '下发数据', '{\"clusterCode\":\"Janus_Test\",\"description\":\"下发数据\",\"submitChanges\":[{\"resourceIdList\":[3],\"sourceCode\":\"API\"}]}', '{\"changeInfoList\":[{\"addCount\":1,\"changeItems\":[{\"changeType\":\"ADD\",\"changeTypeDesc\":\"新增\",\"newData\":{\"apiId\":3,\"clusterCode\":\"Janus_Test\",\"createTime\":\"2020-07-15 02:45:52\",\"createUser\":\"jin.xu\",\"description\":\"配置多个后端地址的URL，进行LB，非通过注册中心进行LB\",\"id\":3,\"isDeleted\":false,\"method\":\"GET\",\"name\":\"测试通过Hosts进行LB\",\"operateType\":\"NEW\",\"operateTypeDesc\":\"新增\",\"outServiceConfig\":{\"outServices\":[{\"method\":\"GET\",\"name\":\"查询菜单接口\",\"paramTransConfig\":{\"paramMappings\":[]},\"path\":\"/admin/menu/manage/allMenu\",\"protocol\":\"REST\",\"type\":\"LOAD_BALANCE\",\"uri\":\"[{\\\"host\\\":\\\"127.0.0.1\\\",\\\"port\\\":\\\"8084\\\"},{\\\"host\\\":\\\"admin.qingcloud.net\\\",\\\"port\\\":\\\"80\\\"}]\"}]},\"paramTransConfig\":{\"params\":[]},\"paramTransType\":\"TRANSPARENT\",\"path\":\"/admin7/menu/manage/allMenu2\",\"protocol\":\"HTTP\",\"status\":\"WAIT_AUDIT\",\"statusDesc\":\"待审核\",\"updateTime\":\"2020-07-15 05:10:33\",\"updateUser\":\"jin.xu\",\"version\":1},\"resourceId\":3}],\"deleteCount\":0,\"sourceCode\":\"API\",\"sourceName\":\"服务路由\",\"totalCount\":1,\"updateCount\":0}],\"clusterCode\":\"Janus_Test\"}', 'jin.xu', 'jin.xu', '', 'RELEASED', '2020-07-14 16:10:33', '2020-07-14 16:11:12', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_audit_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_audit_log`;
CREATE TABLE `t_audit_log` (
                               `id` int NOT NULL AUTO_INCREMENT COMMENT '操作日志id',
                               `username` varchar(20) DEFAULT NULL COMMENT '操作人',
                               `method` varchar(20) DEFAULT NULL COMMENT '请求方式',
                               `url` varchar(200) DEFAULT NULL COMMENT '请求路径',
                               `ip` varchar(50) DEFAULT NULL COMMENT 'IP地址',
                               `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
                               `type` varchar(60) DEFAULT NULL COMMENT '操作业务类型',
                               `params` text COMMENT '请求参数',
                               `deal_result` text COMMENT '请求处理结果内容',
                               `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='admin操作人审计日志';

-- ----------------------------
--  Records of `t_audit_log`
-- ----------------------------
BEGIN;
INSERT INTO `t_audit_log` VALUES ('1', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/cluster/add', '127.0.0.1:51950', '2020-06-19 14:58:36', '新增', '[{\"bizName\":\"bizname1\",\"code\":\"JANUS_ORDER\",\"currentUserId\":\"jin.xu\",\"description\":\"订单集群\",\"domainName\":\"order.api.janus.zmaxis.com\",\"isShare\":0,\"name\":\"订单集群\",\"ownerId\":\"jin.xu\",\"ownerName\":\"许进\",\"state\":\"0\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 14:58:36', '2020-06-19 14:58:36', '0'), ('2', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/cluster/add', '127.0.0.1:52792', '2020-06-19 15:00:33', '新增', '[{\"bizName\":\"sss\",\"code\":\"JANUS_UC\",\"currentUserId\":\"jin.xu\",\"description\":\"用户中心集群\",\"domainName\":\"uc.api.janus.zmaxis.com\",\"isShare\":0,\"name\":\"用户中心集群\",\"ownerId\":\"jin.xu\",\"ownerName\":\"许进\",\"state\":\"0\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:00:33', '2020-06-19 15:00:33', '0'), ('3', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:54876', '2020-06-19 15:05:36', '修改', '[{\"dictCode\":\"biz_name\",\"id\":3,\"itemCode\":\"janus_biz_order\",\"itemDesc\":\"订单业务线\",\"itemName\":\"订单业务线\",\"itemSort\":1,\"itemValue\":\"janus_biz_order\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:05:36', '2020-06-19 15:05:36', '0'), ('4', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:55207', '2020-06-19 15:06:25', '修改', '[{\"dictCode\":\"biz_name\",\"id\":4,\"itemCode\":\"janus_biz_uc\",\"itemDesc\":\"用户中心业务线\",\"itemName\":\"用户中心业务线\",\"itemSort\":2,\"itemValue\":\"janus_biz_uc\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:06:25', '2020-06-19 15:06:25', '0'), ('5', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/cluster/update', '127.0.0.1:55503', '2020-06-19 15:07:08', '修改', '[{\"bizName\":\"janus_biz_uc\",\"code\":\"JANUS_UC\",\"currentUserId\":\"jin.xu\",\"description\":\"用户中心集群\",\"domainName\":\"uc.api.janus.zmaxis.com\",\"id\":2,\"isShare\":0,\"name\":\"用户中心集群\",\"ownerId\":\"jin.xu\",\"ownerName\":\"许进\",\"state\":\"0\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:07:08', '2020-06-19 15:07:08', '0'), ('6', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/cluster/update', '127.0.0.1:55548', '2020-06-19 15:07:14', '修改', '[{\"bizName\":\"janus_biz_order\",\"code\":\"JANUS_ORDER\",\"currentUserId\":\"jin.xu\",\"description\":\"订单集群\",\"domainName\":\"order.api.janus.zmaxis.com\",\"id\":1,\"isShare\":0,\"name\":\"订单集群\",\"ownerId\":\"jin.xu\",\"ownerName\":\"许进\",\"state\":\"0\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:07:14', '2020-06-19 15:07:14', '0'), ('7', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/addDictData', '127.0.0.1:57319', '2020-06-19 15:11:11', '新增', '[{\"dictCode\":\"cluster_codes\",\"itemCode\":\"Janus_Goods\",\"itemDesc\":\"商品集群\",\"itemName\":\"商品集群\",\"itemSort\":3,\"itemValue\":\"Janus_Goods\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:11:11', '2020-06-19 15:11:11', '0'), ('8', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/addDictData', '127.0.0.1:57642', '2020-06-19 15:11:59', '新增', '[{\"dictCode\":\"biz_name\",\"itemCode\":\"janus_biz_goods\",\"itemDesc\":\"商品业务线\",\"itemName\":\"商品业务线\",\"itemSort\":3,\"itemValue\":\"janus_biz_goods\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:11:59', '2020-06-19 15:11:59', '0'), ('9', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/cluster/add', '127.0.0.1:58056', '2020-06-19 15:13:00', '新增', '[{\"bizName\":\"janus_biz_goods\",\"code\":\"Janus_Goods\",\"currentUserId\":\"jin.xu\",\"description\":\"商品网关集群\",\"domainName\":\"goods.api.janus.zmaxis.com\",\"isShare\":0,\"name\":\"商品网关集群\",\"ownerId\":\"jin.xu\",\"ownerName\":\"许进\",\"state\":\"0\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:13:00', '2020-06-19 15:13:00', '0'), ('10', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/cluster/update', '127.0.0.1:58141', '2020-06-19 15:13:13', '修改', '[{\"bizName\":\"janus_biz_uc\",\"code\":\"JANUS_UC\",\"currentUserId\":\"jin.xu\",\"description\":\"用户中心集群\",\"domainName\":\"uc.api.janus.zmaxis.com\",\"id\":2,\"isShare\":0,\"name\":\"用户中心网关集群\",\"ownerId\":\"jin.xu\",\"ownerName\":\"许进\",\"state\":\"0\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:13:13', '2020-06-19 15:13:13', '0'), ('11', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/cluster/update', '127.0.0.1:58202', '2020-06-19 15:13:21', '修改', '[{\"bizName\":\"janus_biz_order\",\"code\":\"JANUS_ORDER\",\"currentUserId\":\"jin.xu\",\"description\":\"订单集群\",\"domainName\":\"order.api.janus.zmaxis.com\",\"id\":1,\"isShare\":0,\"name\":\"订单网关集群\",\"ownerId\":\"jin.xu\",\"ownerName\":\"许进\",\"state\":\"0\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:13:21', '2020-06-19 15:13:21', '0'), ('12', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/addDictData', '127.0.0.1:60858', '2020-06-19 15:19:53', '新增', '[{\"dictCode\":\"cluster_codes\",\"itemCode\":\"Janus_Pay\",\"itemDesc\":\"支付网关集群\",\"itemName\":\"支付网关集群\",\"itemSort\":4,\"itemValue\":\"Janus_Pay\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:19:53', '2020-06-19 15:19:53', '0'), ('13', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/addDictData', '127.0.0.1:61122', '2020-06-19 15:20:31', '新增', '[{\"dictCode\":\"biz_name\",\"itemCode\":\"janus_biz_pay\",\"itemDesc\":\"支付业务线\",\"itemName\":\"支付业务线\",\"itemSort\":1,\"itemValue\":\"janus_biz_pay\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:20:31', '2020-06-19 15:20:31', '0'), ('14', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/cluster/add', '127.0.0.1:61664', '2020-06-19 15:21:48', '新增', '[{\"bizName\":\"janus_biz_pay\",\"code\":\"Janus_Pay\",\"currentUserId\":\"jin.xu\",\"description\":\"支付网关集群\",\"domainName\":\"pay.api.janus.zmaxis.com\",\"isShare\":0,\"name\":\"支付网关集群\",\"ownerId\":\"jin.xu\",\"ownerName\":\"许进\",\"state\":\"0\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:21:48', '2020-06-19 15:21:48', '0'), ('15', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/addDictData', '127.0.0.1:62668', '2020-06-19 15:24:08', '新增', '[{\"dictCode\":\"cluster_codes\",\"itemCode\":\"Janus_Discount\",\"itemDesc\":\"优惠网关集群\",\"itemName\":\"优惠网关集群\",\"itemSort\":4,\"itemValue\":\"Janus_Discount\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:24:08', '2020-06-19 15:24:08', '0'), ('16', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/addDictData', '127.0.0.1:62971', '2020-06-19 15:24:52', '新增', '[{\"dictCode\":\"biz_name\",\"itemCode\":\"janus_biz_discount\",\"itemDesc\":\"优惠业务线\",\"itemName\":\"优惠业务线\",\"itemSort\":1,\"itemValue\":\"janus_biz_discount\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:24:52', '2020-06-19 15:24:52', '0'), ('17', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/cluster/add', '127.0.0.1:63384', '2020-06-19 15:25:51', '新增', '[{\"bizName\":\"janus_biz_discount\",\"code\":\"Janus_Discount\",\"currentUserId\":\"jin.xu\",\"description\":\"优惠业务线\",\"domainName\":\"yh.api.janus.zmaxis.com\",\"isShare\":0,\"name\":\"优惠业务线\",\"ownerId\":\"jin.xu\",\"ownerName\":\"许进\",\"state\":\"0\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:25:51', '2020-06-19 15:25:51', '0'), ('18', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/cluster/update', '127.0.0.1:56465', '2020-06-19 15:48:50', '修改', '[{\"bizName\":\"janus_biz_discount\",\"code\":\"Janus_Discount\",\"currentUserId\":\"jin.xu\",\"description\":\"优惠业务线\",\"domainName\":\"yh.api.janus.xujin.org\",\"id\":5,\"isShare\":0,\"name\":\"优惠业务线\",\"ownerId\":\"jin.xu\",\"ownerName\":\"许进\",\"state\":\"0\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-19 15:48:50', '2020-06-19 15:48:50', '0'), ('19', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/addDictData', '127.0.0.1:49949', '2020-06-22 20:12:31', '新增', '[{\"dictCode\":\"cluster_codes\",\"itemCode\":\"Janus_Test\",\"itemDesc\":\"测试集群\",\"itemName\":\"测试集群\",\"itemSort\":1,\"itemValue\":\"Janus_Test\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-22 20:12:31', '2020-06-22 20:12:31', '0'), ('20', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/cluster/add', '127.0.0.1:50165', '2020-06-22 20:13:43', '新增', '[{\"bizName\":\"janus_biz_order\",\"code\":\"Janus_Test\",\"currentUserId\":\"jin.xu\",\"description\":\"测试集群\",\"domainName\":\"test.api.janus.xujin.org\",\"isShare\":1,\"name\":\"测试集群\",\"ownerId\":\"jin.xu\",\"ownerName\":\"许进\",\"state\":\"0\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-22 20:13:43', '2020-06-22 20:13:43', '0'), ('21', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/add', '127.0.0.1:51442', '2020-06-22 20:20:29', '新增', '[{\"classCode\":\"after_out\",\"currentUserId\":\"jin.xu\",\"description\":\"AuthToken认证\",\"executePlace\":\"pos1\",\"filterCode\":\"AuthToken\",\"filterOrder\":1,\"isGlobal\":0,\"isShare\":1,\"name\":\"AuthToken认证\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-22 20:20:29', '2020-06-22 20:20:29', '0'), ('22', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:51557', '2020-06-22 20:21:08', '修改', '[{\"dictCode\":\"filter_status\",\"id\":25,\"itemCode\":\"1\",\"itemDesc\":\"1\",\"itemName\":\"启用\",\"itemSort\":1,\"itemValue\":\"1\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-22 20:21:08', '2020-06-22 20:21:08', '0'), ('23', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:51641', '2020-06-22 20:21:38', '修改', '[{\"dictCode\":\"filter_status\",\"id\":26,\"itemCode\":\"0\",\"itemDesc\":\"1\",\"itemName\":\"禁用\",\"itemSort\":1,\"itemValue\":\"0\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-22 20:21:38', '2020-06-22 20:21:38', '0'), ('24', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:51925', '2020-06-22 20:23:09', '修改', '[{\"dictCode\":\"filter_execute_place\",\"id\":18,\"itemCode\":\"PRE_IN\",\"itemDesc\":\"PRE_IN\",\"itemName\":\"最先执行\",\"itemSort\":1,\"itemValue\":\"PRE_IN\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-22 20:23:09', '2020-06-22 20:23:09', '0'), ('25', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:52067', '2020-06-22 20:23:59', '修改', '[{\"dictCode\":\"filter_execute_place\",\"id\":19,\"itemCode\":\"INBOUND\",\"itemDesc\":\"INBOUND\",\"itemName\":\"转发前执行\",\"itemSort\":2,\"itemValue\":\"INBOUND\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-22 20:23:59', '2020-06-22 20:23:59', '0'), ('26', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/addDictData', '127.0.0.1:52169', '2020-06-22 20:24:36', '新增', '[{\"dictCode\":\"filter_execute_place\",\"itemCode\":\"INVOKE\",\"itemDesc\":\"转发时执行\",\"itemName\":\"转发时执行\",\"itemSort\":1,\"itemValue\":\"INVOKE\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-22 20:24:36', '2020-06-22 20:24:36', '0'), ('27', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/addDictData', '127.0.0.1:52450', '2020-06-22 20:26:26', '新增', '[{\"dictCode\":\"filter_execute_place\",\"itemCode\":\"OUTBOUND\",\"itemDesc\":\"转发有结果时执行\",\"itemName\":\"转发有结果时执行\",\"itemSort\":3,\"itemValue\":\"OUTBOUND\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-22 20:26:26', '2020-06-22 20:26:26', '0'), ('28', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/addDictData', '127.0.0.1:52588', '2020-06-22 20:27:20', '新增', '[{\"dictCode\":\"filter_execute_place\",\"itemCode\":\"AFTER_OUT\",\"itemDesc\":\"返回结果最后执行\",\"itemName\":\"返回结果最后执行\",\"itemSort\":4,\"itemValue\":\"AFTER_OUT\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-22 20:27:20', '2020-06-22 20:27:20', '0'), ('29', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/update', '127.0.0.1:52684', '2020-06-22 20:27:49', '修改', '[{\"classCode\":\"after_out\",\"currentUserId\":\"jin.xu\",\"description\":\"AuthToken认证\",\"executePlace\":\"PRE_IN\",\"filterCode\":\"AuthToken\",\"filterOrder\":1,\"id\":1,\"isGlobal\":0,\"isShare\":1,\"name\":\"AuthToken认证\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-22 20:27:49', '2020-06-22 20:27:49', '0'), ('30', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/update', '127.0.0.1:52877', '2020-06-22 20:28:54', '修改', '[{\"classCode\":\"after_out\",\"currentUserId\":\"jin.xu\",\"description\":\"AuthToken认证，对网关收到的请求进行AuthToken认证\",\"executePlace\":\"PRE_IN\",\"filterCode\":\"AuthToken\",\"filterOrder\":1,\"id\":1,\"isGlobal\":0,\"isShare\":1,\"name\":\"AuthToken认证\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-22 20:28:54', '2020-06-22 20:28:54', '0'), ('31', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/user/addUserCluster', '127.0.0.1:62271', '2020-06-23 10:49:50', '新增', '[{\"addList\":[\"jin.xu\"]}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-23 10:49:50', '2020-06-23 10:49:50', '0'), ('32', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/user/addUserCluster', '127.0.0.1:64128', '2020-06-23 10:54:21', '新增', '[{\"addList\":[\"jin.xu\"]}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-23 10:54:21', '2020-06-23 10:54:21', '0'), ('33', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/user/addUserCluster', '127.0.0.1:56707', '2020-06-23 11:15:11', '新增', '[{\"addList\":[\"jin.xu\"],\"clusterCode\":\"Janus_Test\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-23 11:15:11', '2020-06-23 11:15:11', '0'), ('34', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/user/addUserCluster', '127.0.0.1:56791', '2020-06-23 11:15:21', '新增', '[{\"addList\":[\"jin.xu\"],\"clusterCode\":\"Janus_Discount\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-23 11:15:21', '2020-06-23 11:15:21', '0'), ('35', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/user/addUserCluster', '127.0.0.1:56847', '2020-06-23 11:15:28', '新增', '[{\"addList\":[\"jin.xu\"],\"clusterCode\":\"Janus_Pay\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-23 11:15:28', '2020-06-23 11:15:28', '0'), ('36', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:63420', '2020-06-23 11:30:48', '修改', '[{\"dictCode\":\"filter_type\",\"id\":7,\"itemCode\":\"public\",\"itemDesc\":\"public\",\"itemName\":\"公共\",\"itemSort\":1,\"itemValue\":\"public\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-23 11:30:48', '2020-06-23 11:30:48', '0'), ('37', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:63971', '2020-06-23 11:32:07', '修改', '[{\"dictCode\":\"filter_type\",\"id\":15,\"itemCode\":\"system\",\"itemDesc\":\"内置\",\"itemName\":\" 内置\",\"itemSort\":1,\"itemValue\":\"system\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-23 11:32:07', '2020-06-23 11:32:07', '0'), ('38', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/add', '127.0.0.1:53645', '2020-06-24 18:05:07', '新增', '[{\"classCode\":\"public\",\"currentUserId\":\"jin.xu\",\"description\":\"该Filter用于对具体API请求添加Header。配置的参数为args: 一个map，自定义key/value;如：X-Request-Red: Red  \",\"executePlace\":\"INBOUND\",\"filterCode\":\"AddRequestHeader\",\"filterOrder\":1,\"isGlobal\":0,\"isShare\":1,\"name\":\"Add请求Header\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-24 18:05:07', '2020-06-24 18:05:07', '0'), ('39', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/add', '127.0.0.1:54022', '2020-06-24 18:07:06', '新增', '[{\"classCode\":\"public\",\"currentUserId\":\"jin.xu\",\"description\":\"用于移除自定义响应头。Filter的参数是一个map，自定义key/value;如：Access-Control-Allow-Origin: *\",\"executePlace\":\"INBOUND\",\"filterCode\":\"RemoveRequestHeader\",\"filterOrder\":1,\"isGlobal\":0,\"isShare\":1,\"name\":\"Remove请求Header\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-24 18:07:06', '2020-06-24 18:07:06', '0'), ('40', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/add', '127.0.0.1:54034', '2020-06-24 18:07:10', '新增', '[{\"classCode\":\"public\",\"currentUserId\":\"jin.xu\",\"description\":\"用于移除自定义响应头。Filter的参数是一个map，自定义key/value;如：Access-Control-Allow-Origin: *\",\"executePlace\":\"INBOUND\",\"filterCode\":\"RemoveRequestHeader\",\"filterOrder\":1,\"isGlobal\":0,\"isShare\":1,\"name\":\"Remove请求Header\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-24 18:07:10', '2020-06-24 18:07:10', '0'), ('41', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/update', '127.0.0.1:54568', '2020-06-24 18:10:19', '修改', '[{\"classCode\":\"public\",\"currentUserId\":\"jin.xu\",\"description\":\"该Filter用于添加自定义响应头。Filter参数args: 一个map，自定义key/value;如：Access-Control-Allow-Origin: *。\",\"executePlace\":\"OUTBOUND\",\"filterCode\":\"AddResponseHeader\",\"filterOrder\":1,\"id\":4,\"isGlobal\":0,\"isShare\":1,\"name\":\"添加响应Header\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-24 18:10:19', '2020-06-24 18:10:19', '0'), ('42', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/add', '127.0.0.1:54913', '2020-06-24 18:12:31', '新增', '[{\"classCode\":\"public\",\"currentUserId\":\"jin.xu\",\"description\":\"该Filter用于移除请求Response中的Header。\",\"executePlace\":\"OUTBOUND\",\"filterCode\":\"RemoveResponseHeader\",\"filterOrder\":1,\"isGlobal\":0,\"isShare\":1,\"name\":\"移除响应Header\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-24 18:12:31', '2020-06-24 18:12:31', '0'), ('43', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/add', '127.0.0.1:55285', '2020-06-24 18:14:52', '新增', '[{\"classCode\":\"public\",\"currentUserId\":\"jin.xu\",\"description\":\"当通过网关对外暴露的API，进行开发时可以在网关上进行API设计设置对应的返回值。当服务需要紧急降级时，可以返回合理的MockResponse。\",\"executePlace\":\"PRE_IN\",\"filterCode\":\"MockResponse\",\"filterOrder\":1,\"isGlobal\":1,\"isShare\":1,\"name\":\"MockAPI返回值\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-24 18:14:52', '2020-06-24 18:14:52', '0'), ('44', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/add', '127.0.0.1:65317', '2020-06-28 11:30:17', '新增', '[{\"classCode\":\"system\",\"currentUserId\":\"jin.xu\",\"description\":\"Janus网关内置，用于负载均衡处理。\",\"executePlace\":\"INVOKE\",\"filterCode\":\"LoadBalanceInvoke\",\"filterOrder\":1,\"isGlobal\":1,\"isShare\":1,\"name\":\"负责均衡Filter\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 11:30:17', '2020-06-28 11:30:17', '0'), ('45', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/add', '127.0.0.1:49614', '2020-06-28 11:34:23', '新增', '[{\"classCode\":\"system\",\"currentUserId\":\"jin.xu\",\"description\":\"网关将请求转发处理之后，统一把返回值处理返回给客户端\",\"executePlace\":\"AFTER_OUT\",\"filterCode\":\"WriteToClient\",\"filterOrder\":1,\"isGlobal\":1,\"isShare\":1,\"name\":\"Response返回Filter\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 11:34:23', '2020-06-28 11:34:23', '0'), ('46', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:49812', '2020-06-28 11:35:27', '修改', '[{\"dictCode\":\"filter_type\",\"id\":10,\"itemCode\":\"\\boutbound类型的Filter\",\"itemDesc\":\"限流\",\"itemName\":\"流控\",\"itemSort\":1,\"itemValue\":\"out\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 11:35:27', '2020-06-28 11:35:27', '0'), ('47', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/add', '127.0.0.1:50243', '2020-06-28 11:37:39', '新增', '[{\"classCode\":\"out\",\"currentUserId\":\"jin.xu\",\"description\":\"当流量过载时，用于网关限流熔断处理.\",\"executePlace\":\"PRE_IN\",\"filterCode\":\"CircuitBreaker\",\"filterOrder\":1,\"isGlobal\":1,\"isShare\":1,\"name\":\"熔断Filter\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 11:37:39', '2020-06-28 11:37:39', '0'), ('48', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/add', '127.0.0.1:50674', '2020-06-28 11:40:03', '新增', '[{\"classCode\":\"system\",\"currentUserId\":\"jin.xu\",\"description\":\"用于网关请求重试\",\"executePlace\":\"INVOKE\",\"filterCode\":\"Retry\",\"filterOrder\":1,\"isGlobal\":0,\"isShare\":1,\"name\":\"重试Filter\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 11:40:03', '2020-06-28 11:40:03', '0'), ('49', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/update', '127.0.0.1:50721', '2020-06-28 11:40:19', '修改', '[{\"classCode\":\"system\",\"currentUserId\":\"jin.xu\",\"description\":\"Janus网关内置，用于负载均衡处理。\",\"executePlace\":\"INVOKE\",\"filterCode\":\"LoadBalanceInvoke\",\"filterOrder\":1,\"id\":7,\"isGlobal\":1,\"isShare\":1,\"name\":\"负载均衡Filter\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 11:40:19', '2020-06-28 11:40:19', '0'), ('50', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:51263', '2020-06-28 11:43:17', '修改', '[{\"dictCode\":\"cluster_config_type\",\"id\":31,\"itemCode\":\"22\",\"itemDesc\":\"2\",\"itemName\":\"property\",\"itemSort\":2,\"itemValue\":\"22\",\"status\":0}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 11:43:17', '2020-06-28 11:43:17', '0'), ('51', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:51704', '2020-06-28 11:45:33', '修改', '[{\"dictCode\":\"cluster_config_keys\",\"id\":27,\"itemCode\":\"serverConf\",\"itemDesc\":\"网关Server的配置\",\"itemName\":\"网关Server的配置\",\"itemSort\":1,\"itemValue\":\"serverConf\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 11:45:33', '2020-06-28 11:45:33', '0'), ('52', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:51868', '2020-06-28 11:46:27', '修改', '[{\"dictCode\":\"cluster_config_keys\",\"id\":30,\"itemCode\":\"clientConf\",\"itemDesc\":\"网关Server客户端配置\",\"itemName\":\"网关Server客户端配置\",\"itemSort\":2,\"itemValue\":\"clientConf\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 11:46:27', '2020-06-28 11:46:27', '0'), ('53', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:52685', '2020-06-28 11:51:07', '修改', '[{\"dictCode\":\"cluster_config_type\",\"id\":31,\"itemCode\":\"property\",\"itemDesc\":\"property属性文件\",\"itemName\":\"property\",\"itemSort\":2,\"itemValue\":\"property\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 11:51:07', '2020-06-28 11:51:07', '0'), ('54', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:52775', '2020-06-28 11:51:31', '修改', '[{\"dictCode\":\"cluster_config_type\",\"id\":28,\"itemCode\":\"yml\",\"itemDesc\":\"yml\",\"itemName\":\"yml\",\"itemSort\":1,\"itemValue\":\"yml\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 11:51:31', '2020-06-28 11:51:31', '0'), ('55', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:52813', '2020-06-28 11:51:43', '修改', '[{\"dictCode\":\"cluster_config_type\",\"id\":31,\"itemCode\":\"property\",\"itemDesc\":\"property属性文件\",\"itemName\":\"property\",\"itemSort\":2,\"itemValue\":\"property\",\"status\":0}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 11:51:43', '2020-06-28 11:51:43', '0'), ('56', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:53400', '2020-06-28 13:37:53', '修改', '[{\"dictCode\":\"cluster_config_type\",\"id\":31,\"itemCode\":\"property\",\"itemDesc\":\"property属性文件\",\"itemName\":\"property\",\"itemSort\":2,\"itemValue\":\"property\",\"status\":0}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 13:37:53', '2020-06-28 13:37:53', '0'), ('57', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:55183', '2020-06-28 13:41:49', '修改', '[{\"dictCode\":\"cluster_config_type\",\"id\":31,\"itemCode\":\"property\",\"itemDesc\":\"property属性文件\",\"itemName\":\"property\",\"itemSort\":2,\"itemValue\":\"property\",\"status\":0}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 13:41:49', '2020-06-28 13:41:49', '0'), ('58', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:56732', '2020-06-28 13:45:34', '修改', '[{\"dictCode\":\"cluster_config_type\",\"id\":31,\"itemCode\":\"property\",\"itemDesc\":\"property属性文件\",\"itemName\":\"property\",\"itemSort\":2,\"itemValue\":\"property\",\"status\":0}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-28 13:45:34', '2020-06-28 13:45:34', '0'), ('59', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/clusterConfig/add', '127.0.0.1:50711', '2020-06-29 13:53:20', '新增', '[{\"clusterCode\":\"Janus_Test\",\"configKey\":\"serverConf\",\"configValue\":\"serverConf:\\n  keepalive: true #Socket参数，连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。可以将此功能视为TCP的心跳机制，需要注意的是：默认的心跳间隔是7200s即2小时。Netty默认关闭该功能\\n  reUseAddr: true #地址复用，默认值False。有四种情况可以使用：(1).当有一个有相同本地地址和端口的socket1处于TIME_WAIT状态时，而你希望启动的程序的socket2要占用该地址和端口，比如重启服务且保持先前端口。(2).有多块网卡或用IP Alias技术的机器在同一端口启动多个进程，但每个进程绑定的本地IP地址不能相同。(3).单个进程绑定相同的端口到多个socket上，但每个socket绑定的ip地址不同。(4).完全相同的地址和端口的重复绑定。但这只用于UDP的多播，不用于TCP。\\n  tcpNoDelay: true #TCP参数，立即发送数据，默认值为Ture（Netty默认为True而操作系统默认为False）。该值设置Nagle算法的启用，改算法将小的碎片数据连接成更大的报文来最小化所发送的报文的数量，如果需要发送一些较小的报文，则需要禁用该算法。Netty默认禁用该算法，从而最小化报文传输延时。\\n  backLog: 1024 #  Socket参数，服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝\\n  sndBuf: 10485760 # Socket参数，TCP数据发送缓冲区大小。\\n  revBuf: 10485760 # Socket参数，TCP数据接收缓冲区大小。\\n  heart: 180 # 读超时时间\\n  bossThread: 2\\n  workThread: 4\\n\\n  maxInitialLineLength: 8192 # The maximum length of the initial line (e.g. \\\"GET / HTTP/1.0\\\") If the length of the initial line exceeds this value, a TooLongFrameException will be raised.\\n  maxHeaderSize: 16384 # \\tThe maximum length of all headers.\\n  maxChunkSize: 18384 #    \\tThe maximum length of the content or each chunk. If the content length exceeds this value, the transfer encoding of the decoded request will be converted to \'chunked\' and the content will be split into multiple HttpContents. If the transfer encoding of the HTTP request is \'chunked\' already, each chunk will be split into smaller chunks if the length of the chunk exceeds this value. If you prefer not to handle HttpContents in your handler, insert HttpObjectAggregator after this decoder in the ChannelPipeline.\\n  maxHttpLength: 65535 #    最大Http请求长度\",\"name\":\"Netty Server的配置\",\"status\":1,\"type\":\"yml\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-29 13:53:20', '2020-06-29 13:53:20', '0'), ('60', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/clusterConfig/add', '127.0.0.1:51216', '2020-06-29 13:56:21', '新增', '[{\"clusterCode\":\"Janus_Test\",\"configKey\":\"clientConf\",\"configValue\":\"clientConf:\\n  keepalive: true #Socket参数，连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。可以将此功能视为TCP的心跳机制，需要注意的是：默认的心跳间隔是7200s即2小时。Netty默认关闭该功能\\n  reUseAddr: true #地址复用，默认值False。有四种情况可以使用：(1).当有一个有相同本地地址和端口的socket1处于TIME_WAIT状态时，而你希望启动的程序的socket2要占用该地址和端口，比如重启服务且保持先前端口。(2).有多块网卡或用IP Alias技术的机器在同一端口启动多个进程，但每个进程绑定的本地IP地址不能相同。(3).单个进程绑定相同的端口到多个socket上，但每个socket绑定的ip地址不同。(4).完全相同的地址和端口的重复绑定。但这只用于UDP的多播，不用于TCP。\\n  tcpNoDelay: true #TCP参数，立即发送数据，默认值为true（Netty默认为True而操作系统默认为False）。该值设置Nagle算法的启用，改算法将小的碎片数据连接成更大的报文来最小化所发送的报文的数量，如果需要发送一些较小的报文，则需要禁用该算法。Netty默认禁用该算法，从而最小化报文传输延时。\\n  backLog: 1024 #  Socket参数，服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝\\n  sndBuf: 10485760 # Socket参数，TCP数据发送缓冲区大小。\\n  revBuf: 10485760 # Socket参数，TCP数据接收缓冲区大小。\\n  heart: 180 # 读超时时间\\n  workThread: 4\",\"name\":\"Netty Client的配置\",\"status\":1,\"type\":\"yml\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-29 13:56:21', '2020-06-29 13:56:21', '0'), ('61', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/addDictData', '127.0.0.1:51459', '2020-06-29 13:57:50', '新增', '[{\"dictCode\":\"cluster_config_keys\",\"itemCode\":\"dynamicClass\",\"itemDesc\":\"热加载Filter路径\",\"itemName\":\"热加载Filter路径\",\"itemSort\":3,\"itemValue\":\"dynamicClass\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-29 13:57:50', '2020-06-29 13:57:50', '0'), ('62', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/clusterConfig/add', '127.0.0.1:51558', '2020-06-29 13:58:22', '新增', '[{\"clusterCode\":\"Janus_Test\",\"configKey\":\"dynamicClass\",\"configValue\":\"dynamicClass:\\n  filtersPath: /opt/apps/conf/janus/plugin/filters  #filters\\n  predicatesPath: /opt/apps/conf/janus/plugin/predicates  #predicates\\n  pollingIntervalSeconds: 300 # 拉取文件时间隔间\\n  compileFileThreads: 4 # 处理java、groovy线程个数\\n  compileFileThreadTimeOut: 30 # 处理java、groovy 线程任务超时时间 单位秒\",\"name\":\"热加载Filter路径\",\"status\":1,\"type\":\"yml\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-29 13:58:22', '2020-06-29 13:58:22', '0'), ('63', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/add', '127.0.0.1:52869', '2020-06-29 14:06:13', '新增', '[{\"classCode\":\"system\",\"currentUserId\":\"jin.xu\",\"description\":\"HttpInvokeFiler是一个URL直接转发到另外一个URL\",\"executePlace\":\"INVOKE\",\"filterCode\":\"HttpInvoke\",\"filterOrder\":1,\"isGlobal\":1,\"isShare\":1,\"name\":\"直接转发Filter\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-29 14:06:13', '2020-06-29 14:06:13', '0'), ('64', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/add', '127.0.0.1:58237', '2020-06-29 14:40:44', '新增', '[{\"classCode\":\"public\",\"currentUserId\":\"jin.xu\",\"description\":\"StripPrefixFilter根据位置去前缀的Filter\",\"executePlace\":\"INBOUND\",\"filterCode\":\"StripPrefix\",\"filterOrder\":1,\"isGlobal\":0,\"isShare\":1,\"name\":\"去前缀Filter\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-29 14:40:44', '2020-06-29 14:40:44', '0'), ('65', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/add', '127.0.0.1:60776', '2020-06-29 14:56:00', '新增', '[{\"classCode\":\"public\",\"currentUserId\":\"jin.xu\",\"description\":\"url 的Mapping Filter，主要用于一个URL映射为另外一个URL\",\"executePlace\":\"INBOUND\",\"filterCode\":\"PathMappingFilter\",\"filterOrder\":1,\"isGlobal\":0,\"isShare\":1,\"name\":\"URL映射Filter\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-06-29 14:56:00', '2020-06-29 14:56:00', '0'), ('66', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/cluster/updateClusterFilter', '127.0.0.1:62835', '2020-07-01 17:09:52', '修改', '[{\"code\":\"Janus_Discount\",\"currentUserId\":\"jin.xu\",\"filterIds\":[6]}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-07-01 17:09:52', '2020-07-01 17:09:52', '0'), ('67', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/clusterConfig/update', '127.0.0.1:65251', '2020-07-03 17:08:15', '修改', '[{\"clusterCode\":\"Janus_Test\",\"configKey\":\"dynamicClass\",\"configValue\":\"dynamicClass:\\n  filtersPath: /opt/apps/conf/janus/plugin/filters  \\n  predicatesPath: /opt/apps/conf/janus/plugin/predicates  \\n  pollingIntervalSeconds: 300 \\n  compileFileThreads: 4 \\n  compileFileThreadTimeOut: 30 \",\"currentUserId\":\"jin.xu\",\"id\":3,\"name\":\"热加载Filter路径\",\"status\":1,\"type\":\"yml\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-07-03 17:08:15', '2020-07-03 17:08:15', '0'), ('68', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/clusterConfig/update', '127.0.0.1:65373', '2020-07-03 17:08:58', '修改', '[{\"clusterCode\":\"Janus_Test\",\"configKey\":\"clientConf\",\"configValue\":\"clientConf:\\n  keepalive: true \\n  reUseAddr: true \\n  tcpNoDelay: true \\n  backLog: 1024 \\n  sndBuf: 10485760 \\n  revBuf: 10485760 \\n  heart: 180 \\n  workThread: 4\",\"currentUserId\":\"jin.xu\",\"id\":2,\"name\":\"Netty Client的配置\",\"status\":1,\"type\":\"yml\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-07-03 17:08:58', '2020-07-03 17:08:58', '0'), ('69', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/clusterConfig/update', '127.0.0.1:49227', '2020-07-03 17:10:23', '修改', '[{\"clusterCode\":\"Janus_Test\",\"configKey\":\"serverConf\",\"configValue\":\"serverConf:\\n  keepalive: true \\n  reUseAddr: true \\n  tcpNoDelay: true \\n  backLog: 1024 \\n  sndBuf: 10485760 \\n  revBuf: 10485760 \\n  heart: 180 \\n  bossThread: 2\\n  workThread: 4\\n\\n  maxInitialLineLength: 8192 \\n  maxHeaderSize: 16384 \\n  maxChunkSize: 18384 \\n  maxHttpLength: 65535 \",\"currentUserId\":\"jin.xu\",\"id\":1,\"name\":\"Netty Server的配置\",\"status\":1,\"type\":\"yml\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-07-03 17:10:23', '2020-07-03 17:10:23', '0'), ('70', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/addDictData', '127.0.0.1:49604', '2020-07-03 17:12:27', '新增', '[{\"dictCode\":\"cluster_config_keys\",\"itemCode\":\"janusInfluxConfig\",\"itemDesc\":\"网关InfluxDB配置\",\"itemName\":\"网关InfluxDB配置\",\"itemSort\":1,\"itemValue\":\"janusInfluxConfig\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-07-03 17:12:27', '2020-07-03 17:12:27', '0'), ('71', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/clusterConfig/add', '127.0.0.1:49722', '2020-07-03 17:13:03', '新增', '[{\"clusterCode\":\"Janus_Test\",\"configKey\":\"janusInfluxConfig\",\"configValue\":\"janusInfluxConfig:\\n  uri: http://120.27.18.197:8086\\n  userName: admin\\n  db: janus\",\"name\":\"网关InfluxDB配置\",\"status\":1,\"type\":\"yml\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-07-03 17:13:03', '2020-07-03 17:13:03', '0'), ('72', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/clusterConfig/update', '127.0.0.1:58388', '2020-07-04 10:01:52', '修改', '[{\"clusterCode\":\"Janus_Test\",\"configKey\":\"serverConf\",\"configValue\":\"serverConf:\\n  keepalive: true #Socket参数，连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。可以将此功能视为TCP的心跳机制，需要注意的是：默认的心跳间隔是7200s即2小时。Netty默认关闭该功能\\n  reUseAddr: true #地址复用，默认值False。有四种情况可以使用：(1).当有一个有相同本地地址和端口的socket1处于TIME_WAIT状态时，而你希望启动的程序的socket2要占用该地址和端口，比如重启服务且保持先前端口。(2).有多块网卡或用IP Alias技术的机器在同一端口启动多个进程，但每个进程绑定的本地IP地址不能相同。(3).单个进程绑定相同的端口到多个socket上，但每个socket绑定的ip地址不同。(4).完全相同的地址和端口的重复绑定。但这只用于UDP的多播，不用于TCP。\\n  tcpNoDelay: true #TCP参数，立即发送数据，默认值为Ture（Netty默认为True而操作系统默认为False）。该值设置Nagle算法的启用，改算法将小的碎片数据连接成更大的报文来最小化所发送的报文的数量，如果需要发送一些较小的报文，则需要禁用该算法。Netty默认禁用该算法，从而最小化报文传输延时。\\n  backLog: 1024 #  Socket参数，服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝\\n  sndBuf: 10485760 # Socket参数，TCP数据发送缓冲区大小。\\n  revBuf: 10485760 # Socket参数，TCP数据接收缓冲区大小。\\n  heart: 180 # 读超时时间\\n  bossThread: 2\\n  workThread: 4\\n\\n  maxInitialLineLength: 8192 # The maximum length of the initial line (e.g. \\\"GET / HTTP/1.0\\\") If the length of the initial line exceeds this value, a TooLongFrameException will be raised.\\n  maxHeaderSize: 16384 # \\tThe maximum length of all headers.\\n  maxChunkSize: 18384 #    \\tThe maximum length of the content or each chunk. If the content length exceeds this value, the transfer encoding of the decoded request will be converted to \'chunked\' and the content will be split into multiple HttpContents. If the transfer encoding of the HTTP request is \'chunked\' already, each chunk will be split into smaller chunks if the length of the chunk exceeds this value. If you prefer not to handle HttpContents in your handler, insert HttpObjectAggregator after this decoder in the ChannelPipeline.\\n  maxHttpLength: 65535 #    最大Http请求长度\",\"currentUserId\":\"jin.xu\",\"id\":1,\"name\":\"Netty Server的配置\",\"status\":1,\"type\":\"yml\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-07-04 10:01:52', '2020-07-04 10:01:52', '0'), ('73', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:50159', '2020-07-04 10:48:14', '修改', '[{\"dictCode\":\"API_OutServiceType\",\"id\":54,\"itemCode\":\"DISCOVERY\",\"itemDesc\":\"服务发现\",\"itemName\":\"服务发现\",\"itemSort\":1,\"itemValue\":\"DISCOVERY\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-07-04 10:48:14', '2020-07-04 10:48:14', '0'), ('74', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:50245', '2020-07-04 10:48:44', '修改', '[{\"dictCode\":\"API_OutServiceType\",\"id\":55,\"itemCode\":\"LOAD_BALANCE\",\"itemDesc\":\"负载均衡\",\"itemName\":\"负载均衡\",\"itemSort\":2,\"itemValue\":\"LOAD_BALANCE\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-07-04 10:48:44', '2020-07-04 10:48:44', '0'), ('75', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/metadata/updateDictData', '127.0.0.1:50317', '2020-07-04 10:49:07', '修改', '[{\"dictCode\":\"API_OutServiceType\",\"id\":56,\"itemCode\":\"STRAIGHT_FORWARD\",\"itemDesc\":\"直接转发\",\"itemName\":\"直接转发\",\"itemSort\":3,\"itemValue\":\"STRAIGHT_FORWARD\",\"status\":1}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-07-04 10:49:07', '2020-07-04 10:49:07', '0'), ('76', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/clusterConfig/update', '127.0.0.1:63978', '2020-07-06 19:12:16', '修改', '[{\"clusterCode\":\"Janus_Test\",\"configKey\":\"janusInfluxConfig\",\"configValue\":\"janusInfluxConfig:\\n  #uri: http://120.27.18.197:8086\\n  uri: http://10.81.53.251:8086\\n  userName: admin\\n  db: janus\",\"currentUserId\":\"jin.xu\",\"id\":4,\"name\":\"网关InfluxDB配置\",\"status\":1,\"type\":\"yml\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-07-06 19:12:16', '2020-07-06 19:12:16', '0'), ('77', 'jin.xu', 'POST', 'http://127.0.0.1:8084/admin/filter/update', '127.0.0.1:57050', '2020-07-07 12:17:26', '修改', '[{\"classCode\":\"system\",\"currentUserId\":\"jin.xu\",\"description\":\"url 的Mapping Filter，主要用于一个URL映射为另外一个URL\",\"executePlace\":\"INBOUND\",\"filterCode\":\"PathMappingFilter\",\"filterOrder\":1,\"id\":13,\"isGlobal\":0,\"isShare\":1,\"name\":\"URL映射Filter\",\"status\":\"1\"}]', 'Response{code=200, msgCode=\'200\', msgContent=\'success\', data=\'null\'}', '2020-07-07 12:17:26', '2020-07-07 12:17:26', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_cluster`
-- ----------------------------
DROP TABLE IF EXISTS `t_cluster`;
CREATE TABLE `t_cluster` (
                             `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '集群Id',
                             `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '集群编码',
                             `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '集群中文名称',
                             `domain_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                             `owner_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '负责人姓名',
                             `biz_name` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '业务名称,包括所有父级业务名称',
                             `owner_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '负责人Id',
                             `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '集群描述',
                             `status` tinyint DEFAULT '0' COMMENT '集群状态  0 使用  1禁用',
                             `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `is_share` tinyint NOT NULL DEFAULT '0' COMMENT '是否共享集群 0 独立集群 1 共享集群',
                             `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
                             `created` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '集群创建人',
                             `updated` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '集群更新人',
                             `stars` double DEFAULT NULL COMMENT '集群评分',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='集群表';

-- ----------------------------
--  Records of `t_cluster`
-- ----------------------------
BEGIN;
INSERT INTO `t_cluster` VALUES ('1', 'JANUS_ORDER', '订单网关集群', 'order.api.janus.xujin.org', '许进', 'janus_biz_order', 'jin.xu', '订单集群', '0', '2020-06-19 14:58:36', '2020-06-19 15:50:02', '0', '0', 'jin.xu', 'jin.xu', '3'), ('2', 'JANUS_UC', '用户中心网关集群', 'uc.api.janus.xujin.org', '许进', 'janus_biz_uc', 'jin.xu', '用户中心集群', '0', '2020-06-19 15:00:33', '2020-06-19 15:49:42', '0', '0', 'jin.xu', 'jin.xu', '4'), ('3', 'Janus_Goods', '商品网关集群', 'goods.api.janus.xujin.org', '许进', 'janus_biz_goods', 'jin.xu', '商品网关集群', '0', '2020-06-19 15:13:00', '2020-06-19 15:49:34', '0', '0', 'jin.xu', null, null), ('4', 'Janus_Pay', '支付网关集群', 'pay.api.janus.xujin.org', '许进', 'janus_biz_pay', 'jin.xu', '支付网关集群', '0', '2020-06-19 15:21:48', '2020-06-19 15:49:27', '0', '0', 'jin.xu', null, null), ('5', 'Janus_Discount', '优惠业务线', 'yh.api.janus.xujin.org', '许进', 'janus_biz_discount', 'jin.xu', '优惠业务线', '0', '2020-06-19 15:25:51', '2020-06-19 15:48:50', '0', '0', 'jin.xu', 'jin.xu', null), ('6', 'Janus_Test', '测试集群', 'test.api.janus.xujin.org', '许进', 'janus_biz_order', 'jin.xu', '测试集群', '0', '2020-06-22 20:13:43', '2020-06-24 10:33:03', '1', '0', 'jin.xu', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `t_cluster_config`
-- ----------------------------
DROP TABLE IF EXISTS `t_cluster_config`;
CREATE TABLE `t_cluster_config` (
                                    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                    `name` varchar(30) DEFAULT NULL COMMENT '配置名称',
                                    `cluster_code` varchar(50) NOT NULL COMMENT '集群编码',
                                    `config_key` varchar(255) NOT NULL COMMENT '配置key',
                                    `config_value` text NOT NULL COMMENT '配置Value',
                                    `status` tinyint DEFAULT '0' COMMENT '状态  0启用 1禁用',
                                    `type` varchar(30) DEFAULT NULL COMMENT '配置类型',
                                    `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
                                    `modifier` varchar(64) DEFAULT NULL COMMENT '修改者',
                                    `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='集群配置表';

-- ----------------------------
--  Records of `t_cluster_config`
-- ----------------------------
BEGIN;
INSERT INTO `t_cluster_config` VALUES ('1', 'Netty Server的配置', 'Janus_Test', 'serverConf', 'serverConf:\n  keepalive: true #Socket参数，连接保活，默认值为False。启用该功能时，TCP会主动探测空闲连接的有效性。可以将此功能视为TCP的心跳机制，需要注意的是：默认的心跳间隔是7200s即2小时。Netty默认关闭该功能\n  reUseAddr: true #地址复用，默认值False。有四种情况可以使用：(1).当有一个有相同本地地址和端口的socket1处于TIME_WAIT状态时，而你希望启动的程序的socket2要占用该地址和端口，比如重启服务且保持先前端口。(2).有多块网卡或用IP Alias技术的机器在同一端口启动多个进程，但每个进程绑定的本地IP地址不能相同。(3).单个进程绑定相同的端口到多个socket上，但每个socket绑定的ip地址不同。(4).完全相同的地址和端口的重复绑定。但这只用于UDP的多播，不用于TCP。\n  tcpNoDelay: true #TCP参数，立即发送数据，默认值为Ture（Netty默认为True而操作系统默认为False）。该值设置Nagle算法的启用，改算法将小的碎片数据连接成更大的报文来最小化所发送的报文的数量，如果需要发送一些较小的报文，则需要禁用该算法。Netty默认禁用该算法，从而最小化报文传输延时。\n  backLog: 1024 #  Socket参数，服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝\n  sndBuf: 10485760 # Socket参数，TCP数据发送缓冲区大小。\n  revBuf: 10485760 # Socket参数，TCP数据接收缓冲区大小。\n  heart: 180 # 读超时时间\n  bossThread: 2\n  workThread: 4\n\n  maxInitialLineLength: 8192 # The maximum length of the initial line (e.g. \"GET / HTTP/1.0\") If the length of the initial line exceeds this value, a TooLongFrameException will be raised.\n  maxHeaderSize: 16384 # 	The maximum length of all headers.\n  maxChunkSize: 18384 #    	The maximum length of the content or each chunk. If the content length exceeds this value, the transfer encoding of the decoded request will be converted to \'chunked\' and the content will be split into multiple HttpContents. If the transfer encoding of the HTTP request is \'chunked\' already, each chunk will be split into smaller chunks if the length of the chunk exceeds this value. If you prefer not to handle HttpContents in your handler, insert HttpObjectAggregator after this decoder in the ChannelPipeline.\n  maxHttpLength: 65535 #    最大Http请求长度', '1', 'yml', 'jin.xu', 'jin.xu', '2020-06-29 13:53:20', '2020-07-04 10:01:52', '0'), ('2', 'Netty Client的配置', 'Janus_Test', 'clientConf', 'clientConf:\n  keepalive: true \n  reUseAddr: true \n  tcpNoDelay: true \n  backLog: 1024 \n  sndBuf: 10485760 \n  revBuf: 10485760 \n  heart: 180 \n  workThread: 4', '1', 'yml', 'jin.xu', 'jin.xu', '2020-06-29 13:56:21', '2020-07-03 17:08:58', '0'), ('3', '热加载Filter路径', 'Janus_Test', 'dynamicClass', 'dynamicClass:\n  filtersPath: /opt/apps/conf/janus/plugin/filters  \n  predicatesPath: /opt/apps/conf/janus/plugin/predicates  \n  pollingIntervalSeconds: 300 \n  compileFileThreads: 4 \n  compileFileThreadTimeOut: 30 ', '1', 'yml', 'jin.xu', 'jin.xu', '2020-06-29 13:58:22', '2020-07-03 17:08:15', '0'), ('4', '网关InfluxDB配置', 'Janus_Test', 'janusInfluxConfig', 'janusInfluxConfig:\n  #uri: http://120.27.18.197:8086\n  uri: http://10.81.53.251:8086\n  userName: admin\n  db: janus', '1', 'yml', 'jin.xu', 'jin.xu', '2020-07-03 17:13:03', '2020-07-06 19:12:16', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_cluster_filter`
-- ----------------------------
DROP TABLE IF EXISTS `t_cluster_filter`;
CREATE TABLE `t_cluster_filter` (
                                    `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                                    `cluster_code` varchar(50) NOT NULL COMMENT '集群编码',
                                    `filter_id` int NOT NULL COMMENT 'Filter的唯一Id',
                                    `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                    `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='集群filter中间表';

-- ----------------------------
--  Records of `t_cluster_filter`
-- ----------------------------
BEGIN;
INSERT INTO `t_cluster_filter` VALUES ('1', 'Janus_Discount', '6', '2020-07-01 17:09:52', '2020-07-01 17:09:52', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_cluster_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_cluster_user`;
CREATE TABLE `t_cluster_user` (
                                  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id',
                                  `cluster_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '集群编码',
                                  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
                                  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='集群用户中间表';

-- ----------------------------
--  Records of `t_cluster_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_cluster_user` VALUES ('1', '', 'jin.xu', '2020-06-23 10:54:20', '2020-06-23 10:55:28', '0'), ('2', 'Janus_Test', 'jin.xu', '2020-06-23 11:15:11', '2020-06-23 11:15:11', '0'), ('3', 'Janus_Discount', 'jin.xu', '2020-06-23 11:15:21', '2020-06-23 11:15:21', '0'), ('4', 'Janus_Pay', 'jin.xu', '2020-06-23 11:15:28', '2020-06-23 11:15:28', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_dict_data`
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_data`;
CREATE TABLE `t_dict_data` (
                               `id` int NOT NULL AUTO_INCREMENT COMMENT '数据字典详细主键',
                               `dict_code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '数据字典分类标识',
                               `item_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '数据字典详细名称',
                               `item_value` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '数据字典详细值',
                               `item_desc` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据字典详细描述',
                               `item_sort` int DEFAULT NULL COMMENT '排序',
                               `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
                               `status` tinyint DEFAULT '0' COMMENT 'COMMENT ''数据字典项启用状态，1：启用，0：未启用'',',
                               `item_code` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='数据字典详细表';

-- ----------------------------
--  Records of `t_dict_data`
-- ----------------------------
BEGIN;
INSERT INTO `t_dict_data` VALUES ('1', 'cluster_status', '状态1', 'state1', 'state1', null, '2020-05-28 13:42:20', '2020-05-28 16:15:32', '1', '1', 'state1'), ('2', 'cluster_status', '状态2', 'state2', 'state2', null, '2020-05-28 13:42:54', '2020-05-28 16:15:32', '1', '1', 'state2'), ('3', 'biz_name', '订单业务线', 'janus_biz_order', '订单业务线', '1', '2020-05-28 13:43:39', '2020-05-28 13:43:39', '0', '1', 'janus_biz_order'), ('4', 'biz_name', '用户中心业务线', 'janus_biz_uc', '用户中心业务线', '2', '2020-05-28 13:43:59', '2020-05-28 13:43:59', '0', '1', 'janus_biz_uc'), ('5', 'cluster_status', '使用中', '0', '使用中', '2', '2020-05-28 13:43:59', '2020-05-29 11:41:43', '0', '1', 'using'), ('6', 'cluster_status', '失效', '1', '失效', '2', '2020-05-28 13:43:59', '2020-05-29 11:41:43', '0', '1', 'invalid'), ('7', 'filter_type', '公共', 'public', 'public', '1', '2020-05-29 14:07:39', '2020-06-03 11:01:22', '0', '1', 'public'), ('8', 'filter_type', '灰度', 'in', '灰度', '1', '2020-05-29 14:09:27', '2020-06-02 10:06:48', '0', '1', 'inbound'), ('9', 'filter_type', '监控', 'invoke', '监控', null, '2020-05-29 14:14:00', '2020-06-02 10:06:48', '0', '0', 'invoke'), ('10', 'filter_type', '流控', 'out', '限流', '1', '2020-05-29 14:15:10', '2020-06-02 10:06:48', '0', '1', 'outbound类型的Filter'), ('11', 'filter_type', '安全', 'after_out', '安全', '1', '2020-05-29 14:16:33', '2020-06-02 10:06:48', '0', '1', 'after_out'), ('12', 'fiter_classify', '组件分类', 'fiter_classify_security', '安全相关的Filter', '1', '2020-05-29 14:20:25', '2020-06-02 17:38:35', '1', '1', 'fiter_classify_security'), ('13', 'cluster_codes', '订单集群编码', 'JANUS_ORDER', '订单集群', '1', '2020-05-29 14:30:01', '2020-05-29 14:30:01', '0', '1', 'JANUS_ORDER'), ('14', 'cluster_codes', '用户中心集群编码', 'JANUS_UC', '用户中心集群', '1', '2020-05-29 14:33:21', '2020-05-29 14:33:21', '0', '1', 'JANUS_UC'), ('15', 'filter_type', ' 内置', 'system', '内置', '1', '2020-05-29 14:16:33', '2020-06-02 10:06:48', '0', '1', 'system'), ('16', 'fiter_classify', '组件分类1', 'fiter_classify_security1', '安全相关的Filter', '1', '2020-05-29 14:20:25', '2020-06-02 17:38:35', '1', '1', 'fiter_classify_security'), ('17', 'filter_module_type', '公用组件', '0', '公用组件', '0', '2020-06-02 14:52:26', '2020-06-02 14:53:53', '0', '0', null), ('18', 'filter_execute_place', '最先执行', 'PRE_IN', 'PRE_IN', '1', '2020-06-02 17:39:11', '2020-06-02 17:39:11', '0', '1', 'PRE_IN'), ('19', 'filter_execute_place', '转发前执行', 'INBOUND', 'INBOUND', '2', '2020-06-02 17:39:29', '2020-06-02 17:39:29', '0', '1', 'INBOUND'), ('20', 'filter_status', '状态1', '1', '状态1', '1', '2020-06-02 17:40:15', '2020-06-03 11:23:27', '1', '1', '状态1'), ('21', 'filter_status', '状态2', '0', '状态2', '2', '2020-06-02 17:40:44', '2020-06-03 11:23:27', '1', '1', '状态2'), ('22', 'filter_module_type', '组件类型', '1', '1', '1', '2020-06-02 19:02:48', '2020-06-02 19:02:48', '0', '1', '组件类型'), ('23', 'cluster_codes', 'x', 'x', 'x', '1', '2020-06-03 11:20:42', '2020-06-03 11:20:42', '1', '1', 'x'), ('24', 'filter_status', 'x', 'x', 'x', '1', '2020-06-03 11:23:21', '2020-06-03 11:23:21', '1', '1', 'x'), ('25', 'filter_status', '启用', '1', '1', '1', '2020-06-03 11:24:46', '2020-06-03 11:24:46', '0', '1', '1'), ('26', 'filter_status', '禁用', '0', '1', '1', '2020-06-03 11:24:58', '2020-06-03 11:24:58', '0', '1', '0'), ('27', 'cluster_config_keys', '网关Server的配置', 'serverConf', '网关Server的配置', '1', '2020-06-03 17:52:11', '2020-06-03 17:52:11', '0', '1', 'serverConf'), ('28', 'cluster_config_type', 'yml', 'yml', 'yml', '1', '2020-06-03 17:52:23', '2020-06-03 17:52:23', '0', '1', 'yml'), ('29', 'cluster_config_status', '配置状态1', '1', '1', '1', '2020-06-03 17:52:34', '2020-06-03 18:47:12', '1', '1', '1'), ('30', 'cluster_config_keys', '网关Server客户端配置', 'clientConf', '网关Server客户端配置', '2', '2020-06-03 19:06:28', '2020-06-03 19:06:28', '0', '1', 'clientConf'), ('31', 'cluster_config_type', 'property', 'property', 'property属性文件', '2', '2020-06-03 19:06:40', '2020-06-03 19:06:40', '0', '0', 'property'), ('32', 'ApiMapping', 'String', 'String', 'xxx', '1', '2020-06-06 16:53:34', '2020-06-06 16:53:34', '0', '1', 'String'), ('33', 'api_protocol', 'HTTP', 'HTTP', '1', '1', '2020-06-08 11:32:33', '2020-06-08 11:32:33', '0', '1', 'HTTP'), ('34', 'api_protocol', 'HTTPS', 'HTTPS', '1', '1', '2020-06-08 11:34:19', '2020-06-08 11:34:19', '0', '1', 'HTTPS'), ('35', 'api_method', 'get', 'get', 'get', '1', '2020-06-08 11:36:02', '2020-06-08 11:36:02', '0', '1', 'get'), ('36', 'api_method', 'post', 'post', 'post', '2', '2020-06-08 11:36:18', '2020-06-08 11:36:18', '0', '1', 'post'), ('37', 'api_param_position', '路径参数', 'PATH', 'PATH', '1', '2020-06-08 13:40:01', '2020-06-08 13:40:01', '0', '1', 'PATH'), ('38', 'api_param_dataType', 'INT', 'INT', '1', '1', '2020-06-08 13:41:28', '2020-06-08 13:41:28', '0', '1', 'INT'), ('39', 'api_param_position', '头参数', 'HEAD', '头参数', '2', '2020-06-08 13:50:11', '2020-06-08 13:50:11', '0', '1', 'HEAD'), ('40', 'api_param_dataType', 'String', 'STRING', 'String', '2', '2020-06-08 13:50:27', '2020-06-08 13:50:27', '0', '1', 'String'), ('41', 'http_method', 'POST', 'POST', '1', '1', '2020-06-08 14:44:11', '2020-06-08 14:44:11', '0', '1', 'POST'), ('42', 'http_method', 'GET', 'GET', 'GET', '1', '2020-06-08 14:44:23', '2020-06-08 14:44:23', '0', '1', 'GET'), ('43', 'api_paramTransType', '透传', 'TRANSPARENT', '透传', '1', '2020-06-08 14:45:41', '2020-06-08 14:45:41', '0', '1', 'TRANSPARENT'), ('44', 'api_paramTransType', '入参映射（过滤未知参数）', 'MAPPING_AND_FILTER', '入参映射（过滤未知参数）', '2', '2020-06-08 14:46:13', '2020-06-08 14:46:13', '0', '1', 'MAPPING_AND_FILTER'), ('45', 'api_paramTransType', '入参映射（透传未知参数）', 'MAPPING_AND_TRANS ', 'MAPPING_AND_TRANS ', '3', '2020-06-08 14:46:30', '2020-06-08 14:46:30', '0', '1', 'MAPPING_AND_TRANS '), ('46', 'api_param_position', '查询参数', 'QUERY', '查询参数', '3', '2020-06-08 14:48:28', '2020-06-08 14:48:28', '0', '1', 'QUERY'), ('47', 'api_param_dataType', 'ARRAY', 'ARRAY', 'ARRAY', '3', '2020-06-08 14:49:24', '2020-06-08 14:49:24', '0', '0', 'ARRAY'), ('48', 'OutServiceProtocol', '服务发现', 'DISCOVERY', 'DISCOVERY', '1', '2020-06-08 19:38:47', '2020-06-08 19:38:47', '0', '1', 'DISCOVERY'), ('49', 'OutServiceProtocol', '负载均衡', 'LOAD_BALANCE', 'LOAD_BALANCE', '2', '2020-06-08 19:39:01', '2020-06-08 19:39:01', '0', '1', 'LOAD_BALANCE'), ('50', 'OutServiceProtocol', '直接转发', 'STRAIGHT_FORWARD', 'STRAIGHT_FORWARD', '3', '2020-06-08 19:39:16', '2020-06-08 19:39:16', '0', '1', 'STRAIGHT_FORWARD'), ('51', 'API_OutServiceProtocol', 'REST', 'REST', 'REST', '1', '2020-06-08 19:40:54', '2020-06-08 19:40:54', '0', '1', 'REST'), ('52', 'API_OutServiceProtocol', 'RPC', 'RPC', 'RPC', '2', '2020-06-08 19:41:08', '2020-06-08 19:41:08', '0', '1', 'RPC'), ('53', 'API_OutServiceProtocol', 'STRAIGHT_FORWARD', 'STRAIGHT_FORWARD', 'STRAIGHT_FORWARD', '3', '2020-06-08 19:43:25', '2020-06-08 19:43:25', '1', '1', 'STRAIGHT_FORWARD'), ('54', 'API_OutServiceType', '服务发现', 'DISCOVERY', '服务发现', '1', '2020-06-08 19:46:35', '2020-06-08 19:46:35', '0', '1', 'DISCOVERY'), ('55', 'API_OutServiceType', '负载均衡', 'LOAD_BALANCE', '负载均衡', '2', '2020-06-08 19:46:45', '2020-06-08 19:46:45', '0', '1', 'LOAD_BALANCE'), ('56', 'API_OutServiceType', '直接转发', 'STRAIGHT_FORWARD', '直接转发', '3', '2020-06-08 19:46:57', '2020-06-08 19:46:57', '0', '1', 'STRAIGHT_FORWARD'), ('57', 'publish_ReleaseTypeEnum', '全量', 'FULL', 'FULL', '1', '2020-06-18 18:27:13', '2020-06-18 18:27:13', '0', '1', 'FULL'), ('58', 'publish_ReleaseTypeEnum', '灰度', 'GRAY', 'GRAY', '1', '2020-06-18 18:27:56', '2020-06-18 18:27:56', '0', '1', 'GRAY'), ('59', 'api_draft_status', '编辑中', '编辑中', '编辑中', null, '2020-06-19 11:42:27', '2020-06-19 11:42:27', '0', '1', 'EDITING'), ('60', 'api_draft_status', '待审核', '待审核', '待审核', null, '2020-06-19 11:42:53', '2020-06-19 11:42:53', '0', '1', 'WAIT_AUDIT'), ('61', 'api_draft_status', '已审核', '已审核', '已审核', '0', '2020-06-19 11:43:35', '2020-06-19 11:43:35', '0', '1', 'AUDITED'), ('62', 'api_draft_status', '已驳回', '已驳回', '已驳回', null, '2020-06-19 11:51:13', '2020-06-19 11:51:13', '0', '1', 'REJECTED'), ('63', 'api_draft_status', '已撤销', '已撤销', '已撤销', null, '2020-06-19 11:51:51', '2020-06-19 11:51:51', '0', '1', 'CANCELED'), ('64', 'api_draft_status', '发布中', '发布中', '发布中', null, '2020-06-19 11:52:15', '2020-06-19 11:52:15', '0', '1', 'RELEASING'), ('65', 'api_draft_status', '已发布', '已发布', '已发布', null, '2020-06-19 11:52:40', '2020-06-19 11:52:40', '0', '1', 'RELEASED'), ('66', 'api_release_status', '在线', '在线', '在线', null, '2020-06-19 11:59:52', '2020-06-19 11:59:52', '0', '1', 'ONLINE'), ('67', 'api_release_status', '已下线', '已下线', '已下线', null, '2020-06-19 12:00:17', '2020-06-19 12:00:17', '0', '1', 'OFFLINE'), ('68', 'api_operate_type', '新增', '新增', '新增', null, '2020-06-19 14:21:30', '2020-06-19 14:21:30', '0', '1', 'NEW'), ('69', 'api_operate_type', '更新', '更新', '更新', null, '2020-06-19 14:21:52', '2020-06-19 14:21:52', '0', '1', 'UPDATE'), ('70', 'api_operate_type', '下线', '下线', '下线', null, '2020-06-19 14:23:39', '2020-06-19 14:23:39', '0', '1', 'OFFLINE'), ('71', 'api_operate_type', '上线', '上线', '上线', null, '2020-06-19 14:24:02', '2020-06-19 14:24:02', '0', '1', 'ONLINE'), ('72', 'api_operate_type', '删除', '删除', '删除', null, '2020-06-19 14:24:22', '2020-06-19 14:24:22', '0', '1', 'DELETE'), ('73', 'cluster_codes', '商品集群', 'Janus_Goods', '商品集群', '3', '2020-06-19 15:11:11', '2020-06-19 15:11:11', '0', '1', 'Janus_Goods'), ('74', 'biz_name', '商品业务线', 'janus_biz_goods', '商品业务线', '3', '2020-06-19 15:11:59', '2020-06-19 15:11:59', '0', '1', 'janus_biz_goods'), ('75', 'cluster_codes', '支付网关集群', 'Janus_Pay', '支付网关集群', '4', '2020-06-19 15:19:53', '2020-06-19 15:19:53', '0', '1', 'Janus_Pay'), ('76', 'biz_name', '支付业务线', 'janus_biz_pay', '支付业务线', '1', '2020-06-19 15:20:31', '2020-06-19 15:20:31', '0', '1', 'janus_biz_pay'), ('77', 'cluster_codes', '优惠网关集群', 'Janus_Discount', '优惠网关集群', '4', '2020-06-19 15:24:08', '2020-06-19 15:24:08', '0', '1', 'Janus_Discount'), ('78', 'biz_name', '优惠业务线', 'janus_biz_discount', '优惠业务线', '1', '2020-06-19 15:24:52', '2020-06-19 15:24:52', '0', '1', 'janus_biz_discount'), ('79', 'cluster_codes', '测试集群', 'Janus_Test', '测试集群', '1', '2020-06-22 20:12:31', '2020-06-22 20:12:31', '0', '1', 'Janus_Test'), ('80', 'filter_execute_place', '转发时执行', 'INVOKE', '转发时执行', '1', '2020-06-22 20:24:36', '2020-06-22 20:24:36', '0', '1', 'INVOKE'), ('81', 'filter_execute_place', '转发有结果时执行', 'OUTBOUND', '转发有结果时执行', '3', '2020-06-22 20:26:26', '2020-06-22 20:26:26', '0', '1', 'OUTBOUND'), ('82', 'filter_execute_place', '返回结果最后执行', 'AFTER_OUT', '返回结果最后执行', '4', '2020-06-22 20:27:20', '2020-06-22 20:27:20', '0', '1', 'AFTER_OUT'), ('83', 'change_apply_status', '待审核', 'WAIT_AUDIT', '待审核', null, '2020-06-19 17:56:25', '2020-06-19 19:58:53', '0', '1', 'WAIT_AUDIT'), ('84', 'change_apply_status', '已审核', 'AUDITED', '已审核', null, '2020-06-19 17:57:06', '2020-06-19 19:58:53', '0', '1', 'AUDITED'), ('85', 'change_apply_status', '发布中', 'RELEASING', '发布中', null, '2020-06-19 17:57:31', '2020-06-19 19:58:53', '1', '1', 'RELEASING'), ('86', 'change_apply_status', '已驳回', 'REJECTED', '已驳回', null, '2020-06-19 17:58:56', '2020-06-19 19:58:53', '0', '1', 'REJECTED'), ('87', 'change_apply_status', '已撤销', 'CANCELED', '已撤销', null, '2020-06-19 17:59:14', '2020-06-19 19:58:53', '0', '1', 'CANCELED'), ('88', 'change_apply_status', '发布中', 'RELEASING', '发布中', null, '2020-06-19 17:59:39', '2020-06-19 19:58:53', '0', '1', 'RELEASING'), ('89', 'change_apply_status', '已发布', 'RELEASED', '已发布', null, '2020-06-19 18:00:01', '2020-06-19 19:58:53', '0', '1', 'RELEASED'), ('90', 'release_status', '待拉出', 'WAIT_PULL', '待拉出', null, '2020-06-19 19:38:31', '2020-06-19 19:38:31', '0', '1', 'WAIT_PULL'), ('91', 'release_status', '待下发', 'WAIT_RELEASE', '待下发', null, '2020-06-19 19:39:01', '2020-06-19 19:39:01', '0', '1', 'WAIT_RELEASE'), ('92', 'release_status', '下发中', 'RELEASING', '下发中', null, '2020-06-19 19:39:26', '2020-06-19 19:39:26', '0', '1', 'RELEASING'), ('93', 'release_status', '拉出中', 'PULLING', '拉出中', null, '2020-06-19 19:39:46', '2020-06-19 19:39:46', '0', '1', 'PULLING'), ('94', 'release_status', '拉出失败', 'PULL_FAILED', '拉出失败', null, '2020-06-19 19:40:06', '2020-06-19 19:40:06', '0', '1', 'PULL_FAILED'), ('95', 'release_status', '拉出成功', 'PULL_SUCCESS', '拉出成功', null, '2020-06-19 19:40:25', '2020-06-19 19:40:25', '0', '1', 'PULL_SUCCESS'), ('96', 'release_status', '下发成功', 'RELEASE_SUCCESS', '下发成功', null, '2020-06-19 19:40:46', '2020-06-19 19:40:46', '0', '1', 'RELEASE_SUCCESS'), ('97', 'release_status', '下发失败', 'RELEASE_FAIL', '下发失败', null, '2020-06-19 19:43:16', '2020-06-19 19:43:16', '0', '1', 'RELEASE_FAIL'), ('98', 'release_status', '忽略', 'IGNORED', '忽略', null, '2020-06-19 19:43:38', '2020-06-19 19:43:38', '0', '1', 'IGNORED'), ('99', 'release_status', '完成', 'COMPLETED', '完成', null, '2020-06-19 19:43:56', '2020-06-19 19:43:56', '0', '1', 'COMPLETED'), ('100', 'api_draft_status', '编辑中', 'EDITING', '编辑中', '0', '2020-06-19 11:42:27', '2020-06-19 11:42:27', '0', '1', 'EDITING'), ('101', 'api_draft_status', '待审核', 'WAIT_AUDIT', '待审核', '0', '2020-06-19 11:42:53', '2020-06-19 11:42:53', '0', '1', 'WAIT_AUDIT'), ('102', 'api_draft_status', '已审核', 'AUDITED', '已审核', '0', '2020-06-19 11:43:35', '2020-06-19 11:43:35', '0', '1', 'AUDITED'), ('103', 'api_draft_status', '已驳回', 'REJECTED', '已驳回', '0', '2020-06-19 11:51:13', '2020-06-19 11:51:13', '0', '1', 'REJECTED'), ('104', 'api_draft_status', '已撤销', 'CANCELED', '已撤销', '0', '2020-06-19 11:51:51', '2020-06-19 11:51:51', '0', '1', 'CANCELED'), ('105', 'api_draft_status', '发布中', 'RELEASING', '发布中', '0', '2020-06-19 11:52:15', '2020-06-19 11:52:15', '0', '1', 'RELEASING'), ('106', 'api_draft_status', '已发布', 'RELEASED', '已发布', '0', '2020-06-19 11:52:40', '2020-06-19 11:52:40', '0', '1', 'RELEASED'), ('107', 'cluster_config_keys', '热加载Filter路径', 'dynamicClass', '热加载Filter路径', '3', '2020-06-29 13:57:50', '2020-06-29 13:57:50', '0', '1', 'dynamicClass'), ('108', 'cluster_config_keys', '网关InfluxDB配置', 'janusInfluxConfig', '网关InfluxDB配置', '1', '2020-07-03 17:12:27', '2020-07-03 17:12:27', '0', '1', 'janusInfluxConfig');
COMMIT;

-- ----------------------------
--  Table structure for `t_dict_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_dict_type`;
CREATE TABLE `t_dict_type` (
                               `id` int NOT NULL AUTO_INCREMENT COMMENT '数据字典分类主键',
                               `dict_name` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '数据字典分类名称',
                               `dict_code` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '数据字典分类唯一标识',
                               `status` tinyint NOT NULL DEFAULT '0' COMMENT '数据字典分类启用状态，0：启用，1：未启用',
                               `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                               `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='数据字典分类表';

-- ----------------------------
--  Records of `t_dict_type`
-- ----------------------------
BEGIN;
INSERT INTO `t_dict_type` VALUES ('1', '集群编码', 'cluster_codes', '1', '2020-05-28 10:37:31', '2020-05-28 10:37:31', '0'), ('2', '业务线', 'biz_name', '1', '2020-05-28 13:41:22', '2020-05-28 13:41:22', '0'), ('3', '集群状态', 'cluster_status', '1', '2020-05-28 13:41:46', '2020-05-29 11:37:24', '0'), ('4', 'Filter类型', 'filter_type', '1', '2020-05-29 14:00:34', '2020-05-29 14:00:34', '0'), ('5', 'filter组件类型', 'filter_module_type', '1', '2020-05-29 14:05:52', '2020-06-02 14:53:03', '0'), ('6', 'filter执行位置', 'filter_execute_place', '1', '2020-06-02 17:37:55', '2020-06-02 17:37:55', '0'), ('7', 'filter状态', 'filter_status', '1', '2020-06-03 11:24:31', '2020-06-03 11:24:31', '0'), ('8', '集群配置项', 'cluster_config_keys', '1', '2020-06-03 17:49:53', '2020-06-03 17:49:53', '0'), ('9', '集群配置类型', 'cluster_config_type', '1', '2020-06-03 17:51:13', '2020-06-03 17:51:13', '0'), ('10', '入参映射', 'ApiMapping', '1', '2020-06-06 16:52:21', '2020-06-06 16:52:21', '0'), ('11', 'api协议类型', 'api_protocol', '1', '2020-06-08 11:31:01', '2020-06-08 11:31:01', '0'), ('12', 'api方法类型', 'http_method', '1', '2020-06-08 11:35:45', '2020-06-08 11:35:45', '0'), ('13', 'api参数位置', 'api_param_position', '1', '2020-06-08 13:39:06', '2020-06-08 13:39:06', '0'), ('14', 'api参数数据类型', 'api_param_dataType', '1', '2020-06-08 13:40:43', '2020-06-08 13:40:43', '0'), ('15', 'API入参请求模式', 'api_paramTransType', '1', '2020-06-08 14:44:48', '2020-06-08 14:44:48', '0'), ('16', 'API服务协议类型', 'API_OutServiceProtocol', '1', '2020-06-08 19:38:19', '2020-06-08 19:38:19', '0'), ('17', 'API服务类型', 'API_OutServiceType', '1', '2020-06-08 19:46:06', '2020-06-08 19:46:06', '0'), ('18', '下发管理-下发类型', 'publish_ReleaseTypeEnum', '1', '2020-06-18 16:53:14', '2020-06-18 16:53:14', '0'), ('19', 'Api草稿环境状态', 'api_draft_status', '1', '2020-06-19 11:33:59', '2020-06-19 11:33:59', '0'), ('20', 'Api正式环境状态', 'api_release_status', '1', '2020-06-19 11:57:10', '2020-06-19 11:57:10', '0'), ('21', 'Api操作类型', 'api_operate_type', '1', '2020-06-19 14:20:18', '2020-06-19 14:20:18', '0'), ('22', '变更申请状态', 'change_apply_status', '1', '2020-06-19 17:54:03', '2020-06-19 17:54:03', '0'), ('23', '下发状态', 'release_status', '1', '2020-06-19 19:37:02', '2020-06-19 19:37:02', '0'), ('24', 'Api草稿环境状态', 'api_draft_status', '1', '2020-06-19 11:33:59', '2020-06-19 11:33:59', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_filter`
-- ----------------------------
DROP TABLE IF EXISTS `t_filter`;
CREATE TABLE `t_filter` (
                            `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                            `name` varchar(64) NOT NULL COMMENT 'Filter名称',
                            `is_global` tinyint NOT NULL DEFAULT '0' COMMENT '是否全局Filter 0 否 1 是',
                            `is_share` tinyint NOT NULL DEFAULT '0' COMMENT '是否所有集群共享Filter 0 独立Filter 1 共享Filter',
                            `filter_code` varchar(60) DEFAULT NULL COMMENT 'filterCode',
                            `filter_args` text COMMENT 'filter初始化时的参数',
                            `class_code` varchar(30) DEFAULT NULL COMMENT '分类code',
                            `execute_place` varchar(60) DEFAULT NULL COMMENT '执行位置',
                            `description` text COMMENT '描述',
                            `filter_order` int DEFAULT '0' COMMENT 'Filter的执行顺序',
                            `status` varchar(30) DEFAULT NULL COMMENT '状态',
                            `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
                            `modifier` varchar(64) DEFAULT NULL COMMENT '修改者',
                            `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                            `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='filter表';

-- ----------------------------
--  Records of `t_filter`
-- ----------------------------
BEGIN;
INSERT INTO `t_filter` VALUES ('1', 'AuthToken认证', '0', '1', 'AuthToken', null, 'public', 'PRE_IN', 'AuthToken认证，对网关收到的请求进行AuthToken认证', '1', '1', null, null, '2020-06-22 20:20:29', '2020-06-23 11:34:50', '0'), ('2', 'Add请求Header', '0', '1', 'AddRequestHeader', null, 'public', 'INBOUND', '该Filter用于对具体API请求添加Header。配置的参数为args: 一个map，自定义key/value;如：X-Request-Red: Red  ', '1', '1', null, null, '2020-06-24 18:05:07', '2020-06-24 18:05:07', '0'), ('3', 'Remove请求Header', '0', '1', 'RemoveRequestHeader', null, 'public', 'INBOUND', '用于移除自定义响应头。Filter的参数是一个map，自定义key/value;如：Access-Control-Allow-Origin: *', '1', '1', null, null, '2020-06-24 18:07:06', '2020-06-24 18:07:06', '0'), ('4', '添加响应Header', '0', '1', 'AddResponseHeader', null, 'public', 'OUTBOUND', '该Filter用于添加自定义响应头。Filter参数args: 一个map，自定义key/value;如：Access-Control-Allow-Origin: *。', '1', '1', null, null, '2020-06-24 18:07:10', '2020-06-24 18:10:19', '0'), ('5', '移除响应Header', '0', '1', 'RemoveResponseHeader', null, 'public', 'OUTBOUND', '该Filter用于移除请求Response中的Header。', '1', '1', null, null, '2020-06-24 18:12:31', '2020-06-24 18:12:31', '0'), ('6', 'MockAPI返回值', '1', '1', 'MockResponse', null, 'public', 'PRE_IN', '当通过网关对外暴露的API，进行开发时可以在网关上进行API设计设置对应的返回值。当服务需要紧急降级时，可以返回合理的MockResponse。', '1', '1', null, null, '2020-06-24 18:14:52', '2020-06-24 18:14:52', '0'), ('7', '负载均衡Filter', '1', '1', 'LoadBalanceInvoke', null, 'system', 'INVOKE', 'Janus网关内置，用于负载均衡处理。', '1', '1', null, null, '2020-06-28 11:30:17', '2020-06-28 11:40:19', '0'), ('8', 'Response返回Filter', '1', '1', 'WriteToClient', null, 'system', 'AFTER_OUT', '网关将请求转发处理之后，统一把返回值处理返回给客户端', '1', '1', null, null, '2020-06-28 11:34:23', '2020-06-28 11:34:23', '0'), ('9', '熔断Filter', '1', '1', 'CircuitBreaker', null, 'out', 'PRE_IN', '当流量过载时，用于网关限流熔断处理.', '1', '1', null, null, '2020-06-28 11:37:39', '2020-06-28 11:37:39', '0'), ('10', '重试Filter', '0', '1', 'Retry', null, 'system', 'INVOKE', '用于网关请求重试', '1', '1', null, null, '2020-06-28 11:40:03', '2020-06-28 11:40:03', '0'), ('11', '直接转发Filter', '1', '1', 'HttpInvoke', null, 'system', 'INVOKE', 'HttpInvokeFiler是一个URL直接转发到另外一个URL', '1', '1', null, null, '2020-06-29 14:06:13', '2020-06-29 14:06:13', '0'), ('12', '去前缀Filter', '0', '1', 'StripPrefix', null, 'public', 'INBOUND', 'StripPrefixFilter根据位置去前缀的Filter', '1', '1', null, null, '2020-06-29 14:40:44', '2020-06-29 14:40:44', '0'), ('13', 'URL映射Filter', '0', '1', 'PathMappingFilter', null, 'system', 'INBOUND', 'url 的Mapping Filter，主要用于一个URL映射为另外一个URL', '1', '1', null, null, '2020-06-29 14:56:00', '2020-07-07 12:17:26', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_instance`
-- ----------------------------
DROP TABLE IF EXISTS `t_instance`;
CREATE TABLE `t_instance` (
                              `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
                              `host` varchar(50) NOT NULL DEFAULT '' COMMENT 'ip:端口',
                              `cluster_code` varchar(50) DEFAULT NULL COMMENT '集群编码',
                              `status` tinyint DEFAULT NULL COMMENT '实例状态：0 在线 1 下线',
                              `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
                              `modifier` varchar(64) DEFAULT NULL COMMENT '修改者',
                              `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                              `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                              `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='网关实例表';

-- ----------------------------
--  Records of `t_instance`
-- ----------------------------
BEGIN;
INSERT INTO `t_instance` VALUES ('1', '192.168.46.246:8081', 'Janus_Test', '0', 'jin.xu', 'jin.xu', '2020-06-19 15:01:40', '2020-07-02 11:26:48', '0'), ('2', '192.168.47.15:8081', 'Janus_Test', '0', 'jin.xu', 'jin.xu', '2020-07-02 16:46:23', '2020-07-02 16:46:23', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键Id',
                          `parent_id` bigint DEFAULT NULL COMMENT '菜单父Id',
                          `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜单名称',
                          `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'url',
                          `roles` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
                          `sort` int NOT NULL DEFAULT '1' COMMENT '菜单排序',
                          `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标',
                          `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
                          `menu_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单key',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='菜单表';

-- ----------------------------
--  Records of `t_menu`
-- ----------------------------
BEGIN;
INSERT INTO `t_menu` VALUES ('1', null, '系统菜单', null, null, '1', null, '2019-07-18 11:46:54', '2020-05-27 11:16:47', '0', 'root'), ('2', '1', '我的网关', '/mygw', 'USER,ADMIN', '4', 'icon-qitalingyu', '1970-01-02 00:00:00', '2020-05-25 14:08:36', '0', 'mygw'), ('3', '1', '网关管理', '/gwMgmt', 'USER,ADMIN', '1', 'icon-yingyong', '1970-01-02 00:00:00', '2020-05-26 18:09:18', '0', 'gwMgmt'), ('4', '1', '系统管理', '/system', 'USER,ADMIN', '1', 'icon-xitong', '1970-01-02 00:00:00', '2019-07-18 11:47:07', '0', 'system'), ('5', '1', '网关监控', '/gwMornior', 'USER,ADMIN', '3', 'icon-yewuguanli', '2019-07-08 16:29:09', '2020-05-25 14:08:24', '0', 'gwMornior'), ('6', '2', '我的集群', '/myCluster', 'USER,ADMIN', '4', 'icon-quyuguanli', '2019-07-09 16:21:06', '2020-06-19 15:27:46', '0', 'myCluster'), ('7', '1', '集群视图', '/desktop', '', '6', 'icon-yewuliu', '2019-07-11 11:10:29', '2020-06-04 15:29:57', '0', '/desktop'), ('8', '4', '菜单管理', '/system/menu', 'USER,ADMIN', '1', 'icon-caidanguanli', '2019-07-11 13:35:15', '2020-06-19 15:26:54', '0', '/system/menu'), ('9', '1', 'DashBoard', '/dashboard', 'USER,ADMIN', '7', 'icon-shouye', '1970-01-02 00:00:00', '2020-05-29 13:51:51', '0', 'Dashboard'), ('10', '4', '用户管理', '/system/user', 'USER,ADMIN', '1', 'icon-yonghuguanli', '2019-07-29 17:45:36', '2020-06-19 15:26:36', '0', '/system/user'), ('11', '4', '元数据管理', '/system/metaData', 'USER,ADMIN', '0', 'icon-yuanshujupeizhi', '2019-09-03 17:34:34', '2020-05-27 19:32:10', '0', '/system/metaData'), ('12', '2', '', '/gwRoute', 'USER,ADMIN', '2', 'icon-xitong', '2019-12-09 15:35:59', '2020-05-29 13:51:45', '1', 'gwRoute'), ('13', '3', '网关审计', '/gatewayManage/audit', 'ADMIN,USER', '1', 'icon-code', '2020-03-25 10:23:18', '2020-06-28 10:55:24', '0', 'gatewayManage/audit'), ('14', '3', '集群管理', '/gatewayManage/clusterManage', 'ADMIN,USER', '3', '0', '2020-05-07 10:58:06', '2020-05-27 14:26:53', '0', '/gatewayManage/clusterManage'), ('15', '3', '路由管理', '/routeMgmt', 'ADMIN,USER', '2', null, '2020-05-07 10:58:58', '2020-05-25 14:09:30', '0', 'routeMgmt'), ('16', '3', 'Filter管理', '/gatewayManage/filterManage', 'ADMIN,USER', '2', 'icon-code', '2020-05-07 10:59:45', '2020-06-01 15:42:18', '0', '/gatewayManage/filterManage'), ('17', '5', '实例监控', '/instanceMornior', 'ADMIN,USER', '2', '“”', '2020-05-25 14:49:39', '2020-06-29 12:14:47', '0', 'instanceMornior'), ('18', '4', '角色管理', '/system/role', 'ADMIN,USER', '1', 'icon-yonghuguanli', '2020-05-26 14:57:47', '2020-06-06 11:13:14', '0', '/system/role'), ('19', '3', '集群配置', '/gatewayManage/clusterConfig', 'ADMIN,USER', '1', '0', '2020-05-27 11:55:51', '2020-06-03 13:50:45', '0', '/gatewayManage/clusterConfig'), ('20', '1', '工作台', '/workshop', 'ADMIN,USER', '5', null, '2020-05-27 14:22:40', '2020-05-27 14:32:00', '0', 'workshop'), ('21', '20', '申请管理', '/changes/apply-manage', 'ADMIN,USER', '1', '1', '2020-05-27 14:25:57', '2020-06-22 21:01:07', '0', '/changes/apply-manage'), ('22', '41', '审批变更', '/changes/approvex', 'ADMIN,USER', '1', '1', '2020-05-27 14:27:33', '2020-06-16 18:21:17', '1', '/changes/approvex'), ('23', '41', '下发变更', '/changes/release', 'ADMIN,USER', '1', '1', '2020-05-27 14:30:35', '2020-06-16 11:17:41', '1', '/changes/release'), ('24', '20', '下发管理', '/changes/publish-manage', 'ADMIN,USER', '1', '“”', '2020-05-27 14:31:23', '2020-06-22 21:01:00', '0', '/changes/publish-manage'), ('25', '2', 'API管理', '/myGate/APIManage', '', '5', 'icon-shouye', '2020-05-29 13:49:00', '2020-06-24 11:46:20', '0', '/myGate/APIManage'), ('27', '5', '告警处理', '/monitor/alarm-manage', '', '1', 'yongpeng', '2020-06-09 10:48:25', '2020-06-28 10:56:55', '0', 'alarmMgmt'), ('28', '2', '我的告警', '/myAlarm', '', '1', '\"\"', '2020-06-15 12:04:59', '2020-06-15 12:04:59', '0', 'myAlarm'), ('29', '2', '网关实例', '/myGate/instance', '', '1', '\"\"', '2020-06-15 12:06:16', '2020-06-30 10:58:43', '0', 'gwInstance');
COMMIT;

-- ----------------------------
--  Table structure for `t_out_service_draft`
-- ----------------------------
DROP TABLE IF EXISTS `t_out_service_draft`;
CREATE TABLE `t_out_service_draft` (
                                       `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                       `api_draft_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT 't_api_draft.id',
                                       `cluster_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所属集群编码',
                                       `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服务名称',
                                       `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服务类型',
                                       `protocol` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服务所用协议',
                                       `uri` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服务uri',
                                       `path` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请求路径',
                                       `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'http请求方法',
                                       `param_trans_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '入参传递模式',
                                       `param_trans_config` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '入参传递映射配置',
                                       `response_trans_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '响应传递模式',
                                       `response_trans_config` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '响应传递映射配置',
                                       `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人用户id',
                                       `update_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '修改人用户id',
                                       `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                       PRIMARY KEY (`id`),
                                       UNIQUE KEY `uk_clusterCode_apiReleaseId_name` (`cluster_code`,`api_draft_id`,`name`),
                                       KEY `idx_clusterCode` (`cluster_code`(20)),
                                       KEY `idx_name` (`name`(20)),
                                       KEY `idx_path` (`path`(20)),
                                       KEY `idx_create_user` (`create_user`(20)),
                                       KEY `idx_update_user` (`update_user`(20)),
                                       KEY `idx_update_time` (`create_time`),
                                       KEY `idx_create_time` (`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='api后端服务配置变更表';

-- ----------------------------
--  Records of `t_out_service_draft`
-- ----------------------------
BEGIN;
INSERT INTO `t_out_service_draft` VALUES ('1', '1', 'Janus_Test', 'janus-admin', 'DISCOVERY', 'REST', 'lb://janus-admin', '/admin/cluster/page', 'GET', '', '{\n	\"paramMappings\":[]\n}', '', null, '', '', '2020-06-22 20:17:08', '2020-06-22 20:17:08'), ('2', '2', 'Janus_Test', '后端转发服务', 'STRAIGHT_FORWARD', 'REST', '[{\"host\":\"127.0.0.1\",\"port\":\"8084\"}]', '/admin/menu/manage/allMenu', 'GET', '', '{\n	\"paramMappings\":[]\n}', '', null, '', '', '2020-07-04 10:35:12', '2020-07-04 10:35:12'), ('3', '3', 'Janus_Test', '查询菜单接口', 'LOAD_BALANCE', 'REST', '[{\"host\":\"127.0.0.1\",\"port\":\"8084\"},{\"host\":\"admin.qingcloud.net\",\"port\":\"80\"}]', '/admin/menu/manage/allMenu', 'GET', '', '{\n	\"paramMappings\":[]\n}', '', null, '', '', '2020-07-14 13:45:52', '2020-07-14 13:45:52');
COMMIT;

-- ----------------------------
--  Table structure for `t_out_service_release`
-- ----------------------------
DROP TABLE IF EXISTS `t_out_service_release`;
CREATE TABLE `t_out_service_release` (
                                         `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                         `api_release_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT 't_api_release.id',
                                         `cluster_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所属集群编码',
                                         `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服务名称',
                                         `type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服务类型',
                                         `protocol` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服务所用协议',
                                         `uri` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '服务uri',
                                         `path` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '请求路径',
                                         `method` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT 'http请求方法',
                                         `param_trans_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '入参传递模式',
                                         `param_trans_config` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '入参传递映射配置',
                                         `response_trans_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '响应传递模式',
                                         `response_trans_config` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT '响应传递映射配置',
                                         `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '创建人用户id',
                                         `update_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '修改人用户id',
                                         `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                         PRIMARY KEY (`id`),
                                         UNIQUE KEY `uk_clusterCode_apiReleaseId_name` (`cluster_code`,`api_release_id`,`name`),
                                         KEY `idx_clusterCode` (`cluster_code`(20)),
                                         KEY `idx_name` (`name`(20)),
                                         KEY `idx_path` (`path`(20)),
                                         KEY `idx_create_user` (`create_user`(20)),
                                         KEY `idx_update_user` (`update_user`(20)),
                                         KEY `idx_update_time` (`create_time`),
                                         KEY `idx_create_time` (`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='api后端服务配置表';

-- ----------------------------
--  Records of `t_out_service_release`
-- ----------------------------
BEGIN;
INSERT INTO `t_out_service_release` VALUES ('1', '1', 'Janus_Test', 'janus-admin', 'DISCOVERY', 'lb://sc', 'janus-admin', '/admin/menu/manage/allMenu', 'GET', '', '{\n	\"paramMappings\":[]\n}', '', null, '', '', '2020-06-24 20:06:27', '2020-07-14 16:48:28'), ('2', '2', 'Janus_Test', '后端转发服务', 'STRAIGHT_FORWARD', 'http', '[{\"host\":\"127.0.0.1\",\"port\":\"8084\"}]', '/admin/menu/manage/allMenu', 'GET', '', '{\n	\"paramMappings\":[]\n}', '', null, '', '', '2020-07-04 10:36:34', '2020-07-14 16:48:36'), ('3', '3', 'Janus_Test', '查询菜单接口', 'LOAD_BALANCE', 'lb://hosts', '[{\"host\":\"127.0.0.1\",\"port\":\"8084\"},{\"host\":\"admin.qingcloud.net\",\"port\":\"80\"}]', '/admin/menu/manage/allMenu', 'GET', '', '{\n	\"paramMappings\":[]\n}', '', null, '', '', '2020-07-14 16:11:12', '2020-07-14 16:48:49');
COMMIT;

-- ----------------------------
--  Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
                                `id` int unsigned NOT NULL AUTO_INCREMENT,
                                `permission` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限字符串：Shiro WildcardPermission格式',
                                `desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
                                `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
                                `group` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '权限组',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `uk_permission` (`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- ----------------------------
--  Table structure for `t_publish`
-- ----------------------------
DROP TABLE IF EXISTS `t_publish`;
CREATE TABLE `t_publish` (
                             `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
                             `apply_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '下发申请的Id',
                             `type` varchar(32) NOT NULL COMMENT '下发类型',
                             `publisher` varchar(50) NOT NULL DEFAULT '' COMMENT '下发人',
                             `status` varchar(32) NOT NULL COMMENT '状态',
                             `cluster_code` varchar(50) NOT NULL COMMENT '集群编码',
                             `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
                             `modifier` varchar(64) DEFAULT NULL COMMENT '修改者',
                             `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                             `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
                             PRIMARY KEY (`id`),
                             KEY `idx_apply_id` (`apply_id`),
                             KEY `idx_status` (`status`(20)),
                             KEY `idx_cluster_code` (`cluster_code`(20)),
                             KEY `idx_gmt_create` (`gmt_create`),
                             KEY `idx_gmt_modified` (`gmt_modified`),
                             KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='下发信息表';

-- ----------------------------
--  Records of `t_publish`
-- ----------------------------
BEGIN;
INSERT INTO `t_publish` VALUES ('1', '1', 'GRAY', 'jin.xu', 'COMPLETED', 'Janus_Test', 'jin.xu', 'jin.xu', '2020-06-24 20:04:18', '2020-06-24 20:06:26', '0'), ('2', '2', 'FULL', 'jin.xu', 'COMPLETED', 'Janus_Test', 'jin.xu', 'jin.xu', '2020-07-04 10:36:17', '2020-07-04 10:36:33', '0'), ('3', '3', 'FULL', 'jin.xu', 'COMPLETED', 'Janus_Test', 'jin.xu', 'jin.xu', '2020-07-14 16:10:51', '2020-07-14 16:11:11', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_publish_ip`
-- ----------------------------
DROP TABLE IF EXISTS `t_publish_ip`;
CREATE TABLE `t_publish_ip` (
                                `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
                                `publish_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT 'publish PK',
                                `ip` varchar(50) NOT NULL DEFAULT '' COMMENT '记录灰度下发IP',
                                `type` varchar(32) NOT NULL COMMENT '下发类型',
                                `status` varchar(32) NOT NULL COMMENT '状态',
                                `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
                                `modifier` varchar(64) DEFAULT NULL COMMENT '修改者',
                                `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `uk_publishId_ip` (`publish_id`,`ip`),
                                KEY `idx_ip` (`ip`(20)),
                                KEY `idx_gmt_create` (`gmt_create`),
                                KEY `idx_gmt_modified` (`gmt_modified`),
                                KEY `idx_is_deleted` (`is_deleted`),
                                KEY `idx_publishId_type` (`publish_id`,`type`(20))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='下发对应IP表';

-- ----------------------------
--  Records of `t_publish_ip`
-- ----------------------------
BEGIN;
INSERT INTO `t_publish_ip` VALUES ('1', '1', '127.0.0.1:8081', 'GRAY', 'COMPLETED', 'jin.xu', 'jin.xu', '2020-06-24 20:04:18', '2020-06-24 20:06:21', '0'), ('2', '2', '192.168.46.246:8081', 'FULL', 'IGNORED', 'jin.xu', 'jin.xu', '2020-07-04 10:36:17', '2020-07-04 10:36:29', '0'), ('3', '2', '192.168.47.15:8081', 'FULL', 'IGNORED', 'jin.xu', 'jin.xu', '2020-07-04 10:36:18', '2020-07-04 10:36:30', '0'), ('4', '3', '192.168.46.246:8081', 'FULL', 'IGNORED', 'jin.xu', 'jin.xu', '2020-07-14 16:10:51', '2020-07-14 16:11:08', '0'), ('5', '3', '192.168.47.15:8081', 'FULL', 'IGNORED', 'jin.xu', 'jin.xu', '2020-07-14 16:10:51', '2020-07-14 16:11:09', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_resource_lock`
-- ----------------------------
DROP TABLE IF EXISTS `t_resource_lock`;
CREATE TABLE `t_resource_lock` (
                                   `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
                                   `resource_type` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资源类型',
                                   `resource_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '资源ID',
                                   `operation` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '操作说明',
                                   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                   PRIMARY KEY (`id`),
                                   UNIQUE KEY `uk_resourceType_resourceId` (`resource_type`,`resource_id`),
                                   KEY `idx_update_time` (`update_time`),
                                   KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='资源锁';

-- ----------------------------
--  Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
                          `id` int unsigned NOT NULL AUTO_INCREMENT,
                          `key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色',
                          `desc` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '描述',
                          `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
                          `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色名称',
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `uk_role` (`key`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- ----------------------------
--  Records of `t_role`
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES ('1', 'USER', '普通用户', '2019-07-04 10:10:24', '2019-07-31 10:13:15', '0', '普通用户'), ('2', 'ADMIN', '管理员用户', '2019-07-04 10:10:24', '2019-07-30 14:58:24', '0', '管理员'), ('3', 'BLACK', '黑名单用户，无任何权限', '2019-07-04 10:39:27', '2019-07-30 14:58:33', '0', '黑名单'), ('4', 'APPROVE', '审批角色', '2020-05-12 17:30:55', '2020-05-29 14:27:07', '0', '审批用户'), ('5', 'PUBLISH', '下发角色', '2020-05-29 14:27:45', '2020-05-29 14:27:45', '0', '下发角色用户');
COMMIT;

-- ----------------------------
--  Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
                                     `id` int unsigned NOT NULL AUTO_INCREMENT,
                                     `role` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色',
                                     `permission_id` int unsigned NOT NULL COMMENT '权限ID',
                                     `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                     `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
                                     PRIMARY KEY (`id`),
                                     UNIQUE KEY `uk_role_permission` (`role`,`permission_id`),
                                     KEY `k_permission` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='角色权限表';

-- ----------------------------
--  Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL COMMENT '英文名唯一',
                          `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL COMMENT '中文名',
                          `password` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_bin NOT NULL COMMENT '密码',
                          `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_bin DEFAULT NULL,
                          `status` tinyint(1) NOT NULL COMMENT '是否启用',
                          `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
--  Records of `t_user`
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES ('1', 'jin.xu', 'jin.xu', 'UUKHSDDI5KPA43A8VL06V0TU2', 'jin.xu@qq.com', '1', '2020-06-19 14:36:42', '2023-12-29 22:11:58', '0');
COMMIT;

-- ----------------------------
--  Table structure for `t_user_roles`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_roles`;
CREATE TABLE `t_user_roles` (
                                `id` int NOT NULL AUTO_INCREMENT,
                                `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                                `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `ix_auth_username` (`username`,`role`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
--  Records of `t_user_roles`
-- ----------------------------
BEGIN;
INSERT INTO `t_user_roles` VALUES ('1', 'jin.xu', 'USER', '2020-06-19 01:36:42', '2020-06-23 10:15:53', '0');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
