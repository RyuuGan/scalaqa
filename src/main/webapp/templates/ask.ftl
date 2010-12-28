[#ftl]
[#include "/layout.ftl"/]
[@layout]
<h1>Ask new question</h1>
<form id="question_ask"
      enctype="multipart/form-data"
      accept-charset="UTF-8"
      accept-"
      action="/ask"
      method="post">
  [#assign errors = flash.errors!/]
  <fieldset>
    <dl>
      <dt><label for="username">Username</label></dt>
      <dd>
        <input id="username" type="text" size="64" name="username" value=""/>
      [@fieldErrors name="username"/]
      </dd>
      <dt><label for="title">Title</label></dt>
      <dd>
        <input id="title" type="text" name="title" size="64"/>
      [@fieldErrors name="title"/]
      </dd>
      <dt><label for="body">Body</label></dt>
      <dd>
        <textarea id="body" name="body" cols="105" rows="10" name="body" wrap="virtual"></textarea>
      [@fieldErrors name="body"/]
      </dd>
      <dt><label for="email">E-mail, if you want to see a notification after answering your question</label></dt>
      <dd>
        <input type="text" id="email" name="email" size="64"/>
      [@fieldErrors name="email"/]
      </dd>
      <dt><label for="topic">Topic</label></dt>
      <dd>
        <select id="topic" name="topic">
          [#list topics as t]
            <option value="${t.id}" selected="true">${t.name}</option>
          [/#list]
        </select>
      [@fieldErrors name="topic"/]
      </dd>
      <dt><label for="attachment">Attachment</label> </dt>
      <dd>
        <input type="file" id="attachment" name="attachment"/>
      </dd>
    </dl>
    <input type="submit" value="submit"/>
  </fieldset>
</form>

[/@layout]

[#macro fieldErrors name]
  [#if errors[name]??]
  <ul class="field-errors">
    [#list errors[name] as e]
      <li>${e}</li>
    [/#list]
  </ul>
  [/#if]
[/#macro]