package ru.circumflex.tutorials

import ru.circumflex._, core._, web._, orm._, freemarker._
import java.util.Date
import org.apache.commons.io.FileUtils
import org.apache.commons.fileupload.FileItem
import java.io.File

import model._

class Main extends RequestRouter {

  'currentDate := new Date

  get("/") = partialFtl("/index.ftl")
  get("/discussions") = forward("/discussions/")
  get("/discussions/") = partialFtl("/discussions.ftl")
  get("/tags") = {
//    'tags := Tag.findWeights
    partialFtl("/snippets/tags.ftl")
  }

  get("/download/*") = {
    val f = new File(servletContext.getRealPath("/public/uploads/" + uri(1)))
    if (f.exists) sendFile(f, f.getName)
    else redirect("/")
  }

  get("/login") = partialFtl("/admin/login.ftl")
  post("/login/?") = User.findByIdentity(param("username").trim, SHA_256(param("password"))) match {
    case Some(u) =>
      session("user") = u
      redirect("/")
    case _ =>
      'errors := List(new Msg("login.error"))
      session.remove("user")
      json("/response.json.ftl")
  }
  get("/logout") = {
    session.remove("principal")
    redirect("/")
  }

  any("/questions") = forward(uri(0) + "/")
  any("/questions/*") = new QuestionsRouter

}

class QuestionsRouter extends RequestRouter("/questions") {
  get("/") = {
//    val q = param("q").trim
//    if (q == "")
//      'questions := Question.findAnswered
//    else 'questions := Question.search(q)
    'questions := Question.all
    partialFtl("/questions/list.ftl")
  }
  get("/tagged/:id") = {
    'questions := Question.findTagged(uri("id").trim)
    partialFtl("/questions/list.ftl")
  }
  get("/:id") = try {
    Question.get(param("id").toLong) match {
      case Some(q) =>
        'question := q
        partialFtl("/questions/view.ftl")
      case _ => sendError(404)
    }
  } catch {
    case e: Exception => sendError(404)
  }
  get("/ask") = {
    partialFtl("ask.ftl")
  }
  post("/ask") = {
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
      q.body := ctx.getAs[String]("body").getOrElse("")
//      ctx.get("file") match {
//        case Some(fi: FileItem) =>
//          val dstRoot = new File(uploadsRoot, md5(q.username()))
//          val dst = new File(dstRoot, fi.getName)
//          fi.write(dst)
//          q.attachment := dst.getCanonicalPath
//        case _ =>
//      }
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
}