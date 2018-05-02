<%-- 
    Document   : best
    Created on : 25/04/2018, 4:28:18 PM
    Author     : Liandri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel ="stylesheet" href="assets/css/landing.css">
<link rel ="stylesheet" href="assets/css/selectDays.css">
<jsp:include page="navbar.jsp"></jsp:include>  
<jsp:useBean id= "user" scope= "session" class= "beans.UserBean" ></jsp:useBean>
<jsp:useBean id= "event" scope= "session" class= "beans.EventBean"></jsp:useBean>
<%
    String redirectURL = "index.jsp";
    if (!user.isLoggedIn()) {
        response.sendRedirect(redirectURL);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Best day so far</title>
    </head>
    <body>
        <div class="centerdiv bordered">
            <h1>Hello World!</h1>
        </div>
    </body>
</html>
