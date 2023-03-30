package classe;

import annotation.*;
import etu1806.framework.ModelView;

public class Emp {
    @Url("Emp-findAll")
    public void findAll(){
        System.out.println("ceci est une fonction pour find All");
    }

    @Url("Emp-test")
    public void test(){
        System.out.println("ceci est une fonction pour test");
    }

    @Url("Emp-getName")
    public static ModelView getName(){
        ModelView view = new ModelView("Bonjour, mon nom est Bogosy");

        return view;
    }
}
