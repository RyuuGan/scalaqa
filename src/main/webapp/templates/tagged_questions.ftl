[#ftl]
<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
  Вопросы по тегу: ${tag} <br>
  [#list questions as  q]
    <div name="topics-${q.body}">
      <a href="/questions/${q.id}">${q.body}</a> &laquo;${q.answer!"We didn't answer for this question yet"}&raquo;
    </div>
  [/#list]
  [#include "tags.ftl"]
</body>
</html>
