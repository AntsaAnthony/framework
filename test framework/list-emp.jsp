<%@ page language="java" contentType="text/html"
    import="java.util.*,classe.*"
    pageEncoding="UTF-8"
%>
<%
    ArrayList<Emp> data = (ArrayList<Emp>) request.getAttribute("lst");
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
    <p>Hello <%
        for(int i=0; i<data.size();i++){
            out.println(data.get(i).getNom());
        }
     %> !!</p>
</body>
</html>