package ru.circumflex.tutorials
package model

import ru.circumflex.orm._

class User extends Record[Long, User] {
  def relation = User
  def PRIMARY_KEY = id

  val id = "id".BIGINT.NOT_NULL
  val login = "login".TEXT.NOT_NULL
  val password = "password".TEXT.NOT_NULL
  val email = "email".TEXT.NOT_NULL

  /*!
    Has to be overridden by developer.
    Says if this user can give an official answer.
   */
  def isAdministration = false;
}

object User extends User with Table[Long, User] {
  override val relationName = properties.USER_TABLE

  UNIQUE(login)

  def findByIdentity(login: String, password: String): Option[User] =
    (this AS "a").map(a => SELECT(a.*)
        .FROM(a)
        .WHERE((a.login EQ login) AND (a.password EQ password))
        .unique)
}