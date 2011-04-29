package ru.circumflex.tutorials
package model

import ru.circumflex.orm._

class Comment extends Record[Long, Comment] with IdentityGenerator[Long, Comment] {
  def relation = Comment
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val body = "body".TEXT.NOT_NULL
  val createdAt = "created_at".TIMESTAMP.NOT_NULL.DEFAULT("current_timestamp")
  val answer = "answer_id".BIGINT.NOT_NULL.REFERENCES(Answer).ON_DELETE(CASCADE).ON_UPDATE(CASCADE)
  val user = "user_id".BIGINT.NOT_NULL.REFERENCES(User).ON_DELETE(CASCADE).ON_UPDATE(CASCADE)
}

object Comment extends Comment with Table[Long, Comment] {

}

