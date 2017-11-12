CREATE DATABASE  IF NOT EXISTS `grupa6` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `grupa6`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: grupa6
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `class_fact`
--

DROP TABLE IF EXISTS `class_fact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class_fact` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `class_id` int(10) NOT NULL,
  `course_dim_id` int(10) NOT NULL,
  `department_dim_id` int(10) NOT NULL,
  `date_dim_id` int(10) NOT NULL,
  `lecturer_dim_id` int(10) NOT NULL,
  `attendance` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `class_fact` (`id`),
  KEY `FKclass_fact899134` (`course_dim_id`),
  KEY `FKclass_fact261084` (`department_dim_id`),
  KEY `FKclass_fact772971` (`lecturer_dim_id`),
  KEY `FKclass_fact462293` (`date_dim_id`),
  CONSTRAINT `FKclass_fact261084` FOREIGN KEY (`department_dim_id`) REFERENCES `department_dim` (`id`),
  CONSTRAINT `FKclass_fact462293` FOREIGN KEY (`date_dim_id`) REFERENCES `date_dim` (`id`),
  CONSTRAINT `FKclass_fact772971` FOREIGN KEY (`lecturer_dim_id`) REFERENCES `lecturer_dim` (`id`),
  CONSTRAINT `FKclass_fact899134` FOREIGN KEY (`course_dim_id`) REFERENCES `course_dim` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_fact`
--

LOCK TABLES `class_fact` WRITE;
/*!40000 ALTER TABLE `class_fact` DISABLE KEYS */;
/*!40000 ALTER TABLE `class_fact` ENABLE KEYS */;
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
  `department` varchar(100) NOT NULL,
  `study_year` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_dim` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_dim`
--

LOCK TABLES `course_dim` WRITE;
/*!40000 ALTER TABLE `course_dim` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_dim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `date_dim`
--

DROP TABLE IF EXISTS `date_dim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `date_dim` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `year` int(10) NOT NULL,
  `month_of_year` int(10) NOT NULL,
  `day_of_month` int(10) NOT NULL,
  `month` int(10) NOT NULL,
  `day_of_week` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `date_dim` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `date_dim`
--

LOCK TABLES `date_dim` WRITE;
/*!40000 ALTER TABLE `date_dim` DISABLE KEYS */;
INSERT INTO `date_dim` VALUES (1,'2013-09-09',2013,6,6,6,6),(2,'2012-08-08',2012,8,8,8,8);
/*!40000 ALTER TABLE `date_dim` ENABLE KEYS */;
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
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `department_dim` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_dim`
--

LOCK TABLES `department_dim` WRITE;
/*!40000 ALTER TABLE `department_dim` DISABLE KEYS */;
INSERT INTO `department_dim` VALUES (1,123,'RI','2010-01-01',NULL);
/*!40000 ALTER TABLE `department_dim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exam_fact`
--

DROP TABLE IF EXISTS `exam_fact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exam_fact` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `exam_id` int(10) NOT NULL,
  `scheduled_date_dim_id` int(10) NOT NULL,
  `semester_dim_id` int(10) NOT NULL,
  `department_dim_id` int(10) NOT NULL,
  `course_dim_id` int(10) NOT NULL,
  `average_points` decimal(19,0) NOT NULL,
  `turnout` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `exam_fact` (`id`),
  KEY `FKexam_fact658995` (`course_dim_id`),
  KEY `FKexam_fact20945` (`department_dim_id`),
  KEY `FKexam_fact157201` (`semester_dim_id`),
  KEY `FKexam_fact906522` (`scheduled_date_dim_id`),
  CONSTRAINT `FKexam_fact157201` FOREIGN KEY (`semester_dim_id`) REFERENCES `semester_dim` (`id`),
  CONSTRAINT `FKexam_fact20945` FOREIGN KEY (`department_dim_id`) REFERENCES `department_dim` (`id`),
  CONSTRAINT `FKexam_fact658995` FOREIGN KEY (`course_dim_id`) REFERENCES `course_dim` (`id`),
  CONSTRAINT `FKexam_fact906522` FOREIGN KEY (`scheduled_date_dim_id`) REFERENCES `date_dim` (`id`)
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
-- Table structure for table `lab_group_dim`
--

DROP TABLE IF EXISTS `lab_group_dim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lab_group_dim` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `lab_group_id` int(10) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `course` varchar(255) NOT NULL,
  `department` varchar(255) NOT NULL,
  `academic_year` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `lab_group_dim` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lab_group_dim`
--

LOCK TABLES `lab_group_dim` WRITE;
/*!40000 ALTER TABLE `lab_group_dim` DISABLE KEYS */;
INSERT INTO `lab_group_dim` VALUES (1,123,'G1','MLTI','RI',2013);
/*!40000 ALTER TABLE `lab_group_dim` ENABLE KEYS */;
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
  `salary` decimal(19,0) DEFAULT NULL,
  `gender` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `lecturer_dim` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `semester_dim` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester_dim`
--

LOCK TABLES `semester_dim` WRITE;
/*!40000 ALTER TABLE `semester_dim` DISABLE KEYS */;
INSERT INTO `semester_dim` VALUES (1,123,'Zimski','2013-09-09','2014-02-02');
/*!40000 ALTER TABLE `semester_dim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_fact`
--

DROP TABLE IF EXISTS `student_fact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_fact` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `student_user_id` int(10) DEFAULT NULL,
  `jmbg` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(64) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `username` varchar(64) NOT NULL,
  `birthdate` date NOT NULL,
  `gender` int(10) NOT NULL,
  `lab_group_dim_id` int(10) NOT NULL,
  `enrollment_date_dim_id` int(10) NOT NULL,
  `dissrollment_date_dim_id` int(10) DEFAULT NULL,
  `department_dim_id` int(10) NOT NULL,
  `graduation_date_dim_id` int(10) DEFAULT NULL,
  `current_semester_dim_id` int(10) NOT NULL,
  `study_year` int(10) NOT NULL,
  `budget` tinyint(1) NOT NULL,
  `average_grade` decimal(19,0) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `student_fact` (`id`),
  KEY `FKstudent_fa585220` (`enrollment_date_dim_id`),
  KEY `FKstudent_fa871910` (`current_semester_dim_id`),
  KEY `FKstudent_fa451922` (`lab_group_dim_id`),
  KEY `FKstudent_fa734639` (`department_dim_id`),
  KEY `FKstudent_fa113871` (`graduation_date_dim_id`),
  KEY `FKstudent_fa698025` (`dissrollment_date_dim_id`),
  CONSTRAINT `FKstudent_fa113871` FOREIGN KEY (`graduation_date_dim_id`) REFERENCES `date_dim` (`id`),
  CONSTRAINT `FKstudent_fa451922` FOREIGN KEY (`lab_group_dim_id`) REFERENCES `lab_group_dim` (`id`),
  CONSTRAINT `FKstudent_fa585220` FOREIGN KEY (`enrollment_date_dim_id`) REFERENCES `date_dim` (`id`),
  CONSTRAINT `FKstudent_fa698025` FOREIGN KEY (`dissrollment_date_dim_id`) REFERENCES `date_dim` (`id`),
  CONSTRAINT `FKstudent_fa734639` FOREIGN KEY (`department_dim_id`) REFERENCES `department_dim` (`id`),
  CONSTRAINT `FKstudent_fa871910` FOREIGN KEY (`current_semester_dim_id`) REFERENCES `semester_dim` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_fact`
--

LOCK TABLES `student_fact` WRITE;
/*!40000 ALTER TABLE `student_fact` DISABLE KEYS */;
INSERT INTO `student_fact` VALUES (1,123,'11111','Edin','Begic','ed@ef.com','31. juli','edobraca','1994-10-17',0,1,1,NULL,1,NULL,1,1,1,8),(2,345,'2345','Vedad','Mulic','ved@ef.com','kg','sovedeckic','1994-01-01',0,1,2,NULL,1,NULL,1,1,1,9);
/*!40000 ALTER TABLE `student_fact` ENABLE KEYS */;
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

-- Dump completed on 2017-11-12 20:05:41
