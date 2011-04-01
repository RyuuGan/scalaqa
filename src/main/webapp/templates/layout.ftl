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
    <h1><a href="/" title="Home">ScalaQA</a></h1>
    <div id="tags-nav">
      <img src="/img/loading.gif" alt="Loading..."/>
    </div>
  </div>
  <section id="content">
    ${content!}
  </section>
  <div id="footer">
    <address>
      <span class="copyright">&copy; 2010</span>
      <a href="http://github.com/RyuuGan/scalaqa">ScalaQA</a>
      <span>by</span>
      <a href="http://whiteants.net" rel="external">whiteants.net</a>
    </address>
    <form id="search" action="/search" method="get">
      <input type="text" name="q" placeholder="Search"/>
    </form>
  </div>
</body>
</html>