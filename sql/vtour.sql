/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50625
 Source Host           : localhost
 Source Database       : vtour

 Target Server Type    : MySQL
 Target Server Version : 50625
 File Encoding         : utf-8

 Date: 03/27/2016 22:54:05 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(20) DEFAULT NULL,
  `nickName` varchar(60) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', 'fan', '照耀', '123@123.com', '13212345678', '123123'), ('3', null, '123123', '1@1.com', null, '123123');
COMMIT;

-- ----------------------------
--  Table structure for `useroauth`
-- ----------------------------
DROP TABLE IF EXISTS `useroauth`;
CREATE TABLE `useroauth` (
  `id` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `oauthName` varchar(20) DEFAULT NULL,
  `oauthId` varchar(60) DEFAULT NULL,
  `accessToken` varchar(100) DEFAULT NULL,
  `expires` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
