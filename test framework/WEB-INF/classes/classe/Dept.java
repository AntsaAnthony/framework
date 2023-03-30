package classe;

import annotation.*;

public class Dept {
    @Url(value="Dept-save")
    public void save(){
        System.out.println("ceci est une fonction pour save");
    }

    @Url(value="Dept-essaie")
    public void essaie(){
        System.out.println("ceci est une fonction d'essaie");
    }

    @Url(value="Elyse-Bogosy")
    public void bg(){
        System.out.println("tena bogosy ilay Elyse");
    }
}
