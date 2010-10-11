package ru.circumflex.tutorials

import ru.circumflex._, core._, web._, freemarker._, orm._

import java.util.Date

class Main extends RequestRouter {

  'currentDate := new Date

  get("/") = {
    val t = Tag AS "t"
    val q = Question AS "q"
    // needed ru.circumflex.orm for doing such things
    'tags := (SELECT(t.name AS "tagname", COUNT(t.id) AS "weight") FROM(t) GROUP_BY(t.name)).list
    'laq := (SELECT(q.id AS "id", q.body AS "body") FROM(q) WHERE(q.answer IS_NOT_NULL) ORDER_BY(q.createdAt DESC) LIMIT 10).list
    'a_ref := "/questions/"
    ftl("index.ftl")
  }
  // /? means that "/" is not necessary
  get("/questions/?") = {
    val t = Tag AS "t"
    'questions := Question.all
    'tags := (SELECT(t.name AS "tagname", COUNT(t.id) AS "weight") FROM(t) GROUP_BY(t.name)).list
    ftl("questions.ftl")
  }

  get("/questions/tagged/:id") = {
    val q = Question AS "q"
    val t = Tag AS "t"
    'a_ref := "/questions/"
    'tag := uri("id")
    'tags := (SELECT(t.name AS "tagname", COUNT(t.id) AS "weight") FROM(t) GROUP_BY(t.name)).list
    'questions := (SELECT(q.id AS "id", q.body AS "body", q.answer AS "answer") FROM(t.JOIN(q,LEFT)) WHERE(t.name EQ uri("id"))).list
    ftl("tagged_questions.ftl")
  }

  get("/administrator/?") = {
    val adm = Administrator AS "adm"
    'administrators := ((SELECT(adm.username AS "username", adm.password AS "password")) FROM(adm)).list
    ftl("administrator.ftl")
  }
  
  get("/questions/:id") = {
    val q = Question AS "q"
    var id:Long = 0
    try{
      id = uri("id").toLong
    } catch{
      case e: java.lang.Exception => id = 0
    }

    'question := (SELECT(q.body AS "body", q.answer AS "answer") FROM(q) WHERE(q.id EQ id)).unique

    ftl("question_id.ftl")
  }

  get("/topics/?") = {
    val t = Topic AS "t"
    'topics := (SELECT(t.name AS "name", t.sendTo AS "send_to") FROM(t)).list
    ftl("topics.ftl")
  }
  get("/topics/:id") = {
    val t = Topic AS "t"
    val q = Question AS "q"
    'topic := uri("id")
    //'topics := (SELECT(t.name AS "name", t.sendTo AS "send_to") FROM(t)).list
    'questions := (SELECT(q.id AS "id", q.body AS "body", q.answer AS "asnwer") FROM(q JOIN t) WHERE (t.name EQ uri("id"))).list
    ftl("topic_question.ftl")
  }

}