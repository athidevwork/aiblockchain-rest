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
  `itemId` varchar(45) DEFAULT NULL,
  `acctId` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `aibc_trans` varchar(45) DEFAULT NULL,
  `rowhash` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `aiblockchain`.`diamond_history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `diamond_fk` INT NULL,
  `date` DATE NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `diamondFK`
    FOREIGN KEY (`id`)
    REFERENCES `aiblockchain`.`diamond` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
