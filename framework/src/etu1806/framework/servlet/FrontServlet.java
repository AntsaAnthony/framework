package etu1806.framework.servlet;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jdk.jshell.execution.Util;
import utilitaire.Utilitaire;
import etu1806.framework.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import annotation.Url;


public class FrontServlet extends HttpServlet {

    HashMap<String,Mapping> mappingUrls;
    HashMap<String, Object> singletons;

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        String contextPath = context.getRealPath("/");
        System.out.println("context Path : "+contextPath);
        try {
            mappingUrls = utilitaire.Package.scanPackages(contextPath);
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
                Object object = cls.getDeclaredConstructor().newInstance();

                //formulaire
                Field[] fields = cls.getDeclaredFields();
                String fieldName;
                String input_value;
                for (Field field : fields){
                    fieldName = field.getName();
                    input_value = req.getParameter(fieldName);
                    
                    String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    
                    if(input_value!=null){
                        try {
                            String methodname = "set"+name;
                            object.getClass().getMethod(methodname, field.getType()).invoke(object, input_value);
                        } catch (Exception e) {
                            out.println("ato amin'ny methodname "+e);
                        }
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
                }else{
                    out.println("can not find : "+param);
                }
            }
        } catch (Exception e) {
            out.println(e);
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
}

