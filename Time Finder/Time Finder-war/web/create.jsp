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
        <jsp:include page="navbar.jsp"></jsp:include>
    </head>
    <body>
      <h1>Enter Event Details</h1>
      <form action="register" method="post">
        <p><input name="eventname">
        <p><input name="description">
        <div class="container">
            <div class="row">
                <div class='col-sm-6'>
                    <div class="form-group">
                        <div class='input-group date' id='datetimepicker1'>
                            <input type='text' class="form-control" />
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                        </span>
                    </div>
                </div>
            </div>
            <script type="text/javascript">
                $(function () {
                $('#datetimepicker1').datetimepicker();
                 ]});
            </script>
            </div>
        </div>
      </form>
    </body>
</html>
