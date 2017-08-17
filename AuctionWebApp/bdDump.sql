CREATE DATABASE  IF NOT EXISTS `auction_v3` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `auction_v3`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: auction_v3
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auction_type`
--

DROP TABLE IF EXISTS `auction_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auction_type` (
  `at_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `at_type` varchar(45) NOT NULL,
  `at_description` tinytext,
  PRIMARY KEY (`at_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction_type`
--

LOCK TABLES `auction_type` WRITE;
/*!40000 ALTER TABLE `auction_type` DISABLE KEYS */;
INSERT INTO `auction_type` VALUES (1,'Internet auction','The seller announces the minimum sale price, below that sell is impossible. Buyers make rates, and the bidding of other participants is unknown and is possible to do only one rate.'),(2,'English auction','The minimum price is set as the starting price, The basis for further trades, during which the requested price increases and rates are known to all participants.'),(3,'Blitz auction','The kind of auction in which a product or service is put up for sale at a minimum fixed cost. The winner is the one who managed to buy the lot first.');
/*!40000 ALTER TABLE `auction_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `с_user_id` int(11) NOT NULL,
  `с_lot_id` int(11) NOT NULL,
  `c_text` text NOT NULL,
  `c_date` date NOT NULL,
  PRIMARY KEY (`c_id`),
  KEY `fk_comment_lot1_idx` (`с_lot_id`),
  KEY `fk_comment_user1_idx` (`с_user_id`),
  KEY `date_idx` (`c_date`),
  CONSTRAINT `fk_comment_lot1` FOREIGN KEY (`с_lot_id`) REFERENCES `lot` (`l_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1` FOREIGN KEY (`с_user_id`) REFERENCES `user` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,4,5,'Donec at nibh lectus. Aliquam sit amet urna non leo gravida tincidunt.))','2017-04-15'),(2,3,6,'Morbi ornare iaculis ligula, non suscipit nulla semper a. Duis ultrices neque!!!!','2017-03-01'),(3,5,3,'Etiam maximus pulvinar rhoncus. Morbi tempus maximus(( ','2017-03-05'),(4,6,7,'Nulla facilisi. Etiam vel diam sit amet orci accumsan cursus. !!','2017-03-06'),(5,4,9,'Duis nec nunc augue. Nam aliquam nibh non pulvinar ...','2017-02-02'),(6,7,3,'Orci varius natoque penatibus et magnis dis parturient montes,','2017-02-02'),(7,8,6,' Aliquam erat volutpat. Orci varius natoque ))','2017-03-12'),(8,3,2,'Proin dictum lacinia leo, non aliquet)','2017-04-21');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_archive`
--

DROP TABLE IF EXISTS `comment_archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_archive` (
  `ca_id` int(11) NOT NULL AUTO_INCREMENT,
  `ca_user_id` int(11) NOT NULL,
  `ca_lot_id` int(11) NOT NULL,
  `ca_text` text NOT NULL,
  `ca_date` date NOT NULL,
  PRIMARY KEY (`ca_id`),
  KEY `fk_comment_archive_lot1_idx` (`ca_lot_id`),
  KEY `fk_comment_archive_user1_idx` (`ca_user_id`),
  KEY `date_idx` (`ca_date`),
  CONSTRAINT `fk_comment_archive_lot1` FOREIGN KEY (`ca_lot_id`) REFERENCES `lot` (`l_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_archive_user1` FOREIGN KEY (`ca_user_id`) REFERENCES `user` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_archive`
--

LOCK TABLES `comment_archive` WRITE;
/*!40000 ALTER TABLE `comment_archive` DISABLE KEYS */;
INSERT INTO `comment_archive` VALUES (1,3,1,'Aenean lectus eros, maximus non lectus at, hendrerit feugiat nibh.','2015-03-15'),(2,2,1,'Aenean lobortis pretium nunc !!!','2015-03-20'),(3,2,4,' Suspendisse libero nisl, imperdiet ut malesuada eu, tristique at lacus.','2016-04-20'),(4,3,1,'\'Donec maximus vulputate purus vel molestie :)','2016-04-21'),(5,1,2,'Aenean lobortis pretium nunc !!!','2016-05-20'),(6,3,3,'Cras posuere nisi ex, bibendum egestas orci lobortis ac.','2016-05-21'),(7,1,1,'\'Donec maximus vulputate purus vel molestie :)','2016-07-01'),(8,3,2,'Cras posuere nisi ex, bibendum egestas orci lobortis ac.','2016-07-01');
/*!40000 ALTER TABLE `comment_archive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `good`
--

DROP TABLE IF EXISTS `good`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `good` (
  `g_id` int(11) NOT NULL AUTO_INCREMENT,
  `g_category_id` int(11) NOT NULL,
  `g_condition_id` int(11) NOT NULL,
  `g_name` varchar(45) NOT NULL,
  `g_description` varchar(255) NOT NULL,
  `g_price` decimal(10,0) NOT NULL,
  PRIMARY KEY (`g_id`),
  KEY `fk_good_good_category1_idx` (`g_category_id`),
  KEY `fk_good_good_condition1_idx` (`g_condition_id`),
  KEY `price_idx` (`g_price`),
  KEY `name_idx` (`g_name`),
  CONSTRAINT `fk_good_good_category1` FOREIGN KEY (`g_category_id`) REFERENCES `good_category` (`gc_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_good_good_condition1` FOREIGN KEY (`g_condition_id`) REFERENCES `good_condition` (`gc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `good`
--

LOCK TABLES `good` WRITE;
/*!40000 ALTER TABLE `good` DISABLE KEYS */;
INSERT INTO `good` VALUES (1,7,4,'Ancient ring','Was found at repair building',100),(2,4,2,'Samsung S9','Only one month in use',200),(3,8,1,'Suzuki R2','New motorcycle',3600),(4,6,4,'Drill ','Manufacter Bosch',50),(5,7,4,'Ancient ring','Was found at repair building',100),(6,4,2,'Samsung S9','Only one month in use',200),(7,8,1,'Suzuki R2','New motorcycle',3600),(8,19,4,'Drill ','Manufacter Bosch',50),(9,6,3,'Photocamera','Manufacter Nikon, just upgrader',152),(10,7,4,'Fishing rod','Like new',15),(11,7,4,'Dali\'s painting','Good copy of Dali',230),(12,5,1,'Notebook Asuz','+ bag for transportation',160),(42,5,2,'Планшет Samsung Galaxy 5','Планшет полностью комплектный.',101);
/*!40000 ALTER TABLE `good` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `good_category`
--

DROP TABLE IF EXISTS `good_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `good_category` (
  `gc_id` int(11) NOT NULL AUTO_INCREMENT,
  `gc_category` varchar(45) NOT NULL,
  `gc_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gc_id`),
  KEY `category_idx` (`gc_category`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `good_category`
--

LOCK TABLES `good_category` WRITE;
/*!40000 ALTER TABLE `good_category` DISABLE KEYS */;
INSERT INTO `good_category` VALUES (1,'Antique items',NULL),(2,'Collectible jewelry',NULL),(3,'Paintings',NULL),(4,'Jewelry',NULL),(5,'Electronics',NULL),(6,'Home & Garden',NULL),(7,'Sports goods',NULL),(8,'Motors',NULL),(19,'Other','All other, that not in category');
/*!40000 ALTER TABLE `good_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `good_condition`
--

DROP TABLE IF EXISTS `good_condition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `good_condition` (
  `gc_id` int(11) NOT NULL AUTO_INCREMENT,
  `gc_condition_name` varchar(45) NOT NULL,
  `gc_condition_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gc_id`),
  KEY `name_idx` (`gc_condition_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `good_condition`
--

LOCK TABLES `good_condition` WRITE;
/*!40000 ALTER TABLE `good_condition` DISABLE KEYS */;
INSERT INTO `good_condition` VALUES (1,'New','Only from shops or manufacter'),(2,'Like new','No more than 1 month after buying'),(3,'Good','Beetwen 1 month and 3 month in use'),(4,'Normal','More than 3 month in use');
/*!40000 ALTER TABLE `good_condition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lot`
--

DROP TABLE IF EXISTS `lot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lot` (
  `l_id` int(11) NOT NULL AUTO_INCREMENT,
  `l_user_id_buyer` int(11) DEFAULT NULL,
  `l_user_id_seller` int(11) NOT NULL,
  `l_good_id` int(11) NOT NULL,
  `l_auction_type_id` tinyint(4) NOT NULL,
  `l_status_id` int(7) NOT NULL DEFAULT '1',
  `l_name` varchar(100) NOT NULL,
  `l_good_amount` int(11) NOT NULL,
  `l_start_date` datetime NOT NULL,
  `l_end_date` datetime DEFAULT NULL,
  `l_buy_date` datetime DEFAULT NULL,
  `l_end_price` decimal(10,0) NOT NULL,
  PRIMARY KEY (`l_id`),
  KEY `fk_lot_user1_idx` (`l_user_id_buyer`),
  KEY `fk_lot_good1_idx` (`l_good_id`),
  KEY `fk_lot_user2_idx` (`l_user_id_seller`),
  KEY `fk_lot_auction_type1_idx` (`l_auction_type_id`),
  KEY `good_amount_idx` (`l_good_amount`),
  KEY `start_date_idx` (`l_start_date`),
  KEY `name_idx` (`l_name`),
  CONSTRAINT `fk_lot_auction_type1` FOREIGN KEY (`l_auction_type_id`) REFERENCES `auction_type` (`at_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lot_good1` FOREIGN KEY (`l_good_id`) REFERENCES `good` (`g_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lot_user1` FOREIGN KEY (`l_user_id_buyer`) REFERENCES `user` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lot_user2` FOREIGN KEY (`l_user_id_seller`) REFERENCES `user` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lot`
--

LOCK TABLES `lot` WRITE;
/*!40000 ALTER TABLE `lot` DISABLE KEYS */;
INSERT INTO `lot` VALUES (1,4,3,2,2,2,'Set of phone',2,'2015-03-01 00:00:00','2015-03-28 00:00:00',NULL,501),(2,NULL,2,9,1,2,'Camera',1,'2015-03-12 00:00:00',NULL,'2017-07-22 00:00:00',180),(3,4,4,8,2,2,'Картина',1,'2016-06-12 00:00:00',NULL,NULL,202),(4,4,4,12,3,2,'Notebook lot',2,'2016-06-14 00:00:00','2017-07-22 00:00:00','2017-07-23 21:27:06',350),(5,4,8,2,3,2,'Set of phone',3,'2017-03-20 00:00:00','2017-07-22 00:00:00','2017-07-23 21:29:54',601),(6,4,6,4,3,2,'Drill makita',1,'2017-03-15 00:00:00','2017-07-22 00:00:00','2017-07-25 17:52:04',1253),(7,2,7,6,3,2,'Phone',4,'2016-03-12 00:00:00','2016-04-12 00:00:00',NULL,500),(8,4,5,7,3,2,'Motorbike',2,'2016-01-01 00:00:00','2017-07-23 00:00:00',NULL,6000),(9,NULL,4,4,2,2,'Drill Siemens',3,'2016-02-03 00:00:00',NULL,NULL,237),(10,6,8,7,1,2,'Motocycle',2,'2017-01-03 00:00:00','2017-01-31 00:00:00',NULL,7250),(11,4,9,9,3,2,'Camera',4,'2017-01-20 00:00:00','2017-07-20 00:00:00',NULL,1236),(12,26,3,3,3,2,'Motorcycle',1,'2017-02-15 00:00:00','2017-07-22 00:00:00','2017-08-07 21:50:07',756),(13,5,4,1,1,2,'Кольцо',2,'2017-04-04 00:00:00','2017-04-07 00:00:00',NULL,458458),(33,NULL,26,42,3,2,'Планшет Samsung Galaxy 5',1,'2017-08-07 21:47:16','2017-09-06 21:47:16',NULL,101);
/*!40000 ALTER TABLE `lot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `p_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `p_user_id_buyer` int(11) NOT NULL,
  `p_lot_id` int(11) NOT NULL,
  `p_date` datetime NOT NULL,
  `p_value` decimal(10,0) NOT NULL,
  PRIMARY KEY (`p_id`),
  UNIQUE KEY `fk_payment_lot1_idx` (`p_lot_id`),
  KEY `fk_payment_user1_idx` (`p_user_id_buyer`),
  KEY `date_idx` (`p_date`),
  KEY `value_idx` (`p_value`),
  CONSTRAINT `fk_payment_lot1` FOREIGN KEY (`p_lot_id`) REFERENCES `lot` (`l_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_user1` FOREIGN KEY (`p_user_id_buyer`) REFERENCES `user` (`u_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,2,1,'2016-12-15 00:00:00',1253),(2,3,2,'2016-12-18 00:00:00',1235),(3,6,6,'2016-12-31 00:00:00',5236),(4,9,12,'2017-02-15 00:00:00',2584),(5,10,11,'2017-04-15 00:00:00',2589),(6,4,4,'2017-05-01 00:00:00',5236),(7,4,8,'2017-04-21 00:00:00',9030),(8,9,10,'2017-04-15 00:00:00',8963),(9,7,7,'2017-04-12 00:00:00',5698),(10,6,3,'2017-04-18 00:00:00',897);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `r_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `r_type` varchar(45) NOT NULL,
  `r_add_lot_ability` tinyint(1) NOT NULL,
  `r_delete_lot_ability` tinyint(1) NOT NULL,
  `r_block_lot_ability` tinyint(1) NOT NULL,
  `r_modify_good_ability` tinyint(1) NOT NULL,
  `r_modify_lot_ability` tinyint(1) NOT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin',1,1,1,1,1),(2,'Client',1,1,0,1,1),(3,'Guest',0,0,0,0,0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_role_id` tinyint(4) NOT NULL,
  `u_status` int(5) NOT NULL DEFAULT '1',
  `u_login` varchar(45) NOT NULL,
  `u_password` varchar(45) NOT NULL,
  `u_first_name` varchar(45) NOT NULL,
  `u_last_name` varchar(45) NOT NULL,
  `u_email` varchar(45) NOT NULL,
  `u_phone` varchar(45) NOT NULL,
  `u_registr_date` datetime NOT NULL,
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `login_idx` (`u_login`),
  UNIQUE KEY `email_idx` (`u_email`),
  KEY `fk_user_role_idx` (`u_role_id`),
  KEY `registr_date_idx` (`u_registr_date`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`u_role_id`) REFERENCES `role` (`r_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,1,'jack','qwer','Jack','Jackson','asdf@mail.ru','+375297891526','2015-01-30 00:00:00'),(2,1,1,'sim','asdf','Nikole','Piou','rty@gmail.com','+375332589635','2015-01-20 00:00:00'),(3,2,1,'horor','xzcbv','Givi','Smith','erdsf@gmail.com','+375331587425','2015-02-28 00:00:00'),(4,2,2,'nick95','asdf','Jack','Dorin','xzcv@gmail.com','+375291583696','2016-06-12 00:00:00'),(5,2,2,'lyli','qwte','Ivan','Smith','cbvn@mail.ru','+37544165896','2016-05-15 00:00:00'),(6,2,1,'nikki63','zxcv','Jack','Ivanow','xcbv@mail.ru','+375441254879','2016-07-14 00:00:00'),(7,2,2,'hias','xcvb','Daiv','Hulgan','ewr@gmail.com','+375295698748','2017-01-12 00:00:00'),(8,2,2,'vbcn','yt','Ola','Violin','nmv@gmail.com','+375332569874','2017-03-15 00:00:00'),(9,2,1,'cvbb','wty','Popi','Fidel','ert@mail.ru','+375888967841','2017-04-18 00:00:00'),(10,2,1,'teylor','xzcvb','Kolya','Genav','uio@gmail.com','+375268971548','2017-04-21 00:00:00'),(11,2,2,'hupno','uipo','Fred','Yellow','klo@mail.ru','+375291254369','2017-05-01 00:00:00'),(17,2,2,'login','password','firstName','lastName','email','phone','2017-06-05 00:00:00'),(18,2,2,'dsfsdfgfgdf','aA123123','Ivan','Kleshev','aasdfsgdfsgdf@mail.ru','(029) 123-12-15','2017-07-20 00:00:00'),(19,2,1,'samovolk_mail','Фф123123','фыва','фыва','aasdf@mail.ru','(029) 123-12-15','2017-07-20 00:00:00'),(20,2,2,'medvezhonok_kotenokasdfasd','123456Aa','Guryan','Kleshev','aasdf@mail.russs','(029) 123-12-15','2017-07-21 00:00:00'),(21,2,2,'qewrqwer','qwer1Q','Guryan','Nicolson','aasdfasdasdf@mail.ru','(029) 123-12-15','2017-07-21 00:00:00'),(22,2,2,'jackasdfasadsf','1234Йй','Guryan','Иванов','aasasdfasdfdf@mail.ru','(029) 123-12-15','2017-07-21 00:00:00'),(23,2,2,'quryan','qwerQ1','Guryan','Kleshev','sam@mail.ru','(029) 123-12-15','2017-07-21 00:00:00'),(25,2,2,'asdfasdffasdfds','123123Qq','Иван','Иванов','aasdasdff@mail.ru','(029) 123-12-15','2017-07-23 22:37:16'),(26,2,2,'luck12','po12PO','Николай','Уткин','erty@gmail.com','(029) 153-15-17','2017-08-07 21:42:06');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-17 21:15:53
