
CREATE DATABASE IF NOT EXISTS `ideploy` DEFAULT CHARACTER SET = utf8mb4;

use `ideploy`;

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `uid` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '帐号ID，英文字母数字',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0-普通帐号，1-ldap帐号，5-超级管理员',
  `nick` varchar(50) NOT NULL COMMENT '帐号昵称',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '电话号码',
  `email` varchar(256) NOT NULL DEFAULT '' COMMENT '邮箱地址',
  `dding` varchar(80) NOT NULL DEFAULT '' COMMENT '用户钉钉号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
   UNIQUE KEY `uniq_key_name_type` (`name`,`type`),
   PRIMARY KEY (`uid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';