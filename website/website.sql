
CREATE TABLE user(
id int PRIMARY KEY AUTO_INCREMENT,
phonenum VARCHAR (15) NOT NULL,
UNIQUE KEY phonenum (phonenum)
);

CREATE TABLE `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `use_id` int(11) DEFAULT NULL,
  `title` varchar(20) NOT NULL,
  `content` text NOT NULL,
  `picture_url` varchar(255) DEFAULT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `title` (`title`),
  KEY `use_id` (`use_id`),
  KEY `time` (`time`) USING BTREE,
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`use_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;



CREATE TABLE `reply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) DEFAULT NULL,
  `content` text,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `picture_url` varchar(255) DEFAULT NULL,
  `ans_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `reply` (`post_id`),
  KEY `user` (`ans_id`),
  CONSTRAINT `reply` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `user` FOREIGN KEY (`ans_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;



