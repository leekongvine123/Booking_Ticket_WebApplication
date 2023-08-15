<%-- 
    Document   : signin
    Created on : Jun 11, 2023, 9:55:26 PM
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
        <style>
            body { 
                background: url(./img/background/background.png) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
            /* Chrome, Safari, Edge, Opera */
            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }

            /* Firefox */
            input[type=number] {
                -moz-appearance: textfield;
            }
            .gender-container {                
                display: flex;                
                justify-content: space-around;                
                min-width: 100%;
                margin: 5px 5px;

            }
            .gender-select{
                width: 91%;
            }


        </style>
    </head>
    <body>
        <!-- Header -->
        <jsp:include page="header.jsp" />
        <!-- End Header -->
        <!-- Sign in -->
        <div class="login-container">
            <form class="login" style="border: none; border-radius: 5px;    box-shadow: 5px 5px 5px 9px #888888;" action="signin" >
                <div class="login-header"><h1>Sign in</h1></div>
                <div class="login-form">
                    <!--//Username-->
                    <div class="input-container">
                        <i class="fa fa-user-circle" aria-hidden="true"></i>
                        <input class="login-input" type="text" name="username" placeholder="Username" required/>
                    </div>
                    <!--Firstname-->
                    <div class="input-container">
                        <i class="" aria-hidden="true"></i>
                        <input class="login-input" type="text" name="userfirstname" placeholder="Firstname" required/>
                    </div>
                    <!--Lastname-->
                    <div class="input-container">
                        <i class="" aria-hidden="true"></i>
                        <input class="login-input" type="text" name="userlastname" placeholder="Lastname" required/>
                    </div>
                    <!--DOB-->
                    <div class="input-container">
                        <i class="fa fa-calendar" aria-hidden="true"></i>
                        <input class="login-input" type="date" name="userdob"  required/>                        
                    </div>
                    <!--Email-->
                    <div class="input-container">
                        <i class="fa fa-envelope" aria-hidden="true"></i>
                        <input class="login-input" type="email" name="gmail" placeholder="Gmail" required/>
                        <p style="display: inline-block; margin-bottom: 0; margin-left: 80%; color: red;" >${requestScope.error_2}</p>

                    </div>
                    <!--Phone-->
                    <div class="input-container">
                        <i class="fa fa-phone-square" aria-hidden="true"></i>
                        <input class="login-input" type="number" name="phone" placeholder="Phone" required/>
                        <p style="display: inline-block; margin-bottom: 0; margin-left: 45%;  color: red;">${requestScope.error_4}</p>
                    </div>
                    <!--Gender-->
                    <div class="input-container">
                        <i class="fa fa-venus-mars" aria-hidden="true"></i>
                        <select class="login-input" name="usergender">
                            <option value="Male"selected>Male</option>
                            <option value="Female">Female</option>
                        </select>
                    </div>
                    <!--Password-->
                    <div class="input-container">
                        <i class="fa fa-key" aria-hidden="true"></i>
                        <input class="login-input" type="password" name="password" placeholder="Enter password  " required/>
                    </div>
                    <!--Confirm pass-->
                    <div class="input-container">
                        <i class="" aria-hidden="true"></i>
                        <input class="login-input" type="text" name="confirm-pass" placeholder="Confirm password" required/>
                        <p style="display: inline-block; margin-bottom: 0; margin-left: 60%;  color: red;" >${requestScope.error_3}</p>
                    </div>
                    <!--Submit-->
                    <div class="input-container">
                        <i class="" aria-hidden="true"></i>
                        <input class="login-input" type="submit" value="Sign in" />
                    </div>

                    <div class="input-container">
                        <i class="" aria-hidden="true"></i>
                        <a href="./login.jsp" style="display: inline-block; color: black; font-size: 19px;">Back to login</a>
                    </div>
                </div>                            
            </form>

        </div>
        <!-- End signin -->

        <!--footer-->
        <jsp:include  page="footer.jsp"/>
        <!--End footer-->
    </body>
</html>
