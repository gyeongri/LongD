-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: longdi_beta
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `balencegame_question`
--

DROP TABLE IF EXISTS `balencegame_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `balencegame_question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(45) NOT NULL,
  `answer_1` varchar(45) NOT NULL,
  `answer_2` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `balencegame_question`
--

LOCK TABLES `balencegame_question` WRITE;
/*!40000 ALTER TABLE `balencegame_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `balencegame_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bucket_list`
--

DROP TABLE IF EXISTS `bucket_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bucket_list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `couple_list_id` int NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `couple_list_id` (`couple_list_id`),
  CONSTRAINT `bucket_list_ibfk_1` FOREIGN KEY (`couple_list_id`) REFERENCES `couple_list` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bucket_list`
--

LOCK TABLES `bucket_list` WRITE;
/*!40000 ALTER TABLE `bucket_list` DISABLE KEYS */;
INSERT INTO `bucket_list` VALUES (1,1,'버킷테스트'),(2,1,'버킷테스트2'),(3,1,'버킷테스트3'),(4,1,'버킷테스트4');
/*!40000 ALTER TABLE `bucket_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendar`
--

DROP TABLE IF EXISTS `calendar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendar` (
  `id` int NOT NULL AUTO_INCREMENT,
  `couple_list_id` int NOT NULL,
  `start` date NOT NULL,
  `end` date DEFAULT NULL,
  `type` int DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `couple_list_id` (`couple_list_id`),
  CONSTRAINT `calendar_ibfk_1` FOREIGN KEY (`couple_list_id`) REFERENCES `couple_list` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendar`
--

LOCK TABLES `calendar` WRITE;
/*!40000 ALTER TABLE `calendar` DISABLE KEYS */;
INSERT INTO `calendar` VALUES (18,1,'2024-02-09','2024-02-11',NULL,'빨간색',NULL,'red'),(19,1,'2024-02-05','2024-02-09',NULL,'주황색',NULL,'orange'),(20,1,'2024-02-05','2024-02-08',NULL,'노란색',NULL,'yellow'),(21,1,'2024-02-05','2024-02-07',NULL,'test','몰라','violet'),(22,1,'2024-02-14','2024-02-15',NULL,'test',NULL,'blue'),(23,1,'2024-02-05','2024-02-07',NULL,'test','이 정도면 뭐','green');
/*!40000 ALTER TABLE `calendar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chat_message`
--

DROP TABLE IF EXISTS `chat_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat_message` (
  `id` int NOT NULL AUTO_INCREMENT,
  `couple_list_id` int NOT NULL,
  `message` varchar(1000) NOT NULL,
  `send_user_id` varchar(20) NOT NULL,
  `send_timestamp` datetime NOT NULL,
  `read_state` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `couple_list_id` (`couple_list_id`),
  CONSTRAINT `chat_message_ibfk_1` FOREIGN KEY (`couple_list_id`) REFERENCES `couple_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat_message`
--

LOCK TABLES `chat_message` WRITE;
/*!40000 ALTER TABLE `chat_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `country_code` char(2) NOT NULL,
  `country_name` varchar(50) NOT NULL,
  PRIMARY KEY (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `couple_list`
--

DROP TABLE IF EXISTS `couple_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `couple_list` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_first` int DEFAULT NULL,
  `user_second` int DEFAULT NULL,
  `start_day` date DEFAULT NULL,
  `oneQA_index` int DEFAULT '1',
  `couple_img_url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id_a` (`user_first`),
  KEY `fk_user_id_b` (`user_second`),
  CONSTRAINT `fk_user_id_a` FOREIGN KEY (`user_first`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_user_id_b` FOREIGN KEY (`user_second`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `couple_list`
--

LOCK TABLES `couple_list` WRITE;
/*!40000 ALTER TABLE `couple_list` DISABLE KEYS */;
INSERT INTO `couple_list` VALUES (1,3,4,NULL,1,NULL),(3,6,7,'2024-02-10',NULL,NULL);
/*!40000 ALTER TABLE `couple_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery`
--

DROP TABLE IF EXISTS `gallery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery` (
  `id` int NOT NULL AUTO_INCREMENT,
  `couple_list_id` int NOT NULL,
  `plan_id` int DEFAULT NULL,
  `folder_name` varchar(45) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `place` varchar(45) DEFAULT NULL,
  `tag` varchar(45) DEFAULT NULL,
  `path_url` varchar(512) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `content` varchar(45) DEFAULT NULL,
  `type` int NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `couple_list_id` (`couple_list_id`),
  KEY `fk_plan_id` (`plan_id`),
  CONSTRAINT `fk_plan_id` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`id`),
  CONSTRAINT `gallery_ibfk_1` FOREIGN KEY (`couple_list_id`) REFERENCES `couple_list` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery`
--

LOCK TABLES `gallery` WRITE;
/*!40000 ALTER TABLE `gallery` DISABLE KEYS */;
INSERT INTO `gallery` VALUES (1,1,2,NULL,NULL,NULL,NULL,'https://picsum.photos/200/300',NULL,NULL,1,NULL,NULL),(2,1,2,NULL,NULL,NULL,NULL,'https://picsum.photos/200/300',NULL,NULL,2,NULL,NULL),(4,1,2,NULL,NULL,NULL,NULL,'https://picsum.photos/200/300',NULL,NULL,4,NULL,NULL),(5,1,2,NULL,NULL,NULL,NULL,'https://picsum.photos/200/300',NULL,NULL,5,NULL,NULL);
/*!40000 ALTER TABLE `gallery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gallery_category`
--

DROP TABLE IF EXISTS `gallery_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gallery_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `couple_list_id` int NOT NULL,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `couple_list_id` (`couple_list_id`),
  CONSTRAINT `gallery_category_ibfk_1` FOREIGN KEY (`couple_list_id`) REFERENCES `couple_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gallery_category`
--

LOCK TABLES `gallery_category` WRITE;
/*!40000 ALTER TABLE `gallery_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `gallery_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oneqa_answer`
--

DROP TABLE IF EXISTS `oneqa_answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oneqa_answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `oneQA_question_id` int NOT NULL,
  `couple_list_id` int NOT NULL,
  `user_key_A` int DEFAULT NULL,
  `user_key_B` int DEFAULT NULL,
  `answer_A` varchar(100) DEFAULT NULL,
  `answer_B` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `oneQA_question_id` (`oneQA_question_id`),
  KEY `couple_list_id` (`couple_list_id`),
  CONSTRAINT `oneqa_answer_ibfk_1` FOREIGN KEY (`oneQA_question_id`) REFERENCES `oneqa_question` (`id`),
  CONSTRAINT `oneqa_answer_ibfk_2` FOREIGN KEY (`couple_list_id`) REFERENCES `couple_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oneqa_answer`
--

LOCK TABLES `oneqa_answer` WRITE;
/*!40000 ALTER TABLE `oneqa_answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `oneqa_answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oneqa_question`
--

DROP TABLE IF EXISTS `oneqa_question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oneqa_question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oneqa_question`
--

LOCK TABLES `oneqa_question` WRITE;
/*!40000 ALTER TABLE `oneqa_question` DISABLE KEYS */;
/*!40000 ALTER TABLE `oneqa_question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `couple_list_id` int NOT NULL,
  `date_start` date DEFAULT NULL,
  `date_end` date DEFAULT NULL,
  `title` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `couple_list_id` (`couple_list_id`),
  CONSTRAINT `plan_ibfk_1` FOREIGN KEY (`couple_list_id`) REFERENCES `couple_list` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1,1,'2024-02-01','2024-02-02','test'),(2,1,'2024-02-03','2024-02-04','test2'),(3,1,'2024-02-05','2024-02-06','test3'),(4,1,'2024-02-06','2024-02-07','test4'),(5,1,'2024-02-08','2024-02-09','test5');
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan_info`
--

DROP TABLE IF EXISTS `plan_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plan_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `plan_id` int NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `info_type` varchar(255) DEFAULT NULL,
  `my_order` varchar(255) DEFAULT NULL,
  `title_url` varchar(255) DEFAULT NULL,
  `date` date NOT NULL,
  `latitude` bigint DEFAULT NULL,
  `longitude` bigint DEFAULT NULL,
  `memo` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `plan_id` (`plan_id`),
  CONSTRAINT `plan_info_ibfk_1` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_info`
--

LOCK TABLES `plan_info` WRITE;
/*!40000 ALTER TABLE `plan_info` DISABLE KEYS */;
INSERT INTO `plan_info` VALUES (1,1,'플랜인포제목','1','1',NULL,'2024-02-02',NULL,NULL,'테스트메모'),(2,2,'서브','2','2',NULL,'2024-02-04',NULL,NULL,'서브'),(3,2,'리얼서브',NULL,NULL,NULL,'2024-02-04',NULL,NULL,'ㄱ'),(4,2,'안녕','2','2',NULL,'2024-02-04',NULL,NULL,'반가워');
/*!40000 ALTER TABLE `plan_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags` (
  `id` varchar(255) NOT NULL,
  `gallery_id` int NOT NULL,
  `couple_list_id` int NOT NULL,
  `content` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `gallery_id` (`gallery_id`),
  KEY `couple_list_id` (`couple_list_id`),
  CONSTRAINT `tags_ibfk_1` FOREIGN KEY (`gallery_id`) REFERENCES `gallery` (`id`),
  CONSTRAINT `tags_ibfk_2` FOREIGN KEY (`couple_list_id`) REFERENCES `gallery` (`couple_list_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_zone`
--

DROP TABLE IF EXISTS `time_zone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time_zone` (
  `zone_name` varchar(35) NOT NULL,
  `country_code` char(2) NOT NULL,
  `abbreviation` varchar(6) NOT NULL,
  `time_start` decimal(11,0) NOT NULL,
  `gmt_offset` int NOT NULL,
  `dst` char(1) NOT NULL,
  PRIMARY KEY (`zone_name`),
  KEY `country_code` (`country_code`),
  CONSTRAINT `time_zone_ibfk_1` FOREIGN KEY (`country_code`) REFERENCES `country` (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_zone`
--

LOCK TABLES `time_zone` WRITE;
/*!40000 ALTER TABLE `time_zone` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_zone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `couple_list_id` int DEFAULT NULL,
  `user_id` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `provider` varchar(45) NOT NULL,
  `name` varchar(20) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `address_nation` varchar(20) NOT NULL,
  `address_city` varchar(20) DEFAULT NULL,
  `address_detail` varchar(45) DEFAULT NULL,
  `birth` varchar(255) DEFAULT NULL,
  `birth_year` varchar(255) DEFAULT NULL,
  `birth_month` varchar(255) DEFAULT NULL,
  `birth_day` varchar(255) DEFAULT NULL,
  `gender` varchar(10) NOT NULL,
  `profile_picture` varchar(1000) DEFAULT NULL,
  `profile_message` varchar(45) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `password_simple` int NOT NULL,
  `code` int NOT NULL,
  `state_message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `couple_list_id` (`couple_list_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`couple_list_id`) REFERENCES `couple_list` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,NULL,'1','a','kakao','a','a','a','a','a','1996-02-28',NULL,NULL,NULL,'a',NULL,NULL,'a',1234,1234,NULL),(4,NULL,'2','b','b','b','b','b','b','b','1994-04-05',NULL,NULL,NULL,'b',NULL,NULL,'b',4321,4321,NULL),(6,3,'NULL방지','bona3377@knu.ac.kr','NULL방지','ㅇㅇㅇ','ㅇㅇ','NULL방지',NULL,NULL,'2024-02-14','NULL방지','NULL방지','NULL방지','NULL방지',NULL,NULL,NULL,214,13,NULL),(7,3,'3299671981','abcd@abcd.com','kakao','손동천','닉네임','NULL방지',NULL,NULL,'2024-02-01','NULL방지','NULL방지','NULL방지','남성',NULL,NULL,NULL,201,1234,NULL);
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

-- Dump completed on 2024-02-05  0:12:03
