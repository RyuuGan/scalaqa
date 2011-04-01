package ru.circumflex

import ru.circumflex._, core._, web._, freemarker._
import org.apache.commons.fileupload.disk.DiskFileItemFactory
import java.io._
import tutorials._

package object tutorials {
  val log = new Logger("ru.circumflex.tutorials")
  val uploadsRoot = new File(servletContext.getRealPath("/public/uploads"))
  val ff = new DiskFileItemFactory(2048, uploadsRoot)
  val maxFileSize = cx.get("scalaqa.maxFileSize").map(_.toString.toLong).getOrElse(2097152l)

  def json(template: String): Nothing = {
    val callback = param("callback")
    if (callback != "") {
      response.contentType("application/javascript")
      val content = ftl2string(template)
      send(callback + "(" + content + ")")
    } else {
      response.contentType("application/json")
      ftl(template)
    }
  }

  def principal: Option[Administrator] = session.getAs[Administrator]("principal")

  def auth(block: Administrator => Unit) = principal match {
    case Some(a) => block(a)
    case _ => sendRedirect("/login")
  }
}