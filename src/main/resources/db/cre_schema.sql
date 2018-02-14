create database aiblockchain;
create database cre;

use cre;
create table domain_user (
	id int not null auto_increment,
	email varchar(25),
	type varchar(25) default null, /* fault user or diamond */
	username varchar(75),
	password varchar(75),
	first_name varchar(25),
	last_name varchar(25),
	primary key (id, email)
);

insert into domain_user (id, email, type, username, password, first_name, last_name) values (1, 'mike@ai-blockchain.com', 'fault', 'mike', 'mike123', 'Mike', 'Wilkowski');
insert into domain_user (id, email, type, username, password, first_name, last_name) values (2, 'scott@ai-blockchain.com', 'fault', 'scott', 'scott123', 'Scott', 'Smith');

create table asset (
	id int not null auto_increment, 
	atype varchar(25) default null, /* cre, diamond */		
	building varchar(25),
	location varchar(25),
	unit varchar(25),
	description varchar(75),
	primary key(id)
);

INSERT INTO cre.asset(id, atype, building, location, unit, description) VALUES(1,'CRE','Building 1','Basement','','Building 1 Utilities');
INSERT INTO cre.asset(id, atype, building, location, unit, description) VALUES(2,'CRE','Building 1','1st Floor','101',"Drew's Apartment");
INSERT INTO cre.asset(id, atype, building, location, unit, description) VALUES(3,'CRE','Building 1','2nd Floor','203',"Athi's Apartment");

create table fault (
	id int not null auto_increment, 
	asset_id int, 
	category varchar(25) default null, /* fault category */
	subcategory varchar(25) default null, /* fault sub category */
	description varchar(75) default null,	
	fdate varchar(25),
	fsignature varchar(64) default null,
	aibc_status varchar(25) default null,
	aibc_trans  varchar(64) default null,
	primary key(id), 
	foreign key(asset_id) references asset(id)
);

/*INSERT INTO asset (id, atype, category, subcategory, description) VALUES (1, 'CRE', 'ROOF_LEAKAGE', 'Shingles', 'Shingles replacement');
INSERT INTO asset (id, atype, category, subcategory, description) VALUES (2, 'CRE', 'ROOF_LEAKAGE', 'Chimney', 'Chimney replacement');
INSERT INTO asset (id, atype, category, subcategory, description) VALUES (3, 'CRE', 'ROOF_LEAKAGE', 'Plumbing', 'Plumbing replacement');	
INSERT INTO asset (id, atype, category, subcategory, description) VALUES (4, 'CRE', 'FLOOR_LEAKAGE', 'Sump Pump Basement', 'Sump Pump Leakage');
INSERT INTO asset (id, atype, category, subcategory, description) VALUES (5, 'CRE', 'FLOOR_LEAKAGE', 'Plumbing', 'Plumbing Leak');
INSERT INTO asset (id, atype, category, subcategory, description) VALUES (6, 'CRE', 'ELECTRICAL', 'Burnt Motor', 'Burnt Motor');
INSERT INTO asset (id, atype, category, subcategory, description) VALUES (7, 'CRE', 'ELECTRICAL', 'Cable Burn', 'Electric Cable Burn');
INSERT INTO asset (id, atype, category, subcategory, description) VALUES (8, 'CRE', 'ELECTRICAL', 'A/C unit', 'A/C Repair');
INSERT INTO asset (id, atype, category, subcategory, description) VALUES (9, 'CRE', 'Plumbing', 'Blocked Filter', 'Blocked Filter');
INSERT INTO asset (id, atype, category, subcategory, description) VALUES (10, 'CRE', 'Plumbing', 'Kitchen Faucet', 'Kitchen Faucet Leak');
INSERT INTO asset (id, atype, category, subcategory, description) VALUES (11, 'CRE', 'Plumbing', 'Bathroom', 'Bathroom Leak');*/