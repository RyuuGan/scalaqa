[#ftl]
[#include "layout.ftl"]
[@page]
<h1>Welcome to Simple Circumflex Web Application!</h1>
<p>Please take a moment to observe your project layout.</p>
<ul>
  <li><strong>pom.xml</strong>
    – Maven2 Project Object Model, an XML file that contains
    information about the project and configuration details used by Maven to build the project.
    It contains sensible default values for most projects. Basically you configure your
    project's dependencies, build names, source and target directories and build plugins using
    this configuration file. For more information, proceed to
    <a href="http://maven.apache.org/guides/introduction/introduction-to-the-pom.html"
       target="_blank">
      Introduction to the POM</a>.
  </li>
  <li><strong>src/main</strong>
    – main source directory of your project:
    <ul>
      <li><strong>scala/main.scala</strong>
        – your application's entry point called <em>Request Router</em>, it handles all incoming
        HTTP requests, matching them against <em>routes</em>: the first route that matches request
        is executed;</li>
      <li><strong>webapp</strong>
        – your Web application context root:
        <ul>
          <li><strong>WEB-INF/web.xml</strong>
            – the Java EE <em>deployment descriptor</em>,
            an XML configuration file that specifies, how your application will be deployed into
            <em>Servlet Container</em>.
            By default it just maps all URLs to <em>Circumflex Filter</em>.</li>
          <li><strong>public</strong>
            – a directory for static resources, e.g. stylesheets,
            scripts, images, etc.; resources under this directory are served directly by the
            container and are not processed by <em>Circumflex Filter</em>.</li>
          <li><strong>templates</strong>
            – a default location for FreeMarker templates; they are resolved relatively to this
            location by <em>Circumflex FreeMarker helper</em>.</li>
        </ul>
      </li>
      <li><strong>resources/Messages.properties</strong>
        – various messages for your application can be defined there for
        <a href="http://java.sun.com/docs/books/tutorial/i18n/index.html"
           target="_blank">Internationalization</a> purposes;</li>
      <li><strong>resources/cx.properties</strong>
        – Circumflex configuration parameters are specified here, by default it only contains the
        <em>cx.router</em> parameter, which points to your application Request Router class;</li>
      <li><strong>resources/log4j.xml</strong>
        – logger configuration for your project, you may use it to obtain more verbose information
        about the application's runtime activity, for example the following changes:
        <pre>${'
<category name="ru.circumflex.core">
  <priority value="debug"/>
</category>'?html}</pre>
        <p>will result in every incoming request being logged to your console.
           If you are not familiar with <em>Apache log4j</em>, take a moment to observe the
          <a href="http://logging.apache.org/log4j/1.2/manual.html" target="_blank">
            Short introduction to log4j</a> article.</p>
      </li>
    </ul>
  </li>
</ul>
<p>Please visit <a href="http://circumflex.ru" target="_blank">Circumflex website</a> for
   further information.</p>
<p style="font-weight:bold">
  We hope you'll get a lot of fun developing with Circumflex! Good luck!</p>
[/@page]
