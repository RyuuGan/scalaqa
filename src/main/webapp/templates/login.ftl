[#ftl]
[#include "/layout.ftl"/]
[@layout]
<h1>Administrators</h1>
<ul id="administrators">
  <h2>[#if msg??]<div class="msg error">${msg}</div>[/#if] </h2>
  <form name="login" action="" method="POST">
    <fieldset>
      <label for="username">Username:</label>
      <input id="username" type="text" name="username"/>
      <label for="password">Password:</label>
      <input id="password" type="password" name="password"/>
      <input type="submit" value="login"/>
    </fieldset>
  </form>
</ul>
[/@layout]
