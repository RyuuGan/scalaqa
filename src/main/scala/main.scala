package ru.circumflex.tutorials

import ru.circumflex._, core._, web._, freemarker._, orm._
import java.util.Date
import org.apache.commons.io.FileUtils
import org.apache.commons.fileupload.FileItem
import java.io.File

class Main extends RequestRouter {

  'currentDate := new Date

  any("/admin/?*") = new AdminRouter

  get("/") = forward("/index.html")
  get("/index.html") = {
    'questions := Question.findLastAnswered
    ftl("index.ftl")
  }

  get("/tags") = {
    'tags := Tag.findWeights
    ftl("/snippets/tags.ftl")
  }

  get("/ask/?") = {
    'topics := Topic.all
    ftl("ask.ftl")
  }

  post("/ask/?") = {
    request.body.parseFileItems(ff) foreach { fi =>
      if (fi.isFormField)
        ctx(fi.getFieldName) = fi.getString("utf-8").trim
      else {
        if (fi.getSize > maxFileSize) {
          'errors := List(new Msg("Question.fileSize", "size" -> FileUtils.byteCountToDisplaySize(maxFileSize)))
          json("/response.json.ftl")
        }
        ctx("file") = fi
      }
    }
    try tx {
      val q = new Question()
      q.username := ctx.getAs[String]("username").getOrElse("")
      q.title := ctx.getAs[String]("title").getOrElse("")
      q.body := ctx.getAs[String]("body").getOrElse("")
      ctx.getAs[String]("email") match {
        case Some(e) if (e != "") => q.email := e
        case _ =>
      }
      ctx.get("topic").map(_.toString.toLong).flatMap(id => Topic.get(id)) match {
        case Some(t: Topic) => q.topic := t
        case _ =>
          'errors := List(new Msg("Question.topic.unknown"))
          json("/response.json.ftl")
      }
      ctx.get("file") match {
        case Some(fi: FileItem) =>
          val dstRoot = new File(uploadsRoot, md5(q.username()))
          val dst = new File(dstRoot, fi.getName)
          fi.write(dst)
          q.attachment := dst.getCanonicalPath
        case _ =>
      }
      q.INSERT()
      'info := List(new Msg("Question.success"))
      'redirect := "/"
      json("/response.json.ftl")
    } catch {
      case e: ValidationException =>
        'errors := e.errors
        json("/response.json.ftl")
    }
  }

  get("/questions/?") = {
    'questions := Question.findAnswered
    ftl("/questions/list.ftl")
  }

  get("/questions/tagged/:id") = {
    'questions := Question.findTagged(uri("id").trim)
    ftl("/questions/list.ftl")
  }

  get("/questions/:id") = try {
    Question.get(param("id").toLong) match {
      case Some(q) =>
        'question := q
        ftl("/questions/view.ftl")
      case _ => sendError(404)
    }
  } catch {
    case e: Exception => sendError(404)
  }

  get("/topics/?") = {
    'topics := Topic.all
    ftl("topics.ftl")
  }
  get("/topics/:id") = {
    'questions := Question.findByTopic(uri("id").trim)
    ftl("/questions/list.ftl")
  }

  get("/search/?") = {
    if (param("q").trim.isEmpty) redirect("/")
    'questions := Question.search(param("q").trim)
    ftl("/questions/list.ftl")
  }

  get("/download/*") = {
    val f = new File(servletContext.getRealPath("/public/uploads/" + uri(1)))
    if (f.exists) sendFile(f, f.getName)
    else redirect("/")
  }

}