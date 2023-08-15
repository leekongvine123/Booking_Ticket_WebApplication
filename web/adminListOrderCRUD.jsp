<%-- 
    Document   : adminListOrderCRUD
    Created on : Jul 16, 2023, 10:59:10 PM
    Author     : Admin
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>
        <link rel="stylesheet" href="./css/route.css" />
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
            .add-route{
                background-color: #ccc;
                display: inline-block;
                text-decoration: none;
                color: #000;
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
            .table {
                background-color: white;
            }
        </style>
    </head>
    <body>

        <%@include file = "admin_header.jsp"%>

        <c:if test="${(requestScope.existError != null)}">
            <input value="${requestScope.existError}" id="check" hidden=""/>
        </c:if>




        <form action="adminsearch" class="form_Sort_Routes" method="GET">

            <input type="hidden" name="action" value="searchOrder"/>

            <div class="form_Sort_Routes-container">
                <div class="form_Sort_Routes-container_small">
                    <select name="form_Search_Routes-Time">
                        <option value="">Order Date</option>
                        <option  ${requestScope.condition == "new" ? "selected" : ""} value="new">Newest</option>
                        <option   ${requestScope.condition == "old" ? "selected" : ""}value="old">Oldest</option>                                                
                    </select>
                </div>

                <div class="form_Sort_Routes-container_small">
                    <select name="form_Search_Routes-ID">
                        <option value="0">Route ID</option>
                        <c:forEach items="${requestScope.listroute}" var="c">
                            <option ${requestScope.routeID == c.routeID ? "selected" : ""} value="${c.routeID}">${c.routeStartLocation.locationCity} -> ${c.routeDestLocation.locationCity} </option>
                        </c:forEach>
                    </select>
                </div>
                <input type="submit" value="Search  " class="form_Sort_Routes-submit">
            </div>
        </form>














        <div class="container">
            <div style="text-align: center"><h1>Order management</h1></div>
            <div style="text-align: center" class="table-container">
                <table class="table table-striped">
                    <thead class="sticky-top">
                        <tr>

                            <th>OrderID</th>
                            <th>UserID</th>
                            <th>JourneyID</th>
                            <th>CarID</th>
                            <th>OrderDob</th>
                            <th>Quantity</th>
                            <th>Seat</th>
                            <th>ToTal Price</th>
                            <th>Status</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${requestScope.list}" var="o">


                            <tr>
                                <td>${o.orderID}</td>
                                <td><a href="orderdetail?action=user&userID=${o.userID.userID}">${o.userID.userID}</a></td>
                                <td><a href="orderdetail?action=journey&journeyID=${o.journeyID.journeyID}">${o.journeyID.journeyID}</a></td>
                                <td><a href="orderdetail?action=car&carID=${o.journeyID.carID.carID}">${o.journeyID.carID.carID}</a></td>
                                <td>${o.orderDob}</td>
                                <td>${o.quantity}</td>
                                <td>
                                    <a href="listseat?action=view&orderID=${o.orderID}&journeyID=${o.journeyID.journeyID}">
                                        View
                                    </a>
                                </td>
                                <fmt:formatNumber value="${o.totalPrice}" pattern="#,###" var="price" />
                                <td>${price}VND</td>

                                <td>${(o.status=='d')?'Done':'Pending'}</td>
                                <td><a href="admindelete?action=order&orderID=${o.orderID}">Delete</a></td>
                                <td><a href="listlocation?orderID=${o.orderID}">Edit</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>

        </div>
        <script type="text/javascript">
            if (document.getElementById('check').value === 'exist') {
                window.alert('The Order can not be modify because it was in use');


            }
        </script>
    </body>
</html>
