/*
Navicat MySQL Data Transfer

Source Server         : 本地sql
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : library

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-05-06 22:19:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for author
-- ----------------------------
DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `remake` text,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of author
-- ----------------------------

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `authorid` int(11) DEFAULT NULL,
  `pressid` int(11) DEFAULT NULL,
  `remake` text,
  `intro` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book
-- ----------------------------

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `power` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES ('1', '管理员', '1');
INSERT INTO `power` VALUES ('2', '会员', '1');

-- ----------------------------
-- Table structure for press
-- ----------------------------
DROP TABLE IF EXISTS `press`;
CREATE TABLE `press` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `remake` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of press
-- ----------------------------

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `bookid` int(11) DEFAULT NULL,
  `borrowTime` varchar(255) DEFAULT NULL,
  `returnTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of record
-- ----------------------------

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `powerid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('1', 'admin', 'admin', '超级管理员', '0', '1888888888', '1', '1');
