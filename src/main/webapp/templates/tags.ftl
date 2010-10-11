[#ftl]
<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
  All Tags
  [#list tags as t]
    <div name="tags">
      <a href="/questions/tagged/${t.tagname}">${t.tagname}</a>
    </div>
  [/#list]
</body>
</html>
