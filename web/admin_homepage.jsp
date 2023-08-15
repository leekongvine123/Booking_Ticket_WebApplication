<%-- 
Document   : admin_homepage
Created on : Jul 7, 2023, 9:50:21 PM
Author     : Admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

        <link rel="stylesheet" href="css/admin_home.css">
        <style>
            body { 
                background: url(img/background/background.png) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
                overflow-x: hidden;
            }
        </style>
    </head>
    <body>


        <!-- Header -->

        <%@include file = "admin_header.jsp"%>


        <div class="container">
            <div class="menu">
                <h1 style="width: 100%;">Menu Management</h1>
                <div onclick="redirect('order')" class="menu_items">Order Management</div>
                <div onclick="redirect('journey')"class="menu_items">Journey Management</div>
                <div onclick="redirect('route')" class="menu_items">Route Management</div>
                <div onclick="redirect('location')" class="menu_items">Location Management</div>
                <div onclick="redirect('car')" class="menu_items">Car Management</div>
                <div onclick="redirect('chart')" class="menu_items">Dashboard</div>
            </div>
        </div>


     
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script type="text/javascript">
                    function redirect(action) {
                        window.location = "adminlist?action=" + action;
                    }
        </script>


    </body>
</html>
