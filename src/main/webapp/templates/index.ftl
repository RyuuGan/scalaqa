[#ftl]
<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
  Tags: 
  [#include "tags.ftl"]
  Last answered questions:
  <ul>
    [#list laq as q]
      <li><a href="${a_ref}${q.id}">${q.body}</a></li>
    [/#list]
  </ul>
</body>
</html>
