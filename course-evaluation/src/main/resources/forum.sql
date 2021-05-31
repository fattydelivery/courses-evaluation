/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : forum

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 31/05/2021 07:46:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `comment_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `course_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `comment_time` datetime(0) NULL DEFAULT NULL,
  `comment_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE,
  CONSTRAINT `t_comment_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('3d9905a6-bd16-11eb-a45d-00e04c860bea', 'B16290', '2021-05-25 13:01:18', '刘老师棒棒！');
INSERT INTO `t_comment` VALUES ('3d992548-bd16-11eb-a45d-00e04c860bea', 'B16290', '2021-05-25 13:01:18', '刘老师棒棒！');
INSERT INTO `t_comment` VALUES ('3d992906-bd16-11eb-a45d-00e04c860bea', 'B16290', '2021-05-25 13:01:18', '刘老师棒棒！');
INSERT INTO `t_comment` VALUES ('af1feafb-bd21-11eb-a45d-00e04c860bea', 'B16290', '2021-05-25 14:23:13', '我爱刘老师!');
INSERT INTO `t_comment` VALUES ('b31a6cf7-bd21-11eb-a45d-00e04c860bea', 'B16290', '2021-05-25 14:23:20', '水水水水水');
INSERT INTO `t_comment` VALUES ('d478b1af-bd21-11eb-a45d-00e04c860bea', 'B16290', '2021-05-25 14:24:16', '天啊');
INSERT INTO `t_comment` VALUES ('da53f9ff-bd21-11eb-a45d-00e04c860bea', 'B16290', '2021-05-25 14:24:25', '这也太能水了');
INSERT INTO `t_comment` VALUES ('df12f509-bd21-11eb-a45d-00e04c860bea', 'B16290', '2021-05-25 14:24:33', '现在是2021年5月25日14:24:32');
INSERT INTO `t_comment` VALUES ('eb732ae8-bd21-11eb-a45d-00e04c860bea', 'B16291', '2021-05-25 14:24:54', '水一水这个');
INSERT INTO `t_comment` VALUES ('f2a6e668-bd21-11eb-a45d-00e04c860bea', 'Z16342', '2021-05-25 14:25:06', '杨老师也不能放过啊');

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`  (
  `course_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `course_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `course_credit` int(0) NULL DEFAULT NULL,
  `course_credit_hour` int(0) NULL DEFAULT NULL,
  `teacher_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `type_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_course
-- ----------------------------
INSERT INTO `t_course` VALUES ('B16290', '使用Sqoop进行数据交换', 2, 32, 'Delucia', '专业课');
INSERT INTO `t_course` VALUES ('B16291', 'Flume使用指南', 2, 32, 'Delucia', '专业课');
INSERT INTO `t_course` VALUES ('Z16342', '数据仓库与数据挖掘', 4, 64, '杨厚群', '选修课');

-- ----------------------------
-- Table structure for t_like
-- ----------------------------
DROP TABLE IF EXISTS `t_like`;
CREATE TABLE `t_like`  (
  `like_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `course_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `like_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`like_id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE,
  CONSTRAINT `t_like_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_like
-- ----------------------------

-- ----------------------------
-- Table structure for t_rating
-- ----------------------------
DROP TABLE IF EXISTS `t_rating`;
CREATE TABLE `t_rating`  (
  `rating_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `course_id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `rating_time` datetime(0) NULL DEFAULT NULL,
  `rating_score` double NULL DEFAULT NULL,
  PRIMARY KEY (`rating_id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE,
  CONSTRAINT `t_rating_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_rating
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
