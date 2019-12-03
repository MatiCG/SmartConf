CREATE DATABASE IF NOT EXISTS SMARTCONF;
USE SMARTCONF;
CREATE TABLE IF NOT EXISTS user
(
	user_id int AUTO_INCREMENT primary key NOT NULL,
	username varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	password varchar(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS reunions
(
	reunion_id int NOT NULL,
	leader_id int NOT NULL,
	reunion_title varchar(255) NOT NULL,
	reunion_date datetime2,
	reunion_status varchar(255) DEFAULT 'not started'
	reunion_subject varchar NOT NULL
);
CREATE TABLE IF NOT EXISTS user_has_reunion
(
	user_id int NOT NULL,
	reunion_id int NOT NULL
);
