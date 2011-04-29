[#ftl]
[#assign content]
<div id="question-page">
[#include "/snippets/question.ftl"/]
</div>
[/#assign]

[#assign sidebar]
  [#include "/snippets/tags.ftl"/]
  [#include "/snippets/last-addings.ftl"/]
  [#include "/snippets/active-users.ftl"/]
  [#include "/snippets/subscribes.ftl"/]

[/#assign]

[#include "/layout.ftl"/]