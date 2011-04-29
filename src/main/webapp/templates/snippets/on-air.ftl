[#ftl]
<div id="on-air">
  <h3 class="center">Прямой эфир</h3>
  <ul>
    [#if on_air??]
      [#list on_air as q]
        <ul>
          <li><a class="author" href="#"><img src="/img/unknown_16.png">${q.user.login}</li>
          <li><a class="question-small" href="#">${q.body?substring(0, 17)} ...</a></li>
        </ul>
      [/#list]
    [/#if]
  </ul>
  <a class="earlier" href="#">Посмотреть все></a>
</div>