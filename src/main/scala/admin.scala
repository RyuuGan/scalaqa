package ru.circumflex.tutorials

import ru.circumflex._, core._, web._, freemarker._, orm._

import java.util.Date

class AdminRouter extends RequestRouter("/admin") {
  val q = Question AS "q"
  val t = Tag AS "t"
  get("/?") = {
    fetchTags
    if (session.get("admin").isEmpty)
      redirect("/admin/login")
    else{
      session("questions") = (SELECT(q.*) FROM(q) WHERE (q.answer IS_NULL)).list
      ftl("administrator.ftl")
    }
  }

  get("/login/?") = {
    fetchTags
    ftl("login.ftl")
  }

  get("/logout/?") = {
    session("admin") = null
    session("questions") = null
    redirect("/")
  }

  post("/login/?") = (Administrator AS "adm").map { adm =>
    SELECT(adm.*)
        .FROM(adm)
        .WHERE((adm.username EQ param("username")) AND (adm.password EQ param("password")))
        .unique
  } match {
    case Some(a) =>
      session("admin") = a.username
      session("questions") = (SELECT(q.*) FROM(q) WHERE (q.answer IS_NULL)).list
      redirect("/admin")
    case _ =>
      fetchTags
      'msg := new Msg("login.error")
      session("admin") = null
      ftl("/login.ftl")
  }

  if (session.get("admin").isEmpty)
    sendError(403)

  get("/edit/questions/:id") = {
    var id:Long = 0
    try{
      id = uri("id").toLong
    } catch{
      case e: java.lang.Exception => id = 0
    }
    fetchTags
    'topics := Topic.all
    'questionTags := (SELECT(t.*) FROM(t) WHERE(t.question.field EQ id)).list
    'question := (SELECT(q.*) FROM(q) WHERE(q.id EQ id)).unique
    ftl("edit_question.ftl")
  }

  post("/edit/questions/:id") = Question.get(uri("id").toLong) match {
    case Some(q) =>
      q.title := param("title")
      q.answer := param("answer")
      q.UPDATE()
      redirect("/admin")
    case _ => sendError(404)
  }

}