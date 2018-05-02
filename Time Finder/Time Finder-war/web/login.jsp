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

    </head>
    <body>
        <jsp:useBean id= "user" scope= "session" class= "beans.UserBean" ></jsp:useBean>       
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
        <jsp:include page="navbar.jsp"></jsp:include>
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
