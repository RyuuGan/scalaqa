[#ftl]
[#include "/layout.ftl"/]
[@layout]
<h2><a href="/ask">Ask question</a></h2>
<h1>All topics</h1>
<ul id="recent-questions">
  [#list topics as t]
    <li>
      <a href="/topics/${t.name}" title="Topic: ${t.name}">${t.name}</a>
      <footer class="question-info">
      </footer>
    </li>
  [/#list]
</ul>
[/@layout]
