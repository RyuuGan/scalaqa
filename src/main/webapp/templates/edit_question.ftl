[#ftl]
[#include "/layout.ftl"/]
[@layout]
<h1>Editing questions №${question.id}</h1>
<h2>Hello ${session['admin']}</h2>
<form id="question_edit" name="question_edit"
      action="/admin/edit/questions/${question.id}" method="post">
  <fieldset>
    <dl>
      <dt><label for="username">Username</label></dt>
      <dd><input id="username" type="text" size="64" name="username"
                 value="${question.username}" readonly="true"/></dd>
      <dt><label for="title">Title</label></dt>
      <dd><input id="title" type="text" name="title" value="${question.title}" size="64"/></dd>
      <dt><label for="body">Body</label></dt>
      <dd><textarea id="body" name="body" cols="105" rows="10" name="body" wrap="virtual" readonly="true">${question.body}</textarea></dd>
      <dt><label for="answer">Answer</label></dt>
      <dd><textarea id="answer" name="answer" cols="105" rows="10" name="body" wrap="virtual"></textarea></dd>
      <dt><label for="email">E-mail</label></dt>
      <dd><input type="text" id="email" name="email" size="64" tabindex="-1" readonly="true"
                 value="${question.email!}"/></dd>
      <dt><label for="topic_id">Topic</label></dt>
      <dd><select id="topic_id" name="topics">
        [#list topics as t]
          <option value="${t.id}"
            [#if t.id = question.topic.id]
                  selected="true"
            [/#if]>${t.name}</option>
        [/#list]
      </select></dd>
    </dl>
    <input type="submit" value="submit"/>
  </fieldset>
</form>
<form id="admins_form" action="/admin" method="GET">
  <input type="submit" value="go to admins page">
</form>
[/@layout]