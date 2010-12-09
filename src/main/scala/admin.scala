package ru.circumflex.tutorials

import ru.circumflex._, core._, web._, freemarker._, orm._
import org.apache.commons.io.IOUtils
import xml._

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

  post("/login/?") = Administrator.findByIdentity(param("username").trim, param("password").trim) match {
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
    'questionTags := Tag.findByQuestion(Question.get(id) match {
      case Some(q) => q
      case _ => sendError(404)
    })
    'question := Question.get(id)
    ftl("edit_question.ftl")
  }

  post("/edit/questions/:id") = Question.get(uri("id").toLong) match {
    case Some(q: Question) =>
        val r = request.body.asXml
        q.answer := (r \ "answer").text.trim
        q.title := (r \ "title").text.trim
        q.topic.field := (r \ "topic").text.toLong
        Tag.deleteByQuestion(q)
        val tags = (r \\ "tag")
        for (tag <- tags) {
          val t = new Tag()
          t.name := tag.text.trim
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