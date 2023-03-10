package etu1806.framework.servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import utilitaire.Utilitaire;

public class FrontServlet extends HttpServlet {

    HashMap<String,Mapping> mappingUrls;
  

    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        out.println(req.getRequestURL());
        String param = Utilitaire.getUrl(String.valueOf(req.getRequestURL()));
        out.println(" parametre url : "+param);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        this.processRequest(req, res);
        
    } 
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       this.processRequest(req, res);
   }
}

