CREATE SCHEMA `car_rent` ;

CREATE TABLE `car_rent`.`rental_users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `user_address` varchar(150) NOT NULL,
  `user_phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_id`));

CREATE TABLE `car_rent`.`rental_cars` (
  `car_id` int NOT NULL AUTO_INCREMENT,
  `car_brand` varchar(40) NOT NULL,
  `car_type` varchar(40) NOT NULL,
  `lic_plate` varchar(10) NOT NULL,
  `daily_cost` int NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`car_id`));

CREATE TABLE `car_rent`.`rental_rents` (
  `rent_id` int NOT NULL AUTO_INCREMENT,
  `car_id` int NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `car_brand` varchar(45) NOT NULL,
  `car_type` varchar(45) NOT NULL,
  `lic_plate` varchar(45) NOT NULL,
  `rent_date` date NOT NULL,
  `return_date` date NOT NULL,
  `fee` int NOT NULL,
  PRIMARY KEY (`rent_id`),
  UNIQUE KEY `rent_id_UNIQUE` (`rent_id`));

CREATE TABLE `car_rent`.`rental_returns` (
  `return_id` int unsigned NOT NULL AUTO_INCREMENT,
  `lic_plate` varchar(45) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `return_date` date NOT NULL,
  `fine` int NOT NULL,
  PRIMARY KEY (`return_id`));

CREATE TABLE `car_rent`.`rental_admins` (
  `admin_id` int unsigned NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(250) DEFAULT NULL,
  `admin_pwd` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`admin_id`);
INSERT INTO `car_rent`.`rental_admins` (`admin_name`, `admin_pwd`) VALUES ('admin', 'pwd'));
