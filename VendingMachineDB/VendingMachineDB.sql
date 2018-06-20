DROP DATABASE IF EXISTS VendingMachineDB;
CREATE DATABASE IF NOT EXISTS VendingMachineDB;

USE VendingMachineDB;

CREATE TABLE IF NOT EXISTS Item
  (
  ItemId int(11) NOT NULL AUTO_INCREMENT,
  Name varchar(30) NOT NULL,
  Price decimal(7,2) NOT NULL,
  Quantity int(11) NOT NULL,
  PRIMARY KEY(ItemId)
  );

-- Dvd Data
  INSERT INTO Item(Name, Price, Quantity)
    VALUES ('Chips', 2.50, 1),
  ('Apple', 1.50, 2);

  SELECT *
    FROM Item;