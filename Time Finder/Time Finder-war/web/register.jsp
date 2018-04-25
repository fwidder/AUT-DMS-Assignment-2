<%-- 
    Document   : index
    Created on : 24.04.2018, 02:36:34
    Author     : Florian Widder
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Time FInder - Register</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="navbar.jsp"></jsp:include>
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
            String passerror = "none";
            if (session.getAttribute("passerror") != null && session.getAttribute("passerror").equals(true)) {
                passerror = "block";
            }
        %>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.jsp">Time Finder</a>
                </div>
                <ul class="nav navbar-nav">
                    <li ><a href="index.jsp">Home</a></li>
                    <li style="display:${user.isLoggedIn() ? 'none' : 'block'}"><a href="login.jsp">Login</a></li>
                    <li class="active" style="display:${user.isLoggedIn() ? 'none' : 'block'}"><a href="register.jsp">Register</a></li>
                    <li style="display:${!user.isLoggedIn() ? 'none' : 'block'}"><a href="logoutProcessing">Logout</a></li>
                </ul>
            </div>
        </nav>                
        <div class="alert alert-danger" role="alert" style=" display:<%=passerror%>">
            Passwords do not match or Username empty or Email empty!
        </div>
        <section class="container-fluid">
            <form action="registerProcessing" method="post">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" name="username">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" name="email">
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" name="password">
                </div>
                <div class="form-group">
                    <label for="passwordrepeat">Repeat Password:</label>
                    <input type="password" class="form-control" name="passwordrepeat">
                </div>
                <button value="register" type="submit" class="btn btn-default">Register</button>
            </form> 
        </section>
    </body>
</html>
