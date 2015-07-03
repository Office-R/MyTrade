-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 03. Jul 2015 um 16:00
-- Server-Version: 5.6.24
-- PHP-Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Datenbank: `mydb`
--
CREATE DATABASE IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `mydb`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `aktie`
--

CREATE TABLE IF NOT EXISTS `aktie` (
  `aktie_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `nominalpreis` decimal(9,2) NOT NULL,
  `dividende` decimal(9,2) NOT NULL,
  `fk_user` int(11) NOT NULL,
  `fk_symbol` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `aktie`
--

INSERT INTO `aktie` (`aktie_id`, `name`, `nominalpreis`, `dividende`, `fk_user`, `fk_symbol`) VALUES
(1, 'Go Pro', '50.00', '0.00', 1, 1),
(2, 'RIB Software AG', '9.14', '45.00', 2, 3),
(3, 'RIB Software AG', '9.14', '45.00', 2, 3),
(5, 'Öl', '1.01', '0.01', 5, 2),
(12, 'Google', '50.00', '1.00', 2, 6),
(13, 'Google', '50.00', '1.00', 2, 6),
(14, 'Google', '50.00', '1.00', 2, 6),
(15, 'Google', '50.00', '1.00', 2, 6),
(16, 'Google', '50.00', '1.00', 2, 6),
(24, 'Amazon', '77.80', '10.00', 2, 8),
(25, 'Amazon', '77.80', '10.00', 2, 8),
(26, 'Amazon', '77.80', '10.00', 2, 8),
(27, 'Amazon', '77.80', '10.00', 2, 8),
(28, 'Amazon', '77.80', '10.00', 1, 8),
(29, 'Amazon', '77.80', '10.00', 2, 8),
(30, 'Amazon', '77.80', '10.00', 1, 8),
(31, 'hundeja Industries', '100000.00', '105.00', 2, 12),
(32, 'hundeja Industries', '100000.00', '105.00', 1, 12),
(33, 'hundeja Industries', '100000.00', '105.00', 6, 12),
(34, 'hundeja Industries', '100000.00', '105.00', 2, 12),
(35, 'hundeja Industries', '100000.00', '105.00', 2, 12),
(36, 'hundeja Industries', '100000.00', '105.00', 1, 12),
(37, 'hundeja Industries', '100000.00', '105.00', 1, 12),
(38, 'hundeja Industries', '100000.00', '105.00', 1, 12),
(39, 'hundeja Industries', '100000.00', '105.00', 1, 12),
(40, 'hundeja Industries', '100000.00', '105.00', 1, 12);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `auftrag`
--

CREATE TABLE IF NOT EXISTS `auftrag` (
  `auftrag_id` int(11) NOT NULL,
  `preis` decimal(9,2) NOT NULL,
  `fk_aktie` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rolle`
--

CREATE TABLE IF NOT EXISTS `rolle` (
  `rolle_id` int(11) NOT NULL,
  `rolle` varchar(45) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `rolle`
--

INSERT INTO `rolle` (`rolle_id`, `rolle`) VALUES
(1, 'Administrator'),
(2, 'Trader');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `symbol`
--

CREATE TABLE IF NOT EXISTS `symbol` (
  `symbol_id` int(11) NOT NULL,
  `symbol` varchar(45) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `symbol`
--

INSERT INTO `symbol` (`symbol_id`, `symbol`) VALUES
(8, 'AMZN'),
(4, 'APPL'),
(6, 'GOGL'),
(1, 'GPRO'),
(12, 'HDJA'),
(3, 'RSTA'),
(2, 'UWTI');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `vorname` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `kontostand` decimal(9,2) NOT NULL,
  `fk_rolle` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`user_id`, `name`, `vorname`, `login`, `password`, `kontostand`, `fk_rolle`) VALUES
(1, 'Daw', 'Gabriel', 'gabriel', '5f4dcc3b5aa765d61d8327deb882cf99', '-1595.12', 1),
(2, 'Frehner', 'Robin', 'robin', '5f4dcc3b5aa765d61d8327deb882cf99', '11503.06', 1),
(4, 'Maggion', 'Gwendolin', 'gwen', '5f4dcc3b5aa765d61d8327deb882cf99', '69.69', 1),
(5, 'Gehrig', 'Dennis', 'dennis', '8c17d70fb343532d4279f663522a029b', '10000.00', 2),
(6, 'Weiersmueller', 'Sacha', 'sacha', 'e10adc3949ba59abbe56e057f20f883e', '10356.00', 2),
(29, 'Gross', 'Sven', 'sveniboy', 'e10adc3949ba59abbe56e057f20f883e', '10000.00', 2),
(30, 'Nordmann', 'Chrisitna', 'chris', 'e10adc3949ba59abbe56e057f20f883e', '10000.00', 2);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `aktie`
--
ALTER TABLE `aktie`
  ADD PRIMARY KEY (`aktie_id`), ADD KEY `fk_aktie_user1_idx` (`fk_user`), ADD KEY `fk_aktie_symbol1_idx` (`fk_symbol`);

--
-- Indizes für die Tabelle `auftrag`
--
ALTER TABLE `auftrag`
  ADD PRIMARY KEY (`auftrag_id`), ADD UNIQUE KEY `fk_aktie` (`fk_aktie`), ADD KEY `fk_auftrag_aktie1_idx` (`fk_aktie`);

--
-- Indizes für die Tabelle `rolle`
--
ALTER TABLE `rolle`
  ADD PRIMARY KEY (`rolle_id`), ADD UNIQUE KEY `rolle_UNIQUE` (`rolle`);

--
-- Indizes für die Tabelle `symbol`
--
ALTER TABLE `symbol`
  ADD PRIMARY KEY (`symbol_id`), ADD UNIQUE KEY `symbol_UNIQUE` (`symbol`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`), ADD UNIQUE KEY `login_UNIQUE` (`login`), ADD KEY `fk_user_rolle1_idx` (`fk_rolle`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `aktie`
--
ALTER TABLE `aktie`
  MODIFY `aktie_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=74;
--
-- AUTO_INCREMENT für Tabelle `auftrag`
--
ALTER TABLE `auftrag`
  MODIFY `auftrag_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=82;
--
-- AUTO_INCREMENT für Tabelle `rolle`
--
ALTER TABLE `rolle`
  MODIFY `rolle_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT für Tabelle `symbol`
--
ALTER TABLE `symbol`
  MODIFY `symbol_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=31;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `aktie`
--
ALTER TABLE `aktie`
ADD CONSTRAINT `fk_aktie_symbol1` FOREIGN KEY (`fk_symbol`) REFERENCES `symbol` (`symbol_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_aktie_user1` FOREIGN KEY (`fk_user`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `auftrag`
--
ALTER TABLE `auftrag`
ADD CONSTRAINT `fk_auftrag_aktie1` FOREIGN KEY (`fk_aktie`) REFERENCES `aktie` (`aktie_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `user`
--
ALTER TABLE `user`
ADD CONSTRAINT `fk_user_rolle1` FOREIGN KEY (`fk_rolle`) REFERENCES `rolle` (`rolle_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
