<%-- 
    Document   : create
    Created on : 25/04/2018, 10:58:08 AM
    Author     : Liandri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
        <link rel ="stylesheet" href="assets/css/create.css">
        <jsp:include page="navbar.jsp"></jsp:include>
    </head>
    <body>
        <h1>Enter Event Details</h1>
        <form action="createEventProcessing" method="post">
            <p><input name="eventName">
            <p><input name="eventDescription">
            <input type="date" name="eventStart" data-date1="" data-date-format="DD MMMM YYYY" value="2018-04-15">
            <input type="date" name="eventEnd" data-date2="" data-date-format="DD MMMM YYYY" value="2015-04-15">
            <button value="registerEvent" type="submit" class="btn btn-default">Login</button>
        </form>
    </body>
    <script>
        $("start").on("change", function () {
            this.setAttribute(
                    "data-date1",
                    moment(this.value, "YYYY-MM-DD")
                    .format(this.getAttribute("data-date-format"))
                    )
        }).trigger("change")
                $("end").on("change", function () {
            this.setAttribute(
                    "data-date2",
                    moment(this.value, "YYYY-MM-DD")
                    .format(this.getAttribute("data-date-format"))
                    )
        }).trigger("change")
    </script>
</html>
