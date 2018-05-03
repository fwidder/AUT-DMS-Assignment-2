 <%-- 
    Document   : index
    Created on : 24.04.2018, 02:36:34
    Author     : Glen Osborne and Florian Widder
 
    Landing page : display what is possable to user, 
    must be logged in to navigate to other pages
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Time Finder - Home</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/landing.css">
        <jsp:include page="navbar.jsp"></jsp:include>
    </head>
    <body>
        <h1 class="center">Welcome to Time Finder</h1>
        <div class="centerdiv">
            <img src="./assets/images/clock.jpg" alt="Smiley face" height="100" width="auto">
        </div>

        <div class="centerdiv">

            <div class="card-group">
                <div class="card">
                    <img class="card-img-top" src="assets/images/event.jpg" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">Create a new event</h5>
                        <p class="card-text">Create an event that your friends can add a time to see when the best time to hold the event is.</p>
                        <p class="card-text"><small class="text-muted"><a href="create.jsp">Create a new event</a></small></p>
                    </div>
                </div>
                <div class="card">
                    <img class="card-img-top" src="assets/images/available.png" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">Add Availability to an event</h5>
                        <p class="card-text">Add when your are free for this event so the organizer can schedule when the event should be</p>
                        <p class="card-text"><small class="text-muted"><a href="available.jsp">Add Availability to an event</a></small></p>
                    </div>
                </div>
                <div class="card">
                    <img class="card-img-top" src="assets/images/viewer.jpg" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">View Events</h5>
                        <p class="card-text">View the different events available</p>
                        <p class="card-text"><small class="text-muted"><a href="available.jsp">Show my events</a></small></p>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
