package ru.circumflex.tutorials

import ru.circumflex._, core._, web._

class AdminRouter extends RequestRouter("/admin") {
  auth { admin =>
    get("/?") = {
      'questions := Question.findUnanswered
      partialFtl("/admin/index.ftl")
    }

    get("/question/:id/edit") = try {
      Question.get(param("id").toLong) match {
        case Some(q) =>
          'question := q
          'topics := Topic.all
          partialFtl("/questions/view.ftl")
        case _ => sendError(404)
      }
    } catch {
      case e: Exception => sendError(404)
    }
    post("/question/:id") = Question.get(param("id").toLong) match {
      case Some(q: Question) =>
        // TODO implement question editing
        json("/response.json.ftl")
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
}