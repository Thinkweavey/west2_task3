/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : shopping

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 14/12/2023 13:13:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品编码',
  `title` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品描述',
  `price` float NOT NULL COMMENT '单价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '10001', '德芙巧克力', '德芙巧克力10粒装', 39.9);
INSERT INTO `goods` VALUES (2, '10002', '奥利奥饼干', '奥利奥饼干10袋礼盒装', 59.9);
INSERT INTO `goods` VALUES (3, '10003', '亲嘴烧', '1片', 0.5);
INSERT INTO `goods` VALUES (4, '10004', '测试数据11', '描述测111', 35);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '订单编号',
  `pcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '商品编号',
  `price` decimal(10, 2) DEFAULT NULL COMMENT '订单价格',
  `ctime` datetime(0) DEFAULT NULL COMMENT '下单时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (4662, '1702460596753', '10003', 0.50, '2023-12-13 17:43:17');
INSERT INTO `order` VALUES (4663, '1702460599960', '10002', 59.90, '2023-12-13 17:43:20');
INSERT INTO `order` VALUES (4664, '1702460603389', '10001', 39.90, '2023-12-13 17:43:23');
INSERT INTO `order` VALUES (4665, '1702522465788', '10004', 35.00, '2023-12-14 10:54:26');

SET FOREIGN_KEY_CHECKS = 1;
