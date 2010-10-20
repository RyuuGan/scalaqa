[#ftl]
[#include "/layout.ftl"/]
[@layout]
<h1>Administrators</h1>
<ul id="administrators">
  <h2>Hello ${session['admin']}</h2>
  <h2>All unanswered questions. Choose one of them to answer</h2>
  [#list questions as q]
    <li>
      [#assign isValues="true"]
      <a href="/admin/edit/questions/${q.id}" title="Question №${q.id}">${q.title}</a>
      <footer class="question-info">
            <span class="published">
              at ${q.createdAt?string("yyyy-MM-dd")}
            </span>
            <span class="vcard author">
              by ${q.username}
            </span>
      </footer>
    </li>
  [/#list]
  [#if !isValues??]
    <h2>Oops... There are no unanswered questions.</h2>
  [/#if]

  <form name="logoff" action="/admin/logout" method="GET" style="margin-top:1em">
    <input type="submit" value="log out" />
  </form>
</ul>
[/@layout]
