[#ftl]
[#assign content]
[#include "/snippets/menu.ftl"/]
<div id="question-page">
  <div class="left">
    <a href="#"><img src="" alt="">${question.user.login}</a>
    <p>Вопрос задан
    <b>${question.createdAt?date?string("dd.MM.yyyy")}</b></p>
    [#if question.comments.size > 0]
    <p>Последний комментарий
    <b>22.04.2009</b></p>
    [#else ]
    <p><b>Коментариев нет</b></p>
    [/#if]
  </div>
  <div class="right">
    <div class= "tail"></div>
    <div id="question">
      <h3>${question.body}</h3>
      [#list question.tags as t]
        <a class="tags" href="#">${t.name}</a>
      [/#list]
    </div>
    <div id="official-answer">
      <div class="left">
        <a href="#"><img src="" alt="">${question.user.login}</a>
        <p>ответил
        22.02.2009</p>
      </div>
      <div class="right">
        <h4><img src="#" alt="">Официальный ответ</h4>
        <p>Я подставил кролика Роджера потому, что он очень кролик.</p>
      </div>
    </div>
    <div id="best-answer">
      <div class="left">
        <a href="#"><img src="" alt="">${question.user.login}</a>
        <p>ответил
        22.02.2009</p>
      </div>
      <div class="right">
        <h4><img src="#" alt="">Лучший ответ</h4>
        <p>Я подставил кролика Роджера потому, что он очень кролик.</p>
      </div>
    </div>
    <div id="answers">
    </div>
  </div>
</div>



















[/#assign]
[#assign sidebar]
  [#include "/snippets/tags.ftl"/]
  [#include "/snippets/last-addings.ftl"/]
  [#include "/snippets/active-users.ftl"/]
  [#include "/snippets/subscribes.ftl"/]

[/#assign]

[#include "/layout.ftl"]
