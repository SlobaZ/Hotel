CREATE USER IF NOT EXISTS root IDENTIFIED BY 'root';

DROP DATABASE IF EXISTS hotelspringbootangularjs;
CREATE DATABASE hotelspringbootangularjs DEFAULT CHARACTER SET utf8;

USE hotelspringbootangularjs;

GRANT ALL ON hotelspringbootangularjs.* TO 'root'@'%';

FLUSH PRIVILEGES;
