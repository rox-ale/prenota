-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Creato il: Lug 19, 2020 alle 14:09
-- Versione del server: 8.0.19
-- Versione PHP: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prenotamensa`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `giorno`
--

CREATE TABLE `giorno` (
  `token_giorno` int NOT NULL,
  `data` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `giorno`
--

INSERT INTO `giorno` (`token_giorno`, `data`) VALUES
(1, '2020-06-04'),
(2, '2020-06-10'),
(14, '2020-06-01'),
(15, '2020-06-13'),
(16, '2020-07-02'),
(17, '2020-07-05'),
(18, '2020-07-20'),
(19, '2020-07-21');

-- --------------------------------------------------------

--
-- Struttura della tabella `menu`
--

CREATE TABLE `menu` (
  `token_menu` int NOT NULL,
  `contorno` varchar(255) DEFAULT NULL,
  `extra` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `pasto` varchar(255) DEFAULT NULL,
  `giorno_token_giorno` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `menu`
--

INSERT INTO `menu` (`token_menu`, `contorno`, `extra`, `numero`, `note`, `pasto`, `giorno_token_giorno`) VALUES
(1, 'tegoline', '', 'menu2', '', 'pasta e fagioli', 14),
(2, NULL, '', 'non mangi un cazzo aahahah', '', '', 15),
(3, 'xxx', '', 'xxx', '', 'xxx', 16),
(4, 'bb', 'bb', 'bb', 'bb', 'bb', 16),
(5, 'rr', '', 'rr', '', 'rr', 17),
(6, 'gsdgsdf', 'gfsd', 'gg', '', 'gfdsgfsd', 17),
(7, 'INSALATA DI FAGIOLINI*  E POMODORI FRESCHI					', 'DESSERT  					', 'LUNCH BOX   N°1', 'condimenti monodose					 bicchiese monouso imbustato					 1/2 litro acqua minerale in pet					 grissini imbustati					', 'FARRO PERLATO CON CHAMPIGNON*,OLIVE E RUCOLA					', 18),
(8, 'CAROTE CRUDE A JULIENNE					', 'DESSERT 					', 'LUNCH BOX  N°2 ', 'condimenti monodose					 bicchiese monouso imbustato					 1/2 litro acqua minerale in pet					 grissini imbustati 					', 'CROSTATA DI SFOGLIA * VEGETARIANA					', 18),
(9, 'CAROTE LESSE* ALL\'EXTRAVERGINE					', 'DESSERT  					', 'LUNCH BOX  N°3 ', 'condimenti monodose					 bicchiese monouso imbustato					 1/2 litro acqua minerale in pet					 grissini imbustati					', 'COTOLETTA DI POLLO* SU MISTICANZA					', 18),
(10, 'INSALATRA MISTA					', 'YOGURT					', 'LUNCH BOX   N°4 ( VEG )', 'condimenti monodose					 bicchiese monouso imbustato					 1/2 litro acqua minerale in pet					 grissini imbustati					', 'MOZZARELLA					', 18),
(11, 'FRUTTA					', 'FRUTTA				', 'LUNCH BOX  N°2 			', 'condimenti monodose					 bicchiese monouso imbustato					 1/2 litro acqua minerale in pet					 grissini imbustati 					', 'ARROSTO DI LONZA* CON ZUCCHINE MARINATE	', 19),
(12, 'TRIS DI VERDURE *LESSE					', 'FRUTTA					', 'LUNCH BOX   N°4 ( VEG )', 'condimenti monodose					 bicchiese monouso imbustato					 1/2 litro acqua minerale in pet					 grissini imbustati					', 'RISO ALL\'INGLESE					', 19),
(13, 'INSALATA MISTA					', 'FRUTTA					', 'LUNCH BOX  N°3 ', 'condimenti monodose					 bicchiese monouso imbustato					 1/2 litro acqua minerale in pet					 grissini imbustati					', 'BOCCONCINI DI MERLUZZO* DORATI SU CAPPUCCIO ROSSO					', 19),
(14, 'INSALATA DI POMODORI					', 'FRUTTA					', 'LUNCH BOX   N°1', 'condimenti monodose					 bicchiese monouso imbustato					 1/2 litro acqua minerale in pet					 grissini imbustati					', 'PASTA INTEGRALE CON POMODORI, MAIS E ZUCCHINE					', 19);

-- --------------------------------------------------------

--
-- Struttura della tabella `prenotazione`
--

CREATE TABLE `prenotazione` (
  `token_prenotazione` int NOT NULL,
  `menu_token_menu` int DEFAULT NULL,
  `utente_token_utente` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `prenotazione`
--

INSERT INTO `prenotazione` (`token_prenotazione`, `menu_token_menu`, `utente_token_utente`) VALUES
(5, 13, 2),
(6, 13, 3),
(7, 11, 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `ruolo`
--

CREATE TABLE `ruolo` (
  `token_ruolo` int NOT NULL,
  `nome` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `ruolo`
--

INSERT INTO `ruolo` (`token_ruolo`, `nome`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE `utente` (
  `token_utente` bigint NOT NULL,
  `cognome` varchar(255) DEFAULT NULL,
  `enable` bit(1) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `token_ruolo` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`token_utente`, `cognome`, `enable`, `nome`, `password`, `username`, `token_ruolo`) VALUES
(1, 'a', b'1', 'a', '$2a$10$2du5CU0n0oO2JJNLlzoWjun3WntEj6TDzfYJM1SM5oeSUvhCS5z9C', 'admin', 1),
(2, 'Bortolotto', b'1', 'Elisabetta', '$2a$10$2du5CU0n0oO2JJNLlzoWjun3WntEj6TDzfYJM1SM5oeSUvhCS5z9C', 'utente', 2),
(3, 'Bonato', b'1', 'Alessio', '$2a$10$2du5CU0n0oO2JJNLlzoWjun3WntEj6TDzfYJM1SM5oeSUvhCS5z9C', 'utente1', 2),
(4, 'Pinarello', b'1', 'Linda', '$2a$10$2du5CU0n0oO2JJNLlzoWjun3WntEj6TDzfYJM1SM5oeSUvhCS5z9C', 'utente2', NULL),
(5, 'Peron', b'1', 'Marta', '$2a$10$2du5CU0n0oO2JJNLlzoWjun3WntEj6TDzfYJM1SM5oeSUvhCS5z9C', 'utente3', 2),
(6, 'Checchin', b'1', 'Silvia', '$2a$10$2du5CU0n0oO2JJNLlzoWjun3WntEj6TDzfYJM1SM5oeSUvhCS5z9C', 'utente4', 2);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `giorno`
--
ALTER TABLE `giorno`
  ADD PRIMARY KEY (`token_giorno`);

--
-- Indici per le tabelle `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`token_menu`),
  ADD KEY `FKg9f76d66bjl3cumkrumwn6swe` (`giorno_token_giorno`);

--
-- Indici per le tabelle `prenotazione`
--
ALTER TABLE `prenotazione`
  ADD PRIMARY KEY (`token_prenotazione`),
  ADD KEY `FKmw8ewds0kpsjjm1jkobpxf6ms` (`menu_token_menu`),
  ADD KEY `FKdta7ik6yv89nyrrh9dxytg7lp` (`utente_token_utente`);

--
-- Indici per le tabelle `ruolo`
--
ALTER TABLE `ruolo`
  ADD PRIMARY KEY (`token_ruolo`);

--
-- Indici per le tabelle `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`token_utente`),
  ADD UNIQUE KEY `UK2vq82crxh3p7upassu0k1kmte` (`username`),
  ADD KEY `FKfo04gcfma15vv4yv2pgw0mddr` (`token_ruolo`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `giorno`
--
ALTER TABLE `giorno`
  MODIFY `token_giorno` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT per la tabella `menu`
--
ALTER TABLE `menu`
  MODIFY `token_menu` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT per la tabella `prenotazione`
--
ALTER TABLE `prenotazione`
  MODIFY `token_prenotazione` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT per la tabella `ruolo`
--
ALTER TABLE `ruolo`
  MODIFY `token_ruolo` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `utente`
--
ALTER TABLE `utente`
  MODIFY `token_utente` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `FKg9f76d66bjl3cumkrumwn6swe` FOREIGN KEY (`giorno_token_giorno`) REFERENCES `giorno` (`token_giorno`);

--
-- Limiti per la tabella `prenotazione`
--
ALTER TABLE `prenotazione`
  ADD CONSTRAINT `FKdta7ik6yv89nyrrh9dxytg7lp` FOREIGN KEY (`utente_token_utente`) REFERENCES `utente` (`token_utente`),
  ADD CONSTRAINT `FKmw8ewds0kpsjjm1jkobpxf6ms` FOREIGN KEY (`menu_token_menu`) REFERENCES `menu` (`token_menu`);

--
-- Limiti per la tabella `utente`
--
ALTER TABLE `utente`
  ADD CONSTRAINT `FKfo04gcfma15vv4yv2pgw0mddr` FOREIGN KEY (`token_ruolo`) REFERENCES `ruolo` (`token_ruolo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
