/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : schoole

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : 65001

Date: 2016-12-06 21:31:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for commodity_info
-- ----------------------------
DROP TABLE IF EXISTS `commodity_info`;
CREATE TABLE `commodity_info` (
  `ID` varchar(128) NOT NULL,
  `ADD_TIME` datetime DEFAULT NULL,
  `BRANK` varchar(64) DEFAULT NULL,
  `CATEGORY` varchar(6) NOT NULL,
  `DESCRIPTION` varchar(512) DEFAULT NULL,
  `GOODS` varchar(255) NOT NULL,
  `LINK` varchar(128) DEFAULT NULL,
  `NAME` varchar(128) NOT NULL,
  `NEW_CONDITION` varchar(16) DEFAULT NULL,
  `PICTURE` varchar(128) DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `RECOMMEND` int(11) NOT NULL,
  `STATUS` int(11) NOT NULL,
  `TYPE_CODE` varchar(64) DEFAULT NULL,
  `UNIT` varchar(16) NOT NULL,
  `USED` int(11) NOT NULL,
  `USERNAME` varchar(24) NOT NULL,
  `VIEWS` int(11) NOT NULL,
  `BRAND` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity_info
-- ----------------------------
INSERT INTO `commodity_info` VALUES ('2c94b7874c8eaccf014c8eb36dec0002', '2015-04-06 20:27:45', '', '1806', '', '1', '', '冥想的艺术', '八成新', '/upload/20150406/110101/102/YJJBROOJOC.JPG', '34', '0', '1', '', '本', '1', '102', '2', null);
INSERT INTO `commodity_info` VALUES ('2c94b7884c917cb4014c91866f6d0003', '2015-04-07 09:37:28', '', '9102', '', '1', '', '手表', '八成新', '/upload/20150407/110101/102/LCKHUJYXYC.JPG', '34', '0', '1', '', '块', '1', '102', '0', null);
INSERT INTO `commodity_info` VALUES ('2c94b7884c917cb4014c918e493d0006', '2015-04-07 09:46:03', '', '1806', '', '1', '', '书籍', '全新', '/upload/20150407/410102/夏天/LQSORCKGAK.JPG', '50', '0', '1', '', '本', '0', '夏天', '1', null);
INSERT INTO `commodity_info` VALUES ('2c94b78a4ca2867a014ca287e8250000', '2015-04-10 16:52:37', '', '9102', '', '1', '', '花朵', '全新', '/upload/20150410/410102/夏天/WPNCUOVKOD.JPG', '12', '0', '1', '', '朵', '0', '夏天', '1', null);
INSERT INTO `commodity_info` VALUES ('2c94b78a4ca2ef6c014ca2f172960000', '2015-04-10 18:47:54', '', '9103', '', '1', '', '图片', '全新', '/upload/20150410/410102/2c94b7874c8eaccf014c8eaf5b200000/HIMKBCDXSC.JPG', '21', '0', '1', '', '个', '0', '夏天', '2', null);
INSERT INTO `commodity_info` VALUES ('2c94b78a4ca2ef6c014ca2f4ae5b0001', '2015-04-10 18:51:26', '', '9103', '', '1', '', '11', '全新', '/upload/20150410/410102/2c94b7874c8eaccf014c8eaf5b200000/UHQRJRSPCA.JPG', '11', '0', '1', '', '1', '0', '夏天', '1', null);
INSERT INTO `commodity_info` VALUES ('2c94b78e4cfa4313014cfa5aa3380000', '2015-04-27 18:09:46', null, '1305', '去年购得，还很新', '2', '', '女式高跟鞋', '六成新', '/upload/20150603/410102/2c94b7874c8eaccf014c8eaf5b200000/GRJEMOQCEC.JPG', '50', '0', '1', '', '双', '1', '夏天', '3', '达芙妮');
INSERT INTO `commodity_info` VALUES ('2c94b7914cb31206014cb31460840000', '2015-04-13 21:59:59', null, '9102', '', '2', '', '贴纸', '全新', '/upload/20150603/410102/2c94b7874c8eaccf014c8eaf5b200000/YXUKBISDNT.JPG', '5', '0', '1', '', '张', '0', '夏天', '2', '');
INSERT INTO `commodity_info` VALUES ('2c94b7964db8593d014db870858b0000', '2015-06-03 16:01:31', null, '1308', '', '八成新，可议价', '', '1111', '八成新', '/upload/20150611/410102/2c94b7874c8eaccf014c8eaf5b200000/VSIWBMQFTD.JPG', '23', '0', '0', '345', '条', '1', '夏天', '1', '');
INSERT INTO `commodity_info` VALUES ('2c94b7bc4de165f2014de238ad2f0000', '2015-06-11 18:44:34', null, '1102', '衣服质量很好', '一张', '', '56', '九成新', '/upload/20150611/410102/2c94b7874c8eaccf014c8eaf5b200000/SMDIJACASH.JPG', '45', '0', '0', '34', '条', '1', '夏天', '0', '');
INSERT INTO `commodity_info` VALUES ('2c94b7bc4de165f2014de23ecc530001', '2015-06-11 18:51:15', null, '1202', '', '很新', '', '123', '八成新', '/upload/20150611/110101/4a4d34164b8feba2014b9018dcaa0001/ADMYEMLNGJ.JPG', '234', '0', '1', '', '个', '1', '102', '0', '');
INSERT INTO `commodity_info` VALUES ('2c94b7bc4de165f2014de2414a4c0002', '2015-06-11 18:53:59', null, '1102', '', '一件', '', '00', '八成新', '/upload/20150611/410102/2c94b7874c8eaccf014c8eaf5b200000/TDUQLMAYRW.JPG', '89', '0', '1', '', '张', '1', '夏天', '1', '');
INSERT INTO `commodity_info` VALUES ('2c96ed184d8a299b014d8b4acfe10000', '2015-05-25 21:37:25', null, '1101', '', '23', '', '高跟鞋', '全新', '/upload/20150611/410102/2c94b7874c8eaccf014c8eaf5b200000/ASEEODVABH.JPG', '45', '0', '1', '', '位', '0', '夏天', '0', '');
INSERT INTO `commodity_info` VALUES ('ff8080814c91add4014c91bff40a0000', '2015-04-07 10:40:18', '', '1604', '', '1', '', '饰品', '六成新', '/upload/20150407/410102/夏天/JWXPWVRTQU.JPG', '12', '0', '1', '', '个', '1', '夏天', '2', null);
INSERT INTO `commodity_info` VALUES ('ff8080814cb5b892014cb5c31af70000', '2015-04-14 10:30:04', null, '1304', '', '1', '', '女包', '九成新', '/upload/20150611/410102/2c94b7874c8eaccf014c8eaf5b200000/DKNQIYIFSN.JPG', '120', '0', '1', '33', 's', '0', '夏天', '2', 'a');
