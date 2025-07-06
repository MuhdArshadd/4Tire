-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 16, 2024 at 06:11 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `car_service`
--

-- --------------------------------------------------------

--
-- Table structure for table `car_service`
--

CREATE TABLE `car_service` (
  `SlotID` int(11) NOT NULL,
  `ServiceID` int(11) NOT NULL,
  `CustID` int(11) NOT NULL,
  `CarName` varchar(20) NOT NULL,
  `CarModel` varchar(20) NOT NULL,
  `CarType` varchar(20) NOT NULL,
  `DateOfAppointment` date DEFAULT NULL,
  `Status` varchar(20) NOT NULL DEFAULT 'PENDING',
  `Description` varchar(50) NOT NULL DEFAULT 'N/A',
  `StaffID` varchar(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `car_service`
--

INSERT INTO `car_service` (`SlotID`, `ServiceID`, `CustID`, `CarName`, `CarModel`, `CarType`, `DateOfAppointment`, `Status`, `Description`, `StaffID`) VALUES
(13, 2211, 1000, 'CR-V', 'HONDA', 'SUV', '2024-07-17', 'CONFIRMED', 'My tire seems to have a problem', NULL),
(14, 2217, 1000, 'CR-V', 'HONDA', 'SUV', '2024-07-16', 'ON GOING', 'Battery is dead', NULL),
(15, 2218, 1000, 'CR-V', 'HONDA', 'SUV', '2024-07-17', 'APPROVED', 'Car won\'t start despite the battery is full', NULL),
(16, 2210, 1000, 'CR-V', 'HONDA', 'SUV', '2024-02-05', 'ON GOING', '', NULL),
(17, 2210, 1001, 'BMW 3 Series', 'BMW', 'SEDAN', '2024-02-05', 'ON GOING', '', NULL),
(18, 2213, 1001, 'BMW 3 Series', 'BMW', 'SEDAN', NULL, 'PENDING', '', NULL),
(19, 2218, 1001, 'BMW 3 Series', 'BMW', 'SEDAN', '2024-07-17', 'APPROVED', '', NULL),
(20, 2221, 1001, 'BMW 3 Series', 'BMW', 'SEDAN', NULL, 'PENDING', '', NULL),
(21, 2210, 1003, 'AUDI R8', 'AUDI', 'SPORTS CAR', '2024-02-05', 'ON GOING', '', NULL),
(22, 2219, 1003, 'AUDI R8', 'AUDI', 'SPORTS CAR', '2024-02-05', 'ON GOING', '', NULL),
(23, 2220, 1003, 'AUDI R8', 'AUDI', 'SPORTS CAR', NULL, 'PENDING', '', NULL),
(24, 2215, 1003, 'AUDI R8', 'AUDI', 'SPORTS CAR', NULL, 'PENDING', '', NULL),
(25, 2217, 1004, 'MODEL 3 55.', 'TESLA', 'ELECTRIC', '2024-07-16', 'ON GOING', '', NULL),
(26, 2218, 1004, 'MODEL 3 55.', 'TESLA', 'ELECTRIC', '2024-07-17', 'APPROVED', '', NULL),
(27, 2214, 1004, 'MODEL 3 55.', 'TESLA', 'ELECTRIC', NULL, 'PENDING', '', NULL),
(28, 2213, 1004, 'MODEL 3 55.', 'TESLA', 'ELECTRIC', NULL, 'PENDING', '', NULL),
(29, 2210, 1000, 'HONDA CRV', 'HONDA', 'SUV', NULL, 'CANCELLED', 'meowmeow', NULL),
(30, 2211, 1000, 'HONDA CRV', 'HONDA', 'SUV', '2024-07-17', 'APPROVED', 'MEOWE', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `CustID` int(11) NOT NULL,
  `CustPass` varchar(20) NOT NULL,
  `FirstName` varchar(30) NOT NULL,
  `LastName` varchar(30) NOT NULL,
  `ContactNo` varchar(10) NOT NULL,
  `EmailAddress` varchar(30) NOT NULL,
  `LoyaltyPointEarned` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CustID`, `CustPass`, `FirstName`, `LastName`, `ContactNo`, `EmailAddress`, `LoyaltyPointEarned`) VALUES
(1000, 'Arshad', 'Muhd', 'Arshad', '0197970753', 'arshadmuhd50@gmail.com', 20),
(1001, 'Khalis', 'Muhd', 'Khalis', '0127770621', 'kz@gmail.com', 140),
(1003, 'Danial', 'Muhammad', 'Danial', '0198985933', 'd@gmail.com', 100),
(1004, 'Rishi', 'Rishi', 'R', '0198929123', 'rishi@gmail.com', 100);

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `InvoiceID` int(11) NOT NULL,
  `InvoiceDate` date NOT NULL DEFAULT curdate(),
  `NormalAmount` double(10,2) NOT NULL DEFAULT 0.00,
  `TotalAmount` double(10,2) NOT NULL DEFAULT 0.00,
  `Discount` double(10,2) NOT NULL DEFAULT 0.00,
  `SlotID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`InvoiceID`, `InvoiceDate`, `NormalAmount`, `TotalAmount`, `Discount`, `SlotID`) VALUES
(56, '2024-07-17', 35.00, 24.00, 11.00, 13);

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `ServiceID` int(11) NOT NULL,
  `ServiceType` varchar(30) NOT NULL,
  `Description` varchar(50) DEFAULT 'N/A',
  `Cost` double(10,2) NOT NULL DEFAULT 0.00,
  `LoyaltyPoint` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`ServiceID`, `ServiceType`, `Description`, `Cost`, `LoyaltyPoint`) VALUES
(2210, 'Oil Change', 'Replacing engine oil and oil filter to ensure prop', 50.00, 30),
(2211, 'Tire Rotation', 'Switching the position of tires to promote even we', 35.00, 20),
(2212, 'Brake Inspection and Replacing', 'Checking brake components, replacing brake pads, a', 200.00, 60),
(2213, 'Wheel Alignment', 'Adjusting the angles of wheels to ensure proper ve', 100.00, 45),
(2214, 'Air Filter Replacement', 'Replacing the engine air filter to maintain optima', 30.00, 15),
(2215, 'Transmission Fluid Change', 'Replacing old transmission fluid to ensure smooth ', 150.00, 50),
(2216, 'Coolant Flush', 'Flushing and replacing the engine coolant to preve', 75.00, 35),
(2217, 'Battery Replacement', 'Replacing the car battery when it no longer holds ', 200.00, 65),
(2218, 'Spark Plug Replacement', 'Replacing spark plugs to maintain proper combustio', 120.00, 40),
(2219, 'Suspension System Service', 'Inspecting and servicing components like shocks an', 300.00, 80),
(2220, 'Power Steering Fluid', 'Flushing and replacing power steering fluid for sm', 75.00, 35),
(2221, 'Fuel System Cleaning', 'Cleaning fuel injectors and removing carbon deposi', 120.00, 50);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `StaffID` varchar(6) NOT NULL,
  `StaffPass` varchar(20) NOT NULL,
  `FirstName` varchar(30) NOT NULL,
  `LastName` varchar(30) NOT NULL,
  `ContactNo` varchar(10) NOT NULL,
  `Position` varchar(30) NOT NULL,
  `AdminID` varchar(6) NOT NULL,
  `AccountStatus` varchar(12) DEFAULT 'ACTIVE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`StaffID`, `StaffPass`, `FirstName`, `LastName`, `ContactNo`, `Position`, `AdminID`, `AccountStatus`) VALUES
('S0001', 'abc123', 'Danial', 'Hakim', '0133483570', 'Mechanic', 'A0001', 'ACTIVE'),
('S0002', 'abc123', 'Muhammad', 'Arshad', '0128736189', 'Senior Mechanic', 'A0001', 'DEACTIVATED'),
('S0003', 'abc123', 'Khalis', 'Zakwan', '0182337823', 'Mechanic', 'A0001', 'ACTIVE'),
('S0004', 'abc123', 'Rishi', 'R', '0134567892', 'Senior Mechanic', 'A0001', 'ACTIVE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `car_service`
--
ALTER TABLE `car_service`
  ADD PRIMARY KEY (`SlotID`,`ServiceID`,`CustID`),
  ADD KEY `StaffID` (`StaffID`),
  ADD KEY `ServiceID` (`ServiceID`),
  ADD KEY `CustID` (`CustID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CustID`),
  ADD UNIQUE KEY `ContactNo` (`ContactNo`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`InvoiceID`),
  ADD KEY `SlotID` (`SlotID`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`ServiceID`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`StaffID`),
  ADD UNIQUE KEY `ContactNo` (`ContactNo`),
  ADD KEY `AdminID` (`AdminID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `car_service`
--
ALTER TABLE `car_service`
  MODIFY `SlotID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `CustID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1005;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `InvoiceID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `ServiceID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2222;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
