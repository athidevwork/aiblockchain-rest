create table customer (id int not null auto_increment, legal_name varchar(45) default null, tax_id varchar(45) default null, govt_id varchar(45) default null, emergency_contact varchar(45) default null, beneficiary varchar(45) default null, primary key(id));

create table address (id int not null auto_increment, customer_id int, address_type varchar(45), line1 varchar(45), line2 varchar(45), city varchar(45), state varchar(45), zip varchar(45), primary key(id), foreign key(customer_id) references customer(id));

create table phone (id int not null auto_increment, customer_id int, phone_type varchar(45), phone_number varchar(45), primary key(id), foreign key(customer_id) references customer(id));

create table email (id int not null auto_increment, customer_id int, email_type varchar(45), email_address varchar(45), primary key(id), foreign key(customer_id) references customer(id));

create table account (id int not null auto_increment, account_id varchar(64), customer_id int, account_desc varchar(45), primary key(id), foreign key(customer_id) references customer(id));

create table asset (id int not null auto_increment, asset_id varchar(64), acct_id int, description varchar(45), cut varchar(45), clarity varchar(45), carat varchar(45), shape varchar(45), weight varchar(45), measurements varchar(45), asset_hash varchar(64), primary key(id), foreign key(acct_id) references account(id));

create table lot (id int not null auto_increment, acct_id int, asset_id int, status varchar(45), qty int, purchase_date datetime, price decimal(10,3), sale_date datetime, sale_price decimal(10,3), primary key(id), foreign key(acct_id) references account(id), foreign key(asset_id) references asset(id));

create table transaction (id int not null auto_increment, from_acct_id int, to_acct_id int, owner_id int, asset_id int, lot_id int, description varchar(45), before_hash varchar(64), after_hash varchar(64), before_trans varchar(64), after_trans varchar(64), primary key(id), foreign key(from_acct_id) references account(id), foreign key(to_acct_id) references account(id), foreign key(owner_id) references account(id), foreign key(asset_id) references asset(id), foreign key(lot_id) references lot(id));