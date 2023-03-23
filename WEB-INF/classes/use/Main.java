package use;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import etu1806.framework.Mapping;
import utilitaire.Utilitaire;


public class Main {
    public static void main(String[] args) {
        // System.out.println("Url "+Utilitaire.getUrl("http://localhost:8081/Sprint1/newTest"));

        try {
            String absolutePath = "C:/Program Files/Apache Software Foundation/Tomcat 10.0/webapps/Spring/WEB-INF/classes";
            Vector<String> rep = Package.getClasses(absolutePath);
            for (String string : rep) {
              // System.out.println(string);
            }
            HashMap<String, Mapping> map = Package.scanPackages(absolutePath);
            // Set<String> set = map.keySet();
            // for (String s : set) {
            //   System.out.println(s);
            //   System.out.println(map.get(s).getClassName());
            //   System.out.println(map.get(s).getMethod());
            // }
          } catch (Exception e) {
            System.out.println(e);
          }
    }
}
