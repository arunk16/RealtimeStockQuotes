This demo Stock application project is created using AppFuse Basic Spring MVC Archetype 
-------------------------------------------------------------------------------------

The application is ready to run as a web application. The pom.xml file is
pre-defined with Hibernate as a persistence model and Spring MVC as the web
framework.

To get started, please go through the following steps:

1. To use proxy , please enable proxy settings in settings.xml of Maven installation used to run this project.
   Please use this link for reference - http://maven.apache.org/guides/mini/guide-proxies.html

2. To see application in action, go to root level of the project checked out in shell/command prompt and type "mvn -Ph2 clean test jetty:run" 
   and view the application at http://localhost:8080. All the unit tests created part of the exercise will run successfully before jetty server
   brings up the application.
   (PLEASE NOTE : -Ph2 is for h2 database profile, please feel free to chose other profiles available in pom.xml 
    but do consider the ground work in preparing those databases ready for this application.)
   
3. Please use admin/admin to login and follow the link Stock U1 & U2 or use Stock App menu to test both user stories.

4. In case, if you encounter out of memory error during your test, please set more memory for Maven before you type "mvn -Ph2 clean test jetty:run" again.
   unix - export MAVEN_OPTS=-Xmx1024m
   windows - set MAVEN_OPTS=-Xmx1024m



