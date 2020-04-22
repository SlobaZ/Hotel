CREATE USER IF NOT EXISTS jwduser IDENTIFIED BY 'pass';

DROP DATABASE IF EXISTS hotelspringbootangularjs;
CREATE DATABASE hotelspringbootangularjs DEFAULT CHARACTER SET utf8;

USE hotelspringbootangularjs;

GRANT ALL ON hotelspringbootangularjs.* TO 'jwduser'@'%';

FLUSH PRIVILEGES;