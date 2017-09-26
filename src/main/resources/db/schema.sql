CREATE TABLE `diamond` (
  `id` int(11) NOT NULL,
  `itemId` int(11) NOT NULL,
  `acctId` int(11) DEFAULT NULL,
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
  `email` varchar(45) DEFAULT NULL,
  `rowhash` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `diamond_history` (
  `id` int(11) NOT NULL,
  `diamond_itemfk` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`diamond_itemfk`),
  KEY `DiamondItemFK_idx` (`diamond_itemfk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;