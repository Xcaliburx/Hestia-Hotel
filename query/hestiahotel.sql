-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 12 Jan 2023 pada 12.24
-- Versi server: 10.4.20-MariaDB
-- Versi PHP: 7.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hestiahotel`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `attendance`
--

CREATE TABLE `attendance` (
  `employeeId` int(10) NOT NULL,
  `attendanceDateTime` timestamp(6) NOT NULL DEFAULT current_timestamp(6) ON UPDATE current_timestamp(6),
  `attendanceType` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `attendance`
--

INSERT INTO `attendance` (`employeeId`, `attendanceDateTime`, `attendanceType`) VALUES
(1, '2023-01-12 11:20:42.000000', 'clock in'),
(1, '2023-01-12 11:20:47.000000', 'clock out');

-- --------------------------------------------------------

--
-- Struktur dari tabel `checkin`
--

CREATE TABLE `checkin` (
  `checkInId` int(11) NOT NULL,
  `dateTime` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `receptionistId` int(11) NOT NULL,
  `numberOfCustomer` int(11) NOT NULL,
  `roomId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `checkin`
--

INSERT INTO `checkin` (`checkInId`, `dateTime`, `receptionistId`, `numberOfCustomer`, `roomId`) VALUES
(1, '2023-01-11 17:58:46', 2, 23, 3),
(2, '2023-01-12 10:06:02', 2, 20, 6),
(3, '2023-01-25 11:19:15', 2, 2, 4);

-- --------------------------------------------------------

--
-- Struktur dari tabel `checkout`
--

CREATE TABLE `checkout` (
  `checkOutId` int(11) NOT NULL,
  `checkInId` int(11) NOT NULL,
  `dateTime` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `receptionistId` int(11) NOT NULL,
  `paymentAmount` int(11) NOT NULL,
  `paymentType` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `checkout`
--

INSERT INTO `checkout` (`checkOutId`, `checkInId`, `dateTime`, `receptionistId`, `paymentAmount`, `paymentType`) VALUES
(1, 1, '2023-01-12 11:17:02', 2, 50000, 'cash'),
(8, 2, '2023-01-10 11:17:19', 2, 25000, 'digital');

-- --------------------------------------------------------

--
-- Struktur dari tabel `customerrequest`
--

CREATE TABLE `customerrequest` (
  `customerRequestId` int(11) NOT NULL,
  `receptionistId` int(11) NOT NULL,
  `roomId` int(11) NOT NULL,
  `request` varchar(255) NOT NULL,
  `requestDateTime` timestamp(6) NULL DEFAULT NULL ON UPDATE current_timestamp(6),
  `handlerEmployeeId` int(11) DEFAULT NULL,
  `responseDateTime` timestamp(6) NULL DEFAULT NULL,
  `responseNote` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `customerrequest`
--

INSERT INTO `customerrequest` (`customerRequestId`, `receptionistId`, `roomId`, `request`, `requestDateTime`, `handlerEmployeeId`, `responseDateTime`, `responseNote`) VALUES
(1, 2, 4, 'request', '2023-01-09 17:57:01.000000', 4, '2023-01-17 17:57:01.000000', 'notes'),
(5, 2, 6, 'Request 2', '2023-01-10 11:19:43.000000', 5, '2023-01-23 11:19:43.000000', 'finish');

-- --------------------------------------------------------

--
-- Struktur dari tabel `employee`
--

CREATE TABLE `employee` (
  `employeeId` int(10) NOT NULL,
  `employeeUsername` varchar(255) NOT NULL,
  `employeePassword` varchar(255) NOT NULL,
  `employeeEmail` varchar(255) NOT NULL,
  `employeeName` varchar(255) NOT NULL,
  `employeeGender` varchar(255) NOT NULL,
  `employeeBirthDate` varchar(255) NOT NULL,
  `employeeRole` varchar(255) NOT NULL,
  `isFired` bit(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `employee`
--

INSERT INTO `employee` (`employeeId`, `employeeUsername`, `employeePassword`, `employeeEmail`, `employeeName`, `employeeGender`, `employeeBirthDate`, `employeeRole`, `isFired`) VALUES
(1, 'manager', 'User123', 'manager@user.com', 'manager 1', 'male', '17 August 2001', 'manager', b'0000000000'),
(2, 'receptionist', 'User123', 'receptionist@user.com', 'Receptionist', 'female', '10 July 2000', 'receptionist', b'0000000000'),
(3, 'security', 'User123', 'security@user.com', 'security 33', 'male', '14 June 2000', 'security staff', b'0000000000'),
(4, 'bell', 'User123', 'bell@user.com', 'Bell', 'female', '7 January 1998', 'bell staff', b'0000000000'),
(5, 'maintenance', 'User123', 'maintenance@user.com', 'Maintenance', 'male', '10 March 1998', 'maintenance staff', b'0000000000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `journal`
--

CREATE TABLE `journal` (
  `journalId` int(11) NOT NULL,
  `securityStaffId` int(11) NOT NULL,
  `journalDate` date NOT NULL,
  `journalMessage` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `journal`
--

INSERT INTO `journal` (`journalId`, `securityStaffId`, `journalDate`, `journalMessage`) VALUES
(1, 3, '2023-01-11', 'Journal 1'),
(2, 3, '2023-01-13', 'journal 2');

-- --------------------------------------------------------

--
-- Struktur dari tabel `room`
--

CREATE TABLE `room` (
  `roomId` int(11) NOT NULL,
  `roomNumber` int(11) NOT NULL,
  `roomType` varchar(255) NOT NULL,
  `roomPrice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `room`
--

INSERT INTO `room` (`roomId`, `roomNumber`, `roomType`, `roomPrice`) VALUES
(3, 64, 'standard', 8000),
(4, 87, 'premium', 10000),
(6, 45, 'standard', 8000);

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `attendance`
--
ALTER TABLE `attendance`
  ADD KEY `employeeId` (`employeeId`);

--
-- Indeks untuk tabel `checkin`
--
ALTER TABLE `checkin`
  ADD PRIMARY KEY (`checkInId`),
  ADD KEY `receiptionistId` (`receptionistId`),
  ADD KEY `roomId` (`roomId`);

--
-- Indeks untuk tabel `checkout`
--
ALTER TABLE `checkout`
  ADD PRIMARY KEY (`checkOutId`),
  ADD KEY `checkInId` (`checkInId`),
  ADD KEY `receptionistId` (`receptionistId`);

--
-- Indeks untuk tabel `customerrequest`
--
ALTER TABLE `customerrequest`
  ADD PRIMARY KEY (`customerRequestId`),
  ADD KEY `receptionistId` (`receptionistId`),
  ADD KEY `roomId` (`roomId`),
  ADD KEY `handlerEmployeeId` (`handlerEmployeeId`);

--
-- Indeks untuk tabel `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employeeId`),
  ADD UNIQUE KEY `employeeEmail` (`employeeEmail`),
  ADD UNIQUE KEY `employeeUsername` (`employeeUsername`);

--
-- Indeks untuk tabel `journal`
--
ALTER TABLE `journal`
  ADD PRIMARY KEY (`journalId`),
  ADD KEY `securityStaffId` (`securityStaffId`);

--
-- Indeks untuk tabel `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`roomId`),
  ADD UNIQUE KEY `roomNumber` (`roomNumber`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `checkin`
--
ALTER TABLE `checkin`
  MODIFY `checkInId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `checkout`
--
ALTER TABLE `checkout`
  MODIFY `checkOutId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `customerrequest`
--
ALTER TABLE `customerrequest`
  MODIFY `customerRequestId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `employee`
--
ALTER TABLE `employee`
  MODIFY `employeeId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `journal`
--
ALTER TABLE `journal`
  MODIFY `journalId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `room`
--
ALTER TABLE `room`
  MODIFY `roomId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`);

--
-- Ketidakleluasaan untuk tabel `checkin`
--
ALTER TABLE `checkin`
  ADD CONSTRAINT `checkin_ibfk_1` FOREIGN KEY (`receptionistId`) REFERENCES `employee` (`employeeId`),
  ADD CONSTRAINT `checkin_ibfk_2` FOREIGN KEY (`roomId`) REFERENCES `room` (`roomId`);

--
-- Ketidakleluasaan untuk tabel `checkout`
--
ALTER TABLE `checkout`
  ADD CONSTRAINT `checkout_ibfk_1` FOREIGN KEY (`checkInId`) REFERENCES `checkin` (`checkInId`),
  ADD CONSTRAINT `checkout_ibfk_2` FOREIGN KEY (`receptionistId`) REFERENCES `employee` (`employeeId`);

--
-- Ketidakleluasaan untuk tabel `customerrequest`
--
ALTER TABLE `customerrequest`
  ADD CONSTRAINT `customerrequest_ibfk_1` FOREIGN KEY (`receptionistId`) REFERENCES `employee` (`employeeId`),
  ADD CONSTRAINT `customerrequest_ibfk_2` FOREIGN KEY (`roomId`) REFERENCES `room` (`roomId`),
  ADD CONSTRAINT `customerrequest_ibfk_3` FOREIGN KEY (`handlerEmployeeId`) REFERENCES `employee` (`employeeId`);

--
-- Ketidakleluasaan untuk tabel `journal`
--
ALTER TABLE `journal`
  ADD CONSTRAINT `journal_ibfk_1` FOREIGN KEY (`securityStaffId`) REFERENCES `employee` (`employeeId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
