package ru.circumflex.tutorials

import ru.circumflex._, core._, web._, freemarker._, orm._


class AdminRouter extends RequestRouter("/admin") {

  get("/?") = if (session.get("admin").isEmpty)
    redirect("/admin/login")
  else redirect("/admin/index.html")

  get("/login/?") = {
    fetchTags
    ftl("login.ftl")
  }

  get("/logout/?") = {
    session.remove("admin")
    redirect("/")
  }

  post("/login/?") = Administrator.selectAdmin(param("username").trim, param("password").trim) match {
    case Some(a) =>
      session("admin") = a.username
      redirect("/admin")
    case _ =>
      fetchTags
      'msg := new Msg("login.error")
      session.remove("admin")
      ftl("/login.ftl")
  }

  if (session.get("admin").isEmpty)
    sendError(403)

  get("/index.html") = {
    fetchTags
    'questions := Question.findUnanswered
    ftl("administrator.ftl")
  }

  get("/edit/questions/:id") = {
    fetchTags
    val id = uri("id").toLong
    'topics := Topic.all
    'questionTags := Tag.tagsForQuestion(id)
    'question := Question.get(id)
    ftl("edit_question.ftl")
  }

  post("/edit/questions/:id") = Question.get(uri("id").toLong) match {
    case Some(q: Question) =>
      q.title := param("title")
      if (param("answer").trim != "")
        q.answer := param("answer")
      q.topic.field := param("topic").toLong
      q.UPDATE()
      // parse comma-separated tags and adding them to database
      param("tags").split(',').map(_.trim).filter(_ != "").foreach { s =>
        val t = new Tag()
        t.name := s
        t.question.field := uri("id").toLong
        try {
          t.INSERT()
        }
        catch {
          case _ => 
        }
      }
      redirect("/admin/index.html")
    case _ => sendError(404)
  }

}