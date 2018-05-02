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
        <%
            String redirectURL = "index.jsp";
            if (!user.isLoggedIn()) {
                response.sendRedirect(redirectURL);
            }
        %> 
    </head>
    <body>
        <div class="centerdiv">
            <h5><%= user.getUser().getName()%> Enter Your availability For <%= request.getParameter("selectedEvent")%></h5>
            <div class="day">
                <form action="setAvailabilityProcessing">
                    <%
                        int id = event.getEventByName(request.getParameter("selectedEvent")).getEventID();
                        Event e = event.getEventByID(id);
                        Date s = e.getStart();
                        int index = 1;
                        while (s.before(e.getEnd())) {
                    %>
                    <div class="centerdiv bordered">

                        <p><input type="checkbox" name="day<%= index%>" value="<%= s.toString()%>"> Select <%= index%> date: <%= s.toString()%> </p>
                        <p><input type="hidden" name="eventid" value="<%= id%>"></input></p>
                    </div>

                    <%
                            s = new Date(s.getTime() + 86400000);
                            index = index + 1;
                        }
                    %>
                    <input type="submit" value="Submit">             
                </form> 
            </div>
        </div>


    </body>
</html>
