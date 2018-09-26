/*
Navicat MySQL Data Transfer

Source Server         : boot
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : boot

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2018-09-26 17:40:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dual
-- ----------------------------
DROP TABLE IF EXISTS `dual`;
CREATE TABLE `dual` (
  `dummy` char(1) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of dual
-- ----------------------------
INSERT INTO `dual` VALUES ('X');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_of_birth` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `login_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `active` char(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dattt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '1', '2018-09-25 18:30:29', '1', '1', '2018-09-25 18:30:09');
