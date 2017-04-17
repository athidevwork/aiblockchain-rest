create database aiblockchain;  
use aiblockchain;  
create table users(id int(10),name varchar(40),age int(3));

CREATE TABLE `users` (
  `id` int(10) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
