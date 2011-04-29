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
[#include "/snippets/header.ftl"/]
<div id="content">
  <div class="wrap">
    <div id="main">
      ${content!}
    </div>
      <div id="sidebar">
        [#if sidebar??]
        ${sidebar!}
        [#else]
          [#include "/snippets/on-air.ftl"/]
        [/#if]
      </div>
  </div>
</div>
[#include "/snippets/footer.ftl"/]
</body>
</html>