[#ftl]
<div id="discussions">
  <div id="discuss-menu">
    <a class="current" href="#"><img align=middle src="/img/all-discuss.png"> Все обсуждения</a>
    <a href="#"><img align=middle src="/img/question_64.png"> Вопросы</a>
    <a href="#"><img align=middle src="/img/idea_64.png" alt=""> Идеи</a>
  </div>
  <div class="filters">
    <div><a class="active" href="#">новые</a></div>
    <div><a href="#">лучшие</a></div>
    <div><a href="#">открытые</a></div>
    <div><a href="#">закрытые</a></div>
  </div>
  <div id="discuss-list">
      [#if questions??]
        [#list questions as q]
        <div>
          <a class="question" href="/questions/${q.id}">
            ${q.body}
          </a>
          <br>
          <img src="/img/tags.png">
          [#list q.tags as t]
            <a class="tags" href="#">${t.name}</a>
          [/#list]
        </div>
      [/#list]
      [/#if]
    <a class="earlier" href="#">раньше></a>
  </div>
</div>