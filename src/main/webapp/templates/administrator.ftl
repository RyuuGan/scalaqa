[#ftl]
[#include "/layout.ftl"/]
[@layout]
<h1>Administrators</h1>
<ul id="administrators">
  <h2>Hello ${session['admin']}</h2>
  [#list session['questions'] as q]
    <li>
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
  <form name="logoff" action="/admin/logout" method="GET">
    <input type="submit" value="log out" />
  </form>
</ul>
[/@layout]
