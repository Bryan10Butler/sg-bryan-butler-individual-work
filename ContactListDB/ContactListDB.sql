DROP DATABASE IF EXISTS ContactListDB;
CREATE DATABASE IF NOT EXISTS ContactListDB;

USE ContactListDB;

CREATE TABLE IF NOT EXISTS Contact
  (
  ContactID int(11) NOT NULL AUTO_INCREMENT,
  FirstName varchar(30) NOT NULL,
  LastName varchar(30) NOT NULL,
  Company varchar(30) NOT NULL,
  Email varchar(30) NOT NULL,
  Phone varchar(15) NOT NULL,
  PRIMARY KEY(ContactID)
  );

-- Dvd Data
  INSERT INTO Contact(FirstName, LastName, Company, Email, Phone)
    VALUES ('Bryan', 'Butler', 'Liberty', 'B@Liberty.com', '444-555-888');