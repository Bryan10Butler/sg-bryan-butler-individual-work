-- MySQL Script generated by MySQL Workbench
-- Mon Apr 16 13:03:13 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema SuperHero
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `SuperHeroProd` ;

-- -----------------------------------------------------
-- Schema SuperHero
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SuperHeroProd` DEFAULT CHARACTER SET utf8 ;
USE `SuperHeroProd` ;

-- -----------------------------------------------------
-- Table `Hero`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Hero` ;

CREATE TABLE IF NOT EXISTS `Hero` (
  `HeroID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) NOT NULL,
  `Description` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`HeroID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Location` ;

CREATE TABLE IF NOT EXISTS `Location` (
  `LocationID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(50) NOT NULL,
  `Description` VARCHAR(255) NOT NULL,
  `Street` VARCHAR(50) NOT NULL,
  `City` VARCHAR(50) NOT NULL,
  `State` VARCHAR(50) NULL,
  `Zip` VARCHAR(50) NOT NULL,
  `Longitude` VARCHAR(50) NULL,
  `Latitude` VARCHAR(50) NULL,
  PRIMARY KEY (`LocationID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Power`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Power` ;

CREATE TABLE IF NOT EXISTS `Power` (
  `PowerID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`PowerID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Organization` ;

CREATE TABLE IF NOT EXISTS `Organization` (
  `OrganizationID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(255) NOT NULL,
  `LocationID` BIGINT(20) NULL,
  PRIMARY KEY (`OrganizationID`),
  CONSTRAINT `FK_orgnization_location`
    FOREIGN KEY (`LocationID`)
    REFERENCES `Location` (`LocationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sighting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Sighting` ;

CREATE TABLE IF NOT EXISTS `Sighting` (
  `SightingID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `Date` DATE NOT NULL,
  `Description` VARCHAR(255) NOT NULL,
  `LocationID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`SightingID`),
  CONSTRAINT `FK_sighting_location`
    FOREIGN KEY (`LocationID`)
    REFERENCES `Location` (`LocationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HeroSighting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HeroSighting` ;

CREATE TABLE IF NOT EXISTS `HeroSighting` (
  `HeroSightingID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `HeroID` BIGINT(20) NOT NULL,
  `SightingID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`HeroSightingID`),
  CONSTRAINT `FK_herosighting_hero`
    FOREIGN KEY (`HeroID`)
    REFERENCES `Hero` (`HeroID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_herosighting_sighting`
    FOREIGN KEY (`SightingID`)
    REFERENCES `Sighting` (`SightingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HeroPower`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HeroPower` ;

CREATE TABLE IF NOT EXISTS `HeroPower` (
  `HeroPowerID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `HeroID` BIGINT(20) NOT NULL,
  `PowerID` BIGINT(20) NOT NULL,
  PRIMARY KEY (`HeroPowerID`),
  CONSTRAINT `FK_heropower_hero`
    FOREIGN KEY (`HeroID`)
    REFERENCES `Hero` (`HeroID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_heropower_power`
    FOREIGN KEY (`PowerID`)
    REFERENCES `Power` (`PowerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `HeroOrganization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `HeroOrganization` ;

CREATE TABLE IF NOT EXISTS `HeroOrganization` (
  `HeroOrganizationID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `HeroID` BIGINT(20) NULL,
  `OrganizationID` BIGINT(20) NULL,
  PRIMARY KEY (`HeroOrganizationID`),
  CONSTRAINT `FK_heroorganization_hero`
    FOREIGN KEY (`HeroID`)
    REFERENCES `Hero` (`HeroID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_heroorganization_organization`
    FOREIGN KEY (`OrganizationID`)
    REFERENCES `Organization` (`OrganizationID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO Hero (Name, Description)
  VALUES ('Human Torch', 'can overheat quickly'),
  ('Captain America', ' has a shield'),
  ('Batman', 'travels alone, scared of bats'),
  ('The Invisible Woman', 'A part of the fantastic four');
INSERT INTO Power (Name, Description)
  VALUES ('Invisibility', 'allows you to go unseen to the naked eye'),
  ('force field creation', 'ultimate protection'),
  ('Telekinesis', 'allows you to read minds'),
  ('Flight', 'allows you to fly'),
  ('Flame Breath', 'allows flame of fire from your mouth');
INSERT INTO HeroPower (HeroID, PowerID)
  VALUES (1, 4),
  (1, 5),
  (2, 4),
  (4, 3),
  (4, 2),
  (4, 1);
INSERT INTO Location (Name, Description, Street, City, State, Zip, Latitude, Longitude)
  VALUES ('Starbucks', 'Coffee Shop', '123 Main St', 'Sunderland', 'MA', '01378', '42.466373', '-72.579511'),
  ('Chick fil a', 'Fast food restaurant', '9 Malphrus Rd', 'Bluffton', 'SC', '29910', '32.242902', '-80.825906'),
  ('Yosemite', 'National Park', '140 highway', 'Yosemite Valley', 'CA', '95389', '37.865101', '-119.538329'),
  ('Statue of Liberty', 'tourist destination', '45 main st', 'New York', 'NY', '10004', '40.689249', '-74.044500');
INSERT INTO Sighting (Date, Description, LocationID)
  VALUES ('2018/04/04', 'saw the invisible woman drinking coffee', '1'),
  ('2018/03/21', 'saw Captain America getting a chicken sandwhich at Chick Fil A', '2'),
  ('2018/04/02', 'saw Batman at Chick Fil A', '2'),
  ('2018/04/04', 'briefly saw invisible woman near statue of liberty', '4');
INSERT INTO HeroSighting (HeroID, SightingID)
  VALUES (4, 1),
  (2, 2),
  (3, 3);
INSERT INTO Organization (Name, Description, LocationID)
  VALUES ('Fantastic Four', 'group of 4', '4'),
  ('Avengers', 'avenge people', '3');
INSERT INTO HeroOrganization (HeroID, OrganizationID)
  VALUES (1, 1),
  (4, 1),
  (2, 2);
