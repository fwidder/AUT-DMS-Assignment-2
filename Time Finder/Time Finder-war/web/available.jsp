<%-- 
    Document   : available
    Created on : 25/04/2018, 11:11:02 AM
    Author     : Liandri
--%>

<%@page import="model.Event"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="navbar.jsp"></jsp:include>
        <jsp:useBean id= "user" scope= "session" class= "beans.UserBean" ></jsp:useBean>
        <jsp:useBean id= "event" scope= "session" class= "beans.EventBean"></jsp:useBean>
    </head>
    <body>
        <select>
            
            <% Event[] events = event.getAllEvents(); 
                for(int i = 0; i< events.length ;i++){  %>
            <option value="<%= events[i].getName()%> "><%= events[i].getName()%></option>
            <%}%>
        </select>
    </body>
</html>
