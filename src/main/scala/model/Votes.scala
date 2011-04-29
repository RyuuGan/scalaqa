package ru.circumflex.tutorials
package model

import ru.circumflex.orm._

class QuestionVote extends Record[Long, QuestionVote] with IdentityGenerator[Long, QuestionVote] {
  def relation = QuestionVote
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val user = "user_id".BIGINT.NOT_NULL.REFERENCES(User).ON_DELETE(CASCADE).ON_UPDATE(CASCADE)
  val question = "question_id".BIGINT.NOT_NULL.REFERENCES(Question).ON_DELETE(CASCADE).ON_UPDATE(CASCADE)
  val vote = "vote".INTEGER.NOT_NULL
}

object QuestionVote extends QuestionVote with Table[Long, QuestionVote] {
  UNIQUE(user, question)
}

class AnswerVote extends Record[Long, AnswerVote] with IdentityGenerator[Long, AnswerVote] {
  def relation = AnswerVote
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val user = "user_id".BIGINT.NOT_NULL.REFERENCES(User).ON_DELETE(CASCADE).ON_UPDATE(CASCADE)
  val answer = "answer_id".BIGINT.NOT_NULL.REFERENCES(Answer).ON_DELETE(CASCADE).ON_UPDATE(CASCADE)
  val vote = "vote".INTEGER.NOT_NULL
}

object AnswerVote extends AnswerVote with Table[Long, AnswerVote] {
  UNIQUE(user, answer)
}

