/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : schoole

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : 65001

Date: 2016-12-06 21:31:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `ID` varchar(128) NOT NULL,
  `ADDRESS` varchar(128) DEFAULT NULL,
  `AREA_CODE` varchar(6) NOT NULL,
  `AREA_NAME` varchar(64) NOT NULL,
  `AUTHENTICATION` varchar(16) DEFAULT NULL,
  `COMPANY_NAME` varchar(64) DEFAULT NULL,
  `COMPANY_PICTURE` varchar(512) DEFAULT NULL,
  `EDU_LEVEL` varchar(1) DEFAULT NULL,
  `EDU_PUBCODE` varchar(8) DEFAULT NULL,
  `EDU_PUBNAME` varchar(24) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  `ENABLE` int(11) DEFAULT NULL,
  `ID_CARD_BACK` varchar(512) DEFAULT NULL,
  `ID_CARD_HEAD` varchar(512) DEFAULT NULL,
  `ID_CARD_NUM` varchar(18) DEFAULT NULL,
  `IP` varchar(32) DEFAULT NULL,
  `LAST_LOGIN_DATE` datetime DEFAULT NULL,
  `MOBILE_PHONE` varchar(11) DEFAULT NULL,
  `PASSWORD` varchar(128) NOT NULL,
  `PHOTO` varchar(512) DEFAULT NULL,
  `PRODUCTS_NUM` int(11) DEFAULT NULL,
  `REAL_NAME` varchar(16) DEFAULT NULL,
  `REGISTER_DATE` datetime DEFAULT NULL,
  `ROLE` varchar(16) DEFAULT NULL,
  `SCHOOL_CODE` varchar(8) DEFAULT NULL,
  `SCHOOL_NAME` varchar(24) DEFAULT NULL,
  `TEL_PHONE` varchar(24) DEFAULT NULL,
  `USERNAME` varchar(24) NOT NULL,
  `DORMITORY` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
