[#ftl]
<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
  Questions
  [#list questions as q]
    <div id="question-${q.id}">
      ${q.username}: &laquo;${q.body}&raquo;
    </div>
  [/#list]
  Tags
  [#include "tags.ftl"]
</body>
</html>
