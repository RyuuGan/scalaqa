package ru.circumflex.tutorials
package model

import ru.circumflex.orm._

class AnswerComment extends Record[Long, AnswerComment] with IdentityGenerator[Long, AnswerComment] {
  def relation = AnswerComment
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val body = "body".TEXT.NOT_NULL
  val createdAt = "created_at".TIMESTAMP.NOT_NULL.DEFAULT("current_timestamp")
  val answer = "answer_id".BIGINT.NOT_NULL.REFERENCES(Answer).ON_DELETE(CASCADE).ON_UPDATE(CASCADE)
  val user = "user_id".BIGINT.NOT_NULL.REFERENCES(User).ON_DELETE(CASCADE).ON_UPDATE(CASCADE)
}

object AnswerComment extends AnswerComment with Table[Long, AnswerComment] {

}

class QuestionComment extends Record[Long, QuestionComment] with IdentityGenerator[Long, QuestionComment] {
  def relation = QuestionComment
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val body = "body".TEXT.NOT_NULL
  val createdAt = "created_at".TIMESTAMP.NOT_NULL.DEFAULT("current_timestamp")
  val question = "question_id".BIGINT.NOT_NULL.REFERENCES(Question).ON_DELETE(CASCADE).ON_UPDATE(CASCADE)
  val user = "user_id".BIGINT.NOT_NULL.REFERENCES(User).ON_DELETE(CASCADE).ON_UPDATE(CASCADE)
}

object QuestionComment extends QuestionComment with Table[Long, QuestionComment] {

}