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
          <li class="tag">Tags: </li>
          [#list questionTags as qt]
            [#assign isValues="true"]
            <li class="tag"><a href="/questions/tagged/${qt.name}">${qt.name}</a></li>
          [/#list]
          [#if !isValues??]
            <li class="tag">No tags for this question</li>
          [/#if]
        </ul>
      </footer>
    </header>
    <article>
      <footer class="question-info">
        <span class="answer" >
        ${question.answer!"Not answered yet"}
        </span>
      </footer>
    </article>
    <span class="published">
      asked at ${question.createdAt?string("yyyy-MM-dd")}
    </span>
    <span class="vcard author">
      by ${question.username}
    </span>
    [#if session['admin']?? ]
      <form name="edit_question" action="/admin/edit/questions/${question.id}" method="GET" style="margin-top:1em">
        <input type="submit" value="edit" />
      </form>
    [/#if]
  </li>
</ul>
[/@layout]
