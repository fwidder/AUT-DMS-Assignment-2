<%-- 
    Document   : index
    Created on : 24.04.2018, 02:36:34
    Author     : Florian Widder
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Time Finder - Login</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
    </head>
    <body>
        <jsp:useBean id= "user" scope= "session"   
                     class= "beans.UserBean" >  
        </jsp:useBean>       
        <% if (user.getSessionID() == null || !user.getSessionID().equals(session.getId())) {
                user.setSessionID(session.getId());
            }%>
        <%
            String redirectURL = "index.jsp";
            if (user.isLoggedIn()) {
                response.sendRedirect(redirectURL);
            }
        %>
        <%
            String loginerror = "none";
            if (session.getAttribute("loginerror") != null && session.getAttribute("loginerror").equals(true)) {
                loginerror = "block";
            }
            String register = "none";
            if (session.getAttribute("register") != null && session.getAttribute("register").equals(true)) {
                register = "block";
            }
        %>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.jsp">Time Finder</a>
                </div>
                <ul class="nav navbar-nav">
                    <li ><a href="index.jsp">Home</a></li>
                    <li class="active" style="display:${user.isLoggedIn() ? 'none' : 'block'}"><a href="login.jsp">Login</a></li>
                    <li style="display:${user.isLoggedIn() ? 'none' : 'block'}"><a href="register.jsp">Register</a></li>
                    <li style="display:${!user.isLoggedIn() ? 'none' : 'block'}"><a href="logoutProcessing">Logout</a></li>
                </ul>
            </div>
        </nav>
        <div class="alert alert-warning" role="alert" style=" display:<%=loginerror%>">
            Wrong Username or Password! Try again!
        </div>
        <div class="alert alert-success" role="alert" style=" display:<%=register%>">
            Successfully registered!
        </div>
        <section class="container-fluid">
            <form action="loginProcessing" method="post">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" name="username">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" name="password">
                </div>
                <button value="login" type="submit" class="btn btn-default">Login</button>
            </form> 
        </section>
    </body>
</html>
