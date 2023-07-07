package utilitaire;


public class Utilitaire {
    // http://localhost:8081/Sprint1/
    public static String getUrl(String url){
        String param = "";
        String domaine = url.substring(url.indexOf("//")+2);
        domaine = domaine.substring(domaine.indexOf("/")+1);
        String temp = domaine.substring(domaine.indexOf("/")+1);
        if (temp.contains("?")){
            temp = temp.replace("?", "//split//");
            param = temp.split("//split//")[0];
        }
        else{
            param = temp;
        }

        return param;
    }

    public static String capitalize(String input){
        char[] strrep = input.toCharArray();
        strrep[0] = Character.toUpperCase(strrep[0]);
        
        return new String(strrep);
    }
}
