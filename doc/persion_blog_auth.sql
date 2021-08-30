/*
Navicat MySQL Data Transfer

Source Server         : aliyun
Source Server Version : 50733
Source Host           : 101.132.26.98:3306
Source Database       : persion_blog_auth

Target Server Type    : MYSQL
Target Server Version : 50733
File Encoding         : 65001

Date: 2021-08-30 11:38:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` tinyint(1) DEFAULT NULL COMMENT '角色，0：普通用户，1：后台管理员',
  `crt_time` datetime DEFAULT NULL,
  `upt_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1', '冰少', '207a36bb02414a2687a75b468ca6a05b', '1', '2021-08-19 11:13:07', '2021-08-19 11:13:07');
