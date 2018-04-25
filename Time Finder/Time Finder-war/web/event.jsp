<%-- 
    Document   : event
    Created on : 25/04/2018, 3:35:17 PM
    Author     : Liandri
--%>

<%@page import="java.sql.Date"%>
<%@page import="model.Event"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel ="stylesheet" href="assets/css/landing.css">
        <link rel ="stylesheet" href="assets/css/selectDays.css">
        <jsp:include page="navbar.jsp"></jsp:include>
        <jsp:useBean id= "user" scope= "session" class= "beans.UserBean" ></jsp:useBean>
        <jsp:useBean id= "event" scope= "session" class= "beans.EventBean"></jsp:useBean>
        </head>
        <body>
            <div class="centerdiv">
                <h5><%= user.getUser().getName()%> Enter Your availability For <%= request.getParameter("selectedEvent")%></h5>
                        <div class="day">
                <form action="best.jsp">
                <%
                Event e = event.getEventByID(10);
                Date s = e.getStart();
                int index = 0;
                while (s.before(e.getEnd())) {
            %>
                <div class="centerdiv bordered">
                    
                    <p><input type="checkbox" name="vehicle" value="Bike"> Select <%= index++ %> date: <%= s.toString()%> </p>
                    
                </div>
                
            <%
                s= new Date(s.getTime()+86400000);
            }
            %>
                 <input type="submit" value="Submit">             
                </form> 
            </div>
        </div>


    </body>
</html>
