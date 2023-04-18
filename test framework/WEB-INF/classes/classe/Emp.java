package classe;

import java.util.ArrayList;
import java.util.List;

import annotation.*;
import etu1806.framework.ModelView;

public class Emp {

    String nom;

    public Emp(String nom){
        this.nom=nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Url("Emp-findAll")
    public static ModelView findAll(){
        ModelView view = new ModelView("list-emp.jsp");
        List<Emp> list = new ArrayList<>();
        Emp Koto = new Emp("Koto");
        Emp Naivo = new Emp("Naivo");

        list.add(Koto);
        list.add(Naivo);
        
        view.addItems("lst", list);
        
        return view;
    }

    @Url("Emp-test")
    public static ModelView test(){
        ModelView view = new ModelView("test.jsp");

        return view;
    }

    @Url("Emp-getName")
    public static ModelView getName(){
        ModelView view = new ModelView("Name.jsp");

        return view;
    }
}
