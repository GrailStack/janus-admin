/*
Janus Server集群表
*/
CREATE TABLE `t_cluster` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '集群Id',
  `code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '集群编码',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '集群中文名称',
  `owner_name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '负责人姓名',
  `biz_name` varchar(500) DEFAULT '' COMMENT '业务名称,包括所有父级业务名称',
  `owner_id` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '负责人Id',
  `description` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '集群描述',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_share` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否共享集群 0 独立集群 1 共享集群',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='集群表';

/**
集群用户表,控制集群的操作权限
**/
CREATE TABLE `t_cluster_user` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `cluster_code` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '集群编码',
  `user_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='集群用户中间表';

/*
t_filter
网关的Filter表
**/
CREATE TABLE `t_filter` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(64) NOT NULL COMMENT 'Filter名称',
  `is_global` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否全局Filter 0 否 1 是',
  `is_share` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否所有集群共享Filter 0 独立Filter 1 共享Filter',
  `filter_args` text COMMENT 'filter初始化时的参数',
  `filter_order` int(11) DEFAULT '0' COMMENT 'Filter的执行顺序',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改者',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='filter表';


/**
集群配置表
**/
CREATE TABLE `t_cluster_config` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cluster_code` varchar(50) NOT NULL COMMENT '集群编码',
  `config_key` varchar(255) NOT NULL COMMENT '配置key',
  `config_value` varchar(255) NOT NULL COMMENT '配置Value',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
   `modifier` varchar(64) DEFAULT NULL COMMENT '修改者',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='集群配置表';



/*
集群Filter中间件表
**/
CREATE TABLE `t_cluster_filter` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cluster_code` varchar(50) NOT NULL COMMENT '集群编码',
  `filter_id` int(11) NOT NULL COMMENT 'Filter的唯一Id',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='集群filter中间表';


/**
 网关配置下发申请表
**/
CREATE TABLE `t_apply` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `cluster_code` varchar(50) NOT NULL COMMENT '集群编码',
  `description` varchar(200) NOT NULL DEFAULT '' COMMENT '申请说明',
  `request` mediumtext NOT NULL COMMENT '请求入参',
  `changes` mediumtext NOT NULL COMMENT '变更内容',
  `creator` varchar(64) DEFAULT NULL COMMENT '申请人',
  `approver` varchar(64) DEFAULT '' COMMENT '审批人',
  `publisher` varchar(64) DEFAULT '' COMMENT '下发人',
  `status` varchar(32) NOT NULL COMMENT '状态',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`(20)),
  KEY `idx_cluster_code` (`cluster_code`(20)),
  KEY `idx_creator` (`creator`(20)),
  KEY `idx_approver` (`approver`(20)),
  KEY `idx_publisher` (`publisher`(20)),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET = utf8 COMMENT='网关配置下发申请表';

/**
网关配置下发表
**/
CREATE TABLE `t_publish` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `apply_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '下发申请的Id',
  `type` varchar(32) NOT NULL COMMENT '下发类型',
  `publisher` varchar(50) NOT NULL DEFAULT '' COMMENT '下发人',
  `status` varchar(32) NOT NULL COMMENT '状态',
  `cluster_code` varchar(50) NOT NULL COMMENT '集群编码',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改者',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
  PRIMARY KEY (`id`),
  KEY `idx_apply_id` (`apply_id`),
  KEY `idx_status` (`status`(20)),
  KEY `idx_cluster_code` (`cluster_code`(20)),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下发信息表';

/**
网关灰度下发表
**/
CREATE TABLE `t_publish_ip` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `publish_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT 'publish PK',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT '记录灰度下发IP',
  `type` varchar(32) NOT NULL COMMENT '下发类型',
  `status` varchar(32) NOT NULL COMMENT '状态',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改者',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 未删除 1 已删除',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uk_publishId_ip` (`publish_id`, `ip`),
  KEY `idx_ip` (`ip`(20)),
  KEY `idx_publishId_type` (`publish_id`, `type`(20)),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='下发对应IP表';


/*
Janus Server请求
Janus Admin的记录表
**/
CREATE TABLE `t_request_log` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '唯一id',
  `cluster_code` int(11) NOT NULL COMMENT '所属集群编码',
  `ip` varchar(50) NOT NULL DEFAULT '' COMMENT 'ip地址',
  `content` text COMMENT '请求的内容',
  `is_success` int(11) NOT NULL COMMENT '是否成功，0:失败  1:成功',
  `time_penalty` int(11) NOT NULL COMMENT '处理耗时，毫秒',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0 否 1 已经删除',
  PRIMARY KEY (`id`),
  INDEX `change_id` (`change_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT='server请求Admin的日志';

/**
Admin后台操作日志表
**/
CREATE TABLE `t_audit_log` (
  `id` int(11) NOT NULL auto_increment COMMENT '操作日志id',
  `username` varchar(20) default NULL COMMENT '操作人',
  `method` varchar(20) default NULL COMMENT '请求方式',
  `cluster_code` varchar(50) default NULL COMMENT '集群编码',
  `cluster_name` varchar(50) default NULL COMMENT '集群名称',
  `url` varchar(50) default NULL COMMENT '请求路径',
  `ip` varchar(50) default NULL COMMENT 'IP地址',
  `start_time` datetime default NULL COMMENT '操作时间',
  `time_penalty` int default NULL COMMENT '',
  `status` tinyint(2) default NULL COMMENT '操作描述（1:执行成功、2:执行失败）',
  `params` text default null comment '请求参数',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8  COMMENT='admin操作人审计日志';

CREATE TABLE `t_api_filter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `api_id` varchar(50) NOT NULL COMMENT 'api Id',
  `filter_id` varchar(50) NOT NULL COMMENT '该表逻辑主键',
  `cluster_code` varchar(50) default NULL COMMENT '集群编码',
  `filter_name` varchar(255) NOT NULL COMMENT 'Filter中文名',
  `filter_code` varchar(255) NOT NULL COMMENT 'Filter类名',
  `filter_param` text DEFAULT NULL COMMENT 'filter参数,json类型',
  `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(64) DEFAULT NULL COMMENT '修改者',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8  COMMENT='API和Filter关联表';



