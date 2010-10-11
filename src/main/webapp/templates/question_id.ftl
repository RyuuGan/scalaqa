[#ftl]
<!doctype html>
<html>
<head>
  <title>Question</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <style type="text/css">
   .block1 {
    width: 200px;
    background: #ccc;
    padding: 5px;
    padding-right: 20px;
    border: solid 1px black;
    float: left;
   }
   .block2 {
    width: 200px;
    background: #fc0;
    padding: 5px;
    border: solid 1px black;
    float: left;
    position: relative;
    top: 40px;
    left: -70px;
   }
   .blockError {
    width: 200px;
    background: #FF0000;
    padding: 5px;
    padding-right: 20px;
    border: solid 1px black;
    float: left;
   }
  </style>
</head>
<body>
  [#if question??]
      <div class = "block1">
        ${question.body!"There is no such question"}
      </div>
      <div class = "block2">
        ${question.answer!"This question is unanswered."}
      </div>
  [#else]
    [#include "error.ftl"]
  [/#if]
</body>
</html>
