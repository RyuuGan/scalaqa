package ru.circumflex.tutorials
package model

import ru.circumflex.orm._

class Answer extends Record[Long, Answer] with IdentityGenerator[Long, Answer] {
  def relation = Answer
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val body = "body".TEXT.NOT_NULL
  val createdAt = "created_at".TIMESTAMP.NOT_NULL.DEFAULT("current_timestamp")
  val question = "question_id".BIGINT.NOT_NULL.REFERENCES(Question).ON_DELETE(CASCADE).ON_UPDATE(CASCADE)
  val user = "user_id".BIGINT.NOT_NULL.REFERENCES(User).ON_DELETE(CASCADE).ON_UPDATE(CASCADE)

  def comments = inverseMany(AnswerComment.answer)
  def votes = inverseMany(AnswerVote.answer)
}

object Answer extends Answer with Table[Long, Answer] {

}