<%-- 
    Document   : resetPass
    Created on : Jul 7, 2023, 10:05:34 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>
        <title>JSP Page</title>
    </head>
    <style>
        body { 
            background: url(./img/background/background.png) no-repeat center center fixed; 
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }
        .resetpass-container{
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;                
            width: 100%;
            height: 1000px;
        }
        .resetpass-cont{
            padding: 20px;
            background-color: #fff; 
            width: 30%;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            box-shadow: 5px 10px 10px #888888;
        }
        .repass-input{
            margin: 10px 0;
            width: 100%;
            height: 40px;
        }
        .reset-password-body{
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            padding: 30px;
            width: 70%;
            margin: auto;
        }
        .repass-submit:hover{
            opacity: 0.7;
        }
        .repass-submit{
            height: 30%;          
            background-color: #3366ff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .resetpass_cont{
            width: 100%; 
            display: flex; 
            align-items: center; 
        }
    </style>
    <body>


        <div class="resetpass-container">
            <div  class="resetpass-cont">

                <h1>Reset password</h1>  
                <form class="reset-password-body" action="resetpass" method="POST">
                    <input type="hidden" name="userID" value="${requestScope.UserId}"/>

                    <div class="resetpass_cont">
                        <i style="margin-right: 20px;" class="fa fa-key" aria-hidden="true"></i>
                        <input class="repass-input" name="newpass-input"  type="password" placeholder="New password" />
                    </div>
                    <p style="margin: 0;">${requestScope.conf_error}</p>
                    <div class="resetpass_cont">
                        <i style="margin-right: 20px;" class="fa fa-check" aria-hidden="true"></i>
                        <input class="repass-input" name="confpass-input"  type="text" placeholder="Confirm new password" />
                    </div>
                    <input class="repass-submit" style="width: 40%; margin-top: 5%;"type="submit" value="Reset"/>

                </form>    
                <hr />

            </div>

        </div>




    </body>
</html>
