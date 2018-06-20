DROP DATABASE IF EXISTS DvdDB;
CREATE DATABASE IF NOT EXISTS DvdDB;

USE DvdDB;

CREATE TABLE IF NOT EXISTS Dvd
  (
  dvdId int(11) NOT NULL AUTO_INCREMENT,
  title varchar(30) NOT NULL,
  releaseLocalDate datetime NOT NULL,
  directorName varchar(30) NOT NULL,
  ratingMppa varchar(30) NOT NULL,
  PRIMARY KEY (dvdid)
  );

-- Dvd Data
  INSERT INTO Dvd(Title, ReleaseLocalDate, DirectorName, RatingMppa)
    VALUES ('Happy', '1995/01/01', 'Bryan Butler', 'R');

  SELECT *
    FROM Dvd d;
