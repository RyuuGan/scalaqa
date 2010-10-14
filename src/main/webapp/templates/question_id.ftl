[#ftl]
[#include "/layout.ftl"/]
[@layout]
<h1>Question</h1>
<ul id="question">
    <li>
      <header>
        <h2><a href="/questions/${question.id}" title="Question №${question.id}">${question.title}</a></h2>
        <span class="body">
          ${question.body}
        </span>
        <footer class="tags">
          <ul>
            [#list questionTags as qt]
            <li class="tag"><a href="/questions/tagged/${qt.name}">${qt.name}</a></li>
            [/#list]
          </ul>
        </footer>
      </header>
      <article>
      <footer class="question-info">
        <span class="answer" >
          ${question.answer!"Not answered yet"}
        </span>
        </article>
        <span class="published">
          at ${question.createdAt?string("yyyy-MM-dd")}
        </span>
        <span class="vcard author">
          by ${question.username}
        </span>
      </footer>
    </li>
</ul>
[/@layout]
