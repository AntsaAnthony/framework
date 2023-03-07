package servlet;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import utilitaire.Utilitaire;

public class FrontServlet extends HttpServlet {
  

    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        out.println(req.getRequestURL());
        String test = Utilitaire.getUrl(String.valueOf(req.getRequestURL()));
        out.println(" parametre url : "+test);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter out = res.getWriter();
        this.processRequest(req, res);
        out.println("post");
        
    } 
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
       PrintWriter out = res.getWriter();
       this.processRequest(req, res);
       out.println("get");
   }
}

