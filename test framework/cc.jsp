<%@ page language="java" contentType="text/html"
    import="java.util.*,classe.*"
    pageEncoding="UTF-8"
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
        if(request.getAttribute("id")!=null){ %>
            <p>Hello <% out.print(request.getAttribute("id")); %> </p>
        <% }else{
            out.print("mifona");
        } %>
    
</body>
</html>