package classe;

import annotation.*;

public class Dept {
    @Url("Dept-save")
    public void save(){
        System.out.println("ceci est une fonction pour save");
    }

    @Url("Dept-essaie")
    public void essaie(){
        System.out.println("ceci est une fonction d'essaie");
    }

    @Url("Elyse-Bogosy")
    public void bg(){
        System.out.println("tena bogosy ilay Elyse");
    }
}
