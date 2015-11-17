
-- Address table
DROP TABLE IF EXISTS Addresses;
CREATE TABLE Addresses 
(
	AddressID INT(11) NOT NULL AUTO_INCREMENT,
	Street VARCHAR(30) NOT NULL,
	City VARCHAR(30) NOT NULL,
	State VARCHAR(30) NOT NULL,
	Zipcode VARCHAR(30) NOT NULL,
	Country VARCHAR(30) NOT NULL,
	CONSTRAINT Addresses_PK PRIMARY KEY (AddressID)
);

-- Populate Address table
INSERT INTO Addresses(Street, City, State, Zipcode, Country)
VALUES 
	('123 Mulberry Lane', 'Boston', 'MA', '42367', 'USA'), 
    ('42 Bryce Ave', 'Albany', 'NY', '11452', 'USA'), 
    ('11456 Hillsborough St', 'Omaha', 'NE', '68116', 'USA'), 
    ('1 Pinewood Blvd', 'Salem', 'OR', '32132', 'USA'), 
    ('908 Versaw Run', 'Santa Barbara', 'CA', '90233', 'USA'), 
    ('31 Calle Morena', 'Cancun', 'Quintana Roo', '342', 'Mexico'), 
    ('7 Church Street North', 'Montclair', 'NJ', '07042', 'USA'), 
    ('422 Cypress Court', 'Charlotte', 'NC', '28205', 'USA'), 
    ('208 Devon Road', 'Groton', 'CT', '06340', 'USA'), 
    ('615 6th Avenue', 'North Fort Myers', 'FL', '33917', 'USA'), 
    ('777 Elizabeth Street', 'Hinesville', 'GA', '31313', 'USA'), 
    ('467 East Street', 'New Baltimore', 'MI', '48047', 'USA'),
    ('260 Cedar Avenue', 'Adrian', 'MI', '49221', 'USA'), 
    ('428 Magnolia Drive', 'Fitchburg', 'MA', '01420', 'USA'),
    ('446 Highland Avenue', 'Flemington', 'NJ', '08822', 'USA'), 
    ('277 Devon Road', 'Moses Lake', 'WA', '98837', 'USA'), 
    ('700 Myrtle Avenue', 'Myrtle Beach', 'SC', '29577', 'USA'),
    ('546 Madison Avenue', 'Clarksville', 'TN', '37040', 'USA'),
    ('60 Durham Court', 'Trenton', 'NJ', '08610', 'USA'), 
    ('505 Walnut Avenue', 'Southington', 'CT', '06489', 'USA'),
    ('473 Lake Street', 'South Bend', 'IN', '46614', 'USA'),
    ('454 Park Avenue', 'Schenectady', 'NY', '12302', 'USA'), 
    ('730 Lexington Drive', 'Palm Bay', 'FL', '32907', 'USA'),
    ('145 Valley Road', 'Clinton', 'MD', '20735', 'USA'),
    ('672 Maiden Lane', 'Elizabeth City', 'NC', '27909', 'USA'),
    ('265 Main Street', 'New Castle', 'PA', '16101', 'USA'),
    ('194 Route 6', 'Mc Lean', 'VA', '22101', 'USA'), 
    ('491 Euclid Avenue', 'Lanham', 'MD', '20706', 'USA'),
    ('857 Grove Avenue', 'Winter Park', 'FL', '32792', 'USA'),
    ('447 Taylor Street', 'Piedmont', 'SC', '29673', 'USA'),
    ('806 Carriage Drive', 'Mountain View', 'CA', '94043', 'USA'),
    ('98 Williams Street', 'Starkville', 'MS', '39759', 'USA'),
    ('314 Delaware Avenue', 'Hanover', 'PA', '17331', 'USA'), 
    ('58 North Street', 'Jamaica Plain', 'MA', '02130', 'USA'), 
    ('946 6th Avenue', 'Shrewsbury', 'MA', '01545', 'USA'), 
    ('879 Valley Drive', 'Gloucester', 'MA', '01930', 'USA'),
    ('433 Route 10', 'Crawfordsville', 'IN', '47933', 'USA'), 
    ('282 Pin Oak Drive', 'Peachtree City', 'GA', '30269', 'USA'),
    ('9 Cooper Street', 'Gibsonia', 'PA', '15044', 'USA'), 
    ('619 Washington Avenue', 'Mount Holly', 'NJ', '08060', 'USA'), 
    ('453 Aspen Court', 'Dayton', 'OH', '45420', 'USA'),
    ('10 Cottage Street', 'Westfield', 'MA', '01085', 'USA'), 
    ('845 Cottage Street', 'Glendale Heights', 'IL', '60139', 'USA'), 
    ('248 Hawthorne Lane', 'North Royalton', 'OH', '44133', 'USA'), 
    ('116 Arch Street', 'Perkasie', 'PA', '18944', 'USA'), 
    ('273 Elm Street', 'Fishers', 'IN', '46037', 'USA'),
    ('154 Liberty Street', 'Orchard Park', 'NY', '14127', 'USA'),
    ('790 Washington Avenue', 'Malvern', 'PA', '19355', 'USA'),
    ('93 Hillside Avenue', 'Ambler', 'PA', '19002', 'USA'), 
    ('971 Jefferson Court', 'Ontario', 'CA', '91762', 'USA'), 
    ('724 Willow Avenue', 'Portland', 'ME', '04103', 'USA'), 
    ('144 Edgewood Road', 'Wooster', 'OH', '44691', 'USA'), 
    ('644 Woodland Drive', 'Cookeville', 'TN', '38501', 'USA'),
    ('3 Clay Street', 'Fairport', 'NY', '14450', 'USA'), 
    ('275 Briarwood Court', 'Neptune', 'NJ', '07753', 'USA'),
    ('311 3rd Avenue', 'Anchorage', 'AK', '99504', 'USA'),
    ('ONLINE','ONLINE','ON','ON','ON');

-- Person table
DROP TABLE IF EXISTS Persons;
CREATE TABLE Persons 
(
	PersonID INT(11) NOT NULL AUTO_INCREMENT,
	PersonCode VARCHAR(30) NOT NULL UNIQUE,
	FirstName VARCHAR(30) NOT NULL,
	LastName VARCHAR(30) NOT NULL,
	AddressID INT(11),					-- online sales won't have an address
	PhoneNumber VARCHAR(30),
	CONSTRAINT Persons_PK PRIMARY KEY (PersonID),
	FOREIGN KEY (AddressID) REFERENCES Addresses(AddressID)
);

-- Populate Person table
INSERT INTO Persons(PersonCode, FirstName, LastName, AddressID, PhoneNumber)
VALUES 
	('000', 'ONLINE', 'ONLINE', NULL, NULL),
    ('2ad', 'Rebecca', 'Black', 37, NULL),
    ('34b', 'Jon', 'Snow', 38, NULL), 
    ('ap2', 'Harry', 'Potter', 39, NULL),
    ('l34', 'Peter', 'Parker', 40, '908-386-0089'), 
    ('554', 'Scarlett', 'Johannson', 41, '618-442-3489'),
    ('jCv', 'Edwardo', 'Minich', 1, '311-182-8787'),
    ('mKE', 'Miles', 'Leggio', 2, '903-241-2973'), 
    ('q6d', 'Fay', 'Wardell', 3, '692-316-5658'),
    ('DTR', 'Oliva', 'Don', 4, '636-345-2739'), 
    ('Rm3', 'Lorean', 'Casados', 5, '388-465-5592'), 
    ('IYf', 'Ronnie', 'Chamness', 6, '825-480-8119'), 
    ('dyc', 'Lonna', 'Ziebarth', 7, '927-603-9676'),
    ('CPH', 'Kasey', 'Rapoza', 8, '317-863-5589'), 
    ('ZBD', 'Hong', 'Sorkin', 9, '927-721-4268'), 
    ('MZr', 'Noelle', 'Baumgarten', 10, '388-822-1045'),
    ('u86', 'Larissa', 'Croskey', 11, '704-699-7645'),
    ('1Zt', 'Adrianne', 'Zufelt', 12, '280-791-5795'), 
    ('tgu', 'Sona', 'Iliff', 13, '435-633-7037'),
    ('gcH', 'Sharla', 'Goertz', 14, '338-294-3688'),
    ('LI5', 'Seth', 'Hicklin', 15, '617-890-5897'),
    ('eUA', 'Rubye', 'Godsey', 16, '265-804-8620'), 
    ('xAB', 'Garret', 'Enfinger', 17, '655-153-4907'), 
    ('bl4', 'Hubert', 'Riehl', 18, '150-437-1160'), 
    ('Gj5', 'Domenic', 'Budzinski', 19, '870-776-8248'),
    ('jdu', 'Jazmine', 'Nolting', 20, '887-661-2897'),
    ('3dp', 'Qiana', 'Mehl', 21, '463-472-4782'),
    ('dB7', 'Jillian', 'Brenes', 22, '528-212-5045'),
    ('dvp', 'Shantel', 'Hageman', 23, '686-187-5792'), 
    ('UZf', 'Javier', 'Arms', 24, NULL),
    ('5Oq', 'Caitlyn', 'Lickteig', 25, '707-544-9361'), 
    ('pow', 'Stacy', 'Salvador', 26, '686-438-4605'), 
    ('t6m', 'Jacquie', 'Conkling', 27, '109-389-7173'), 
    ('AoG', 'Demarcus', 'Brunk', 28, '664-541-7570'),
    ('h8P', 'Cyndi', 'Einhorn', 29, '747-841-5531'), 
    ('inN', 'Sharri', 'Rotella', 30, '127-248-5480'),
    ('V8x', 'Devin', 'Cosentino', 31, '765-281-7455'),
    ('CWD', 'Sabra', 'Stpierre', 32, '279-926-4277'), 
    ('Rw3', 'Adela', 'Holyfield', 33, '912-318-8757'), 
    ('wKN', 'Felisa', 'Auld', 34, '393-488-9076'),
    ('wDb', 'Audie', 'Rouse', 35, '715-196-5793'), 
    ('86y', 'Mariann', 'Capers', 36, NULL);


-- Email table
DROP TABLE IF EXISTS Emails;
CREATE TABLE Emails 
(
	EmailID INT(11) NOT NULL AUTO_INCREMENT,
	Email VARCHAR(50) NOT NULL,
	PersonID INT(11) NOT NULL,
	CONSTRAINT Emails_PK PRIMARY KEY (EmailID),
	FOREIGN KEY (PersonID) REFERENCES Persons(PersonID)
);

-- Populate Email table
INSERT INTO Emails(Email, PersonID)
VALUES 
	('black.friday98@hotmail.com', 3), 
    ('parents.money98@gmail.com', 4),
    ('theNorthRemembers@thrones.org', 5),
    ('mugglelover97@hogwarts.eu', 5), 
    ('blackwidow.baby@yahoo.com', 6);

-- Customer table
DROP TABLE IF EXISTS Customers;
CREATE TABLE Customers 
(
	CustomerID INT(11) NOT NULL AUTO_INCREMENT,
    CustomerCode VARCHAR(30) NOT NULL UNIQUE,
	CustomerType VARCHAR(1) NOT NULL,
	PrimaryContactID INT(11) NOT NULL,
	CustomerName VARCHAR(50) NOT NULL,
	AirlineMiles FLOAT(25) NOT NULL DEFAULT 0,
	CONSTRAINT Customers_PK PRIMARY KEY (CustomerID),
	FOREIGN KEY (PrimaryContactID) REFERENCES Persons(PersonID)
);

-- Populate Customer table
INSERT INTO Customers(CustomerCode, CustomerType, PrimaryContactID, CustomerName, AirlineMiles)
VALUES 
	('aeg8', 'C', 8, 'Black Studios', 4.23),
    ('eoij', 'G', 12, 'Friday Night Inc.', 4221.02), 
    ('qfe6', 'V', 15, 'House of Stark Industries', 0.00),
    ('qef8', 'G', 22, 'Hogwarts School of Witch Craft and Wizardry', 777.33);

-- Airport table
DROP TABLE IF EXISTS Airports;
CREATE TABLE Airports
(
	AirportID INT(11) NOT NULL AUTO_INCREMENT,
	AirportCode VARCHAR(3) NOT NULL,
	AirportName VARCHAR(50) NOT NULL,
	AddressID INT(11) NOT NULL,
	LatitudeDegrees INT(11) NOT NULL,
	LatitudeMinutes INT(11) NOT NULL,
	LongitudeDegrees INT(11) NOT NULL,
	LongitudeMinutes INT(11) NOT NULL,
	FacilityFee FLOAT(10) NOT NULL DEFAULT 0,
	CONSTRAINT Airports_PK PRIMARY KEY (AirportID),
	FOREIGN KEY (AddressID) REFERENCES Addresses(AddressID)
);

-- Populate Airport table
INSERT INTO Airports(AirportCode, AirportName, AddressID, LatitudeDegrees, LatitudeMinutes, LongitudeDegrees, LongitudeMinutes, FacilityFee)
VALUES 
	('JJR', 'Jakey Junior''s Runways', 43, 52, 25, 30, 45, 1.50), 
    ('KPX', 'Kimborough Platform Airport', 44, 15, 50, 43, 22, 3.00),
    ('WDF', 'Wheatly Daniels Fields', 45, 32, 11, 40, 54, 2.50), 
    ('HOX', 'Harrington Offset Airport', 46, 45, 16, 48, 32, 4.50),
    ('ROE', 'Dallas Fields', 47, 35, 32, 1, 17, 6.82),
    ('JNL', 'Austin International', 48, 27, 1, 29, 5, 2.64),
    ('YNX', 'Lincoln Grassy Patch', 49, 27, 47, 25, 41, 9.89),
    ('SGN', 'Omaha Airway', 50, 55, 29, 29, 16, 4.6),
    ('ZAG', 'New York Airport', 51, 51, 2, 18, 55, 1.25),
    ('LXJ', 'Cleveland International', 52, 58, 54, 35, 22, 7.05),
    ('CFV', 'Bermuda Runways', 53, 53, 48, 45, 7, 1.15),
    ('LIR', 'Raleigh Runways', 54, 39, 26, 34, 36, 8.11),
    ('RKG', 'Bozeman Runways', 55, 44, 28, 8, 19, 9.94),
    ('BQH', 'Fremont Grassy Patch', 56, 0, 27, 36, 40, 7.84);

-- Invoice table
DROP TABLE IF EXISTS Invoices;
CREATE TABLE Invoices 
(
	InvoiceID INT(11) NOT NULL AUTO_INCREMENT,
	InvoiceCode VARCHAR(30) NOT NULL,
	CustomerID INT(11) NOT NULL,
	SalespersonID INT(11), -- '000' is the person code if online
	InvoiceDate VARCHAR(12) NOT NULL,
	PNR VARCHAR(10) NOT NULL,
	CONSTRAINT Invoices_PK PRIMARY KEY (InvoiceID),
	FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
	FOREIGN KEY (SalespersonID) REFERENCES Persons(PersonID)
);

-- Populate Invoice table
INSERT INTO Invoices(InvoiceCode, CustomerID, SalespersonID, InvoiceDate, PNR)
VALUES
	('INV001', 1, 12, '2015-04-12', '14cp'),
    ('INV002', 2, 14, '2015-10-02', 'hf86'), 
    ('INV003', 1, 11, '2015-03-10', 'g6d8'),
    ('INV004', 2, 5, '2015-09-09', 'd94h'),
    ('INV005', 4, 1, '2015-04-19', 'xerr');
    
-- Ticket table
DROP TABLE IF EXISTS Tickets;
CREATE TABLE Tickets
(
	TicketID INT(11) NOT NULL AUTO_INCREMENT,
	DepAirportID INT(11) NOT NULL,
	ArrAirportID INT(11) NOT NULL,
	DepTime VARCHAR(10) NOT NULL,
	ArrTime VARCHAR(10) NOT NULL,
	FlightNumber VARCHAR(10) NOT NULL,
	FlightClass VARCHAR(2) NOT NULL DEFAULT 'EC',
	AircraftType VARCHAR(30) NOT NULL,
	SeasonStartDate VARCHAR(12),
	SeasonEndDate VARCHAR(12),
	Rebate FLOAT(10),
	PointsPerMile FLOAT(10),
	CONSTRAINT Tickets_PK PRIMARY KEY (TicketID),
	FOREIGN KEY (DepAirportID) REFERENCES Airports(AirportID),
	FOREIGN KEY (ArrAirportID) REFERENCES Airports(AirportID)
);

-- Populate Ticket table
INSERT INTO Tickets(DepAirportID, ArrAirportID, DepTime, ArrTime, FlightNumber, FlightClass, AircraftType, SeasonStartDate, SeasonEndDate, Rebate, PointsPerMile)
VALUES
	(1, 2, '09:20', '15:30', 'A4', 'EP', 'Boeing 747', NULL, NULL, NULL, NULL),
    (3, 4, '09:45', '17:00', 'B77', 'EC', 'B2-Bomber', NULL, NULL, NULL, 50.25),
    (5, 14, '10:15', '19:30', 'A7C2', 'EP', 'Carrier', NULL, NULL, NULL, NULL),
    (12, 13, '13:30', '20:20', 'D29', 'EC', 'Passenger', '2015-05-01', '2015-10-01', 500, NULL),
    (10, 7, '08:10', '15:45', 'B4F7', 'EP', 'Boeing 747', NULL, NULL, NULL, NULL);

-- Service table
DROP TABLE IF EXISTS Services;
CREATE TABLE Services
(
	ServiceID INT(11) NOT NULL AUTO_INCREMENT, -- CheckedBaggage items will have null for all fields, but are still listed here because they are tied in with the Products table
	InsuranceName VARCHAR(30),
	AgeClass VARCHAR(30), -- for insurance
	CostPerMile FLOAT(10), -- for insurance
    TypeOfService VARCHAR(255), -- for special assistance
	RefreshmentName VARCHAR(30), 
	Cost FLOAT(10), -- for drinks
	CONSTRAINT Services_PK PRIMARY KEY (ServiceID)
);

-- Populate Service table
INSERT INTO Services(InsuranceName, AgeClass, CostPerMile, TypeOfService, RefreshmentName, Cost)
VALUES
	('Sleep Easy Insurance', '0-30', 3.25, NULL, NULL, NULL),
	(NULL, NULL, NULL, NULL, NULL, NULL), 
	(NULL, NULL, NULL, NULL, 'Basketball Drink', 1.50), 
    (NULL, NULL, NULL, 'Back Massage', NULL, NULL), 
    ('Insurance Company', '25-60', 12, NULL, NULL, NULL),
    ('Healthy Company', '25-48', .2, NULL, NULL, NULL),
    ('Please Stay Alive', '36-38', 1, NULL, NULL, NULL);

-- Create Products Table
DROP TABLE IF EXISTS Products;
CREATE TABLE Products
(
	ProductID INT(11) NOT NULL AUTO_INCREMENT,
    ProductCode VARCHAR(30) NOT NULL,
    ProductType VARCHAR(2) NOT NULL,
    TicketID INT(11),
    ServiceID INT(11),
    CONSTRAINT Products_ID PRIMARY KEY (ProductID),
    FOREIGN KEY (TicketID) REFERENCES Tickets(TicketID),
    FOREIGN KEY (ServiceID) REFERENCES Services(ServiceID)
);

INSERT INTO Products(ProductCode, ProductType, TicketID)
VALUES
	('ff23', 'TS', 1),
	('1255', 'TA', 2),
	('90fa', 'TS', 3),
	('1238', 'TO', 4),
	('xer2', 'TS', 5);
    
INSERT INTO Products(ProductCode, ProductType, ServiceID)
VALUES
	('ff25', 'SI', 1),
	('32f4', 'SR', 3),
	('xer4', 'SS', 4),
	('ff24', 'SI', 5),
	('fg23', 'SI', 6),
	('fh23', 'SI', 7);
    
INSERT INTO Products(ProductCode, ProductType, ServiceID, TicketID)
VALUES
	('90fb', 'SC', 2, 2);

-- InvoiceProducts Table
DROP TABLE IF EXISTS InvoiceProducts;
CREATE TABLE InvoiceProducts
(
	InvoiceProductsID INT(11) NOT NULL AUTO_INCREMENT,
    InvoiceID INT(11) NOT NULL,
    ProductID INT(11) NOT NULL,
    TicketNote VARCHAR(255),
    Quantity INT(11), -- checked baggage, insurance, or refreshments
    InsuranceTicketID INT(11),
    SpecialAssistancePersonID INT(11),
    TravelDate VARCHAR(12) NOT NULL DEFAULT '0000-01-01',
    CONSTRAINT InvoiceProducts_PK PRIMARY KEY (InvoiceProductsID),
    FOREIGN KEY (InvoiceID) REFERENCES Invoices(InvoiceID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID),
    FOREIGN KEY (InsuranceTicketID) REFERENCES Tickets(TicketID),
    FOREIGN KEY (SpecialAssistancePersonID) REFERENCES Persons(PersonID)
);

INSERT INTO InvoiceProducts(InvoiceID, ProductID, TicketNote, Quantity, InsuranceTicketID, SpecialAssistancePersonID, TravelDate)
VALUES
	(1,1,NULL,NULL,NULL,NULL,'2015-05-06'),
    (1,2,NULL,NULL,NULL,NULL,'2014-04-29'),
    (1,3,NULL,NULL,NULL,NULL,'2015-12-15'),
    (3,1,NULL,NULL,NULL,NULL,'2013-12-13'),
    (2,6,NULL,2,5,NULL,NULL),
    (2,7,NULL,2,NULL,NULL,NULL),
    (2,8,NULL,1,NULL,2,NULL),
    (2,9,NULL,1,3,NULL,NULL);
    
    -- Seat table
DROP TABLE IF EXISTS Seats;
CREATE TABLE Seats
(
	SeatID INT(11) NOT NULL AUTO_INCREMENT,
	SeatNumber VARCHAR(3) NOT NULL,
	PersonID INT(11) NOT NULL,
	IDNumber VARCHAR(30) NOT NULL,
	Age INT(3) NOT NULL,
	Nationality VARCHAR(30) NOT NULL,
	InvoiceProductsID INT(11) NOT NULL,
	CONSTRAINT Seats_PK PRIMARY KEY (SeatID),
	FOREIGN KEY (PersonID) REFERENCES Persons(PersonID),
	FOREIGN KEY (InvoiceProductsID) REFERENCES InvoiceProducts(InvoiceProductsID)
);

-- Populate Seat table
INSERT INTO Seats(SeatNumber, PersonID, IDNumber, Age, Nationality, InvoiceProductsID)
VALUES 
	('B22', 5, '554g643', 25, 'American', 1), 
    ('E19', 8, '99889', 23, 'American', 1),
    ('C02', 9, 'sj223j1', 30, 'British', 1), 
    ('D12', 12, '66h7y', 20, 'Canadian', 2), 
    ('H07', 2, '878h', 18, 'American', 2),
    ('J10', 15, 'nno998', 54, 'Jamaican', 4);