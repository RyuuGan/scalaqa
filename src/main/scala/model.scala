/*

package ru.circumflex.tutorials

import ru.circumflex.orm._

class Question extends Record[Long, Question]
with IdentityGenerator[Long, Question] {
  def relation = Question
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val username = "username".TEXT.NOT_NULL
  val createdAt = "created_at".TIMESTAMP.NOT_NULL.DEFAULT("current_timestamp")
  val title = "title".TEXT.NOT_NULL
  val body = "body".TEXT.NOT_NULL
  val email = "email".TEXT
  val answer = "answer".TEXT
  val topic = "topic_id".BIGINT.NOT_NULL.REFERENCES(Topic).ON_DELETE(CASCADE)
  val attachment = "attachment".TEXT

  def tags = inverseMany(Tag.question)
}

object Question extends Question with Table[Long, Question] {
  validation.notEmpty(_.title)
      .notEmpty(_.username)
      .notEmpty(_.body)
      .pattern(_.email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$", "syntax")

  def findUnanswered(): Seq[Question] =
    (this AS "q").map(q => SELECT(q.*).FROM(q).WHERE(q.answer IS_NULL).list)

  def findLastAnswered(): Seq[Question] =
    (this AS "q").map(q => SELECT(q.*).FROM(q).WHERE(q.answer IS_NOT_NULL).ORDER_BY(q.createdAt DESC).LIMIT(10).list)

  def findAnswered(): Seq[Question] =
    (this AS "q").map(q => SELECT(q.*).FROM(q).WHERE(q.answer IS_NOT_NULL).list)

  def findTagged(tag: String): Seq[Question] = {
    val q = this AS "q"
    val t = Tag AS "t"
    SELECT(q.*).DISTINCT.FROM(t.JOIN(q,LEFT)).WHERE(t.name EQ tag).list
  }

  def findByTopic(top: String): Seq[Question] = {
    val t = Topic AS "t"
    val q = this AS "q"
    SELECT(q.*).FROM(q JOIN t).WHERE(t.name EQ top).list
  }

  def search(s: String): Seq[Question] =
    (this AS "q").map(q => (SELECT(q.*).FROM(q)
        .WHERE ((q.body ILIKE ("%" + s + "%"))
        OR (q.title ILIKE ("%" + s + "%")))).list)

}

class Tag extends Record[Long, Tag] with IdentityGenerator[Long, Tag]{

  def relation = Tag
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val name = "name".TEXT.NOT_NULL
  val question = "question_id".BIGINT.NOT_NULL.REFERENCES(Question).ON_DELETE(CASCADE)

}

object Tag extends Tag with Table[Long, Tag]{
  val tagKey = UNIQUE(name, question)

  def findWeights: Seq[TagWeight] = {
    val counts = (Tag AS "t").map(t =>
      SELECT(COUNT(t.id).AS("count") -> t.name.AS("name"))
          .FROM(t)
          .ORDER_BY(t.name)
          .GROUP_BY(t.name)
          .list)
        .flatMap {
      case (Some(count), Some(name)) => Some(new TagWeight(name, count))
      case _ => None
    }
    val min = counts.foldLeft(Double.MaxValue)((w, t) => if (t.weight < w) t.weight else w)
    val max = counts.foldLeft(0d)((w, t) => if (t.weight > w) t.weight else w)
    return counts.map(w => new TagWeight(w.name, (w.weight - min) / (max - min)))
  }

  def deleteByQuestion(q: Question) = (this AS "t").map(t => DELETE(t).WHERE(t.question IS q).execute)
}

case class TagWeight(name: String, weight: Double)

class Topic extends Record[Long, Topic] with IdentityGenerator[Long, Topic]{
  def relation = Topic
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val name = "name".TEXT.NOT_NULL.UNIQUE
  val sendTo = "send_to".TEXT
}

object Topic extends Topic with Table[Long, Topic]

class Administrator extends Record[Long, Administrator] with IdentityGenerator[Long, Administrator]{

  def relation = Administrator
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL.AUTO_INCREMENT
  val username = "username".TEXT.NOT_NULL.UNIQUE
  val password = "password".TEXT.NOT_NULL
}

object Administrator extends Administrator with Table[Long, Administrator]{

  def findByIdentity(username: String, password: String): Option[Administrator] =
    (this AS "a").map(a => SELECT(a.*)
        .FROM(a)
        .WHERE((a.username EQ username) AND (a.password EQ password))
        .unique)

}
*/