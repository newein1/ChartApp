-- MySQL dump 10.13  Distrib 5.6.36, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: healthcare
-- ------------------------------------------------------
-- Server version	5.6.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES UTF8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result` (
  `ResultId` int(10) NOT NULL AUTO_INCREMENT,
  `UserId` int(10) NOT NULL,
  `MacId` varchar(245) DEFAULT NULL,
  `Height` float DEFAULT NULL,
  `Weight` float DEFAULT NULL,
  `Spo2` float DEFAULT NULL,
  `Temp` float DEFAULT NULL,
  `Bloodpressure` varchar(255) DEFAULT NULL,
  `HeartRate` float DEFAULT NULL,
  PRIMARY KEY (`ResultId`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (1,0,'bb-cc-ff',10,10,10,10,'10',NULL),(2,0,'bb-cc-ff-ee-zz-xx',176,80,1,35,'33',NULL),(3,0,'bb-cc-ff-ef-zz-zz',176,82,1,37,'33',NULL),(4,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(14,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(25,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(26,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(27,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(28,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(29,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(31,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(32,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(33,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(34,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(35,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(36,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(37,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(38,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(39,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(40,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(41,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(42,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(43,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(44,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(45,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(46,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(47,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(48,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(49,0,'12',123,123,11,11,'11',NULL),(50,0,'',0,0,0,0,'0',NULL),(51,0,'1',32,41,100,32,'0',NULL),(52,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(53,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,0,'0',123,188,22,22,'22',NULL),(55,0,'null',123,188,22,22,'22',70),(56,0,'null',123,188,22,22,'22',76),(57,0,'null',123,188,22,42,'100',81),(58,0,'1:2:3:4',123,188,22,42,'100',73),(59,0,'1:2:3:4',123,188,22,42,'100',NULL),(60,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(61,0,'null',123,188,22,22,'1000',NULL),(62,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(63,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(64,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(65,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(66,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(67,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(68,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(69,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(71,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(72,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(73,0,'0',0,170,0,60,'0',NULL),(74,0,'0',0,170,0,60,'0',NULL),(75,0,'0',0,170,0,60,'0',NULL),(76,0,'0',0,170,0,60,'0',NULL),(77,0,'0',0,170,0,60,'0',NULL),(78,0,'0',170,60,100,40,'0',NULL),(79,0,'69:69:69:69:69:69',170,60,100,40,'0',NULL),(80,0,'69:69:69:69:69:69',170,60,100,40,'0',NULL),(81,0,'69:69:69:69:69:69',0,0,0,0,'0',NULL),(82,0,'69:69:69:69:69:69',0,0,0,0,'0',NULL),(83,0,'69:69:69:69:69:69',0,0,0,0,'0',70),(84,0,'69:69:69:69:69:69',0,0,0,0,'0',70),(85,0,'69:69:69:69:69:69',0,0,0,0,'0',70),(86,0,'69:69:69:69:69:69',0,0,0,0,'0',70),(87,0,'69:69:69:69:69:69',0,0,0,0,'0',70),(88,0,'69:69:69:69:69:69',0,0,0,0,'0',0),(89,0,'69:69:69:69:69:69',0,11.8,0,0,'0',0),(90,0,'30:75:12:be:b7:21',179,64,100,34,'120/80',72),(91,0,'30:75:12:be:b7:21',179,64,100,34,'122/85',76),(92,0,'30:75:12:be:b7:21',174,70,99,33,'123/86',78),(93,0,'30:75:12:be:b7:21',160,63,99,35,'127/83',80),(94,0,'30:75:12:be:b7:21',170,56,99,32,'0',70),(95,0,'30:75:12:be:b7:21',175,72,99,33,'0',65),(96,0,'30:75:12:be:b7:21',175,72,99,33,'117/70',65),(97,0,'0',0,0,0,67,',',0),(98,0,'0',0,0,0,67,',',0),(99,0,'0',0,0,0,67,',',0),(100,0,'0',0,0,0,67,',',0),(101,0,'0',0,0,0,67,',',0),(102,0,'0',0,0,0,0,',',0),(103,0,'0',0,0,0,0,',',0),(104,0,'0',0,0,0,0,',',0),(105,0,'0',0,0,0,0,',',0),(106,0,'0',0,0,0,0,',',0),(107,0,'0',0,0,0,0,',',0),(108,0,'0',0,0,0,0,',',0),(109,0,'0',0,0,0,67,'0',100),(110,0,'0',0,0,0,67,'0',100),(111,0,'0',0,67,100,0,'0',70),(112,0,'0',0,67,100,0,'0',70),(113,0,'0',0,67,100,36.5,'0',70),(114,0,'69:69:69:69:69:69',0,67,100,36.5,'0',70),(115,0,'69:69:69:69:69:69',0,67,100,36.5,'0',70),(116,0,'69:69:69:69:69:69',0,0.3,0,0,'0',0),(117,0,'69:69:69:69:69:69',0,0.3,0,0,'0',0),(118,0,'69:69:69:69:69:69',0,0.3,0,0,'0',0),(119,0,'69:69:69:69:69:69',0,0.3,0,0,'0',0),(120,0,'69:69:69:69:69:69',0,0,0,0,'0',0),(121,0,'69:69:69:69:69:69',0,0.4,0,0,'0',0),(122,0,'69:69:69:69:69:69',177,67.7,0,36.57,'0',0),(123,0,'69:69:69:69:69:69',177,71,0,36.48,'0',0),(124,0,'69:69:69:69:69:69',177,69.9,100,0,'0',93),(125,0,'69:69:69:69:69:69',178,70.8,97,37.23,'0',54),(126,0,'69:69:69:69:69:69',177,69.7,98,0,'0',67),(127,0,'69:69:69:69:69:69',177,66.1,98,36.7,'0',98),(128,0,'69:69:69:69:69:69',167,58.9,99,36.88,'0',72),(129,0,'69:69:69:69:69:69',174,70,100,36.88,'0',93),(130,0,'69:69:69:69:69:69',176,71,70,36.8,'0',120),(131,0,'69:69:69:69:69:69',175,69.7,100,37.19,'0',88),(132,0,'69:69:69:69:69:69',179,70.8,100,36.97,'0',93),(133,0,'69:69:69:69:69:69',181,70.4,99,37.51,'0',105),(134,0,'69:69:69:69:69:69',180,68.3,100,36.85,'0',107),(135,0,'69:69:69:69:69:69',181,68.3,99,36.92,'0',101),(136,0,'69:69:69:69:69:69',124,-0.1,0,37.04,'0',0),(137,0,'69:69:69:69:69:69',180,72.4,99,37.19,'0',103),(138,0,'69:69:69:69:69:69',180,72,99,36.7,'0',98),(139,0,'69:69:69:69:69:69',181,72.2,100,36.85,'0',92),(140,0,'69:69:69:69:69:69',180,71.8,100,36.97,'0',100),(141,0,'69:69:69:69:69:69',182,72.5,100,36.7,'0',83),(142,0,'69:69:69:69:69:69',180,71.5,99,36.88,'0',83),(143,0,'69:69:69:69:69:69',-4305,71.2,100,36.97,'0',90),(144,0,'69:69:69:69:69:69',182,71.1,100,36.63,'0',92),(145,0,'69:69:69:69:69:69',180,71.3,100,36.7,'0',86),(146,0,'69:69:69:69:69:69',178,81.8,96,36.88,'0',80),(147,0,'69:69:69:69:69:69',177,82.9,94,36.67,'0',150),(148,0,'69:69:69:69:69:69',176,83.6,53,36.97,'0',176),(149,0,'69:69:69:69:69:69',177,59.6,99,36.48,'0',58),(150,0,'69:69:69:69:69:69',107,81.7,100,36.92,'0',66),(151,0,'69:69:69:69:69:69',180,71.8,100,37.23,'0',86),(152,0,'69:69:69:69:69:69',181,72.1,100,37.23,'0',85),(153,0,'69:69:69:69:69:69',181,72.4,100,37.19,'0',93),(154,0,'69:69:69:69:69:69',179,72.2,99,37.04,'0',93),(155,0,'69:69:69:69:69:69',-4012,66.9,100,50.63,'0',105),(156,0,'69:69:69:69:69:69',180,59.5,100,36.8,'0',136);
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) NOT NULL,
  `PassWord` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Quang','123456','quanghuynh@gmail.com'),(2,'QuangHuynh',NULL,NULL),(3,'QuangHuynh2',NULL,'quanghuynh3695@gmail.com'),(4,'Newein',NULL,'quanghuynh@gmail.com'),(5,'Quang123',NULL,'quanghuynh@gmail.com'),(6,'QuangPro',NULL,'quanghuynh@gmail.com'),(7,'QuangProHuynh',NULL,'newphysicsking2000@gmail.com');
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

-- Dump completed on 2017-12-28  8:12:13
