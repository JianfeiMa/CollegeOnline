/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : trade

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2016-09-04 23:00:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `collect`
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `collectionid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `shopid` int(11) NOT NULL COMMENT 'shopId',
  `username` varchar(1024) COLLATE utf8_unicode_ci NOT NULL COMMENT 'collectorName',
  PRIMARY KEY (`collectionid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('1', '2', 'majianfei');

-- ----------------------------
-- Table structure for `look`
-- ----------------------------
DROP TABLE IF EXISTS `look`;
CREATE TABLE `look` (
  `lookid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `lookname` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT 'hopeShopName',
  `description` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'hopeShopNamescript',
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT 'hoperName',
  `userphone` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT 'hoperNamePhoneNuber',
  `category` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT 'hopeCategory',
  `put_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'relaeseTime',
  PRIMARY KEY (`lookid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of look
-- ----------------------------
INSERT INTO `look` VALUES ('1', '我想要一台笔记本电脑', '二手的也可以', 'majianfei', '13138742085', '电脑配件', '2016-08-11 09:08:23');

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `messageid` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `content` varchar(1024) COLLATE utf8_unicode_ci NOT NULL COMMENT '留言内容',
  `username` varchar(1024) COLLATE utf8_unicode_ci NOT NULL COMMENT '留言者姓名',
  `receivename` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '接收留言者姓名',
  `leave_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '留言时间',
  PRIMARY KEY (`messageid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '请问多少钱呢?', 'majianfei', 'abc', '2016-06-16 21:44:28');
INSERT INTO `message` VALUES ('2', '250啊！要嘛咯?', 'abc', 'majianfei', '2016-08-11 09:28:34');

-- ----------------------------
-- Table structure for `shop`
-- ----------------------------
DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `shopid` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `shopname` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '商品名',
  `description` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品描述',
  `username` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '发布者姓名',
  `userphone` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户电话',
  `category` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '商品类别',
  `picture` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '商品图片',
  `price` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '商品价格',
  `put_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`shopid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of shop
-- ----------------------------
INSERT INTO `shop` VALUES ('1', '相册', '好', 'abc', '13138742085', '生活用品', 'showmark2_mr1465640927074.jpg;', '23.00元', '2016-06-16 20:38:19');
INSERT INTO `shop` VALUES ('2', '香烟', '雪茄', 'abc', '13138742085', '生活用品', 'mmexport1465990781482.jpg;', '700.00元', '2016-06-16 20:53:28');
INSERT INTO `shop` VALUES ('3', '准考证', '漂亮', 'majianfei', '13138742085', '办公用品', 'mmexport1465220551800.jpg;', '48.00元', '2016-06-16 21:42:56');
INSERT INTO `shop` VALUES ('4', '手机', '发个邮', 'majianfei', '13138742085', '办公用品', 'Universal Image Loader @#&=+-_.,!()~\'%20.png;', '25.00元', '2016-06-17 16:07:28');
INSERT INTO `shop` VALUES ('5', '鞋', '', 'abc', '13138742085', '生活用品', 'Photoplus.jpg;', '80.00元', '2016-06-23 15:18:16');
INSERT INTO `shop` VALUES ('6', '照片', '', 'abc', '13138742085', '体育用品', 'IMG20160621001947.jpg;', '2.00元', '2016-06-23 17:33:48');
INSERT INTO `shop` VALUES ('7', '代码', '', 'abc', '13138742085', '电脑配件', 'IMG20160415113012.jpg;', '100.00元', '2016-07-20 22:07:45');
INSERT INTO `shop` VALUES ('8', '手机', '', '马健飞', '13138742085', '体育用品', 'IMG_20160706_232124.jpg;IMG_20160618_180023.jpg;', '200.00元', '2016-07-23 15:08:59');
INSERT INTO `shop` VALUES ('9', 'linux系统', '', '马健飞', '13138742085', '电脑配件', 'IMG_20160726_014930.jpg;', '100.00元', '2016-07-27 10:06:18');
INSERT INTO `shop` VALUES ('10', '个人照片', '', '马健飞', '13138742085', '生活用品', 'IMG_20160526_173316.jpg;IMG_20160526_173314.jpg;IMG_20160526_173310.jpg;', '200.00元', '2016-07-27 10:11:26');
INSERT INTO `shop` VALUES ('11', '夜景', '', 'majianfei', '13138742085', '生活用品', 'IMG_20160806_204805.jpg;IMG_20160806_204733.jpg;', '800.00元', '2016-08-09 21:43:37');
INSERT INTO `shop` VALUES ('12', '英语4级证书', '', 'majianfei', '13138742085', '体育用品', 'Screenshot_2016-02-26-21-33-08.png;', '500.00元', '2016-08-12 17:06:43');
INSERT INTO `shop` VALUES ('13', '海凌岛', '', 'majianfei', '13138742085', '生活用品', 'IMG_20160806_204805.jpg;IMG_20160806_204733.jpg;IMG_20160731_131623.jpg;', '2.00元', '2016-08-12 17:17:31');
INSERT INTO `shop` VALUES ('14', 'aaaa', '', 'majianfei', '13138742085', '图书', 'IMG_20160806_204805.jpg;', '30.00元', '2016-09-02 23:11:53');
INSERT INTO `shop` VALUES ('15', '车票', '', 'majianfei', '13138742085', '体育用品', 'toutu.jpeg;', '100.00元', '2016-09-02 23:20:31');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户密码',
  `email` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户邮箱',
  `school` varchar(64) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户所在学校',
  `court` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户所在院',
  `professional` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户所学专业',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'really', '123456', '1017033681@qq.com', '阳江职业技术学院', '计算机', '计算机应用技术');
INSERT INTO `users` VALUES ('2', 'abc', '123', '1017033681@qq.com', 'yangjiang', 'zhiyejishuxueyuan', 'b');
INSERT INTO `users` VALUES ('3', 'majianfei', '123456', '321606147@qq.com', '阳江职业技术学院', '信息工程系', '计算机应用技术');
INSERT INTO `users` VALUES ('4', '马健飞', '123456', '321606147@qq.com', '阳江职业技术学院', '信息工程系', '计算机应用技术');
INSERT INTO `users` VALUES ('5', '李正文', '123456', '1158312774@qq.com', '阳江职业技术学院', '信息工程系', '计算机');
