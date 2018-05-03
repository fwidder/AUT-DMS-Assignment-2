<%-- 
    Document   : available
    Created on : 25/04/2018, 11:11:02 AM
    Author     : Glen Osborne and Florian Widder

    Available  : this page prompt the user to select an event to either select
    an event to set availablity or to review the best date so far
--%>

<%@page import="model.Event"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel ="stylesheet" href="assets/css/landing.css">
        <jsp:include page="navbar.jsp"></jsp:include>
        <jsp:useBean id= "user" scope= "session" class= "beans.UserBean" ></jsp:useBean>
        <jsp:useBean id= "event" scope= "session" class= "beans.EventBean"></jsp:useBean>
        <!--   REDIRECT IF NOT LOGGED IN          -->
        <%
            String redirectURL = "index.jsp";
            if (!user.isLoggedIn()) {
                response.sendRedirect(redirectURL);
            }
        %>
    </head>
    <body>
        <br>
        <h5 class="center">Set availability</h5>
        <br>
        <div class="centerdiv bordered">
            <h5 class="center">Select event to set availability </h5>
            <br>
            <!--   PRESENT THE EVENTS LIST TO SELECT AN EVENT TO SET AVAILABLITY          -->
            <form action="event.jsp">
                <select id="selectedEvent" name="selectedEvent">
                    <% Event[] events = event.getAllEvents();
                        for (int i = 0; i < events.length; i++) {%>
                    <option value="<%= events[i].getName()%> "><%= events[i].getName()%></option>
                    <%}%>
                </select>
                <input type="submit" value="Submit" name="event">
            </form>
        </div>
        <br>
        <h5 class="center">Get Best date for event</h5>
        <br>
        <div class="centerdiv bordered">
            <h5 class="center">Select event to view best day so far... </h5>
            <br>
            <!--   SELECT AN EVENT TO SEE THE BEST DATE         -->
            <form action="bestDayProcessing">
                <select id="selectedEvent2" name="selectedEvent">
                    <% Event[] events2 = event.getAllEvents();
                        for (int i = 0; i < events.length; i++) {%>
                    <option value="<%= events2[i].getName()%> "><%= events2[i].getName()%></option>
                    <%}%>
                </select>
                <input type="submit" value="Submit" name="best">
            </form>
        </div>
    </body>
    <!--   GET THE SELECTED NAME OF THE EVENT FROM DROP DOWN LIST          -->
    <script>
        $("selectedEvent").on("click", function () {
            var event = document.getElementById("selectedEvent");
        }).trigger("change");
    </script>
</html>
