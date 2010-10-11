[#ftl]
<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
  [#list topics as t]
    <div name="topics-${t.name}">
      <a href= "/topics/${t.name}">${t.name}</a> &laquo;<a href="mailto:${t.send_to}">Послать сообщение: ${t.send_to}</a>&raquo;
    </div>
  [/#list]
</body>
</html>
