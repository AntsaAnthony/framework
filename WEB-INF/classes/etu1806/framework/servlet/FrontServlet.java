package etu1806.framework.servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import utilitaire.Utilitaire;
import etu1806.framework.*;
import java.util.HashMap;
import java.util.Map;

import use.*;


public class FrontServlet extends HttpServlet {

    HashMap<String,Mapping> mappingUrls;

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        String contextPath = context.getRealPath("/");
        System.out.println("ty ilay olana "+contextPath);
        try {
            mappingUrls = use.Package.scanPackages(contextPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
  

    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.init();
        PrintWriter out = res.getWriter();
        String param = Utilitaire.getUrl(String.valueOf(req.getRequestURL()));
        out.println(" parametre url : "+param);
        if (mappingUrls.isEmpty()) {
            out.println("tsy misy inoninona eh");
        }

        for (Map.Entry<String,Mapping> entry : mappingUrls.entrySet()){
            out.println("misy ve");
            String cle = entry.getKey();
            Mapping value = entry.getValue();
            out.println("cle : "+cle+ " value : "+ value.getClassName()+" sy "+value.getMethod());
        }

        out.println("aiza eh");
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.processRequest(req, res);
        
    } 
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       this.processRequest(req, res);
   }
}

