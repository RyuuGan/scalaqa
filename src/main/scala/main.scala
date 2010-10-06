package ru.circumflex.tutorials

import ru.circumflex._, core._, web._, ftl._
import java.util.Date

class Main extends RequestRouter {
  val log = new Logger("ru.circumflex.tutorials")

  'currentDate := new Date

  get("/") = ftl("index.ftl")

}