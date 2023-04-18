cd framework/src

javac -d . annotation/*.java 
javac -d . etu1806/framework/servlet/*.java 
javac -d . etu1806/framework/*.java 
javac -d . utilitaire/*.java 

cd ../

jar cvf fw.jar -C src/ . 

copy "fw.jar" "D:\ITU\Java\servlet\framwork tsy git\test framework\WEB-INF\lib"

cd ../


cd test framework/WEB-INF/classes/

javac -cp "../lib/fw.jar" -d . classe/*.java
javac -cp "../lib/fw.jar" -d . use/*.java

cd ../../

jar cvf testFramework.war *

copy "testFramework.war" "C:\Program Files\Apache Software Foundation\Tomcat 10.0\webapps"

cd ../



