
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `lms`
--



--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id` varchar(7) NOT NULL,
  `password` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `admin` (`id`, `password`) VALUES


('admin', '1234'),
('zjin10', '1236831');



--
-- Table structure for table `author`
--

CREATE TABLE IF NOT EXISTS `author` (
  `authorid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`authorid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

INSERT INTO `author` (`authorid`, `name`) VALUES
(1, 'A A'),
(2, 'B B'),
(3, 'C C'),
(4, 'D D'),
(5, 'E E'),
(6, 'F F'),
(7, 'G G'),
(8, 'H H'),
(9, 'I I'),
(10, 'J J'),
(11, 'K K'),
(12, 'L L'),
(13, 'M M'),
(14, 'N N'),
(15, 'O O');



--
-- Table structure for table `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `isbn` varchar(11) NOT NULL,
  `bookid` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=186 ;

INSERT INTO `book` (`isbn`, `bookid`) VALUES
('200001', 1),
('200002', 2),
('200002', 3),
('200003', 4),
('200004', 5),
('200004', 6),
('200004', 7),
('200005', 8),
('200005', 9),
('200006', 10),
('200007', 11),
('200007', 12),
('200007', 13),
('200008', 14),
('200008', 15),
('200009', 16),
('200010', 17),
('200010', 18),
('200010', 19),
('200011', 20),
('200011', 21),
('200012', 22),
('200013', 23),
('200013', 24),
('200013', 25),
('200014', 26),
('200014', 27),
('200015', 28),
('200016', 29),
('200016', 30),
('200016', 31),
('200017', 32),
('200017', 33),
('200018', 34),
('200019', 35),
('200019', 36),
('200019', 37),
('200020', 38),
('200020', 39),
('200021', 40),
('200022', 41),
('200022', 42),
('200022', 43),
('200023', 44),
('200023', 45),
('200024', 46),
('200025', 47),
('200025', 48),
('200025', 49),
('200026', 50);



--
-- Table structure for table `bookinfo`
--

CREATE TABLE IF NOT EXISTS `bookinfo` (
  `isbn` varchar(9) NOT NULL,
  `title` varchar(45) NOT NULL,
  `publisherid` int(11) NOT NULL,
  `publicationdate` date NOT NULL,
  `authorid` int(11) NOT NULL,
  PRIMARY KEY (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `bookinfo` (`isbn`, `title`, `publisherid`, `publicationdate`, `authorid`) VALUES

('200001', 'Hush Hush', 5, '1990-11-01', 15),
('200002', 'Big Lake Burning', 4, '1991-04-01', 14),
('200003', 'Jewels and Panties', 3, '1992-01-01', 13),
('200004', 'The New Girl', 2, '1993-03-01', 12),
('200005', 'Beneath the Skin', 1, '1994-12-01', 11),
('200006', 'Love Real Food', 5, '1995-10-01', 10),
('200007', 'Harry Potter', 4, '1996-05-01', 9),
('200008', 'Arcane', 3, '1997-06-01', 8),
('200009', 'Relation', 2, '1998-04-01', 7),
('200010', 'Stand By Me', 1, '1999-04-01', 6),
('200011', 'Life', 5, '2000-10-01', 5),
('200012', 'Five Unforgivable Things', 4, '2018-10-01', 4),
('200013', 'A Long Way From Home', 3, '2017-11-01', 3),
('200014', 'The Dispalced', 2, '2016-01-01', 2),
('200015', 'LL Story', 1, '2015-12-01', 1),
('200016', 'The Evil in the Tower', 5, '2014-11-01', 2),
('200017', 'Two Tower', 4, '2013-11-01', 3),
('200018', 'Skyward', 3, '2012-11-01', 4),
('200019', 'Thin Air', 2, '2011-11-01', 5),
('200020', 'Before the Broken Star', 1, '2010-09-01', 6),
('200021', 'Price of Paradise', 5, '2001-10-01', 7),
('200022', 'Deer Camp', 4, '2002-05-01', 8),
('200023', 'The Apology', 3, '2003-06-01', 9),
('200024', 'A place for us', 2, '2004-12-01', 10),
('200025', 'Becoming', 1, '2005-04-01', 11),
('200026', 'A Memoir', 5, '2006-05-01', 12);



--
-- Table structure for table `borrow`
--

CREATE TABLE IF NOT EXISTS `borrow` (
  `bookid` int(11) NOT NULL,
  `readerid` int(11) NOT NULL,
  `branchid` int(11) NOT NULL,
  `bdate` date NOT NULL,
  `rdate` date DEFAULT NULL,
  `fine` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`bookid`,`readerid`,`bdate`),
  UNIQUE KEY `bookid` (`bookid`,`bdate`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `borrow` (`bookid`, `readerid`, `branchid`, `bdate`, `rdate`, `fine`) VALUES
(1, 01001, 1, '2018-12-24', '2018-12-28', 0),
(2, 01001, 1, '2019-02-21', '2013-03-10', 0),
(3, 01002, 2, '2019-04-28', NULL, 0),
(4, 01003, 1, '2018-08-24', '2018-09-28', 0),
(5, 01005, 1, '2018-07-13', '2018-09-01', 0),
(6, 01009, 2, '2018-12-24', '2018-12-31', 0),
(7, 03001, 2, '2019-04-24', NULL, 0),
(10, 03005, 1, '2019-01-24', '2019-02-22', 0),
(12, 03005, 1, '2019-02-11', '2019-03-21', 0),
(13, 03004, 2, '2018-02-28', '2018-03-31', 0),
(13, 02001, 2, '2018-12-24', '2019-01-29', 0),
(14, 02010, 1, '2018-01-13', '2018-04-29', 0),
(14, 02003, 1, '2018-09-03', '2019-01-15', 0),
(15, 01006, 1, '2019-04-20', NULL, 0),
(16, 03002, 2, '2018-10-21', '2018-10-31', 0),
(16, 03009, 2, '2019-01-24', '2019-01-29', 0),
(17, 02007, 3, '2018-03-24', '2018-03-29', 0),
(17, 02005, 3, '2018-09-24', '2018-11-02', 0),
(18, 02009, 3, '2018-12-13', '2018-12-12', 0),
(20, 02009, 1, '2018-12-03', '2019-01-01', 0),
(24, 03007, 2, '2019-02-11', '2019-03-21', 0),
(34, 03006, 3, '2019-03-29', '2019-04-03', 0),
(39, 01004, 3, '2019-02-05', '2019-03-24', 0),
(41, 02004, 1, '2019-03-03', '2019-03-12', 0),
(42, 02006, 1, '2019-01-20', '2019-02-28', 0);




--
-- Table structure for table `branch`
--

CREATE TABLE IF NOT EXISTS `branch` (
  `branchid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `location` varchar(25) NOT NULL,
  PRIMARY KEY (`branchid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;


INSERT INTO `branch` (`branchid`, `name`, `location`) VALUES
(1, 'Manhattan', 'Downtown'),
(2, 'Queens', 'Elmhurst'),
(3, 'Brooklyn', 'Grand AV');



--
-- Table structure for table `copy`
--

CREATE TABLE IF NOT EXISTS `copy` (
  `branchid` int(11) NOT NULL,
  `isbn` varchar(25) NOT NULL,
  `numcopy` int(11) NOT NULL,
  PRIMARY KEY (`branchid`,`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



INSERT INTO `copy` (`branchid`, `isbn`, `numcopy`) VALUES
(1, '200001', 1),
(1, '200002', 1),
(1, '200003', 1),
(1, '200004', 1),
(1, '200005', 2),
(1, '200006', 1),
(1, '200007', 2),
(1, '200008', 2),
(1, '200011', 1),
(1, '200013', 1),
(1, '200014', 1),
(1, '200016', 1),
(1, '200017', 1),
(1, '200019', 1),
(1, '200022', 2),
(1, '200025', 1),
(2, '200002', 1),
(2, '200004', 2),
(2, '200007', 1),
(2, '200009', 1),
(2, '200011', 1),
(2, '200013', 1),
(2, '200014', 1),
(2, '200016', 1),
(2, '200017', 1),
(2, '200019', 1),
(2, '200021', 1),
(2, '200022', 1),
(2, '200023', 1),
(2, '200025', 1),
(3, '200010', 3),
(3, '200012', 1),
(3, '200013', 1),
(3, '200015', 1),
(3, '200016', 1),
(3, '200018', 1),
(3, '200019', 1),
(3, '200020', 2),
(3, '200023', 1),
(3, '200024', 1),
(3, '200025', 1),
(3, '200026', 1);



--
-- Table structure for table `location`
--

CREATE TABLE IF NOT EXISTS `location` (
  `bookid` int(11) NOT NULL,
  `branchid` int(11) NOT NULL,
  `position` int(9) NOT NULL,
  PRIMARY KEY (`branchid`,`position`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



INSERT INTO `location` (`bookid`, `branchid`, `position`) VALUES
(1, 1, '01201'),
(2, 1, '01102'),
(3, 2, '01102'),
(4, 1, '01103'),
(5, 1, '01104'),
(6, 2, '01103'),
(7, 2, '01104'),
(8, 1, '01105'),
(9, 1, '01106'),
(10, 1, '01107'),
(11, 1, '01108'),
(12, 1, '01109'),
(13, 2, '01105'),
(14, 1, '01110'),
(15, 1, '02101'),
(16, 2, '01106'),
(17, 3, '01101'),
(18, 3, '01102'),
(19, 3, '01103'),
(20, 1, '02102'),
(21, 2, '01107'),
(22, 3, '01104'),
(23, 1, '02103'),
(24, 2, '01108'),
(25, 3, '01105'),
(26, 1, '02104'),
(27, 2, '01109'),
(28, 3, '01106'),
(29, 1, '02105'),
(30, 2, '01110'),
(31, 3, '01107'),
(32, 1, '02106'),
(33, 2, '01101'),
(34, 3, '01108'),
(35, 1, '02107'),
(36, 2, '02102'),
(37, 3, '01109'),
(38, 3, '01110'),
(39, 3, '02101'),
(40, 2, '02103'),
(41, 1, '02108'),
(42, 1, '02109'),
(43, 2, '02104'),
(44, 2, '02105'),
(45, 3, '02102'),
(46, 3, '02103'),
(47, 1, '02110'),
(48, 2, '02106'),
(49, 3, '02104'),
(50, 3, '02105');




--
-- Table structure for table `publisher`
--

CREATE TABLE IF NOT EXISTS `publisher` (
  `publisherid` int(11) NOT NULL AUTO_INCREMENT,
  `publishername` varchar(20) NOT NULL,
  `location` varchar(10) NOT NULL,
  PRIMARY KEY (`publisherid`),
  KEY `publisherid` (`publisherid`),
  KEY `publisherid_2` (`publisherid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=20 ;


INSERT INTO `publisher` (`publisherid`, `publishername`, `location`) VALUES
(1, 'pub1', 'NY'),
(2, 'pub2', 'NY'),
(3, 'pub3', 'CA'),
(4, 'pub4', 'VA'),
(5, 'pub5', 'TX');



--
-- Table structure for table `reader`
--

CREATE TABLE IF NOT EXISTS `reader` (
  `readerid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone` varchar(12) NOT NULL,
  PRIMARY KEY (`readerid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=99032 ;



INSERT INTO `reader` (`readerid`, `name`, `address`, `phone`) VALUES
(01001, 'A.B', '1234 Ast', '1'),
(01002, 'B.C', '1234 Bst', '2'),
(01003, 'C.D', '1234 Cst', '3'),
(01004, 'D.E', '1234 Dst', '4'),
(01005, 'E.F', '1234 Est', '5'),
(01006, 'F.G', '1234 Fst', '6'),
(01007, 'G.H', '1234 Gst', '7'),
(01008, 'H.I', '1234 Hst', '8'),
(01009, 'I.J', '1234 Ist', '9'),
(01010, 'J.K', '1234 Jst', '0'),
(02001, 'K.L', '1234 Kst', '11'),
(02002, 'L.M', '1234 Lst', '22'),
(02003, 'M.N', '1234 Mst', '33'),
(02004, 'N.O', '1234 Nst', '44'),
(02005, 'O.P', '1234 Ost', '55'),
(02006, 'P.Q', '1234 Pst', '66'),
(02007, 'Q.R', '1234 Qst', '77'),
(02008, 'R.S', '1234 Rst', '88'),
(02009, 'S.T', '1234 Sst', '99'),
(02010, 'T.U', '1234 Tst', '00'),
(03001, 'U.V', '1234 Ust', '111'),
(03002, 'V.W', '1234 Vst', '222'),
(03003, 'W.X', '1234 Wst', '333'),
(03004, 'X.Y', '1234 Xst', '444'),
(03005, 'Y.Z', '1234 Yst', '555'),
(03006, 'B.A', '1234 Zst', '666'),
(03007, 'C.B', '2345 CCst', '777'),
(03008, 'D.C', '2345 DDst', '888'),
(03009, 'E.D', '2345 EEst', '999'),
(03010, 'Y.E', '2345 FFst', '000');




--
-- Table structure for table `reserve`
--

CREATE TABLE IF NOT EXISTS `reserve` (
  `readerid` int(11) NOT NULL,
  `bookid` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`readerid`,`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


INSERT INTO `reserve` (`readerid`, `bookid`, `date`) VALUES
(03010, 8, '2019-05-01'),
(03009, 19, '2019-04-15'),
(03008, 23, '2019-04-28'),
(02008, 31, '2019-04-29'),
(01009, 35, '2019-04-29'),
(01007, 46, '2019-05-01'),
(01010, 50, '2019-05-01');





/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
