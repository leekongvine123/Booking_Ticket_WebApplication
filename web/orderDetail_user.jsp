<%-- 
    Document   : profile
    Created on : Jun 20, 2023, 7:58:10 AM
    Author     : USER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
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
            .add-location:hover{
                opacity: 0.7;
                box-shadow: 0px 8px 12px rgba(0,128, 0, 0.5);
                transform: translateY(-15%);
                cursor: pointer;
            }
            .add-location:not(:hover) {
                transform: translateY(0);
            }
            .add-location{
                transition: transform 0.3s ease;

                margin: auto;
                
                background-color: green;
                padding: 10px;
                display: inline-block;
                border-radius: 5px;
                cursor: pointer;
            }
            .add-location > a{
                display: block;
                text-decoration: none;


            }

        </style>
    </head>
    <body>
        <!-- Header -->
        <%@include file="admin_header.jsp" %>
        <!-- End Header -->

        <div class="login-container">
            <div class="login" style="border: none; border-radius: 5px;    box-shadow: 5px 5px 5px 9px #888888;"  >
                <c:set var="c" value="${requestScope.udto}"/>


                <div class="login-form">
                    <div class="login-header">
                        <h1>Profile</h1>      
                    </div>
                    <div class="container-input-line">
                        <hr style="width:100%;text-align:left;margin-left:0">
                    </div>
                    <!--//Username-->
                    <div class="container-input">
                        <h4>Username:</h4>
                        <h4>${c.userName}</h4>    


                    </div>
                    <div class="container-input-line">
                        <hr style="width:100%;text-align:left;margin-left:0">
                    </div>

                    <!--name-->
                    <div class="container-input">
                        <h4>Name:</h4>
                        <h4>${c.userFirstName}  ${c.userLastName}</h4>                         
                    </div>
                    <div class="container-input-line">
                        <hr style="width:100%;text-align:left;margin-left:0">
                    </div>

                    <!--DOB-->
                    <div class="container-input">                        
                        <h4>Date of birth:</h4>
                        <h4>${c.userDob}</h4> 

                    </div>
                    <div class="container-input-line">
                        <hr style="width:100%;text-align:left;margin-left:0">
                    </div>

                    <!--Phone-->
                    <div class="container-input">
                        <h4>Phone:</h4>                       
                        <h4>${c.userPhoneNumber}</h4> 
                    </div>

                    <div class="container-input-line">
                        <hr style="width:100%;text-align:left;margin-left:0">
                    </div>


                    <!--Gender-->
                    <div class="container-input">
                        <h4>Gender:</h4>
                        <h4>${c.userGender}</h4> 
                    </div>
                    <div class="container-input-line">
                        <hr style="width:100%;text-align:left;margin-left:0">
                    </div>


                    <!--Submit-->

                    <div class="add-location">
                        <a style="text-decoration: none; color: white" href="adminlist?action=journey">
                            Back 
                        </a>
                    </div> 
                </div>                            
            </div>

        </div>
   
    </body>
</html>
