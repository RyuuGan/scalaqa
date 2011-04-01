package ru.circumflex.tutorials

import ru.circumflex._, core._, web._, freemarker._, orm._

class AdminRouter extends RequestRouter("/admin") {

  get("/?") = if (session.get("admin").isEmpty)
    redirect("/admin/login")
  else redirect("/admin/index.html")

  get("/login/?") = {
    ftl("login.ftl")
  }

  get("/logout/?") = {
    session.remove("admin")
    redirect("/")
  }

  post("/login/?") = Administrator.findByIdentity(param("username").trim, param("password").trim) match {
    case Some(a) =>
      session("admin") = a.username
      redirect("/admin")
    case _ =>
      'msg_login := new Msg("login.error")
      session.remove("admin")
      ftl("/login.ftl")
  }

  if (session.get("admin").isEmpty)
    sendError(403)

  get("/index.html") = {
    'questions := Question.findUnanswered
    ftl("administrator.ftl")
  }

  get("/edit/:id") = try {
    Question.get(param("id").toLong) match {
      case Some(q) =>
        'question := q
        'topics := Topic.all
        ftl("/questions/view.ftl")
      case _ => sendError(404)
    }
  } catch {
    case e: Exception => sendError(404)
  }

  post("/edit/questions/:id") = Question.get(uri("id").toLong) match {
    case Some(q: Question) =>
        val r = request.body.asXml
        q.answer := (r \ "answer").text.trim
        q.title := (r \ "title").text.trim
        q.topic.field := (r \ "topic").text.toLong
        q.save
        Tag.deleteByQuestion(q)
        val tags = (r \\ "tag")
        for (tag <- tags) {
          val t = new Tag()
          t.name := tag.text.trim.toLowerCase
          t.question.field := uri("id").toLong
          try {
            t.INSERT()
          }
          catch {
            case e: java.lang.Exception =>  
          }
        }
        redirect("/admin/index.html")
    case _ =>sendError(404)
  }
}