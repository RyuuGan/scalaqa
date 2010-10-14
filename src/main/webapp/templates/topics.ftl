[#ftl]
<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <title>Scala QA</title>
  <link rel="stylesheet" href="/public/css/main.css" type="text/css" /> 
</head>
<body>
  [#include "header.ftl"]

  <section id="content" class="body">
    <ul id="posts-list" class="hfeed">
      [#list topics as t]
      <li><article class="hentry">
            <header>
                <h2 class="entry-title"><a href="/topics/${t.name}" rel="bookmark" title="${t.name}">${t.name}</a></h2>
            </header>
            <footer class="post-info">
            </footer><!-- /.post-info -->
        </article></li>
      [/#list]
    </ul>
  </section>
  [#include "footer.ftl"]
</body>
</html>
