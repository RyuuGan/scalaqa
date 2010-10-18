package ru.circumflex.tutorials

import ru.circumflex._, core._, web._, freemarker._, orm._

import java.util.Date

class Main extends RequestRouter {

  'currentDate := new Date
  val t = Tag AS "t"
  val q = Question AS "q"

  get("/") = forward("/index.html")

  get("/index.html") = {
    // needed ru.circumflex.orm for doing such things
    fetchTags
    'questions := (SELECT(q.*) FROM(q) WHERE(q.answer IS_NOT_NULL) ORDER_BY(q.createdAt DESC) LIMIT 10).list
    ftl("index.ftl")
  }

  get("/ask/?") = {
    fetchTags
    'topics := Topic.all
    ftl("ask.ftl")
  }
  post("/ask/?") = {
    val q = new Question()
    q.username := param("username")
    q.title := param("title")
    q.body := param("body")
    q.email := param("email")
    q.topic := param("topic_id").toLong
    q.INSERT()
    redirect("/")
  }
  // /? means that "/" is not necessary
  get("/questions/?") = {
    fetchTags
    'message := "All questions"
    'questions := (SELECT(q.*) FROM(q) WHERE (q.answer IS_NOT_NULL)).list
    ftl("questions.ftl")
  }

  get("/questions/tagged/:id") = {
    fetchTags
    'message := "Questions for tag: " + uri("id")
    'questions := (SELECT(q.*) FROM(t.JOIN(q,LEFT)) WHERE(t.name EQ uri("id"))).list
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
    'questionTags := (SELECT(t.*) FROM(t) WHERE(t.question.field EQ id)).list
    'question := (SELECT(q.*) FROM(q) WHERE(q.id EQ id)).unique
    ftl("question_id.ftl")
  }

  get("/topics/?") = {
    val top = Topic AS "top"
    fetchTags
    'topics := (SELECT(top.*) FROM(top)).list
    ftl("topics.ftl")
  }
  get("/topics/:id") = {
    fetchTags
    'message := "All questions for topic: " + uri("id")
    'questions := (SELECT(q.*) FROM(q JOIN t) WHERE (t.name EQ uri("id"))).list
    ftl("questions.ftl")
  }

  get("/search/?") = {
    fetchTags
    if (param("q").length == 0) {
      redirect("/")
    }
    'message := "All questions for: " + param("q")
    'questions := (SELECT(q.*) FROM(q) WHERE ((q.body ILIKE ("%" + param("q") + "%")) OR (q.title ILIKE ("%" + param("q") + "%")))).list
    ftl("questions.ftl")
  }

  any("/admin/?*") = new AdminRouter

}