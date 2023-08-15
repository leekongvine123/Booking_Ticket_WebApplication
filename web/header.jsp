<%-- 
    Document   : header
    Created on : Jun 13, 2023, 10:59:38 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=0, initial-scale=1.0">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/home.css">
        <title>Document</title>

    </head>
    <body>
        <div class="container">
            <!-- Header -->
            <div class="header">
                <div class="header-logo"><a href="listlocation"><img src="img/logo/logo.png" alt=""></div></a>
                <div class="navigation">
                    <ul class="navigation-navbar">
                        <li><a href="listlocation">HOME</a></li>

                        <li><a href="history">HISTORY</a></li>    
                    </ul>
                </div>
                <c:if test="${(sessionScope.account == null )}" >

                    <div style="width: 6%;" class="header-login">     
                    </c:if>
                    <c:if test="${(sessionScope.account != null )}">
                        <div  class="header-login">  
                        </c:if>
                        <i class="fa fa-user-circle-o" aria-hidden="true" style="margin-top: 13px; margin-left: 2px;"> </i> 


                        <c:if test="${(sessionScope.account == null )}" >
                            <a href="./login.jsp" style="display: inline-block; text-decoration: none; color: black; margin: 0 0 0 5px;">Log in</a>
                        </c:if>

                        <c:if test="${(sessionScope.account != null )}">
                            <a class="header-tag-a" href="profile.jsp" style="margin: 0 0 0 5px; display: inline-block; text-decoration: none; color: black;">
                                ${sessionScope.account.userName}
                            </a>
/
                            <a href=""  style="display: inline-block; text-decoration: none; color: black;">
                                <form action="login" method="GET" >
                                    <input name="logout" type="submit" value="Log out" style="border: none; background: none    ; padding: 0 0 0 2px;  "/> 
                                </form>
                            </a>
                        </c:if>
                    </div>
                </div>

            </div>
    </body>
</html>
