package ru.circumflex.tutorials

import ru.circumflex._, core._, web._, freemarker._, orm._

import java.util.Date

class Main extends RequestRouter {

  'currentDate := new Date

  get("/") = forward("/index.html")

  get("/index.html") = {
    fetchTags
    'questions := Question.findLastAnswered
    ftl("index.ftl")
  }

  get("/ask/?") = {
    fetchTags
    'topics := Topic.all
    ftl("ask.ftl")
  }

  post("/ask/?") = {
    val q = new Question()
    q.username := param("username").trim
    q.title := param("title").trim
    q.body := param("body").trim
    param("email").trim match {
      case "" => q.email.setNull
      case v => q.email := v
    }
    q.topic := param("topic").toLong
    try {
      q.INSERT()
      redirect("/")
    } catch {
      case e: ValidationException =>
        flash("errors") = e.errors.by { e =>
          e.param("field") match {
            case Some(f: Field[_, _]) => f.name
            case _ => ""
          }
        }
        redirect("/ask")
    }
  }

  get("/questions/?") = {
    fetchTags
    'message := new Msg("message.allQuestions")
    'questions := Question.findAnswered
    ftl("questions.ftl")
  }

  get("/questions/tagged/:id") = {
    fetchTags
    'message := new Msg("message.tag") + uri("id").trim
    'questions := Question.findTagged(uri("id").trim)
    ftl("questions.ftl") //tagged_questions
  }

  get("/questions/:id") = {
    var id:Long = 0
    try {
      id = uri("id").toLong
    } catch{
      case _ => sendError(404)
    }
    fetchTags
    val q: Question = Question.get(id) match {
      case Some(q) => q
      case _ => null
    }
    if (q == null) {
      sendError(404)
    }
    'questionTags := Tag.findByQuestion(q)
    'question := Question.get(id)
    ftl("question_id.ftl")
  }

  get("/topics/?") = {
    fetchTags
    'topics := Topic.all
    ftl("topics.ftl")
  }
  get("/topics/:id") = {
    fetchTags
    'message := new Msg("message.topic") + uri("id").trim
    'questions := Question.findByTopic(uri("id").trim)
    ftl("questions.ftl")
  }

  get("/search/?") = {
    if (param("q").trim.isEmpty) {
      redirect("/")
    }
    fetchTags
    'message := new Msg("message.search") + param("q").trim
    'questions := Question.search(param("q").trim)
    ftl("questions.ftl")
  }

  any("/admin/?*") = new AdminRouter

}