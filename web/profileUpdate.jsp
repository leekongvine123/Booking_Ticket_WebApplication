<%-- 
    Document   : profileUpdate
    Created on : Jun 20, 2023, 8:46:15 AM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

        </style>
    </head>
    <body>
        <!-- Header -->
        <jsp:include page="header.jsp" />
        <!-- End Header -->




        <div class="login-container">
            <form class="login" style="border: none; border-radius: 5px;    box-shadow: 5px 5px 5px 9px #888888;" action="updateprofile" >
                <c:set var="c" value="${sessionScope.account}"/>

                <div class="login-header"><h1>Edit profile</h1></div>
                <div class="login-form">
                    <!--//Username-->
                    <div class="input-container">
                        <h5>Username:</h5>
                        <input class="login-input" type="text" name="update-name" placeholder="Username" value="${c.userName}" />                        
                    </div>
                    <!--Firstname-->
                    <div class="input-container">
                        <h5>First name:</h5>
                        <input class="login-input" type="text" name="update-firstname"  placeholder="Firstname" value="${c.userFirstName}" />                        
                    </div>
                    <!--Lastname-->
                    <div class="input-container">
                        <h5>Last name:</h5>
                        <input class="login-input" type="text" name="update-lastname" placeholder="Lastname" value="${c.userLastName}" required/>
                    </div>
                    <!--DOB-->
                    <div class="input-container">
                        <h5>Date of birth:</h5>
                        <input class="login-input" type="date" name="update-dob"  value="${c.userDob}" required/>                        
                    </div>

                    <!--Phone-->
                    <div class="input-container">
                        <h5>Phone:</h5>
                        <input class="login-input" type="number" name="update-phone" placeholder="Phone" value="${c.userPhoneNumber}" required/>
                        <p style="display: inline-block; margin-bottom: 0; margin-left: 45%;  color: red;">${requestScope.error_4}</p>
                        <p style="color: red">${requestScope.error_5}</p>

                    </div>
                    <!--Gender-->
                    <div class="input-container">
                        <h5>Gender:</h5>
                        <select class="login-input" name="update-gender">
                            <option value="Male" selected>Male</option>
                            <option value="Female">Female</option>
                        </select>
                    </div>

                    <!--Submit-->
                    <div class="input-container">

                        <input class="login-input" type="submit" value="Save" />
                    </div>

                    <div class="input-container">
                        <i class="" aria-hidden="true"></i>
                        <a href="./changePass.jsp" style="display: inline-block; ">Change password</a>
                    </div>

                    <div class="input-container">
                        <i class="" aria-hidden="true"></i>
                        <a href="./login.jsp" style="display: inline-block; color: black;">Back to login</a>
                    </div>

                </div>                            
            </form>

        </div>


        <jsp:include  page="footer.jsp"/>
    </body>
</html>
