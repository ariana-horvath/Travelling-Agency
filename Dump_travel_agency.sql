-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: travel_agency
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `destination`
--

DROP TABLE IF EXISTS `destination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `destination` (
  `destination_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`destination_id`),
  UNIQUE KEY `UK_kw349sqcyo1k39xa0wn3k3q2r` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `destination`
--

LOCK TABLES `destination` WRITE;
/*!40000 ALTER TABLE `destination` DISABLE KEYS */;
INSERT INTO `destination` VALUES (2,'','Maldive'),(8,'Spain. Wonderful city at the beach','Valencia'),(9,'Tropical','Bali'),(10,'Czech Republic','Prague'),(13,'Capital of Italy','Rome'),(15,'Island in Greece','Lefkada'),(19,'','Dubai'),(20,'','Ibiza');
/*!40000 ALTER TABLE `destination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'user','user'),(2,'12345','user1'),(6,'a','a'),(7,'1111111111','ariana.horvath'),(8,'123456789','ariana12345');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_package`
--

DROP TABLE IF EXISTS `user_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_package` (
  `user_id` bigint NOT NULL,
  `package_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`package_id`),
  KEY `FK3ctgldfb9o04ep0opa037260r` (`package_id`),
  CONSTRAINT `FK3ctgldfb9o04ep0opa037260r` FOREIGN KEY (`package_id`) REFERENCES `vacation_package` (`package_id`),
  CONSTRAINT `FK8n8qhfs5eceli4n13yf6u1agp` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_package`
--

LOCK TABLES `user_package` WRITE;
/*!40000 ALTER TABLE `user_package` DISABLE KEYS */;
INSERT INTO `user_package` VALUES (6,1),(1,2),(6,2),(7,30),(8,32),(7,33),(8,33);
/*!40000 ALTER TABLE `user_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vacation_package`
--

DROP TABLE IF EXISTS `vacation_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vacation_package` (
  `package_id` bigint NOT NULL AUTO_INCREMENT,
  `details` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `nb_of_places` int NOT NULL,
  `price` float NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `status` int DEFAULT NULL,
  `destination_id` bigint DEFAULT NULL,
  `available_places` int DEFAULT NULL,
  PRIMARY KEY (`package_id`),
  UNIQUE KEY `UK_n2rsnvkhj0htw07rn9mxn3j6` (`name`),
  KEY `FKdxupp03nu120q45ihtophscyw` (`destination_id`),
  CONSTRAINT `FKdxupp03nu120q45ihtophscyw` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vacation_package`
--

LOCK TABLES `vacation_package` WRITE;
/*!40000 ALTER TABLE `vacation_package` DISABLE KEYS */;
INSERT INTO `vacation_package` VALUES (1,'Cea mai cool vacanta','2022-12-22 00:00:00','Maldive Package 1',120,1500,'2022-12-12 00:00:00',2,2,99),(2,'Cea mai cool vacanta','2022-12-19 00:00:00','Maldive Package New',2,1299,'2022-12-12 00:00:00',0,2,0),(29,'','2022-04-19 00:00:00','Dubai 1',13,1399,'2022-04-15 00:00:00',1,19,13),(30,'','2022-08-20 00:00:00','Ibiza Trip',25,699,'2022-08-20 00:00:00',2,20,24),(31,'','2022-08-26 00:00:00','Ibiza Trip 2',25,699,'2022-08-20 00:00:00',1,20,25),(32,'','2022-07-28 00:00:00','Rome CityBreak',10,478,'2022-07-23 00:00:00',2,13,9),(33,'','2022-07-06 00:00:00','Lefkada Holiday ',7,900,'2022-06-29 00:00:00',2,15,5);
/*!40000 ALTER TABLE `vacation_package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'travel_agency'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-17  4:11:45
