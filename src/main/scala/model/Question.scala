package ru.circumflex.tutorials
package model

import ru.circumflex.orm._

class Question extends Record[Long, Question] with IdentityGenerator[Long, Question] {
  def relation = Question
  def PRIMARY_KEY = id

  val id = "id".BIGINT.AUTO_INCREMENT.NOT_NULL
  val body = "body".TEXT.NOT_NULL
  val createdAt = "created_at".TIMESTAMP.NOT_NULL.DEFAULT("current_timestamp")
  val opened = "opened".BOOLEAN.NOT_NULL.DEFAULT("true")
  val user = "user_id".BIGINT.NOT_NULL.REFERENCES(User).ON_DELETE(CASCADE).ON_UPDATE(CASCADE)

  def tags = inverseMany(Tag.question)
  def votes = inverseMany(QuestionVote.question)
}

object Question extends Question with Table[Long, Question] {

  def findTagged(tag:String): Seq[Question] = {
    val t = Tag AS "t"
    val q = Question AS "q"

    SELECT(q.*).FROM(q JOIN t).WHERE(t.name EQ tag).list
  }

  def findLast(count:Int):Seq[Question] = {
    (this AS "q").map(q => SELECT(q.*).FROM(q).ORDER_BY(q.createdAt DESC).LIMIT(count).list)

  }
}