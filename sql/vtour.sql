/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.13 : Database - vtour
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`vtour` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Table structure for table `pano` */

DROP TABLE IF EXISTS `pano`;

CREATE TABLE `pano` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL COMMENT '全景图名称',
  `desc` varchar(200) DEFAULT NULL COMMENT '全景图描述',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  `uid` int(11) DEFAULT NULL COMMENT '所属用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100003 DEFAULT CHARSET=utf8;

/*Data for the table `pano` */

insert  into `pano`(`id`,`name`,`desc`,`createDate`,`updateDate`,`uid`) values (100000,'1231',NULL,NULL,NULL,3),(100001,'全景图1',NULL,NULL,NULL,3),(100002,'全景图测试',NULL,NULL,NULL,3);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `loginName` varchar(20) DEFAULT NULL,
  `nickName` varchar(60) DEFAULT NULL COMMENT '昵称',
  `email` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机号',
  `password` varchar(60) DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `uid` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`loginName`,`nickName`,`email`,`mobile`,`password`,`avatar`,`uid`) values (100000,NULL,'照耀','1@1.com','13212345678','601f1889667efaebb33b8c12572835da3f027f78',NULL,NULL);

/*Table structure for table `useroauth` */

DROP TABLE IF EXISTS `useroauth`;

CREATE TABLE `useroauth` (
  `id` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `oauthName` varchar(20) DEFAULT NULL,
  `oauthId` varchar(60) DEFAULT NULL,
  `accessToken` varchar(100) DEFAULT NULL,
  `expires` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `useroauth` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
