package etu1806.framework.servlet;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import jdk.jshell.execution.Util;
import utilitaire.Utilitaire;
import etu1806.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import annotation.Url;

@MultipartConfig
public class FrontServlet extends HttpServlet {

    HashMap<String,Mapping> mappingUrls;
    HashMap<String, Object> singletons;
    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        String contextPath = context.getRealPath("/");
        System.out.println("context Path : "+contextPath);
        singletons = new HashMap<String,Object>();
        try {
            mappingUrls = utilitaire.Package.scanPackages(contextPath,singletons);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
  

    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        String param = Utilitaire.getUrl(String.valueOf(req.getRequestURL()));
        
       
        try {
            if(mappingUrls.containsKey(param)){
                Mapping mapping = mappingUrls.get(param);
                Class<?> cls = Class.forName(mapping.getClassName());
                Object object;
                if(singletons.containsKey(cls.getSimpleName())){
                    resetObject(this.singletons.get(cls.getSimpleName()));
                    object = singletons.get(cls.getSimpleName());
                }else{
                    object = cls.getDeclaredConstructor().newInstance();
                }

                //formulaire
                Field[] fields = cls.getDeclaredFields();
                String fieldName;
                Object input_value;
                for (Field field : fields){
                    fieldName = field.getName();
                    if(field.getType().equals(FileUpload.class)){
                        input_value = FileUpload.uploadFile(req, this);
                    }
                    else{
                        input_value = req.getParameter(fieldName);
                    }                    
                    if(input_value!=null){
                        try {
                            String methodname = "set"+Utilitaire.capitalize(fieldName);
                            object.getClass().getMethod(methodname, field.getType()).invoke(object, parseType(input_value,field.getType()));
                        } catch (Exception e) {
                            out.print(this.singletons);
                            out.println("error 2 : "+e);
                        }
                    }else{
                        out.println("null zany");
                    }
                }
                Method method = this.getMethodByName(mapping.getMethod(), cls);
                Parameter[] parameters = method.getParameters();
                Object[] parametersObject = new Object[parameters.length];
                Object para = null;
                Object value = null;
                int i = 0;
                String[] tabParam = null; 
                String allParam = method.getAnnotation(Url.class).paramName();
                tabParam = allParam.split(",");
                    
                for (Parameter parameter : parameters) {
                    para = req.getParameter(tabParam[i]);
                    out.println("params : "+para);
                    parametersObject[i] = parseType(para, parameter.getType());    
                    i++;
                }
                if(parametersObject.length==0){
                    value = method.invoke(object);
                }
                else{
                    value = method.invoke(object,parametersObject);
                }
                    if (value instanceof ModelView) {
                        ModelView view = (ModelView) value;
                        HashMap<String,Object> data = view.getData();
                        for(String key : data.keySet()){
                            out.println("key : "+key+" , value : "+data.get(key));
                            req.setAttribute(key,data.get(key));
                        }
                        req.getRequestDispatcher(view.getView()).forward(req, res);
                    }
                else{
                    out.println("tsizy eh");
                }
            }
            else{
                if (param=="") {
                    out.println("salut");
                    for(String key : singletons.keySet()){
                        out.println(key +" : "+singletons.get(key));
                    }
                }else{
                    out.println("can not find : "+param);
                }
            }
        } catch (Exception e) {
            out.println("error 1 : "+e);
        }
        

    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.processRequest(req, res);
        
    } 
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       this.processRequest(req, res);
   }

   private Object parseType(Object input,Class type){
        Object value = null;
        String strval = String.valueOf(input);
        if (type == int.class) {
            value = Integer.parseInt(strval);
        }else if(type == double.class){
            value = Double.valueOf(strval);
        }else if(type == String.class){
            value = strval;
        }else if(type == FileUpload.class){
            value = (FileUpload) input;
        }
        return value;
    }

    public Method getMethodByName(String methodName,Class classe){
        Method meth = null;
        Method[] methods = classe.getMethods();
        for(Method method : methods){
            if(method.getName().equals(methodName)){
                meth=method;
            }
        }
        return meth;
    }

    public void resetObject(Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        Object value = null;

        for (Field field : fields){
            if(field.getType().equals(int.class) || field.getType().equals(double.class) || field.getType().equals(float.class)){
                value = 0;
            }
            else if(field.getType().equals(boolean.class)){
                value = false;
            }
            else{
                value = null;
            }

            try {
                object.getClass().getMethod("set"+Utilitaire.capitalize(field.getName()), field.getType()).invoke(object, value);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

}

