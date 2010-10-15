[#ftl]
[#include "/layout.ftl"/]
[@layout]
<h1>Ask new question</h1>
<form id="question_ask" name="question_ask" accept-charset="UTF-8"
      action="/ask" method="post">
  <fieldset>
    <dl>
      <dt><label for="username">Username</label></dt>
      <dd><input id="username" type="text" size="64" name="username" value=""/></dd>
      <dt><label for="title">Title</label></dt>
      <dd><input id="title" type="text" name="title" size="64"/></dd>
      <dt><label for="body">Body</label></dt>
      <dd><textarea id="body" name="body" cols="105" rows="10" name="body" wrap="virtual"></textarea></dd>
      <dt><label for="email">E-mail, if you want to see a notification after answering your question</label></dt>
      <dd><input type="text" id="email" name="email" size="64"/></dd>
      <dt><label for="topic">Topic</label></dt>
      <dd>
        <select id="topic" name="topic">
          [#list topics as t]
            <option value="${t.id}" selected="true">${t.name}</option>
          [/#list]
        </select>
      </dd>
    </dl>
    <input type="submit" value="submit"/>
  </fieldset>
</form>
[/@layout]