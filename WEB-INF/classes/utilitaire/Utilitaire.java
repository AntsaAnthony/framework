package utilitaire;

import java.io.PrintWriter;

public class Utilitaire {
    // http://localhost:8081/Sprint1/
    public static String getUrl(String url){
        String param = "";
        String domaine = url.substring(url.indexOf("//")+2);
        domaine = domaine.substring(domaine.indexOf("/")+1);
        System.out.println("domaine = "+domaine);
        String temp = domaine.substring(domaine.indexOf("/")+1);
        System.out.println("temp = "+temp);
        if (temp.contains("?")){
            temp = temp.replace("?", "//split//");
            param = temp.split("//split//")[0];
        }
        else{
            param = temp;
        }

        return param;
    }
}
