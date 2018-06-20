DROP DATABASE IF EXISTS DvdDB_Test;
CREATE DATABASE IF NOT EXISTS DvdDB_Test;

USE DvdDB_Test;

CREATE TABLE IF NOT EXISTS Dvd
  (
  dvdId int(11) NOT NULL AUTO_INCREMENT,
  title varchar(30) NOT NULL,
  releaseLocalDate datetime NOT NULL,
  directorName varchar(30) NOT NULL,
  ratingMppa varchar(30) NOT NULL,
  PRIMARY KEY (dvdid)
  );

SELECT *
  FROM Dvd;