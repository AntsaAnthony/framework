package etu1806.framework.servlet;

import java.io.*;
import java.lang.reflect.Method;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import utilitaire.Utilitaire;
import etu1806.framework.*;
import java.util.HashMap;
import java.util.Map;


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
                Object value = cls.getMethod(mapping.getMethod()).invoke( null);
                if (value instanceof ModelView) {
                    ModelView view = (ModelView) value;
                    HashMap<String,Object> data = view.getData();
                    for(String key : data.keySet()){
                         req.setAttribute(key,data.get(key));
                    }
                    req.getRequestDispatcher(view.getView()).forward(req, res);
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
            throw new ServletException(e);
        }
        

    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.processRequest(req, res);
        
    } 
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       this.processRequest(req, res);
   }
}

