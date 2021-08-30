/*
Navicat MySQL Data Transfer

Source Server         : aliyun
Source Server Version : 50733
Source Host           : 101.132.26.98:3306
Source Database       : persion_blog_admin

Target Server Type    : MYSQL
Target Server Version : 50733
File Encoding         : 65001

Date: 2021-08-30 11:37:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_user_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_user_info`;
CREATE TABLE `admin_user_info` (
  `id` bigint(20) NOT NULL COMMENT '用户id',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `picture_url` varchar(255) DEFAULT NULL COMMENT '头像路径',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识，0：删除；1，未删除',
  `crt_time` datetime DEFAULT NULL,
  `upt_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_user_info
-- ----------------------------
INSERT INTO `admin_user_info` VALUES ('1', '冰少', 'http://shigongzi.oss-cn-beijing.aliyuncs.com/2021-08-30/idea%E8%83%8C%E6%99%AF.jpg?Expires=1632119651&OSSAccessKeyId=LTAI5t8Kdt3koi1e4i59M77B&Signature=hvZjevomEXsEyTLU4CNgycubF9E%3D', '1', '2021-08-24 16:32:05', '2021-08-24 16:32:07');
