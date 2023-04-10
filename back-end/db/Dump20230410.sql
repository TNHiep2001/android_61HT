-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: manga
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chapters`
--

DROP TABLE IF EXISTS `chapters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `chapters` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CommicId` int(11) NOT NULL,
  `Chapter` int(11) NOT NULL,
  `Content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `CommicId` (`CommicId`),
  CONSTRAINT `chapters_ibfk_1` FOREIGN KEY (`CommicId`) REFERENCES `commics` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapters`
--

LOCK TABLES `chapters` WRITE;
/*!40000 ALTER TABLE `chapters` DISABLE KEYS */;
INSERT INTO `chapters` VALUES (4,5,1,'she is my love',NULL),(5,6,2,'a war',NULL);
/*!40000 ALTER TABLE `chapters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comments` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `CommicId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `Content` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `CommicId` (`CommicId`),
  KEY `userId` (`userId`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`CommicId`) REFERENCES `commics` (`Id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (9,5,9,'truyện này cũng khá là okela phet'),(10,6,10,'truyện này cũng khá là okela phet'),(11,7,10,'truyện đáng để đọc nhé mọi người'),(12,7,10,'truyện đáng để đọc nhé mọi người'),(13,7,10,'truyện đáng để đọc nhé mọi người'),(14,6,10,'truyện đáng để đọc nhé'),(15,6,9,'truyện đáng để đọc nhé'),(16,6,9,'truyện đáng để đọc nhé'),(17,6,9,'truyện đáng để đọc nhé'),(18,6,9,'truyện đáng để đọc phết nhé'),(19,6,10,'truyện đáng để đọc nhé nhé');
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commics`
--

DROP TABLE IF EXISTS `commics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `commics` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `url` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commics`
--

LOCK TABLES `commics` WRITE;
/*!40000 ALTER TABLE `commics` DISABLE KEYS */;
INSERT INTO `commics` VALUES (5,'simple love','love story',NULL),(6,'world war','world war around the world',NULL),(7,'star war','star war galaxy',NULL);
/*!40000 ALTER TABLE `commics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `save_manga`
--

DROP TABLE IF EXISTS `save_manga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `save_manga` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `commicId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `userId` (`userId`),
  KEY `commicId` (`commicId`),
  CONSTRAINT `save_manga_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`Id`),
  CONSTRAINT `save_manga_ibfk_2` FOREIGN KEY (`commicId`) REFERENCES `commics` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `save_manga`
--

LOCK TABLES `save_manga` WRITE;
/*!40000 ALTER TABLE `save_manga` DISABLE KEYS */;
INSERT INTO `save_manga` VALUES (7,9,5),(8,9,6),(9,10,7);
/*!40000 ALTER TABLE `save_manga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (9,'chithanh2001','$2a$10$4hwcTRSUI855xSFMS/w78.aSRbNn3KjAr89i0lqGr0RAZtU0K3ITy'),(10,'longxu123','$2a$10$7P00L.q5IH8pNL7NrlkBP.5ToYGM2cNoOJ3W112bDQnrfO0MV5an2');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-10 21:24:37
