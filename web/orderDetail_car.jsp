<%-- 
<<<<<<< HEAD
    Document   : adminListCarCRUD
    Created on : Jul 9, 2023, 2:23:15 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">

        <title>JSP Page</title>
        <style>
            body { 
                background: url(img/background/background.png) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
                overflow-x: hidden;
            }
             .table-container {
                   border-radius: 25px;
                max-height: 300px;
                overflow-y: scroll;
            }
            .sticky-top{
                color:  black !important; 
            }
            th,td {
                white-space: nowrap;

            }
            .table > thead > tr > th{
                background-color: #ccc;
            }
            td {

                border:1px solid black;
            }

            .locImg{
                width: 30%;
                height: 30%;

            }
            .add-location:hover{
                opacity: 0.7;
            }
            .add-location{
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
        <%@include file = "admin_header.jsp"%>

        <c:if test="${(requestScope.existError != null)}">


            <input value="${requestScope.existError}" id="check" hidden=""/>
        </c:if>
        <div class="container">
            <div style="text-align: center"><h1>Car management</h1></div>
            <div style="text-align: center" class="table-container">
                <table style="background-color: white;" class="table table-striped">
                    <thead class="sticky-top">
                        <tr>
                            <th>
                                car Id
                            </th>
                            <th>
                                Car type
                            </th>
                            <th>
                                Cabin
                            </th>
                            <th>
                                Wifi
                            </th>  
                            <th>
                                License plate
                            </th>  
                            <th>
                                Status
                            </th>  
                           
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${requestScope.listcar}" var="c">
                            <tr>
                                <td>${c.carID}</td>  
                                <td>${c.carType}</td>  

                                <c:if test="${c.cabin == 'T'}">
                                    <td style="color: blue;">available</td>
                                </c:if>
                                <c:if test="${c.cabin == 'F'}">
                                    <td>unavailable</td>
                                </c:if>    

                                <c:if test="${c.wifi == 'T'}">
                                    <td style="color: blue;">available</td>
                                </c:if>
                                <c:if test="${c.wifi == 'F'}">
                                    <td>unavailable</td>
                                </c:if>  

                                <td>${c.licensePlate}</td>
                                <c:if test="${c.status == true}">
                                    <td style="color: blue;">available</td>
                                </c:if>

                                <c:if test="${c.status == false}">
                                    <td>unavailable</td>
                                </c:if>

                                <!--Edit Car-->



                              
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="add-location">
                <a style="text-decoration: none; color: white" href="adminlist?action=order">
                    Back
                </a>
            </div>
        </div>

        <script type="text/javascript">
            if (document.getElementById('check').value === 'exist') {
                window.alert('The Location can not be change because it was in use');
            }
        </script>


    </body>
</html>
