package ru.circumflex.tutorials

import ru.circumflex._, core._, web._, freemarker._, orm._

import java.util.Date

import org.apache.commons.fileupload.util.Streams
import org.apache.commons.fileupload.FileItem
import scala.tools.nsc.io.Directory
import java.io.File

import scala.collection.mutable.Map

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

    // getting all fields name using apache commons file upload
    val m = Map.empty[String, String]
    val i = request.body.parseFileStreams
    while (i.hasNext) {
      val itemStream = i.next
      val name = itemStream.getFieldName
      val stream = itemStream.openStream
      if (itemStream.isFormField)
        m += (name -> Streams.asString(stream,"UTF-8").trim)
      else {
        val un = m.getOrElse("username","").toLowerCase.trim
        if (un != "") {
          m += ("file_name" -> (un + "/" + itemStream.getName))
          val dir = Directory(servletContext.getRealPath("/public/uploads/" + un + "/"))
          if (!dir.exists)
            dir.createDirectory(true)

          val out = new java.io.FileOutputStream(servletContext.getRealPath("/public/uploads/"
              + un + "/" + itemStream.getName))
          Streams.copy(stream, out, true)
        }
        else {
          m += ("file_name" -> "")
        }
      }
    }

    val q = new Question()
    q.username := m.getOrElse("username", "").trim
    q.title := m.getOrElse("title", "").trim
    q.body := m.getOrElse("body", "").trim
    m.getOrElse("email", "").trim match {
      case "" => q.email.setNull
      case v => q.email := v
    }
    q.topic.field := m("topic").toString.toLong
    m("file_name") match {
      case "" => q.attachment.setNull
      case v => q.attachment := v
    }
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

  get("/download/?") = {
    val filename = param("attachment").trim
    val f = new File(servletContext.getRealPath("/public/uploads/" + filename))
    if (f.exists)
      sendFile(f, f.getName)
    else
      redirect("/")
  }

  any("/admin/?*") = new AdminRouter

}