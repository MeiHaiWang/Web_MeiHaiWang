-- phpMyAdmin SQL Dump
-- version 4.4.3
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 29, 2015 at 03:52 PM
-- Server version: 5.6.24
-- PHP Version: 5.5.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `MEIHAIWAN_TEST`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_blog`
--

CREATE TABLE IF NOT EXISTS `t_blog` (
  `t_blog_id` int(10) unsigned NOT NULL,
  `t_blog_name` varchar(255) DEFAULT NULL,
  `t_blog_postedDate` datetime DEFAULT NULL,
  `t_blog_detailText` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_claim`
--

CREATE TABLE IF NOT EXISTS `t_claim` (
  `t_claim_id` int(10) unsigned NOT NULL,
  `t_claim_userId` int(10) unsigned DEFAULT NULL,
  `t_claim_salonId` int(10) unsigned DEFAULT NULL,
  `t_claim_reserveId` int(10) unsigned DEFAULT NULL,
  `t_claim_menuId` int(10) unsigned DEFAULT NULL,
  `t_claim_message` text,
  `t_claim_date` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_claim`
--

INSERT INTO `t_claim` (`t_claim_id`, `t_claim_userId`, `t_claim_salonId`, `t_claim_reserveId`, `t_claim_menuId`, `t_claim_message`, `t_claim_date`) VALUES
(1, 1, 1, 1, 1, '1', NULL),
(2, 1, 1, 1, 1, '1', NULL),
(3, 1, 1, 1, 1, '1', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_comment`
--

CREATE TABLE IF NOT EXISTS `t_comment` (
  `t_comment_id` int(10) unsigned NOT NULL,
  `t_comment_userId` int(10) unsigned DEFAULT NULL,
  `t_comment_message` text,
  `t_comment_reviewId` int(10) unsigned DEFAULT NULL,
  `t_comment_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_coupon`
--

CREATE TABLE IF NOT EXISTS `t_coupon` (
  `t_coupon_Id` int(10) unsigned NOT NULL,
  `t_coupon_name` varchar(255) DEFAULT NULL,
  `t_coupon_couponKindId` int(10) unsigned DEFAULT NULL,
  `t_coupon_detailText` varchar(255) DEFAULT NULL,
  `t_coupon_useCondition` varchar(255) DEFAULT NULL,
  `t_coupon_imagePath` varchar(255) DEFAULT NULL,
  `t_coupon_price` int(10) unsigned DEFAULT NULL,
  `t_coupon_deadLine` varchar(255) DEFAULT NULL,
  `t_coupon_isFirstFlag` int(10) unsigned DEFAULT '0',
  `t_coupon_presentationCondition` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_evaluation`
--

CREATE TABLE IF NOT EXISTS `t_evaluation` (
  `t_evaluation_evaluationId` int(10) unsigned NOT NULL,
  `t_evaluation_name` varchar(255) DEFAULT NULL,
  `t_evaluation_point` int(10) unsigned DEFAULT '0',
  `t_evaluation_userId` int(10) unsigned DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_hairSalonMaster`
--

CREATE TABLE IF NOT EXISTS `t_hairSalonMaster` (
  `t_hairSalonMaster_salonId` int(10) unsigned NOT NULL,
  `t_hairSalonMaster_name` varchar(255) DEFAULT NULL,
  `t_hairSalonMaster_viewNumber` int(10) unsigned DEFAULT NULL,
  `t_hairSalonMaster_goodNumber` int(10) unsigned DEFAULT NULL,
  `t_hairSalonMaster_displayOrder` int(10) unsigned DEFAULT NULL,
  `t_hairSalonMaster_areaId` varchar(255) DEFAULT NULL,
  `t_hairSalonMaster_menuId` text,
  `t_hairSalonMaster_disableFlag` int(10) unsigned DEFAULT '0',
  `t_hairSalonMaster_detailText` text,
  `t_hairSalonMaster_couponId` text,
  `t_hairSalonMaster_stylistId` text,
  `t_hairSalonMaster_blogId` text,
  `t_hairSalonMaster_salonImagePath` text,
  `t_hairSalonMaster_reviewId` text,
  `t_hairSalonMaster_hairStyleId` text,
  `t_hairSalonMaster_contactUserName` varchar(255) DEFAULT NULL,
  `t_hairSalonMaster_address` varchar(255) DEFAULT NULL,
  `t_hairSalonMaster_phoneNumber` varchar(255) DEFAULT NULL,
  `t_hairSalonMaster_mail` varchar(128) DEFAULT NULL,
  `t_hairSalonMaster_passward` varchar(45) DEFAULT NULL,
  `t_hairSalonMaster_openTime` varchar(25) DEFAULT NULL,
  `t_hairSalonMaster_closeTime` varchar(25) DEFAULT NULL,
  `t_hairSalonMaster_closeDay` varchar(512) DEFAULT NULL,
  `t_hairSalonMaster_creditAvailable` int(10) unsigned DEFAULT NULL,
  `t_hairSalonMaster_carParkAvailable` int(10) unsigned DEFAULT NULL,
  `t_hairSalonMaster_mapUrl` varchar(255) DEFAULT NULL,
  `t_hairSalonMaster_mapImagePath` varchar(255) DEFAULT NULL,
  `t_hairSalonMaster_message` text,
  `t_hairSalonMaster_mapLatitude` double unsigned DEFAULT '0',
  `t_hairSalonMaster_mapLongitude` double unsigned DEFAULT '0',
  `t_hairSalonMaster_mapInfoText` text,
  `t_hairSalonMaster_availableCountryId` text,
  `t_hairSalonMaster_favoriteNumber` int(10) unsigned DEFAULT '0',
  `t_hairSalonMaster_isNetReservation` int(10) unsigned DEFAULT '1',
  `t_hairSalonMaster_searchConditionId` text,
  `t_hairSalonMaster_reservationId` varchar(1024) DEFAULT NULL,
  `t_hairSalonMaster_seatId` varchar(256) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_hairSalonMaster`
--

INSERT INTO `t_hairSalonMaster` (`t_hairSalonMaster_salonId`, `t_hairSalonMaster_name`, `t_hairSalonMaster_viewNumber`, `t_hairSalonMaster_goodNumber`, `t_hairSalonMaster_displayOrder`, `t_hairSalonMaster_areaId`, `t_hairSalonMaster_menuId`, `t_hairSalonMaster_disableFlag`, `t_hairSalonMaster_detailText`, `t_hairSalonMaster_couponId`, `t_hairSalonMaster_stylistId`, `t_hairSalonMaster_blogId`, `t_hairSalonMaster_salonImagePath`, `t_hairSalonMaster_reviewId`, `t_hairSalonMaster_hairStyleId`, `t_hairSalonMaster_contactUserName`, `t_hairSalonMaster_address`, `t_hairSalonMaster_phoneNumber`, `t_hairSalonMaster_mail`, `t_hairSalonMaster_passward`, `t_hairSalonMaster_openTime`, `t_hairSalonMaster_closeTime`, `t_hairSalonMaster_closeDay`, `t_hairSalonMaster_creditAvailable`, `t_hairSalonMaster_carParkAvailable`, `t_hairSalonMaster_mapUrl`, `t_hairSalonMaster_mapImagePath`, `t_hairSalonMaster_message`, `t_hairSalonMaster_mapLatitude`, `t_hairSalonMaster_mapLongitude`, `t_hairSalonMaster_mapInfoText`, `t_hairSalonMaster_availableCountryId`, `t_hairSalonMaster_favoriteNumber`, `t_hairSalonMaster_isNetReservation`, `t_hairSalonMaster_searchConditionId`, `t_hairSalonMaster_reservationId`, `t_hairSalonMaster_seatId`) VALUES
(1, 'testSalonだよ！', NULL, NULL, NULL, '5', '3,4,5,6,7', 0, '', NULL, '3,1,2,4', NULL, 'http://localhost:8080/MeiHaiWangWebProject/upload/32.jpeg,http://localhost:8080/MeiHaiWangWebProject/upload/31.jpeg,http://localhost:8080/MeiHaiWangWebProject/upload/29.jpeg,img/notfound.jpg', NULL, '2,3,4,2', NULL, NULL, NULL, 'mail1', 'password', '10:00', '10:00', '星期一', 0, 0, 'http;//war', '', NULL, 0, 0, NULL, '-1', 0, 1, '2,16', NULL, NULL),
(2, 'adf', NULL, NULL, NULL, '1', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, NULL, NULL, 0, 1, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_hairStyle`
--

CREATE TABLE IF NOT EXISTS `t_hairStyle` (
  `t_hairStyle_id` int(10) unsigned NOT NULL,
  `t_hairStyle_name` varchar(45) DEFAULT NULL,
  `t_hairStyle_hairTypeId` int(10) unsigned DEFAULT NULL,
  `t_hairStyle_goodNumber` int(10) unsigned DEFAULT '0',
  `t_hairStyle_viewNumber` int(10) unsigned DEFAULT '0',
  `t_hairStyle_stylistId` int(10) unsigned DEFAULT NULL,
  `t_hairStyle_areaId` text,
  `t_hairStyle_imagePath` varchar(255) DEFAULT NULL,
  `t_hairStyle_salonId` int(10) unsigned DEFAULT NULL,
  `t_hairStyle_updateDate` datetime DEFAULT NULL,
  `t_hairStyle_favoriteNumber` int(10) unsigned DEFAULT '0',
  `t_hairStyle_searchConditionId` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_hairStyle`
--

INSERT INTO `t_hairStyle` (`t_hairStyle_id`, `t_hairStyle_name`, `t_hairStyle_hairTypeId`, `t_hairStyle_goodNumber`, `t_hairStyle_viewNumber`, `t_hairStyle_stylistId`, `t_hairStyle_areaId`, `t_hairStyle_imagePath`, `t_hairStyle_salonId`, `t_hairStyle_updateDate`, `t_hairStyle_favoriteNumber`, `t_hairStyle_searchConditionId`) VALUES
(1, 'test1', 1, 0, 0, NULL, NULL, NULL, 1, NULL, 0, '1,2'),
(2, '', 0, 0, 0, 3, '0', 'http://localhost:8080/MeiHaiWangWebProject/upload/19.jpeg,http://localhost:8080/MeiHaiWangWebProject/upload/20.jpeg,http://localhost:8080/MeiHaiWangWebProject/upload/21.jpeg', 1, '1970-01-01 09:00:00', 0, ''),
(3, 'a', 0, 0, 0, 0, '0', 'img/notfound.jpg,img/notfound.jpg,img/notfound.jpg', 1, '1970-01-01 09:00:00', 0, '28,31,35');

-- --------------------------------------------------------

--
-- Table structure for table `t_image`
--

CREATE TABLE IF NOT EXISTS `t_image` (
  `t_image_id` int(10) unsigned NOT NULL,
  `t_image_name` varchar(255) DEFAULT NULL,
  `t_image_filepath` varchar(255) DEFAULT NULL,
  `t_image_size` varchar(45) DEFAULT NULL,
  `t_image_salonId` int(11) DEFAULT NULL,
  `t_image_hash` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_image`
--

INSERT INTO `t_image` (`t_image_id`, `t_image_name`, `t_image_filepath`, `t_image_size`, `t_image_salonId`, `t_image_hash`) VALUES
(1, 'ダウンロード.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/1.png', '8578', 1, 'null'),
(2, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/2.png', '70275', 1, 'null'),
(3, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/3.jpeg', '10132', 1, 'null'),
(4, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/4.jpeg', '10132', 1, 'null'),
(5, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/5.jpeg', '70275', 1, 'null'),
(6, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/6.png', '70275', 1, 'null'),
(7, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/7.jpeg', '70275', 1, 'null'),
(8, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/8.jpeg', '70275', 1, 'null'),
(9, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/9.jpeg', '70275', 1, 'null'),
(10, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/10.jpeg', '10132', 1, 'null'),
(11, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/11.jpeg', '70275', 1, 'null'),
(12, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/12.jpeg', '70275', 1, 'null'),
(13, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/13.jpeg', '70275', 1, 'null'),
(14, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/14.jpeg', '10132', 1, 'null'),
(15, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/15.jpeg', '70275', 1, 'null'),
(16, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/16.jpeg', '10132', 1, 'null'),
(17, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/17.jpeg', '10132', 1, 'null'),
(18, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/18.jpeg', '10132', 1, 'null'),
(19, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/19.jpeg', '10132', 1, 'null'),
(20, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/20.jpeg', '10132', 1, 'null'),
(21, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/21.jpeg', '10132', 1, 'null'),
(22, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/22.jpeg', '10132', 1, 'null'),
(23, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/23.jpeg', '70275', 1, 'null'),
(24, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/24.jpeg', '70275', 1, 'null'),
(25, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/25.jpeg', '10132', 1, 'null'),
(26, 'ダウンロード.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/26.jpeg', '8578', 1, 'null'),
(27, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/27.jpeg', '70275', 1, 'null'),
(28, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/28.jpeg', '70275', 1, 'null'),
(29, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/29.jpeg', '10132', 1, 'null'),
(30, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/30.jpeg', '10132', 1, 'null'),
(31, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/31.jpeg', '70275', 1, 'null'),
(32, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/32.jpeg', '10132', 1, 'null'),
(33, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/33.jpeg', '70275', 1, 'null'),
(34, 'ダウンロード.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/34.jpeg', '8578', 1, 'null'),
(35, 'free.jpeg', 'http://localhost:8080/MeiHaiWangWebProject/upload/35.jpeg', '10132', 1, 'null'),
(36, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/36.jpeg', '70275', -1, 'null'),
(37, 'logo.png', 'http://localhost:8080/MeiHaiWangWebProject/upload/37.jpeg', '70275', 1, 'null');

-- --------------------------------------------------------

--
-- Table structure for table `t_masterArea`
--

CREATE TABLE IF NOT EXISTS `t_masterArea` (
  `t_area_areaId` int(10) unsigned NOT NULL,
  `t_area_areaName` varchar(45) DEFAULT NULL,
  `t_area_level` int(10) unsigned DEFAULT NULL,
  `t_area_countryId` int(10) unsigned DEFAULT NULL,
  `t_area_isDetailFlag` int(10) unsigned DEFAULT '0',
  `t_area_parentAreaId` int(10) unsigned DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_masterArea`
--

INSERT INTO `t_masterArea` (`t_area_areaId`, `t_area_areaName`, `t_area_level`, `t_area_countryId`, `t_area_isDetailFlag`, `t_area_parentAreaId`) VALUES
(1, '北京', 0, 1, 0, 0),
(2, '五道口', 1, 1, 0, 1),
(3, '万寿寺', 1, 1, 0, 1),
(4, '中关村', 1, 1, 0, 1),
(5, '大钟寺', 1, 1, 0, 1),
(6, '西直门', 1, 1, 0, 1),
(7, '朝阳公园', 1, 1, 0, 1),
(8, '三里屯', 1, 1, 0, 1),
(9, '国贸', 1, 1, 0, 1),
(10, '工体', 1, 1, 0, 1),
(11, '大望路', 1, 1, 0, 1),
(12, '百子湾', 1, 1, 0, 1),
(13, '三元桥', 1, 1, 0, 1),
(14, '四惠', 1, 1, 0, 1),
(15, '德胜门', 1, 1, 0, 1),
(16, '复兴门', 1, 1, 0, 1),
(17, '后海', 1, 1, 0, 1),
(18, '新街口', 1, 1, 0, 1),
(19, '西单', 1, 1, 0, 1),
(20, '朝阳门', 1, 1, 0, 1),
(21, '东直门', 1, 1, 0, 1),
(22, '建国门', 1, 1, 0, 1),
(23, '王府井', 1, 1, 0, 1),
(24, '雍和宫', 1, 1, 0, 1),
(25, '东四', 1, 1, 0, 1),
(26, '広州', 0, 1, 0, 0),
(27, '広州', 1, 1, 0, 26);

-- --------------------------------------------------------

--
-- Table structure for table `t_masterCountry`
--

CREATE TABLE IF NOT EXISTS `t_masterCountry` (
  `t_country_countryId` int(11) NOT NULL,
  `t_country_countryName` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_masterCountry`
--

INSERT INTO `t_masterCountry` (`t_country_countryId`, `t_country_countryName`) VALUES
(1, '中国');

-- --------------------------------------------------------

--
-- Table structure for table `t_masterCouponKind`
--

CREATE TABLE IF NOT EXISTS `t_masterCouponKind` (
  `t_couponKind_id` int(10) unsigned NOT NULL,
  `t_couponKind_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_masterCouponKind`
--

INSERT INTO `t_masterCouponKind` (`t_couponKind_id`, `t_couponKind_name`) VALUES
(1, '单点优惠'),
(2, '套餐优惠	'),
(3, '新顾客优惠'),
(4, '常客优惠'),
(5, '儿童优惠'),
(6, '学生优惠');

-- --------------------------------------------------------

--
-- Table structure for table `t_masterHairType`
--

CREATE TABLE IF NOT EXISTS `t_masterHairType` (
  `t_hairType_id` int(10) unsigned NOT NULL,
  `t_hairType_name` varchar(45) DEFAULT NULL,
  `t_hairType_sex` varchar(12) DEFAULT NULL,
  `t_hairType_ImagePath` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_masterMenuCategory`
--

CREATE TABLE IF NOT EXISTS `t_masterMenuCategory` (
  `t_menuCategory_categoryId` int(10) unsigned NOT NULL,
  `t_menuCategory_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_masterMenuCategory`
--

INSERT INTO `t_masterMenuCategory` (`t_menuCategory_categoryId`, `t_menuCategory_name`) VALUES
(1, '剪发'),
(2, '烫发'),
(3, '染发'),
(4, '洗发'),
(5, '洗头按摩'),
(6, '护发'),
(7, '套餐'),
(8, '接发'),
(9, '睫毛扩展'),
(10, '化妆'),
(11, '其他');

-- --------------------------------------------------------

--
-- Table structure for table `t_masterNews`
--

CREATE TABLE IF NOT EXISTS `t_masterNews` (
  `t_masterNewsId` int(10) unsigned NOT NULL,
  `t_masterNewsName` varchar(255) DEFAULT NULL,
  `t_masterNewImagePath` varchar(255) DEFAULT NULL,
  `t_masterNewsURL` varchar(255) DEFAULT NULL,
  `t_masterNewsUpdateDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_masterRecommend`
--

CREATE TABLE IF NOT EXISTS `t_masterRecommend` (
  `t_masterRecommend_Id` int(11) NOT NULL,
  `t_masterRecommend_salonId` int(11) DEFAULT '-1',
  `t_masterRecommend_hairStyleId` int(11) DEFAULT '-1',
  `t_masterRecommend_type` varchar(512) DEFAULT NULL,
  `t_masterRecommend_updateDate` datetime DEFAULT NULL,
  `t_masterRecommend_displayOrder` int(10) unsigned DEFAULT NULL,
  `t_masterRecommend_stylistId` int(11) DEFAULT '-1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_masterReservation`
--

CREATE TABLE IF NOT EXISTS `t_masterReservation` (
  `t_masterReservation_id` int(10) unsigned NOT NULL,
  `t_masterReservation_userId` int(10) unsigned DEFAULT NULL,
  `t_masterReservation_salonId` int(10) unsigned DEFAULT NULL,
  `t_masterReservation_stylistId` int(10) unsigned DEFAULT NULL,
  `t_masterReservation_Date` datetime DEFAULT NULL,
  `t_masterReservation_isFinished` int(10) unsigned DEFAULT '0',
  `t_masterReservation_isCanceled` int(10) unsigned DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_masterSearchCondition`
--

CREATE TABLE IF NOT EXISTS `t_masterSearchCondition` (
  `t_masterSearchCondition_id` int(10) unsigned NOT NULL,
  `t_masterSearchCondition_name` varchar(255) DEFAULT NULL,
  `t_masterSearchCondition_titleId` int(10) unsigned DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_masterSearchCondition`
--

INSERT INTO `t_masterSearchCondition` (`t_masterSearchCondition_id`, `t_masterSearchCondition_name`, `t_masterSearchCondition_titleId`) VALUES
(1, '日语OK', 1),
(2, '韩语OK', 1),
(3, '英语OK', 1),
(4, '惠赠软饮料', 1),
(5, '招募发型模特儿', 1),
(6, '完全预定制', 1),
(7, '上门服务', 1),
(8, '学生优惠', 1),
(9, '可带婴儿', 1),
(10, '优惠券', 1),
(11, '受理晚20点以后的预约', 1),
(12, '有停车位', 2),
(13, '有单人包间', 2),
(14, '有无线Wi-Fi', 2),
(15, '有15席以上座位', 2),
(16, '美发器材最新完备', 2),
(17, '男士', 3),
(18, '女士', 3),
(19, '短发', 4),
(20, '中发', 4),
(21, '中长发', 4),
(22, '长发', 4),
(23, '自然', 5),
(24, '可爱', 5),
(25, 'Feminine', 5),
(26, '性感', 5),
(27, '豪华', 5),
(28, '男士', 6),
(29, '女士', 6),
(30, '特短发', 7),
(31, '短发', 7),
(32, '中发', 7),
(33, '长发', 7),
(34, '酷', 8),
(35, '野生样式', 8),
(36, '街头样式', 8),
(37, '商务样式', 8);

-- --------------------------------------------------------

--
-- Table structure for table `t_masterSearchConditionTitle`
--

CREATE TABLE IF NOT EXISTS `t_masterSearchConditionTitle` (
  `t_masterSearchConditionTitle_id` int(10) unsigned NOT NULL,
  `t_masterSearchConditionTitle_name` varchar(255) DEFAULT NULL,
  `t_masterSearchConditionTitle_typeId` int(10) unsigned NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_masterSearchConditionTitle`
--

INSERT INTO `t_masterSearchConditionTitle` (`t_masterSearchConditionTitle_id`, `t_masterSearchConditionTitle_name`, `t_masterSearchConditionTitle_typeId`) VALUES
(1, '服务', 1),
(2, '设备', 1),
(3, '性别', 2),
(4, '长短', 2),
(5, '印象', 2),
(6, '性别', 3),
(7, '长短', 3),
(8, '印象', 3);

-- --------------------------------------------------------

--
-- Table structure for table `t_masterSearchConditionType`
--

CREATE TABLE IF NOT EXISTS `t_masterSearchConditionType` (
  `t_masterSearchConditionType_id` int(10) unsigned NOT NULL,
  `t_masterSearchConditionType_name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_masterSearchConditionType`
--

INSERT INTO `t_masterSearchConditionType` (`t_masterSearchConditionType_id`, `t_masterSearchConditionType_name`) VALUES
(1, 'サロン検索条件'),
(2, '女性用ヘアスタイル検索条件'),
(3, '男性用ヘアスタイル検索条件');

-- --------------------------------------------------------

--
-- Table structure for table `t_menu`
--

CREATE TABLE IF NOT EXISTS `t_menu` (
  `t_menu_menuId` int(10) unsigned NOT NULL,
  `t_menu_name` varchar(255) DEFAULT NULL,
  `t_menu_price` int(10) unsigned DEFAULT NULL,
  `t_menu_categoryId` int(10) unsigned DEFAULT NULL,
  `t_menu_detailText` text,
  `t_menu_imagePath` varchar(255) DEFAULT NULL,
  `t_menu_time` varchar(45) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_menu`
--

INSERT INTO `t_menu` (`t_menu_menuId`, `t_menu_name`, `t_menu_price`, `t_menu_categoryId`, `t_menu_detailText`, `t_menu_imagePath`, `t_menu_time`) VALUES
(1, 'menu1', 1000, 1, NULL, NULL, '60'),
(2, 'sdfg', NULL, 2, NULL, NULL, '60'),
(3, 'menu1', 3000, 1, '', 'img/notfound.jpg', '40'),
(4, 'menu2', 2000, 1, '', 'img/notfound.jpg', '60'),
(5, 'menu3', 1000, 1, '', 'img/notfound.jpg', '30'),
(6, 'menu4', 5000, 2, '', 'img/notfound.jpg', '120'),
(7, 'menu5', 6000, 2, '', 'img/notfound.jpg', '30');

-- --------------------------------------------------------

--
-- Table structure for table `t_reservation`
--

CREATE TABLE IF NOT EXISTS `t_reservation` (
  `t_reservation_id` int(10) unsigned NOT NULL,
  `t_reservation_userId` int(10) unsigned DEFAULT NULL,
  `t_reservation_salonId` int(10) unsigned DEFAULT NULL,
  `t_reservation_stylistId` int(10) unsigned DEFAULT NULL,
  `t_reservation_Date` datetime DEFAULT NULL,
  `t_reservation_isFinished` int(10) unsigned DEFAULT '0',
  `t_reservation_menuId` varchar(45) DEFAULT NULL,
  `t_reservation_seatId` int(10) unsigned DEFAULT NULL,
  `t_reservation_memo` varchar(512) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_reservation`
--

INSERT INTO `t_reservation` (`t_reservation_id`, `t_reservation_userId`, `t_reservation_salonId`, `t_reservation_stylistId`, `t_reservation_Date`, `t_reservation_isFinished`, `t_reservation_menuId`, `t_reservation_seatId`, `t_reservation_memo`) VALUES
(1, 1, 1, 1, '2015-09-09 00:00:00', 3, '1', 1, '1'),
(2, 1, 1, 1, '2015-09-19 11:00:00', 0, '1', 1, '1'),
(3, 1, 1, 2, '2015-09-28 10:00:00', 0, '3,5', 1, ''),
(4, 1, 1, 4, '2015-09-28 18:00:00', 0, '4,5', 1, ''),
(5, 5, 1, 3, '2015-09-29 10:00:00', 0, '3,5', 1, ''),
(6, 1, 1, 3, '2015-09-30 10:00:00', 0, '4', 1, ''),
(7, 18, 1, 3, '2015-09-29 15:00:00', 0, '5', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `t_review`
--

CREATE TABLE IF NOT EXISTS `t_review` (
  `t_review_id` int(11) NOT NULL,
  `t_review_userId` int(10) unsigned DEFAULT NULL,
  `t_review_postedDate` datetime DEFAULT NULL,
  `t_review_commentId` text,
  `t_review_text` text,
  `t_review_evaluation_point` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_seat`
--

CREATE TABLE IF NOT EXISTS `t_seat` (
  `t_seat_id` int(10) unsigned NOT NULL,
  `t_seat_name` varchar(128) DEFAULT NULL,
  `t_seat_salonId` int(10) unsigned DEFAULT NULL,
  `t_seat_memo` varchar(256) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_seat`
--

INSERT INTO `t_seat` (`t_seat_id`, `t_seat_name`, `t_seat_salonId`, `t_seat_memo`) VALUES
(1, '1111', 1, '1');

-- --------------------------------------------------------

--
-- Table structure for table `t_stylist`
--

CREATE TABLE IF NOT EXISTS `t_stylist` (
  `t_stylist_Id` int(10) unsigned NOT NULL,
  `t_stylist_name` varchar(255) DEFAULT NULL,
  `t_stylist_sex` int(10) unsigned DEFAULT NULL,
  `t_stylist_detailText` text,
  `t_stylist_userId` int(10) unsigned DEFAULT NULL,
  `t_stylist_imagePath` varchar(255) DEFAULT NULL,
  `t_stylist_position` varchar(45) DEFAULT NULL,
  `t_stylist_message` text,
  `t_stylist_experienceYear` int(10) unsigned DEFAULT NULL,
  `t_stylist_specialMenu` varchar(255) DEFAULT NULL,
  `t_stylist_goodNumber` int(10) unsigned DEFAULT NULL,
  `t_stylist_viewNumber` int(10) unsigned DEFAULT NULL,
  `t_stylist_mail` varchar(128) DEFAULT NULL,
  `t_stylist_phoneNumber` varchar(45) DEFAULT NULL,
  `t_stylist_birth` datetime DEFAULT NULL,
  `t_stylist_menuId` text,
  `t_stylist_hairStyleId` text,
  `t_stylist_salonId` int(10) unsigned DEFAULT NULL,
  `t_stylist_favoriteNumber` int(10) unsigned DEFAULT '0',
  `t_stylist_isNetReservation` int(10) unsigned DEFAULT '1',
  `t_stylist_searchConditionId` text,
  `t_stylist_areaId` text,
  `t_stylist_restday` varchar(45) DEFAULT NULL,
  `t_stylist_resttime` varchar(45) DEFAULT NULL,
  `t_stylist_reservationId` varchar(10000) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_stylist`
--

INSERT INTO `t_stylist` (`t_stylist_Id`, `t_stylist_name`, `t_stylist_sex`, `t_stylist_detailText`, `t_stylist_userId`, `t_stylist_imagePath`, `t_stylist_position`, `t_stylist_message`, `t_stylist_experienceYear`, `t_stylist_specialMenu`, `t_stylist_goodNumber`, `t_stylist_viewNumber`, `t_stylist_mail`, `t_stylist_phoneNumber`, `t_stylist_birth`, `t_stylist_menuId`, `t_stylist_hairStyleId`, `t_stylist_salonId`, `t_stylist_favoriteNumber`, `t_stylist_isNetReservation`, `t_stylist_searchConditionId`, `t_stylist_areaId`, `t_stylist_restday`, `t_stylist_resttime`, `t_stylist_reservationId`) VALUES
(1, 'stylist_name_test', 0, '', 1, 'img/notfound.jpg', '', '', 0, '', 0, 0, '', '060', '2015-01-01 00:00:00', '', '0', 1, 0, 0, 'null', '0', NULL, NULL, NULL),
(2, 'test2', 0, '', 5, 'http://localhost:8080/MeiHaiWangWebProject/upload/35.jpeg', '', '', 0, '', 0, 0, '', '', '2015-01-01 00:00:00', '', '0', 1, 0, 0, 'null', '0', NULL, NULL, NULL),
(3, 'test3', 0, '', 1, 'img/notfound.jpg', '', '', 0, '', 0, 0, 'mail2', '070', '2015-01-01 00:00:00', '', '0', 1, 0, 0, 'null', '0', NULL, NULL, NULL),
(4, 'test4', 0, '', 5, 'img/notfound.jpg', '', '', 0, '', 0, 0, 'amijt', '080', '2015-01-01 00:00:00', '', '0', 1, 0, 0, 'null', '0', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_user`
--

CREATE TABLE IF NOT EXISTS `t_user` (
  `t_user_Id` int(10) unsigned NOT NULL,
  `t_user_disableFlag` int(11) DEFAULT '0',
  `t_user_mail` varchar(255) DEFAULT NULL,
  `t_user_passward` varchar(255) DEFAULT NULL,
  `t_user_cookie` varchar(255) DEFAULT NULL,
  `t_user_imagePath` varchar(255) DEFAULT NULL,
  `t_user_sex` int(10) unsigned DEFAULT NULL,
  `t_user_birth` datetime DEFAULT NULL,
  `t_user_name` varchar(255) DEFAULT NULL,
  `t_user_favoriteSalonId` text,
  `t_user_favoriteStylistId` text,
  `t_user_latestViewSalonId` text,
  `t_user_latestViewStylistId` text,
  `t_user_favoriteHairStyleId` text,
  `t_user_latestViewHairStyleId` text,
  `t_user_point` int(10) unsigned DEFAULT '0',
  `t_user_historySalonId` int(10) unsigned DEFAULT NULL,
  `t_user_MasterSalonId` int(10) unsigned DEFAULT NULL,
  `t_user_tel` varchar(255) DEFAULT NULL,
  `t_user_reservationId` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `t_user`
--

INSERT INTO `t_user` (`t_user_Id`, `t_user_disableFlag`, `t_user_mail`, `t_user_passward`, `t_user_cookie`, `t_user_imagePath`, `t_user_sex`, `t_user_birth`, `t_user_name`, `t_user_favoriteSalonId`, `t_user_favoriteStylistId`, `t_user_latestViewSalonId`, `t_user_latestViewStylistId`, `t_user_favoriteHairStyleId`, `t_user_latestViewHairStyleId`, `t_user_point`, `t_user_historySalonId`, `t_user_MasterSalonId`, `t_user_tel`, `t_user_reservationId`) VALUES
(1, 0, '', '0000', '', '', 0, '1989-01-01 00:00:00', 'name1', '', '', '', '', '', '', 0, 0, 0, '060', NULL),
(2, 0, 'mail2', '0000', '', 'img/notfound.jpg', 0, '2015-01-01 00:00:00', 'test', '', '', '', '', '', '', 0, 0, 1, NULL, NULL),
(3, 0, 'mailer', '0000', '', 'img/notfound.jpg', 0, '2015-05-01 00:00:00', 'test1', '', '', '', '', '', '', 0, 0, 1, NULL, NULL),
(4, 0, 'maille', '0000', '', 'img/notfound.jpg', 0, '2015-03-01 00:00:00', 'test1', '', '', '', '', '', '', 0, 0, 1, NULL, NULL),
(5, 0, 'amijt', '0000', '', '', 1, '2015-01-01 00:00:00', 'test', '', '', '', '', '', '', 0, 0, 0, '080', NULL),
(11, 0, '050', '', '', '', 1, '1995-01-01 00:00:00', 'name4', '', '', '', '', '', '', 0, 0, 0, '050', NULL),
(12, 0, '040', '', '', '', 1, '1985-01-01 00:00:00', 'name5', '', '', '', '', '', '', 0, 0, 0, '040', NULL),
(15, 0, '040-', '', '', '', 1, '1985-01-01 00:00:00', 'name5', '', '', '', '', '', '', 0, 0, 0, '040-', NULL),
(17, 0, '010', '', '', '', 1, '1985-01-01 00:00:00', 'name5', '', '', '', '', '', '', 0, 0, 0, '010', NULL),
(18, 0, '020', '', '', '', 1, '1985-01-01 00:00:00', 'name5', '', '', '', '', '', '', 0, 0, 0, '020', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_blog`
--
ALTER TABLE `t_blog`
  ADD PRIMARY KEY (`t_blog_id`),
  ADD UNIQUE KEY `t_blog_id_UNIQUE` (`t_blog_id`);

--
-- Indexes for table `t_claim`
--
ALTER TABLE `t_claim`
  ADD PRIMARY KEY (`t_claim_id`);

--
-- Indexes for table `t_comment`
--
ALTER TABLE `t_comment`
  ADD PRIMARY KEY (`t_comment_id`),
  ADD UNIQUE KEY `t_comment_id_UNIQUE` (`t_comment_id`);

--
-- Indexes for table `t_coupon`
--
ALTER TABLE `t_coupon`
  ADD PRIMARY KEY (`t_coupon_Id`),
  ADD UNIQUE KEY `t_coupon_couponId_UNIQUE` (`t_coupon_Id`);

--
-- Indexes for table `t_evaluation`
--
ALTER TABLE `t_evaluation`
  ADD PRIMARY KEY (`t_evaluation_evaluationId`),
  ADD UNIQUE KEY `t_evaluation_evaluationId_UNIQUE` (`t_evaluation_evaluationId`);

--
-- Indexes for table `t_hairSalonMaster`
--
ALTER TABLE `t_hairSalonMaster`
  ADD PRIMARY KEY (`t_hairSalonMaster_salonId`),
  ADD UNIQUE KEY `t_hairSalonMaster_salonId_UNIQUE` (`t_hairSalonMaster_salonId`),
  ADD UNIQUE KEY `t_hairSalonMaster_mail_UNIQUE` (`t_hairSalonMaster_mail`);

--
-- Indexes for table `t_hairStyle`
--
ALTER TABLE `t_hairStyle`
  ADD PRIMARY KEY (`t_hairStyle_id`),
  ADD UNIQUE KEY `t_hairStyle_id_UNIQUE` (`t_hairStyle_id`);

--
-- Indexes for table `t_image`
--
ALTER TABLE `t_image`
  ADD PRIMARY KEY (`t_image_id`),
  ADD UNIQUE KEY `t_image_id_UNIQUE` (`t_image_id`);

--
-- Indexes for table `t_masterArea`
--
ALTER TABLE `t_masterArea`
  ADD PRIMARY KEY (`t_area_areaId`),
  ADD UNIQUE KEY `t_area_areaId_UNIQUE` (`t_area_areaId`);

--
-- Indexes for table `t_masterCountry`
--
ALTER TABLE `t_masterCountry`
  ADD PRIMARY KEY (`t_country_countryId`),
  ADD UNIQUE KEY `t_country_countryId_UNIQUE` (`t_country_countryId`);

--
-- Indexes for table `t_masterCouponKind`
--
ALTER TABLE `t_masterCouponKind`
  ADD PRIMARY KEY (`t_couponKind_id`),
  ADD UNIQUE KEY `t_couponKind_id_UNIQUE` (`t_couponKind_id`);

--
-- Indexes for table `t_masterHairType`
--
ALTER TABLE `t_masterHairType`
  ADD PRIMARY KEY (`t_hairType_id`),
  ADD UNIQUE KEY `t_hairType_id_UNIQUE` (`t_hairType_id`);

--
-- Indexes for table `t_masterMenuCategory`
--
ALTER TABLE `t_masterMenuCategory`
  ADD PRIMARY KEY (`t_menuCategory_categoryId`),
  ADD UNIQUE KEY `t_menuCategory_categoryId_UNIQUE` (`t_menuCategory_categoryId`);

--
-- Indexes for table `t_masterNews`
--
ALTER TABLE `t_masterNews`
  ADD PRIMARY KEY (`t_masterNewsId`),
  ADD UNIQUE KEY `t_masterNewsId_UNIQUE` (`t_masterNewsId`);

--
-- Indexes for table `t_masterRecommend`
--
ALTER TABLE `t_masterRecommend`
  ADD PRIMARY KEY (`t_masterRecommend_Id`),
  ADD UNIQUE KEY `t_masterRecommendId_UNIQUE` (`t_masterRecommend_Id`);

--
-- Indexes for table `t_masterSearchCondition`
--
ALTER TABLE `t_masterSearchCondition`
  ADD PRIMARY KEY (`t_masterSearchCondition_id`),
  ADD UNIQUE KEY `t_masterSearchCondition_id_UNIQUE` (`t_masterSearchCondition_id`);

--
-- Indexes for table `t_masterSearchConditionTitle`
--
ALTER TABLE `t_masterSearchConditionTitle`
  ADD PRIMARY KEY (`t_masterSearchConditionTitle_id`),
  ADD UNIQUE KEY `t_masterSearchCondition_id_UNIQUE` (`t_masterSearchConditionTitle_id`);

--
-- Indexes for table `t_masterSearchConditionType`
--
ALTER TABLE `t_masterSearchConditionType`
  ADD PRIMARY KEY (`t_masterSearchConditionType_id`),
  ADD UNIQUE KEY `t_masterSearchConditionType_id_UNIQUE` (`t_masterSearchConditionType_id`),
  ADD UNIQUE KEY `t_masterSearchConditionType_name_UNIQUE` (`t_masterSearchConditionType_name`);

--
-- Indexes for table `t_menu`
--
ALTER TABLE `t_menu`
  ADD PRIMARY KEY (`t_menu_menuId`),
  ADD UNIQUE KEY `t_menu_menuId_UNIQUE` (`t_menu_menuId`);

--
-- Indexes for table `t_reservation`
--
ALTER TABLE `t_reservation`
  ADD PRIMARY KEY (`t_reservation_id`),
  ADD UNIQUE KEY `t_masterReservation_id_UNIQUE` (`t_reservation_id`);

--
-- Indexes for table `t_review`
--
ALTER TABLE `t_review`
  ADD PRIMARY KEY (`t_review_id`),
  ADD UNIQUE KEY `t_review_id_UNIQUE` (`t_review_id`);

--
-- Indexes for table `t_seat`
--
ALTER TABLE `t_seat`
  ADD PRIMARY KEY (`t_seat_id`),
  ADD UNIQUE KEY `t_seat_id_UNIQUE` (`t_seat_id`);

--
-- Indexes for table `t_stylist`
--
ALTER TABLE `t_stylist`
  ADD PRIMARY KEY (`t_stylist_Id`),
  ADD UNIQUE KEY `t_stylist_stylistId_UNIQUE` (`t_stylist_Id`);

--
-- Indexes for table `t_user`
--
ALTER TABLE `t_user`
  ADD PRIMARY KEY (`t_user_Id`),
  ADD UNIQUE KEY `t_user_userId_UNIQUE` (`t_user_Id`),
  ADD UNIQUE KEY `t_user_mail_UNIQUE` (`t_user_mail`),
  ADD UNIQUE KEY `t_user_tel_UNIQUE` (`t_user_tel`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_blog`
--
ALTER TABLE `t_blog`
  MODIFY `t_blog_id` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `t_claim`
--
ALTER TABLE `t_claim`
  MODIFY `t_claim_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `t_comment`
--
ALTER TABLE `t_comment`
  MODIFY `t_comment_id` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `t_coupon`
--
ALTER TABLE `t_coupon`
  MODIFY `t_coupon_Id` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `t_evaluation`
--
ALTER TABLE `t_evaluation`
  MODIFY `t_evaluation_evaluationId` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `t_hairSalonMaster`
--
ALTER TABLE `t_hairSalonMaster`
  MODIFY `t_hairSalonMaster_salonId` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `t_hairStyle`
--
ALTER TABLE `t_hairStyle`
  MODIFY `t_hairStyle_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `t_image`
--
ALTER TABLE `t_image`
  MODIFY `t_image_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT for table `t_masterArea`
--
ALTER TABLE `t_masterArea`
  MODIFY `t_area_areaId` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=28;
--
-- AUTO_INCREMENT for table `t_masterCountry`
--
ALTER TABLE `t_masterCountry`
  MODIFY `t_country_countryId` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `t_masterCouponKind`
--
ALTER TABLE `t_masterCouponKind`
  MODIFY `t_couponKind_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `t_masterHairType`
--
ALTER TABLE `t_masterHairType`
  MODIFY `t_hairType_id` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `t_masterMenuCategory`
--
ALTER TABLE `t_masterMenuCategory`
  MODIFY `t_menuCategory_categoryId` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `t_masterNews`
--
ALTER TABLE `t_masterNews`
  MODIFY `t_masterNewsId` int(10) unsigned NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `t_masterSearchCondition`
--
ALTER TABLE `t_masterSearchCondition`
  MODIFY `t_masterSearchCondition_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT for table `t_masterSearchConditionTitle`
--
ALTER TABLE `t_masterSearchConditionTitle`
  MODIFY `t_masterSearchConditionTitle_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `t_masterSearchConditionType`
--
ALTER TABLE `t_masterSearchConditionType`
  MODIFY `t_masterSearchConditionType_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `t_menu`
--
ALTER TABLE `t_menu`
  MODIFY `t_menu_menuId` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `t_reservation`
--
ALTER TABLE `t_reservation`
  MODIFY `t_reservation_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `t_seat`
--
ALTER TABLE `t_seat`
  MODIFY `t_seat_id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `t_stylist`
--
ALTER TABLE `t_stylist`
  MODIFY `t_stylist_Id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `t_user`
--
ALTER TABLE `t_user`
  MODIFY `t_user_Id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
