-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema MEIHAIWAN_TEST
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `MEIHAIWAN_TEST` ;

-- -----------------------------------------------------
-- Schema MEIHAIWAN_TEST
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `MEIHAIWAN_TEST` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `MEIHAIWAN_TEST` ;

-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_user` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_user` (
  `t_user_Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `t_user_disableFlag` INT NULL DEFAULT 0,
  `t_user_mail` VARCHAR(255) NULL,
  `t_user_passward` VARCHAR(255) NULL,
  `t_user_cookie` VARCHAR(255) NULL,
  `t_user_imagePath` VARCHAR(255) NULL,
  `t_user_sex` INT UNSIGNED NULL,
  `t_user_birth` DATETIME NULL,
  `t_user_name` VARCHAR(255) NULL,
  `t_user_favoriteSalonId` VARCHAR(255) NULL,
  PRIMARY KEY (`t_user_Id`),
  UNIQUE INDEX `t_user_userId_UNIQUE` (`t_user_Id` ASC),
  UNIQUE INDEX `t_user_mail_UNIQUE` (`t_user_mail` ASC),
  UNIQUE INDEX `t_user_passward_UNIQUE` (`t_user_passward` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_hairSalonMaster`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_hairSalonMaster` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_hairSalonMaster` (
  `t_hairSalonMaster_salonId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `t_hairSalonMaster_name` VARCHAR(255) NULL,
  `t_hairSalonMaster_viewNumber` INT UNSIGNED NULL,
  `t_hairSalonMaster_goodNumber` INT UNSIGNED NULL,
  `t_hairSalonMaster_displayOrder` INT UNSIGNED NULL,
  `t_hairSalonMaster_areaId` VARCHAR(255) NULL,
  `t_hairSalonMaster_menuId` TEXT(10000) NULL,
  `t_hairSalonMaster_disableFlag` INT UNSIGNED NULL DEFAULT 0,
  `t_hairSalonMaster_detailText` TEXT(10000) NULL,
  `t_hairSalonMaster_evaluationId` TEXT(10000) NULL,
  `t_hairSalonMaster_couponId` TEXT(10000) NULL,
  `t_hairSalonMaster_stylistId` TEXT(10000) NULL,
  `t_hairSalonMaster_blogId` TEXT(10000) NULL,
  `t_hairSalonMaster_salonImagePath` VARCHAR(255) NULL,
  `t_hairSalonMaster_reviewId` TEXT(10000) NULL,
  `t_hairSalonMaster_hairStyleId` TEXT(10000) NULL,
  `t_hairSalonMaster_contactUserName` VARCHAR(255) NULL,
  `t_hairSalonMaster_address` VARCHAR(255) NULL,
  `t_hairSalonMaster_phoneNumber` VARCHAR(255) NULL,
  `t_hairSalonMaster_mail` VARCHAR(128) NULL,
  `t_hairSalonMaster_passward` VARCHAR(45) NULL,
  `t_hairSalonMaster_openTime` DATETIME NULL,
  `t_hairSalonMaster_closeTime` DATETIME NULL,
  `t_hairSalonMaster_closeDay` VARCHAR(512) NULL,
  `t_hairSalonMaster_creditAvailable` INT UNSIGNED NULL,
  `t_hairSalonMaster_carParkAvailable` INT UNSIGNED NULL,
  `t_hairSalonMaster_japaneseAvailable` INT UNSIGNED NULL,
  `t_hairSalonMaster_mapUrl` VARCHAR(255) NULL,
  `t_hairSalonMaster_mapImagePath` VARCHAR(255) NULL,
  `t_hairSalonMaster_message` TEXT(512) NULL,
  PRIMARY KEY (`t_hairSalonMaster_salonId`),
  UNIQUE INDEX `t_hairSalonMaster_salonId_UNIQUE` (`t_hairSalonMaster_salonId` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_menu` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_menu` (
  `t_menu_menuId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `t_menu_name` VARCHAR(255) NULL,
  `t_menu_price` INT UNSIGNED NULL,
  `t_menu_categoryId` INT UNSIGNED NULL,
  `t_menu_detailText` TEXT(20000) NULL,
  `t_menu_imagePath` VARCHAR(255) NULL,
  PRIMARY KEY (`t_menu_menuId`),
  UNIQUE INDEX `t_menu_menuId_UNIQUE` (`t_menu_menuId` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_masterMenuCategory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_masterMenuCategory` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_masterMenuCategory` (
  `t_menuCategory_categoryId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `t_menuCategory_name` VARCHAR(255) NULL,
  PRIMARY KEY (`t_menuCategory_categoryId`),
  UNIQUE INDEX `t_menuCategory_categoryId_UNIQUE` (`t_menuCategory_categoryId` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_masterArea`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_masterArea` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_masterArea` (
  `t_area_areaId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `t_area_areaName` VARCHAR(45) NULL,
  `t_area_level` INT UNSIGNED NULL,
  `t_area_countryId` INT UNSIGNED NULL,
  PRIMARY KEY (`t_area_areaId`),
  UNIQUE INDEX `t_area_areaId_UNIQUE` (`t_area_areaId` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_masterCountry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_masterCountry` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_masterCountry` (
  `t_country_countryId` INT NOT NULL AUTO_INCREMENT,
  `t_country_countryName` VARCHAR(45) NULL,
  PRIMARY KEY (`t_country_countryId`),
  UNIQUE INDEX `t_country_countryId_UNIQUE` (`t_country_countryId` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_evaluation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_evaluation` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_evaluation` (
  `t_evaluation_evaluationId` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `t_evaluation_name` VARCHAR(255) NULL,
  `t_evaluation_point` INT UNSIGNED NULL DEFAULT 0,
  `t_evaluation_detailText` TEXT(20000) NULL,
  `t_evaluation_userId` INT UNSIGNED NULL,
  PRIMARY KEY (`t_evaluation_evaluationId`),
  UNIQUE INDEX `t_evaluation_evaluationId_UNIQUE` (`t_evaluation_evaluationId` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_coupon`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_coupon` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_coupon` (
  `t_coupon_Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `t_coupon_name` VARCHAR(255) NULL,
  `t_coupon_couponKindId` INT NULL,
  `t_coupon_detailText` VARCHAR(255) NULL,
  `t_coupon_spatialCondition` VARCHAR(255) NULL,
  `t_coupon_imagePath` VARCHAR(255) NULL,
  PRIMARY KEY (`t_coupon_Id`),
  UNIQUE INDEX `t_coupon_couponId_UNIQUE` (`t_coupon_Id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_stylist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_stylist` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_stylist` (
  `t_stylist_Id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `t_stylist_name` VARCHAR(255) NULL,
  `t_stylist_sex` INT UNSIGNED NULL,
  `t_stylist_detailText` TEXT(1024) NULL,
  `t_stylist_userId` INT UNSIGNED NULL,
  `t_stylist_imagePath` VARCHAR(255) NULL,
  `t_stylist_position` VARCHAR(45) NULL,
  `t_stylist_message` TEXT(1024) NULL,
  `t_stylist_experienceYear` INT UNSIGNED NULL,
  `t_stylist_specialMenu` VARCHAR(255) NULL,
  `t_stylist_goodNumber` INT UNSIGNED NULL,
  `t_stylist_viewNumber` INT UNSIGNED NULL,
  `t_stylist_mail` VARCHAR(128) NULL,
  `t_stylist_phoneNumber` VARCHAR(45) NULL,
  `t_stylist_birth` DATETIME NULL,
  `t_stylist_menuId` TEXT(10000) NULL,
  `t_stylist_hairStyleId` TEXT(10000) NULL,
  PRIMARY KEY (`t_stylist_Id`),
  UNIQUE INDEX `t_stylist_stylistId_UNIQUE` (`t_stylist_Id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_hairStyle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_hairStyle` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_hairStyle` (
  `t_hairStyle_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `t_hairStyle_name` VARCHAR(45) NULL,
  `t_hairStyle_hairTypeId` INT UNSIGNED NULL,
  `t_hairStyle_goodNumber` VARCHAR(45) NULL,
  `t_hairStyle_viewNumber` VARCHAR(45) NULL,
  `t_hairStyle_stylistId` INT UNSIGNED NULL,
  `t_hairStyle_areaId` TEXT(10000) NULL,
  `t_hairStyle_imagePath` VARCHAR(255) NULL,
  `t_hairStyle_salonId` INT UNSIGNED NULL,
  PRIMARY KEY (`t_hairStyle_id`),
  UNIQUE INDEX `t_hairStyle_id_UNIQUE` (`t_hairStyle_id` ASC),
  UNIQUE INDEX `t_hairStyle_stylistId_UNIQUE` (`t_hairStyle_stylistId` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_masterHairType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_masterHairType` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_masterHairType` (
  `t_hairType_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `t_hairType_name` VARCHAR(45) NULL,
  `t_hairType_sex` VARCHAR(12) NULL,
  PRIMARY KEY (`t_hairType_id`),
  UNIQUE INDEX `t_hairType_id_UNIQUE` (`t_hairType_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_blog`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_blog` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_blog` (
  `t_blog_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `t_blog_name` VARCHAR(255) NULL,
  `t_blog_postedDate` DATETIME NULL,
  `t_blog_detailText` TEXT(21845) NULL,
  PRIMARY KEY (`t_blog_id`),
  UNIQUE INDEX `t_blog_id_UNIQUE` (`t_blog_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_review` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_review` (
  `t_review_id` INT NOT NULL,
  `t_review_userId` INT UNSIGNED NULL,
  `t_review_postedDate` DATETIME NULL,
  `t_review_commentId` TEXT(20000) NULL,
  PRIMARY KEY (`t_review_id`),
  UNIQUE INDEX `t_review_id_UNIQUE` (`t_review_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_comment` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_comment` (
  `t_comment_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `t_comment_userId` INT UNSIGNED NULL,
  `t_comment_message` TEXT(21845) NULL,
  `t_comment_reviewId` INT UNSIGNED NULL,
  PRIMARY KEY (`t_comment_id`),
  UNIQUE INDEX `t_comment_id_UNIQUE` (`t_comment_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_masterCouponKind`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_masterCouponKind` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_masterCouponKind` (
  `t_couponKind_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `t_couponKind_name` VARCHAR(255) NULL,
  PRIMARY KEY (`t_couponKind_id`),
  UNIQUE INDEX `t_couponKind_id_UNIQUE` (`t_couponKind_id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `MEIHAIWAN_TEST`.`t_masterRecommend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `MEIHAIWAN_TEST`.`t_masterRecommend` ;

CREATE TABLE IF NOT EXISTS `MEIHAIWAN_TEST`.`t_masterRecommend` (
  `t_masterRecommend_Id` INT NOT NULL,
  `t_masterRecommend_salonId` INT NULL DEFAULT -1,
  `t_masterRecommend_hairStyleId` INT NULL DEFAULT -1,
  `t_masterRecommend_type` VARCHAR(512) NULL,
  `t_masterRecommend_updateDate` DATETIME NULL,
  `t_masterRecommend_displayOrder` INT UNSIGNED NULL,
  PRIMARY KEY (`t_masterRecommend_Id`),
  UNIQUE INDEX `t_masterRecommendId_UNIQUE` (`t_masterRecommend_Id` ASC))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
