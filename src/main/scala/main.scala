package ru.circumflex.tutorials

import ru.circumflex._, core._, web._, freemarker._, orm._

import java.util.Date

class Main extends RequestRouter {

  'currentDate := new Date
  val t = Tag AS "t"
  val q = Question AS "q"

  get("/") = forward("/index.html")

  get("/index.html") = {
    fetchTags
    'questions := Question.findLastAnsweredQuestions
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
    'message := "All questions"
    'questions := Question.allAnsweredQuestions
    ftl("questions.ftl")
  }

  get("/questions/tagged/:id") = {
    fetchTags
    'message := "Questions for tag: " + uri("id").trim
    'questions := Question.taggedQuestions(uri("id").trim)
    ftl("questions.ftl") //tagged_questions
  }

  get("/questions/:id") = {
    var id:Long = 0
    try{
      id = uri("id").toLong
    } catch{
      case e: java.lang.Exception => id = 0
    }
    fetchTags
    'questionTags := Tag.tagsForQuestion(id)
    'question := Question.get(id)
    ftl("question_id.ftl")
  }

  get("/topics/?") = {
    val top = Topic AS "top"
    fetchTags
    'topics := Topic.all
    ftl("topics.ftl")
  }
  get("/topics/:id") = {
    fetchTags
    'message := "All questions for topic: " + uri("id").trim
    'questions := Question.questionsForTopic(uri("id").trim)
    ftl("questions.ftl")
  }

  get("/search/?") = {
    fetchTags
    if (param("q").length == 0) {
      redirect("/")
    }
    'message := "All questions for: " + param("q").trim
    'questions := Question.search(param("q").trim)
    ftl("questions.ftl")
  }

  any("/admin/?*") = new AdminRouter

}