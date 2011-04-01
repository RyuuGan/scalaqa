[#ftl]
{
[#if redirect??]
  "redirect": "${redirect}",
[/#if]
  "error": [
    [#list error![] as m]
    { "key": "${m.key}", "title": "${m?html}" } [#if m_has_next],[/#if]
    [/#list]
  ],
  "warn": [
    [#list warn![] as m]
    { "key": "${m.key}", "title": "${m?html}" } [#if m_has_next],[/#if]
    [/#list]
  ],
  "info": [
    [#list info![] as m]
    { "key": "${m.key}", "title": "${m?html}" } [#if m_has_next],[/#if]
    [/#list]
  ]
}