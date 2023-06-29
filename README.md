# Framework 
Ceci est un projet de réalisation d'un framework similaire à Spring MVCQ

# Pour commencer
Copiez le fichier .jar (dans le dossier framework de la branche master) dans le dossier WEB-INF/lib de votre application web 

# Pré-requis
Pour pouvoir utiliser correctement le framework, il faut :
:
* version JDK supérieur à 8
* version Tomcat supérieur à 10.0

# Comment ça marche
## web.xml

Vous devez configurer le fichier web.xml comme suit : 
```xml

<display-name>name</display-name> 
        <servlet>
            <servlet-name>FrontServlet</servlet-name> 
            <servlet-class>etu1806.framework.servlet.FrontServlet</servlet-class> 
        </servlet>
        <servlet-mapping>
            <servlet-name>FrontServlet</servlet-name>
            <url-pattern>/</url-pattern>
        </servlet-mapping>
```
## Annotations 
Toutes les méthodes de votre classe doivent être annotés par ***@Url*** avec comme argument "***value***" pour associer la méthode à l'url que vous voulez, et "***paramName***" (par défaut "") si votre méthode à besoin d'argument, vous devez écrire ces arguments comme suit : 
```java
@Url(value = "Classe-nom_methode", paramName = "<argument>")
    public void <nom_methode>(int <argument>){
        System.out.println(<argument>);
    }
```
## Vues 
pour rediriger vers une vue, la méthode utilisée doit retourner un object de type ModelView qui prend en paramètre :
* le nom de la page
* les données à envoyer. On peut passer les données à l'objet ModelView grâce à la méthode `addItems(String key, Object value)`  avec _key_ est le nom de la variable et _value_ sa valeur

voici un exemple :
```java

@Url(value = "Emp-save", paramName ="")
    public ModelView save(){

        ModelView view = new ModelView();
        String name = this.nom;
        view.setView("test.jsp");
        view.addItems("nom", name);

        return view;    
    }
```


## Setter
Si vous voulez passer des variables de classe de la vue vers le modele, le nom de la variable ***doit être identique*** à celui de l'attribut de la classe en question





# Auteur
 **RAHANIRAKA Antsa Anthony** _alias_ __[@AntsaAnthony](https://github.com/AntsaAnthony)__