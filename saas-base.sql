/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : saas-base

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 25/11/2022 11:03:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ent_dict_config
-- ----------------------------
DROP TABLE IF EXISTS `ent_dict_config`;
CREATE TABLE `ent_dict_config`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `company_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业编号',
  `item_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编号',
  `item_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `status` smallint(6) NULL DEFAULT NULL COMMENT '停用:0,启用:1',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典项表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ent_dict_config
-- ----------------------------
INSERT INTO `ent_dict_config` VALUES ('aef73f02-2b39-4381-b89f-9ded66bf8fe1', 'JXSG20221121c85d', 'infomationType', '资讯类型', 1, '发表文章所属类型', 'adminGQ', '2022-11-22 08:20:50', 'adminGQ', '2022-11-22 08:21:23');

-- ----------------------------
-- Table structure for ent_dict_item_config
-- ----------------------------
DROP TABLE IF EXISTS `ent_dict_item_config`;
CREATE TABLE `ent_dict_item_config`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `company_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业编号',
  `item_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编号',
  `detail_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '明细code',
  `detail_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典名称',
  `status` smallint(6) NULL DEFAULT NULL COMMENT '停用:0,启用:1,删除:2',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典明细表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ent_dict_item_config
-- ----------------------------
INSERT INTO `ent_dict_item_config` VALUES ('6bfe722f-bf7b-451d-85d0-f3697679b7ce', 'JXSG20221121c85d', 'infomationType', 'InfomationJoke', '笑话', 1, '资讯类型之笑话', '超级管理员', '2022-11-22 08:23:54', '超级管理员', '2022-11-22 08:30:25');

-- ----------------------------
-- Table structure for ent_log
-- ----------------------------
DROP TABLE IF EXISTS `ent_log`;
CREATE TABLE `ent_log`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `login_account` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账号',
  `company_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业编码',
  `company_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `method` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `method_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法描述',
  `method_args` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '方法参数',
  `operate_time` datetime(0) NULL DEFAULT NULL COMMENT '登录时间',
  `operate_ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `operate_type` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型（0-登录日志，1-操作日志）',
  `terminal_type` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '终端(0-pc端，1-安卓，2-ios)',
  `status` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态(0成功1异常)',
  `status_code` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态码',
  `messages` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求返回描述',
  `is_delete` int(11) NULL DEFAULT NULL COMMENT '是否删除',
  `exception_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常编码',
  `exception_description` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常描述',
  `exception_stack_msg` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常堆栈',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ent_menu
-- ----------------------------
DROP TABLE IF EXISTS `ent_menu`;
CREATE TABLE `ent_menu`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `company_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属企业',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `menu_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sys_menu主键',
  `module_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'sys_module主键',
  `parent_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单父级id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ent_menu
-- ----------------------------
INSERT INTO `ent_menu` VALUES ('12703580-a7bb-431c-bf08-1e4e656b48b4', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', '69b60731-6b35-423f-a52a-bb524fe0ec09', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'e3a1972b-5637-4e01-b080-670d555cf26f');
INSERT INTO `ent_menu` VALUES ('1cf74031-75a5-47c5-8291-2a83e82bf426', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', 'c0bd9ae4-6375-462f-85c0-526aba1c486a', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'a4a3e7e0-6252-4a27-a281-80684d50882e');
INSERT INTO `ent_menu` VALUES ('1de1f942-a8df-48b2-b314-a8e4f8b931cc', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', 'fe98249c-0d01-4ecf-85a3-e360ed21f172', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'a4a3e7e0-6252-4a27-a281-80684d50882e');
INSERT INTO `ent_menu` VALUES ('35d2d1d6-075f-483e-95e9-81d60db191af', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', '1223709c-bf3f-4f54-8a85-895cc8709146', 'dd44c89d-4c59-436d-aef1-abc6a3fbf4bc', '#');
INSERT INTO `ent_menu` VALUES ('39777dd4-61f1-498d-904c-57f8c8bba628', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', 'f5cf8c1c-6491-44b5-9e54-e289a4fdb094', 'dd44c89d-4c59-436d-aef1-abc6a3fbf4bc', '#');
INSERT INTO `ent_menu` VALUES ('4a2790c1-90e5-434d-9ec9-baf9cd4b2167', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', 'b8bf4f17-0749-4251-b547-856ee4dff8dd', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', '#');
INSERT INTO `ent_menu` VALUES ('4fea593d-3f75-48ca-aaff-bf116c57ac4d', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', 'db095c80-111f-4326-b183-d2e43e343f04', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'a4a3e7e0-6252-4a27-a281-80684d50882e');
INSERT INTO `ent_menu` VALUES ('8b118006-d784-4b5e-af6f-4577dc40c0da', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', 'c2d82c74-0b30-41e2-9c0d-5f4f861c24e6', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'e3a1972b-5637-4e01-b080-670d555cf26f');
INSERT INTO `ent_menu` VALUES ('ba0d4ea4-09c9-4633-8d23-4618d1bcf9a2', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', '3e751234-7949-4562-b73c-cf0370351d95', 'dd44c89d-4c59-436d-aef1-abc6a3fbf4bc', '1223709c-bf3f-4f54-8a85-895cc8709146');
INSERT INTO `ent_menu` VALUES ('cf07d723-ec57-4aa6-8fcf-7523c6848f95', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', '216da748-27f7-45cf-9986-9b40e9ecd666', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'b8bf4f17-0749-4251-b547-856ee4dff8dd');
INSERT INTO `ent_menu` VALUES ('d6652ae7-69a9-4127-ac1c-7d99ccad3f94', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', 'a4a3e7e0-6252-4a27-a281-80684d50882e', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', '#');
INSERT INTO `ent_menu` VALUES ('d9a485ee-c9be-402e-98d3-3439bc066419', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', 'e3a1972b-5637-4e01-b080-670d555cf26f', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', '#');
INSERT INTO `ent_menu` VALUES ('da01d78d-a0f4-4b77-a0c1-34d04cad07a0', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', 'a7d6dc6b-c9f3-4763-a186-4a86840f9e4c', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'a4a3e7e0-6252-4a27-a281-80684d50882e');
INSERT INTO `ent_menu` VALUES ('f73347fc-336d-43f8-836d-5b0118f07bd1', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', 'a1ca0716-1236-4acc-9bb1-1708a2199f6c', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'a4a3e7e0-6252-4a27-a281-80684d50882e');

-- ----------------------------
-- Table structure for ent_module
-- ----------------------------
DROP TABLE IF EXISTS `ent_module`;
CREATE TABLE `ent_module`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `company_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业编号',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `module_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联sys_module的主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业关联系统模块表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ent_module
-- ----------------------------
INSERT INTO `ent_module` VALUES ('3f8282e0-40dc-49c4-b6e8-8ee454e5c007', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-24 05:52:16', '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `ent_module` VALUES ('dbb1911f-17fd-4ad0-8701-ed90633418aa', 'JXSG20221121c85d', 'admin', '2022-11-23 05:52:16', 'admin', '2022-11-23 05:52:16', 'dd44c89d-4c59-436d-aef1-abc6a3fbf4bc');

-- ----------------------------
-- Table structure for ent_organization
-- ----------------------------
DROP TABLE IF EXISTS `ent_organization`;
CREATE TABLE `ent_organization`  (
  `org_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组织编号',
  `company_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业编号',
  `parent_org_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级编号',
  `org_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织名称',
  `org_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织编码',
  `org_manager` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `org_tel` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话(手机号)',
  `org_short_tel` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定电话',
  `status` smallint(6) NULL DEFAULT NULL COMMENT '状态（1：启用  0：停用）',
  `org_type` smallint(6) NULL DEFAULT NULL COMMENT '组织机构类型（1=部门;0=公司）',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`org_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '组织架构管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ent_organization
-- ----------------------------
INSERT INTO `ent_organization` VALUES ('5f99cbdf-4218-4cd9-b33a-3bde583fb044', 'JXSG20221121c85d', 'f01b7758-744b-4c3f-b531-9c784464a8cf', '接待小组', 'JDXZ', '恒馨', '18234598372', '0551-6298374', 1, 1, 'adminGQ', '2022-11-22 05:56:25', 'adminGQ', '2022-11-22 06:09:04');
INSERT INTO `ent_organization` VALUES ('e2604acf-1368-4873-8b16-4cba823c8ef3', 'JXSG20221121c85d', 'f01b7758-744b-4c3f-b531-9c784464a8cf', '科技小组', 'KJXZ', '佛学徒', '18256909311', '', 1, 1, 'adminGQ', '2022-11-22 05:46:09', 'adminGQ', '2022-11-22 06:09:09');
INSERT INTO `ent_organization` VALUES ('e2655d9a-d5e1-44ab-a4cc-7806b3f5b88c', 'JXSG20221121c85d', 'f01b7758-744b-4c3f-b531-9c784464a8cf', '财政小组', 'CZXZ', '恒馨', '18259102110', '0551-6820284', 1, 1, 'adminGQ', '2022-11-22 05:52:36', 'adminGQ', '2022-11-22 06:09:48');
INSERT INTO `ent_organization` VALUES ('f01b7758-744b-4c3f-b531-9c784464a8cf', 'JXSG20221121c85d', '#', '静馨舍工作室', '1', '佛学徒', '18256908110', NULL, 1, 0, 'admin', '2022-11-21 03:19:28', 'adminGQ', '2022-11-22 06:09:01');

-- ----------------------------
-- Table structure for ent_other_config
-- ----------------------------
DROP TABLE IF EXISTS `ent_other_config`;
CREATE TABLE `ent_other_config`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `company_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业编码',
  `other_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '三方id',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业关联三方配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ent_other_config
-- ----------------------------
INSERT INTO `ent_other_config` VALUES ('4739c32f-2c31-430a-a9da-747466716969', 'JXSG20221121c85d', '13d03111-f149-4cb5-8555-eed59a5dbf7c', 'admin', '2022-11-21 05:46:35', 'admin', '2022-11-21 05:46:35');
INSERT INTO `ent_other_config` VALUES ('59d606f9-78a6-4c9f-8325-1148d29caa09', 'JXSG20221121c85d', '752214aa-dc42-45fd-895d-8b7d7d98a02e', 'admin', '2022-11-21 05:46:35', 'admin', '2022-11-21 05:46:35');

-- ----------------------------
-- Table structure for ent_profile
-- ----------------------------
DROP TABLE IF EXISTS `ent_profile`;
CREATE TABLE `ent_profile`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `company_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业编号',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '公司简介',
  `delete_flag` smallint(6) NULL DEFAULT NULL COMMENT '删除标识 0 未删除 1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ent_profile
-- ----------------------------
INSERT INTO `ent_profile` VALUES ('0c713979-5a94-40bc-b38b-5bd731def0d3', 'JXSG20221121c85d', '<p>段祺瑞（1865年3月6日－1936年11月2日），原名启瑞，字芝泉，<a href=\"https://baike.baidu.com/item/%E5%AE%89%E5%BE%BD%E7%9C%81/526353\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">安徽省</a><a href=\"https://baike.baidu.com/item/%E5%BA%90%E5%B7%9E%E5%BA%9C/5492566\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">庐州府</a><a href=\"https://baike.baidu.com/item/%E5%90%88%E8%82%A5%E5%8E%BF/5314625\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">合肥县</a>（今<a href=\"https://baike.baidu.com/item/%E5%AE%89%E5%BE%BD%E7%9C%81/526353\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">安徽省</a><a href=\"https://baike.baidu.com/item/%E5%90%88%E8%82%A5%E5%B8%82/6501395\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">合肥市</a>）人。中华民国时期<a href=\"https://baike.baidu.com/item/%E7%9A%96%E7%B3%BB%E5%86%9B%E9%98%80/424862\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">皖系军阀</a>首领，号称“<a href=\"https://baike.baidu.com/item/%E5%8C%97%E6%B4%8B%E4%B9%8B%E8%99%8E/17613519\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">北洋之虎</a>”，<a href=\"https://baike.baidu.com/item/%E5%AD%99%E4%B8%AD%E5%B1%B1/128084\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">孙中山</a>“<a href=\"https://baike.baidu.com/item/%E6%8A%A4%E6%B3%95%E8%BF%90%E5%8A%A8/4484972\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">护法运动</a>”的主要讨伐对象。<span style=\"color: rgb(51, 102, 204);\">&nbsp;[1-3]</span><span style=\"color: rgb(19, 110, 194);\">&nbsp;&nbsp;&nbsp;</span></p><p>1916年至1920年为北洋政府的实际掌权者。1924年至1926年为中华民国临时执政。1926年3月18日发生了段祺瑞政府镇压北京学生运动的<a href=\"https://baike.baidu.com/item/%E4%B8%89%C2%B7%E4%B8%80%E5%85%AB%E6%83%A8%E6%A1%88/1080857\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">三·一八惨案</a>。“<a href=\"https://baike.baidu.com/item/%E4%B9%9D%C2%B7%E4%B8%80%E5%85%AB/265540\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">九·一八</a>”事变后，日本人曾胁迫段祺瑞去东北组织傀儡政府，段严词拒绝。1936年11月2日，段祺瑞逝于上海宏恩医院。</p><p>号称“六不总理”，曾四任总理，四任陆军总长，一任参谋总长，一任国家元首。是中国现代化军队的第一任陆军总长和炮兵司令。任过中国第一所现代化军事学校——<a href=\"https://baike.baidu.com/item/%E4%BF%9D%E5%AE%9A%E5%86%9B%E6%A0%A1/792620\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">保定军校</a>的总办。<span style=\"color: rgb(51, 102, 204);\">&nbsp;[4]</span><span style=\"color: rgb(19, 110, 194);\">&nbsp;</span></p><p><img src=\"http://192.168.102.19:9100/api/file/8a91c3c499dd4279b61c16991f3aa30a.jpg\"></p><p><br></p><p>来一张段总长的照片：</p><p><img src=\"http://192.168.102.19:9100/api/file/3f16606616fd4a818ef1b7a168f2a252.jpg\"></p>', 0);
INSERT INTO `ent_profile` VALUES ('28505c1e-2cc8-4732-bf47-17420c1db649', 'JXSG20221121c85d', '<p>段祺瑞（1865年3月6日－1936年11月2日），原名启瑞，字芝泉，<a href=\"https://baike.baidu.com/item/%E5%AE%89%E5%BE%BD%E7%9C%81/526353\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">安徽省</a><a href=\"https://baike.baidu.com/item/%E5%BA%90%E5%B7%9E%E5%BA%9C/5492566\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">庐州府</a><a href=\"https://baike.baidu.com/item/%E5%90%88%E8%82%A5%E5%8E%BF/5314625\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">合肥县</a>（今<a href=\"https://baike.baidu.com/item/%E5%AE%89%E5%BE%BD%E7%9C%81/526353\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">安徽省</a><a href=\"https://baike.baidu.com/item/%E5%90%88%E8%82%A5%E5%B8%82/6501395\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">合肥市</a>）人。中华民国时期<a href=\"https://baike.baidu.com/item/%E7%9A%96%E7%B3%BB%E5%86%9B%E9%98%80/424862\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">皖系军阀</a>首领，号称“<a href=\"https://baike.baidu.com/item/%E5%8C%97%E6%B4%8B%E4%B9%8B%E8%99%8E/17613519\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">北洋之虎</a>”，<a href=\"https://baike.baidu.com/item/%E5%AD%99%E4%B8%AD%E5%B1%B1/128084\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">孙中山</a>“<a href=\"https://baike.baidu.com/item/%E6%8A%A4%E6%B3%95%E8%BF%90%E5%8A%A8/4484972\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">护法运动</a>”的主要讨伐对象。<span style=\"color: rgb(51, 102, 204);\">&nbsp;[1-3]</span><span style=\"color: rgb(19, 110, 194);\">&nbsp;&nbsp;&nbsp;</span></p><p>1916年至1920年为北洋政府的实际掌权者。1924年至1926年为中华民国临时执政。1926年3月18日发生了段祺瑞政府镇压北京学生运动的<a href=\"https://baike.baidu.com/item/%E4%B8%89%C2%B7%E4%B8%80%E5%85%AB%E6%83%A8%E6%A1%88/1080857\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">三·一八惨案</a>。“<a href=\"https://baike.baidu.com/item/%E4%B9%9D%C2%B7%E4%B8%80%E5%85%AB/265540\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">九·一八</a>”事变后，日本人曾胁迫段祺瑞去东北组织傀儡政府，段严词拒绝。1936年11月2日，段祺瑞逝于上海宏恩医院。</p><p>号称“六不总理”，曾四任总理，四任陆军总长，一任参谋总长，一任国家元首。是中国现代化军队的第一任陆军总长和炮兵司令。任过中国第一所现代化军事学校——<a href=\"https://baike.baidu.com/item/%E4%BF%9D%E5%AE%9A%E5%86%9B%E6%A0%A1/792620\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">保定军校</a>的总办。<span style=\"color: rgb(51, 102, 204);\">&nbsp;[4]</span><span style=\"color: rgb(19, 110, 194);\">&nbsp;</span></p><p><img src=\"http://192.168.102.19:9100/api/file/8a91c3c499dd4279b61c16991f3aa30a.jpg\"></p><p><br></p><p>来一张段总长的照片：</p><p><img src=\"http://192.168.102.19:9100/api/file/3f16606616fd4a818ef1b7a168f2a252.jpg\"></p>', 1);
INSERT INTO `ent_profile` VALUES ('c9eb5c6b-b4cd-457e-bbcd-10b4d20777f4', 'JXSG20221121c85d', '<p>	段祺瑞（1865年3月6日－1936年11月2日），原名启瑞，字芝泉，<a href=\"https://baike.baidu.com/item/%E5%AE%89%E5%BE%BD%E7%9C%81/526353\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">安徽省</a><a href=\"https://baike.baidu.com/item/%E5%BA%90%E5%B7%9E%E5%BA%9C/5492566\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">庐州府</a><a href=\"https://baike.baidu.com/item/%E5%90%88%E8%82%A5%E5%8E%BF/5314625\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">合肥县</a>（今<a href=\"https://baike.baidu.com/item/%E5%AE%89%E5%BE%BD%E7%9C%81/526353\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">安徽省</a><a href=\"https://baike.baidu.com/item/%E5%90%88%E8%82%A5%E5%B8%82/6501395\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">合肥市</a>）人。中华民国时期<a href=\"https://baike.baidu.com/item/%E7%9A%96%E7%B3%BB%E5%86%9B%E9%98%80/424862\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">皖系军阀</a>首领，号称“<a href=\"https://baike.baidu.com/item/%E5%8C%97%E6%B4%8B%E4%B9%8B%E8%99%8E/17613519\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">北洋之虎</a>”，<a href=\"https://baike.baidu.com/item/%E5%AD%99%E4%B8%AD%E5%B1%B1/128084\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">孙中山</a>“<a href=\"https://baike.baidu.com/item/%E6%8A%A4%E6%B3%95%E8%BF%90%E5%8A%A8/4484972\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">护法运动</a>”的主要讨伐对象。<span style=\"color: rgb(51, 102, 204);\">&nbsp;[1-3]</span><span style=\"color: rgb(19, 110, 194);\">&nbsp;&nbsp;&nbsp;</span></p><p>	1916年至1920年为北洋政府的实际掌权者。1924年至1926年为中华民国临时执政。1926年3月18日发生了段祺瑞政府镇压北京学生运动的<a href=\"https://baike.baidu.com/item/%E4%B8%89%C2%B7%E4%B8%80%E5%85%AB%E6%83%A8%E6%A1%88/1080857\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">三·一八惨案</a>。“<a href=\"https://baike.baidu.com/item/%E4%B9%9D%C2%B7%E4%B8%80%E5%85%AB/265540\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">九·一八</a>”事变后，日本人曾胁迫段祺瑞去东北组织傀儡政府，段严词拒绝。1936年11月2日，段祺瑞逝于上海宏恩医院。</p><p>	号称“六不总理”，曾四任总理，四任陆军总长，一任参谋总长，一任国家元首。是中国现代化军队的第一任陆军总长和炮兵司令。任过中国第一所现代化军事学校——<a href=\"https://baike.baidu.com/item/%E4%BF%9D%E5%AE%9A%E5%86%9B%E6%A0%A1/792620\" target=\"_blank\" style=\"color: rgb(19, 110, 194);\">保定军校</a>的总办。<span style=\"color: rgb(51, 102, 204);\">&nbsp;[4]</span><span style=\"color: rgb(19, 110, 194);\">&nbsp;</span></p><p><img src=\"http://192.168.102.19:9100/api/file/8a91c3c499dd4279b61c16991f3aa30a.jpg\"></p><p><br></p>', 1);

-- ----------------------------
-- Table structure for ent_role
-- ----------------------------
DROP TABLE IF EXISTS `ent_role`;
CREATE TABLE `ent_role`  (
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编号',
  `company_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业编号',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `status` smallint(6) NULL DEFAULT NULL COMMENT '状态  1：启用  0：停用',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ent_role
-- ----------------------------
INSERT INTO `ent_role` VALUES ('8bec474a-f8e8-4a22-9979-3b08af0ce77b', 'JXSG20221121c85d', '普通管理员', 1, 'adminGQ', '2022-11-23 06:12:02', 'adminGQ', '2022-11-23 06:23:40', '测试下');
INSERT INTO `ent_role` VALUES ('a4713756-137f-4a19-8492-1af5190a1dde', 'JXSG20221121c85d', '超级管理员角色', 1, 'admin', '2022-11-21 03:19:28', 'admin', '2022-11-21 03:19:28', NULL);
INSERT INTO `ent_role` VALUES ('ff920c1b-d555-4706-8dc8-78a50d5cb399', 'e57cb1f1-0195-4c07-a218-b2b411a036bd', '超级管理员', 1, 'admin', '2018-06-11 15:49:10', 'admin', '2018-06-11 15:49:10', '');

-- ----------------------------
-- Table structure for ent_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `ent_role_menu`;
CREATE TABLE `ent_role_menu`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统编号',
  `company_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业编号',
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编号',
  `menu_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单资源编号',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限管理表:权限关联模块以及模块下的菜单，模块id存储在ent_module表中' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ent_role_menu
-- ----------------------------
INSERT INTO `ent_role_menu` VALUES ('007f3402-6112-4b57-9787-f67829382742', '', 'ff920c1b-d555-4706-8dc8-78a50d5cb399', 'aed7bade-638f-4923-897a-20c1ec56a386', 'admin', '2018-06-12 15:44:30');
INSERT INTO `ent_role_menu` VALUES ('047008fb-685f-47c0-90d8-12d6ed548339', '', 'ff920c1b-d555-4706-8dc8-78a50d5cb399', 'bed7bade-638f-4923-897a-20c1ec56a123', 'admin', '2018-06-12 15:45:01');
INSERT INTO `ent_role_menu` VALUES ('08ff8122-ce91-4014-a960-8f9764e179ed', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'a4a3e7e0-6252-4a27-a281-80684d50882e', 'admin', '2022-11-23 05:52:16');
INSERT INTO `ent_role_menu` VALUES ('0afbe20d-6588-45fc-ad96-c2a47739904b', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', '3e751234-7949-4562-b73c-cf0370351d95', 'admin', '2022-11-23 05:52:16');
INSERT INTO `ent_role_menu` VALUES ('3b1ae199-3742-430b-837e-3574226ff975', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'c2d82c74-0b30-41e2-9c0d-5f4f861c24e6', 'admin', '2022-11-23 05:52:16');
INSERT INTO `ent_role_menu` VALUES ('3b2c5584-ba02-4d89-8841-b3e6c1ac7f36', 'JXSG20221121c85d', '8bec474a-f8e8-4a22-9979-3b08af0ce77b', 'f5cf8c1c-6491-44b5-9e54-e289a4fdb094', 'adminGQ', '2022-11-25 02:18:40');
INSERT INTO `ent_role_menu` VALUES ('46ff8da4-4088-4c0e-8279-4e01788359b2', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', '69b60731-6b35-423f-a52a-bb524fe0ec09', 'admin', '2022-11-23 05:52:16');
INSERT INTO `ent_role_menu` VALUES ('4e848967-b1ef-4721-848d-f55c15e11cdf', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'c0bd9ae4-6375-462f-85c0-526aba1c486a', 'admin', '2022-11-23 05:52:16');
INSERT INTO `ent_role_menu` VALUES ('52366e0c-fd7c-47ac-8fd6-feb1d4a30c60', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'db095c80-111f-4326-b183-d2e43e343f04', 'admin', '2022-11-23 05:52:16');
INSERT INTO `ent_role_menu` VALUES ('719adddf-ecd7-4117-8835-49f1aa483ffe', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'a1ca0716-1236-4acc-9bb1-1708a2199f6c', 'admin', '2022-11-23 05:52:16');
INSERT INTO `ent_role_menu` VALUES ('7e0bc18d-a17a-43bc-b234-ae1c0c0352ff', 'JXSG20221121c85d', '8bec474a-f8e8-4a22-9979-3b08af0ce77b', 'c2d82c74-0b30-41e2-9c0d-5f4f861c24e6', 'adminGQ', '2022-11-25 02:18:40');
INSERT INTO `ent_role_menu` VALUES ('9665edf6-9b8e-4dbb-9fc1-f2ea6963f17d', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', '1223709c-bf3f-4f54-8a85-895cc8709146', 'admin', '2022-11-23 05:52:16');
INSERT INTO `ent_role_menu` VALUES ('as56gh67-6112-4b57-9787-as4566ugh678', '', 'ff920c1b-d555-4706-8dc8-78a50d5cb399', '7d19cf26-f334-4c16-bebd-d8f789aa8d5c', 'admin', '2018-06-12 15:44:30');
INSERT INTO `ent_role_menu` VALUES ('as56gh67-6112-4b57-9787-f67829382742', '', 'ff920c1b-d555-4706-8dc8-78a50d5cb399', '82b25d15-dcf8-44db-87be-035d5c8fd630', 'admin', '2018-06-12 15:44:30');
INSERT INTO `ent_role_menu` VALUES ('dadfc435-4bfd-40c9-bf86-f7fdf854b3b9', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'a7d6dc6b-c9f3-4763-a186-4a86840f9e4c', 'admin', '2022-11-23 05:52:16');
INSERT INTO `ent_role_menu` VALUES ('e4831c81-88d4-41ec-890a-322d597c7e53', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'fe98249c-0d01-4ecf-85a3-e360ed21f172', 'admin', '2022-11-23 05:52:16');
INSERT INTO `ent_role_menu` VALUES ('e57bb583-bec8-4bbd-9d7c-8ccdab991fd5', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', '216da748-27f7-45cf-9986-9b40e9ecd666', 'admin', '2022-11-23 05:52:16');
INSERT INTO `ent_role_menu` VALUES ('f0cb4319-187c-4a3b-959c-3debf2aa09bb', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'e3a1972b-5637-4e01-b080-670d555cf26f', 'admin', '2022-11-23 05:52:16');
INSERT INTO `ent_role_menu` VALUES ('f298be7b-e13b-4063-b7c4-dfd2abdb6376', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'f5cf8c1c-6491-44b5-9e54-e289a4fdb094', 'admin', '2022-11-23 05:52:16');
INSERT INTO `ent_role_menu` VALUES ('fd61a9b6-ec9b-4938-be65-8523abb36c6f', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'b8bf4f17-0749-4251-b547-856ee4dff8dd', 'admin', '2022-11-23 05:52:16');

-- ----------------------------
-- Table structure for ent_user
-- ----------------------------
DROP TABLE IF EXISTS `ent_user`;
CREATE TABLE `ent_user`  (
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号',
  `company_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业编号',
  `org_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织编号',
  `employee_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工编号',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名:超级管理员有且仅有一个，在创建企业时生成',
  `sex` smallint(6) NULL DEFAULT NULL COMMENT '性别:1男，２女',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `tel` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `qq` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `status` smallint(6) NULL DEFAULT NULL COMMENT '状态  1：启用 0：停用2：锁定（在密码错误达到一定次数时，进行锁定）',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `lock_time` datetime(0) NULL DEFAULT NULL COMMENT '锁定时间',
  `latest_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `latest_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后一次登录IP',
  `error_count` int(11) NULL DEFAULT NULL COMMENT '错误次数（通过手动解锁来重置错误次数）',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `UK_USER`(`user_id`, `company_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业用户管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ent_user
-- ----------------------------
INSERT INTO `ent_user` VALUES ('02c09399-ab1f-471c-b6ff-160ad082face', 'JXSG20221121c85d', 'f01b7758-744b-4c3f-b531-9c784464a8cf', NULL, 'adminGQ', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', 0, NULL, '18256909696', NULL, 1, NULL, 'a4713756-137f-4a19-8492-1af5190a1dde', NULL, '2022-11-25 11:02:09', '192.168.102.4', 0, 'admin', '2022-11-21 03:19:28', 'adminGQ', '2022-11-24 02:27:07');
INSERT INTO `ent_user` VALUES ('3c0d4353-96ab-4b42-86c0-0220f3b551b6', 'JXSG20221121c85d', 'e2655d9a-d5e1-44ab-a4cc-7806b3f5b88c', '', 'hengxin', 'e10adc3949ba59abbe56e057f20f883e', '恒馨', 1, '11051072654@qq.com', '18225603323', '1105107265', 1, '领导当然是管财政的', '8bec474a-f8e8-4a22-9979-3b08af0ce77b', NULL, '2022-11-25 11:02:27', '192.168.102.4', 0, 'adminGQ', '2022-11-24 01:49:45', 'adminGQ', '2022-11-24 10:13:48');
INSERT INTO `ent_user` VALUES ('9aa851d8-f314-4efb-a78d-370c6f949f66', 'JXSG20221121c85d', 'e2604acf-1368-4873-8b16-4cba823c8ef3', '9527', 'gaoq', 'e10adc3949ba59abbe56e057f20f883e', '佛学徒', 0, '1105107264@qq.com', '17654433322', '1105107264', 1, '科技小组中坚力量，俗称技男', NULL, NULL, NULL, NULL, 0, 'adminGQ', '2022-11-24 02:02:40', 'adminGQ', '2022-11-24 02:16:38');

-- ----------------------------
-- Table structure for ent_user_menu_desensite
-- ----------------------------
DROP TABLE IF EXISTS `ent_user_menu_desensite`;
CREATE TABLE `ent_user_menu_desensite`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统编号',
  `company_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业编号',
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `menu_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单资源编号',
  `is_desensite` smallint(6) NULL DEFAULT 0 COMMENT '是否脱敏 1：是 0：否(默认值)',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户针对菜单脱敏表-表示当前用户针对当前存在敏感信息的菜单是否可展示该敏感信息。' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ent_user_menu_desensite
-- ----------------------------
INSERT INTO `ent_user_menu_desensite` VALUES ('82ccf883-ffb8-4a2f-be7d-653c0011e88d', 'JXSG20221121c85d', '02c09399-ab1f-471c-b6ff-160ad082face', 'c0bd9ae4-6375-462f-85c0-526aba1c486a', 1, 'adminGQ', '2022-11-24 07:28:26', 'adminGQ', '2022-11-24 07:31:36');

-- ----------------------------
-- Table structure for sys_enterprise
-- ----------------------------
DROP TABLE IF EXISTS `sys_enterprise`;
CREATE TABLE `sys_enterprise`  (
  `company_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业编号',
  `company_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业名称',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业邮箱',
  `tel` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业联系电话',
  `company_manager` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `org_img` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照',
  `org_owner_img` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人身份证',
  `status` smallint(6) NULL DEFAULT NULL COMMENT '企业状态 1：启用（默认值） 0：停用',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `short_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业简称',
  `wechat_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信号',
  PRIMARY KEY (`company_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业信息管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_enterprise
-- ----------------------------
INSERT INTO `sys_enterprise` VALUES ('JXSG20221121c85d', '静馨舍工作室', '1105107264@qq.com', '18256908359', '高强', NULL, NULL, 1, 'admin', '2022-11-21 03:19:28', 'admin', '2022-11-23 05:52:15', '恒馨', 'foxuetu');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单路径',
  `sequence` int(11) NULL DEFAULT NULL COMMENT '序号',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `parent_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级菜单id',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `status` smallint(6) NULL DEFAULT NULL COMMENT '状态   0：停用, 1：启用',
  `desensitize_status` smallint(6) NULL DEFAULT -1 COMMENT '是否支持脱敏(0,否,1是)；默认-1表示不支持脱敏设置',
  `module_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前菜单所属模块',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统模块下菜单配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1223709c-bf3f-4f54-8a85-895cc8709146', '系统管理', '', 1, 'fa fa-cogs', '#', 'admin', '2022-11-17 07:18:36', 'admin', '2022-11-17 07:18:36', '系统管理,添加模块时，会默认生成一个系统菜单', 1, -1, 'dd44c89d-4c59-436d-aef1-abc6a3fbf4bc');
INSERT INTO `sys_menu` VALUES ('216da748-27f7-45cf-9986-9b40e9ecd666', '客户列表', '1223', 1, 'fa fa-address-card-o', 'b8bf4f17-0749-4251-b547-856ee4dff8dd', 'admin', '2022-11-18 07:46:48', 'admin', '2022-11-21 01:57:57', '', 1, 1, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('3e751234-7949-4562-b73c-cf0370351d95', '功能管理', 'moduletest', 1, 'fa fa-bank', '1223709c-bf3f-4f54-8a85-895cc8709146', 'admin', '2022-11-23 05:36:06', 'admin', '2022-11-23 05:36:06', '', 1, -1, 'dd44c89d-4c59-436d-aef1-abc6a3fbf4bc');
INSERT INTO `sys_menu` VALUES ('69b60731-6b35-423f-a52a-bb524fe0ec09', '操作日志', '/ent-manage/operation-log', 2, 'fa fa-eye', 'e3a1972b-5637-4e01-b080-670d555cf26f', 'admin', '2022-11-18 07:11:36', 'admin', '2022-11-18 07:11:36', '', 1, -1, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('9a563537-398c-4f97-9d30-41d331970789', '不支持脱敏', 'test', 1, 'fa fa-balance-scale', 'f5cf8c1c-6491-44b5-9e54-e289a4fdb094', 'admin', '2022-11-24 06:35:56', 'admin', '2022-11-24 06:36:10', '', 1, -1, 'dd44c89d-4c59-436d-aef1-abc6a3fbf4bc');
INSERT INTO `sys_menu` VALUES ('a1ca0716-1236-4acc-9bb1-1708a2199f6c', '架构管理', '/ent-manage/organization', 3, 'fa fa-calendar-check-o', 'a4a3e7e0-6252-4a27-a281-80684d50882e', 'admin', '2022-11-18 06:54:31', 'admin', '2022-11-18 06:54:31', '公司组织架构', 1, -1, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('a4a3e7e0-6252-4a27-a281-80684d50882e', '系统管理', '', 1, 'fa fa-cogs', '#', 'admin', '2022-11-17 07:23:12', 'admin', '2022-11-18 06:57:18', '系统管理,添加模块时，会默认生成一个系统菜单', 1, -1, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('a7d6dc6b-c9f3-4763-a186-4a86840f9e4c', '公司简介', '/ent-manage/company', 4, 'fa fa-anchor', 'a4a3e7e0-6252-4a27-a281-80684d50882e', 'admin', '2022-11-18 06:58:32', 'admin', '2022-11-18 07:05:37', '', 1, -1, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('b8bf4f17-0749-4251-b547-856ee4dff8dd', '客户管理', '', 2, 'fa fa-address-book-o', '#', 'admin', '2022-11-17 08:52:24', 'admin', '2022-11-18 06:45:32', 'app用户管理', 1, -1, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('c0bd9ae4-6375-462f-85c0-526aba1c486a', '用户管理', '/ent-manage/user', 1, 'fa fa-user-o', 'a4a3e7e0-6252-4a27-a281-80684d50882e', 'admin', '2022-11-17 12:26:03', 'admin', '2022-11-21 01:58:18', '企业下的用户管理', 1, 1, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('c2d82c74-0b30-41e2-9c0d-5f4f861c24e6', '登录日志', '/ent-manage/login-log', 1, 'fa fa-lock', 'e3a1972b-5637-4e01-b080-670d555cf26f', 'admin', '2022-11-18 07:10:06', 'admin', '2022-11-18 07:10:06', '', 1, -1, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('db095c80-111f-4326-b183-d2e43e343f04', '字典管理', '/ent-manage/dict', 5, 'fa fa-book', 'a4a3e7e0-6252-4a27-a281-80684d50882e', 'admin', '2022-11-18 07:04:13', 'admin', '2022-11-18 07:05:31', '', 1, -1, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('e3a1972b-5637-4e01-b080-670d555cf26f', '日志管理', NULL, 3, 'fa fa-commenting-o', '#', 'admin', '2022-11-17 11:52:25', 'admin', '2022-11-17 11:52:25', '企业端日志信息，登录日志和操作日志', 1, -1, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('f5cf8c1c-6491-44b5-9e54-e289a4fdb094', '测试管理', NULL, 1, 'fa fa-adjust', '#', 'admin', '2022-11-23 05:35:36', 'admin', '2022-11-23 05:35:36', '', 1, -1, 'dd44c89d-4c59-436d-aef1-abc6a3fbf4bc');
INSERT INTO `sys_menu` VALUES ('fe98249c-0d01-4ecf-85a3-e360ed21f172', '角色管理', '/ent-manage/role', 2, 'fa fa-calendar-plus-o', 'a4a3e7e0-6252-4a27-a281-80684d50882e', 'admin', '2022-11-18 06:50:35', 'admin', '2022-11-18 06:50:35', '', 1, -1, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');

-- ----------------------------
-- Table structure for sys_menu_fixed
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_fixed`;
CREATE TABLE `sys_menu_fixed`  (
  `menu_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单路径',
  `sequence` int(11) NULL DEFAULT NULL COMMENT '序号',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `parent_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级菜单id',
  `parent_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级菜单名称',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `status` smallint(6) NULL DEFAULT NULL COMMENT '状态   0：停用, 1：启用',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单表-当前系统管理固定菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu_fixed
-- ----------------------------
INSERT INTO `sys_menu_fixed` VALUES ('7d19cf26-f334-4c16-bebd-d8f789aa8d5c', '模块管理', '/sys-manage/modular', 7, 'fa fa-plus-square', 'aed7bade-638f-4923-897a-20c1ec56a386', '系统管理', 'admin', '2022-10-18 17:13:54', 'admin', '2022-10-18 16:51:48', '', 1);
INSERT INTO `sys_menu_fixed` VALUES ('82b25d15-dcf8-44db-87be-035d5c8fd630', '企业管理', '/sys-manage/business', 1, 'fa fa-bank', 'aed7bade-638f-4923-897a-20c1ec56a386', '系统管理', 'admin', '2022-10-18 18:15:09', 'admin', '2022-10-18 17:09:15', '', 1);
INSERT INTO `sys_menu_fixed` VALUES ('aed7bade-638f-4923-897a-20c1ec56a386', '系统管理', '', 1, 'fa fa-cogs', '#', '', 'admin', '2022-10-18 17:20:28', 'admin', '2022-10-18 13:44:22', '', 1);
INSERT INTO `sys_menu_fixed` VALUES ('bed7bade-638f-4923-897a-20c1ec56a123', '参数维护', '/sys-manage/other', 5, 'fa fa-bank', 'aed7bade-638f-4923-897a-20c1ec56a386', '系统管理', 'admin', '2022-10-18 18:23:04', 'admin', '2022-10-18 16:40:37', NULL, 1);

-- ----------------------------
-- Table structure for sys_module
-- ----------------------------
DROP TABLE IF EXISTS `sys_module`;
CREATE TABLE `sys_module`  (
  `module_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `module_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统名称',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` smallint(6) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`module_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统模块' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_module
-- ----------------------------
INSERT INTO `sys_module` VALUES ('04c3ea3f-18b5-46bc-83d4-f45c573a6210', '身旁事', 'admin', '2022-11-17 07:23:12', 'admin', '2022-11-17 07:52:20', '围绕我们身边的一些事情或礼仪方面的语录', 1);
INSERT INTO `sys_module` VALUES ('dd44c89d-4c59-436d-aef1-abc6a3fbf4bc', '慧分期', 'admin', '2022-11-17 07:18:36', 'admin', '2022-11-17 07:18:36', '购买商品分期付款的一个项目', 1);

-- ----------------------------
-- Table structure for sys_other_columns_conf
-- ----------------------------
DROP TABLE IF EXISTS `sys_other_columns_conf`;
CREATE TABLE `sys_other_columns_conf`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `other_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sortting` int(11) NULL DEFAULT NULL COMMENT '排序值',
  `para_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数编码',
  `para_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `para_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数名称 ',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` smallint(6) NULL DEFAULT 1 COMMENT '状态(1=有效；0=无效）',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_other_columns_conf
-- ----------------------------
INSERT INTO `sys_other_columns_conf` VALUES ('0dbdfc8d-5d74-4b7f-b523-39333634624a', '927fc87c-b2cf-4014-8b8d-9c4439baf42a', 0, 'wechatPay', 'wechatPay', '微信支付', '', 1, 'admin', '2022-11-21 05:25:06', 'admin', '2022-11-21 05:25:06');
INSERT INTO `sys_other_columns_conf` VALUES ('249ed3dd-d8eb-4d12-85a9-228292c3bfc1', '752214aa-dc42-45fd-895d-8b7d7d98a02e', 3, 'aplipayPrivateKey', 'PrivateKey-9527', '支付宝支付私钥', '支付宝私钥和公钥是一起的', 1, 'admin', '2022-11-17 05:47:19', 'admin', '2022-11-17 05:56:49');
INSERT INTO `sys_other_columns_conf` VALUES ('4fb19658-e001-4876-a363-a982e18e7724', '752214aa-dc42-45fd-895d-8b7d7d98a02e', 2, 'aplipayPublicKey', 'PublicKey-9527', '支付宝支公钥', '支付宝私钥和公钥是一起的', 1, 'admin', '2022-11-17 05:51:28', 'admin', '2022-11-17 05:56:49');
INSERT INTO `sys_other_columns_conf` VALUES ('ab09d206-4dbe-484d-a6ed-cd85599fff4f', '13d03111-f149-4cb5-8555-eed59a5dbf7c', 1, 'androidVersionCode', '1', 'android版本号', '', 1, 'admin', '2022-11-16 10:04:11', 'admin', '2022-11-16 10:04:11');
INSERT INTO `sys_other_columns_conf` VALUES ('ddd0aad2-506f-4cb0-a3c0-a234ebde77a6', '752214aa-dc42-45fd-895d-8b7d7d98a02e', 1, 'aplipayUrl', 'https://www.alipay.com', '支付宝网络请求地址', '', 1, 'admin', '2022-11-17 05:16:43', 'admin', '2022-11-17 05:56:49');
INSERT INTO `sys_other_columns_conf` VALUES ('e99d4862-f3a3-42b4-8509-bc2e5a91718e', '13d03111-f149-4cb5-8555-eed59a5dbf7c', 2, 'androidVersionName', '1.0.0', 'android版本名称', '', 1, 'admin', '2022-11-16 10:04:11', 'admin', '2022-11-16 10:04:11');

-- ----------------------------
-- Table structure for sys_other_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_other_config`;
CREATE TABLE `sys_other_config`  (
  `other_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `other_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '三方名称',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `status` smallint(6) NULL DEFAULT 1 COMMENT '1：有效，0：无效',
  `type` smallint(6) NULL DEFAULT 1 COMMENT '参数类型(1=三方参数;0=系统配置参数)',
  PRIMARY KEY (`other_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '三方配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_other_config
-- ----------------------------
INSERT INTO `sys_other_config` VALUES ('13d03111-f149-4cb5-8555-eed59a5dbf7c', '基础配置', 'admin', '2022-11-16 10:04:11', 'admin', '2022-11-17 05:29:15', 1, 0);
INSERT INTO `sys_other_config` VALUES ('752214aa-dc42-45fd-895d-8b7d7d98a02e', '支付宝', 'admin', '2022-11-17 05:16:43', 'admin', '2022-11-17 05:56:49', 1, 1);
INSERT INTO `sys_other_config` VALUES ('927fc87c-b2cf-4014-8b8d-9c4439baf42a', '微信支付', 'admin', '2022-11-21 05:25:06', 'admin', '2022-11-21 05:25:06', 1, 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号',
  `org_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织编号',
  `employee_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工编号',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `sex` smallint(6) NULL DEFAULT NULL COMMENT '性别:1男，２女',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `tel` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `qq` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `status` smallint(6) NULL DEFAULT NULL COMMENT '状态  1：启用 0：停用2：锁定（在密码错误达到一定次数时，进行锁定）',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `lock_time` datetime(0) NULL DEFAULT NULL COMMENT '锁定时间',
  `latest_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `latest_ip` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后一次登录IP',
  `error_count` int(11) NULL DEFAULT NULL COMMENT '错误次数（通过手动解锁来重置错误次数）',
  `create_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `UK_USER`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户管理表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('fb616e4e-2f24-4666-9fa1-e0749a60d228', '20b99b5e-3d9b-4591-a501-f15dd6bd00a7', 'zzl463381', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', 1, NULL, NULL, NULL, 1, NULL, 'ff920c1b-d555-4706-8dc8-78a50d5cb399', NULL, '2022-11-24 14:31:33', '192.168.102.4', 0, 'admin', '2018-05-23 17:31:51', 'admin', '2018-05-29 17:20:58');

SET FOREIGN_KEY_CHECKS = 1;
