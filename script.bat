#compilation du framework
cd framework/src

javac -d . annotation/*.java 
javac -d . etu1806/framework/servlet/*.java 
javac -d . etu1806/framework/*.java 
javac -d . utilitaire/*.java 

cd ../

#creation du fichier .jar apres compilation
jar cvf fw.jar -C src/ . 

#copie du fichier .jar vers le repertoire lib du projet de test
copy "fw.jar" "D:\ITU\Java\servlet\framwork tsy git\test framework\WEB-INF\lib"

cd ../

#compilation des classes dans le projet du test
cd test framework/WEB-INF/classes/

javac -cp "../lib/fw.jar" -d . classe/*.java
javac -cp "../lib/fw.jar" -d . use/*.java

cd ../../../

#creation du repertoire temporaire
mkdir temp

cd temp 

mkdir WEB-INF

cd WEB-INF

mkdir classes
mkdir lib

cd ../../ 

xcopy "test framework\WEB-INF\classes\*.class" "temp\WEB-INF\classes" /E /H /C /I
xcopy "test framework\WEB-INF\lib\*.jar" "temp\WEB-INF\lib  " /E /H /C /I
copy "test framework\WEB-INF\*.xml" "temp\WEB-INF"
copy "test framework\*.jsp" "temp\"

cd temp

#creation du fichier .war du projet de test
jar cvf testFramework.war *

#copie du fichier .war vers le repertoire webapps de Tomcat
copy "testFramework.war" "C:\Program Files\Apache Software Foundation\Tomcat 10.0\webapps"

cd ../

rmdir /s /q temp



