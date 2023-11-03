-- MySQL dump 10.13  Distrib 8.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: scrumplay
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `game_details`
--

DROP TABLE IF EXISTS `game_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_details` (
  `game_id` int NOT NULL,
  `game_start` datetime DEFAULT NULL,
  `game_end` datetime DEFAULT NULL,
  `scrumcall_duration` int DEFAULT NULL,
  PRIMARY KEY (`game_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_details`
--

LOCK TABLES `game_details` WRITE;
/*!40000 ALTER TABLE `game_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `game_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `junction_table`
--

DROP TABLE IF EXISTS `junction_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `junction_table` (
  `game_id` int NOT NULL,
  `sprint_id` int NOT NULL,
  `player_id` int NOT NULL,
  `us_id` int NOT NULL,
  PRIMARY KEY (`game_id`,`sprint_id`,`player_id`,`us_id`),
  KEY `sprint_id` (`sprint_id`),
  KEY `player_id` (`player_id`),
  KEY `us_id` (`us_id`),
  CONSTRAINT `junction_table_ibfk_1` FOREIGN KEY (`game_id`) REFERENCES `game_details` (`game_id`),
  CONSTRAINT `junction_table_ibfk_2` FOREIGN KEY (`sprint_id`) REFERENCES `sprint_details` (`sprint_id`),
  CONSTRAINT `junction_table_ibfk_3` FOREIGN KEY (`player_id`) REFERENCES `player_details` (`player_id`),
  CONSTRAINT `junction_table_ibfk_4` FOREIGN KEY (`us_id`) REFERENCES `us_details` (`us_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `junction_table`
--

LOCK TABLES `junction_table` WRITE;
/*!40000 ALTER TABLE `junction_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `junction_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player_details`
--

DROP TABLE IF EXISTS `player_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player_details` (
  `player_id` int NOT NULL,
  `player_name` varchar(300) DEFAULT NULL,
  `role` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player_details`
--

LOCK TABLES `player_details` WRITE;
/*!40000 ALTER TABLE `player_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `player_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ps_details`
--

DROP TABLE IF EXISTS `ps_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ps_details` (
  `ps_id` int NOT NULL,
  `ps_title` varchar(1000) DEFAULT NULL,
  `ps_notes` varchar(5000) DEFAULT NULL,
  `us_id` int DEFAULT NULL,
  KEY `us_id` (`us_id`),
  CONSTRAINT `ps_details_ibfk_1` FOREIGN KEY (`us_id`) REFERENCES `us_details` (`us_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ps_details`
--

LOCK TABLES `ps_details` WRITE;
/*!40000 ALTER TABLE `ps_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `ps_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sprint_details`
--

DROP TABLE IF EXISTS `sprint_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sprint_details` (
  `sprint_id` int NOT NULL,
  `team_size` int NOT NULL,
  `sprint_length` int NOT NULL,
  PRIMARY KEY (`sprint_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sprint_details`
--

LOCK TABLES `sprint_details` WRITE;
/*!40000 ALTER TABLE `sprint_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `sprint_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `us_details`
--

DROP TABLE IF EXISTS `us_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `us_details` (
  `us_id` int NOT NULL,
  `us_title` varchar(300) DEFAULT NULL,
  `us_description` varchar(600) DEFAULT NULL,
  `us_points` int DEFAULT NULL,
  `us_status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`us_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `us_details`
--

LOCK TABLES `us_details` WRITE;
/*!40000 ALTER TABLE `us_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `us_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-31 23:20:21
