package classe;

import annotation.*;

public class Emp {
    @Url(value="/Emp/find-all")
    public void findAll(){
        System.out.println("ceci est une fonction pour find All");
    }
}
