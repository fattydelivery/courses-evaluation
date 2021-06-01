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

 Date: 31/05/2021 22:01:39
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `comment_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_time` datetime(0) NULL DEFAULT NULL,
  `comment_content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE,
  CONSTRAINT `t_comment_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES ('d31ef330-c1b9-11eb-88be-00e04c860bea', 'B16290', '2021-05-31 10:42:22', '大家好');
INSERT INTO `t_comment` VALUES ('ee684a06-c1b5-11eb-88be-00e04c860bea', 'B16290', '2021-05-31 10:14:29', '刘老师棒棒！');
INSERT INTO `t_comment` VALUES ('ee684f8c-c1b5-11eb-88be-00e04c860bea', 'B16290', '2021-05-31 10:14:29', '刘老师好腻害！');
INSERT INTO `t_comment` VALUES ('ee6852e7-c1b5-11eb-88be-00e04c860bea', 'B16290', '2021-05-31 10:14:29', '吹爆刘老师！');

-- ----------------------------
-- Table structure for t_course
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course`  (
  `course_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `course_credit` int(0) NULL DEFAULT NULL,
  `course_credit_hour` int(0) NULL DEFAULT NULL,
  `teacher_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `like_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `like_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`like_id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE,
  CONSTRAINT `t_like_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_like
-- ----------------------------
INSERT INTO `t_like` VALUES ('06583f64-c1ba-11eb-88be-00e04c860bea', 'B16290', '2021-05-31 10:43:47');
INSERT INTO `t_like` VALUES ('1f12c19b-c1be-11eb-88be-00e04c860bea', 'B16291', '2021-05-31 11:13:07');
INSERT INTO `t_like` VALUES ('37a89fa2-c1be-11eb-88be-00e04c860bea', 'B16291', '2021-05-31 11:13:48');
INSERT INTO `t_like` VALUES ('382aae8e-c1be-11eb-88be-00e04c860bea', 'B16291', '2021-05-31 11:13:49');
INSERT INTO `t_like` VALUES ('40e552f8-c1be-11eb-88be-00e04c860bea', 'Z16342', '2021-05-31 11:14:04');

-- ----------------------------
-- Table structure for t_rating
-- ----------------------------
DROP TABLE IF EXISTS `t_rating`;
CREATE TABLE `t_rating`  (
  `rating_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `course_id` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rating_time` datetime(0) NULL DEFAULT NULL,
  `rating_score` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`rating_id`) USING BTREE,
  INDEX `course_id`(`course_id`) USING BTREE,
  CONSTRAINT `t_rating_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`course_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_rating
-- ----------------------------
INSERT INTO `t_rating` VALUES ('1be69042-c1ba-11eb-88be-00e04c860bea', 'B16290', '2021-05-31 10:44:24', 5);
INSERT INTO `t_rating` VALUES ('271d7ec7-c1ba-11eb-88be-00e04c860bea', 'B16290', '2021-05-31 10:44:42', 5);
INSERT INTO `t_rating` VALUES ('36d4e400-c1be-11eb-88be-00e04c860bea', 'B16291', '2021-05-31 11:13:47', 5);
INSERT INTO `t_rating` VALUES ('38ffc51f-c1be-11eb-88be-00e04c860bea', 'B16291', '2021-05-31 11:13:50', 3);
INSERT INTO `t_rating` VALUES ('3fbdf338-c1be-11eb-88be-00e04c860bea', 'Z16342', '2021-05-31 11:14:02', 3);
INSERT INTO `t_rating` VALUES ('40e4b6f5-c1be-11eb-88be-00e04c860bea', 'Z16342', '2021-05-31 11:14:04', 4);
INSERT INTO `t_rating` VALUES ('4b769a81-c1b6-11eb-88be-00e04c860bea', 'B16290', '2021-05-31 10:17:05', 5);
INSERT INTO `t_rating` VALUES ('4b773d7c-c1b6-11eb-88be-00e04c860bea', 'B16290', '2021-05-31 10:17:05', 4);
INSERT INTO `t_rating` VALUES ('7e850a73-c1ba-11eb-88be-00e04c860bea', 'B16290', '2021-05-31 10:47:09', 5);

SET FOREIGN_KEY_CHECKS = 1;
