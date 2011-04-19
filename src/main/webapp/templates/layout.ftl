[#ftl]
<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
  <title>Scala QA [#if title?? && title != ""]&mdash; ${title}[/#if]</title>
  <link rel="stylesheet/less"
        href="/css/main.less"
        type="text/css"
        media="screen, projection"/>
  <script type="text/javascript"
          src="/public/js/jquery-1.4.3.min.js">
  </script>
  <script type="text/javascript"
          src="/public/js/jquery.history.js">
  </script>
  <script type="text/javascript"
          src="/public/js/jquery.form.js">
  </script>
  <script type="text/javascript"
          src="/public/js/less.js">
  </script>
  <script type="text/javascript"
          src="/public/js/application.js">
  </script>
${scripts!}
</head>
<body>
<div id="header">
  <div class="wrap">
    <h1><a href="/" title="Home">Вопросы и ответы</a></h1>
    <div class="search">
      <input/>
    </div>
  </div>
</div>
<div id="content">
  <div class="wrap">
    <div id="main">
      ${content!}
    </div>

    <!--Sidebar-->
    <div id="sidebar">
      <div id="on-air">
        <h3 class="center">Прямой эфир</h3>
        <ul>
          <li>
            <ul>
              <li><a class="author" href="#"><img src="/img/unknown_16.png">christina</a> </li>
              <li><a class="question-small" href="#">Как сделать одно?</a></li>
            </ul>
          </li>
          <li>
            <ul>
              <li><a class="author" href="#"><img src="/img/unknown_16.png">christina</a> </li>
              <li><a class="question-small" href="#">Как сделать второе?</a></li>
            </ul>
          </li>
          <li>
            <ul>
              <li><a class="author" href="#"><img src="/img/unknown_16.png">christina</a> </li>
              <li><a class="question-small" href="#">Как сделать третье?</a></li>
            </ul>
          </li>
          <li>
            <ul>
              <li><a class="author" href="#"><img src="/img/unknown_16.png">christina</a> </li>
              <li><a class="idea-small" href="#">Идея одын</a></li>
            </ul>
          </li>
          <li>
            <ul>
              <li><a class="author" href="#"><img src="/img/unknown_16.png">christina</a> </li>
              <li><a class="idea-small" href="#">Ещё идея</a></li>
            </ul>
          </li>
          <li>
            <ul>
              <li><a class="author" href="#"><img src="/img/unknown_16.png">christina</a> </li>
              <li><a class="question-small" href="#">Как сделать что-то ещё?</a></li>
            </ul>
          </li>
          <li>
            <ul>
              <li><a class="author" href="#"><img src="/img/unknown_16.png">christina</a> </li>
              <li><a class="question-small" href="#">А ещё что-то?</a></li>
            </ul>
          </li>
          <li>
            <ul>
              <li><a class="author" href="#"><img src="/img/unknown_16.png">christina</a> </li>
              <li><a class="idea-small" href="#">Тратата</a></li>
            </ul>
          </li>
          <li>
            <ul>
              <li><a class="author" href="#"><img src="/img/unknown_16.png">christina</a> </li>
              <li><a class="question-small" href="#">А ещё что-то?</a></li>
            </ul>
          </li>
        </ul>
        <a class="earlier" href="#">Посмотреть все></a>
      </div>
        <br>
        <h3><img src="/img/users_32.png"> Активные участники</h3>
        <div id="users-wrap">
        <div id="left">
          <ul>
            <li><a class="author" href="#"><img src="/img/unknown.png">christina</a></li>
            <li><a class="author" href="#"><img src="/img/unknown.png">christina</a></li>
            <li><a class="author" href="#"><img src="/img/unknown.png">christina</a></li>
          </ul>
        </div>
        <div id="right">
          <ul>
            <li><a class="author" href="#"><img src="/img/unknown.png">christina</a></li>
            <li><a class="author" href="#"><img src="/img/unknown.png">christina</a></li>
            <li><a class="author" href="#"><img src="/img/unknown.png">christina</a></li>
          </ul>
        </div>
       </div>
       <a class="earlier" href="#">Все участники></a>
      <br>
      <br>
      <ul id="subs">
        <li><a href="#"><img src="/img/watch.png"> следить за сообществом</a> </li>
        <li><a href="#"><img src="/img/rss.png"> подписаться по rss</a></li>
      </ul>

      </div>
   </div>
  </div>
</div>
<div id="footer">
  footer
</div>
</body>
</html>