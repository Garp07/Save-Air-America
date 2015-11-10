-- 1
SELECT a.PersonCode, a.FirstName, a.LastName, c.Street, c.City, c.State, c.Zipcode, c.Country, b.Email FROM Persons a 
	LEFT JOIN Emails b 
		ON a.PersonID = b.PersonID
	LEFT JOIN Addresses c 
		ON a.AddressID = c.AddressID;

-- 2
INSERT INTO Emails(PersonID, Email)
SELECT a.PersonID, 'test678@aol.com' FROM Persons a 
	WHERE a.PersonCOde = 'MZr';
    
-- 3
UPDATE Addresses a 
	JOIN Airports b 
		ON a.AddressID = b.AddressID
	SET a.Street = '120 Victory Blvd'
		WHERE b.AirportID = (SELECT b.AirportID FROM Airports b WHERE b.AirportName ='Dallas Fields');
        
-- 4
-- delete tickets from seats, then from InvoiceProducts, then from Products, then from Services, then from Tickets, then Address, then Airport
DELETE f FROM Seats f
	LEFT JOIN InvoiceProducts a 
		ON a.InvoiceProductsID = f.InvoiceProductsID
	LEFT JOIN Products b
		ON a.ProductID = b.ProductID
	WHERE b.TicketID = 
		(SELECT t.TicketID FROM  Tickets t 
			LEFT JOIN Airports d 
				ON t.DepAirportID = d.AirportID
			LEFT JOIN Airports e 
				ON t.ArrAirportID = e.AirportID
			WHERE d.AirportID = 4 OR e.AirportID = 4);

DELETE a FROM InvoiceProducts a
	LEFT JOIN Products b
		ON a.ProductID = b.ProductID
	WHERE b.TicketID = 
		(SELECT t.TicketID FROM  Tickets t 
			LEFT JOIN Airports d 
				ON t.DepAirportID = d.AirportID
			LEFT JOIN Airports e 
				ON t.ArrAirportID = e.AirportID
			WHERE d.AirportID = 4 OR e.AirportID = 4);
            
DELETE a FROM InvoiceProducts a
	LEFT JOIN Products b
		ON a.ProductID = b.ProductID
	LEFT JOIN Services s ON
		s.ServiceID = b.ServiceID 
	WHERE s.TicketID = 
		(SELECT t.TicketID FROM  Tickets t 
			LEFT JOIN Airports d 
				ON t.DepAirportID = d.AirportID
			LEFT JOIN Airports e 
				ON t.ArrAirportID = e.AirportID
			WHERE d.AirportID = 4 OR e.AirportID = 4);

DELETE b FROM Products b
	WHERE b.TicketID = 
		(SELECT t.TicketID FROM  Tickets t 
			LEFT JOIN Airports d 
				ON t.DepAirportID = d.AirportID
			LEFT JOIN Airports e 
				ON t.ArrAirportID = e.AirportID
			WHERE d.AirportID = 4 OR e.AirportID = 4); 
            
DELETE b FROM Products b
	LEFT JOIN Services s ON
		s.ServiceID = b.ServiceID 
	WHERE s.TicketID = 
		(SELECT t.TicketID FROM  Tickets t 
			LEFT JOIN Airports d 
				ON t.DepAirportID = d.AirportID
			LEFT JOIN Airports e 
				ON t.ArrAirportID = e.AirportID
			WHERE d.AirportID = 4 OR e.AirportID = 4); 

DELETE s FROM Services s
	WHERE s.TicketID = 
		(SELECT t.TicketID FROM Tickets t
			LEFT JOIN Airports d 
				ON t.DepAirportID = d.AirportID
			LEFT JOIN Airports e 
				ON t.ArrAirportID = e.AirportID
			WHERE d.AirportID = 4 OR e.AirportID = 4);

DELETE a FROM Tickets a 
	LEFT JOIN Airports b 
		ON a.DepAirportID = b.AirportID
	LEFT JOIN Airports c 
		ON a.ArrAirportID = c.AirportID
	WHERE b.AirportID = 4 OR c.AirportID = 4;

DELETE a FROM Airports a 
	WHERE a.AirportID = 4;
    
-- 5
SELECT p.ProductCode FROM Products p 
	JOIN InvoiceProducts ip
		ON ip.ProductID = p.ProductID
	JOIN Invoices i
		ON i.InvoiceID = ip.InvoiceID
	WHERE i.InvoiceID = 1;
    
-- 6
SELECT i.InvoiceCode FROM Invoices i 
	JOIN Customers c 
		ON c.CustomerID = i.CustomerID
	WHERE c.CustomerID = 2;
    
-- 7
INSERT INTO InvoiceProducts(InvoiceID, ProductID, SpecialAssistancePersonID)
	SELECT 1, p.ProductID, a.PersonID
		FROM Products p, Persons a 
			WHERE p.ProductCode = 'xer4' AND a.PersonID = 14;
            
-- 8
SELECT SUM(t.PointsPerMile) AS SumOfPointsPerMile FROM Tickets t
	JOIN Products p 
		ON p.TicketID = t.TicketID
	WHERE p.ProductType = 'TA';
    
-- 9
SELECT i.InvoiceDate, COUNT(p.ProductID) AS TotalAwardTickets FROM Invoices i 
	JOIN InvoiceProducts ip
		ON ip.InvoiceID = i.InvoiceID
	JOIN Products p 
		ON p.ProductID = ip.ProductID
	WHERE p.ProductType = 'TA' AND i.InvoiceDate = '2015-04-12';
    
-- 10
SELECT s.TravelDate, p.FirstName, p.LastName, a.Street, a.City, a.State, a.Zipcode, a.Country, p.PhoneNumber, e.Email, s.Nationality, s.Age, s.IDNumber FROM Seats s
	JOIN Persons p 
		ON p.PersonID = s.PersonID
	LEFT JOIN Addresses a
		ON a.AddressID = p.AddressID
	LEFT JOIN Emails e 
		ON p.PersonID = e.PersonID
	JOIN InvoiceProducts ip 
		ON ip.InvoiceProductsID = s.InvoiceProductsID
	JOIN Products pr 
		ON pr.ProductID = ip.ProductID
	JOIN Tickets t 
		ON t.TicketID = pr.TicketID
	WHERE t.FlightNumber = 'A4' 
		AND t.DepAirportID = 
			(SELECT t.DepAirportID FROM Tickets t 
				JOIN Airports a 
					ON a.AirportID = t.DepAirportID
				JOIN Addresses ad 
					ON ad.AddressID = a.AddressID
				WHERE ad.City = 'Glendale Heights')
		AND t.DepTime = '09:20';

-- 11
SELECT COUNT(DISTINCT ip.InvoiceID) AS TotalInvoices, SUM(a.FacilityFee) AS AirportRevenue FROM Seats s 
	JOIN InvoiceProducts ip 
		ON ip.InvoiceProductsID = s.InvoiceProductsID
	JOIN Products pr 
		ON pr.ProductID = ip.ProductID
	JOIN Tickets t 
		ON t.TicketID = pr.TicketID
	JOIN Airports a 
		ON a.AirportID = t.ArrAirportID
	WHERE a.AirportID = 2;

-- 12
SELECT i.InvoiceDate, SUM(ip.Quantity * se.Cost) AS TotalDrinkRevenue From InvoiceProducts ip 
	JOIN Products p
		ON p.ProductID = ip.ProductID
	JOIN Services se 
		ON se.ServiceID = p.ServiceID
	JOIN Invoices i 
		ON i.InvoiceID = ip.InvoiceID
	WHERE p.ProductType = 'SR' AND i.InvoiceDate = '2015-10-02';

-- 13
SELECT p.ProductType, COUNT(*) FROM Invoices i 
	JOIN InvoiceProducts ip
		ON ip.InvoiceID = i.InvoiceID
	JOIN Products p
		ON p.ProductID = ip.ProductID
	JOIN Services s
		ON s.ServiceID = p.ServiceID
	GROUP BY p.ProductType;

-- 14
SELECT i.InvoiceCode FROM InvoiceProducts ip
	JOIN Products p 
		ON p.ProductID = ip.ProductID
	JOIN Invoices i 
		ON i.InvoiceID = ip.InvoiceID
	WHERE p.TicketID IS NOT NULL
	GROUP BY i.InvoiceID
		HAVING COUNT(*) > 1;
        
-- 15
SELECT i.InvoiceCode FROM Invoices i 
	JOIN Customers c 
		ON c.PrimaryContactID = i.SalespersonID;














