CREATE DATABASE another_blog;
grant all on another_blog.* to 'alokit'@'localhost';

use another_blog;

DROP TABLE IF EXISTS `admin_user`;


CREATE TABLE `admin_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_user_name` varchar(50) NOT NULL,
  `login_password` varchar(50) NOT NULL,
  `nick_name` varchar(50) NOT NULL,
  `locked` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into `admin_user`(`id`,`login_user_name`,`login_password`,`nick_name`,`locked`) values (1,'admin','e10adc3949ba59abbe56e057f20f883e','jindal',0);


DROP TABLE IF EXISTS `blog_category`;

CREATE TABLE `blog_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `category_name` varchar(50) NOT NULL ,
  `category_icon` varchar(50) NOT NULL ,
  `category_rank` int(11) NOT NULL DEFAULT '1' ,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' ,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

select * from blog_category;

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `blog_title` varchar(200) NOT NULL ,
  `blog_sub_url` varchar(200) NOT NULL,
  `blog_short_desc` varchar(200) NOT NULL,
  `blog_cover_image` varchar(200) NOT NULL,
  `blog_content` mediumtext NOT NULL,
  `blog_category_id` bigint(20) NOT NULL,
  `blog_category_name` varchar(50) NOT NULL,
  `blog_tags` varchar(200) NOT NULL,
  `blog_status` tinyint(4) NOT NULL DEFAULT '0',
  `blog_views` bigint(20) NOT NULL DEFAULT '0',
  `enable_comment` tinyint(4) NOT NULL DEFAULT '0',
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

select * from blog;

DROP TABLE IF EXISTS `blog_config`;

CREATE TABLE `blog_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `config_name` varchar(100) NOT NULL DEFAULT '' ,
  `config_value` varchar(200) NOT NULL DEFAULT '' ,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `blog_comment`;

CREATE TABLE `blog_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `blog_id` bigint(20) NOT NULL DEFAULT '0' ,
  `commentator` varchar(50) NOT NULL DEFAULT '' ,
  `email` varchar(100) NOT NULL DEFAULT '' ,
  `website_url` varchar(50) NOT NULL DEFAULT '' ,
  `comment_body` varchar(200) NOT NULL DEFAULT '' ,
  `comment_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `commentator_ip` varchar(20) NOT NULL DEFAULT '' ,
  `reply_body` varchar(200) NOT NULL DEFAULT '' ,
  `reply_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  `comment_status` tinyint(4) NOT NULL DEFAULT '0',
  `is_deleted` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

select * from blog_comment;

DROP TABLE IF EXISTS `blog_link`;

CREATE TABLE `blog_link` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `link_type` tinyint(4) NOT NULL DEFAULT '0' ,
  `link_name` varchar(50) NOT NULL ,
  `link_url` varchar(100) NOT NULL ,
  `link_description` varchar(100) NOT NULL ,
  `link_rank` int(11) NOT NULL DEFAULT '0' ,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' ,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `blog_tag`;

CREATE TABLE `blog_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `tag_name` varchar(100) NOT NULL ,
  `is_deleted` tinyint(4) NOT NULL DEFAULT '0' ,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

select * from blog_tag;

DROP TABLE IF EXISTS `blog_tag_relation`;

CREATE TABLE `blog_tag_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `blog_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

