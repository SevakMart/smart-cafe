CREATE TABLE IF NOT EXISTS user(
id bigint not null auto_increment,
email varchar(255),
first_Name varchar(255),
is_Active bit not null,
last_Name varchar(255),
password varchar(255),
role varchar(255),
primary key (id));