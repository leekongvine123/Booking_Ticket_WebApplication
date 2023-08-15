<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Fixed Header Table</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
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
            .add-route{
                background-color: #ccc;
                display: inline-block;
                text-decoration: none;
                color: #000;
            }        
            .add-location:hover{
                opacity: 0.7;
                box-shadow: 0px 8px 12px rgba(0,128, 0, 0.5);
                transform: translateY(-15%);
                cursor: pointer;
            }


            .add-location:hover{

                opacity: 0.7;
            }
            .add-location{

            .add-location:not(:hover) {
                transform: translateY(0);
            }
            .add-location{
                transition: transform 0.3s ease;

                margin-right:  30%;
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
            .table {
                background-color: white;
            }            

                text-decoration: none;


            }


        </style>
    </head>
    <body>
        <%@include file = "admin_header.jsp"%>

        <div class="container">
            <c:if test="${requestScope.jdto != null}">


                <div style="text-align: center"><h1>Journey's Information</h1></div>
                <div style="text-align: center" class="table-container">
                    <table class="table table-striped">
                        <thead class="sticky-top">
                            <tr>
                                <th>
                                    Journey ID
                                </th>
                                <th>
                                    From    
                                </th>
                                <th>
                                    To
                                </th>
                                <th>
                                    Date
                                </th>
                                <th>
                                    Departure time
                                </th>
                                <th>
                                    Price
                                </th>
                                <th>
                                    CarID
                                </th>
                                <th>
                                    Car's type
                                </th>
                                <th>
                                    Seat
                                </th>

                            </tr>
                        </thead>

                        <tbody>
                            <c:set var="c"  value="${requestScope.jdto}" />
                            <c:set var="journeyID" value="${c.journeyID}"/>
                            <c:set var="From" value="${c.routeID.routeStartLocation.locationCity}"/>
                            <c:set var="To" value="${c.routeID.routeDestLocation.locationCity}"/>
                            <c:set var="depTime" value="${c.depID.depTime}"/>
                            <c:set var="carType" value="${c.carID.carType}"/>
                            <tr>
                                <td>
                                    ${journeyID}
                                </td> 

                                <td>
                                    ${From}
                                </td>

                                <td>
                                    ${To}
                                </td>

                                <td>
                                    ${c.journeyDepDay}
                                </td>

                                <td>
                                    ${depTime}:00
                                </td>
                                <fmt:formatNumber value="${c.price}" pattern="#,###" var="price" />
                                <td>
                                    ${price}VND
                                </td>

                                <td>
                                    ${c.carID.carID}
                                </td> 
                                <td>
                                    ${carType}
                                </td> 

                                <td>
                                    <a href="listseat?action=journey&journeyID=${journeyID}">View</a>  

                                </td>


                            </tr>

                        </tbody>
                    </table>

                </div>
                <div class="add-location">
                    <a style="text-decoration: none; color: white" href="adminlist?action=order">
                        Back
                    </a>
                </div>

            </c:if>

        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">

            if (document.getElementById('check').value === 'exist') {
                window.alert('The Journey can not be edit because it was in use');


            }


        </script>
    </body>
</html>


