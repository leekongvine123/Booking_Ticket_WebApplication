<%-- 
    Document   : forgotPass
    Created on : Jul 7, 2023, 8:02:47 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>
        <link rel="stylesheet" href="./css/login_signin.css" />
        <title>JSP Page</title>
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
        <div class="forget-container">
            <div class="forget-password-header">
                <h1 style="">Forgot your password ?</h1>
                <p>Change your password in three eassy steps. This will help you to secure your password!</p>
                <p>1. Enter your email address below</p>
                <p>2. Our system will send you an OTP to your email</p>
                <p>3. Enter the OTP on the next page</p>
            </div>

            <form class="forget-password-body" action="forgotpass" method="POST">

                <h1 >Enter your email address</h1>
                <hr>

                <input style="height: 12%; width: 100%;" type="email" name="email-forget" placeholder="Enter your email address" required=""/>
                <p style="color: red;">${requestScope.error_forg}</p>
                <p>Enter the registered email address. Then we'll email a OTP to this address</p>
                <hr style="">

                <div class="submit-footer">
                    <input class="pass-submit" type="submit" name="email-forget" value="Get new password"/>
                    <div class="back-login">
                        <a href="login.jsp">Back to Login</a>
                    </div>

                </div>
                <div class="footer-forgot">    
                    <hr style="">
                    <h2>Don't have an Account? <a href="signin.jsp"  style="color: red; ">Register Now!</a></h2>
                </div>

            </form>
        </div>
    </body>
</html>
