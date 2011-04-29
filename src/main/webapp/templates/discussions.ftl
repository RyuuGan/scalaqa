[#ftl]

[#assign content]
<div id="discussions-page">
[#include "/snippets/ask-form.ftl"/]
[#include "/snippets/discuss-list.ftl"/]
</div>
[/#assign]

[#assign sidebar]
  [#include "/snippets/tags.ftl"/]
  [#include "/snippets/last-addings.ftl"/]
  [#include "/snippets/active-users.ftl"/]
  [#include "/snippets/subscribes.ftl"/]

[/#assign]

[#include "/layout.ftl"/]