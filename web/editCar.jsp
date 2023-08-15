<%-- 
    Document   : carEdit
    Created on : Jul 16, 2023, 9:58:36 PM
    Author     : USER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body { 
                background: url(./img/background/background.png) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }

            .location-container{
                width: 100%;
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
                margin: 10% 0;






            }
            table {
                width: 100%;
                border-collapse: collapse;
                caption-side: top; /* Ensure caption appears at the top */
                text-align: center;
                align-items: center;    

            }

            th, td {
                padding: 8px;

                border-bottom: 1px solid #333;
            }
            td > input{
                width: 100%;
            }
            th {
                background-color: #f2f2f2;
            }

            img {
                max-width: 100px;
                max-height: 100px;
            }
            .location-edit-container{
                width: 50%;
                display: flex;  
                justify-content: center;
                text-align: center;
                align-items: center;
                margin-top: 10%;
                flex-direction: column;
                box-shadow: 0 0 10px 5px #888888;
                padding-bottom: 0.5%;

            }

            .location-before-container{
                width: 50%;
                display: flex;  
                justify-content: center;
                text-align: center;
                align-items: center;
                margin-top: 5%;
                flex-direction: column;
                box-shadow: 0 0 10px 5px #888888;

                display: inline-block;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                background-color: white;
                color: green;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                transition: all 0.3s ease;

            }
            .location-edit-container > td > input{
                width: 100%;

            }
            .locSubmit:hover{
                opacity: 0.7;
            }
            .locSubmit{
                background-color: green;
                color: white;
                border: none;
                padding: 10px;

                margin-top: 1%;
                width: 100%;
            }
            .car-option > p{
                width: 15%;
            }


            .car-option{
                display: flex;
                justify-content: space-around;
                flex-wrap: wrap;    
                align-items: center;
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
                margin-right:  47.2%;
                margin-top: 5px;
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
        <%@include file="admin_header.jsp" %>

        <form class="location-container" action="adminedit" method="Post">
            <div class="location-before-container">
                <c:set var="c" value="${requestScope.carEdit}"/>
                <input type="hidden" name="action" value="car"/>
                <input type="hidden" name="carID" value="${c.carID}"/>

                <table>
                    <thead>
                        <tr>
                    <h1 style="background-color: #f2f2f2;width: 100%; margin: 0;">Edit location</h1>
                    </tr>
                    <tr>
                        <th>Car ID</th>
                        <th>Car Type</th>
                        <th>Cabin</th>
                        <th>Wifi</th>
                        <th>License Plate</th>
                        <th>Status</th>
                    </tr>
                    </thead>



                    <tbody>
                        <tr>
                            <td>${c.carID}</td>
                            <!--Car type-->
                            <td class="car-option">
                                <c:if test="${c.carType == '1F'}">
                                    <p>1F<input style="margin-left: 10%;" name="carType" value="1F" type="radio" checked=""/></p>
                                    <p>2F<input style="margin-left:  10%;" name="carType" value="2F" type="radio" /></p>

                                </c:if>

                                <c:if test="${c.carType == '2F'}">
                                    <p>1F<input style="margin-left: 10%;" name="carType" value="1F" type="radio"  /></p>
                                    <p>2F<input style="margin-left:  10%;" name="carType" value="2F" type="radio"  checked=""/></p>
                                    </c:if>
                            </td>

                            <!--Car option-->

                            <c:if test="${c.cabin == 'T'}">
                                <td><input  name="carCabin" value="T" type="checkbox"  checked/></td>
                                </c:if>

                            <c:if test="${c.cabin == 'F'}">
                                <td><input  name="carCabin" type="checkbox" /></td>
                                </c:if>    

                            <c:if test="${c.wifi == 'T'}">
                                <td><input  name="carWifi"  value="T" type="checkbox"  checked/></td>
                                </c:if>

                            <c:if test="${c.wifi == 'F'}">
                                <td><input  name="carWifi"  type="checkbox" /></td>
                                </c:if>  


                            <td>${c.licensePlate}</td>

                            <c:if test="${c.status == true}">
                                <td style="color: blue;">available</td>
                            </c:if>

                            <c:if test="${c.status == false}">
                                <td>unavailable</td>
                            </c:if>



                        </tr>


                    </tbody>
                </table>
                <input class="locSubmit" type="submit" value="Save" required/>

            </div>

            <div style="" class="add-location">
                <a style="text-decoration: none; color: white" href="adminlist?action=car">
                    Back
                </a>
            </div>
        </form>

    </body>
</html>
