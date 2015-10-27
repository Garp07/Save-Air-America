-- Address table
DROP TABLE IF EXISTS Addresses;
CREATE TABLE Addresses
	(
	AddressID INT(11) NOT NULL AUTO INCREMENT,
	Street VARCHAR(30) NOT NULL,
	State VARCHAR(30) NOT NULL,
	Zipcode VARCHAR(30) NOT NULL,
	Country VARCHAR(30) NOT NULL,
	PRIMARY KEY (AddressID)
	)

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

-- Customer table
DROP TABLE IF EXISTS Customers;
CREATE TABLE Customers
	(
	CustomerID INT(11) NOT NULL AUTO INCREMENT,
	CustomerType VARCAHR(1) NOT NULL,
	PrimaryContactID INT(11) NOT NULL,
	CustomerName VARCHAR(30) NOT NULL,
	AirlineMiles FLOAT(2) NOT NULL DEFAULT 0,
	PRIMARY KEY (CustomerID),
	FOREIGN KEY (PrimaryContactID) REFERENCES Persons(PersonID)
	)

-- Airport table
DROP TABLE IF EXISTS Airports;
CREATE TABLE Airports
	(
	AirportID INT(11) NOT NULL AUTO INCREMENT,
	AirportCode VARCHAR(3) NOT NULL,
	AirportName VARCHAR(30) NOT NULL,
	AddressID INT(11) NOT NULL,
	LatitudeDegrees INT(11) NOT NULL,
	LatitudeMinutes INT(11) NOT NULL,
	LongitudeDegrees INT(11) NOT NULL,
	LongitudeDegrees INT(11) NOT NULL,
	FacilityFee FLOAT(2) NOT NULL DEFAULT 0,
	PRIMARY KEY (AirportID),
	FOREIGN KEY (AddressID) REFERENCES Addresses(AddressID)
	)

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
	PRIMARY KEY (ServiceID),
	FOREIGN KEY (TicketID) REFERENCES Tickets(TicketID),
	FOREIGN KEY (PersonID) REFERENCES Persons(PersonID)
	)
	 