/*
SQLyog Enterprise v12.5.1 (64 bit)
MySQL - 10.4.18-MariaDB : Database - gaji
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`gaji` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `gaji`;

/*Table structure for table `data_absen` */

DROP TABLE IF EXISTS `data_absen`;

CREATE TABLE `data_absen` (
  `id_absen` varchar(100) NOT NULL,
  `id_kar` varchar(100) NOT NULL,
  `masuk` varchar(100) NOT NULL,
  `keluar` varchar(100) NOT NULL,
  `status` varchar(200) NOT NULL,
  PRIMARY KEY (`id_absen`),
  KEY `id_kar` (`id_kar`),
  CONSTRAINT `data_absen_ibfk_1` FOREIGN KEY (`id_kar`) REFERENCES `data_karyawan` (`id_kar`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `data_absen` */

insert  into `data_absen`(`id_absen`,`id_kar`,`masuk`,`keluar`,`status`) values 
('12','2074','2/4/2021  07:30','2/4/2021  16:00','hadir'),
('8756','1720','07.00','15.00','Hadir');

/*Table structure for table `data_cuti` */

DROP TABLE IF EXISTS `data_cuti`;

CREATE TABLE `data_cuti` (
  `id_cuti` varchar(100) NOT NULL,
  `id_kar` varchar(100) NOT NULL,
  `start_cuti` varchar(100) NOT NULL,
  `end_cuti` varchar(100) NOT NULL,
  `reason` varchar(200) NOT NULL,
  PRIMARY KEY (`id_cuti`),
  KEY `id_kar` (`id_kar`),
  CONSTRAINT `data_cuti_ibfk_1` FOREIGN KEY (`id_kar`) REFERENCES `data_karyawan` (`id_kar`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `data_cuti` */

insert  into `data_cuti`(`id_cuti`,`id_kar`,`start_cuti`,`end_cuti`,`reason`) values 
('12345','2074','01/06/2021 02:16','08/06/2021 02:16','mudik'),
('2334','1720','08/06/2021 12:00','14/06/2021 05:04','Mudik');

/*Table structure for table `data_karyawan` */

DROP TABLE IF EXISTS `data_karyawan`;

CREATE TABLE `data_karyawan` (
  `id_kar` varchar(30) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jk` varchar(100) NOT NULL,
  `agama` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `gp` decimal(16,2) NOT NULL,
  `alamat` varchar(500) NOT NULL,
  `jabatan` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_kar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `data_karyawan` */

insert  into `data_karyawan`(`id_kar`,`nama`,`jk`,`agama`,`email`,`gp`,`alamat`,`jabatan`) values 
('1718','Rully','Laki-Laki','Islam','rully@gmail.com',5000000.00,'Depok','HRD'),
('1720','Damar Fajri P','Laki-laki','Islam','damarfajri06@gmail.com',5000000.00,'Depok','Staff it'),
('1888','Muhammad Zaki','Laki-Laki','Islam','zaki@gmail.com',6000000.00,'Bekasi','Manager'),
('2074','Ripal','Laki-Laki','Islam','ripal@gmail.com',5000000.00,'bekasi','Staff');

/*Table structure for table `data_lembur` */

DROP TABLE IF EXISTS `data_lembur`;

CREATE TABLE `data_lembur` (
  `id_lembur` int(100) NOT NULL AUTO_INCREMENT,
  `id_kar` varchar(100) DEFAULT NULL,
  `bayaran` decimal(16,2) DEFAULT NULL,
  PRIMARY KEY (`id_lembur`),
  KEY `id_kar` (`id_kar`),
  CONSTRAINT `data_lembur_ibfk_1` FOREIGN KEY (`id_kar`) REFERENCES `data_karyawan` (`id_kar`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `data_lembur` */

insert  into `data_lembur`(`id_lembur`,`id_kar`,`bayaran`) values 
(7,'1888',400.00),
(9,'1720',800.00),
(10,'1720',600.00),
(11,'2074',200000.00);

/*Table structure for table `data_payment` */

DROP TABLE IF EXISTS `data_payment`;

CREATE TABLE `data_payment` (
  `id_pay` int(100) NOT NULL AUTO_INCREMENT,
  `id_kar` varchar(100) NOT NULL,
  `tunjangan` decimal(16,2) DEFAULT NULL,
  `gp` decimal(16,2) DEFAULT NULL,
  `pem_lembur` decimal(16,2) DEFAULT NULL,
  `potongan` decimal(16,2) DEFAULT NULL,
  `tot_gaji` decimal(16,2) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  PRIMARY KEY (`id_pay`),
  KEY `id_kar` (`id_kar`),
  CONSTRAINT `data_payment_ibfk_1` FOREIGN KEY (`id_kar`) REFERENCES `data_karyawan` (`id_kar`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `data_payment` */

insert  into `data_payment`(`id_pay`,`id_kar`,`tunjangan`,`gp`,`pem_lembur`,`potongan`,`tot_gaji`,`created_date`) values 
(1,'1720',900000.00,5000000.00,1400.00,420000.00,5481400.00,'2021-06-13'),
(5,'1720',900000.00,5000000.00,1400.00,420000.00,5481400.00,'2021-07-14');

/*Table structure for table `data_potongan` */

DROP TABLE IF EXISTS `data_potongan`;

CREATE TABLE `data_potongan` (
  `id_pot` varchar(100) NOT NULL,
  `id_kar` varchar(100) NOT NULL,
  `bpjs` decimal(16,2) NOT NULL,
  `pot_ket` decimal(16,2) NOT NULL,
  `pot_tam` decimal(16,2) NOT NULL,
  PRIMARY KEY (`id_pot`),
  KEY `id_kar` (`id_kar`),
  CONSTRAINT `data_potongan_ibfk_1` FOREIGN KEY (`id_kar`) REFERENCES `data_karyawan` (`id_kar`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `data_potongan` */

insert  into `data_potongan`(`id_pot`,`id_kar`,`bpjs`,`pot_ket`,`pot_tam`) values 
('123','2074',50000.00,50000.00,50000.00),
('1345','1718',150000.00,150000.00,50000.00),
('5467','1720',150000.00,200000.00,70000.00);

/*Table structure for table `data_tunjangan` */

DROP TABLE IF EXISTS `data_tunjangan`;

CREATE TABLE `data_tunjangan` (
  `id_tunjangan` varchar(100) NOT NULL,
  `id_kar` varchar(100) DEFAULT NULL,
  `total_tun` decimal(16,2) NOT NULL,
  PRIMARY KEY (`id_tunjangan`),
  KEY `id_kar` (`id_kar`),
  CONSTRAINT `data_tunjangan_ibfk_1` FOREIGN KEY (`id_kar`) REFERENCES `data_karyawan` (`id_kar`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `data_tunjangan` */

insert  into `data_tunjangan`(`id_tunjangan`,`id_kar`,`total_tun`) values 
('2234','1720',900000.00),
('4321','2074',400000.00);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
