SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `hhovsepy_compannex` DEFAULT CHARACTER SET UTF8;
USE `hhovsepy_compannex`;

-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`Industry`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`industry` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`Category`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`category` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `industry_ID` INT NOT NULL ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `FK_CAT_IND` (`industry_ID` ASC) ,
  CONSTRAINT `FK_CAT_IND`
    FOREIGN KEY (`industry_ID` )
    REFERENCES `hhovsepy_compannex`.`industry` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`Company`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`company` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(255) NULL ,
  `website` VARCHAR(45) NULL ,
  `create_date` DATE NULL ,
  `logo` VARCHAR(255) NULL ,
  `employee_count` INT NULL ,
  `status` VARCHAR(45) NULL ,
  `added_date` DATE NOT NULL ,
  `category_ID` INT NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `FK_COMP_CAT` (`category_ID` ASC) ,
  CONSTRAINT `FK_COMP_CAT`
    FOREIGN KEY (`category_ID` )
    REFERENCES `hhovsepy_compannex`.`category` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`Partner`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`partner` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `company_ID1` INT NOT NULL ,
  `company_ID2` INT NOT NULL ,
  `added_date` DATE NOT NULL ,
  `status` VARCHAR(45) NOT NULL ,
  `is_active` BOOLEAN NOT NULL DEFAULT true ,
  `description` VARCHAR(255) NULL ,
  `feedback` VARCHAR(255) NULL ,
  `create_date` DATE NULL ,
  PRIMARY KEY (`ID`) ,
  UNIQUE INDEX `UK_COMP12` (`company_ID1` ASC, `company_ID2` ASC) ,
  CONSTRAINT `FK_PARTNER_COMP1`
    FOREIGN KEY (`company_ID1` )
    REFERENCES `hhovsepy_compannex`.`company` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PARTNER_COMP2`
    FOREIGN KEY (`company_ID2` )
    REFERENCES `hhovsepy_compannex`.`company` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`Language`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`language` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `original_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`Industry_Tr`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`industry_tr` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `industry_ID` INT NOT NULL ,
  `language_ID` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `FK_IND_TR_IND` (`industry_ID` ASC) ,
  INDEX `FK_IND_TR_LANG` (`language_ID` ASC) ,
  UNIQUE INDEX `UK_IND_LANG` (`industry_ID` ASC, `language_ID` ASC) ,
  CONSTRAINT `FK_IND_TR_IND`
    FOREIGN KEY (`industry_ID` )
    REFERENCES `hhovsepy_compannex`.`industry` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_IND_TR_LANG`
    FOREIGN KEY (`language_ID` )
    REFERENCES `hhovsepy_compannex`.`language` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`Category_Tr`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`category_tr` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `category_ID` INT NOT NULL ,
  `language_ID` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `FK_CAT_TR_CAT` (`category_ID` ASC) ,
  INDEX `FK_CAT_TR_LANG` (`language_ID` ASC) ,
  UNIQUE INDEX `UK_CAT_LANG` (`language_ID` ASC, `category_ID` ASC) ,
  CONSTRAINT `FK_CAT_TR_CAT`
    FOREIGN KEY (`category_ID` )
    REFERENCES `hhovsepy_compannex`.`category` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CAT_TR_LANG`
    FOREIGN KEY (`language_ID` )
    REFERENCES `hhovsepy_compannex`.`language` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`Country`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`country` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`Country_Tr`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`country_tr` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `country_ID` INT NOT NULL ,
  `language_ID` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  INDEX `FK_COUNTTR_COUNT` (`country_ID` ASC) ,
  INDEX `FK_COUNTTR_LANG` (`language_ID` ASC) ,
  UNIQUE INDEX `UK_COUNT_LANG` (`language_ID` ASC, `country_ID` ASC) ,
  PRIMARY KEY (`ID`) ,
  CONSTRAINT `FK_COUNTTR_COUNT`
    FOREIGN KEY (`country_ID` )
    REFERENCES `hhovsepy_compannex`.`country` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_COUNTTR_LANG`
    FOREIGN KEY (`language_ID` )
    REFERENCES `hhovsepy_compannex`.`language` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`Company_Country`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`company_country` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `company_ID` INT NOT NULL ,
  `country_ID` INT NOT NULL ,
  `description` VARCHAR(255) NULL ,
  `create_date` DATE NULL ,
  PRIMARY KEY (`ID`) ,
  UNIQUE INDEX `UK_COMP_COUNT` (`company_ID` ASC, `country_ID` ASC) ,
  INDEX `FK_COMPCOUNT_COMP` (`company_ID` ASC) ,
  CONSTRAINT `FK_COMPCOUNT_COUNT`
    FOREIGN KEY (`country_ID` )
    REFERENCES `hhovsepy_compannex`.`country` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_COMPCOUNT_COMP`
    FOREIGN KEY (`company_ID` )
    REFERENCES `hhovsepy_compannex`.`company` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`Company_Tr`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`company_tr` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `company_ID` INT NOT NULL ,
  `language_ID` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `address` VARCHAR(255) NULL ,
  `contacts` VARCHAR(255) NULL ,
  `description` VARCHAR(255) NULL ,
  `slogan` VARCHAR(255) NULL ,
  `status` VARCHAR(45) NULL ,
  INDEX `FK_COMP_TR_COMP` (`company_ID` ASC) ,
  INDEX `FK_COMP_TR_LANG` (`language_ID` ASC) ,
  PRIMARY KEY (`ID`) ,
  UNIQUE INDEX `UK_COMP_LANG` (`company_ID` ASC, `language_ID` ASC) ,
  CONSTRAINT `FK_COMP_TR_COMP`
    FOREIGN KEY (`company_ID` )
    REFERENCES `hhovsepy_compannex`.`company` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_COMP_TR_LANG`
    FOREIGN KEY (`language_ID` )
    REFERENCES `hhovsepy_compannex`.`language` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`Parent`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`parent` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `parent_ID` INT NOT NULL ,
  `child_ID` INT NOT NULL ,
  `added_date` DATE NOT NULL ,
  `create_date` DATE NULL ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `FK_PAR_PARCOMP` (`parent_ID` ASC) ,
  INDEX `FK_PAR_CHILDCOMP` (`child_ID` ASC) ,
  UNIQUE INDEX `UK_PAR_CHILD` (`parent_ID` ASC, `child_ID` ASC) ,
  CONSTRAINT `FK_PAR_PARCOMP`
    FOREIGN KEY (`parent_ID` )
    REFERENCES `hhovsepy_compannex`.`company` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PAR_CHILDCOMP`
    FOREIGN KEY (`child_ID` )
    REFERENCES `hhovsepy_compannex`.`company` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`news`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`news` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `date` DATE NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`news_tr`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`news_tr` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `news_ID` INT NOT NULL ,
  `header` VARCHAR(45) NOT NULL ,
  `text` VARCHAR(255) NOT NULL ,
  `language_ID` INT NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `FK_NEWSTR_NEWS` (`news_ID` ASC) ,
  INDEX `FK_NEWSTR_LANG` (`language_ID` ASC) ,
  CONSTRAINT `FK_NEWSTR_NEWS`
    FOREIGN KEY (`news_ID` )
    REFERENCES `hhovsepy_compannex`.`news` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_NEWSTR_LANG`
    FOREIGN KEY (`language_ID` )
    REFERENCES `hhovsepy_compannex`.`language` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`feedback`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`feedback` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `company_ID` INT NOT NULL ,
  `text` VARCHAR(255) NOT NULL ,
  `person` VARCHAR(45) NOT NULL ,
  `position` VARCHAR(45) NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `FK_FEEDB_COMP` (`company_ID` ASC) ,
  CONSTRAINT `FK_FEEDB_COMP`
    FOREIGN KEY (`company_ID` )
    REFERENCES `hhovsepy_compannex`.`company` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

ALTER TABLE `hhovsepy_compannex`.`company`
 ADD `email` VARCHAR(45) NOT NULL AFTER `website`,
 ADD `password` VARCHAR(45) NOT NULL AFTER `email`,
 ADD `telephone` VARCHAR(45) AFTER `website`,
 ADD `fax` VARCHAR(45) AFTER `telephone`;

ALTER TABLE `hhovsepy_compannex`.`category_tr`
 MODIFY `name` VARCHAR(255) NOT NULL;

ALTER TABLE `hhovsepy_compannex`.`feedback`
ADD `date` DATE NOT NULL AFTER `position`;

ALTER TABLE `hhovsepy_compannex`.`company`
 ADD UNIQUE INDEX `UK_COMP_EMAIL` (`email`);

update category_tr set language_ID = 2 where language_ID = 1 and category_ID = 39;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`question`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`question` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `person` VARCHAR(45) NULL ,
  `email` VARCHAR(45) NULL ,
  `company_ID` INT NULL ,
  `text` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `FK_QUEST_COMP` (`company_ID` ASC) ,
  CONSTRAINT `FK_QUEST_COMP`
    FOREIGN KEY (`company_ID` )
    REFERENCES `hhovsepy_compannex`.`company` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

ALTER TABLE `hhovsepy_compannex`.`question`
ADD `date` DATE NOT NULL AFTER `text`;

ALTER TABLE `hhovsepy_compannex`.`question`
ADD `subject` VARCHAR(45) NOT NULL AFTER `ID`;

ALTER TABLE `hhovsepy_compannex`.`company`
ADD `zipcode` VARCHAR(10) NULL;

ALTER TABLE `hhovsepy_compannex`.`company_tr`
ADD `city` VARCHAR(255) NULL,
ADD `region` VARCHAR(255) NULL;

ALTER TABLE `hhovsepy_compannex`.`company`
ADD `token` VARCHAR(255) NULL;

ALTER TABLE `hhovsepy_compannex`.`company`
ADD `password_token` VARCHAR(255) NULL;

ALTER TABLE `hhovsepy_compannex`.`company`
ADD `password_token_date` DATE NULL AFTER `password_token`;

-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`answer`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`answer` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `question_ID` INT NULL ,
  `text` VARCHAR(255) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `FK_ANSWER_QUEST` (`question_ID` ASC) ,
  CONSTRAINT `FK_ANSWER_QUEST`
    FOREIGN KEY (`question_ID` )
    REFERENCES `hhovsepy_compannex`.`question` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

ALTER TABLE `hhovsepy_compannex`.`question`
ADD `question_ID` INT NULL AFTER `company_ID`,
ADD  INDEX `FK_RE_QUEST` (`question_ID` ASC),
ADD  CONSTRAINT `FK_RE_QUEST`
    FOREIGN KEY (`question_ID` )
    REFERENCES `hhovsepy_compannex`.`question` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE `hhovsepy_compannex`.`answer`
ADD `date` DATE NOT NULL AFTER `text`;


-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`user`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`user` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `telephone` VARCHAR(45),
  `address` VARCHAR(255) NULL,
  `fax` VARCHAR(45);
  PRIMARY KEY (`ID`)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `hhovsepy_compannex`.`User_Category`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `hhovsepy_compannex`.`user_category` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `user_ID` INT NOT NULL ,
  `category_ID` INT NOT NULL ,
  `description` VARCHAR(255) NULL ,
  `create_date` DATE NULL ,
  PRIMARY KEY (`ID`) ,
  UNIQUE INDEX `UK_USER_CAT` (`user_ID` ASC, `category_ID` ASC) ,
  INDEX `FK_USERCAT_USER` (`user_ID` ASC) ,
  CONSTRAINT `FK_USERCAT_USER`
    FOREIGN KEY (`user_ID` )
    REFERENCES `hhovsepy_compannex`.`user` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USERCAT_CAT`
    FOREIGN KEY (`category_ID` )
    REFERENCES `hhovsepy_compannex`.`category` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

ALTER TABLE `hhovsepy_compannex`.`answer`
ADD `user_ID` INT NULL AFTER `text`,
ADD `is_private` BOOLEAN NOT NULL DEFAULT false;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
