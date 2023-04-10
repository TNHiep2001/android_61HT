-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: manga
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `chapters`
--

DROP TABLE IF EXISTS `chapters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapters` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `CommicId` int DEFAULT NULL,
  `Chapter` int NOT NULL,
  `Content` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `Status` enum('true','false') DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `CommicId` (`CommicId`),
  CONSTRAINT `chapters_ibfk_1` FOREIGN KEY (`CommicId`) REFERENCES `commics` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapters`
--

LOCK TABLES `chapters` WRITE;
/*!40000 ALTER TABLE `chapters` DISABLE KEYS */;
/*!40000 ALTER TABLE `chapters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `CommicId` int DEFAULT NULL,
  `Content` varchar(100) NOT NULL,
  `userId` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `CommicId` (`CommicId`),
  KEY `userId` (`userId`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`CommicId`) REFERENCES `commics` (`Id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `users` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
INSERT INTO `comments` VALUES (1,1,'thanh ngu',1),(2,2,'thanh ngu',1),(4,1,'thanh non',1),(5,1,'thanh ga',1),(6,3,'thanh ga',2),(7,4,'aasadasd',2),(8,5,'1234',2),(9,5,'12345',2),(10,5,'12345',2),(11,4,'12345',2),(12,4,'12345',2),(13,4,'12345',2),(14,4,'123456',2),(15,4,'123456',2),(16,1,'CommentText',1),(17,1,'truyen hay',1),(18,1,'aaaaa',1),(19,1,'ddaa',1),(20,1,'aa',1),(21,1,'aahhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh',1),(22,3,'aaa',1),(23,1,'123',1),(24,2,'123',4),(25,9,'1234',1),(26,9,'123',1),(27,9,'long',10),(28,9,'13',1),(29,1,'123',11),(30,1,'5',11),(31,5,'5',11),(32,12,'123',4),(33,7,'123',4),(34,5,'6',4),(35,5,'4',1);
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commics`
--

DROP TABLE IF EXISTS `commics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commics` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `url` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commics`
--

LOCK TABLES `commics` WRITE;
/*!40000 ALTER TABLE `commics` DISABLE KEYS */;
INSERT INTO `commics` VALUES (1,'Bảo vệ trẻ em trên mạng','Chapter 10','https://www.mattrancantho.vn/files/images/2022/1-4.png'),(2,'Tình yêu và số phận','Chapter 20','https://img.wattpad.com/cover/55667845-144-k795244.jpg'),(3,'Con Gái Của Loài Người!','Chapter 16','https://img.wattpad.com/cover/59875029-144-k677169.jpg'),(4,'KẺ HÀNH QUYẾT','Chapter 12','https://img.wattpad.com/cover/229607274-144-k350514.jpg'),(5,'[Đam mỹ] Tù binh của Ông trùm Mafia','Chapter 22','https://img.wattpad.com/cover/224248767-144-k914291.jpg'),(6,'Xin hãy chăm sóc em thật tốt','Chapter 12','https://img.wattpad.com/cover/224248767-144-k914291.jpg'),(7,'Lời Chúc Phúc Của Thánh Thần','Chapter 18','https://img.wattpad.com/cover/226361156-144-k198178.jpg'),(8,'Chuyện Của Vịt (Remake)','Chapter 12','https://img.wattpad.com/cover/277124336-144-k466709.jpg'),(9,'Những người quái dị','Chapter 14','https://img.wattpad.com/cover/212976166-144-k571659.jpg'),(10,'Nhật Ký Đơn Phương','Chapter 16','https://img.wattpad.com/cover/154249493-144-k827230.jpg'),(11,'Ừ thì sống','Chapter 12','https://img.wattpad.com/cover/170923865-144-k468663.jpg'),(12,'Câu Truyện Về Mẹ','Chapter 20','https://img.wattpad.com/cover/185718288-144-k181965.jpg');
/*!40000 ALTER TABLE `commics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `save_manga`
--

DROP TABLE IF EXISTS `save_manga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `save_manga` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `userId` int DEFAULT NULL,
  `commicId` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `userId` (`userId`),
  KEY `commicId` (`commicId`),
  CONSTRAINT `save_manga_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`Id`),
  CONSTRAINT `save_manga_ibfk_2` FOREIGN KEY (`commicId`) REFERENCES `commics` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `save_manga`
--

LOCK TABLES `save_manga` WRITE;
/*!40000 ALTER TABLE `save_manga` DISABLE KEYS */;
INSERT INTO `save_manga` VALUES (14,2,4),(15,2,1),(28,2,8),(44,11,5),(46,4,12),(47,4,7);
/*!40000 ALTER TABLE `save_manga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'chithanh2001','$2a$10$pqoV5UgqWbetlnz7xMoszeIIN0sieBRw4PuCLcAR28lZARLv/tMSK'),(2,'longnguyen','$2a$10$ZmSxwtugffDO0tDUkOZ3quuBUX0LuAuq3.7sUM8bK5jRxm5yzfAr2'),(3,'vuxuanson','$2a$10$yF1dvs3VhLHzGu4t5DxQKedbRw8lo2slUIu/HSGhq9as7pNTp41K.'),(4,'tranngochiep','$2a$10$Up/xRsEJatyFKEJlNJtaGObH8kEmtoq0HbceiqgBf7D3/J58KfTxO'),(5,'tranngochiep1','$2a$10$Rsw.WnIGsdUmnVlydmcZNu5zMGHZm66Dws6VE5OydM8BA6hx/vU0O'),(6,'nguyenhoanglong','$2a$10$GxNDx4XYIkPUkO4wAVE0Q.pp0NL9dVnbqk4zSVmyH6HgUQViXGY4.'),(7,'nguyenhoanglong1','$2a$10$jfYpKCHTbzovTEK80tQAqeyvJ3B3pjpb6YZVtpUlZN1DgXmUCA3gm'),(8,'nguyen','$2a$10$5OBi407DwP.qsXa/nt340OrJgBAOyrHtwaSoX6wxtkvT94V/EfLUK'),(9,'hoang','$2a$10$eqRefACof.04o7rr.8d8h.D3D21yvRazi.RHiwcnS7ccHkWGj8cOi'),(10,'long11','$2a$10$hr1XMSQcc0qqDufBFd6P6eVT6jN6iZMvp6LOAjw1HDQbZPuvWDoPa'),(11,'long1','$2a$10$g/Knf5kwvDXy4BULznjE1.kl5kCEEEh2KVzk1APW05VhBXUJEGqBa');
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

-- Dump completed on 2023-04-10 22:44:02
