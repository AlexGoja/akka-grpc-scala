package com.scalagrpcakka.questionaire.storage.mysql

import com.scalagrpcakka.questionaire.storage.Storage
import explori.Tables._
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}


class MysqlImpl extends Storage {

  def getQuestion(id: String): Future[Unit] = {

    val db = Database.forConfig("mysql")

//    val blob = db.createSession().conn.createBlob()
    ////    blob.setBytes(1, id.getBytes)

//    val action = sql"select surveyid from surveydatastr".as[(String)]
//    db.run(action)

//    val bytes:Array[Byte] = id.getBytes
//    val string: String = bytes.map(_.toChar).mkString

    println(new String(id.getBytes))

    val q: Future[Unit] = db.run(
      Surveydatastr
        .join(Surveydataopt)
        .on(_.surveyid === _.surveyid)
        .map(_._1.surveyid)
        .result
        .filter(s => s.map(l => l.filter(b => new String(b.getBytes(1, b.length().toInt)) === id)))
        .map(s => println( s.map( l => new String(l.get.getBytes(1, l.get.length().toInt)).getBytes)))





    val duration = Duration(100, "seconds")
    Await.result(q, duration)


    //    val q = Surveydatastr
//      .filter(s => s.surveyid.r.get.asInstanceOf[Blob].getBytes(1, s.surveyid.r.get.asInstanceOf[Blob].length().asInstanceOf[Int]) sameElements  id.getBytes)
//      .join(Surveydataopt)
//      .on(_.surveyid === _.surveyid)
//      .map{case(survey, surveyOpt) => (survey, surveyOpt)}
//      .result
//      .headOption

//val q = Surveydatastr
//  .filter(_.surveyid.asColumnOf[Array[Byte]] === id.getBytes())
//  .join(Surveydataopt)
//  .on(_.surveyid === _.surveyid)
//  .result
//  .headOption



//    val f = db.run(q)
//
//    f

    q

  }

}


//DROP TABLE IF EXISTS `surveydataopt`;
//CREATE TABLE `surveydataopt`  (
//`id` int(11) NOT NULL AUTO_INCREMENT,
//`surveyId` binary(16) NULL DEFAULT NULL,
//`surveyId_txt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`surveyId`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`surveyDataId` binary(16) NULL DEFAULT NULL,
//`surveyDataId_txt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`surveyDataId`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`set1` binary(16) NULL DEFAULT NULL,
//`set1_txt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`set1`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`set2` binary(16) NULL DEFAULT NULL,
//`set2_txt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`set2`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`pagequestionId` binary(16) NULL DEFAULT NULL,
//`pagequestionId_txt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`pagequestionId`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`priority` tinyint(3) UNSIGNED NULL DEFAULT NULL,
//`questionid` binary(16) NULL DEFAULT NULL,
//`questionid_txt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`questionid`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`type` int(11) NULL DEFAULT NULL,
//`startdate` timestamp(0) NULL DEFAULT NULL,
//`completeddate` datetime(0) NULL DEFAULT NULL,
//`status` int(4) NULL DEFAULT NULL,
//`campaignid` int(11) NULL DEFAULT NULL,
//`surveyUserListId` binary(16) NULL DEFAULT NULL,
//`surveyUserListId_txt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`surveyUserListId`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`importId` binary(16) NULL DEFAULT NULL,
//`importId_txt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`importId`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`importRecordId` int(5) NULL DEFAULT NULL,
//PRIMARY KEY (`id`) USING BTREE,
//INDEX `idx_surveydataopt_1`(`surveyDataId`) USING BTREE,
//INDEX `idx_surveydataopt_3`(`type`) USING BTREE,
//INDEX `idx_surveydataopt_4`(`startdate`) USING BTREE,
//INDEX `idx_surveydataopt_5`(`completeddate`) USING BTREE,
//INDEX `idx_surveydataopt_6`(`status`) USING BTREE,
//INDEX `idx_surveydataopt_7`(`campaignid`) USING BTREE,
//INDEX `idx_surveydataopt_8`(`type`, `status`) USING BTREE,
//INDEX `idx_surveydataopt_9`(`set1`) USING BTREE,
//INDEX `idx_surveydataopt_10`(`set2`) USING BTREE,
//INDEX `idx_surveydataopt_11`(`questionid`) USING BTREE,
//INDEX `idx_surveydataopt_12`(`set1`, `set2`) USING BTREE,
//INDEX `idx_surveydataopt_13`(`surveyId`) USING BTREE,
//INDEX `idx_surveydataopt_14`(`surveyId`, `pagequestionId`, `type`, `status`) USING BTREE,
//INDEX `idx_surveydataopt_15`(`surveyId`, `questionid`, `type`, `status`) USING BTREE,
//INDEX `idx_surveydataopt_16`(`pagequestionId`) USING BTREE,
//INDEX `idx_surveydataopt_17`(`surveyId`, `set1`, `set2`, `questionid`) USING BTREE,
//CONSTRAINT `surveydataopt_ibfk_1` FOREIGN KEY (`surveyDataId`) REFERENCES `surveydata` (`surveyDataId`) ON DELETE CASCADE ON UPDATE CASCADE
//) ENGINE = InnoDB AUTO_INCREMENT = 85499500 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
//
//
//DROP TABLE IF EXISTS `surveydatastr`;
//CREATE TABLE `surveydatastr`  (
//`id` int(11) NOT NULL AUTO_INCREMENT,
//`surveyId` binary(16) NULL DEFAULT NULL,
//`surveyId_txt` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`surveyId`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`surveyDataId` binary(16) NULL DEFAULT NULL,
//`surveyDataId_txt` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`surveyDataId`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`str` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
//`pagequestionId` binary(16) NULL DEFAULT NULL,
//`pagequestionId_txt` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`pagequestionId`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`questionid` binary(16) NULL DEFAULT NULL,
//`questionid_txt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`questionid`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`questionOptionId` binary(16) NULL DEFAULT NULL,
//`questionOptionId_txt` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`questionOptionId`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`type` int(11) NULL DEFAULT NULL,
//`startdate` datetime(0) NULL DEFAULT NULL,
//`completeddate` datetime(0) NULL DEFAULT NULL,
//`status` int(11) NULL DEFAULT NULL,
//`campaignid` int(11) NULL DEFAULT NULL,
//`surveyUserListId` binary(16) NULL DEFAULT NULL,
//`surveyUserListId_txt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`surveyUserListId`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`importId` binary(16) NULL DEFAULT NULL,
//`importId_txt` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci GENERATED ALWAYS AS (insert(insert(insert(insert(hex(`importId`),9,0,'-'),14,0,'-'),19,0,'-'),24,0,'-')) VIRTUAL NULL,
//`importRecordId` int(5) NULL DEFAULT NULL,
//PRIMARY KEY (`id`) USING BTREE,



