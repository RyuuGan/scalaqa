package ru.circumflex.tutorials
package model

import ru.circumflex.orm._

class User extends Record[Long, User] with IdentityGenerator[Long, User]{
  def relation = User
  def PRIMARY_KEY = id

  val id = "id".BIGINT.AUTO_INCREMENT.NOT_NULL
  val lastName = "lastname".TEXT.NOT_NULL
  val firstName = "firstname".TEXT.NOT_NULL
  val login = "login".TEXT.NOT_NULL
  val password = "password".TEXT.NOT_NULL
  val registeredAt = "registered_at".TIMESTAMP.DEFAULT("current_timestamp").NOT_NULL
  val email = "email".TEXT.NOT_NULL
  val birthDate = "birth_date".DATE
  val locality = "locality".TEXT
  val roles = "roles".TEXT.NOT_NULL("")

  def getRoles: Seq[String] = roles().split("\\|").map(_.trim).filter(_ != "")
}

object User extends User with Table[Long, User] {
  UNIQUE(login)
}