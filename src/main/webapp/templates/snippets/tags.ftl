[#ftl]
<ul class="tags">
  [#list tags as t]
    <li style="font-size:${50 + (50 * t.weight)}%">
      <a href="/questions/tagged/${t.name}">${t.name}</a>
    </li>
  [/#list]
</ul>