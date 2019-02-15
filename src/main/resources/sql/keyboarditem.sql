/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : keyboarditem

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-03-29 14:27:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_article_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_article_type`;
CREATE TABLE `sys_article_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id 为1 ，默认是我发布的',
  `articlePicture` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `articleTypeName` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '分类名字',
  `articleTypeDesc` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '分类备注',
  `state` int(1) NOT NULL COMMENT '分类状态0可用，1不可用, 3代表是用户自己的分类',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_article_type
-- ----------------------------
INSERT INTO `sys_article_type` VALUES ('1', 'image&quality=100&size=b4000_4000&sec=1521770865&di=14fcb5181444f1929f031b42f1b866e8&src=http://img5.xiazaizhijia.com/walls/20160708/1440x900_2f172c09d079701.jpg', '我自己发布', '自己', '3', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('2', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', 'ip发布', 'ip', '0', '2018-03-22 17:26:33', '2018-03-22 17:26:36');
INSERT INTO `sys_article_type` VALUES ('3', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('4', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('5', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('6', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('7', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('8', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('9', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('10', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('11', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('12', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('13', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('14', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('15', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('16', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('17', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('18', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('19', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('20', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('21', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('22', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('23', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('24', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('25', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('26', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('27', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('28', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('29', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('30', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');
INSERT INTO `sys_article_type` VALUES ('31', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '我自己发布', '自己', '0', '2018-03-22 16:09:50', '2018-03-22 16:09:52');

-- ----------------------------
-- Table structure for sys_digital_currency_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_digital_currency_info`;
CREATE TABLE `sys_digital_currency_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `digitalCurrencyName` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '数字货币名字',
  `digitalCurrencyAddr` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数字货币在公网地址',
  `parentId` bigint(20) NOT NULL COMMENT '币对应的父id',
  `fee` decimal(20,10) NOT NULL COMMENT '手续费',
  `tokenDecimal` int(2) NOT NULL COMMENT '每种币对应的Decimals',
  `realBalanceLimit` decimal(20,10) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `digitalCurrencyAddr_Index` (`digitalCurrencyAddr`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_digital_currency_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_native_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_native_code`;
CREATE TABLE `sys_native_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nativeCode` varchar(10) COLLATE utf8mb4_bin NOT NULL COMMENT '国家代码',
  `nativeName` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '国家',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  `natveCodeSort` int(5) NOT NULL COMMENT '排序，越大的越前面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_native_code
-- ----------------------------
INSERT INTO `sys_native_code` VALUES ('1', '0086', '中国', '2018-02-03 15:52:29', '2018-02-03 15:52:32', '1');
INSERT INTO `sys_native_code` VALUES ('2', '001', '美国', '2018-02-03 15:53:25', '2018-02-03 15:53:28', '2');
INSERT INTO `sys_native_code` VALUES ('3', '0082', '韩国', '2018-02-03 15:53:52', '2018-02-03 15:53:54', '3');
INSERT INTO `sys_native_code` VALUES ('4', '0081', '日本', '2018-02-03 15:54:21', '2018-02-03 15:54:23', '4');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ios 判断',
  `noticeName` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '公告名字',
  `noticeDesc` text COLLATE utf8mb4_bin NOT NULL COMMENT '版本描述',
  `type` int(1) NOT NULL COMMENT '显示类型：0：不显示，1：跳转链接，2：加群',
  `noticeNum` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '安卓判断',
  `androidState` int(1) NOT NULL COMMENT 'android是否显示 0 显示，1 不显示',
  `iosState` int(1) NOT NULL COMMENT 'ios是否显示 0 显示，1 不显示',
  `androidUrl` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'android跳转URL',
  `iosUrl` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'ios跳转URL',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------

-- ----------------------------
-- Table structure for sys_phone_sms
-- ----------------------------
DROP TABLE IF EXISTS `sys_phone_sms`;
CREATE TABLE `sys_phone_sms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `phone` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '记录手机信息',
  `ipAddr` text COLLATE utf8mb4_bin NOT NULL COMMENT 'IP地址',
  `count` int(1) NOT NULL COMMENT '发送短信计数，暂定为5',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_index_unique` (`phone`) USING BTREE,
  KEY `phone_index_normal` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_phone_sms
-- ----------------------------
INSERT INTO `sys_phone_sms` VALUES ('1', '008615219486931', 0x3132372E302E302E312C3139322E3136382E312E3133302C3139322E3136382E312E313332, '2');
INSERT INTO `sys_phone_sms` VALUES ('2', '008613249841384', 0x3132372E302E302E312C3139322E3136382E312E313330, '2');
INSERT INTO `sys_phone_sms` VALUES ('3', '008613267017181', 0x3139322E3136382E312E313332, '3');

-- ----------------------------
-- Table structure for sys_proterties_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_proterties_data`;
CREATE TABLE `sys_proterties_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataKey` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `dataValue` varchar(500) COLLATE utf8mb4_bin NOT NULL,
  `dataDesc` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_dataKey_index_unique` (`dataKey`) USING BTREE,
  KEY `sys_dataKey_index_normal` (`dataKey`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_proterties_data
-- ----------------------------
INSERT INTO `sys_proterties_data` VALUES ('1', 'phoneCodeCount', '50', '手机短信发送条数限制', '2018-03-20 15:13:31', '2018-03-20 15:13:33');
INSERT INTO `sys_proterties_data` VALUES ('2', 'invitationCode', '0', '0需要邀请码，1不需要邀请码', '2018-03-20 16:13:44', '2018-03-20 16:13:48');
INSERT INTO `sys_proterties_data` VALUES ('3', 'invitationCodeCount', '3', '用户邀请码的个数', '2018-03-20 16:26:42', '2018-03-20 16:26:44');
INSERT INTO `sys_proterties_data` VALUES ('4', 'invitationExemptionPhone', '008618801370152', '豁免邀请码规则的手机号', '2018-03-20 17:08:25', '2018-03-20 17:08:31');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nativeCode` varchar(4) NOT NULL COMMENT '区号',
  `account` varchar(100) NOT NULL COMMENT '用户id',
  `createTime` datetime NOT NULL,
  `nickName` varchar(100) DEFAULT NULL COMMENT '昵称',
  `passwd` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '手机号',
  `type` int(1) unsigned zerofill NOT NULL COMMENT '暂时没有使用',
  `updateTime` datetime NOT NULL,
  `loginState` int(1) NOT NULL COMMENT '0：离线；1：在线；2：活跃',
  `state` int(1) unsigned zerofill NOT NULL COMMENT '0代表正常，1代表伪删除，2代表删除',
  `parentExtensionId` bigint(20) DEFAULT NULL COMMENT '代表推广用的父id',
  `invitationCount` int(3) NOT NULL COMMENT '邀请次数累计',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_phone_index_unique` (`phone`) USING BTREE,
  UNIQUE KEY `sys_account_index_unique` (`account`) USING BTREE,
  KEY `sys_account_index_normal` (`account`) USING BTREE,
  KEY `sys_phone_index_normal` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('4', '0086', '25237350', '2018-03-22 15:29:47', 'hhh', '', '', '008615219486931', '0', '2018-03-22 15:29:47', '1', '0', null, '3');
INSERT INTO `sys_user` VALUES ('6', '0086', '88505919', '2018-03-26 14:29:28', '', '', '', '008613249841384', '0', '2018-03-26 14:29:28', '1', '0', '4', '0');
INSERT INTO `sys_user` VALUES ('7', '0086', '70786332', '2018-03-28 15:46:47', '', '', '', '008613267017181', '0', '2018-03-28 15:46:47', '1', '0', '4', '0');

-- ----------------------------
-- Table structure for sys_user_article
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_article`;
CREATE TABLE `sys_user_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `articleMongoId` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `forwardCount` int(11) NOT NULL COMMENT '已转次数',
  `clickUrlCount` int(11) NOT NULL COMMENT '链接点击次数,阅读量',
  `type` int(1) NOT NULL COMMENT '这个字段暂时废弃',
  `articleType` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `extensionMonery` decimal(14,2) NOT NULL COMMENT '推广设置的推广金额',
  `state` int(1) NOT NULL COMMENT '0代表正常，1代表取消推广，2代表该条数据已经废弃',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `articleMongoId_index_unique` (`articleMongoId`) USING BTREE,
  KEY `userId_index` (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_user_article
-- ----------------------------
INSERT INTO `sys_user_article` VALUES ('2', '4', '5ab4c5caf5ca1404eca9b60c', '0', '0', '0', '1,2', '32.60', '0', '2018-03-23 17:15:57', '2018-03-23 17:15:59');
INSERT INTO `sys_user_article` VALUES ('3', '4', '5ab4d53cf5ca141b7421947b', '0', '0', '0', '1,2', '1.90', '1', '2018-03-23 18:21:51', '2018-03-23 18:21:54');
INSERT INTO `sys_user_article` VALUES ('4', '4', '5ab4d652f5ca141b7421947c', '0', '2', '1', '1,2', '23.60', '1', '2018-03-23 18:26:27', '2018-03-23 18:26:27');

-- ----------------------------
-- Table structure for sys_user_article_mol
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_article_mol`;
CREATE TABLE `sys_user_article_mol` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `totalMOL` decimal(14,2) NOT NULL,
  `todayMOL` decimal(14,2) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId_index_unique` (`userId`) USING BTREE,
  KEY `userId_index_normal` (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_user_article_mol
-- ----------------------------
INSERT INTO `sys_user_article_mol` VALUES ('1', '7', '0.10', '0.10', '2018-03-28 17:28:01', '2018-03-28 17:28:02');
INSERT INTO `sys_user_article_mol` VALUES ('2', '6', '0.40', '0.40', '2018-03-28 18:57:16', '2018-03-28 18:57:16');
INSERT INTO `sys_user_article_mol` VALUES ('3', '4', '0.10', '0.10', '2018-03-29 09:39:39', '2018-03-29 09:39:40');

-- ----------------------------
-- Table structure for sys_user_identification
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_identification`;
CREATE TABLE `sys_user_identification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `realName` varchar(50) COLLATE utf8mb4_bin NOT NULL COMMENT '真实用户名',
  `identificationCard` varchar(150) COLLATE utf8mb4_bin NOT NULL COMMENT '身份证',
  `picturePrecedingUrl` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '正面照',
  `pictureAfterUrl` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '背面照',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId_index_unique` (`userId`) USING BTREE,
  KEY `userId_index_normal` (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_user_identification
-- ----------------------------
INSERT INTO `sys_user_identification` VALUES ('2', '4', '董飞', '530328199402170916', 'http://gosmallapp.com/ide/%E8%91%A3%E9%A3%9E%E6%AD%A3%E9%9D%A2.jpg?x-oss-process=style/xiaoyixiao', 'http://gosmallapp.com/ide/2.jpg?x-oss-process=style/xiaoyixiao', '2018-03-27 10:27:35', '2018-03-27 10:27:35');

-- ----------------------------
-- Table structure for sys_user_invitationcode
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_invitationcode`;
CREATE TABLE `sys_user_invitationcode` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `invitationCode` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '用户邀请码，每个用户限制5个',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_userId_index_unique` (`userId`) USING BTREE,
  KEY `sys_invitationCode_index_normal` (`invitationCode`) USING BTREE,
  KEY `sys_userId_index_normal` (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_user_invitationcode
-- ----------------------------
INSERT INTO `sys_user_invitationcode` VALUES ('1', '4', '0636ixKj', '2018-03-22 15:29:47', '2018-03-22 15:29:47');
INSERT INTO `sys_user_invitationcode` VALUES ('35', '6', '23oEoKDi', '2018-03-28 15:20:15', '2018-03-28 15:20:15');
INSERT INTO `sys_user_invitationcode` VALUES ('36', '7', '928QSAJF', '2018-03-28 15:46:47', '2018-03-28 15:46:47');

-- ----------------------------
-- Table structure for sys_user_schedule
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_schedule`;
CREATE TABLE `sys_user_schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` bigint(20) NOT NULL,
  `pyPasswd` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  `equipmentIdentification` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  `molCount` decimal(14,2) NOT NULL COMMENT '用户mol币数量',
  `articleType` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '分类id用,分隔',
  `headIcon` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '用户头像',
  `userLoginCount` int(2) NOT NULL COMMENT '使用userLoginCount之前，每天定时要检查userLoginState的状态是否0，为0代表这个要清0，否则每天第一次发消息要+1',
  `userLoginState` int(1) NOT NULL COMMENT '用户每天赠币状态0代表每天重置，1代表已经赠送。（每晚定时更新）',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  `authenticationState` int(1) NOT NULL COMMENT '0代表没有实名认证，1代表已经实名认证',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_index_unique` (`userId`) USING BTREE,
  KEY `userId_index_normal` (`userId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_user_schedule
-- ----------------------------
INSERT INTO `sys_user_schedule` VALUES ('1', '4', '', 'phonedev', '27.10', '1,2', 'https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1521770865&di=14fcb5181444f1929f031b42f1b866e8&src=http://img5.xiazaizhijia.com/walls/20160708/1440x900_2f172c09d079701.jpg', '2', '1', '2018-03-22 15:29:47', '2018-03-22 15:29:47', '1');
INSERT INTO `sys_user_schedule` VALUES ('3', '6', 'ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413', '09dd2a7cfa5d1d33704362491309f5de', '15.60', '1', 'http://gosmallapp.com/keybroad/userinfo/6/1522221594042.jpg?x-oss-process=style/ysx_66', '1', '0', '2018-03-26 14:29:28', '2018-03-26 14:29:28', '0');
INSERT INTO `sys_user_schedule` VALUES ('4', '7', 'ba3253876aed6bc22d4a6ff53d8406c6ad864195ed144ab5c87621b6c233b548baeae6956df346ec8c17f5ea10f35ee3cbc514797ed7ddd3145464e2a0bab413', '0000', '0.10', '1', 'http://13.125.122.136:8088/Avatar/f767f279dd6406ad144359cb5352d9d0.jpg', '1', '1', '2018-03-28 15:46:47', '2018-03-28 15:46:47', '0');

-- ----------------------------
-- Table structure for sys_user_share
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_share`;
CREATE TABLE `sys_user_share` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dataId` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '数据id',
  `userArticleId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL COMMENT '用户id',
  `useCount` int(11) NOT NULL COMMENT '用户点击数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `sys_userArticleId_userId_index_unique` (`dataId`) USING BTREE,
  KEY `sys_userArticleId_index_normal` (`dataId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_user_share
-- ----------------------------
INSERT INTO `sys_user_share` VALUES ('1', '1233232', '4', '6', '1');
INSERT INTO `sys_user_share` VALUES ('2', '1233212132', '4', '6', '1');
INSERT INTO `sys_user_share` VALUES ('3', 'efw2323', '4', '6', '1');

-- ----------------------------
-- Table structure for sys_version
-- ----------------------------
DROP TABLE IF EXISTS `sys_version`;
CREATE TABLE `sys_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `versionName` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '版本名字',
  `versionDesc` text COLLATE utf8mb4_bin NOT NULL COMMENT '版本描述',
  `type` int(1) NOT NULL COMMENT '更新  0：强制，1：不强制',
  `versionNum` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT '把要升级的不同的版本号写在这里，用，分隔',
  `androidState` int(1) NOT NULL COMMENT 'android是否更新 0 更新，1 不更新',
  `iosState` int(1) NOT NULL COMMENT 'ios是否更新 0 更新，1 不更新',
  `androidUrl` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT 'android更新URL',
  `iosUrl` varchar(255) COLLATE utf8mb4_bin NOT NULL COMMENT 'ios更新URL',
  `createTime` datetime NOT NULL,
  `updateTime` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_version
-- ----------------------------
INSERT INTO `sys_version` VALUES ('1', '强制升级', 0x31E38081E69BB4E696B0E7BAA2E58C85E58A9FE883BDEFBC8CE698A5E88A82E5928CE69C8BE58F8BE4B880E8B5B7E68AA2E58CBAE59D97E993BEE7BAA2E58C85E590A7EFBC9B5C6E5C5C6E32E38081E8BDACE8B4A6E58A9FE883BDEFBC9AE58CBAE59D97E993BEE79A84E694AFE4BB98E5AE9DE69DA5E4BA86EFBC815C6E5C5C6E33E38081E5B881E59C88E8B584E8AEAFEFBC9AE7ACACE4B880E697B6E997B4E69FA5E79C8BE5B881E59C88E8B584E8AEAFEFBC9B5C5C6E5C6E34E38081E69BB4E5A5BDE79C8BE79A84E7958CE99DA2E8AEBEE8AEA1E38082, '0', '0.0.2,0.0.1', '0', '0', 'https://www.both.cash/appdownload/both.apk', 'https://www.pgyer.com/FfJk', '2018-03-22 18:01:57', '2018-03-22 18:02:00');
