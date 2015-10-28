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
	LEFT JOIN Tickets c 
		ON b.TicketID = c.TicketID
	LEFT JOIN Airports d 
		ON c.DepAirportID = d.AirportID
	LEFT JOIN Airports e 
		ON c.ArrAirportID = e.AirportID
	WHERE d.AirportID = 4 OR e.AirportID = 4;

DELETE a FROM InvoiceProducts a
	LEFT JOIN Products b
		ON a.ProductID = b.ProductID
	LEFT JOIN Tickets c 
		ON b.TicketID = c.TicketID
	LEFT JOIN Airports d 
		ON c.DepAirportID = d.AirportID
	LEFT JOIN Airports e 
		ON c.ArrAirportID = e.AirportID
	WHERE d.AirportID = 4 OR e.AirportID = 4;

DELETE s FROM Services s 
	LEFT JOIN Products b 
		ON s.TicketID = b.TicketID
	LEFT JOIN Tickets c 
		ON b.TicketID = c.TicketID
	LEFT JOIN Airports d 
		ON c.DepAirportID = d.AirportID
	LEFT JOIN Airports e 
		ON c.ArrAirportID = e.AirportID
	WHERE d.AirportID = 4 OR e.AirportID = 4;

DELETE b FROM Products b
	LEFT JOIN Tickets c 
		ON b.TicketID = c.TicketID
	LEFT JOIN Airports d 
		ON c.DepAirportID = d.AirportID
	LEFT JOIN Airports e 
		ON c.ArrAirportID = e.AirportID
	WHERE d.AirportID = 4 OR e.AirportID = 4;

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









