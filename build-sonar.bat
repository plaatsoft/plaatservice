set JAVA_HOME=C:\Program Files\Java\jdk-11.0.16.1
call mvn clean test sonar:sonar -Dsonar.scm.disabled=true
pause