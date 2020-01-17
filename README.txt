
Getting Started

If you want to load the project into an IDE, import the project as a maven project
into eclipse or IntelliJ or if you want to build the project follow the following steps.
 

Prerequisites

Maven
JAVA 8
MySQL setup

Installing

 mvn clean install - to build
mvn spring-boot:run - to run
Note: if you want to run the code after the build step, go into target directory and
run command java -jar StarRentalSystem-0.0.1-SNAPSHOT.jar
Install MAMP ( Or any MySQL database setup)
Follow SQL DB setup below:


Open MyPhpAdmin
Create a Database - car_rental
Run below SQL queries:


SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE TABLE `c_clients` (
  `licenseNumber` varchar(30) NOT NULL,
  `firstName` varchar(30) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `phoneNumber` bigint(15) DEFAULT NULL,
  `licenseExpiryDate` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



INSERT INTO `c_clients` (`licenseNumber`, `firstName`, `lastName`, `phoneNumber`, `licenseExpiryDate`) VALUES
('A-1234-123456-12', 'Client1', 'Client1', 1234567890, '2029-11-15'),
('B-4321-435536-99', 'Ava', 'Noa', 1234567890, '2029-11-15'),
('B-6224-123456-14', 'Sam', 'William', 1234567890, '2029-11-15');

ALTER TABLE `c_clients`
  ADD PRIMARY KEY (`licenseNumber`);


CREATE TABLE `c_transactions` (
  `transactionID` int(3) NOT NULL,
  `vehicleLicensePlate` varchar(7) NOT NULL,
  `clientLicenseNumber` varchar(16) NOT NULL,
  `status` varchar(16) DEFAULT NULL,
  `timeStamp` varchar(50) DEFAULT NULL,
  `bookingFrom` varchar(10) DEFAULT NULL,
  `transactionBy` varchar(10) DEFAULT NULL,
  `bookingTill` varchar(10) DEFAULT NULL,
  `transactionType` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `c_transactions`
  MODIFY `transactionID` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `c_transactions`
  ADD PRIMARY KEY (`transactionID`);



CREATE TABLE `c_users` (
  `userName` varchar(6) NOT NULL,
  `Password` varchar(10) DEFAULT NULL,
  `userType` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `c_users` (`userName`, `Password`, `userType`) VALUES
('Admin', 'admin', 'A'),
('Admin2', 'admin2', 'A'),
('Clerk', 'test', 'C'),
('Clerk2', 'test2', 'C');

ALTER TABLE `c_users`
  ADD PRIMARY KEY (`userName`);


CREATE TABLE `c_catalog` (
  `Type` varchar(20) DEFAULT NULL,
  `Make` varchar(20) DEFAULT NULL,
  `Model` varchar(15) DEFAULT NULL,
  `Year` int(4) DEFAULT NULL,
  `Color` varchar(11) DEFAULT NULL,
  `vehicleLicensePlate` varchar(7) NOT NULL,
  `Status` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `c_catalog` (`Type`, `Make`, `Model`, `Year`, `Color`, `vehicleLicensePlate`, `Status`) VALUES
('Saloon', 'Mercedes', 'E-Class', 1999, 'pink', 'ABC 121', 'Available'),
('Saloon', 'Hyundai', 'Veloster', 2001, 'Green', 'ABC 122', 'Available'),
('SUV', 'Honda', 'Accord', 2002, 'Black', 'ABC 123', 'Available'),
('TWD', 'Toyota', 'Corolla', 1988, 'babyblue', 'ABC 124', 'Available'),
('SUV', 'Honda', 'Accord', 2003, 'Blue', 'ABC 125', 'Available'),
('SUV', 'Honda', 'Pilot', 2001, 'Grey', 'ABC 126', 'Available'),
('TWD', 'Honda', 'Civic', 1999, 'White', 'ABC 127', 'Available'),
('Jeep', 'Mercedes', 'G-Wagon', 1999, 'White', 'ABC 128', 'Available'),
('Jeep', 'Mercedes', 'G-Wagon', 1999, 'indigo', 'ABC 129', 'Available'),
('Saloon', 'Hyundai', 'Sonata', 2001, 'Yellow', 'ABC 130', 'Available');

ALTER TABLE `c_catalog`
  ADD PRIMARY KEY (`vehicleLicensePlate`);

Built With

[JDK 8](https://openjdk.java.net/projects/jdk/11/) - language used
 [SprintBoot](https://spring.io/projects/spring-boot) - framework used for Java
MySQL - external DataBase



On successful launch of Application:

 Open -> http://localhost:8080/loginPage
Login as Admin: 
Username: Admin
Password: admin
Login as Clerk: 
Username: Clerk
Password: test
More credentials can be fetched or added to the ‘c_users’ table in the DataBase.



Support for concurrency and persistence

We have implemented UoW for Vehicle, Client, and Transactions.
Maintain the integrity of DB data and calls
Prevent multiple connection setups


The threshold in UoW is set to 3 i.e. for CUD(Create Update Delete) functions, DB will be updated only when 3 valid operations are made.

Also, in the case of vehicle UoW, there is an implemented timer for UoW, i.e. the timer starts once a valid CUD operation is performed. Once the timer crosses the threshold(10 sec) UoW commits to DB.  In case CUD operation threshold exceeds 3 before timer threshold, the commit is made.

Object registration in UoW sets a objects currently in UoW to be dirty.
This prevents the wrongful update of data.


We also set rightful checks to keep data persistency. 
A vehicle that is rented or a client that has a vehicle rented cannot be deleted, a message with information is displayed in system logs.

Patterns implemented to access data services layers
Table Data Gateway
Data Mapper

We have 4 model elements mapped to database tables. We have a TDG for each table:
VehicleTDG, ClientTDG, TransactionTDG, UserTDG. TDGs act as an intermediary between domain objects and the database.

Similarly, we have a DM corresponding to each TDG, which moves data between objects and the database while keeping them independent of each other. 

