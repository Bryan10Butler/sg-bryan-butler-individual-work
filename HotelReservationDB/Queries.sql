USE hotelReservation;

-- Room Details-------------------------------------------------------
SELECT
  r.RoomNumber,
  r.Floor,
  rt.TypeOfRoom,
  rt.OccupancyLimit,
  a.AmenityType
FROM Room r
  INNER JOIN RoomType rt
    ON rt.RoomTypeID = r.RoomTypeID
  INNER JOIN RoomAmenities ra
    ON ra.RoomID = r.RoomID
  INNER JOIN Amenities a
    ON a.AmenityID = ra.AmentityID
GROUP BY r.RoomNumber,
         a.AmenityType;

-- Select Room by Price------------------------------------------------
SELECT
  rr.StartDate,
  rr.EndDate,
  rr.price,
  rt.TypeOfRoom,
  rt.OccupancyLimit
FROM RoomRates rr
  INNER JOIN RoomType rt
    ON rr.RoomTypeID = rt.RoomTypeID
WHERE rr.price BETWEEN 100 AND 140
ORDER BY rr.StartDate, rr.Price ASC;

-- Add-Ons-----------------------------------------------------------------
SELECT
  ao.AddOnType,
  aor.StartDate,
  aor.EndDate,
  aor.Price
FROM addons ao
  INNER JOIN addonsrate aor
    ON ao.AddOnID = aor.AddOnID
ORDER BY aor.Price ASC;

-- Promotion Codes----------------------------------------------------------
SELECT
  p.StartDate,
  p.EndDate,
  p.DiscountDescription,
  p.DiscountValue
FROM promotioncode p;

-- Reservation Lookup by Reservation ID-------------------------------------
SELECT
  r.ReservationID,
  p.FirstName,
  p.LastName,
  p.Email,
  p.PhoneNumber,
  p.Age,
  rt.TypeOfRoom,
  pc.DiscountDescription,
  ao.AddOnType,
  a.AmenityType
FROM reservation r
  INNER JOIN customer c
    ON r.CustomerID = c.CustomerID
  INNER JOIN person p
    ON c.PersonID = p.PersonID
  INNER JOIN promotioncode pc
    ON r.PromotionCodeID = pc.PromotionCodeID
  INNER JOIN billing b
    ON b.ReservationID = r.ReservationID
  INNER JOIN reservationroom rr
    ON rr.ReservationID = r.ReservationID
  INNER JOIN reservationaddons rao
    ON rao.ReservationID = r.ReservationID
  INNER JOIN addons ao
    ON rao.AddOnID = ao.AddOnID
  INNER JOIN addonsrate aor
    ON aor.AddOnID = ao.AddOnID
  INNER JOIN room rm
    ON rr.RoomID = rm.RoomID
  INNER JOIN roomtype rt
    ON rm.RoomTypeID = rt.RoomTypeID
  INNER JOIN roomrates rmr
    ON rmr.RoomTypeID = rt.RoomTypeID
  INNER JOIN roomamenities ra
    ON ra.RoomID = rm.RoomID
  INNER JOIN amenities a
    ON ra.AmentityID = a.AmenityID
WHERE r.ReservationID = 1
GROUP BY a.AmenityType;

-- Guests of Customer by Reservation ID-------------------------------------
SELECT
  p.FirstName,
  p.LastName,
  p.Email,
  p.PhoneNumber,
  p.age,
  g.ReservationID
FROM person p
  INNER JOIN Guests g
    ON p.PersonID = g.PersonID
WHERE g.ReservationID = 1;

-- Billing------------------------------------------------------------------

-- Add Ons by Res ID --DO NOT USE VIEW--
DROP VIEW AddOnByRes;
CREATE VIEW AddOnByRes
AS
SELECT
  r.reservationID,
  aor.Price
FROM Reservation r
  INNER JOIN reservationaddons rao
    ON rao.ReservationID = r.ReservationID
  INNER JOIN addons ao
    ON ao.AddOnID = rao.AddOnID
  INNER JOIN addonsrate aor
    ON aor.AddOnID = ao.AddOnID;

SELECT
  *
FROM AddOnByRes;

-- Promotion Codes by Res ID --DO NOT USE VIEW--
DROP VIEW PromoByRes;
CREATE VIEW PromoByRes
AS
SELECT
  p.DiscountValue
FROM promotioncode p;

SELECT
  *
FROM PromoByRes pbr;

-- Main Room Bill by Reservation ID------------------------------------------------------------------
SELECT
  r.ReservationID,
  p.FirstName,
  p.LastName,
  r.CheckIn,
  r.CheckOut,
  -- Price of Room only--
  SUM((DATEDIFF(r.CheckOut, r.CheckIn) * rr.Price)) AS RoomCostOnly,
  pc.DiscountDescription,
  pc.DiscountValue,
  ao.AddOnType,
  aor.Price,
  -- Price of Room exlcuding tax--
  SUM(((DATEDIFF(r.CheckOut, r.CheckIn) * rr.Price) + aor.Price) - pc.DiscountValue) AS RoomCostExcludingTax,
  -- Tax--
  SUM((((DATEDIFF(r.CheckOut, r.CheckIn) * rr.Price) + aor.Price) - pc.DiscountValue) * b.Tax) AS Tax,
  -- Price of Room with Tax included--
  SUM(((((DATEDIFF(r.CheckOut, r.CheckIn) * rr.Price) + aor.Price) - pc.DiscountValue)) * (1 + b.tax)) AS Total
FROM reservation r
  INNER JOIN reservationroom rrm
    ON rrm.ReservationID = r.ReservationID
  INNER JOIN room rm
    ON rrm.RoomID = rm.RoomID
  INNER JOIN roomtype rt
    ON rm.RoomTypeID = rt.RoomTypeID
  INNER JOIN roomrates rr
    ON rr.RoomTypeID = rt.RoomTypeID
    AND r.CheckIn BETWEEN rr.StartDate AND rr.EndDate
  INNER JOIN Customer c
    ON c.CustomerID = r.CustomerID
  INNER JOIN Person p
    ON p.PersonID = c.PersonID
  INNER JOIN reservationaddons rao
    ON rao.ReservationID = r.ReservationID
  INNER JOIN addons ao
    ON ao.AddOnID = rao.AddOnID
  INNER JOIN addonsrate aor
    ON aor.AddOnID = ao.AddOnID
  INNER JOIN promotioncode pc
    ON pc.PromotionCodeID = r.PromotionCodeID
  INNER JOIN billing b
    ON b.BillingID = r.ReservationID
WHERE r.ReservationID = 1;