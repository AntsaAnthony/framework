package classe;

import java.util.ArrayList;
import java.util.List;

import annotation.*;
import etu1806.framework.ModelView;

public class Emp {

    String nom;

    public Emp(){

    }

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

    @Url("Emp-form")
    public ModelView formulaire(){

        ModelView view = new ModelView("form.jsp");
        view.addItems("form", "form");

        return view;
    }

    @Url(value = "Emp-save", paramName ="")
    public ModelView save(){

        ModelView view = new ModelView();
        String name = this.nom;
        view.setView("test.jsp");
        view.addItems("nom", name);

        return view;    
    }

    @Url(value = "Emp-findById", paramName = "id")
    public ModelView findById(int id){
        ModelView view = new ModelView("cc.jsp");

        view.addItems("id", id);

        return view;
    }
}
