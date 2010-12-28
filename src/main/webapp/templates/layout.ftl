[#ftl]
[#macro layout]
<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
  <title>Scala QA</title>
  <link rel="stylesheet"
        href="/public/css/main.css"
        type="text/css"
        media="screen, projection"/>
  <script type="text/javascript"
          src="/public/js/jquery-1.4.3.min.js">
  </script>
  <script type="text/javascript"
          src="/public/js/tags.js">
  </script>
</head>
<body>
  <header id="header">
    <h1>
      <a href="/" title="Home">ScalaQA: Circumflex tutorials</a>
    </h1>
    <nav>
      <ul>
        [#list tags as t]
          <li class="weight-${t.weight}"><a href="/questions/tagged/${t.tagname}">${t.tagname}</a></li>
        [/#list]
      </ul>
    </nav>
  </header>
  <section id="content">
    [#nested /]
  </section>
  <footer id="footer">
    <address>
      <span class="copyright">&copy; 2010</span>
      <a href="http://github.com/ryuugan/scalaqa">ScalaQA</a>
      <span>by</span>
      <a href="http://whiteants.net" rel="external">whiteants.net</a>
    </address>
    <form id="search" action="/search" method="get">
      <input type="text" name="q" placeholder="Search"/>
    </form>
  </footer>
</body>
</html>
[/#macro]