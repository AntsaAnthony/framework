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

import annotation.Url;


public class FrontServlet extends HttpServlet {

    HashMap<String,Mapping> mappingUrls;

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
                Object o = cls.getDeclaredConstructor().newInstance();
                Field[] fields = o.getClass().getDeclaredFields();
                String fieldName;
                String input_value;
                for (Field field : fields){
                    fieldName = field.getName();
                    input_value = req.getParameter(fieldName);

                    String name = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                    
                    if(input_value!=null){
                        try {
                            String methodname = "set"+name;
                            o.getClass().getMethod(methodname, String.class).invoke(o, input_value);
                        } catch (Exception e) {
                            out.println(e);
                        }
                    }
                }
                // String g = "String.class";
                // Class[] b = new Class<?>[] {String.class, String.class};
                Method m = o.getClass().getMethod(mapping.getMethod(),int.class);
                
                String[] tabParam = null; 
                
                try {
                    String allParam = m.getAnnotation(Url.class).paramName();
                    tabParam = allParam.split(",");
                } catch (Exception ignored) { }
                    Object value = null;
                    
                    for(String paramEter : tabParam ) {
                        out.print(paramEter.trim());
                        String parametre = req.getParameter(paramEter.trim());
                        if (parametre!=null) {
                            value = m.invoke(o,Integer.parseInt(parametre));
                        }
                    }
                    // value = m.invoke(o,0);
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
}

