[#ftl]
<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
  All questions for Topic: ${topic} <br>
  [#list questions as  q]
    <div name="topics-${q.body}">
      <a href="/questions/${q.id}">${q.body}</a> &laquo;${q.answer!"We didn't answer for this question yet"}&raquo;
    </div>
  [/#list]
</body>
</html>
