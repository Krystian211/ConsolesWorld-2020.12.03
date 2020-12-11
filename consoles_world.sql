-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 11 Gru 2020, 20:59
-- Wersja serwera: 10.4.14-MariaDB
-- Wersja PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `consoles_world`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tproduct`
--

CREATE TABLE `tproduct` (
  `id` int(11) NOT NULL,
  `name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `brand` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `category` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `pieces` int(11) NOT NULL,
  `manufacturerCode` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `price` double(7,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `tproduct`
--

INSERT INTO `tproduct` (`id`, `name`, `brand`, `category`, `pieces`, `manufacturerCode`, `price`) VALUES
(1, 'PlayStation 5', 'Sony', 'CONSOLES', 10, '711719757313/SONY', 2250.00),
(2, 'Xbox Series X', 'Microsoft', 'consoles', 10, 'RRT-00010', 2250.00),
(3, 'Xbox Series S', 'Microsoft', 'consoles', 20, 'RRS-00010', 1300.00),
(4, 'Kontroler DualSense', 'Sony', 'controllers', 20, '711719399605/SONY', 350.00),
(5, 'Kontroler Xbox Series', 'Microsoft', 'controllers', 25, 'QAS-00002', 250.00),
(6, 'Słuchawki PULSE 3D', 'Sony', 'accessories', 5, '711719387909/SONY', 400.00);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tuser`
--

CREATE TABLE `tuser` (
  `id` int(11) NOT NULL,
  `login` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(33) COLLATE utf8_unicode_ci NOT NULL,
  `firstName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `lastName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `master` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `tuser`
--

INSERT INTO `tuser` (`id`, `login`, `password`, `firstName`, `lastName`, `email`, `master`) VALUES
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'Admin', 'Admin', 'admin@gmail.com', 1),
(2, 'customer', '91ec1f9324753048c0096d036a694f86', 'Customer', 'Customer', 'customer@gmail.com', 0);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `tproduct`
--
ALTER TABLE `tproduct`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `manufacturerCode` (`manufacturerCode`);

--
-- Indeksy dla tabeli `tuser`
--
ALTER TABLE `tuser`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `tproduct`
--
ALTER TABLE `tproduct`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT dla tabeli `tuser`
--
ALTER TABLE `tuser`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
