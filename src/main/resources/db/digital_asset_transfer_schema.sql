/* customer address */
select cu.id, cu.LEGAL_NAME, a.* from `aiblockchain`.customer cu, `aiblockchain`.address a,  `aiblockchain`.contact_address ca
where cu.LEGAL_NAME like '%Athi%'
and a.ID = ca.ADDRESS_ID and cu.ID = ca.CUSTOMER_ID;

/* customer phone */
select cu.id, cu.LEGAL_NAME, p.* from `aiblockchain`.customer cu, `aiblockchain`.phone p,  `aiblockchain`.contact_phone cp
where cu.LEGAL_NAME like '%Athi%'
and p.ID = cp.PHONE_ID and cu.ID = cp.CUSTOMER_ID;

/* customer email */
select cu.id, cu.LEGAL_NAME, e.* from `aiblockchain`.customer cu, `aiblockchain`.email e,  `aiblockchain`.contact_email ce
where cu.LEGAL_NAME like '%Athi%'
and e.ID = ce.EMAIL_ID and cu.ID = ce.CUSTOMER_ID;

/* Acct with assets */
select acct.ACCT_ID, asst.DESCRIPTION 
from account acct, asset asst
where acct.ASSET_ID = asst.ID 
and acct.CUSTOMER_ID = (select c.id from customer c where c.legal_name like '%Athi%');

select t.DESCRIPTION, l.PURCHASE_DATE, l.PURCHASE_PRICE, l.SALE_DATE, l.SALE_PRICE, a.DESCRIPTION
from transaction t, lot l, asset a
where t.ASSET_ID = a.ID and l.ID = t.LOT_ID
order by l.PURCHASE_DATE desc;

/* asset, act and transaction */
select acct.* , t.*, t.asset_id, a.id, acct.asset_id
from asset a, account acct, transaction t
where a.id = acct.asset_id and a.id = t.ASSET_ID and a.id = 1
group by t.asset_id/*, acct.asset_id, a.id*/
;

/***********************************************************************************************/

drop table transaction;
drop table lot ;
drop table asset ;
drop table account ;
drop table contact_address;
drop table contact_phone;
drop table contact_email;
drop table address;
drop table email;
drop table phone;
drop table customer;

CREATE TABLE `customer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LEGAL_NAME` varchar(45) DEFAULT NULL,
  `TAX_ID` varchar(45) DEFAULT NULL,
  `GOVT_ID` varchar(45) DEFAULT NULL,
  `EMERGENCY_CONTACT` varchar(45) DEFAULT NULL,
  `BENEFICIARY` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Customer Information';

CREATE TABLE `address` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ATYPE` varchar(45) DEFAULT NULL,
  `ADDRESS1` varchar(45) DEFAULT NULL,
  `ADDRESS2` varchar(45) DEFAULT NULL,
  `CITY` varchar(45) DEFAULT NULL,
  `STATE` varchar(45) DEFAULT NULL,
  `ZIP` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Address Information';

CREATE TABLE `contact_address` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(11) NOT NULL,
  `ADDRESS_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CONTACT_ADDRESS_CUSTOMER_IDX` (`CUSTOMER_ID`),
  KEY `CONTACT_ADDRESS_IDX` (`ADDRESS_ID`),
  CONSTRAINT `CONTACT_ADDRESS_CUSTOMER_FK` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CONTACT_ADDRESS_FK` FOREIGN KEY (`ADDRESS_ID`) REFERENCES `address` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Contact Address Information';

CREATE TABLE `phone` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PTYPE` varchar(45) DEFAULT NULL,
  `PNUMBER` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Phone Information';

CREATE TABLE `contact_phone` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(11) NOT NULL,
  `PHONE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CONTACT_PHONE_CUSTOMER_IDX` (`CUSTOMER_ID`),
  KEY `CONTACT_PHONE_IDX` (`PHONE_ID`),
  CONSTRAINT `CONTACT_PHONE_CUSTOMER_FK` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CONTACT_PHONE_FK` FOREIGN KEY (`PHONE_ID`) REFERENCES `phone` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Contact Phone Information';

CREATE TABLE `email` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ETYPE` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Email Information';

CREATE TABLE `contact_email` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` int(11) NOT NULL,
  `EMAIL_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `CONTACT_EMAIL_CUSTOMER_IDX` (`CUSTOMER_ID`),
  KEY `CONTACT_EMAIL_IDX` (`EMAIL_ID`),
  CONSTRAINT `CONTACT_EMAIL_CUSTOMER_FK` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `CONTACT_EMAIL_FK` FOREIGN KEY (`EMAIL_ID`) REFERENCES `email` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Contact Email Information';

CREATE TABLE `account` (
  `ID` varchar(64) NOT NULL,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ACCT_CUST_FK_IDX` (`CUSTOMER_ID`),
  CONSTRAINT `ACCT_CUST_FK` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Account Information';

CREATE TABLE `asset` (
  `ID` varchar(64) NOT NULL,
  `ACCT_ID` varchar(64) DEFAULT NULL,
  `DESCRIPTION` varchar(45) DEFAULT NULL,
  `CUT` varchar(45) DEFAULT NULL,
  `COLOR` varchar(45) DEFAULT NULL,
  `CLARITY` varchar(45) DEFAULT NULL,
  `CARAT` varchar(45) DEFAULT NULL,
  `SHAPE` varchar(45) DEFAULT NULL,
  `CERTIFICATION` varchar(45) DEFAULT NULL,
  `QUALITY` varchar(45) DEFAULT NULL,
  `WEIGHT` varchar(45) DEFAULT NULL,
  `MEASUREMENTS` varchar(45) DEFAULT NULL,
  `ASSET_HASH` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ASSET_ACCT_IDX` (`ACCT_ID`),
  CONSTRAINT `ASSET_ACCT_FK` FOREIGN KEY (`ACCT_ID`) REFERENCES `account` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Asset Information';

CREATE TABLE `lot` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCT_ID` varchar(64) DEFAULT NULL,
  `PURCHASE_DATE` date DEFAULT NULL,
  `PURCHASE_PRICE` decimal(10,0) DEFAULT NULL,
  `SALE_DATE` date DEFAULT NULL,
  `SALE_PRICE` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `LOT_ACCT_IDX` (`ACCT_ID`),
  CONSTRAINT `LOT_ACCT_FK` FOREIGN KEY (`ACCT_ID`) REFERENCES `account` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='Lot Information';

CREATE TABLE `transaction` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FROM_ACCT_ID` varchar(64) DEFAULT NULL,
  `TO_ACCT_ID` varchar(64) DEFAULT NULL,
  `OWNER_ID` varchar(64) DEFAULT NULL,  
  `ASSET_ID` varchar(45) DEFAULT NULL,
  `LOT_ID` int(11) DEFAULT NULL,
  `DESCRIPTION` varchar(45) DEFAULT NULL,
  `BEFORE_HASH` varchar(45) DEFAULT NULL,
  `BEFORE_TRANS` varchar(45) DEFAULT NULL,
  `AFTER_HASH` varchar(45) DEFAULT NULL,
  `AFTER_TRANS` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `TRANS_ASSET_IDX` (`ASSET_ID`),
  KEY `TRANS_LOT_IDX` (`LOT_ID`),
  KEY `TRANS_FROM_ACCT_IDX` (`FROM_ACCT_ID`),
  KEY `TRANS_TO_ACCT_IDX` (`TO_ACCT_ID`),
  KEY `TRANS_OWN_ACCT_IDX` (`OWNER_ID`),
  CONSTRAINT `TRANS_FROM_ACCT_FK` FOREIGN KEY (`FROM_ACCT_ID`) REFERENCES `account` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,  
  CONSTRAINT `TRANS_TO_ACCT_FK` FOREIGN KEY (`TO_ACCT_ID`) REFERENCES `account` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION, 
  CONSTRAINT `TRANS_OWN_ACCT_FK` FOREIGN KEY (`OWNER_ID`) REFERENCES `account` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,  
  CONSTRAINT `TRANS_ASSET_FK` FOREIGN KEY (`ASSET_ID`) REFERENCES `asset` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,  
  CONSTRAINT `TRANS_LOT_FK` FOREIGN KEY (`LOT_ID`) REFERENCES `lot` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Transaction Information';

drop table hibernate_sequence;
CREATE TABLE hibernate_sequence (id INT NOT NULL, next_val BIGINT NOT NULL);
INSERT INTO hibernate_sequence VALUES (0, 0);

/***********************************************************************************************/
/* schema data */

INSERT INTO `aiblockchain`.`customer`(`ID`,`LEGAL_NAME`,`TAX_ID`,`GOVT_ID`,`EMERGENCY_CONTACT`,`BENEFICIARY`) VALUES
(1, "Athi Muthu", "1234560789", "M8373626385", "Emergency Contact", "Beneficiary"),
(2, "Macy's", "9876554242", "89777764641", "", ""),
(3, "Mike W", "45654665476", "M74r5644566", "Contact1", "Beneficiary1");

INSERT INTO `aiblockchain`.`account`(`ID`,`CUSTOMER_ID`) VALUES 
("10_12345", 1),
("20_12345", 2),
("30_12345", 3);

INSERT INTO `aiblockchain`.`address`(`ID`,`ATYPE`,`ADDRESS1`,`ADDRESS2`,`CITY`,`STATE`,`ZIP`) VALUES
(1, "Home", "203 Yarrow Cir", "", "Dayton", "NJ", "08810"),
(2, "Work", "642 Alexander Rd", "", "Princeton", "NJ", "08640");

INSERT INTO `aiblockchain`.`contact_address`(`ID`,`CUSTOMER_ID`,`ADDRESS_ID`) VALUES
(1, 1, 1),
(2, 1, 2);

INSERT INTO `aiblockchain`.`email`(`ID`,`ETYPE`,`EMAIL`) VALUES
(1, "Home", "athi@home.com"),
(2, "Work", "athi@work.com");

INSERT INTO `aiblockchain`.`contact_email`(`ID`,`CUSTOMER_ID`,`EMAIL_ID`)
VALUES
(1, 1, 1);

INSERT INTO `aiblockchain`.`phone`(`ID`,`PTYPE`,`PNUMBER`) VALUES
(1, "Home", "732-486-0083"),
(2, "Mobile", "732-789-7683");

INSERT INTO `aiblockchain`.`contact_phone`(`ID`,`CUSTOMER_ID`,`PHONE_ID`)
VALUES
(1, 1, 1),
(2, 1, 1);

INSERT INTO `aiblockchain`.`asset`(`ID`,`ACCT_ID`,`DESCRIPTION`,`CUT`,`COLOR`,`CLARITY`,`CARAT`,`SHAPE`,`CERTIFICATION`,`QUALITY`,`WEIGHT`,`MEASUREMENTS`,`ASSET_HASH`) VALUES
("1", "10_12345", "Diamond Pendant", "Round", "Colorless", "VVS1", "1.01", "Square", "None", "Yellow", "1", "1x1x1", "");

INSERT INTO `aiblockchain`.`lot`(`ID`,`ACCT_ID`,`PURCHASE_DATE`,`PURCHASE_PRICE`,`SALE_DATE`,`SALE_PRICE`) VALUES
(1, 2, "2017-01-01", 1000.0, null, 0.0),
(2, 1, "2017-03-20", 5000.0, null, 0.0);

INSERT INTO `aiblockchain`.`transaction`(`ID`,`FROM_ACCT_ID`,`TO_ACCT_ID`,`OWNER_ID`,`ASSET_ID`,`LOT_ID`,`DESCRIPTION`,`BEFORE_HASH`,`BEFORE_TRANS`,`AFTER_HASH`,`AFTER_TRANS`) VALUES
(1, "10_12345", "10_12345", "10_12345", "1", 1, "Macy's Retail Purchase", "", "", "", ""),
(2, "10_12345", "20_12345", "20_12345", "1", 2, "Birthday Gift", "", "", "", "");
