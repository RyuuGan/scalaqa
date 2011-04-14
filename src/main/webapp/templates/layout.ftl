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
      <div id="top-nav">
        <ul id="main-nav">
          <li><a href="#" class="current">Главная</a></li>
          <li><a href="#">Обсуждения</a></li>
          <li><a href="#">Люди</a></li>
        </ul>
        <ul id="user-nav">
          <li><a href="#">войти</a></li>
          <li>•</li>
          <li><a href="#">зарегистрироваться</a></li>
        </ul>
      </div>
    </div>
    <div id="sidebar">
      sidebar<br/>
      sidebar<br/>
      sidebar<br/>
      sidebar<br/>
      sidebar<br/>
      sidebar<br/>
      sidebar<br/>
      sidebar<br/>
    </div>
  </div>
</div>
<div id="footer">
  footer
</div>
</body>
</html>