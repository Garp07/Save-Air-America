-- Address table
DROP TABLE IF EXISTS Addresses;
CREATE TABLE Addresses
	(
	AddressID INT(11) NOT NULL AUTO INCREMENT,
	Street VARCHAR(30) NOT NULL,
	City VARCHAR(30) NOT NULL,
	State VARCHAR(30) NOT NULL,
	Zipcode VARCHAR(30) NOT NULL,
	Country VARCHAR(30) NOT NULL,
	PRIMARY KEY (AddressID)
	)

-- Populate Address table
INSERT INTO Addresses(Street, City, State, Zipcode, Country)
VALUES ('123 Mulberry Lane', 'Boston', 'MA', '42367', 'USA'), ('42 Bryce Ave', 'Albany', 'NY', '11452', 'USA'), ('11456 Hillsborough St', 'Omaha', 'NE', '68116', 'USA'), ('1 Pinewood Blvd', 'Salem', 'OR', '32132', 'USA'), ('908 Versaw Run', 'Santa Barbara', 'CA', '90233', 'USA'), ('31 Calle Morena', 'Cancun', 'Quintana Roo', '342', 'Mexico');


-- Person table
DROP TABLE IF EXISTS Persons;
CREATE TABLE Persons
	(
	PersonID INT(11) NOT NULL AUTO INCREMENT,
	PersonCode VARCHAR(30) NOT NULL,
	FirstName VARCHAR(30) NOT NULL,
	LastName VARCHAR(30) NOT NULL,
	AddressID INT(11) NOT NULL,
	PhoneNumber VARCHAR(30),
	PRIMARY KEY (PersonID),
	FOREIGN KEY (AddressID) REFERENCES Addresses(AddressID)
	)

-- Populate Person table
INSERT INTO Persons(PersonCode, FirstName, LastName, AddressID, PhoneNumber)
VALUES ('2ad', 'Rebecca', 'Black', (SELECT AddressID FROM Addresses WHERE Street = '123 Mulberry Lane'), '622-903-1332'), ('34b', 'Jon', 'Snow', (SELECT AddressID FROM Addresses WHERE Street = '31 Calle Morena'), '+52-122-984-1120'), ('ap2', 'Harry', 'Potter', (SELECT AddressID FROM Addresses WHERE Street = '123 Mulberry Lane'), '622-334-9080'), ('l34', 'Peter', 'Parker', (SELECT AddressID FROM Addresses WHERE Street = '42 Bryce Ave'), '908-386-0089'), ('554', 'Scarlett', 'Johannson', (SELECT AddressID FROM Addresses WHERE Street = '908 Versaw Run'), '618-442-3489');


-- Email table
DROP TABLE IF EXISTS Emails;
CREATE TABLE Emails
	(
	EmailID INT(11) NOT NULL AUTO INCREMENT,
	Email VARCHAR(30) NOT NULL,
	PersonID INT(11) NOT NULL,
	PRIMARY KEY (EmailID),
	FOREIGN KEY (PersonID) REFERENCES Persons(PersonID)
	)

-- Populate Email table
INSERT INTO Emails(Email, PersonID)
VALUES ('black.friday98@hotmail.com', (SELECT PersonID FROM Persons WHERE PersonCode = '2ad')), ('parents.money98@gmail.com', (SELECT PersonID FROM Persons WHERE PersonCode = '2ad')), ('theNorthRemembers@thrones.org', (SELECT PersonID FROM Persons WHERE PersonCode = '34b')), ('mugglelover97@hogwarts.eu', (SELECT PersonID FROM Persons WHERE PersonCode = 'ap2')), ('blackwidow.baby@yahoo.com', (SELECT PersonID FROM Persons WHERE PersonID = '554'));


-- Customer table
DROP TABLE IF EXISTS Customers;
CREATE TABLE Customers
	(
	CustomerID INT(11) NOT NULL AUTO INCREMENT,
	CustomerType VARCAHR(1) NOT NULL,
	PrimaryContactID INT(11) NOT NULL,
	CustomerName VARCHAR(50) NOT NULL,
	AirlineMiles FLOAT(2) NOT NULL DEFAULT 0,
	PRIMARY KEY (CustomerID),
	FOREIGN KEY (PrimaryContactID) REFERENCES Persons(PersonID)
	)

--Populate Customer table
INSERT INTO Customers(CustomerType, PrimaryContactID, CustomerName, AirlineMiles)
VALUES ('C', (SELECT PersonID FROM Persons WHERE PersonCode = '2ad'), 'Black Studios', 4.23), ('G', (SELECT PersonID FROM Persons WHERE PersonCode = '2ad'), 'Friday Night Inc.', 4221.02), ('V', (SELECT PersonID FROM Persons WHERE PersonCode = '34b'), 'House of Stark Industries', 0.00), ('G', (SELECT PersonID FROM Persons WHERE PersonCode = 'ap2'), 'Hogwarts School of Witch Craft and Wizardry', 777.33);


-- Airport table
DROP TABLE IF EXISTS Airports;
CREATE TABLE Airports
	(
	AirportID INT(11) NOT NULL AUTO INCREMENT,
	AirportCode VARCHAR(3) NOT NULL,
	AirportName VARCHAR(50) NOT NULL,
	AddressID INT(11) NOT NULL,
	LatitudeDegrees INT(11) NOT NULL,
	LatitudeMinutes INT(11) NOT NULL,
	LongitudeDegrees INT(11) NOT NULL,
	LongitudeMinutes INT(11) NOT NULL,
	FacilityFee FLOAT(2) NOT NULL DEFAULT 0,
	PRIMARY KEY (AirportID),
	FOREIGN KEY (AddressID) REFERENCES Addresses(AddressID)
	)

--Populate Airport table
INSERT INTO Airports(AirportCode, AirportName, AddressID, LatitudeDegrees, LatitudeMinutes, LongitudeDegrees, LongitudeMinutes, FacilityFee)
VALUES ('JJR', 'Jakey Junior''s Runways', (SELECT AddressID FROM Addresses WHERE Street = '123 Mulberry Lane'), 52, 25, 30, 45, 1.50), ('KPX', 'Kimborough Platform Airport', (SELECT AddressID FROM Addresses WHERE Street = '1 Pinewood Blvd'), 15, 50, 43, 22, 3.00), ('WDF', 'Wheatly Daniels Fields', (SELECT AddressID FROM Addresses WHERE Street = '11456 Hillsborough St'), 32, 11, 40, 54, 2.50), ('HOX', 'Harrington Offset Airport', (SELECT AddressID FROM Addresses WHERE Street = '42 Bryce Ave'), 45, 16, 48, 32, 4.50);


-- Invoice table
DROP TABLE IF EXISTS Invoices;
CREATE TABLE Invoices
	(
	InvoiceID INT(11) NOT NULL AUTO INCREMENT,
	InvoiceCode VARCHAR(10) NOT NULL,
	CustomerID INT(11) NOT NULL,
	SalespersonID INT(11) NOT NULL,
	InvoiceDate DATE NOT NULL,
	Pnr VARCHAR(10) NOT NULL,
	PRIMARY KEY (InvoiceID),
	FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
	FOREIGN KEY (SalespersonID) REFERENCES Persons(PersonID)
	)

-- Populate Invoice table
INSERT INTO Invoices(InvoiceCode, CustomerID, SalespersonID, InvoiceDate, Pnr)
VALUES ('12d34', (SELECT CustomerID FROM Customers WHERE CustomerName = 'Friday Night Inc.'), (SELECT PersonID FROM Persons WHERE PersonCode = '554'), 2015-04-12, '14cp'), ('dd34', (SELECT CustomerID FROM Customers WHERE CustomerName = 'Black Studios'), (SELECT PersonID FROM Persons WHERE PersonCode = '554'), 2015-10-02, 'hf86'), ('838hn', (SELECT CustomerID FROM Customers WHERE CustomerName = 'House of Stark Industries'), (SELECT PersonID FROM Persons WHERE PersonCode = '554'), 2015-03-10, 'g6d8'), ('99fd', (SELECT CustomerID FROM Customers WHERE CustomerName = 'Hogwarts School of Witch Craft and Wizardry'), (SELECT PersonID FROM Persons WHERE PersonCode = 'l34'), 2015-09-09, 'd94h');


-- Ticket table
DROP TABLE IF EXISTS Tickets;
CREATE TABLE Tickets
	(
	TicketID INT(11) NOT NULL AUTO INCREMENT,
	ProductCode VARCHAR(30) NOT NULL,
	ProductType VARCHAR(2) NOT NULL,
	DepAirportID INT(11) NOT NULL,
	ArrAirportID INT(11) NOT NULL,
	DepTime VARCHAR(10) NOT NULL,
	ArrTime VARCHAR(10) NOT NULL,
	FlightNumber VARCHAR(10) NOT NULL,
	FlightClass VARCHAR(2) NOT NULL DEFAULT 'EC',
	AircraftType VARCHAR(30) NOT NULL,
	TravelDate DATE NOT NULL,
	TicketNote VARCHAR(50),
	SeasonStartDate DATE,
	SeasonEndDate DATE,
	Rebate FLOAT(2),
	PointsPerMile FLOAT(2),
	InvoiceID INT(11) NOT NULL,
	PRIMARY KEY (TicketID),
	FOREIGN KEY (DepAirportID) REFERENCES Airports(AirportID),
	FOREIGN KEY (ArrAirportID) REFERENCES Airports(AirportID),
	FOREIGN KEY (InvoiceID) REFERENCES Invoices(InvoiceID)
	)

-- Populate Ticket table
INSERT INTO Tickets(ProductCode, ProductType, DepAirportID, ArrAirportID, DepTime, ArrTime, FlightNumber, FlightClass, AircraftType, TravelDate, TicketNote, SeasonStartDate, SeasonEndDate, Rebate, PointsPerMile, InvoiceID)
VALUES ('1d23', 'TS', (SELECT AirportID FROM Airports WHERE AirportCode = 'JJR'), (SELECT AirportID FROM Airports WHERE AirportCode = 'HOX'), '09:20', '15:30', 'A4', 'EP', 'Boeing 747', 2015-03-12, NULL, NULL, NULL, NULL, NULL, (SELECT InvoiceID FROM Invoices WHERE InvoiceCode = '12d34')), ('3343', 'TA', (SELECT AirportID FROM Airports WHERE AirportCode = 'JJR'), (SELECT AirportID FROM Airports WHERE AirportCode = 'WDF'), '09:45', '17:00', 'B77', 'EC', 'B2-Bomber', 2015-05-17, 'Jonnys first plane ride!', NULL, NULL, NULL, 50.25, (SELECT InvoiceID FROM Invoices WHERE InvoiceCode = '12d34')), ('808d', 'TS', (SELECT AirportID FROM Airports WHERE AirportCode = 'HOX'), (SELECT AirportID FROM Airports WHERE AirportCode = 'JJR'), '10:15', '19:30', 'A7C2', 'EP', 'Carrier', 2015-02-22, 'Afraid of heights; give aisle seat', NULL, NULL, NULL, NULL, (SELECT InvoiceID FROM Invoices WHERE InvoiceCode = 'dd34')), ('993f', 'T0', (SELECT AirportID FROM Airports WHERE AirportCode = 'KPX'), (SELECT AirportID FROM Airports WHERE AirportCode = 'JJR'), '13:30', '20:20', 'D29', 'EC', 'Passenger', 2015-06-22, NULL, 2015-05-01, 2015-10-01, 500.00, NULL, (SELECT InvoiceID FROM Invoices WHERE InvoiceCode = '838hn')), ('mg90', 'TS', (SELECT AirportID FROM Airports WHERE AirportCode = 'WDF'), (SELECT AirportID FROM Airports WHERE AirportCode = 'KPX'), '08:10', '15:45', 'B4F7', 'EP', 'Boeing 747', 2015-07-25, NULL, NULL, NULL, NULL, NULL, (SELECT InvoiceID FROM Invoices WHERE InvoiceCode = '99fd'));


-- Seat table
DROP TABLE IF EXISTS Seats;
CREATE TABLE Seats
	(
	SeatID INT(11) NOT NULL AUTO INCREMENT,
	SeatNumber VARCHAR(3) NOT NULL,
	PersonID INT(11) NOT NULL,
	IDNumber VARCHAR(30) NOT NULL,
	Age INT(3) NOT NULL,
	Nationality VARCHAR(30) NOT NULL,
	TicketID INT(11) NOT NULL,
	PRIMARY KEY (SeatID),
	FOREIGN KEY (PersonID) REFERENCES Persons(PersonID),
	FOREIGN KEY (TicketID) REFERENCES Tickets(TicketID)
	)

-- Populate Seat table
INSERT INTO Seats(SeatNumber, PersonID, IDNumber, Age, Nationality, TicketID)
VALUES ('B22', (SELECT PersonID FROM Persons WHERE PersonCode = '2ad'), '554g643', 25, 'American', (SELECT TicketID FROM Tickets WHERE ProductCode = '1d23')), ('E19', (SELECT PersonID FROM Persons WHERE PersonCode = '2ad'), '99889', 23, 'American', (SELECT TicketID FROM Tickets WHERE ProductCode = '1d23')), ('C02', (SELECT PersonID FROM Persons WHERE PersonCode = '34b'), 'sj223j1', 30, 'British', (SELECT TicketID FROM Tickets WHERE ProductCode = '3343')), ('D12', (SELECT PersonID FROM Persons WHERE PersonCode = 'ap2'), '66h7y', 20, 'Canadian', (SELECT TicketID FROM Tickets WHERE ProductCode = '808d')), ('H07', (SELECT PersonID FROM Persons WHERE PersonCode = 'ap2'), '878h', 18, 'American', (SELECT TicketID FROM Tickets WHERE ProductCode = '993f')), ('J10', (SELECT PersonID FROM Persons WHERE PersonCode = '2ad'), 'nno998', 54, 'Jamaican', (SELECT TicketID FROM Tickets WHERE ProductCode = 'mg90'));


-- Service table
DROP TABLE IF EXISTS Services;
CREATE TABLE Services
	(
	ServiceID INT(11) NOT NULL AUTO INCREMENT,
	ProductCode VARCHAR(30) NOT NULL,
	ProductType VARCHAR(2) NOT NULL,
	Quantity INT(11) NOT NULL,
	TicketID INT(11),
	InsuranceName VARCHAR(30),
	AgeClass VARCHAR(30),
	CostPerMile FLOAT(2),
	NameOfService VARCHAR(30),
	PersonID INT(11),
	Cost FLOAT(2),
	InvoiceID INT(11) NOT NULL,
	PRIMARY KEY (ServiceID),
	FOREIGN KEY (TicketID) REFERENCES Tickets(TicketID),
	FOREIGN KEY (PersonID) REFERENCES Persons(PersonID),
	FOREIGN KEY (InvoiceID) REFERENCES Invoices(InvoiceID)
	)

-- Populate Service table
INSERT INTO Services(ProductCode, ProductType, Quantity, TicketID, InsuranceName, AgeClass, CostPerMile, NameOfService, PersonID, Cost, InvoiceID)
VALUES ('1ow3', 'SR', 2, NULL, NULL, NULL, NULL, 'Dr. Pepper Ten', NULL, 1.50, (SELECT InvoiceID FROM Invoices WHERE InvoiceCode = '12d34')), ('55k', 'SS', 1, NULL, NULL, NULL, NULL, 'Basketball hoop', NULL, NULL, (SELECT InvoiceID FROM Invoices WHERE InvoiceCode = 'mg90')), ('94r4', 'SI', 1, (SELECT TicketID FROM Tickets WHERE ProductCode = '993f'), 'Sleep Easy Insurance', '0-30', 3.25, NULL, NULL, 1.50, (SELECT InvoiceID FROM Invoices WHERE InvoiceCode = '12d34')), ('p90x', 'SC', 3, (SELECT TicketID FROM Tickets WHERE ProductCode = '1d23'), NULL, NULL, NULL, NULL, NULL, NULL, (SELECT InvoiceID FROM Invoices WHERE InvoiceCode = '838hn'));
