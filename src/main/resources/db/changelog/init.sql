SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for crs_patient_info
-- ----------------------------
DROP TABLE IF EXISTS `crs_patient_info`;
CREATE TABLE `crs_patient_info` (
  `pi_pk` bigint(20) NOT NULL auto_increment COMMENT '主键',
  `pi_name` varchar(64) default NULL COMMENT '受试者姓名缩写',
  `pi_code` varchar(64) default NULL COMMENT '受试者编号',
  `pi_hospital` varchar(64) default NULL COMMENT '医院名称',
  `pi_start_time` bigint(20) default NULL COMMENT '入住时间',
  `pi_sex` varchar(64) default NULL COMMENT '性别',
  `pi_age` int(11) default NULL COMMENT '年龄',
  `pi_patient_id` varchar(64) default NULL COMMENT '受试者病例号',
  `pi_pro_pk` bigint(20) default NULL COMMENT '项目主键',
  `pi_extend_info` text COMMENT '扩展信息',
  `pi_memo` text COMMENT '备注',
  `pi_create_user` bigint(20) default NULL COMMENT '记录创建时间',
  `pi_create_time` bigint(20) default NULL COMMENT '记录创建人',
  `pi_primary_lesion` varchar(64) default NULL COMMENT '原发病灶',
  `pi_random_number` int(11) default NULL COMMENT '随机号',
  `pi_random_group` int(11) default NULL COMMENT '随机组',
  `pi_random_group_json` text COMMENT '随机分组信息',
  `pi_clinical_trial_code` varchar(64) default NULL COMMENT '试验中心编号',
  PRIMARY KEY  (`pi_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='受试者';
-- ----------------------------
-- Table structure for crs_project
-- ----------------------------
DROP TABLE IF EXISTS `crs_project`;
CREATE TABLE `crs_project` (
  `pro_pk` bigint(20) NOT NULL auto_increment COMMENT '项目主键',
  `pro_name` varchar(256) default NULL COMMENT '名称',
  `pro_batch_number` varchar(64) default NULL COMMENT '临床实验批号',
  `pro_bosshead` varchar(64) default NULL COMMENT '负责人',
  `pro_clinical_trial_bosshead` varchar(64) default NULL COMMENT '临床实验负责人',
  `pro_start_time` bigint(20) default NULL COMMENT '启动时间',
  `pro_end_time` bigint(20) default NULL COMMENT '预计完成时间',
  `pro_patient_count` int(11) default NULL COMMENT '临床实验病例',
  `pro_group_count` int(11) default NULL COMMENT '分组数',
  `pro_factor_level` int(11) default NULL COMMENT '因素水平',
  `pro_multicenter_flag` int(11) default NULL COMMENT '多中心标志',
  `pro_group_method` int(11) default NULL COMMENT '分组选择',
  `pro_clinical_trial_organization` text COMMENT '临床实验机构',
  `pro_create_time` bigint(20) default NULL COMMENT '项目创建时间',
  `pro_create_user` bigint(20) default NULL COMMENT '项目创建人',
  `pro_memo` text COMMENT '备注',
  `pro_group_state` int(11) default NULL COMMENT '分组状态',
  `pro_clinical_trial_code` varchar(64) default NULL COMMENT '实验中心编号',
  PRIMARY KEY  (`pro_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='项目表';

-- ----------------------------
-- Table structure for crs_static_random_number
-- ----------------------------
DROP TABLE IF EXISTS `crs_static_random_number`;
CREATE TABLE `crs_static_random_number` (
  `srn_pk` bigint(20) NOT NULL auto_increment COMMENT '主键',
  `srn_pro_pk` bigint(20) default NULL COMMENT '项目主键',
  `srn_random_number` int(11) default NULL COMMENT '随机号码',
  `srn_group` int(11) default NULL COMMENT '随机分组',
  `srn_use_flag` int(11) default NULL COMMENT '使用标志',
  `srn_use_time` bigint(20) default NULL COMMENT '使用时间',
  `srn_memo` text COMMENT '备注',
  PRIMARY KEY  (`srn_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='静态随机号码表';

-- ----------------------------
-- Records of crs_static_random_number
-- ----------------------------

-- ----------------------------
-- Table structure for crs_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `crs_sys_user`;
CREATE TABLE `crs_sys_user` (
  `su_pk` bigint(20) NOT NULL auto_increment COMMENT '主键',
  `su_name` varchar(64) default NULL COMMENT '姓名',
  `su_login_name` varchar(64) default NULL COMMENT '登录名',
  `su_passwd` varchar(64) default NULL COMMENT '密码',
  `su_role` int(11) default NULL COMMENT '角色',
  `su_right_text` text COMMENT '权限值',
  PRIMARY KEY  (`su_pk`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of crs_sys_user
-- ----------------------------
INSERT INTO `crs_sys_user` VALUES ('1', '管理员', 'admin', '83ed4b68564a719e5de78b9537c8a0875e297701', '1', null);


DROP TABLE IF EXISTS `yxs_user`;
CREATE TABLE `yxs_user`  (
  `us_pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `us_openid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'openid',
  `us_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `us_create_time` bigint(20) NULL DEFAULT NULL COMMENT '创建时间',
  `us_update_time` bigint(20) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`us_pk`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户测试' ROW_FORMAT = Dynamic;
