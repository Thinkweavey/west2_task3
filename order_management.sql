CREATE DATABASE `order_management`;
USE `order_management`;
DROP TABLE IF EXISTS `goods`;
/*goods的结构*/
CREATE TABLE `goods` (
  `g_id` INT NOT NULL,
  `g_name` VARCHAR(255) NOT NULL,
  `g_price` DOUBLE NOT NULL,
  PRIMARY KEY (`g_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb3;

/*goods的数据*/
INSERT  INTO `goods`(`g_id`,`g_name`,`g_price`) VALUES (1,'薯片',7),(2,'饼干',9),(3,'果冻',3),(4,'方便面',5),(5,'奶茶',15),(6,'可乐',7),(7,'蛋糕',22),(8,'泡椒鸡爪',5),(9,'巧克力',9);

DROP TABLE IF EXISTS `order`;

/*order的结构*/
CREATE TABLE `order` (
  `o_id` INT NOT NULL AUTO_INCREMENT,
  `og_id` INT DEFAULT NULL,
  `time` DATETIME DEFAULT NULL,
  `count` INT DEFAULT NULL,
  `price` DOUBLE DEFAULT NULL,
  PRIMARY KEY (`o_id`),
  KEY `og_id` (`og_id`),
  CONSTRAINT `o_g` FOREIGN KEY (`og_id`) REFERENCES `goods` (`g_id`)
) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

/*order的数据*/
INSERT INTO `order`(`o_id`,`og_id`,`time`,`count`,`price`)VALUES(1,2,'2023-11-28 18:24:00',1,9),(2,4,'2023-11-28 18:56:00',5,25);
