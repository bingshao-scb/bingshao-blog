/*
Navicat MySQL Data Transfer

Source Server         : aliyun
Source Server Version : 50733
Source Host           : 101.132.26.98:3306
Source Database       : persion_blog_blog

Target Server Type    : MYSQL
Target Server Version : 50733
File Encoding         : 65001

Date: 2021-08-30 11:38:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` bigint(20) NOT NULL,
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `show_content` longtext COMMENT '页面展示被识别的html内容',
  `origin_content` longtext COMMENT '纯文本',
  `crt_user_id` bigint(20) DEFAULT NULL COMMENT '操作用户id',
  `upt_user_id` bigint(20) DEFAULT NULL COMMENT '修改人id',
  `delete_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识，0：删除，1：未删除',
  `crt_time` datetime DEFAULT NULL,
  `upt_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1430039120599580672', '勿起飞', '<h1><a id=\"_0\"></a>三级标题</h1>\n<p><code>java代码</code></p>\n<p><strong>没事了</strong></p>\n<p>勿这样哦</p>\n', '# 三级标题\n\n``java代码``\n\n**没事了**\n\n勿这样哦\n\n\n', '1', '1', '1', '2021-08-24 13:27:50', '2021-08-28 11:14:02');
INSERT INTO `blog` VALUES ('1430065411725983744', 'Springboot项目中使用过滤器Filter+ThreadLocal实现对请求用户的拦截和保存', '<h1><a id=\"_0\"></a>三级标题</h1>\n<p><code>java代码</code></p>\n<p><strong>没事了</strong></p>\n', '# 三级标题\n\n``java代码``\n\n**没事了**\n\n\n', '1', '1', '1', '2021-08-24 15:12:18', '2021-08-28 11:57:51');
INSERT INTO `blog` VALUES ('1431465479020478464', '爷起飞一直可以的', '<p>我是你爸爸</p>\n', '我是你爸爸', '1', '1', '1', '2021-08-28 11:55:40', '2021-08-28 16:12:39');

-- ----------------------------
-- Table structure for blog_classify
-- ----------------------------
DROP TABLE IF EXISTS `blog_classify`;
CREATE TABLE `blog_classify` (
  `blog_id` bigint(20) NOT NULL,
  `classify_id` int(11) NOT NULL,
  `crt_user_id` int(11) DEFAULT NULL,
  `upt_user_id` int(11) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `upt_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_classify
-- ----------------------------
INSERT INTO `blog_classify` VALUES ('1430039120599580672', '1', '1', '1', '2021-08-26 16:42:44', '2021-08-26 16:42:47');
INSERT INTO `blog_classify` VALUES ('1430039120599580672', '2', '1', '1', '2021-08-26 16:43:26', '2021-08-26 16:43:29');
INSERT INTO `blog_classify` VALUES ('1430065411725983744', '1', '1', '1', '2021-08-26 16:43:51', '2021-08-26 16:43:54');
INSERT INTO `blog_classify` VALUES ('1431465479020478464', '1', '1', '1', '2021-08-28 11:55:40', '2021-08-28 11:55:40');
INSERT INTO `blog_classify` VALUES ('1431465479020478464', '2', '1', '1', '2021-08-28 11:55:40', '2021-08-28 11:55:40');

-- ----------------------------
-- Table structure for classify
-- ----------------------------
DROP TABLE IF EXISTS `classify`;
CREATE TABLE `classify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `column_name` varchar(50) DEFAULT NULL COMMENT '分类',
  `crt_user_id` bigint(20) DEFAULT NULL,
  `upt_user_id` bigint(20) DEFAULT NULL,
  `crt_time` datetime DEFAULT NULL,
  `upt_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classify
-- ----------------------------
INSERT INTO `classify` VALUES ('1', 'SpringBoot', '1', '1', '2021-08-26 13:21:04', '2021-08-26 13:21:06');
INSERT INTO `classify` VALUES ('2', 'Redis', '1', '1', '2021-08-26 16:22:03', '2021-08-26 16:22:06');
INSERT INTO `classify` VALUES ('3', 'Rabbitmq', '1', '1', '2021-08-28 15:47:18', '2021-08-28 15:47:18');
