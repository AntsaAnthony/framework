package classe;

import java.util.ArrayList;
import java.util.List;

import annotation.*;
import etu1806.framework.FileUpload;
import etu1806.framework.ModelView;

@Scope(types="singleton")
public class Emp {

    String nom;
    int numero;
    FileUpload file;

    public Emp(){

    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    public FileUpload getFile() {
        return file;
    }

    public void setFile(FileUpload file) {
        this.file = file;
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
        view.setView("test.jsp");
        view.addItems("emp", this);
        return view;    
    }

    @Url(value = "Emp-findById", paramName = "id")
    public ModelView findById(int id){
        ModelView view = new ModelView("cc.jsp");

        view.addItems("id", id);

        return view;
    }

    public int getBybyte(){
        return this.file.getFile().length;
    }

 

    
}
