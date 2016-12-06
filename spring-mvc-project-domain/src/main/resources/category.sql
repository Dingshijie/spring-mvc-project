/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50130
Source Host           : localhost:3306
Source Database       : schoole

Target Server Type    : MYSQL
Target Server Version : 50130
File Encoding         : 65001

Date: 2016-12-06 21:31:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `ID` varchar(128) NOT NULL,
  `CATEGORY` varchar(2) NOT NULL,
  `CODE` varchar(4) NOT NULL,
  `ENABLE` int(11) DEFAULT NULL,
  `HOT` int(11) NOT NULL,
  `NAME` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('2c94b7874c8eaccf014c8eb288bb0001', '18', '1806', '1', '1', '其他书籍');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687a84010000', '11', '1101', '1', '1', '连衣裙');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687ad73b0001', '11', '1102', '1', '0', '半身裙');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687b143a0002', '11', '1103', '1', '0', '外套');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687b5f110003', '11', '1104', '1', '1', '短裤');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687bbaf50004', '11', '1105', '1', '0', '长裤');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687bf8990005', '11', '1106', '1', '0', '棉衣');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687c2d1b0006', '11', '1107', '1', '0', '其他');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687cac8f0007', '12', '1201', '1', '0', '男士外套');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687d0ae50008', '12', '1202', '1', '0', '衬衫');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687d4f770009', '12', '1203', '1', '0', 'T恤');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687db0bc000a', '12', '1204', '1', '0', '牛仔裤');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687dfedb000b', '12', '1205', '1', '0', '男士棉衣');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687f25cb000c', '13', '1301', '1', '0', '男士运动鞋');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687f7ffe000d', '13', '1302', '1', '0', '女士运动鞋');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c687fd0fe000e', '13', '1303', '1', '0', '男士皮鞋');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6880725c000f', '13', '1304', '1', '0', '女靴');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6880c1a80010', '13', '1305', '1', '1', '高跟鞋');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c688113590011', '13', '1306', '1', '0', '凉鞋');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c688156080012', '13', '1307', '1', '0', '男士包包');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c68819c890013', '13', '1308', '1', '1', '女士包包');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6881f5130014', '14', '1401', '1', '0', '羽毛球（拍）');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c688248300015', '14', '1402', '1', '0', '乒乓球（拍）');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6882969d0016', '14', '1403', '1', '0', '足球');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6882d90f0017', '14', '1404', '1', '1', '篮球');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c688330040018', '14', '1405', '1', '0', '排球');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c68839ff30019', '14', '1406', '1', '0', '跳绳');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6883e73c001a', '14', '1407', '1', '1', '自行车');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c68842a1b001b', '14', '1408', '1', '0', '电动车');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c688471e1001c', '15', '1501', '1', '1', '手机');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6884c2ef001d', '15', '1502', '1', '1', '电脑');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c68850679001e', '15', '1503', '1', '0', '数码相机');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c68855d87001f', '15', '1504', '1', '0', '手机数码配件');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6885c6850020', '15', '1505', '1', '0', '电脑配件');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c68861c3f0021', '16', '1601', '1', '0', '美妆产品');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c68866a8b0022', '16', '1602', '1', '1', '护肤产品');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6886e4ff0023', '16', '1603', '1', '0', '美妆工具');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6887294a0024', '16', '1604', '1', '1', '头饰配件');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6887801d0025', '17', '1701', '1', '1', '暖瓶');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6887d82b0026', '17', '1702', '1', '1', '台灯');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c68887b330027', '17', '1703', '1', '0', '洗衣盆');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6888bf340028', '17', '1704', '1', '1', '衣架');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6889016a0029', '17', '1705', '1', '0', '垃圾桶');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6889485a002a', '18', '1801', '1', '1', '理科书籍');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c688995c9002b', '18', '1802', '1', '1', '文科书籍');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c6889dc87002c', '18', '1803', '1', '1', '上课笔记');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c688a3d7c002d', '18', '1804', '1', '1', '考研辅导书');
INSERT INTO `category` VALUES ('2c94b7884c686ccb014c688abc3c002e', '18', '1805', '1', '0', '钢笔圆珠笔');
INSERT INTO `category` VALUES ('2c94b7884c917cb4014c91853be70000', '91', '9101', '1', '0', '学习类');
INSERT INTO `category` VALUES ('2c94b7884c917cb4014c918581ee0001', '91', '9102', '1', '0', '生活类');
INSERT INTO `category` VALUES ('2c94b7884c917cb4014c9185cb580002', '91', '9103', '1', '0', '其他类');
