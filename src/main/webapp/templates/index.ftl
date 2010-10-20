[#ftl]
[#include "/layout.ftl"/]
[@layout]
<h2><a href="/ask">Ask question</a></h2>
<h1>Recently asked questions</h1>
<ul id="recent-questions">
  [#list questions as q]
    <li>
      <a href="/questions/${q.id}" title="Question №${q.id}">${q.title}</a>
      <footer class="question-info">
        <span class="published">
          asked at ${q.createdAt?string("yyyy-MM-dd")}
        </span>
        <span class="vcard author">
          by ${q.username}
        </span>
      </footer>
    </li>
  [/#list]
</ul>
[/@layout]
