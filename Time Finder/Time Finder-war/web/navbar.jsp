<%-- 
    Document   : navbar
    Created on : 25/04/2018, 12:33:44 PM
    Author     : Liandri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="assets/css/landing.css">
    </head>
    <body>
        <jsp:useBean id= "user" scope= "session"   class= "beans.UserBean" >  
        </jsp:useBean>        
        <% if (user.getSessionID() == null || !user.getSessionID().equals(session.getId())) {
                user.setSessionID(session.getId());
            }%>            
        <%
            String logout = "none";
            if (session.getAttribute("logout") != null && session.getAttribute("logout").equals(true)) {
                logout = "block";
            }
        %>
        


        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Time Finder</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="navbar-dark bg-dark">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item" style="display:${user.isLoggedIn() ? 'none' : 'block'}">
                        <a class="nav-link" <a href="login.jsp">Login</a>
                    </li>
                    <li class="nav-item" style="display:${user.isLoggedIn() ? 'none' : 'block'}">
                        <a class="nav-link" href="register.jsp">Register</a>
                    </li>
                    <li class="nav-item" style="display:${!user.isLoggedIn() ? 'none' : 'block'}">
                        <a class="nav-link" User: <%= user.getUsername()%></a>
                    </li>
                    <li class="nav-item" style="display:${!user.isLoggedIn() ? 'none' : 'block'}">
                        <a class="nav-link" href="logoutProcessing">Logout</a>
                    </li>
                    <li class="nav-item" style="display:${!user.isLoggedIn() ? 'none' : 'block'}">
                        <a class="nav-link" href="create.jsp">Create Event</a>
                    </li>
                    <li class="nav-item" style="display:${!user.isLoggedIn() ? 'none' : 'block'}">
                        <a class="nav-link" href="available.jsp">Available</a>
                    </li>
                    <li class="nav-item" style="display:${!user.isLoggedIn() ? 'none' : 'block'}">
                        <a class="nav-link" href="viewevent.jsp">View</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="alert alert-info" role="alert" style=" display:<%=logout%>">
            Successfully logged out!
        </div>
    </body>
</html>
