/*
 Navicat Premium Data Transfer

 Source Server         : 49.232.158.14-mysql
 Source Server Type    : MySQL
 Source Server Version : 80300 (8.3.0)
 Source Host           : 49.232.158.14:3306
 Source Schema         : may_blog

 Target Server Type    : MySQL
 Target Server Version : 80300 (8.3.0)
 File Encoding         : 65001

 Date: 10/04/2024 11:14:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oj_question
-- ----------------------------
DROP TABLE IF EXISTS `oj_question`;
CREATE TABLE `oj_question`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '题目标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '题目内容',
  `tags` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '题目标签（json数组）',
  `answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '标准答案',
  `submit_num` int NOT NULL DEFAULT 0 COMMENT '题目提交数',
  `accept_num` int NOT NULL DEFAULT 0 COMMENT '题目通过数',
  `judge_config` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '判题配置（json对象）\r\n{\r\n        timeLimit: \'xxx\',  时间限制\r\n        memoryLimit: \'xxx\',  内存限制\r\n        stackLimit: \'xxx\',  堆栈限制\r\n}',
  `judge_case` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '判题用例（json数组）\r\n\r\njson存储类型\r\n[\r\n    {\r\n        input: \'xxx\',  输入用例\r\n        output: \'xxx\'  输出用例\r\n    },\r\n]',
  `user_id` int NOT NULL COMMENT '创建题目用户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = 'oj模块-题目' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oj_question
-- ----------------------------
INSERT INTO `oj_question` VALUES (1, 'a+b', '题目内容', '[\"队列\",\"简单\"]', '暴力破解1', 0, 0, '{\"memoryLimit\":1000,\"stackLimit\":1000,\"timeLimit\":1000}', '[{\"input\":\"1 2\",\"output\":\"2 4\"}]', 1, '2024-02-26 17:40:54', '2024-02-26 17:44:20', 0);
INSERT INTO `oj_question` VALUES (2, 'a+c', '题目内容', '[\"队列\",\"中等\"]', '暴力破解1', 0, 0, '{\"memoryLimit\":1000,\"stackLimit\":1000,\"timeLimit\":1000}', '[{\"input\":\"1 2\",\"output\":\"2 4\"}]', 1, '2024-02-26 17:40:54', '2024-02-26 17:44:20', 0);

-- ----------------------------
-- Table structure for oj_question_submit
-- ----------------------------
DROP TABLE IF EXISTS `oj_question_submit`;
CREATE TABLE `oj_question_submit`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `question_id` int NOT NULL COMMENT '题目id',
  `user_id` int NOT NULL COMMENT '创建用户id',
  `language` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '编程语言',
  `code` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户代码',
  `judge_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '判题信息（json）\r\n[\r\n    {\r\n        message: xxx, 程序执行信息\r\n        time: xxx,  执行时间 ms\r\n        memory: xxx  占用内存 kb\r\n    }\r\n]',
  `status` int NULL DEFAULT 0 COMMENT '判题状态 （0：待判题，1：判题中，2：成功，3：失败）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_question_id`(`question_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = 'oj模块-题目提交' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oj_question_submit
-- ----------------------------

-- ----------------------------
-- Table structure for t_home
-- ----------------------------
DROP TABLE IF EXISTS `t_home`;
CREATE TABLE `t_home`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '模块id',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '模块名称',
  `component` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组件名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `preview_img` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '预览图片',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '门户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_home
-- ----------------------------
INSERT INTO `t_home` VALUES (7, '日历图', 'homeCalendar', '', NULL, '2024-01-31 14:42:51', '2024-03-26 15:19:41');
INSERT INTO `t_home` VALUES (8, '层叠折线图示例', 'homeDemo2', '1213', 'homeDemo2.png', '2024-01-31 14:43:03', '2024-03-18 14:54:58');
INSERT INTO `t_home` VALUES (9, '柱状图示例', 'homeDemo3', '1213', 'homeDemo3.png', '2024-01-31 14:43:14', '2024-03-18 14:54:54');
INSERT INTO `t_home` VALUES (18, '折线图示例', 'homeDemo4', '123', 'homeDemo4.png', '2024-02-04 16:14:43', '2024-03-18 14:54:36');
INSERT INTO `t_home` VALUES (22, '饼状图示例', 'homeDemo5', NULL, NULL, '2024-02-22 16:04:35', '2024-03-18 14:54:51');
INSERT INTO `t_home` VALUES (23, 'HomeDemo6', 'homeDemo6', NULL, NULL, '2024-02-23 16:35:05', '2024-02-26 12:11:03');
INSERT INTO `t_home` VALUES (24, 'HOME_DEMO7', 'homeDemo7', NULL, NULL, '2024-02-23 16:35:50', '2024-02-26 12:11:39');
INSERT INTO `t_home` VALUES (25, 'HomeDemo8', 'homeDemo8', NULL, NULL, '2024-02-23 16:36:03', '2024-02-26 12:11:09');
INSERT INTO `t_home` VALUES (26, '快捷导航', 'homeQuickNavigation', '快捷导航', NULL, '2024-03-21 17:46:45', '2024-03-22 17:31:22');

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单名',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单路径',
  `component` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '组件',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜单icon',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `order_num` tinyint(1) NOT NULL COMMENT '排序',
  `parent_id` int NULL DEFAULT NULL COMMENT '父id',
  `is_hidden` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否隐藏  0否1是',
  `menu_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '菜单类型  0目录  1菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 267 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, '首页', '/', '/home/index.vue', 'el-icon-myshouye', '2021-01-26 17:06:51', '2024-01-30 15:09:42', 1, NULL, 0, 1);
INSERT INTO `t_menu` VALUES (2, '文章管理', '/article', 'Layout', 'el-icon-mywenzhang-copy', '2021-01-25 20:43:07', '2023-08-04 10:26:48', 2, NULL, 0, 0);
INSERT INTO `t_menu` VALUES (6, '发布文章', '/article/publish', '/article/Publish.vue', 'el-icon-myfabiaowenzhang', '2021-01-26 14:30:48', '2023-09-01 17:12:56', 1, 2, 0, 1);
INSERT INTO `t_menu` VALUES (229, '系统管理', '/system', 'Layout', 'el-icon-myxitong', '2023-07-28 17:18:00', '2024-02-21 17:55:18', 4, NULL, 0, 0);
INSERT INTO `t_menu` VALUES (230, '菜单管理', '/system/menu-management', '/system/menu-management/index.vue', 'el-icon-mycaidan', '2023-07-28 17:18:00', '2023-08-04 11:16:11', 1, 229, 0, 1);
INSERT INTO `t_menu` VALUES (241, '角色管理', '/system/role-management', '/system/role-management/index.vue', 'el-icon-myjiaoseliebiao', '2023-08-04 11:09:50', '2023-08-23 14:49:02', 2, 229, 0, 1);
INSERT INTO `t_menu` VALUES (242, '接口管理', '/system/resource-management', '/system/resource-management/index.vue', 'el-icon-myjiekouguanli', '2023-08-10 09:48:51', '2023-08-10 10:47:54', 3, 229, 0, 1);
INSERT INTO `t_menu` VALUES (243, '用户管理', '/system/user-management', '/system/user-management/index.vue', 'el-icon-myyonghuliebiao', '2023-08-24 11:21:54', '2023-08-24 11:23:15', 4, 229, 0, 1);
INSERT INTO `t_menu` VALUES (244, 'demo', '/demo', 'Layout', 'el-icon-mywenzhang-copy', '2023-09-04 18:21:18', NULL, 4, NULL, 0, 0);
INSERT INTO `t_menu` VALUES (245, 'SSE', '/demo/sse', '/demo/sse.vue', 'el-icon-mycaidan', '2023-09-04 18:23:42', NULL, 1, 244, 0, 1);
INSERT INTO `t_menu` VALUES (246, 'EasyExcel', '/demo/easyExcel', '/demo/easyExcel.vue', 'el-icon-mycaidan', '2023-12-21 15:32:31', '2023-12-21 15:33:26', 2, 244, 0, 1);
INSERT INTO `t_menu` VALUES (247, 'ThreejsMap', '/demo/three', '/demo/three/index.vue', 'el-icon-mycaidan', '2024-01-11 16:24:55', '2024-01-11 16:25:03', 3, 244, 0, 1);
INSERT INTO `t_menu` VALUES (250, '门户管理', '/system/home-management', '/system/home-management/index.vue', 'el-icon-myshouye', '2024-01-22 14:26:07', '2024-01-31 18:07:47', 5, 229, 0, 1);
INSERT INTO `t_menu` VALUES (251, 'loaderCss', '/demo/loaderCss', '/demo/loaderCss.vue', '1231', '2024-01-24 10:26:52', '2024-01-24 10:29:10', 4, 244, 0, 1);
INSERT INTO `t_menu` VALUES (258, 'OJ判题模块', '/oj', 'Layout', '123', '2024-02-21 17:55:11', NULL, 3, NULL, 0, 0);
INSERT INTO `t_menu` VALUES (259, '题目管理', '/oj/management', 'Layout', '123', '2024-02-21 17:57:02', '2024-02-21 18:00:23', 2, 258, 0, 0);
INSERT INTO `t_menu` VALUES (260, '创建题目', '/oj/management/save-question', '/oj/management/save-question/index.vue', '123', '2024-02-21 17:59:44', '2024-03-14 18:01:54', 1, 259, 0, 1);
INSERT INTO `t_menu` VALUES (261, '做题', '/oj/do-question', 'Layout', '123', '2024-02-21 18:17:50', NULL, 2, 258, 0, 0);
INSERT INTO `t_menu` VALUES (262, '题目预览', '/oj/do-question/preview-question', '/oj/do-question/preview-question/index.vue', '123', '2024-02-21 18:19:12', '2024-02-21 18:19:16', 1, 261, 0, 1);
INSERT INTO `t_menu` VALUES (263, 'test', '/123', '/123', '123', '2024-03-01 11:35:52', NULL, 123, NULL, 0, 1);
INSERT INTO `t_menu` VALUES (265, '修改题目', '/oj/management/save-question/:id', '/oj/management/save-question/index.vue', '1', '2024-03-14 18:04:52', NULL, 2, 259, 1, 1);
INSERT INTO `t_menu` VALUES (266, '题目列表', '/oj/management/question-list', '/oj/management/question-list/index.vue', '1', '2024-03-15 17:22:06', NULL, 3, 259, 0, 1);

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '资源名',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '权限路径',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '请求方式',
  `parent_id` int NULL DEFAULT NULL COMMENT '父模块id',
  `is_anonymous` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否匿名访问 0否 1是',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1231 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '资源表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES (1, '菜单模块', NULL, NULL, NULL, 0, '2022-08-19 22:26:21', NULL);
INSERT INTO `t_resource` VALUES (2, '获取当前用户路由', '/admin/menus/user', 'GET', 1, 0, '2022-08-19 22:26:22', '2024-01-17 14:48:44');
INSERT INTO `t_resource` VALUES (3, '新增或修改菜单', '/admin/menus/menu', 'POST', 1, 0, '2022-08-19 22:26:22', '2024-01-17 14:49:02');
INSERT INTO `t_resource` VALUES (1191, '文章模块', NULL, '', NULL, 0, '2023-08-23 14:20:14', NULL);
INSERT INTO `t_resource` VALUES (1192, '获取指定和推荐文章', '/articles/topAndFeatured', 'GET', 1191, 1, '2023-08-23 14:25:59', NULL);
INSERT INTO `t_resource` VALUES (1193, '查询系统路由', '/admin/menus/menu', 'GET', 1, 0, '2023-08-23 14:27:19', '2024-01-17 14:49:12');
INSERT INTO `t_resource` VALUES (1194, '资源模块', NULL, '', NULL, 0, '2023-08-23 14:27:41', NULL);
INSERT INTO `t_resource` VALUES (1195, '获取资源列表', '/admin/resource/resources', 'GET', 1194, 0, '2023-08-23 14:27:57', '2024-01-17 14:50:27');
INSERT INTO `t_resource` VALUES (1197, '新增或修改资源', '/admin/resource/resources', 'POST', 1194, 0, '2023-08-23 14:29:36', '2024-01-17 14:50:57');
INSERT INTO `t_resource` VALUES (1198, '角色模块', NULL, '', NULL, 0, '2023-08-23 14:34:11', NULL);
INSERT INTO `t_resource` VALUES (1199, '查询角色列表', '/admin/role/roles', 'GET', 1198, 0, '2023-08-23 14:34:23', '2024-01-17 14:53:24');
INSERT INTO `t_resource` VALUES (1200, '查询角色菜单', '/admin/role/menus', 'GET', 1198, 0, '2023-08-23 14:36:03', NULL);
INSERT INTO `t_resource` VALUES (1201, '新增或修改角色菜单权限', '/admin/role/menus', 'POST', 1198, 0, '2023-08-23 14:36:59', NULL);
INSERT INTO `t_resource` VALUES (1202, '新增或修改角色资源权限', '/admin/role/resources', 'POST', 1198, 0, '2023-08-23 14:37:16', NULL);
INSERT INTO `t_resource` VALUES (1203, '获取可分配角色列表', '/admin/role/allow', 'GET', 1198, 0, '2023-08-25 11:53:16', NULL);
INSERT INTO `t_resource` VALUES (1204, '分配角色', '/admin/role/allow', 'PUT', 1198, 0, '2023-08-25 11:53:30', NULL);
INSERT INTO `t_resource` VALUES (1209, '删除系统路由', '/admin/menus/menu', 'DELETE', 1, 0, '2024-01-17 14:39:50', '2024-01-17 14:49:42');
INSERT INTO `t_resource` VALUES (1210, '删除资源', '/admin/resource/resources', 'DELETE', 1194, 0, '2024-01-17 14:51:26', NULL);
INSERT INTO `t_resource` VALUES (1211, '新增或修改角色', '/admin/role/roles', 'POST', 1198, 0, '2024-01-17 14:53:54', NULL);
INSERT INTO `t_resource` VALUES (1212, '删除角色', '/admin/role/roles', 'DELETE', 1198, 0, '2024-01-17 14:54:41', NULL);
INSERT INTO `t_resource` VALUES (1213, '获取角色资源列表', '/admin/role/resources', 'GET', 1198, 0, '2024-01-17 14:58:28', '2024-01-17 14:59:49');
INSERT INTO `t_resource` VALUES (1214, '接口文档', NULL, '', NULL, 0, '2024-01-22 10:26:17', NULL);
INSERT INTO `t_resource` VALUES (1215, 'Swagger2地址', '/swagger-ui.html', 'GET', 1214, 1, '2024-01-22 10:26:56', NULL);
INSERT INTO `t_resource` VALUES (1216, '用户模块', NULL, '', NULL, 0, '2024-02-06 09:38:14', NULL);
INSERT INTO `t_resource` VALUES (1217, '门户模块', NULL, '', NULL, 0, '2024-02-06 09:38:45', NULL);
INSERT INTO `t_resource` VALUES (1218, '创建门户', '/admin/home/add', 'POST', 1217, 0, '2024-02-06 09:40:37', NULL);
INSERT INTO `t_resource` VALUES (1219, '编辑门户', '/admin/home/edit', 'POST', 1217, 0, '2024-02-06 09:41:20', NULL);
INSERT INTO `t_resource` VALUES (1220, '查询用户启用，未启用门户', '/admin/home/listByUser', 'GET', 1217, 0, '2024-02-06 09:41:52', NULL);
INSERT INTO `t_resource` VALUES (1221, '查询角色启用，未启用门户', '/admin/home/listByRole', 'GET', 1217, 0, '2024-02-06 09:42:17', NULL);
INSERT INTO `t_resource` VALUES (1222, '处理门户块启用', '/admin/home/enable', 'POST', 1217, 0, '2024-02-06 09:42:59', NULL);
INSERT INTO `t_resource` VALUES (1223, '删除门户', '/admin/home/delete', 'DELETE', 1217, 0, '2024-02-06 09:43:21', NULL);
INSERT INTO `t_resource` VALUES (1224, '获取用户门户', '/admin/home/list', 'GET', 1217, 0, '2024-02-06 09:43:50', NULL);
INSERT INTO `t_resource` VALUES (1225, '获取用户列表', '/admin/users/list', 'GET', 1216, 0, '2024-02-06 09:50:32', NULL);
INSERT INTO `t_resource` VALUES (1226, '查询用户信息', '/admin/users/info', 'GET', 1216, 0, '2024-02-06 09:51:04', NULL);
INSERT INTO `t_resource` VALUES (1227, '上传头像', '/admin/users/avatar', 'POST', 1216, 0, '2024-02-06 09:51:27', NULL);
INSERT INTO `t_resource` VALUES (1228, '创建用户', '/admin/users/user', 'POST', 1216, 0, '2024-02-06 09:51:51', NULL);
INSERT INTO `t_resource` VALUES (1229, '删除用户', '/admin/users/user', 'DELETE', 1216, 0, '2024-02-06 09:52:10', NULL);
INSERT INTO `t_resource` VALUES (1230, '退出登录', '/admin/users/logout', 'GET', 1216, 1, '2024-02-06 09:52:41', NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名',
  `is_disable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用  0否 1是',
  `describe` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'admin', 0, NULL, '2022-07-20 13:25:19', '2024-03-18 14:52:06');
INSERT INTO `t_role` VALUES (2, 'user', 0, NULL, '2022-07-20 13:25:40', '2022-08-19 22:55:26');
INSERT INTO `t_role` VALUES (26, 'test', 0, '测试角色', '2024-01-17 11:08:24', NULL);
INSERT INTO `t_role` VALUES (27, 'oj', 0, 'oj权限', '2024-01-30 17:53:01', NULL);
INSERT INTO `t_role` VALUES (28, 'blog', 0, '博客权限', '2024-01-30 17:53:15', NULL);
INSERT INTO `t_role` VALUES (29, 'api', 0, '开放api平台权限', '2024-01-30 17:53:48', NULL);

-- ----------------------------
-- Table structure for t_role_home
-- ----------------------------
DROP TABLE IF EXISTS `t_role_home`;
CREATE TABLE `t_role_home`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NOT NULL,
  `home_id` int NOT NULL,
  `order_num` int NOT NULL,
  `width_value` int NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 499 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色门户关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role_home
-- ----------------------------
INSERT INTO `t_role_home` VALUES (226, 2, 7, 1, 1);
INSERT INTO `t_role_home` VALUES (227, 2, 18, 2, 1);
INSERT INTO `t_role_home` VALUES (228, 2, 8, 3, 1);
INSERT INTO `t_role_home` VALUES (229, 2, 9, 4, 1);
INSERT INTO `t_role_home` VALUES (421, 26, 8, 1, 1);
INSERT INTO `t_role_home` VALUES (422, 26, 7, 2, 1);
INSERT INTO `t_role_home` VALUES (423, 26, 18, 3, 1);
INSERT INTO `t_role_home` VALUES (424, 26, 9, 4, 1);
INSERT INTO `t_role_home` VALUES (425, 26, 22, 5, 5);
INSERT INTO `t_role_home` VALUES (490, 1, 7, 1, 1);
INSERT INTO `t_role_home` VALUES (491, 1, 18, 2, 1);
INSERT INTO `t_role_home` VALUES (492, 1, 8, 3, 1);
INSERT INTO `t_role_home` VALUES (493, 1, 23, 4, 1);
INSERT INTO `t_role_home` VALUES (494, 1, 26, 5, 1);
INSERT INTO `t_role_home` VALUES (495, 1, 22, 6, 1);
INSERT INTO `t_role_home` VALUES (496, 1, 9, 7, 1);
INSERT INTO `t_role_home` VALUES (497, 1, 24, 8, 1);
INSERT INTO `t_role_home` VALUES (498, 1, 25, 9, 1);

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `menu_id` int NULL DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3394 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (3269, 2, 1);
INSERT INTO `t_role_menu` VALUES (3270, 2, 2);
INSERT INTO `t_role_menu` VALUES (3271, 2, 6);
INSERT INTO `t_role_menu` VALUES (3272, 2, 244);
INSERT INTO `t_role_menu` VALUES (3273, 2, 245);
INSERT INTO `t_role_menu` VALUES (3274, 2, 246);
INSERT INTO `t_role_menu` VALUES (3275, 2, 247);
INSERT INTO `t_role_menu` VALUES (3276, 2, 251);
INSERT INTO `t_role_menu` VALUES (3277, 26, 1);
INSERT INTO `t_role_menu` VALUES (3278, 26, 2);
INSERT INTO `t_role_menu` VALUES (3279, 26, 6);
INSERT INTO `t_role_menu` VALUES (3280, 26, 229);
INSERT INTO `t_role_menu` VALUES (3281, 26, 230);
INSERT INTO `t_role_menu` VALUES (3282, 26, 241);
INSERT INTO `t_role_menu` VALUES (3283, 26, 242);
INSERT INTO `t_role_menu` VALUES (3284, 26, 243);
INSERT INTO `t_role_menu` VALUES (3285, 26, 250);
INSERT INTO `t_role_menu` VALUES (3286, 26, 244);
INSERT INTO `t_role_menu` VALUES (3287, 26, 245);
INSERT INTO `t_role_menu` VALUES (3288, 26, 246);
INSERT INTO `t_role_menu` VALUES (3289, 26, 247);
INSERT INTO `t_role_menu` VALUES (3290, 26, 251);
INSERT INTO `t_role_menu` VALUES (3372, 1, 1);
INSERT INTO `t_role_menu` VALUES (3373, 1, 2);
INSERT INTO `t_role_menu` VALUES (3374, 1, 6);
INSERT INTO `t_role_menu` VALUES (3375, 1, 258);
INSERT INTO `t_role_menu` VALUES (3376, 1, 259);
INSERT INTO `t_role_menu` VALUES (3377, 1, 260);
INSERT INTO `t_role_menu` VALUES (3378, 1, 265);
INSERT INTO `t_role_menu` VALUES (3379, 1, 266);
INSERT INTO `t_role_menu` VALUES (3380, 1, 261);
INSERT INTO `t_role_menu` VALUES (3381, 1, 262);
INSERT INTO `t_role_menu` VALUES (3382, 1, 229);
INSERT INTO `t_role_menu` VALUES (3383, 1, 230);
INSERT INTO `t_role_menu` VALUES (3384, 1, 241);
INSERT INTO `t_role_menu` VALUES (3385, 1, 242);
INSERT INTO `t_role_menu` VALUES (3386, 1, 243);
INSERT INTO `t_role_menu` VALUES (3387, 1, 250);
INSERT INTO `t_role_menu` VALUES (3388, 1, 244);
INSERT INTO `t_role_menu` VALUES (3389, 1, 245);
INSERT INTO `t_role_menu` VALUES (3390, 1, 246);
INSERT INTO `t_role_menu` VALUES (3391, 1, 247);
INSERT INTO `t_role_menu` VALUES (3392, 1, 251);
INSERT INTO `t_role_menu` VALUES (3393, 1, 263);

-- ----------------------------
-- Table structure for t_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resource`;
CREATE TABLE `t_role_resource`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  `resource_id` int NULL DEFAULT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6067 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色资源关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_role_resource
-- ----------------------------
INSERT INTO `t_role_resource` VALUES (5944, 2, 1);
INSERT INTO `t_role_resource` VALUES (5945, 2, 2);
INSERT INTO `t_role_resource` VALUES (5946, 2, 3);
INSERT INTO `t_role_resource` VALUES (5947, 2, 1193);
INSERT INTO `t_role_resource` VALUES (5948, 2, 1209);
INSERT INTO `t_role_resource` VALUES (5949, 2, 1191);
INSERT INTO `t_role_resource` VALUES (5950, 2, 1192);
INSERT INTO `t_role_resource` VALUES (5951, 2, 1194);
INSERT INTO `t_role_resource` VALUES (5952, 2, 1195);
INSERT INTO `t_role_resource` VALUES (5953, 2, 1197);
INSERT INTO `t_role_resource` VALUES (5954, 2, 1210);
INSERT INTO `t_role_resource` VALUES (5955, 2, 1198);
INSERT INTO `t_role_resource` VALUES (5956, 2, 1199);
INSERT INTO `t_role_resource` VALUES (5957, 2, 1200);
INSERT INTO `t_role_resource` VALUES (5958, 2, 1201);
INSERT INTO `t_role_resource` VALUES (5959, 2, 1202);
INSERT INTO `t_role_resource` VALUES (5960, 2, 1203);
INSERT INTO `t_role_resource` VALUES (5961, 2, 1204);
INSERT INTO `t_role_resource` VALUES (5962, 2, 1211);
INSERT INTO `t_role_resource` VALUES (5963, 2, 1212);
INSERT INTO `t_role_resource` VALUES (5964, 2, 1213);
INSERT INTO `t_role_resource` VALUES (5965, 2, 1214);
INSERT INTO `t_role_resource` VALUES (5966, 2, 1215);
INSERT INTO `t_role_resource` VALUES (6010, 1, 1);
INSERT INTO `t_role_resource` VALUES (6011, 1, 2);
INSERT INTO `t_role_resource` VALUES (6012, 1, 3);
INSERT INTO `t_role_resource` VALUES (6013, 1, 1193);
INSERT INTO `t_role_resource` VALUES (6014, 1, 1209);
INSERT INTO `t_role_resource` VALUES (6015, 1, 1191);
INSERT INTO `t_role_resource` VALUES (6016, 1, 1192);
INSERT INTO `t_role_resource` VALUES (6017, 1, 1194);
INSERT INTO `t_role_resource` VALUES (6018, 1, 1195);
INSERT INTO `t_role_resource` VALUES (6019, 1, 1197);
INSERT INTO `t_role_resource` VALUES (6020, 1, 1210);
INSERT INTO `t_role_resource` VALUES (6021, 1, 1198);
INSERT INTO `t_role_resource` VALUES (6022, 1, 1199);
INSERT INTO `t_role_resource` VALUES (6023, 1, 1200);
INSERT INTO `t_role_resource` VALUES (6024, 1, 1201);
INSERT INTO `t_role_resource` VALUES (6025, 1, 1202);
INSERT INTO `t_role_resource` VALUES (6026, 1, 1203);
INSERT INTO `t_role_resource` VALUES (6027, 1, 1204);
INSERT INTO `t_role_resource` VALUES (6028, 1, 1211);
INSERT INTO `t_role_resource` VALUES (6029, 1, 1212);
INSERT INTO `t_role_resource` VALUES (6030, 1, 1213);
INSERT INTO `t_role_resource` VALUES (6031, 1, 1216);
INSERT INTO `t_role_resource` VALUES (6032, 1, 1225);
INSERT INTO `t_role_resource` VALUES (6033, 1, 1226);
INSERT INTO `t_role_resource` VALUES (6034, 1, 1227);
INSERT INTO `t_role_resource` VALUES (6035, 1, 1228);
INSERT INTO `t_role_resource` VALUES (6036, 1, 1229);
INSERT INTO `t_role_resource` VALUES (6037, 1, 1230);
INSERT INTO `t_role_resource` VALUES (6038, 1, 1217);
INSERT INTO `t_role_resource` VALUES (6039, 1, 1218);
INSERT INTO `t_role_resource` VALUES (6040, 1, 1219);
INSERT INTO `t_role_resource` VALUES (6041, 1, 1220);
INSERT INTO `t_role_resource` VALUES (6042, 1, 1221);
INSERT INTO `t_role_resource` VALUES (6043, 1, 1222);
INSERT INTO `t_role_resource` VALUES (6044, 1, 1223);
INSERT INTO `t_role_resource` VALUES (6045, 1, 1224);
INSERT INTO `t_role_resource` VALUES (6046, 26, 1);
INSERT INTO `t_role_resource` VALUES (6047, 26, 2);
INSERT INTO `t_role_resource` VALUES (6048, 26, 1193);
INSERT INTO `t_role_resource` VALUES (6049, 26, 1191);
INSERT INTO `t_role_resource` VALUES (6050, 26, 1192);
INSERT INTO `t_role_resource` VALUES (6051, 26, 1194);
INSERT INTO `t_role_resource` VALUES (6052, 26, 1195);
INSERT INTO `t_role_resource` VALUES (6053, 26, 1198);
INSERT INTO `t_role_resource` VALUES (6054, 26, 1199);
INSERT INTO `t_role_resource` VALUES (6055, 26, 1200);
INSERT INTO `t_role_resource` VALUES (6056, 26, 1203);
INSERT INTO `t_role_resource` VALUES (6057, 26, 1213);
INSERT INTO `t_role_resource` VALUES (6058, 26, 1214);
INSERT INTO `t_role_resource` VALUES (6059, 26, 1215);
INSERT INTO `t_role_resource` VALUES (6060, 26, 1216);
INSERT INTO `t_role_resource` VALUES (6061, 26, 1225);
INSERT INTO `t_role_resource` VALUES (6062, 26, 1226);
INSERT INTO `t_role_resource` VALUES (6063, 26, 1217);
INSERT INTO `t_role_resource` VALUES (6064, 26, 1220);
INSERT INTO `t_role_resource` VALUES (6065, 26, 1221);
INSERT INTO `t_role_resource` VALUES (6066, 26, 1224);

-- ----------------------------
-- Table structure for t_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `t_user_auth`;
CREATE TABLE `t_user_auth`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_info_id` int NOT NULL COMMENT '用户信息id',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `login_type` tinyint(1) NOT NULL COMMENT '登录类型 1 邮箱 2 qq',
  `ip_address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户登录ip',
  `ip_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'ip来源',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1032 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_auth
-- ----------------------------
INSERT INTO `t_user_auth` VALUES (1014, 1024, '2938007768@qq.com', '$2a$10$hiltyiaBve/qZ4oRrUHLyeZmBVfgmU3i2/b4AAz3w0rnqpfU/Z/FK', 1, '202.106.86.134', '中国|北京|北京市|联通', '2023-07-05 09:51:19', '2023-07-05 09:59:08', '2023-07-05 09:59:08');
INSERT INTO `t_user_auth` VALUES (1020, 1030, 'test', '$2a$10$GaQEG/DdBaGmjt9lNXrSt.a0XKyXQyDARmQL2JDrNDFb3gsxdBlNK', 3, '10.14.114.91', '内网IP|内网IP', '2024-02-05 09:49:42', '2024-03-21 14:38:41', '2024-03-21 14:38:40');
INSERT INTO `t_user_auth` VALUES (1031, 1041, 'admin', '$2a$10$iY6Go3QJxoKOItSFtiutHOsHks83.u5tyVoxso0.gYgDgymILnhRy', 3, '10.14.114.74', '内网IP|内网IP', '2024-03-21 10:41:16', '2024-04-09 18:07:08', '2024-04-09 18:07:07');

-- ----------------------------
-- Table structure for t_user_home
-- ----------------------------
DROP TABLE IF EXISTS `t_user_home`;
CREATE TABLE `t_user_home`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_info_id` int NOT NULL COMMENT '用户id',
  `home_order` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '门户排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户门户关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_home
-- ----------------------------
INSERT INTO `t_user_home` VALUES (4, 1024, '[{\"homeId\":9,\"orderNum\":1},{\"homeId\":7,\"orderNum\":2}]');
INSERT INTO `t_user_home` VALUES (5, 1030, '[{\"homeId\":8,\"widthValue\":2,\"orderNum\":1},{\"homeId\":9,\"widthValue\":2,\"orderNum\":2},{\"homeId\":22,\"widthValue\":2,\"orderNum\":3},{\"homeId\":18,\"widthValue\":2,\"orderNum\":4},{\"homeId\":7,\"widthValue\":4,\"orderNum\":5}]');
INSERT INTO `t_user_home` VALUES (10, 1041, '[{\"homeId\":7,\"widthValue\":5,\"orderNum\":1},{\"homeId\":26,\"widthValue\":3,\"orderNum\":2},{\"homeId\":24,\"widthValue\":1,\"orderNum\":3},{\"homeId\":25,\"widthValue\":1,\"orderNum\":4},{\"homeId\":23,\"widthValue\":1,\"orderNum\":5},{\"homeId\":18,\"widthValue\":1,\"orderNum\":6},{\"homeId\":9,\"widthValue\":1,\"orderNum\":7},{\"homeId\":8,\"widthValue\":1,\"orderNum\":8},{\"homeId\":22,\"widthValue\":1,\"orderNum\":9}]');

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱号',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '用户头像',
  `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户简介',
  `website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个人网站',
  `is_subscribe` tinyint(1) NULL DEFAULT NULL COMMENT '是否订阅',
  `is_disable` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1042 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
INSERT INTO `t_user_info` VALUES (1024, '2938007768@qq.com', '用户1676408381937090561', '', NULL, NULL, NULL, 0, '2023-07-05 09:51:19', NULL);
INSERT INTO `t_user_info` VALUES (1030, 'test@163.com', 'test111', 'http://127.0.0.1:9000/may-blog/avatar/831b18d73382b915b11f2c4638b87eb4.jpg', NULL, NULL, NULL, 0, '2024-02-05 09:49:41', NULL);
INSERT INTO `t_user_info` VALUES (1041, NULL, 'admin', 'http://49.232.158.14:9000/may-blog/avatar/831b18d73382b915b11f2c4638b87eb4.jpg', NULL, NULL, NULL, 0, '2024-03-21 10:41:16', NULL);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL COMMENT '用户id',
  `role_id` int NULL DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1073 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1050, 1024, 2);
INSERT INTO `t_user_role` VALUES (1069, 1030, 26);
INSERT INTO `t_user_role` VALUES (1072, 1041, 1);

SET FOREIGN_KEY_CHECKS = 1;
