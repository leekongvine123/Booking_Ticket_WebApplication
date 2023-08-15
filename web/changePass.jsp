<%-- 
    Document   : changePass
    Created on : Jun 20, 2023, 5:24:08 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./css/login_signin.css" />
        <style>
            body { 
                background: url(./img/background/background.png) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }


        </style>

    </head>
    <body>
        <jsp:include page="header.jsp" />








        <div class="login-container">
            <form class="login" style="border: none; border-radius: 5px;    box-shadow: 5px 5px 5px 9px #888888;" action="updateprofile" method="POST" >
                <div class="login-header"><h1>Change password</h1></div>

                <div class="login-form">
                    <div class="container-input-line">
                        <hr style="width:100%;text-align:left;margin-left:0">
                    </div> 
                    <!--//Username-->
                    <div class="input-container">
                        <h4>Enter current password:</h4>
                        <input class="login-input" type="password" name="cur_pass" placeholder="Enter current password"/>
                        <p style="color: red;">${requestScope.error_7}</p>
                    </div>
                    <!--Firstname-->
                    <div class="input-container">
                        <h4>Enter new password:</h4>
                        <input class="login-input" type="password" name="new_pass" placeholder="Enter new password"/>
                    </div>
                    <!--Lastname-->
                    <div class="input-container">
                        <h4>Confirm new password: </h4>
                        <input class="login-input" type="text" name="conf_new_pass" placeholder="Confirm new password"/>
                        <p style="color: red;">${requestScope.error_8}</p>
                    </div>
                    <div class="container-input-line">
                        <hr style="width:100%;text-align:left;margin-left:0">
                    </div> 
                    <div class="input-container">
                        <input class="login-input" type="submit" value="save" />

                    </div>

                </div>   
            </form>

        </div>
        <!--footer-->
        <jsp:include  page="footer.jsp"/>
    </body>
</html>
