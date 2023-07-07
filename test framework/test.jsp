<%@ page language="java" contentType="text/html"
    import="java.util.*,classe.*"
    pageEncoding="UTF-8"
%>

<%
    Emp emp = (Emp) request.getAttribute("emp");

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Name</title>
</head>
<body>

    <%
        if(emp!=null){ %>
            <p>Hello <% out.print(emp.getNom());%> numero <% out.print(emp.getNumero());%></p>
            <p> ty ilay file size  <% out.print(emp.getBybyte());%> </p>
        <% }else{
            out.println("coucou");
        } %>
    
</body>
</html>