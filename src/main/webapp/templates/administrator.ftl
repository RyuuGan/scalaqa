[#ftl]
<!doctype html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
  [#list administrators as adm]
    <div name="administrator-${adm.username}">
      ${adm.username}: &laquo;${adm.password}&raquo;
    </div>
  [/#list]
</body>
</html>
