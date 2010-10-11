package ru.circumflex.tutorials

import java.util.Date
import ru.circumflex.orm._

// if you write "new Question()" that means that you'll create an instance of the class
// but if you write "Question" that means that you will access to Table with the name "Question"

class Question extends Record[Long, Question]
    with IdentityGenerator[Long, Question] {
  def relation = Question
  def PRIMARY_KEY = id
  
  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val username = "username".TEXT.NOT_NULL
  val createdAt = "created_at".TIMESTAMP.NOT_NULL.DEFAULT("current_timestamp")
  val body = "body".TEXT.NOT_NULL
  val email = "email".TEXT
  val answer = "answer".TEXT
  val topic = "topic_id".BIGINT.NOT_NULL.REFERENCES(Topic).ON_DELETE(CASCADE)
}

object Question extends Question with Table[Long, Question] {
  
}

class Tag extends Record[Long, Tag]
    with IdentityGenerator[Long, Tag]{

  def relation = Tag
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val name = "name".TEXT.NOT_NULL
  val question = "question_id".BIGINT.NOT_NULL.REFERENCES(Question).ON_DELETE(CASCADE)
  
}

object Tag extends Tag with Table[Long, Tag]{

}

class Topic extends Record[Long, Topic]
    with IdentityGenerator[Long, Topic]{
  def relation = Topic
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val name = "name".TEXT.NOT_NULL.UNIQUE
  val sendTo = "send_to".TEXT
}

object Topic extends Topic with Table[Long, Topic]{
  
}

class Administrator extends Record[Long, Administrator]
    with IdentityGenerator[Long, Administrator]{

  def relation = Administrator
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val username = "username".TEXT.NOT_NULL.UNIQUE
  val password = "password".TEXT.NOT_NULL
}

object Administrator extends Administrator
    with Table[Long, Administrator]{

}