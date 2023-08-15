<%-- 
    Document   : login_admin
    Created on : Jun 11, 2023, 10:22:11 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>
        <link rel="stylesheet" href="./css/login_signin.css" />
    </head>
    <body>
        <div class="login-container">
            <form class="login">
                <div class="login-header"><h1>Hi Admin</h1></div>
                <div class="login-form">

                    <div class="input-container">
                        <i class="fa fa-user-circle" aria-hidden="true"></i>
                        <input class="login-input" type="text" name="username" placeholder="Username or gmail"/>
                    </div>

                    <div class="input-container">
                        <i class="fa fa-key" aria-hidden="true"></i>
                        <input class="login-input" type="password " name="password" placeholder="Password"/>
                    </div>

                    <div class="input-container">
                        <input class="login-input" type="submit" name="password"value="LOGIN" />
                    </div>


                    <div class="input-container">
                        <a href="./login.jsp">back to login</a>
                    </div>



                </div>

                <!-- login form -->
            </form>
            <!-- login -->
        </div>
        <!-- Login container -->

    </body>
</html>
