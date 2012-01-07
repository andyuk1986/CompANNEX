SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `company_management` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `company_management`;

-- -----------------------------------------------------
-- Table `company_management`.`Industry`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `company_management`.`Industry` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company_management`.`Category`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `company_management`.`Category` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `industry_ID` INT NOT NULL ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `FK_CAT_IND` (`industry_ID` ASC) ,
  CONSTRAINT `FK_CAT_IND`
    FOREIGN KEY (`industry_ID` )
    REFERENCES `company_management`.`Industry` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company_management`.`Company`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `company_management`.`Company` (
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
    REFERENCES `company_management`.`Category` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company_management`.`Partner`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `company_management`.`Partner` (
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
    REFERENCES `company_management`.`Company` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PARTNER_COMP2`
    FOREIGN KEY (`company_ID2` )
    REFERENCES `company_management`.`Company` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company_management`.`Language`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `company_management`.`Language` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `original_name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company_management`.`Industry_Tr`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `company_management`.`Industry_Tr` (
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
    REFERENCES `company_management`.`Industry` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_IND_TR_LANG`
    FOREIGN KEY (`language_ID` )
    REFERENCES `company_management`.`Language` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company_management`.`Category_Tr`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `company_management`.`Category_Tr` (
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
    REFERENCES `company_management`.`Category` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_CAT_TR_LANG`
    FOREIGN KEY (`language_ID` )
    REFERENCES `company_management`.`Language` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company_management`.`Country`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `company_management`.`Country` (
  `ID` INT NOT NULL AUTO_INCREMENT ,
  `description` VARCHAR(255) NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company_management`.`Country_Tr`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `company_management`.`Country_Tr` (
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
    REFERENCES `company_management`.`Country` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_COUNTTR_LANG`
    FOREIGN KEY (`language_ID` )
    REFERENCES `company_management`.`Language` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company_management`.`Company_Country`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `company_management`.`Company_Country` (
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
    REFERENCES `company_management`.`Country` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_COMPCOUNT_COMP`
    FOREIGN KEY (`company_ID` )
    REFERENCES `company_management`.`Company` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company_management`.`Company_Tr`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `company_management`.`Company_Tr` (
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
    REFERENCES `company_management`.`Company` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_COMP_TR_LANG`
    FOREIGN KEY (`language_ID` )
    REFERENCES `company_management`.`Language` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `company_management`.`Parent`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `company_management`.`Parent` (
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
    REFERENCES `company_management`.`Company` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_PAR_CHILDCOMP`
    FOREIGN KEY (`child_ID` )
    REFERENCES `company_management`.`Company` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
