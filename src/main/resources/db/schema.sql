CREATE TABLE `diamond` (
  `id` int(11) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `cut` varchar(45) DEFAULT NULL,
  `color` varchar(45) DEFAULT NULL,
  `clarity` varchar(45) DEFAULT NULL,
  `carat` varchar(45) DEFAULT NULL,
  `shape` varchar(45) DEFAULT NULL,
  `certification` varchar(45) DEFAULT NULL,
  `quality` varchar(45) DEFAULT NULL,
  `weight` varchar(45) DEFAULT NULL,
  `measurements` varchar(45) DEFAULT NULL,
  `uuid` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `rowhash` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;