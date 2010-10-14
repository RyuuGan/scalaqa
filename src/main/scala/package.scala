package ru.circumflex

import ru.circumflex._, core._, orm._

package object tutorials {

  val log = new Logger("ru.circumflex.tutorials")

  def fetchTags = {
    val t = Tag AS "t"
    val count = SELECT(COUNT(t.id)).FROM(t).unique.get
    'tags := SELECT(t.name AS "tagname", COUNT(t.id) AS "count")
        .FROM(t)
        .GROUP_BY(t.name)
        .list.map { m =>
      val c = m("count").asInstanceOf[Long]
      val w = math.ceil(c * 6.0 / count).toInt
      m + ("weight" -> w)
    }
  }

}