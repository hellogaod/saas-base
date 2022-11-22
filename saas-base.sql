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

 Date: 22/11/2022 15:30:38
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
-- Records of ent_log
-- ----------------------------
INSERT INTO `ent_log` VALUES ('00972d4e-6a88-49a5-8db1-be1b7f0d15cf', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 03:11:22', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 03:11:22', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('01f4c067-ab53-4da0-a49a-b6c2b63305f8', NULL, NULL, NULL, '/getOtherList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 02:02:32', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 02:02:32', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('0516c1d0-bcf7-48c4-9c35-b1af7660c86b', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:42:30', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:42:30', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('0590eb3e-f30d-445f-8dc9-5bafd9a57ca6', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 02:37:58', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 02:37:58', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('07ae158a-b50e-461d-bce1-a03ab3e8721b', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 03:19:29', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 03:19:29', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('0ab8bf81-7cf8-458b-b9c7-97692c200bde', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=c0bd9ae4-6375-462f-85c0-526aba1c486a', '2022-11-18 03:16:53', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:16:53', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('0f76b7b1-cb0e-4923-b4ef-e95f34691771', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=c0bd9ae4-6375-462f-85c0-526aba1c486a', '2022-11-18 03:27:24', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:27:24', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('10d8a7dd-a86a-4512-b9fe-58cfecbb00cb', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:23:30', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:23:30', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('11322cd0-603e-424d-bc7b-74a07da9aa47', NULL, NULL, NULL, '/getEnterpriseByCompanyCode', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 05:16:58', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:16:58', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('188d7abc-c641-4901-92da-5a77c15f9772', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=c0bd9ae4-6375-462f-85c0-526aba1c486a', '2022-11-18 05:45:54', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 05:45:54', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('19c984a8-29b9-4442-8055-eec49fcb89b7', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=db095c80-111f-4326-b183-d2e43e343f04', '2022-11-18 07:05:29', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 07:05:29', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('1b8903ac-eb07-4cb8-9274-c340bc200a4a', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:17:25', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:17:25', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('1c2e9978-5b5b-4f69-a73d-e7f42ee6c7b2', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=c0bd9ae4-6375-462f-85c0-526aba1c486a', '2022-11-18 07:05:46', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 07:05:46', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('1d06f710-97b4-4833-85b8-203903afc3d3', NULL, NULL, NULL, '/getModuleList', NULL, 'status=-1&pageIndex=1&pageSize=10', '2022-11-18 03:16:39', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:16:39', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('214a3bcb-0580-4fa1-be69-ec1824edc9ce', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:42:45', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:42:45', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('228ba3bd-e2b7-4538-a985-993ace0cb2a5', 'adminGQ', 'JXSG20221121c85d', '静馨舍工作室', '/doLogin', NULL, '{\"keyScript\":\"fs+6ao+USo3zmKbdUUGLisRBDb6XsrpTwv1XXrfNbCPe7pQrbmvdaXD70hBa+2SHROMvIqQrK1Oc1wWHVR6amTQCL4zB/q55AgWXMlugkBeUyP/ecmNStfvwweAYzr4VBqNJKeE4+qiOd2mLiedVZawLbYnZRO/Rq8w2cQoYqGIT6fboto6Mq2gVEdzUXL9MTuUI0dL/YOs+jxiSsNXAw16+qdCgRpu4hqL7iXT64N7gy8uxr559zDHZf/GbPyybNwd8g9B+fSdlkhOeeJ8ShJw1drBppQM7FKVxxVuP2RBGsrxtTpG+6Tpc4/GmkgNJFUIC9MAVHxDoBM/K1NaWSjT+jgdarwhsMpihDuN2clGJCZDnzFHjQrdHh4JtIKacirgYhzQd71jR/tatZQZ9kM/GBy6whuP4WALkbH8UNK83uD0QYPTIzKpJjVsuj0ygT1+7myN96fs2McbZUvscA7PabuRrTgUfWJZhx6wzqeVtSMDVppUfMMvn0gldLZkAdfQXlcegF6WQVN1e/4Zt7dUuiFDpY7n58qMoVwL7qq1lnWFCHO7TnkQlxPVLWaNRMbAJoqHDOaB3frm+h1W4xCVGSouIn32FKfcV4evSFHRPKV7V6Cexvq0l2pV5oVbkfeptP36jrsXewLqTStjRqRxKS3QvmvcLC5OTYiMkIOE=\"}', '2022-11-21 08:56:49', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 08:56:49', 'adminGQ', NULL, NULL);
INSERT INTO `ent_log` VALUES ('24129c30-6527-4867-8df1-3ae5c1411c6f', 'adminGQ', 'JXSG20221121c85d', '静馨舍工作室', '/doLogin', NULL, '{\"keyScript\":\"weg688K+Re6VW65fKcbBHRiVbhs6JuCIpRinZ8xvswRnlp0n5FrEJlfLiVsO+zZzGwKI5UUpy5mu8p/6SXooVDxbjrT9O/AySwrYdWf1TEFt5qDWBzqJM2lIgYo5LxxwR/XzfqrJ4ppbMpCHXMxJkwwhtSCJSRS2KQZwByzD8WMfCqFTw6zp4gtOXEyUg1Yj1ynBRwQUJH0mrye8/g6Q9sREbt5RmjpSaDokor1DRrJ3a1V4bMKt8PHrqRIpFVyog+PO/dmvgiWQUwRi2ppYPqh88Yps/mY5FSuhjtNFs59lewMWEjZLeOyLIWrUKP2kX/Fn7g7433eVNJrsi99GbBhM1yCkxM4m6sDuV20KQDNM1upsAx2saUX+Ifb5Ckq8QxxCUIqYGuMfp0r6k4XTVdO1enU0NwLSCqyTSvsxDC9rQb7jCr8sNLyLZNZK4wfNUcCLNbsAgwW4UqxZvAZl0WRgvAPsbF6cs3nWVnCrFBpCl/ZwJfNaNEpqvB5JeL5Y+gBEaIpwkaXWO5f2kfJOArvzNwi3yJkQLp4SOto8xKDFD1153DVI0ti9254nJ3cD5j7QCjoE7LWcLcFiSvT2A0dx5y1/X+2UcxDDyQIrm4oCBDfLiqBOmDvFzDTtEuxtbT92+/xE2/FjZl9Ekm8oQmZvalLGM9DSm5yCykx80Rw=\"}', '2022-11-22 02:34:22', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-22 02:34:22', 'adminGQ', NULL, NULL);
INSERT INTO `ent_log` VALUES ('24e90d13-2117-4cb0-ad4a-84ac8fdb5e7f', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 03:23:43', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 03:23:43', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('25a1a128-1af5-4eaf-8e36-2e2549b0b461', NULL, NULL, NULL, '/doLogin', NULL, '{\"keyScript\":\"lSp3u0jON3YONJrYKQ53hCKAcNuCtJq0f1vflq2rwmTn/Hkt9TWyPtFXUCLAu7IYyGly+UkmT3XVJxaRVW9bazksOhcIU+/4AvmwmeXRzyzMd89Yw7vnAbBLAfw2Jlr6cHCsin7aEps+q/oHRhxqpTe8llpgW5PWFHQmK4bhk9Wo5hjx8WIuWc723kbEdbPWt1NNod9o+m8EAOW+aaA5FJ9F4xt6gOoX3sfVeIyp79bsgdS/Iy1+PxFlIfcmcG19Rem/heJArfE2/tAxtLMIzi0flf0oT1AYe9FYEC2dA+wpMuAx7MkbVgcke37KKPab1LjwoN8Rfw7oYlxBlOIktoesaIbixUPX1yMU73VdW67dMCtoztVJlpooeRzXGUcbisJ4W8yZ8iNgU5uK41rHwDoZS/p8PdVNaoejrMGBsTq+lB0/69+3sWhFxB7WCBePhhUvviMR4j7vlg7HH7iHZ1+capD8Ua3I4bZKxTab/mOE1D/42VCRSAaZII9Pfpc1Whp/S8/WPY3Qo5DL/kxvv3URrLXJDSGjzUeVr1r+AK0PmxEXUChomXuoPpw+R1TSy2OxJmV4ykEa+jhRexBONw4rIwIh6gR3a2ua8F/3doYuR7JPGLs4e4DfAQbfgkaFJv8tVzEHvksT1PAuDkq5QKyG3SxFgfprVA0aRVkRodo=\"}', '2022-11-22 02:37:31', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-22 02:37:31', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('2a39a0ff-c170-4994-9fbc-8829947b25c7', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=fe98249c-0d01-4ecf-85a3-e360ed21f172', '2022-11-18 07:05:42', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 07:05:42', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('2cd6da83-2ab3-40b4-b958-5c014cd6d400', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 03:18:51', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 03:18:51', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('2f59da5b-d1f5-46d1-aeb9-3a15698e299d', NULL, NULL, NULL, '/getMenuById', NULL, 'id=a4a3e7e0-6252-4a27-a281-80684d50882e', '2022-11-18 03:04:33', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:04:33', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('2f811258-e5d9-4f02-8d3c-87e454db6a05', NULL, NULL, NULL, '/getEnterpriseByCompanyCode', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 05:17:46', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:17:46', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('325bd809-b11c-40de-b364-e8039b1cfdca', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:20:21', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:20:21', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('32cfe5e8-249d-444b-8a5a-6c4729c659eb', NULL, NULL, NULL, '/getModuleList', NULL, 'status=-1&pageIndex=1&pageSize=10', '2022-11-18 02:35:50', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 02:35:50', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('342ab9e8-cf2a-4b13-8efb-42ea2c0a18a7', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=a4a3e7e0-6252-4a27-a281-80684d50882e', '2022-11-18 03:17:45', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:17:45', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('37606476-904c-4c9b-8cce-50d6775a92bf', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:19:12', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:19:12', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('3f2a8fc6-4d79-4eb6-97dc-7ca2f1cd650b', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=c0bd9ae4-6375-462f-85c0-526aba1c486a', '2022-11-18 05:45:17', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 05:45:17', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('3fd8dafd-36a7-47ec-a093-eb4ed773b802', NULL, NULL, NULL, '/getModuleList', NULL, 'status=-1&pageIndex=1&pageSize=10', '2022-11-21 01:57:26', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 01:57:26', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('40afe1fc-f449-4f0e-af25-7a3663c0bbd8', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=c0bd9ae4-6375-462f-85c0-526aba1c486a', '2022-11-18 06:52:41', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 06:52:41', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('4313c2a5-b219-40a4-8c11-c5a5ef55d223', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=c0bd9ae4-6375-462f-85c0-526aba1c486a', '2022-11-21 01:58:16', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 01:58:16', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('482abb66-b95e-4408-8580-9c00bfcf88ff', NULL, NULL, NULL, '/getEnterpriseByCompanyCode', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 03:33:38', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 03:33:38', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('489a3deb-1d86-40dc-b556-53decc0938d0', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=a4a3e7e0-6252-4a27-a281-80684d50882e', '2022-11-18 03:20:54', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:20:54', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('490535e8-fc05-466f-afac-1042dead1c69', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=fe98249c-0d01-4ecf-85a3-e360ed21f172', '2022-11-18 06:52:37', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 06:52:37', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('4905e022-9b70-4d84-93c5-8a5c900de522', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:36:06', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:36:06', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('49792ec8-7465-4226-a9ed-ad22e9967daf', NULL, NULL, NULL, '/getOneMenu', NULL, 'flag=1&sysCode=04c3ea3f-18b5-46bc-83d4-f45c573a6210', '2022-11-18 03:05:06', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:05:06', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('497ebbb2-b5fa-474b-aa96-5e8c9b83e896', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:41:42', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:41:42', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('4a866c52-119d-42bf-9fb3-6325e0d62299', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=b8bf4f17-0749-4251-b547-856ee4dff8dd', '2022-11-18 06:45:24', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 06:45:24', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('4aaada48-1f5f-42fd-ba89-9c9ed3de9724', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=db095c80-111f-4326-b183-d2e43e343f04', '2022-11-18 07:13:15', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 07:13:15', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('4b1967a1-0b81-4aa2-8b38-ffcd9a5e451c', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:17:49', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:17:49', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('4c4c2d69-abd0-474d-9060-76dc21493a27', 'adminGQ', 'JXSG20221121c85d', '静馨舍工作室', '/doLogin', NULL, '{\"keyScript\":\"uVbZY85AkSzfNRvhWovY1KGj+XT7U4rgTgifdlHrjyAdE+d/PBesNAQnWf2cEU1ri8T0WEx043JVzT9H8ZYtoSjNYuxQghuJmYJvTOq1zd3Sw6id5MQ3pFPiLGUslxXJxF5oFXhdeOkgAjsRs9OclWoYbRuQX1Pls01SUHK0gLFW7+fSL/4e/er4UviCe9p/4A4pNhjfzcSJTy5a11hMdZpazJt3QfRxjB0tSlwJqqW/PAQ0NqogR4j2ihgNZ3C+xpBVQZp8zNW79f/mL4RiEZuGIra03gJag6Ito5EqmCqQTOaJhTL0tyjZQdtTIAnEEIG4P8hHjCHmgYb96B7Xw5qkg3cYF/9I0kKHPE/f4CG9nhm7oPZY1SMhaE0C10Dn+vh+EUotbVAtHdcguXRlaKn41TQjTE6GUIbqQoDe4cPl8iIdZq/pwEmnU+8iewxLgrUZ1xxtKGw2TX1lBmp13kkenWpuHpJsVkYX0z5GeDv4d3m1nvqhwx/um5YExAeam8N+0wyUaGiyk9k9SQrwADJo99Hq9rlPSi4Rxiiyy8QhEE7nHQEBrPjhJ1bcpwGN5F2GInpRpTiuEV85P5uxV1Dh4JboYh6ZwdvoY/M4Ur+jJW+1cv5AUe11lP7hfIVJw/+VLkvgu1p4+0TZRfftHFhioJUL4VKkny/p+SyPIDk=\"}', '2022-11-21 08:34:01', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 08:34:01', 'adminGQ', NULL, NULL);
INSERT INTO `ent_log` VALUES ('4d76ea47-3d39-4d4f-a026-51ec8b1df660', NULL, NULL, NULL, '/getEffectiveModule', NULL, NULL, '2022-11-18 07:48:29', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 07:48:29', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('4fb873d4-5991-4f79-b5ed-222aa65b60f4', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=a4a3e7e0-6252-4a27-a281-80684d50882e', '2022-11-18 06:53:13', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 06:53:13', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('51d5c09c-18f4-4d8a-b3b2-d8ba3eb71e66', NULL, NULL, NULL, '/getEnterpriseByCompanyCode', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 03:23:45', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 03:23:45', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('52291a78-ef6f-49c7-a4aa-027ab3a7be7b', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 03:21:56', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 03:21:56', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('56a48a19-19c8-43be-ab4d-b2c8af31afbd', 'adminGQ', 'JXSG20221121c85d', '静馨舍工作室', '/doLogin', NULL, '{\"keyScript\":\"YAS7Y4sz8iXM07cUVd431pGC4mW6HNurpMbkSjRJ1yU5veEEGT1kA67z2pesPcXmei7h2RoFlPUROb7VOAkqR/0ZsDTM/F1fkjLyN/IO1xwKU1YEq60rttkKqRBAgtJWXKwp5VHQUmiNfs70Dy6dkPsdDfi55C348CHvuOcdsynrWHFgbrdSvEqvLI6NT6nxOzmgWiNJlNJpu9OZbUx7/FMLSHVXp0/RKp3cC9zR0uK3kKBRNA8BHypkvWzmB58R1fKoXaeLomXFbSihTzZO2y6ew7FAQqcSckwmFh6jH2wuIqcAU/8n/MQaYFmkkVTP4zyWdumwCBYqKq6GvSE7x5YVg/1jwGtBAT8+Iy7wAxUoWsgQ3aEEjVSW7Hzkxn5AoBphyoNbJztClP7WmolM/C8U8FoVlj5FR1HBbxyAISfQqrZGyQTuG6Ny9rGtO+O9V9QEPFrBtFJ0pAfITDyiH5k47Ax22hu/JahRVx/kA8Rxg1MW/YSEWzliS8y1SvHeJo3R1gi+/YpaEnNsqjHJzpI4ejp0Qex8J97HlAsrDQ4wBbbCSDOP/NSH0tpk5zKpXExDXaiGn5Acp+TUUEs/WA6pv72qDOXUq76BZCYyxzcoWDyYYflI87E13rKw+nV0XSU2x5cMJ6Xjw6Okl6mB14vWVijHW6hQXaCB8I1hqjg=\"}', '2022-11-21 08:45:51', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 08:45:51', 'adminGQ', NULL, NULL);
INSERT INTO `ent_log` VALUES ('57fb0c10-674e-4903-8806-916abdf39a51', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 08:48:50', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 08:48:50', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('5c3e3451-d015-4447-a186-b9c759fb00ec', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 02:38:04', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 02:38:04', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('5cd25069-476e-4d4a-bf0c-a949e28bcfad', NULL, NULL, NULL, '/getOtherList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 02:07:49', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 02:07:49', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('5e9c7d4c-d0ac-423b-b5c8-9ce20d943949', NULL, NULL, NULL, '/getModuleList', NULL, 'status=-1&pageIndex=1&pageSize=10', '2022-11-18 03:00:39', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:00:39', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('633b3086-9448-4c09-b5c7-170e6e58b3e1', NULL, NULL, NULL, '/getOtherList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:24:34', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:24:34', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('648e0fb5-f984-4396-8544-dd68bb11420f', NULL, NULL, NULL, '/doLogin', NULL, '{\"username\":\"admin\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"code\":\"3539\",\"key\":\"142c65e00f4f7cf2e6c4c996e34005df\"}', '2022-11-21 08:48:49', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 08:48:49', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('66300780-6d5a-4482-bfff-6eff05acb07c', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 02:13:19', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 02:13:19', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('6661e931-65c0-4946-bf52-7a321883c41a', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=a4a3e7e0-6252-4a27-a281-80684d50882e', '2022-11-18 03:19:42', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:19:42', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('678ad10d-aef6-4b8b-83b8-4409b4055fc9', 'adminGQ', 'JXSG20221121c85d', '静馨舍工作室', '/doLogin', NULL, '{\"keyScript\":\"wqiuHmbNYMAHwarYOrseFpshd03JMJoF79Gmk0lJAzxInzAjkYgZlALDtJJwG5AoadEP4UVQS8uGurPPcLr0urjnoU6c4UrT6JJkHp1SDQpMPTfo/15uhfz+QrCQzUBffRpkL8dLH2AuJqMSEzw6peKenb51vl2AhQeOdqMPmG3kKjfw35z5TQilCdGWPN2V9m1lfN//1yhJb5co48zUnJGT3gvF7KWFGXtbx84EvjP8lEFwx/jg8IIfutsxW/kkNhUYkV8SMe3cC8A3Vc2iA8D9IAAAkUp1tDeIgHVoC2Jj3Enav0xFY7si7g2F4YL5IhacEvTiSBi01X6YCwVZUhvwCvF8w0WB6KueJ8MY4xfYV1H3F9XgccUSgzXFR7PP4MljlOAFaTofB1MBqXzj/CYuVobTH18btUzwlu9fPcyCCHKmMFu/Rt6xrqJFM9PKRrBUKzOuCDbDCJplrnlLQm58KLvLqCNXix8tkmPoC73kTSRQ5gmtQz0khx3vTdNbQm+jruoDFiwwQFXVkeNvl5JpWH/JOiRaqS6KA6iNv2Aliz/TRxwO/FbPAuM8wiYhYa2CYc33uoNDCbkVOdim6asUo/+WAXsVwkQMGlFaR9ghKX5zLTgVLWbeT7edQ0pxC/AupIwO2XwLghZtVuSPEV/lBCgGWWhjLVtBmVvqzto=\"}', '2022-11-22 02:27:48', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-22 02:27:48', 'adminGQ', NULL, NULL);
INSERT INTO `ent_log` VALUES ('679fd8c4-012a-4760-9727-c18c8619b976', 'adminGQ', 'JXSG20221121c85d', '静馨舍工作室', '/doLogin', NULL, '{\"keyScript\":\"tWt1moD86QF832m9k/30kqGcbca/MqwLC5ByRo0SG3zI3TVrvnTdWksttdv0s0Wk8pwpEMmIDj30p1Y+NRVU7SGEwz/qlWZFGT1JZBnLjzPpXkXXHqgnJ09XyjW5vCiwmNjylGqq4vhmRe24UpXsIgstIOx0MPiSFt1YDRhimDDHRylKhjJpjUU1imlTKwaedQBTWFmXz3YEWwbCczFDqQLTKnXOCqS4Do/g/FysH7i104J5yu1Vd+IAlXzX4/URmlScqFK9d6Iz/GHkW9fDat6GgIA8ayhFzNPE5nnQvkAn3cFtDLgOWrlxT0k/9clPorooNVYwvXM7Vsy0irqnM6uMmPsdb898EB80kMctEzemxxbO+ThVXTey39D0wRrI3hKOG8/9Fsu/T7GEQNXTz7oXb1UtigdYPT5YNCDNVNmWEo+QsU6gY9lp/InxUb+Nn7PoTCGqE3ceYeUzhk4YqH+CsXvu7gwUCwsNjJ1t/eD/gjIHrNrHHF5mjR1wnaiNWitbiqKz0t8U3zKLL7qm8eShJsb7ScRjujtSuwPjEwIiN9SkRyv0CK4qyFtwtH+bqt6iZaCInW6+GihZWhyOj0AI0JqTaTw8YXjB5gqcMOqFRJexXlUpflN2UdirrvSmTBr4NrQAhypuX/XIZrCouX3Ro06fR3smuH/rpQeshwE=\"}', '2022-11-22 02:29:40', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-22 02:29:40', 'adminGQ', NULL, NULL);
INSERT INTO `ent_log` VALUES ('688b4048-4706-4bdc-b28e-cb14a5fcdf13', NULL, NULL, NULL, '/getEnterpriseByCompanyCode', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 05:20:25', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:20:25', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('68fa33a6-b018-45f8-a180-55e6e4a82516', NULL, NULL, NULL, '/doLogin', NULL, '{\"keyScript\":\"A1E5IjU3HTH5v0Q46heWwE7ZQibjgvPAMBhpoPykfR4EMczqoEFTAabWNot1OY1Q6rcP3lk5x7DwPXxVSzpZGIh9jvIFbuVE2qNoJSYCpb6Cn3MG+NN2dlj5yjFW+FaRrTfMqA4GDCTgXoLSYQJxSgyUwsiVWl7NeYVdkYjTQ9sdPWt8DygJM5XtA7osoCnPrvFr6YeT8Wl5fX79scw5sK8Sk+u6F0VELhRUkR9TFOGEOaPp/f3UEAiktxZxw2Gh9UbBWpFfMi2vjTJ5jIvcF9W638YzzRR5i3VsKtgxtsxhn/abj2KrBN0pruIjFy3/u++QC0HaOTq1H5c/NSl+T2JTlsXYcwiqJGsKiWHwQ7Cody2Y55w5uFc8LxiZV078KLRpA8w1C++YCnlvMfjXjfgwhrZZWnyxSb4xqQBQDdrUWxdK8s5HZE++yt/ai5ClgYVY0zN1CqRRAuj340Eb2yI3BvihS4EHd7GfEFdh2LgO9ln412r/Ce1Qsz+fWWzeoYahZyE483EoXi3Yrw/baKhmnm9ZmLlLUXlNfPvDmgnPBPGnguKByYtghq8sUDHzoGUKrHD+8eBT0mnz9/0Hf5+II6wk6nRVeBD8dHhvvdNvgLrey0mMpbGsAQHDocQ9AEjo0sh2ZBc7SBkctxypLdTYJG2HbajgZtTxZGn6RrE=\"}', '2022-11-22 05:22:58', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-22 05:22:58', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('6d06e3d6-e52a-470c-acac-0c4f4987347d', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=c0bd9ae4-6375-462f-85c0-526aba1c486a', '2022-11-18 05:45:35', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 05:45:35', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('73bc0a13-5330-42bc-98d4-6b920d72855e', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:46:28', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:46:28', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('76cfb815-e51b-4496-98dc-d99eefc35f97', 'adminGQ', 'JXSG20221121c85d', '静馨舍工作室', '/doLogin', NULL, '{\"keyScript\":\"n/JjbcN/O6QpFxrflVYwn60t8r4uowf35DFDlhkaat0z+8L9gsaYkL2FOpjHgTjGOvbuEmozzqFwih+WELnRPsb/G+Lke97ROOShLPaOa5pWXXYfPbRDOwUhaXundMyxy3kBeCCiKBII8E/nkDKOaR44J165znu8eH7xfpE5mRyiOYt5TOfRNHpvc+dzVWyekQQKdWrjVNSpdlQ/z6d4Q70saKOfLxDTFHBcDDYQUbQ3YQLw7Dg+VzjnvFatg68ND2y14Co+DqIFgShIXx2t66hpfJbbm4IJo2lN/Y4MYF7ef0IJG2fxZlB+2s7Wwq1Et57Vi1NHH7t0/vebaZQ1+a+lox0RrjXsyf36eiJAwGBT3xMZYb0tWaIQW9eT3iJo8qJmF2r4KvKQv/OjXw9p7iMqwYFfOIH4WuQch3+LkSZw7+Q1Jrq3VNKX8EK+76U/lyCaXrMXzFLL9GU6ljNBLhrCQAfpPujRwfwLKXhudX87EmZjw6veU+yPp3nA1+4YKZ53OttrdjsPziZbAFqmX6xRK4gGQXWusF/pUF/dOJtW3b/32JyBcUT+zY8V6TSf5ZSyTvi5+Fl32PAQJhe7DzRzkev/7fi/U1p46/B3fsuqTsLuA8BxUEeSQqeA83Lfb8JbYd2UYBsO5YAu1RuCc1Fwa64oUz9FoNy9yGHiqBc=\"}', '2022-11-22 02:32:52', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-22 02:32:52', 'adminGQ', NULL, NULL);
INSERT INTO `ent_log` VALUES ('7851ddbe-37bb-417c-b6df-d863972ccf1d', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:46:31', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:46:31', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('7e7628f1-4018-477a-8e48-113dfe473bc7', NULL, NULL, NULL, '/getOtherList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 08:48:51', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 08:48:51', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('802bca6b-4e75-4d56-be33-f7979de0f538', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:45:16', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:45:16', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('80920d84-7d36-45a4-a6ab-9ff789ba9406', NULL, NULL, NULL, '/getModuleList', NULL, 'status=-1&pageIndex=1&pageSize=10', '2022-11-21 08:48:53', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 08:48:53', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('822f3d9d-023d-4201-b3c4-6bf8f593fc4d', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=e3a1972b-5637-4e01-b080-670d555cf26f', '2022-11-18 03:20:46', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:20:46', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('833906dc-9795-4445-9787-e7cc2a4e37ee', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 03:08:18', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 03:08:18', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('860bfffe-6471-4a00-ae0c-ecaf35c18912', NULL, NULL, NULL, '/getOrgById', NULL, 'orgId=f01b7758-744b-4c3f-b531-9c784464a8cf', '2022-11-22 06:09:12', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-22 06:09:12', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('860c8627-e509-4c52-9530-265a9d767119', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 03:10:41', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 03:10:41', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('8869570c-3064-4fc0-b6ee-70b43c66ab0b', 'adminGQ', 'JXSG20221121c85d', '静馨舍工作室', '/doLogin', NULL, '{\"keyScript\":\"LQbITqHOv+hHsWDcChKhFWeP+TAnpYJMyaYoaVYZa0/aPrh1G/KJkkRgJnXguhD0le4knaRdDsZJrFICR5pwM3uMbNcoxRmmuxOr9cTPIMXKCVMipm8wOiTCNQLIAeirr2CblzqKVWiho3jOiPFzOIIMpCdaN2bNWb73LiMbzQAn+vPGHA0MlfeBXdKOdOUa7ekpPDqNG7R1E3FLRm81kuvMrQOib4WiZ6Q+qkeGKOXNTJTHUTI/xhJxMhEdznulP99/mJl7nT0dRP2Z++ugDpyAdHRDYgCHmzARLOCbWvCSHnzQkfIaDTZ4AHAeXXX6y15kbz+dRCRDiE5AJbYb1IERT9TMAO42Wjwz2e1Lj4M1xi9aRhAfc3MtfoNEhA+ZH8tWcfDB7UxrBMuQLMzbjUioT+Q3TYOTmuEVux+rpGkswZgJYY9DMK/Jf6WVaVGwsNJnXx8gNTrVT6meHfj0LIw493GQr1ZOHocMC6vl7/q+crmj05Z4pUT7U9U1hbxn9hvwCqgszAhZi3Qscv/LcNdx3rc4Rvf1tw7dp6rcMOdB6nfT3ZxceP8sd5HqsHjU/80NFWSV9NKfwj3b3RB3y3Kh0ancFYxUDZ/JpN+NSTozThPd53xzP1cuMDyWlNzE809nsId32y3yQVZHR0dIK6RKD2MNqKVU7vRYmzUt3fQ=\"}', '2022-11-21 08:57:35', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 08:57:35', 'adminGQ', NULL, NULL);
INSERT INTO `ent_log` VALUES ('88733c8c-fbad-436c-bb25-cfc9a1bd50b8', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:38:31', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:38:31', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('897e19da-e79f-4264-80a7-596b4968132d', 'adminGQ', 'JXSG20221121c85d', '静馨舍工作室', '/doLogin', NULL, '{\"keyScript\":\"TYQVLxUfTFHAJjwtsUIa8DolE/GJjYiNjn/QEM01+g5rA9pqt/TvSy78a8Z2AI1Jr030PVXceo2gYA2ewGQD3a2egCe17h1mFmky9b/J2mC/IkMt5YaznA8bAejaOtLdWxUmrW/xdr0Ya8aA/0lJLF15jYacKDFF9Jyw+fWXQ1HE068qY/KCKDTh53xg1P2QqjVETyLNReZQErRost3Gxyy4k3OlwRK3hHS0V9/1AtALEFnAWFVGqvYmju3fzUQTl3U+GX0+I0k4/kDMny4dkk+ir3UKsU7ckCYW0z8+oed/yqX4vpHK6JDVXNewPYMmwKeLKJ3PTDq3xsFO+h1bHtA9bYJObbcaUY2qy4V+fwBUZP7dW4rEiOkKljbgSVzPcIaCwhROVlHHF88U1sfiQAPwKYrb9wt2T/ODoacqXvzIW5GOeIs6+BMkK1sZGY5zUaqqQROA23DFv0mPGC0S/Q/6N2zbIgcqzjPoUCgiDTZ4AdHhDR02DwbUIV7cguS/jSdwC+x5a54q8O7jIaLZRnc0y1zo2mx/KUAyJDoVNi/GZecQzHeD0kdQ9weC1IrhececIZK/+WThOVz1oTWCAq2NEjsipu6I8gWKJt6GuxziffODg11qiZp1dfJ+Z/L5af9jZtwUkGfPjFWG2oCjuBLBMVwvSfwohYOgBphiV08=\"}', '2022-11-21 08:52:45', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 08:52:45', 'adminGQ', NULL, NULL);
INSERT INTO `ent_log` VALUES ('8cb8f012-c1b8-4a49-b08b-10a6f2e7a87e', NULL, NULL, NULL, '/getOrgById', NULL, 'orgId=e2655d9a-d5e1-44ab-a4cc-7806b3f5b88c', '2022-11-22 06:09:29', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-22 06:09:29', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('8e73b965-4fb2-419e-9651-d8823437382e', NULL, NULL, NULL, '/getModuleList', NULL, 'status=-1&pageIndex=1&pageSize=10', '2022-11-18 02:18:16', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 02:18:16', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('95d2580a-230e-47ee-88f7-921494969ab3', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 03:22:04', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 03:22:04', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('9718f1a5-6331-48ba-aadf-78f8f7af83a5', 'adminGQ', 'JXSG20221121c85d', '静馨舍工作室', '/doLogin', NULL, '{\"keyScript\":\"ABQG62C3Z0w7c+0UDQxaQDmIvJLSa541AuMowFcfHGlYP3FXa2N0EayDZu4cYybbooP3UIWor7Z1sfb/GD0PEg2IHUn6CVIFIs6ckhOqmR3ZiI/qWpStpkENCW16pWebMAeBFrThwb+3XUKsX0adNfuLfGUwVBniFfFseUGIsV+cAwHN2ggubI9SjHppIN08pEo5XgdFe6mWO8ZVINJGuyuKQL923NcbF2bfnStIcN02cN54einfm4n4Lu+z7XY5HbqfNqHvYxj8QBfhstCoBFwUcWS5p5NL5WF1py7f7zPK0RLRzvF1HaSTjg8pRriCcoNyDQEAOYAFML6nNp9DZOjLe1xG8eez0dNemvZyQQAavl1M6Q5oDg3dRY3YkYjLqDFJUSfIBoN1vSNB8gWAsAYevoddgimlDZRc+zAz0JLIsUBCXYLox/IyPIPGeMlF5bHMadq9m1FumzgRujU/XSxi31Yw1jj3Ob7K1Z8CbROkLjJlnQ2sNkh4R/WGvYTwL67+VSHNGwQgIGLlQApOjbiOXFzum/R/IsxIJLuxkwczdp+YA7FBz9XbtdEUoSL6Ybu8BNuE0wYN1CV6bRhYEnCuEMRmBprhUzPqYXKKHK0uC19rnp65tGojqQIhA2mRsjOosEnPLHqn0Sq3S5VFxsi2WsWpCzwxNXLJJICmupk=\"}', '2022-11-21 08:42:48', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 08:42:48', 'adminGQ', NULL, NULL);
INSERT INTO `ent_log` VALUES ('97b42736-7b56-425f-ac3c-3c522a79bd24', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=c0bd9ae4-6375-462f-85c0-526aba1c486a', '2022-11-18 06:44:56', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 06:44:56', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('97d6f1be-2bab-48b2-a390-4eee9e1e12a2', NULL, NULL, NULL, '/getDictList', NULL, 'status=-1&pageIndex=1&pageSize=6', '2022-11-22 07:20:31', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-22 07:20:31', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('987a7418-c73c-403a-bdcd-2009dded5a93', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:46:07', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:46:07', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('9fc01290-c911-40ff-8106-735dc3e4d37b', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=a4a3e7e0-6252-4a27-a281-80684d50882e', '2022-11-18 03:26:40', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:26:40', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('a0b8d027-51a8-4fbd-bd07-582091bcf45e', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=a7d6dc6b-c9f3-4763-a186-4a86840f9e4c', '2022-11-18 07:05:35', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 07:05:35', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('a43e0800-ea60-4d2c-a00c-2735e4f25367', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 08:48:54', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 08:48:54', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('a71534c6-44cb-4bbd-92ef-da31e705928c', NULL, NULL, NULL, '/getModuleList', NULL, 'status=-1&pageIndex=1&pageSize=10', '2022-11-18 02:33:34', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 02:33:34', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('a8032dcb-ad65-47b5-9cc9-1d3795f066a7', NULL, NULL, NULL, '/getOrgById', NULL, 'orgId=5f99cbdf-4218-4cd9-b33a-3bde583fb044', '2022-11-22 06:09:19', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-22 06:09:19', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('a8f40338-75bf-420a-bd7b-556ddfbee9b0', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=216da748-27f7-45cf-9986-9b40e9ecd666', '2022-11-21 01:57:54', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 01:57:54', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('abd14fd1-fb19-4374-90a4-379fdaf93fa2', NULL, NULL, NULL, '/getMenuById', NULL, 'id=a4a3e7e0-6252-4a27-a281-80684d50882e', '2022-11-18 03:04:46', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:04:46', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('abe9fd3a-25f1-4e13-8c4e-e145d39cfb82', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=c0bd9ae4-6375-462f-85c0-526aba1c486a', '2022-11-18 03:17:07', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:17:07', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('aee06c1b-4f93-4d15-9a83-a228463699cf', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=a4a3e7e0-6252-4a27-a281-80684d50882e', '2022-11-18 05:34:21', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 05:34:21', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('b0dd882c-32bc-4ae0-89ca-a997d212e9c3', NULL, NULL, NULL, '/getEnterpriseByCompanyCode', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 05:20:57', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:20:57', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('b6d09cac-8ac2-4f68-aaf4-7e6ddfbe80ca', NULL, NULL, NULL, '/getModuleList', NULL, 'status=-1&pageIndex=1&pageSize=10', '2022-11-18 05:34:15', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 05:34:15', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('b955929e-6383-4005-9464-a0b2dfbfe9ee', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=a4a3e7e0-6252-4a27-a281-80684d50882e', '2022-11-18 03:05:06', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:05:06', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('ba15f698-1142-4728-9d54-43a509eb4a26', NULL, NULL, NULL, '/getCompanyConfigList', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 05:21:48', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:21:48', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('be0a0242-ec6c-45b6-88ad-6c0227c0739f', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:46:36', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:46:36', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('be7c64ef-6d94-4047-86b5-42c481740092', NULL, NULL, NULL, '/getEnterpriseByCompanyCode', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 04:02:17', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 04:02:17', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('c5050a27-2aa9-4179-8c4f-c9a5ec46e3d4', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=a4a3e7e0-6252-4a27-a281-80684d50882e', '2022-11-18 06:57:17', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 06:57:17', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('c5989a4e-f722-4ea1-9aed-10c17f6c6092', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:16:15', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:16:15', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('c6b19279-d044-48f7-accd-868f0c61256e', NULL, NULL, NULL, '/getEnterpriseByCompanyCode', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 05:19:10', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:19:10', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('c6c648d8-5e82-4988-a777-f2cab2ec0a45', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:25:09', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:25:09', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('c7317505-e6a0-4701-9f27-1605899ec71d', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=a4a3e7e0-6252-4a27-a281-80684d50882e', '2022-11-18 03:17:03', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:17:03', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('c76a1a82-9804-4f11-a9da-e1b227cccac8', NULL, NULL, NULL, '/getModuleList', NULL, 'status=-1&pageIndex=1&pageSize=10', '2022-11-21 01:58:27', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 01:58:27', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('c97adc74-c65f-4c18-b10a-4b252e7df796', NULL, NULL, NULL, '/getEnterpriseByCompanyCode', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 05:16:18', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:16:18', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('cb7d98b4-36d4-494a-9d0b-8b2a98f47d73', NULL, NULL, NULL, '/doLogin', NULL, '{\"username\":\"admin\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"code\":\"0613\",\"key\":\"71f1c79e7e368a537e891c49552fa4c4\"}', '2022-11-18 02:18:06', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 02:18:06', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('ce3d1a80-7cbf-4b71-ac83-489c898ba25d', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=a4a3e7e0-6252-4a27-a281-80684d50882e', '2022-11-18 03:16:44', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:16:44', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('ce89149a-152d-464b-93d1-301241cf39b4', NULL, NULL, NULL, '/doLogin', NULL, '{\"username\":\"admin\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\",\"code\":\"3410\",\"key\":\"c6f798b844366ccd65d99bc7f31e0e02\"}', '2022-11-21 01:57:24', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 01:57:24', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('cf208fd5-3d0f-4a50-b7fc-a1154d598479', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:42:35', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:42:35', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('d1e173c4-3649-46b4-b0d4-c0c21c63da01', NULL, NULL, NULL, '/getAllMenuDetailList', NULL, 'parentId=a4a3e7e0-6252-4a27-a281-80684d50882e&moduleId=04c3ea3f-18b5-46bc-83d4-f45c573a6210', '2022-11-18 05:49:57', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 05:49:57', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('d31b71ea-158e-4e5f-846e-b389d22d1a47', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:17:43', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:17:43', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('d372776f-f87c-425e-880c-9ca921fc6edf', NULL, NULL, NULL, '/getCompanyConfigList', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 05:20:40', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:20:40', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('d4dbe989-81d6-4989-8235-37991b3b8195', NULL, NULL, NULL, '/getEnterpriseByCompanyCode', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 03:32:39', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 03:32:39', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('d6488a38-7b57-4891-9b1b-b3c56cd23b3f', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=c0bd9ae4-6375-462f-85c0-526aba1c486a', '2022-11-21 01:58:03', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 01:58:03', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('d8bd0041-c442-49c2-8419-9abf7daea722', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=a4a3e7e0-6252-4a27-a281-80684d50882e', '2022-11-18 05:45:11', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 05:45:11', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('ded85baf-54f2-4847-b639-aa1c09178a7e', NULL, NULL, NULL, '/getMenuById', NULL, NULL, '2022-11-18 03:01:57', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:01:57', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('def47577-05cd-4066-b995-317b29a0fac1', 'adminGQ', 'JXSG20221121c85d', '静馨舍工作室', '/doLogin', NULL, '{\"keyScript\":\"mfSOVIrDYQerZoPtSlaTgqTn48WbugqtvSzSU+ngMXGj5x30CMshhiZIBJTzf6RJmFvwL6Wi4crSsa5rOOPyE5Dw5BCoqoLChPaMxTv3hrneV5Y4Y/XcZoVVkVTNAISh5gEzz+DwgCjAt6VEf7mtgkobfrf7Lc4cqTxxIg3+r+SHfPNPFxmp7DNPnYpFzUUzJquF1BLwoZwrHr8XcXbyQM/PFDM8hpqx+vOlcsIU3hQt8AVqPPvzujuZa117T3MA1V/P4gKMPjGuOvuSnAEp8ZWjdUQwo2SD4U1IxkF9NU3S7pzSmgi2eueHDun94mAjftN83+TsxywfZ7dr3l4Z78us8qCg3EKL4EipANDOJn3nq/OH5yWHcTX4u6EPe7wUh27eqHE9zh5/mHGSaF1bWtznMXwCGgWT+oF3wnWqnpOzrgTwEaPwzN/wfzO5zvrJXbeb4CBr/n4HRuI5jwm6OcBr2NCmQhjqjvqmJDldfJhWW21tcsoHuRjY35MG7hrdETEPVE2+HXOO/c2m4rfmm2ztaMG8CbKQnGg5FRlpwaJU60Udmlm4/HvXG0yKLX0o1bnOyKzoxkzbQeNWm0fFwqHlO2e3jcXAkqM6KunHUWaj2+AO1wh/VfBnZLTSceQRdO6vKRwOXeZBYbp/abHEVRaDiTREJcA1+v1mfUTxUgA=\"}', '2022-11-21 08:44:25', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 08:44:25', 'adminGQ', NULL, NULL);
INSERT INTO `ent_log` VALUES ('e3c373bf-8550-4a20-9643-c9cf0cdd58c4', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:46:39', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:46:39', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('e53ec249-0698-41f0-9b66-1f36814708a6', NULL, NULL, NULL, '/getOtherList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:25:06', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:25:06', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('e80fb420-51f6-45ce-9265-b6acb95c6c70', NULL, NULL, NULL, '/getOtherList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 02:04:43', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 02:04:43', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('ecaefac0-e4d3-4c0e-97c3-55fa5937ea24', NULL, NULL, NULL, '/getCompanyConfigList', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 05:17:56', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:17:56', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('ed1dfe32-de4e-4588-bb86-94c8e3c653bf', 'adminGQ', 'JXSG20221121c85d', '静馨舍工作室', '/doLogin', NULL, '{\"keyScript\":\"aIcUAiXTX9C/XwjTFZ7a1OxJX1skOLLSEc8n75HFDFMWmtYGniS9Vwbl6QpZn5NHzYsKCJNjxhy5Hw6DHvNAr5uvZHVX2jK3k1p1HfOAUVMcowz/6H5w169kJs+2Nb6IEIhvdHfS4iJ/4ZAZbjeYTE2zpgc3Ep6WYQ+uAkYU7Bdn5tVOsQuAcZR8Y4oJhC0JYFTP1a0kHgzJ+ZH4JIBHX4dPkDJc4CMXv+x2ZSC5rRihnXYF+hYdyTxAdRWtGnJjy4bXpRshTQVBiExgffO2ptJZLDXuwj0lkZ0zk9tsfCZndpVfQ989OWJWSuO0mUBmJyz3CtlnczmW/BB2iXKfw/T4U2TzFJF/o2sWNcXo4U3JDZQEL0QCJ2t9Wahhhv/yM5PpDuVRqExKDJavffj42CwPh7CsHhE7OLivkiQcY8ttEvZsuUpPyFPjKrfkmHAzCD68zlcPHb7msQgXAs0VKVH6WW4TyKqDtbpcvsU5QkD5rXuUYzTDK5HFDfH9wpF8ol9dxlAqU1t0RZDn3sm9KRZFO05iu1Qk7Ntqj1pdC6/6baibZEjpmIMrOG7GcRHOIFbooU6whpxHxbXbzC7uAtaTtXj/fUKCiHcC3T7zsP+qS1uvcv12GgIWx/5sVbyOsCXJ5WGoF+eoRiHAHlqcHia9xHeXcie7XMj5aueIBf0=\"}', '2022-11-21 08:58:35', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 08:58:35', 'adminGQ', NULL, NULL);
INSERT INTO `ent_log` VALUES ('efdfcd89-4a82-4013-95ca-d9887e723334', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:36:49', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:36:49', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('f0107827-26e3-4a79-bad7-e3307cf9310e', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=c0bd9ae4-6375-462f-85c0-526aba1c486a', '2022-11-18 05:34:25', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 05:34:25', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('f4be9f73-3fdb-4e42-8f86-a3b0880a7061', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:24:01', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:24:01', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('f8f0e5af-f66e-441b-8036-ccc7aa808fa1', NULL, NULL, NULL, '/getOtherList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 02:37:02', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 02:37:02', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('f9d8fcbd-fc53-4c59-b163-d9192c6955bf', NULL, NULL, NULL, '/getMenuById', NULL, 'menuId=c0bd9ae4-6375-462f-85c0-526aba1c486a', '2022-11-18 03:26:47', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-18 03:26:47', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('fb934199-2b01-440d-b91a-e7e56abad46c', NULL, NULL, NULL, '/getEnterpriseByCompanyCode', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 05:17:28', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:17:28', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('fbcc05c6-eac7-425a-84ce-22c19b01ecb0', NULL, NULL, NULL, '/getCompanyConfigList', NULL, 'companyCode=JXSG20221121c85d', '2022-11-21 03:22:08', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 03:22:08', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('fc5b8b37-7d67-4d59-a19c-212ef908281f', 'adminGQ', 'JXSG20221121c85d', '静馨舍工作室', '/doLogin', NULL, '{\"keyScript\":\"rtXVyg9LeGNBew/1zuXFRTwgajvjxz8SP5tHaDP24y2BsY3+JzgBuoZHzFS5WGzqq35jPA1wGtLNOhgmRvj3aX5+9Y+uN9eFdZ5yloQkmwnaCyE8wmYHGnbwZu0+yYqNhKMxfyUa8OTeSlAePo6S+qt6GJm9NUC/X8MNLsmQAZ7NQXJ/Gj/BvuU9ovHfkP1AuD1kEj5aAUNZE/e+GkeUhGm95fTjrcXfT7yNGhNj8YUwiHGDOM3x4ZWrRGnWluVFBpQ8b8jSzIlEQlU8ZkeTbCtqFwD34FCdOKmgxLRqwHGjFjA0T7+Rs0kDNQf1heMTVl+9p0sVUJ0PtmDf8pnhBMIKMNXzMle0Nftys2mAEKxQC17mzyrS98508R3LClL5rKDazpTfDx5f33m37tQl6wCKMBlY3BCs6XH1P09aKxG/qIt4SUJ1dhdttiTGppCjLzfFby0+my3R1T/5KJGHOVwvmBg3Ew3ky05wQWpdOwVmkD2laiX8C8u87OmpUBEYR2448G+Gw+vp2aWBryjuAzMJR07G1qAhluPVvatmyxRx2RtGGkhsd/j12eJLUZfMI4oEEos6GVZaOwT6hhI2fmKt0XeJswUffUjzmNb/3eFtGcAy93I/KYB/yBtmUtM4rKtCDthSXfsLFONA8yuketRlQ4P++xf1cT2SSc5psBs=\"}', '2022-11-21 08:37:01', '192.168.102.19', '0', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 08:37:01', 'adminGQ', NULL, NULL);
INSERT INTO `ent_log` VALUES ('fe3ef67f-f950-4cb4-bc11-3bcf625270be', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 05:20:28', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 05:20:28', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('fe6f39e7-0315-45a5-b3ec-b6c01d7b2432', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 03:22:00', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 03:22:00', NULL, NULL, NULL);
INSERT INTO `ent_log` VALUES ('ff515c4f-2353-4ac8-af66-90ef886df1f4', NULL, NULL, NULL, '/getSysEnterpriseList', NULL, 'pageIndex=1&pageSize=10', '2022-11-21 02:37:04', '192.168.102.19', '1', '0', '1', '400', NULL, 0, NULL, NULL, NULL, '2022-11-21 02:37:04', NULL, NULL, NULL);

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
INSERT INTO `ent_menu` VALUES ('066a7c76-062f-4b2d-b08d-f1caf8c9360d', 'JXSG20221121c85d', 'admin', '2022-11-21 05:20:28', 'admin', '2022-11-21 05:20:28', 'b8bf4f17-0749-4251-b547-856ee4dff8dd', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', '#');
INSERT INTO `ent_menu` VALUES ('1a9d0c6a-cf28-44cf-9f71-d4be3f3a041f', 'JXSG20221121c85d', 'admin', '2022-11-21 05:20:28', 'admin', '2022-11-21 05:20:28', 'a1ca0716-1236-4acc-9bb1-1708a2199f6c', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'a4a3e7e0-6252-4a27-a281-80684d50882e');
INSERT INTO `ent_menu` VALUES ('1baa5963-2018-49b2-bcfb-032fc81f7b1a', 'JXSG20221121c85d', 'admin', '2022-11-21 05:20:28', 'admin', '2022-11-21 05:20:28', 'a4a3e7e0-6252-4a27-a281-80684d50882e', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', '#');
INSERT INTO `ent_menu` VALUES ('434bed16-c7b1-4b99-95ce-21d38d416b89', 'JXSG20221121c85d', 'admin', '2022-11-21 05:20:28', 'admin', '2022-11-21 05:20:28', 'e3a1972b-5637-4e01-b080-670d555cf26f', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', '#');
INSERT INTO `ent_menu` VALUES ('7f0a84ab-959b-4d38-8e0c-78b824daea07', 'JXSG20221121c85d', 'admin', '2022-11-21 05:20:28', 'admin', '2022-11-21 05:20:28', '2443e4b3-033e-40c9-87e7-a818c32fd113', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'a4a3e7e0-6252-4a27-a281-80684d50882e');
INSERT INTO `ent_menu` VALUES ('a9d4f095-56d6-4d97-8714-9734285bb2aa', 'JXSG20221121c85d', 'admin', '2022-11-21 05:20:28', 'admin', '2022-11-21 05:20:28', '216da748-27f7-45cf-9986-9b40e9ecd666', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'b8bf4f17-0749-4251-b547-856ee4dff8dd');
INSERT INTO `ent_menu` VALUES ('b5fa2c76-e7e9-4224-bd34-4cde94e07fa4', 'JXSG20221121c85d', 'admin', '2022-11-21 05:20:28', 'admin', '2022-11-21 05:20:28', 'db095c80-111f-4326-b183-d2e43e343f04', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'a4a3e7e0-6252-4a27-a281-80684d50882e');
INSERT INTO `ent_menu` VALUES ('c978287a-e197-4bbd-a649-98e5b797c0da', 'JXSG20221121c85d', 'admin', '2022-11-21 05:20:28', 'admin', '2022-11-21 05:20:28', 'a7d6dc6b-c9f3-4763-a186-4a86840f9e4c', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'a4a3e7e0-6252-4a27-a281-80684d50882e');
INSERT INTO `ent_menu` VALUES ('d4acdafd-a9a8-4f89-9878-6e93cd6bc334', 'JXSG20221121c85d', 'admin', '2022-11-21 05:20:28', 'admin', '2022-11-21 05:20:28', '69b60731-6b35-423f-a52a-bb524fe0ec09', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'e3a1972b-5637-4e01-b080-670d555cf26f');
INSERT INTO `ent_menu` VALUES ('ea92552c-b7eb-4087-9f1f-98296b6530af', 'JXSG20221121c85d', 'admin', '2022-11-21 05:20:28', 'admin', '2022-11-21 05:20:28', 'c0bd9ae4-6375-462f-85c0-526aba1c486a', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'a4a3e7e0-6252-4a27-a281-80684d50882e');
INSERT INTO `ent_menu` VALUES ('fb386c27-c199-4dab-8da0-8fd8a6c041ee', 'JXSG20221121c85d', 'admin', '2022-11-21 05:20:28', 'admin', '2022-11-21 05:20:28', 'fe98249c-0d01-4ecf-85a3-e360ed21f172', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'a4a3e7e0-6252-4a27-a281-80684d50882e');
INSERT INTO `ent_menu` VALUES ('ff7106fc-c454-4067-a4bc-4286dfb1f549', 'JXSG20221121c85d', 'admin', '2022-11-21 05:20:28', 'admin', '2022-11-21 05:20:28', 'c2d82c74-0b30-41e2-9c0d-5f4f861c24e6', '04c3ea3f-18b5-46bc-83d4-f45c573a6210', 'e3a1972b-5637-4e01-b080-670d555cf26f');

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
INSERT INTO `ent_module` VALUES ('b7b383a9-6899-49bb-ad23-f225f245b176', 'JXSG20221121c85d', 'admin', '2022-11-21 05:20:28', 'admin', '2022-11-21 05:20:28', '04c3ea3f-18b5-46bc-83d4-f45c573a6210');

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
INSERT INTO `ent_role_menu` VALUES ('044f6834-e57e-4aad-b8cb-830ff3b52197', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'c0bd9ae4-6375-462f-85c0-526aba1c486a', 'admin', '2022-11-21 05:20:28');
INSERT INTO `ent_role_menu` VALUES ('047008fb-685f-47c0-90d8-12d6ed548339', '', 'ff920c1b-d555-4706-8dc8-78a50d5cb399', 'bed7bade-638f-4923-897a-20c1ec56a123', 'admin', '2018-06-12 15:45:01');
INSERT INTO `ent_role_menu` VALUES ('0fc53492-8958-4a1b-ba3b-363b01c31dcb', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', '2443e4b3-033e-40c9-87e7-a818c32fd113', 'admin', '2022-11-21 05:20:28');
INSERT INTO `ent_role_menu` VALUES ('32f4caba-64f7-4880-a19c-1c4b933228ce', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'a7d6dc6b-c9f3-4763-a186-4a86840f9e4c', 'admin', '2022-11-21 05:20:28');
INSERT INTO `ent_role_menu` VALUES ('5332071f-589c-4b11-858a-28095b0c3cca', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'b8bf4f17-0749-4251-b547-856ee4dff8dd', 'admin', '2022-11-21 05:20:28');
INSERT INTO `ent_role_menu` VALUES ('63afc17b-8007-446d-9978-aaffac47c6c3', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', '216da748-27f7-45cf-9986-9b40e9ecd666', 'admin', '2022-11-21 05:20:28');
INSERT INTO `ent_role_menu` VALUES ('74a19a61-dc93-4af2-a6e7-bc3769094149', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'a1ca0716-1236-4acc-9bb1-1708a2199f6c', 'admin', '2022-11-21 05:20:28');
INSERT INTO `ent_role_menu` VALUES ('89413955-748c-420a-80f4-f556cae42baa', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'a4a3e7e0-6252-4a27-a281-80684d50882e', 'admin', '2022-11-21 05:20:28');
INSERT INTO `ent_role_menu` VALUES ('as56gh67-6112-4b57-9787-as4566ugh678', '', 'ff920c1b-d555-4706-8dc8-78a50d5cb399', '7d19cf26-f334-4c16-bebd-d8f789aa8d5c', 'admin', '2018-06-12 15:44:30');
INSERT INTO `ent_role_menu` VALUES ('as56gh67-6112-4b57-9787-f67829382742', '', 'ff920c1b-d555-4706-8dc8-78a50d5cb399', '82b25d15-dcf8-44db-87be-035d5c8fd630', 'admin', '2018-06-12 15:44:30');
INSERT INTO `ent_role_menu` VALUES ('b26af0a2-6ae8-47b3-85c4-57192ee3891d', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'fe98249c-0d01-4ecf-85a3-e360ed21f172', 'admin', '2022-11-21 05:20:28');
INSERT INTO `ent_role_menu` VALUES ('d16c193a-f4ff-4bb4-bb29-3c8ae65611a1', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', '69b60731-6b35-423f-a52a-bb524fe0ec09', 'admin', '2022-11-21 05:20:28');
INSERT INTO `ent_role_menu` VALUES ('d265c064-27b2-4964-a0cc-277989e77078', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'db095c80-111f-4326-b183-d2e43e343f04', 'admin', '2022-11-21 05:20:28');
INSERT INTO `ent_role_menu` VALUES ('f6c8ed9e-7bc6-468d-9cae-4a3a6c37259f', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'e3a1972b-5637-4e01-b080-670d555cf26f', 'admin', '2022-11-21 05:20:28');
INSERT INTO `ent_role_menu` VALUES ('face0023-aa87-46ad-a67d-7f2ce4cd530f', 'JXSG20221121c85d', 'a4713756-137f-4a19-8492-1af5190a1dde', 'c2d82c74-0b30-41e2-9c0d-5f4f861c24e6', 'admin', '2022-11-21 05:20:28');

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
INSERT INTO `ent_user` VALUES ('02c09399-ab1f-471c-b6ff-160ad082face', 'JXSG20221121c85d', 'f01b7758-744b-4c3f-b531-9c784464a8cf', NULL, 'adminGQ', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', NULL, NULL, NULL, NULL, 1, NULL, 'a4713756-137f-4a19-8492-1af5190a1dde', NULL, '2022-11-22 13:22:58', '192.168.102.4', 0, 'admin', '2022-11-21 03:19:28', 'admin', '2022-11-21 03:19:28');

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
INSERT INTO `sys_enterprise` VALUES ('JXSG20221121c85d', '静馨舍工作室', '1105107264@qq.com', '18256908359', '高强', NULL, NULL, 1, 'admin', '2022-11-21 03:19:28', 'admin', '2022-11-21 05:20:27', '恒馨', 'foxuetu');

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
  `desensitize_status` smallint(6) NULL DEFAULT 0 COMMENT '是否支持脱敏(0,否,1是)',
  `module_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前菜单所属模块',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统模块下菜单配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1223709c-bf3f-4f54-8a85-895cc8709146', '系统管理', '', 1, 'fa fa-cogs', '#', 'admin', '2022-11-17 07:18:36', 'admin', '2022-11-17 07:18:36', '系统管理,添加模块时，会默认生成一个系统菜单', 1, 0, 'dd44c89d-4c59-436d-aef1-abc6a3fbf4bc');
INSERT INTO `sys_menu` VALUES ('216da748-27f7-45cf-9986-9b40e9ecd666', '客户列表', '1223', 1, 'fa fa-address-card-o', 'b8bf4f17-0749-4251-b547-856ee4dff8dd', 'admin', '2022-11-18 07:46:48', 'admin', '2022-11-21 01:57:57', '', 1, 1, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('2443e4b3-033e-40c9-87e7-a818c32fd113', '系统参数', '/ent-manage/params', 6, 'fa fa-cart-arrow-down', 'a4a3e7e0-6252-4a27-a281-80684d50882e', 'admin', '2022-11-18 07:13:11', 'admin', '2022-11-18 07:13:11', '', 1, 0, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('69b60731-6b35-423f-a52a-bb524fe0ec09', '操作日志', '/ent-manage/operation-log', 2, 'fa fa-eye', 'e3a1972b-5637-4e01-b080-670d555cf26f', 'admin', '2022-11-18 07:11:36', 'admin', '2022-11-18 07:11:36', '', 1, 0, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('a1ca0716-1236-4acc-9bb1-1708a2199f6c', '架构管理', '/ent-manage/organization', 3, 'fa fa-calendar-check-o', 'a4a3e7e0-6252-4a27-a281-80684d50882e', 'admin', '2022-11-18 06:54:31', 'admin', '2022-11-18 06:54:31', '公司组织架构', 1, 0, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('a4a3e7e0-6252-4a27-a281-80684d50882e', '系统管理', '', 1, 'fa fa-cogs', '#', 'admin', '2022-11-17 07:23:12', 'admin', '2022-11-18 06:57:18', '系统管理,添加模块时，会默认生成一个系统菜单', 1, 0, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('a7d6dc6b-c9f3-4763-a186-4a86840f9e4c', '公司简介', '/ent-manage/company', 4, 'fa fa-anchor', 'a4a3e7e0-6252-4a27-a281-80684d50882e', 'admin', '2022-11-18 06:58:32', 'admin', '2022-11-18 07:05:37', '', 1, 0, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('b8bf4f17-0749-4251-b547-856ee4dff8dd', '客户管理', '', 2, 'fa fa-address-book-o', '#', 'admin', '2022-11-17 08:52:24', 'admin', '2022-11-18 06:45:32', 'app用户管理', 1, 0, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('c0bd9ae4-6375-462f-85c0-526aba1c486a', '用户管理', '/ent-manage/user', 1, 'fa fa-user-o', 'a4a3e7e0-6252-4a27-a281-80684d50882e', 'admin', '2022-11-17 12:26:03', 'admin', '2022-11-21 01:58:18', '企业下的用户管理', 1, 1, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('c2d82c74-0b30-41e2-9c0d-5f4f861c24e6', '登录日志', '/ent-manage/login-log', 1, 'fa fa-lock', 'e3a1972b-5637-4e01-b080-670d555cf26f', 'admin', '2022-11-18 07:10:06', 'admin', '2022-11-18 07:10:06', '', 1, 0, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('db095c80-111f-4326-b183-d2e43e343f04', '字典管理', '/ent-manage/dict', 5, 'fa fa-book', 'a4a3e7e0-6252-4a27-a281-80684d50882e', 'admin', '2022-11-18 07:04:13', 'admin', '2022-11-18 07:05:31', '', 1, 0, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('e3a1972b-5637-4e01-b080-670d555cf26f', '日志管理', NULL, 3, 'fa fa-commenting-o', '#', 'admin', '2022-11-17 11:52:25', 'admin', '2022-11-17 11:52:25', '企业端日志信息，登录日志和操作日志', 1, 0, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');
INSERT INTO `sys_menu` VALUES ('fe98249c-0d01-4ecf-85a3-e360ed21f172', '角色管理', '/ent-manage/role', 2, 'fa fa-calendar-plus-o', 'a4a3e7e0-6252-4a27-a281-80684d50882e', 'admin', '2022-11-18 06:50:35', 'admin', '2022-11-18 06:50:35', '', 1, 0, '04c3ea3f-18b5-46bc-83d4-f45c573a6210');

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
INSERT INTO `sys_user` VALUES ('fb616e4e-2f24-4666-9fa1-e0749a60d228', '20b99b5e-3d9b-4591-a501-f15dd6bd00a7', 'zzl463381', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '超级管理员', 1, NULL, NULL, NULL, 1, NULL, 'ff920c1b-d555-4706-8dc8-78a50d5cb399', NULL, '2022-11-21 16:48:49', '192.168.102.4', 0, 'admin', '2018-05-23 17:31:51', 'admin', '2018-05-29 17:20:58');

SET FOREIGN_KEY_CHECKS = 1;
