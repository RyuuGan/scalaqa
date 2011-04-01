package ru.circumflex.tutorials

import ru.circumflex._, core._, web._, freemarker._, orm._

class AdminRouter extends RequestRouter("/admin") {
  auth { admin =>
    get("/?") = ftl("/admin/index.ftl")
    get("/logout") = {
      session.remove("principal")
      redirect("/")
    }
    post("/login") = Administrator.findByIdentity(param("username").trim, param("password")) match {
      case Some(a) =>
        session("principal") = a
        redirect("/admin")
    }
  }

  get("/index.html") = {
    'questions := Question.findUnanswered
    ftl("administrator.ftl")
  }

  get("/question/:id/edit") = try {
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

  post("/question/:id") = Question.get(param("id").toLong) match {
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

  delete("/question/:id") = Question.get(param("id").toLong) match {
    case Some(q: Question) =>
      q.DELETE_!
      'info := List(new Msg("Question.deleted", "no" -> q.id()))
      'redirect := "/admin"
      json("/response.json.ftl")
    case _ => sendError(404)
  }
}