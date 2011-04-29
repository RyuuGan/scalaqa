package ru.circumflex.tutorials
package model

import ru.circumflex.orm._

class Tag extends Record[Long, Tag] with IdentityGenerator[Long, Tag] {
  def relation = Tag
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val name = "name".TEXT.NOT_NULL
  val question = "question_id".BIGINT.NOT_NULL.REFERENCES(Question).ON_DELETE(CASCADE).ON_UPDATE(CASCADE)
}

object Tag extends Tag with Table[Long, Tag] {
  UNIQUE(name, question)
}