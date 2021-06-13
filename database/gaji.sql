-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2021 at 08:51 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gaji`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_absen`
--

CREATE TABLE `data_absen` (
  `id_absen` int(20) NOT NULL,
  `id_kar` int(20) NOT NULL,
  `in` date NOT NULL,
  `out` date NOT NULL,
  `status` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `data_cuti`
--

CREATE TABLE `data_cuti` (
  `id_cut` int(20) NOT NULL,
  `id_kar` int(20) NOT NULL,
  `starcut` date NOT NULL,
  `endcut` date NOT NULL,
  `reason` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `data_karyawan`
--

CREATE TABLE `data_karyawan` (
  `id_kar` int(20) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jk` varchar(5) NOT NULL,
  `agama` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `gp` varchar(10) NOT NULL,
  `id_tun` int(5) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `data_potongan`
--

CREATE TABLE `data_potongan` (
  `id_pot` int(20) NOT NULL,
  `id_kar` int(20) NOT NULL,
  `bpjs` varchar(100) NOT NULL,
  `potongan_keterangan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `data_tunjangan`
--

CREATE TABLE `data_tunjangan` (
  `id_tun` int(20) NOT NULL,
  `jabatan` varchar(100) NOT NULL,
  `totaltun` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_absen`
--
ALTER TABLE `data_absen`
  ADD PRIMARY KEY (`id_absen`);

--
-- Indexes for table `data_cuti`
--
ALTER TABLE `data_cuti`
  ADD PRIMARY KEY (`id_cut`);

--
-- Indexes for table `data_karyawan`
--
ALTER TABLE `data_karyawan`
  ADD PRIMARY KEY (`id_kar`);

--
-- Indexes for table `data_potongan`
--
ALTER TABLE `data_potongan`
  ADD PRIMARY KEY (`id_pot`);

--
-- Indexes for table `data_tunjangan`
--
ALTER TABLE `data_tunjangan`
  ADD PRIMARY KEY (`id_tun`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `data_absen`
--
ALTER TABLE `data_absen`
  MODIFY `id_absen` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `data_cuti`
--
ALTER TABLE `data_cuti`
  MODIFY `id_cut` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `data_tunjangan`
--
ALTER TABLE `data_tunjangan`
  MODIFY `id_tun` int(20) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
