-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 27, 2013 at 09:37 PM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hms`
--

-- --------------------------------------------------------

--
-- Table structure for table `09108001011_accounts`
--

CREATE TABLE IF NOT EXISTS `09108001011_accounts` (
  `year` int(4) unsigned NOT NULL,
  `jan_mar` varchar(5) DEFAULT 'due',
  `apr_jun` varchar(5) DEFAULT 'due',
  `jul_sep` varchar(5) DEFAULT 'due',
  `oct_dec` varchar(5) DEFAULT 'due',
  `jan` varchar(5) DEFAULT 'due',
  `feb` varchar(5) DEFAULT 'due',
  `mar` varchar(5) DEFAULT 'due',
  `apr` varchar(5) DEFAULT 'due',
  `may` varchar(5) DEFAULT 'due',
  `jun` varchar(5) DEFAULT 'due',
  `jul` varchar(5) DEFAULT 'due',
  `aug` varchar(5) DEFAULT 'due',
  `sep` varchar(5) DEFAULT 'due',
  `oct` varchar(5) DEFAULT 'due',
  `nov` varchar(5) DEFAULT 'due',
  `december` varchar(5) DEFAULT 'due',
  `jan_bill` int(5) DEFAULT '0',
  `feb_bill` int(5) DEFAULT '0',
  `mar_bill` int(5) DEFAULT '0',
  `apr_bill` int(5) DEFAULT '0',
  `may_bill` int(5) DEFAULT '0',
  `jun_bill` int(5) DEFAULT '0',
  `jul_bill` int(5) DEFAULT '0',
  `aug_bill` int(5) DEFAULT '0',
  `sep_bill` int(5) DEFAULT '0',
  `oct_bill` int(5) DEFAULT '0',
  `nov_bill` int(5) DEFAULT '0',
  `dec_bill` int(5) DEFAULT '0',
  PRIMARY KEY (`year`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `09108001011_accounts`
--

INSERT INTO `09108001011_accounts` (`year`, `jan_mar`, `apr_jun`, `jul_sep`, `oct_dec`, `jan`, `feb`, `mar`, `apr`, `may`, `jun`, `jul`, `aug`, `sep`, `oct`, `nov`, `december`, `jan_bill`, `feb_bill`, `mar_bill`, `apr_bill`, `may_bill`, `jun_bill`, `jul_bill`, `aug_bill`, `sep_bill`, `oct_bill`, `nov_bill`, `dec_bill`) VALUES
(2013, 'due', 'paid', 'due', 'due', 'due', 'due', 'due', 'paid', 'paid', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `09108001011_meals`
--

CREATE TABLE IF NOT EXISTS `09108001011_meals` (
  `day` int(2) unsigned NOT NULL,
  `meal` varchar(3) DEFAULT 'on',
  `veg` varchar(3) NOT NULL DEFAULT 'yes',
  PRIMARY KEY (`day`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `09108001011_meals`
--

INSERT INTO `09108001011_meals` (`day`, `meal`, `veg`) VALUES
(1, 'on', 'yes'),
(2, 'on', 'yes'),
(3, 'on', 'yes'),
(4, 'on', 'yes'),
(5, 'on', 'yes'),
(6, 'on', 'yes'),
(7, 'on', 'yes'),
(8, 'on', 'yes'),
(9, 'on', 'yes'),
(10, 'on', 'yes'),
(11, 'on', 'yes'),
(12, 'on', 'yes'),
(13, 'on', 'yes'),
(14, 'on', 'yes'),
(15, 'on', 'yes'),
(16, 'on', 'yes'),
(17, 'on', 'yes'),
(18, 'on', 'yes'),
(19, 'on', 'yes'),
(20, 'on', 'yes'),
(21, 'on', 'yes'),
(22, 'on', 'yes'),
(23, 'on', 'yes'),
(24, 'on', 'yes'),
(25, 'on', 'yes'),
(26, 'on', 'yes'),
(27, 'on', 'yes'),
(28, 'on', 'yes'),
(29, 'on', 'yes'),
(30, 'on', 'yes'),
(31, 'on', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `09108001049_accounts`
--

CREATE TABLE IF NOT EXISTS `09108001049_accounts` (
  `year` int(4) unsigned NOT NULL,
  `jan_mar` varchar(5) DEFAULT 'due',
  `apr_jun` varchar(5) DEFAULT 'due',
  `jul_sep` varchar(5) DEFAULT 'due',
  `oct_dec` varchar(5) DEFAULT 'due',
  `jan` varchar(5) DEFAULT 'due',
  `feb` varchar(5) DEFAULT 'due',
  `mar` varchar(5) DEFAULT 'due',
  `apr` varchar(5) DEFAULT 'due',
  `may` varchar(5) DEFAULT 'due',
  `jun` varchar(5) DEFAULT 'due',
  `jul` varchar(5) DEFAULT 'due',
  `aug` varchar(5) DEFAULT 'due',
  `sep` varchar(5) DEFAULT 'due',
  `oct` varchar(5) DEFAULT 'due',
  `nov` varchar(5) DEFAULT 'due',
  `december` varchar(5) DEFAULT 'due',
  `jan_bill` int(5) DEFAULT '0',
  `feb_bill` int(5) DEFAULT '0',
  `mar_bill` int(5) DEFAULT '0',
  `apr_bill` int(5) DEFAULT '0',
  `may_bill` int(5) DEFAULT '0',
  `jun_bill` int(5) DEFAULT '0',
  `jul_bill` int(5) DEFAULT '0',
  `aug_bill` int(5) DEFAULT '0',
  `sep_bill` int(5) DEFAULT '0',
  `oct_bill` int(5) DEFAULT '0',
  `nov_bill` int(5) DEFAULT '0',
  `dec_bill` int(5) DEFAULT '0',
  PRIMARY KEY (`year`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `09108001049_accounts`
--

INSERT INTO `09108001049_accounts` (`year`, `jan_mar`, `apr_jun`, `jul_sep`, `oct_dec`, `jan`, `feb`, `mar`, `apr`, `may`, `jun`, `jul`, `aug`, `sep`, `oct`, `nov`, `december`, `jan_bill`, `feb_bill`, `mar_bill`, `apr_bill`, `may_bill`, `jun_bill`, `jul_bill`, `aug_bill`, `sep_bill`, `oct_bill`, `nov_bill`, `dec_bill`) VALUES
(2013, 'due', 'paid', 'due', 'due', 'due', 'due', 'due', 'paid', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `09108001049_meals`
--

CREATE TABLE IF NOT EXISTS `09108001049_meals` (
  `day` int(2) unsigned NOT NULL,
  `meal` varchar(3) DEFAULT 'on',
  `veg` varchar(3) NOT NULL DEFAULT 'yes',
  PRIMARY KEY (`day`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `09108001049_meals`
--

INSERT INTO `09108001049_meals` (`day`, `meal`, `veg`) VALUES
(1, 'on', 'yes'),
(2, 'on', 'yes'),
(3, 'on', 'yes'),
(4, 'on', 'yes'),
(5, 'on', 'yes'),
(6, 'on', 'yes'),
(7, 'on', 'yes'),
(8, 'on', 'yes'),
(9, 'on', 'yes'),
(10, 'on', 'yes'),
(11, 'on', 'yes'),
(12, 'on', 'yes'),
(13, 'on', 'yes'),
(14, 'on', 'yes'),
(15, 'on', 'yes'),
(16, 'on', 'yes'),
(17, 'on', 'yes'),
(18, 'on', 'yes'),
(19, 'on', 'yes'),
(20, 'on', 'yes'),
(21, 'on', 'yes'),
(22, 'on', 'yes'),
(23, 'on', 'yes'),
(24, 'on', 'yes'),
(25, 'on', 'yes'),
(26, 'on', 'yes'),
(27, 'on', 'yes'),
(28, 'on', 'yes'),
(29, 'on', 'yes'),
(30, 'on', 'yes'),
(31, 'on', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `09108001072_accounts`
--

CREATE TABLE IF NOT EXISTS `09108001072_accounts` (
  `year` int(4) unsigned NOT NULL,
  `jan_mar` varchar(5) DEFAULT 'due',
  `apr_jun` varchar(5) DEFAULT 'due',
  `jul_sep` varchar(5) DEFAULT 'due',
  `oct_dec` varchar(5) DEFAULT 'due',
  `jan` varchar(5) DEFAULT 'due',
  `feb` varchar(5) DEFAULT 'due',
  `mar` varchar(5) DEFAULT 'due',
  `apr` varchar(5) DEFAULT 'due',
  `may` varchar(5) DEFAULT 'due',
  `jun` varchar(5) DEFAULT 'due',
  `jul` varchar(5) DEFAULT 'due',
  `aug` varchar(5) DEFAULT 'due',
  `sep` varchar(5) DEFAULT 'due',
  `oct` varchar(5) DEFAULT 'due',
  `nov` varchar(5) DEFAULT 'due',
  `december` varchar(5) DEFAULT 'due',
  `jan_bill` int(5) DEFAULT '0',
  `feb_bill` int(5) DEFAULT '0',
  `mar_bill` int(5) DEFAULT '0',
  `apr_bill` int(5) DEFAULT '0',
  `may_bill` int(5) DEFAULT '0',
  `jun_bill` int(5) DEFAULT '0',
  `jul_bill` int(5) DEFAULT '0',
  `aug_bill` int(5) DEFAULT '0',
  `sep_bill` int(5) DEFAULT '0',
  `oct_bill` int(5) DEFAULT '0',
  `nov_bill` int(5) DEFAULT '0',
  `dec_bill` int(5) DEFAULT '0',
  PRIMARY KEY (`year`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `09108001072_accounts`
--

INSERT INTO `09108001072_accounts` (`year`, `jan_mar`, `apr_jun`, `jul_sep`, `oct_dec`, `jan`, `feb`, `mar`, `apr`, `may`, `jun`, `jul`, `aug`, `sep`, `oct`, `nov`, `december`, `jan_bill`, `feb_bill`, `mar_bill`, `apr_bill`, `may_bill`, `jun_bill`, `jul_bill`, `aug_bill`, `sep_bill`, `oct_bill`, `nov_bill`, `dec_bill`) VALUES
(2013, 'due', 'paid', 'due', 'due', 'due', 'due', 'due', 'paid', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `09108001072_meals`
--

CREATE TABLE IF NOT EXISTS `09108001072_meals` (
  `day` int(2) unsigned NOT NULL,
  `meal` varchar(3) DEFAULT 'on',
  `veg` varchar(3) NOT NULL DEFAULT 'yes',
  PRIMARY KEY (`day`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `09108001072_meals`
--

INSERT INTO `09108001072_meals` (`day`, `meal`, `veg`) VALUES
(1, 'on', 'yes'),
(2, 'on', 'yes'),
(3, 'on', 'yes'),
(4, 'on', 'yes'),
(5, 'on', 'yes'),
(6, 'on', 'yes'),
(7, 'on', 'yes'),
(8, 'on', 'yes'),
(9, 'on', 'yes'),
(10, 'on', 'yes'),
(11, 'on', 'yes'),
(12, 'on', 'yes'),
(13, 'on', 'yes'),
(14, 'on', 'yes'),
(15, 'on', 'yes'),
(16, 'on', 'yes'),
(17, 'on', 'yes'),
(18, 'on', 'yes'),
(19, 'on', 'yes'),
(20, 'on', 'yes'),
(21, 'on', 'yes'),
(22, 'on', 'yes'),
(23, 'on', 'yes'),
(24, 'on', 'yes'),
(25, 'on', 'yes'),
(26, 'on', 'yes'),
(27, 'on', 'yes'),
(28, 'on', 'yes'),
(29, 'on', 'yes'),
(30, 'on', 'yes'),
(31, 'on', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `09108001094_accounts`
--

CREATE TABLE IF NOT EXISTS `09108001094_accounts` (
  `year` int(4) unsigned NOT NULL,
  `jan_mar` varchar(5) DEFAULT 'due',
  `apr_jun` varchar(5) DEFAULT 'due',
  `jul_sep` varchar(5) DEFAULT 'due',
  `oct_dec` varchar(5) DEFAULT 'due',
  `jan` varchar(5) DEFAULT 'due',
  `feb` varchar(5) DEFAULT 'due',
  `mar` varchar(5) DEFAULT 'due',
  `apr` varchar(5) DEFAULT 'due',
  `may` varchar(5) DEFAULT 'due',
  `jun` varchar(5) DEFAULT 'due',
  `jul` varchar(5) DEFAULT 'due',
  `aug` varchar(5) DEFAULT 'due',
  `sep` varchar(5) DEFAULT 'due',
  `oct` varchar(5) DEFAULT 'due',
  `nov` varchar(5) DEFAULT 'due',
  `december` varchar(5) DEFAULT 'due',
  `jan_bill` int(5) DEFAULT '0',
  `feb_bill` int(5) DEFAULT '0',
  `mar_bill` int(5) DEFAULT '0',
  `apr_bill` int(5) DEFAULT '0',
  `may_bill` int(5) DEFAULT '0',
  `jun_bill` int(5) DEFAULT '0',
  `jul_bill` int(5) DEFAULT '0',
  `aug_bill` int(5) DEFAULT '0',
  `sep_bill` int(5) DEFAULT '0',
  `oct_bill` int(5) DEFAULT '0',
  `nov_bill` int(5) DEFAULT '0',
  `dec_bill` int(5) DEFAULT '0',
  PRIMARY KEY (`year`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `09108001094_accounts`
--

INSERT INTO `09108001094_accounts` (`year`, `jan_mar`, `apr_jun`, `jul_sep`, `oct_dec`, `jan`, `feb`, `mar`, `apr`, `may`, `jun`, `jul`, `aug`, `sep`, `oct`, `nov`, `december`, `jan_bill`, `feb_bill`, `mar_bill`, `apr_bill`, `may_bill`, `jun_bill`, `jul_bill`, `aug_bill`, `sep_bill`, `oct_bill`, `nov_bill`, `dec_bill`) VALUES
(2013, 'due', 'paid', 'paid', 'paid', 'due', 'due', 'due', 'paid', 'paid', 'paid', 'paid', 'paid', 'paid', 'paid', 'due', 'due', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `09108001094_meals`
--

CREATE TABLE IF NOT EXISTS `09108001094_meals` (
  `day` int(2) unsigned NOT NULL,
  `meal` varchar(3) DEFAULT 'on',
  `veg` varchar(3) NOT NULL DEFAULT 'yes',
  PRIMARY KEY (`day`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `09108001094_meals`
--

INSERT INTO `09108001094_meals` (`day`, `meal`, `veg`) VALUES
(1, 'on', 'yes'),
(2, 'on', 'yes'),
(3, 'on', 'yes'),
(4, 'on', 'yes'),
(5, 'on', 'yes'),
(6, 'on', 'yes'),
(7, 'on', 'yes'),
(8, 'on', 'yes'),
(9, 'on', 'yes'),
(10, 'on', 'yes'),
(11, 'on', 'yes'),
(12, 'on', 'yes'),
(13, 'on', 'yes'),
(14, 'on', 'yes'),
(15, 'on', 'yes'),
(16, 'on', 'yes'),
(17, 'on', 'yes'),
(18, 'on', 'yes'),
(19, 'on', 'yes'),
(20, 'on', 'yes'),
(21, 'on', 'yes'),
(22, 'on', 'yes'),
(23, 'on', 'yes'),
(24, 'on', 'yes'),
(25, 'on', 'yes'),
(26, 'on', 'yes'),
(27, 'on', 'yes'),
(28, 'on', 'yes'),
(29, 'on', 'yes'),
(30, 'on', 'yes'),
(31, 'on', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `09108001099_accounts`
--

CREATE TABLE IF NOT EXISTS `09108001099_accounts` (
  `year` int(4) unsigned NOT NULL,
  `jan_mar` varchar(5) DEFAULT 'due',
  `apr_jun` varchar(5) DEFAULT 'due',
  `jul_sep` varchar(5) DEFAULT 'due',
  `oct_dec` varchar(5) DEFAULT 'due',
  `jan` varchar(5) DEFAULT 'due',
  `feb` varchar(5) DEFAULT 'due',
  `mar` varchar(5) DEFAULT 'due',
  `apr` varchar(5) DEFAULT 'due',
  `may` varchar(5) DEFAULT 'due',
  `jun` varchar(5) DEFAULT 'due',
  `jul` varchar(5) DEFAULT 'due',
  `aug` varchar(5) DEFAULT 'due',
  `sep` varchar(5) DEFAULT 'due',
  `oct` varchar(5) DEFAULT 'due',
  `nov` varchar(5) DEFAULT 'due',
  `december` varchar(5) DEFAULT 'due',
  `jan_bill` int(5) DEFAULT '0',
  `feb_bill` int(5) DEFAULT '0',
  `mar_bill` int(5) DEFAULT '0',
  `apr_bill` int(5) DEFAULT '0',
  `may_bill` int(5) DEFAULT '0',
  `jun_bill` int(5) DEFAULT '0',
  `jul_bill` int(5) DEFAULT '0',
  `aug_bill` int(5) DEFAULT '0',
  `sep_bill` int(5) DEFAULT '0',
  `oct_bill` int(5) DEFAULT '0',
  `nov_bill` int(5) DEFAULT '0',
  `dec_bill` int(5) DEFAULT '0',
  PRIMARY KEY (`year`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `09108001099_accounts`
--

INSERT INTO `09108001099_accounts` (`year`, `jan_mar`, `apr_jun`, `jul_sep`, `oct_dec`, `jan`, `feb`, `mar`, `apr`, `may`, `jun`, `jul`, `aug`, `sep`, `oct`, `nov`, `december`, `jan_bill`, `feb_bill`, `mar_bill`, `apr_bill`, `may_bill`, `jun_bill`, `jul_bill`, `aug_bill`, `sep_bill`, `oct_bill`, `nov_bill`, `dec_bill`) VALUES
(2013, 'due', 'paid', 'paid', 'due', 'due', 'due', 'due', 'paid', 'paid', 'paid', 'paid', 'due', 'due', 'due', 'due', 'due', 0, 0, 0, 3000, 3000, 0, 0, 0, 0, 0, 0, 0),
(2012, 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(2011, 'due', 'paid', 'due', 'due', 'due', 'due', 'due', 'paid', 'paid', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `09108001099_meals`
--

CREATE TABLE IF NOT EXISTS `09108001099_meals` (
  `day` int(2) unsigned NOT NULL,
  `meal` varchar(3) DEFAULT 'on',
  `veg` varchar(3) NOT NULL DEFAULT 'yes',
  PRIMARY KEY (`day`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `09108001099_meals`
--

INSERT INTO `09108001099_meals` (`day`, `meal`, `veg`) VALUES
(1, 'on', 'yes'),
(2, 'on', 'yes'),
(3, 'on', 'yes'),
(4, 'on', 'yes'),
(5, 'on', 'yes'),
(6, 'on', 'yes'),
(7, 'on', 'yes'),
(8, 'on', 'yes'),
(9, 'on', 'yes'),
(10, 'on', 'yes'),
(11, 'on', 'yes'),
(12, 'on', 'yes'),
(13, 'on', 'yes'),
(14, 'on', 'yes'),
(15, 'on', 'yes'),
(16, 'on', 'yes'),
(17, 'on', 'yes'),
(18, 'on', 'yes'),
(19, 'on', 'yes'),
(20, 'on', 'yes'),
(21, 'on', 'yes'),
(22, 'on', 'yes'),
(23, 'on', 'yes'),
(24, 'on', 'yes'),
(25, 'on', 'yes'),
(26, 'on', 'yes'),
(27, 'on', 'yes'),
(28, 'on', 'yes'),
(29, 'on', 'yes'),
(30, 'on', 'yes'),
(31, 'on', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `09108002064_accounts`
--

CREATE TABLE IF NOT EXISTS `09108002064_accounts` (
  `year` int(4) unsigned NOT NULL,
  `jan_mar` varchar(5) DEFAULT 'due',
  `apr_jun` varchar(5) DEFAULT 'due',
  `jul_sep` varchar(5) DEFAULT 'due',
  `oct_dec` varchar(5) DEFAULT 'due',
  `jan` varchar(5) DEFAULT 'due',
  `feb` varchar(5) DEFAULT 'due',
  `mar` varchar(5) DEFAULT 'due',
  `apr` varchar(5) DEFAULT 'due',
  `may` varchar(5) DEFAULT 'due',
  `jun` varchar(5) DEFAULT 'due',
  `jul` varchar(5) DEFAULT 'due',
  `aug` varchar(5) DEFAULT 'due',
  `sep` varchar(5) DEFAULT 'due',
  `oct` varchar(5) DEFAULT 'due',
  `nov` varchar(5) DEFAULT 'due',
  `december` varchar(5) DEFAULT 'due',
  `jan_bill` int(5) DEFAULT '0',
  `feb_bill` int(5) DEFAULT '0',
  `mar_bill` int(5) DEFAULT '0',
  `apr_bill` int(5) DEFAULT '0',
  `may_bill` int(5) DEFAULT '0',
  `jun_bill` int(5) DEFAULT '0',
  `jul_bill` int(5) DEFAULT '0',
  `aug_bill` int(5) DEFAULT '0',
  `sep_bill` int(5) DEFAULT '0',
  `oct_bill` int(5) DEFAULT '0',
  `nov_bill` int(5) DEFAULT '0',
  `dec_bill` int(5) DEFAULT '0',
  PRIMARY KEY (`year`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `09108002064_accounts`
--

INSERT INTO `09108002064_accounts` (`year`, `jan_mar`, `apr_jun`, `jul_sep`, `oct_dec`, `jan`, `feb`, `mar`, `apr`, `may`, `jun`, `jul`, `aug`, `sep`, `oct`, `nov`, `december`, `jan_bill`, `feb_bill`, `mar_bill`, `apr_bill`, `may_bill`, `jun_bill`, `jul_bill`, `aug_bill`, `sep_bill`, `oct_bill`, `nov_bill`, `dec_bill`) VALUES
(2013, 'due', 'paid', 'paid', 'due', 'due', 'due', 'due', 'paid', 'paid', 'paid', 'due', 'due', 'due', 'due', 'due', 'due', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(2014, 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(2015, 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(2016, 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 'due', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `09108002064_meals`
--

CREATE TABLE IF NOT EXISTS `09108002064_meals` (
  `day` int(2) unsigned NOT NULL,
  `meal` varchar(3) DEFAULT 'on',
  `veg` varchar(3) NOT NULL DEFAULT 'yes',
  PRIMARY KEY (`day`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `09108002064_meals`
--

INSERT INTO `09108002064_meals` (`day`, `meal`, `veg`) VALUES
(1, 'on', 'yes'),
(2, 'on', 'yes'),
(3, 'on', 'yes'),
(4, 'on', 'yes'),
(5, 'on', 'yes'),
(6, 'on', 'yes'),
(7, 'on', 'yes'),
(8, 'on', 'yes'),
(9, 'on', 'yes'),
(10, 'on', 'yes'),
(11, 'on', 'yes'),
(12, 'on', 'yes'),
(13, 'on', 'yes'),
(14, 'on', 'yes'),
(15, 'on', 'yes'),
(16, 'on', 'yes'),
(17, 'on', 'yes'),
(18, 'on', 'yes'),
(19, 'on', 'yes'),
(20, 'on', 'yes'),
(21, 'on', 'yes'),
(22, 'on', 'yes'),
(23, 'on', 'yes'),
(24, 'on', 'yes'),
(25, 'on', 'yes'),
(26, 'on', 'yes'),
(27, 'on', 'yes'),
(28, 'on', 'yes'),
(29, 'on', 'yes'),
(30, 'on', 'yes'),
(31, 'on', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `admin_id` bigint(12) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` char(41) NOT NULL,
  `session_id` bigint(12) NOT NULL DEFAULT '0',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `username`, `password`, `session_id`) VALUES
(1, 'admin', 'f9d4049dd6a4dc35d40e5265954b2a46', 1286062169);

-- --------------------------------------------------------

--
-- Table structure for table `pb`
--

CREATE TABLE IF NOT EXISTS `pb` (
  `student_id` bigint(12) unsigned NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` char(41) NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pb`
--

INSERT INTO `pb` (`student_id`, `username`, `password`) VALUES
(2, 'v6(E8$oZ', 'y7!A7oC'),
(3, 's8(X1(fP', 'w7@Q8!iE'),
(4, 'p6@R6_zA', 'v2@T7$qS'),
(5, 's3_F3_kK', 'a7(U1)zW'),
(6, 'n2(F8@eW', 'y5$G3@rE'),
(7, 'a8!K4(lM', 'o9$U3@uV');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `room_no` int(3) unsigned NOT NULL,
  `1st_seat1` varchar(12) DEFAULT '',
  `1st_seat2` varchar(12) DEFAULT '',
  `2nd_seat1` varchar(12) DEFAULT '',
  `2nd_seat2` varchar(12) DEFAULT '',
  `3rd_seat1` varchar(12) DEFAULT '',
  `3rd_seat2` varchar(12) DEFAULT '',
  `4th_seat1` varchar(12) DEFAULT '',
  `4th_seat2` varchar(12) DEFAULT '',
  PRIMARY KEY (`room_no`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`room_no`, `1st_seat1`, `1st_seat2`, `2nd_seat1`, `2nd_seat2`, `3rd_seat1`, `3rd_seat2`, `4th_seat1`, `4th_seat2`) VALUES
(1, '', '', '', '', '', '', '09108001099', '09108001011'),
(2, '', '', '', '', '', '', '09108001049', '09108001072'),
(3, '', '', '', '', '', '', '09108001094', '09108002064'),
(4, '', '', '', '', '', '', '', ''),
(5, '', '', '', '', '', '', '', ''),
(6, '', '', '', '', '', '', '', ''),
(7, '', '', '', '', '', '', '', ''),
(8, '', '', '', '', '', '', '', ''),
(9, '', '', '', '', '', '', '', ''),
(10, '', '', '', '', '', '', '', ''),
(11, '', '', '', '', '', '', '', ''),
(12, '', '', '', '', '', '', '', ''),
(13, '', '', '', '', '', '', '', ''),
(14, '', '', '', '', '', '', '', ''),
(15, '', '', '', '', '', '', '', ''),
(16, '', '', '', '', '', '', '', ''),
(17, '', '', '', '', '', '', '', ''),
(18, '', '', '', '', '', '', '', ''),
(19, '', '', '', '', '', '', '', ''),
(20, '', '', '', '', '', '', '', ''),
(21, '', '', '', '', '', '', '', ''),
(22, '', '', '', '', '', '', '', ''),
(23, '', '', '', '', '', '', '', ''),
(24, '', '', '', '', '', '', '', ''),
(25, '', '', '', '', '', '', '', ''),
(26, '', '', '', '', '', '', '', ''),
(27, '', '', '', '', '', '', '', ''),
(28, '', '', '', '', '', '', '', ''),
(29, '', '', '', '', '', '', '', ''),
(30, '', '', '', '', '', '', '', ''),
(31, '', '', '', '', '', '', '', ''),
(32, '', '', '', '', '', '', '', ''),
(33, '', '', '', '', '', '', '', ''),
(34, '', '', '', '', '', '', '', ''),
(35, '', '', '', '', '', '', '', ''),
(36, '', '', '', '', '', '', '', ''),
(37, '', '', '', '', '', '', '', ''),
(38, '', '', '', '', '', '', '', ''),
(39, '', '', '', '', '', '', '', ''),
(40, '', '', '', '', '', '', '', ''),
(41, '', '', '', '', '', '', '', ''),
(42, '', '', '', '', '', '', '', ''),
(43, '', '', '', '', '', '', '', ''),
(44, '', '', '', '', '', '', '', ''),
(45, '', '', '', '', '', '', '', ''),
(46, '', '', '', '', '', '', '', ''),
(47, '', '', '', '', '', '', '', ''),
(48, '', '', '', '', '', '', '', ''),
(49, '', '', '', '', '', '', '', ''),
(50, '', '', '', '', '', '', '', ''),
(51, '', '', '', '', '', '', '', ''),
(52, '', '', '', '', '', '', '', ''),
(53, '', '', '', '', '', '', '', ''),
(54, '', '', '', '', '', '', '', ''),
(55, '', '', '', '', '', '', '', ''),
(56, '', '', '', '', '', '', '', ''),
(57, '', '', '', '', '', '', '', ''),
(58, '', '', '', '', '', '', '', ''),
(59, '', '', '', '', '', '', '', ''),
(60, '', '', '', '', '', '', '', ''),
(61, '', '', '', '', '', '', '', ''),
(62, '', '', '', '', '', '', '', ''),
(63, '', '', '', '', '', '', '', ''),
(64, '', '', '', '', '', '', '', ''),
(65, '', '', '', '', '', '', '', ''),
(66, '', '', '', '', '', '', '', ''),
(67, '', '', '', '', '', '', '', ''),
(68, '', '', '', '', '', '', '', ''),
(69, '', '', '', '', '', '', '', ''),
(70, '', '', '', '', '', '', '', ''),
(71, '', '', '', '', '', '', '', ''),
(72, '', '', '', '', '', '', '', ''),
(73, '', '', '', '', '', '', '', ''),
(74, '', '', '', '', '', '', '', ''),
(75, '', '', '', '', '', '', '', ''),
(76, '', '', '', '', '', '', '', ''),
(77, '', '', '', '', '', '', '', ''),
(78, '', '', '', '', '', '', '', ''),
(79, '', '', '', '', '', '', '', ''),
(80, '', '', '', '', '', '', '', ''),
(81, '', '', '', '', '', '', '', ''),
(82, '', '', '', '', '', '', '', ''),
(83, '', '', '', '', '', '', '', ''),
(84, '', '', '', '', '', '', '', ''),
(85, '', '', '', '', '', '', '', ''),
(86, '', '', '', '', '', '', '', ''),
(87, '', '', '', '', '', '', '', ''),
(88, '', '', '', '', '', '', '', ''),
(89, '', '', '', '', '', '', '', ''),
(90, '', '', '', '', '', '', '', ''),
(91, '', '', '', '', '', '', '', ''),
(92, '', '', '', '', '', '', '', ''),
(93, '', '', '', '', '', '', '', ''),
(94, '', '', '', '', '', '', '', ''),
(95, '', '', '', '', '', '', '', ''),
(96, '', '', '', '', '', '', '', ''),
(97, '', '', '', '', '', '', '', ''),
(98, '', '', '', '', '', '', '', ''),
(99, '', '', '', '', '', '', '', ''),
(100, '', '', '', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `student_id` bigint(12) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` char(41) NOT NULL,
  `pic_name` varchar(30) DEFAULT NULL,
  `session_id` bigint(12) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT NULL,
  `roll_no` varchar(12) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `address1` varchar(200) DEFAULT NULL,
  `address2` varchar(200) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `pincode` varchar(6) DEFAULT NULL,
  `batch` varchar(10) DEFAULT NULL,
  `stream` varchar(5) DEFAULT NULL,
  `room_no` int(3) DEFAULT NULL,
  `allot_date` date DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `roll_no` (`roll_no`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_id`, `username`, `password`, `pic_name`, `session_id`, `name`, `roll_no`, `email`, `address1`, `address2`, `city`, `pincode`, `batch`, `stream`, `room_no`, `allot_date`) VALUES
(1, 'jeet1099', 'a0d542bcc8bcafd12927cff556d47ad1', 'NPP.png', 1325769735, 'Indrajeet Kumar Mishra', '123456', 'demo@gmail.com', 'myplace', '', 'Asansol', '713301', '2009-2013', 'CSE', NULL, NULL),
(2, 'v6(E8$oZ', '95bc4e921e8374b0bfd2d9f2e0b10dd0', '09108001099.jpg', 0, 'Jeet Mishra', '09108001099', 'jeet.indra18@yahoo.com', 'parbelia', '', 'Asansol', '723121', '2009-2013', 'CSE', 1, '2011-04-27'),
(3, 's8(X1(fP', '54fcaf8b99cfcc107aa7a54c8fe04f38', '09108001011.jpg', 0, 'Pritish Tiwari', '09108001011', 'pritish.tiwari@gmail.com', 'Gobindpur', '', 'Asansol', '713305', '2009-2013', 'CSE', 1, '2013-04-27'),
(4, 'p6@R6_zA', '4b97859039d1bc817f3d4f7cd18e4d68', 'NPP.png', 0, 'Sourabh Suman', '09108001049', 'sourabh.suman@rocketmail.com', 'Apkar Garden', 'Asansol', 'Asansol', '713304', '2009-2013', 'CSE', 2, '2013-04-27'),
(5, 's3_F3_kK', '03ddfe331d68e7b53334b7ce635378fb', '09108001072.jpg', 0, 'Amrish Pandey', '09108001072', 'amrish.pandey@gmail.com', 'gobindpur', '', 'Asansol', '713305', '2009-2013', 'CSE', 2, '2013-04-27'),
(6, 'n2(F8@eW', '1cc757a09aefb771d674cf473a7eecb7', '09108001094.jpg', 0, 'Deepak Mahto', '09108001094', 'deepak215@gmail.com', 'Gobindpur', '', 'Asansol', '713305', '2009-2013', 'CSE', 3, '2013-04-27'),
(7, 'a8!K4(lM', '1dc15e6ec0c754c311218ba16fd5bef9', '09108002064.jpg', 0, 'Sandeep Singh', '09108002064', 'sandeep.sing.dua@gmail.com', 'West Apkar Garden', '', 'Asansol', '713304', '2012-2016', 'IT', 3, '2013-04-27');
