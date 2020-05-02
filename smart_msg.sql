-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 02, 2020 at 08:22 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smart_msg`
--

-- --------------------------------------------------------

--
-- Table structure for table `friend_list`
--

CREATE TABLE `friend_list` (
  `id` int(11) NOT NULL,
  `user` varchar(200) NOT NULL,
  `friend` varchar(200) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `friend_list`
--

INSERT INTO `friend_list` (`id`, `user`, `friend`, `created_at`) VALUES
(7, 'a', 'c', '2020-03-09 17:32:54'),
(8, 'a', 'b', '2020-03-09 17:33:26'),
(9, '', '', '2020-03-09 17:34:30'),
(10, 'a', 'b', '2020-03-09 17:36:28');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `public_key` text NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `phone`, `email`, `password`, `public_key`, `created_at`) VALUES
(11, 'a', 'a', 'a', 'a', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv7FuzjvM7f6PA1mZ1wXcjV7qsNxtC565\n1Akt2b3LMkG54EEWqGcm5iO/duq/XWJg3+cYIlzHBPcnnWwndxiPTdG9eRPv/TdGvIBIpFhgpTKb\n322lESYX9wClo7pb84T7jAly5SsLmd3gODLBtiRPxGcZ7ZuyU/IZim+6E771zAZSK7Sc44TLlbJA\n2d4BkhoxtvOUGqA2jIUDTpfVfvDzJPouii8Fmsoo+WRVp8oUfbu2kNZPGjEvb2x9YkvdiZ5wI9N4\nuHMw9TUIvH+tITOvZgVPIMWJMNo7WGzwJgyW+q4J/R/8KByIqz69221FL5hGJBh2sZbEPWxNKV6N\nX+ShAwIDAQAB\n', '2020-03-09 13:15:46'),
(12, 'b', 'b', 'b', 'b', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApGyGf3KGJ3Puel8MEXatwyBAzISRWd7z\ndQN5Qo2aWD0mfpnF+OkTd8BhZr/iidktLoTXM8wMfnexFU2JgbNM6cQEEIDvhKvycI0VydVza3nS\nertluNN1C1sLRBcYRxDY/WVE6aKWAX572GrDS96NhOK+n+3EhY28+rmv/SbKZUNPrS7mtjAxKTyU\n8EfAtXvwt+nBNwEvcjJ/ugWQmH/I5PMthpD3k/fp7AJ+N2NGX3CZhnDWWJJGSjavSvgKE0Tsi2fl\nyUxFg1RRhxTpNvZ8/HnVR8EXjSdnKf2D6IW4Ritwz1xsA1xJkiRYLFjlfbnQT/NG1tsgtLRt4eDV\ndMw7TQIDAQAB\n', '2020-03-09 13:15:51'),
(13, 'vishu', 'c', 'c', 'c', 'MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsmyrttVReSwOr0Nq3wgT1yswPnlma4Pd\nnJK6W1vcIC5ir2wkYpF7S9naWDMfPAvTz+nGS2feQoHt+NDsegNZMLOj5Idkc9WSwOfXVhbJ47cK\nRL9RgtreVLOhN8xfl+j3IRkW8UEZmjQPMIyDhugMiVnDIR5eFg5pZIpNDWnPB4IlunfOECDq7vb/\n7zBbv3YWyNgJHAATdNu7ihGJn1hgUn4hp9hjJpwxGUP8bb5mFI/1XxeBa9KuJMBCxbVk03vnpNuP\n3w/3UbLzy3LNcTWF+B8gP0C5AMNZvYBlUMKFFgNHWH7ofJxHykCgkTIgghw5xthoTl6j6DybnIQR\nYmg0XwIDAQAB\n', '2020-03-09 16:09:55');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `friend_list`
--
ALTER TABLE `friend_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `friend_list`
--
ALTER TABLE `friend_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
