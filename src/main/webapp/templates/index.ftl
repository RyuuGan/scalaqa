[#ftl]

[#assign content]
<div id="index-page">
[#include "/snippets/ask-form.ftl"/]
[#include "/snippets/news.ftl"/]
[#include "/snippets/hot-discussions.ftl"/]
</div>
[/#assign]

[#assign sidebar]
  [#include "/snippets/on-air.ftl"/]
  [#include "/snippets/active-users.ftl"/]
  [#include "/snippets/subscribes.ftl"/]
[/#assign]

[#include "/layout.ftl"/]