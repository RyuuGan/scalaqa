[#ftl]
[#include "/layout.ftl"/]
[@layout]
<script type="text/javascript">
  $(function() {
    [#list questionTags as qt]
    addTagElement('${qt.name}');
    [/#list]
  });
</script>
<h1>Editing questions №${question.id}</h1>
<h2>Hello ${session['admin']}</h2>
<form id="question_edit" name="question_edit" accept-charset="UTF-8"
      action="/admin/edit/questions/${question.id}" method="POST">
  <fieldset>
    <dl>
      <dt><label for="username">Question by</label></dt>
      <dd><input id="username" type="text" size="64" name="username"
                 value="${question.username}" readonly="true"/></dd>
      <dt><label for="title">Title</label></dt>
      <dd><input id="title" type="text" name="title" value="${question.title}" size="64"/></dd>
      <dt><label for="body">Body</label></dt>
      <dd><textarea id="body" name="body" cols="105" rows="10" name="body" wrap="virtual" readonly>${question.body}</textarea></dd>
      <dt><label for="answer">Answer</label></dt>
      <dd><textarea id="answer" name="answer" cols="105" rows="10" name="body" wrap="virtual">${question.answer!}</textarea></dd>
      <!--<dt><label for="email">E-mail</label></dt>
      <dd><input type="text" id="email" name="email" size="64" tabindex="-1" readonly="true"
                 value="${question.email!}"/></dd> -->
      <dt><label for="topic">Topic</label></dt>
      <dd><select id="topic" name="topic">
        [#list topics as t]
          <option value="${t.id}"
            [#if t.id = question.topic.id]
                  selected="true"
            [/#if]>${t.name}</option>
        [/#list]
      </select></dd>
      <dt><label for="showedTags">Tags</label></dt>
      <dd>
        <input id="tagsInput" type="text" size="40"/>
        <ul id="showedTags">
        </ul>
      </dd>
    </dl>
    <input id="submitButton" type="button" value="submit"/>
  </fieldset>
</form>
[#if question.attachment??]
<p>There is an <a href="getAttachment?attachment=${question.attachment}">attachment</a> to this question</p>
[/#if]
<form id="admins_form" action="/admin" method="GET">
  <input type="submit" value="go to admins page">
</form>
[/@layout]