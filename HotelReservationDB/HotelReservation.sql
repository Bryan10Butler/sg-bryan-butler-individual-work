DROP DATABASE IF EXISTS hotelReservation;

CREATE DATABASE IF NOT EXISTS hotelReservation;

USE hotelReservation;

CREATE TABLE IF NOT EXISTS Person (
  PersonID int(11) NOT NULL AUTO_INCREMENT,
  FirstName varchar(30) NOT NULL,
  LastName varchar(30) NOT NULL,
  Email varchar(50) NULL,
  PhoneNumber varchar(15) NOT NULL,
  Age int(3) NOT NULL,
  PRIMARY KEY (PersonID)
);

CREATE TABLE IF NOT EXISTS Customer (
  CustomerID int(11) NOT NULL AUTO_INCREMENT,
  PersonID int(11) NOT NULL,
  PRIMARY KEY (CustomerID),
  CONSTRAINT FK_PersonsCustomer FOREIGN KEY (PersonID) REFERENCES Person (PersonID)
);

CREATE TABLE IF NOT EXISTS RoomType (
  RoomTypeID int(11) NOT NULL AUTO_INCREMENT,
  TypeOfRoom varchar(30) NOT NULL,
  OccupancyLimit int(2) NOT NULL,
  PRIMARY KEY (RoomTypeID)
);

CREATE TABLE IF NOT EXISTS RoomRates (
  RoomRateID int(11) NOT NULL AUTO_INCREMENT,
  RoomTypeID int(11) NOT NULL,
  StartDate date NOT NULL,
  EndDate date NULL,
  Price decimal(5, 2) NOT NULL,
  PRIMARY KEY (RoomRateID),
  CONSTRAINT FK_RoomTypeRoomRates FOREIGN KEY (RoomTypeID) REFERENCES RoomType (RoomTypeID)
);

CREATE TABLE IF NOT EXISTS Amenities (
  AmenityID int(11) NOT NULL AUTO_INCREMENT,
  AmenityType varchar(30) NOT NULL,
  PRIMARY KEY (AmenityID)
);

CREATE TABLE IF NOT EXISTS Room (
  RoomID int(11) NOT NULL AUTO_INCREMENT,
  RoomTypeID int(11) NOT NULL,
  RoomNumber int(3) NOT NULL,
  Floor varchar(10) NOT NULL,
  PRIMARY KEY (RoomID),
  CONSTRAINT FK_RoomTypeRoom FOREIGN KEY (RoomTypeID) REFERENCES RoomType (RoomTypeID)
);

CREATE TABLE IF NOT EXISTS RoomAmenities (
  RoomID int(11) NOT NULL,
  AmentityID int(11) NOT NULL,
  CONSTRAINT FK_RoomRoomAmenities FOREIGN KEY (RoomID) REFERENCES Room (RoomID),
  CONSTRAINT FK_AmenitiesRoomAmenities FOREIGN KEY (AmentityID) REFERENCES Amenities (AmenityID),
  PRIMARY KEY (RoomID, AmentityID)
);

CREATE TABLE IF NOT EXISTS PromotionCode (
  PromotionCodeID int(11) NOT NULL AUTO_INCREMENT,
  StartDate date NOT NULL,
  EndDate date NULL,
  DiscountDescription varchar(30) NOT NULL,
  DiscountType varchar(30) NOT NULL,
  DiscountValue decimal(5, 2) NOT NULL,
  PRIMARY KEY (PromotionCodeID)
);

CREATE TABLE IF NOT EXISTS AddOns (
  AddOnID int(11) NOT NULL AUTO_INCREMENT,
  AddOnType varchar(30) NOT NULL,
  PRIMARY KEY (AddOnID)
);

CREATE TABLE IF NOT EXISTS AddOnsRate (
  AddOnRateID int(11) NOT NULL AUTO_INCREMENT,
  AddOnID int(11) NOT NULL,
  StartDate date NOT NULL,
  EndDate date NULL,
  Price decimal(5, 2) NOT NULL,
  PRIMARY KEY (AddOnRateID),
  CONSTRAINT FK_AddOnsAddOnsRate FOREIGN KEY (AddOnID) REFERENCES AddOns (AddOnID)
);

CREATE TABLE IF NOT EXISTS Reservation (
  ReservationID int(11) NOT NULL AUTO_INCREMENT,
  CustomerID int(11) NOT NULL,
  PromotionCodeID int(11) NOT NULL,
  CheckIn date NOT NULL,
  CheckOut date NOT NULL,
  PRIMARY KEY (ReservationID),
  CONSTRAINT FK_CustomerReservation FOREIGN KEY (CustomerID) REFERENCES Customer (CustomerID),
  CONSTRAINT FK_PromotionCodeReservation FOREIGN KEY (PromotionCodeID) REFERENCES PromotionCode (PromotionCodeID)
);

CREATE TABLE IF NOT EXISTS Guests (
  GuestID int(11) NOT NULL AUTO_INCREMENT,
  PersonID int(11) NOT NULL,
  ReservationID int(11) NOT NULL,
  PRIMARY KEY (GuestID),
  CONSTRAINT FK_PersonGuest FOREIGN KEY (PersonID) REFERENCES Person (PersonID),
  CONSTRAINT FK_ReservationGuests FOREIGN KEY (ReservationID) REFERENCES Reservation (ReservationID)
);

CREATE TABLE IF NOT EXISTS ReservationRoom (
  ReservationID int(11) NOT NULL,
  RoomID int(11) NOT NULL,
  CONSTRAINT FK_ReservationReservationRoom FOREIGN KEY (ReservationID) REFERENCES Reservation (ReservationID),
  CONSTRAINT FK_RoomReservationRoom FOREIGN KEY (RoomID) REFERENCES Room (RoomID),
  PRIMARY KEY (ReservationID, RoomID)
);

CREATE TABLE IF NOT EXISTS ReservationAddOns (
  ReservationID int(11) NOT NULL,
  AddOnID int(11) NOT NULL,
  CONSTRAINT FK_ReservationReservationAddOns FOREIGN KEY (ReservationID) REFERENCES Reservation (ReservationID),
  CONSTRAINT FK_AddOnsReservationAddOns FOREIGN KEY (AddOnID) REFERENCES AddOns (AddOnID),
  PRIMARY KEY (ReservationID, AddOnID)
);

CREATE TABLE IF NOT EXISTS Billing (
  BillingID int(11) NOT NULL AUTO_INCREMENT,
  Tax decimal(5, 2) NOT NULL,
  ReservationID int(11) NOT NULL,
  PRIMARY KEY (BillingID),
  CONSTRAINT FK_ReservationBilling FOREIGN KEY (ReservationID) REFERENCES Reservation (ReservationID)
);

-- Person data
  INSERT INTO person (FirstName, LastName, Email, PhoneNumber, Age)
    VALUES ('Happy', 'Gilmore', 'HappyGilmore@Clown.com', '222-333-4444', 30),
  ('Ram', 'Rod', 'RamRod@car.com', '111-222-3333', 45),
  ('Billy', 'Madison', 'BillyMadison@MadisonHotels.com', '222-111-4444', 28),
  ('Carl', 'Spackler', 'CarlSpackler@CaddyShack.com', '555-444-3333', 35); 

-- Customers data
  INSERT INTO customer (PersonID)
    VALUES (1),
    (2);

-- Room Type data
  INSERT INTO roomtype (TypeOfRoom, OccupancyLimit)
    VALUES ('Single', 2),
    ('Queen', 2),
    ('King', 2),
    ('Suite', 4);

-- Room Rate data
  INSERT INTO roomrates (RoomTypeID, StartDate, EndDate, Price)
    VALUES (1, '2018/01/01', '2018/04/30', 120),
    (1, '2018/05/01', '2018/08/31', 140),
    (1, '2018/09/01', '2018/12/31', 180),
    (2, '2018/01/01', '2018/04/30', 130),
    (2, '2018/05/01', '2018/08/31', 150),
    (2, '2018/09/01', '2018/12/31', 190),
    (3, '2018/01/01', '2018/04/30', 140),
    (3, '2018/05/01', '2018/08/31', 160),
    (3, '2018/09/01', '2018/12/31', 200),
    (4, '2018/01/01', '2018/04/30', 150),
    (4, '2018/05/01', '2018/08/31', 170),
    (4, '2018/09/01', '2018/12/31', 210);

-- Room data
  INSERT INTO room (RoomTypeID, RoomNumber, Floor)
    VALUES (1, 100, 'Floor-1'),
    (2, 200, 'Floor-2'),
    (3, 300, 'Floor-3'),
    (4, 400, 'Floor-4');

-- Amenities
  INSERT INTO amenities (AmenityType)
    VALUES ('Free WI-FI'), 
    ('Gym'),
    ('Pool'),
    ('Free Coffee');

-- Add Ons data
  INSERT INTO addons (AddOnType)
    VALUES ('All Inclusive'),
    ('Hotel Parking'),
    ('Beach Pass'),
    ('Ski Pass');

-- Add On Rates data
  INSERT INTO addonsrate (AddOnID, StartDate, EndDate, Price)
    VALUES (1, '2018/01/01', '2018/04/30', 150),
    (2, '2018/01/01', '2018/12/31', 25),
    (3, '2018/05/01', '2018/09/10', 500),
    (4, '2018/11/01', '2018/04/01', 500);

-- Promotions data
  INSERT INTO PromotionCode (StartDate, EndDate, DiscountDescription, DiscountType, DiscountValue)
    VALUES ('2018/01/01', '2018/03/31', 'Student Discount', 'Dollar', 100),
    ('2018/10/01', '2018/01/01', 'Holiday Discount', 'Dollar', 100);

-- Reservations data
  INSERT INTO reservation (CustomerID, PromotionCodeID, CheckIn, CheckOut)
    VALUES (1, 1, '2018/02/01', '2018/02/10'),
    (2, 1, '2018/03/01', '2018/03/10');

-- Guests data
  INSERT INTO guests (PersonID, ReservationID)
    VALUES (3, 1),
    (4, 2);

-- Billing data
  INSERT INTO Billing (Tax, ReservationID)
    VALUES (.14, 1),
    (.14, 2); 

-- Reservation Room data
  INSERT INTO ReservationRoom (ReservationID, RoomID)
    VALUES (1, 4),
    (2, 3);

-- Reservation Add On data
  INSERT INTO ReservationAddOns (ReservationID, AddOnID)
    VALUES (1, 4),
    (2, 1); 

-- Room Amenities data
  INSERT INTO RoomAmenities (RoomID, AmentityID)
    VALUES (1, 1),
    (1, 4),
    (2, 1),
    (2, 4),
    (3, 1),
    (3, 4),
    (4, 1),
    (4, 2),
    (4, 3),
    (4, 4);
   
