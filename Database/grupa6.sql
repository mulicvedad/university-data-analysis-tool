CREATE DATABASE  IF NOT EXISTS `grupa6` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `grupa6`;
-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: grupa6
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `academic_year_dim`
--

DROP TABLE IF EXISTS `academic_year_dim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `academic_year_dim` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `academic_year_id` int(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  `active` int(10) NOT NULL,
  `start_year` smallint(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `academic_year_dim`
--

LOCK TABLES `academic_year_dim` WRITE;
/*!40000 ALTER TABLE `academic_year_dim` DISABLE KEYS */;
/*!40000 ALTER TABLE `academic_year_dim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance_fact`
--

DROP TABLE IF EXISTS `attendance_fact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attendance_fact` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `course_dim_id` int(10) NOT NULL,
  `department_dim_id` int(10) NOT NULL,
  `time_dim_id` int(10) NOT NULL,
  `lecturer_dim_id` int(10) NOT NULL,
  `attendance` int(10) NOT NULL,
  `attendance_precentage` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `class_fact` (`id`),
  KEY `fk_attendance_fact_1_idx` (`course_dim_id`),
  KEY `fk_attendance_fact_2_idx` (`department_dim_id`),
  KEY `fk_attendance_fact_3_idx` (`time_dim_id`),
  KEY `fk_attendance_fact_4_idx` (`lecturer_dim_id`),
  CONSTRAINT `fk_attendance_fact_1` FOREIGN KEY (`course_dim_id`) REFERENCES `course_dim` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_attendance_fact_2` FOREIGN KEY (`department_dim_id`) REFERENCES `department_dim` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_attendance_fact_3` FOREIGN KEY (`time_dim_id`) REFERENCES `time_dim` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_attendance_fact_4` FOREIGN KEY (`lecturer_dim_id`) REFERENCES `lecturer_dim` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance_fact`
--

LOCK TABLES `attendance_fact` WRITE;
/*!40000 ALTER TABLE `attendance_fact` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance_fact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_dim`
--

DROP TABLE IF EXISTS `course_dim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_dim` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `course_id` int(10) NOT NULL,
  `title` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_dim` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_dim`
--

LOCK TABLES `course_dim` WRITE;
/*!40000 ALTER TABLE `course_dim` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_dim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department_dim`
--

DROP TABLE IF EXISTS `department_dim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department_dim` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `department_id` int(10) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `department_dim` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_dim`
--

LOCK TABLES `department_dim` WRITE;
/*!40000 ALTER TABLE `department_dim` DISABLE KEYS */;
/*!40000 ALTER TABLE `department_dim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment_fact`
--

DROP TABLE IF EXISTS `enrollment_fact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enrollment_fact` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `academic_year_dim_id` int(10) NOT NULL,
  `department_dim_id` int(10) NOT NULL,
  `semester_dim_id` int(10) NOT NULL,
  `budget` tinyint(1) NOT NULL,
  `is_repeating` tinyint(1) NOT NULL,
  `enrolled_count` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_fact` (`id`),
  KEY `fk_enrollment_fact_1_idx` (`semester_dim_id`),
  KEY `fk_enrollment_fact_2_idx` (`department_dim_id`),
  KEY `fk_enrollment_fact_3_idx` (`academic_year_dim_id`),
  CONSTRAINT `fk_enrollment_fact_1` FOREIGN KEY (`semester_dim_id`) REFERENCES `semester_dim` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_enrollment_fact_2` FOREIGN KEY (`department_dim_id`) REFERENCES `department_dim` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_enrollment_fact_3` FOREIGN KEY (`academic_year_dim_id`) REFERENCES `academic_year_dim` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=601 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment_fact`
--

LOCK TABLES `enrollment_fact` WRITE;
/*!40000 ALTER TABLE `enrollment_fact` DISABLE KEYS */;
/*!40000 ALTER TABLE `enrollment_fact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_fact`
--

DROP TABLE IF EXISTS `exam_fact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_fact` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `academic_year_dim_id` int(10) NOT NULL,
  `scheduled_date_dim_id` int(10) NOT NULL,
  `semester_dim_id` int(10) NOT NULL,
  `department_dim_id` int(10) NOT NULL,
  `course_dim_id` int(10) NOT NULL,
  `average_points` decimal(19,0) NOT NULL,
  `turnout` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `exam_fact` (`id`),
  KEY `fk_exam_fact_2_idx` (`scheduled_date_dim_id`),
  KEY `fk_exam_fact_3_idx` (`course_dim_id`),
  KEY `fk_exam_fact_4_idx` (`department_dim_id`),
  KEY `fk_exam_fact_5_idx` (`semester_dim_id`),
  KEY `fk_exam_fact_1_idx` (`academic_year_dim_id`),
  CONSTRAINT `fk_exam_fact_1` FOREIGN KEY (`academic_year_dim_id`) REFERENCES `academic_year_dim` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_exam_fact_2` FOREIGN KEY (`scheduled_date_dim_id`) REFERENCES `time_dim` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_exam_fact_3` FOREIGN KEY (`course_dim_id`) REFERENCES `course_dim` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_exam_fact_4` FOREIGN KEY (`department_dim_id`) REFERENCES `department_dim` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_exam_fact_5` FOREIGN KEY (`semester_dim_id`) REFERENCES `semester_dim` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exam_fact`
--

LOCK TABLES `exam_fact` WRITE;
/*!40000 ALTER TABLE `exam_fact` DISABLE KEYS */;
/*!40000 ALTER TABLE `exam_fact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `import_time`
--

DROP TABLE IF EXISTS `import_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `import_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name_of_table` varchar(45) DEFAULT NULL,
  `time_of_import` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `import_time`
--

LOCK TABLES `import_time` WRITE;
/*!40000 ALTER TABLE `import_time` DISABLE KEYS */;
/*!40000 ALTER TABLE `import_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lecturer_dim`
--

DROP TABLE IF EXISTS `lecturer_dim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lecturer_dim` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `lecturer_zamger_user_id` int(10) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `is_student` tinyint(1) NOT NULL,
  `salary` decimal(10,0) NOT NULL,
  `gender` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `lecturer_dim` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1615 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lecturer_dim`
--

LOCK TABLES `lecturer_dim` WRITE;
/*!40000 ALTER TABLE `lecturer_dim` DISABLE KEYS */;
/*!40000 ALTER TABLE `lecturer_dim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester_dim`
--

DROP TABLE IF EXISTS `semester_dim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `semester_dim` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `semester_id` int(10) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `semester_dim` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester_dim`
--

LOCK TABLES `semester_dim` WRITE;
/*!40000 ALTER TABLE `semester_dim` DISABLE KEYS */;
/*!40000 ALTER TABLE `semester_dim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_dim`
--

DROP TABLE IF EXISTS `time_dim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_dim` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `full_date` date NOT NULL,
  `year` smallint(5) NOT NULL,
  `month_of_year` smallint(5) NOT NULL,
  `day_of_month` smallint(5) NOT NULL,
  `hour` smallint(5) NOT NULL,
  `month` varchar(20) NOT NULL,
  `day_of_week` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `date_dim` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_dim`
--

LOCK TABLES `time_dim` WRITE;
/*!40000 ALTER TABLE `time_dim` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_dim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(2000) NOT NULL,
  `email` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `role_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKuser994439` (`role_id`),
  CONSTRAINT `FKuser994439` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Admin','Adminkovic','admin','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','admin@etf.unsa.ba','2017-11-11 14:07:31',1),(2,'Neko','Nekic','neko','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','neko@etf.unsa.ba','2017-11-11 14:07:31',2);
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

-- Dump completed on 2017-12-21  0:54:56
