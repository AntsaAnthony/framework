package classe;

import annotation.*;

public class Dept {
    @Url(value="/Dept/save")
    public void save(){
        System.out.println("ceci est une fonction pour save");
    }
}
