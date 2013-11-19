SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
CREATE SCHEMA IF NOT EXISTS `opensms` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP);


-- -----------------------------------------------------
-- Table `mydb`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`category` (
  `category_id` INT NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`));


-- -----------------------------------------------------
-- Table `mydb`.`timestamps`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`timestamps` (
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP NULL);

USE `opensms` ;

-- -----------------------------------------------------
-- Table `opensms`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`user` (
  `user_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `createdate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `account_status` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 54
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`employee` (
  `user_id` INT(10) UNSIGNED NOT NULL,
  `surname` VARCHAR(100) NOT NULL,
  `initials` VARCHAR(20) NOT NULL,
  `name_referred_by_initials` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_employee_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `opensms`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`vendor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`vendor` (
  `user_id` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `remark` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_table1_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `opensms`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`grn_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`grn_order` (
  `grn_order_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `receive_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `data_entry_employee` INT(10) UNSIGNED NOT NULL,
  `vendor` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`grn_order_id`),
  INDEX `fk_grn_order_employee1_idx` (`data_entry_employee` ASC),
  INDEX `fk_grn_order_vendor1_idx` (`vendor` ASC),
  CONSTRAINT `fk_grn_order_employee1`
    FOREIGN KEY (`data_entry_employee`)
    REFERENCES `opensms`.`employee` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grn_order_vendor1`
    FOREIGN KEY (`vendor`)
    REFERENCES `opensms`.`vendor` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`profit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`profit` (
  `profit_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `value` DECIMAL(12,2) NOT NULL,
  `type` ENUM('PERCENTAGE','MARGIN') NOT NULL,
  PRIMARY KEY (`profit_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`unit`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`unit` (
  `unit_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `unit` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`unit_id`),
  UNIQUE INDEX `unit_id_UNIQUE` (`unit_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`category` (
  `category_id` INT(11) NOT NULL,
  `category` VARCHAR(100) NOT NULL,
  `parent_category` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  INDEX `fk_category_category1_idx` (`parent_category` ASC),
  CONSTRAINT `fk_category_category1`
    FOREIGN KEY (`parent_category`)
    REFERENCES `opensms`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`item` (
  `item_id` VARCHAR(100) NOT NULL,
  `unit` INT(10) UNSIGNED NOT NULL,
  `name` TEXT NOT NULL,
  `default_profit` INT(10) UNSIGNED NOT NULL,
  `category` INT(11) NOT NULL,
  PRIMARY KEY (`item_id`),
  UNIQUE INDEX `item_id_UNIQUE` (`item_id` ASC),
  INDEX `fk_item_unit1_idx` (`unit` ASC),
  INDEX `fk_item_profit1_idx` (`default_profit` ASC),
  INDEX `fk_item_category1_idx` (`category` ASC),
  CONSTRAINT `fk_item_profit1`
    FOREIGN KEY (`default_profit`)
    REFERENCES `opensms`.`profit` (`profit_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_unit1`
    FOREIGN KEY (`unit`)
    REFERENCES `opensms`.`unit` (`unit_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_category1`
    FOREIGN KEY (`category`)
    REFERENCES `opensms`.`category` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`batch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`batch` (
  `batch_code` VARCHAR(100) NOT NULL,
  `item` VARCHAR(100) NOT NULL,
  `buying_unit_price` DECIMAL(20,2) NOT NULL,
  `quantity` DECIMAL(15,3) NOT NULL,
  `profit` INT(10) UNSIGNED NULL DEFAULT NULL,
  `grn_order` BIGINT(20) UNSIGNED NOT NULL,
  `remaining_quantity` DECIMAL(15,3) NULL,
  `expire_date` TIMESTAMP NULL,
  PRIMARY KEY (`batch_code`),
  INDEX `fk_batch_item1_idx` (`item` ASC),
  INDEX `fk_batch_profit1_idx` (`profit` ASC),
  INDEX `fk_batch_grn_order1_idx` (`grn_order` ASC),
  CONSTRAINT `fk_batch_grn_order1`
    FOREIGN KEY (`grn_order`)
    REFERENCES `opensms`.`grn_order` (`grn_order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_batch_item1`
    FOREIGN KEY (`item`)
    REFERENCES `opensms`.`item` (`item_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_batch_profit1`
    FOREIGN KEY (`profit`)
    REFERENCES `opensms`.`profit` (`profit_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`customer` (
  `user_id` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `remark` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_customer_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `opensms`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`type` (
  `type_id` INT(11) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`type_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`employee_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`employee_type` (
  `user` INT(10) UNSIGNED NOT NULL,
  `type` INT(11) NOT NULL,
  `assign_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `resign_date` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`user`, `type`, `assign_date`),
  INDEX `fk_employee_has_type_type1_idx` (`type` ASC),
  INDEX `fk_employee_has_type_employee1_idx` (`user` ASC),
  CONSTRAINT `fk_employee_has_type_employee1`
    FOREIGN KEY (`user`)
    REFERENCES `opensms`.`employee` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_has_type_type1`
    FOREIGN KEY (`type`)
    REFERENCES `opensms`.`type` (`type_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`payment_method`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`payment_method` (
  `payment_method_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`payment_method_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`grn_payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`grn_payment` (
  `payement_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cashier_employee` INT(10) UNSIGNED NOT NULL,
  `amount` DECIMAL(20,2) NOT NULL,
  `payment_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `grn_order` BIGINT(20) UNSIGNED NOT NULL,
  `payment_method` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`payement_id`),
  INDEX `fk_grn_payment_employee1_idx` (`cashier_employee` ASC),
  INDEX `fk_grn_payment_grn_order1_idx` (`grn_order` ASC),
  INDEX `fk_grn_payment_payment_method1_idx` (`payment_method` ASC),
  CONSTRAINT `fk_grn_payment_employee1`
    FOREIGN KEY (`cashier_employee`)
    REFERENCES `opensms`.`employee` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grn_payment_grn_order1`
    FOREIGN KEY (`grn_order`)
    REFERENCES `opensms`.`grn_order` (`grn_order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grn_payment_payment_method1`
    FOREIGN KEY (`payment_method`)
    REFERENCES `opensms`.`payment_method` (`payment_method_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`gsr_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`gsr_order` (
  `gsr_order_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer` INT(10) UNSIGNED NOT NULL,
  `selling_date_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`gsr_order_id`),
  INDEX `fk_gsr_order_customer1_idx` (`customer` ASC),
  CONSTRAINT `fk_gsr_order_customer1`
    FOREIGN KEY (`customer`)
    REFERENCES `opensms`.`customer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`gsr_payment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`gsr_payment` (
  `gsr_payment_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `payment_method` INT(10) UNSIGNED NOT NULL,
  `cashier_employee` INT(10) UNSIGNED NOT NULL,
  `gsr_order` BIGINT(20) UNSIGNED NOT NULL,
  `amount` DECIMAL(20,2) NOT NULL,
  `payment_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`gsr_payment_id`),
  INDEX `fk_gsr_payment_payment_method1_idx` (`payment_method` ASC),
  INDEX `fk_gsr_payment_employee1_idx` (`cashier_employee` ASC),
  INDEX `fk_gsr_payment_gsr_order1_idx` (`gsr_order` ASC),
  CONSTRAINT `fk_gsr_payment_employee1`
    FOREIGN KEY (`cashier_employee`)
    REFERENCES `opensms`.`employee` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gsr_payment_gsr_order1`
    FOREIGN KEY (`gsr_order`)
    REFERENCES `opensms`.`gsr_order` (`gsr_order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gsr_payment_payment_method1`
    FOREIGN KEY (`payment_method`)
    REFERENCES `opensms`.`payment_method` (`payment_method_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`iis_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`iis_order` (
  `iis_order_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `item_issuer_employee` INT(10) UNSIGNED NOT NULL,
  `return_check_employee` INT(10) UNSIGNED NULL DEFAULT NULL COMMENT ' /* comment truncated */ /*accepts end of the day returns*/',
  `sales_employee` INT(10) UNSIGNED NOT NULL,
  `iss_order_date_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`iis_order_id`),
  INDEX `fk_iis_order_employee1_idx` (`item_issuer_employee` ASC),
  INDEX `fk_iis_order_employee2_idx` (`return_check_employee` ASC),
  INDEX `fk_iis_order_employee3_idx` (`sales_employee` ASC),
  CONSTRAINT `fk_iis_order_employee1`
    FOREIGN KEY (`item_issuer_employee`)
    REFERENCES `opensms`.`employee` (`user_id`),
  CONSTRAINT `fk_iis_order_employee2`
    FOREIGN KEY (`return_check_employee`)
    REFERENCES `opensms`.`employee` (`user_id`),
  CONSTRAINT `fk_iis_order_employee3`
    FOREIGN KEY (`sales_employee`)
    REFERENCES `opensms`.`employee` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = ' /* comment truncated */ /*Item issue order*/';


-- -----------------------------------------------------
-- Table `opensms`.`iis_order_has_batch`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`iis_order_has_batch` (
  `iis_order` BIGINT(20) UNSIGNED NOT NULL,
  `batch` VARCHAR(100) NOT NULL,
  `issued_quantity` DECIMAL(15,3) NOT NULL,
  `return_quantity` DECIMAL(15,3) NULL DEFAULT NULL,
  PRIMARY KEY (`iis_order`, `batch`),
  INDEX `fk_iis_order_has_batch_iis_order1_idx` (`iis_order` ASC),
  INDEX `fk_iis_order_has_batch_batch1_idx` (`batch` ASC),
  CONSTRAINT `fk_iis_order_has_batch_iis_order1`
    FOREIGN KEY (`iis_order`)
    REFERENCES `opensms`.`iis_order` (`iis_order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_iis_order_has_batch_batch1`
    FOREIGN KEY (`batch`)
    REFERENCES `opensms`.`batch` (`batch_code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`iis_order_batch_has_gsr_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`iis_order_batch_has_gsr_order` (
  `gsr_order` BIGINT(20) UNSIGNED NOT NULL,
  `iis_order` BIGINT(20) UNSIGNED NOT NULL,
  `batch` VARCHAR(100) NOT NULL,
  `quantity` DECIMAL(15,3) NOT NULL,
  PRIMARY KEY (`gsr_order`, `iis_order`, `batch`),
  INDEX `fk_iis_order_has_batch_has_gsr_order_gsr_order1_idx` (`gsr_order` ASC),
  INDEX `fk_iis_order_batch_has_gsr_order_iis_order_has_batch1_idx` (`iis_order` ASC, `batch` ASC),
  CONSTRAINT `fk_iis_order_has_batch_has_gsr_order_gsr_order1`
    FOREIGN KEY (`gsr_order`)
    REFERENCES `opensms`.`gsr_order` (`gsr_order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_iis_order_batch_has_gsr_order_iis_order_has_batch1`
    FOREIGN KEY (`iis_order` , `batch`)
    REFERENCES `opensms`.`iis_order_has_batch` (`iis_order` , `batch`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`pre_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`pre_order` (
  `pre_order_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `customer` INT(10) UNSIGNED NOT NULL,
  `pre_order_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `priority` INT(11) NOT NULL DEFAULT '5',
  `is_open` TINYINT(1) NOT NULL DEFAULT '1',
  `iis_order` BIGINT(20) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`pre_order_id`),
  INDEX `fk_pre_order_customer1_idx` (`customer` ASC),
  INDEX `fk_pre_order_iis_order1_idx` (`iis_order` ASC),
  CONSTRAINT `fk_pre_order_customer1`
    FOREIGN KEY (`customer`)
    REFERENCES `opensms`.`customer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pre_order_iis_order1`
    FOREIGN KEY (`iis_order`)
    REFERENCES `opensms`.`iis_order` (`iis_order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`pre_order_has_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`pre_order_has_item` (
  `item` VARCHAR(100) NOT NULL,
  `pre_order` BIGINT(20) UNSIGNED NOT NULL,
  `quantity` DECIMAL(15,3) NOT NULL,
  PRIMARY KEY (`item`, `pre_order`),
  INDEX `fk_item_has_pre_order_pre_order1_idx` (`pre_order` ASC),
  INDEX `fk_item_has_pre_order_item1_idx` (`item` ASC),
  CONSTRAINT `fk_item_has_pre_order_item1`
    FOREIGN KEY (`item`)
    REFERENCES `opensms`.`item` (`item_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_has_pre_order_pre_order1`
    FOREIGN KEY (`pre_order`)
    REFERENCES `opensms`.`pre_order` (`pre_order_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`role` (
  `role_id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`user_contact_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`user_contact_detail` (
  `user_id` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `address_line1` VARCHAR(100) NULL DEFAULT NULL,
  `address_line2` VARCHAR(100) NULL DEFAULT NULL,
  `city` VARCHAR(100) NOT NULL,
  `province` VARCHAR(100) NOT NULL,
  `postal_code` VARCHAR(45) NOT NULL,
  `country` VARCHAR(100) NOT NULL DEFAULT 'Sri Lanka',
  `phone_number` VARCHAR(15) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_user_address_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `opensms`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `opensms`.`user_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `opensms`.`user_role` (
  `role` INT(10) UNSIGNED NOT NULL,
  `user` INT(10) UNSIGNED NOT NULL,
  `assign_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `resign_date` TIMESTAMP NULL DEFAULT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role`, `user`, `assign_date`),
  INDEX `fk_user_role_has_user_user1_idx` (`user` ASC),
  INDEX `fk_user_role_has_user_user_role1_idx` (`role` ASC),
  CONSTRAINT `fk_user_role_has_user_user1`
    FOREIGN KEY (`user`)
    REFERENCES `opensms`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_has_user_user_role1`
    FOREIGN KEY (`role`)
    REFERENCES `opensms`.`role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
