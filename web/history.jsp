<%-- 
<<<<<<< HEAD
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

                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="./css/route.css" />
        <title>JSP Page</title>
        <style>
             body { 
                background: url(./img/background/background.png) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
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
            .history_header{
                 border: none;
    border-radius: 35px;
    background-color: white;
    color: green;
    text-align: center;
    text-decoration: none;
    font-weight: bold;
    box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
    transition: all 0.3s ease;
    display: inline-block;
    padding: 0 10%;
            }
        </style>
    </head>
    <body>

        <%@include file = "header.jsp"%>

    
        <div class="container" style="">
            
            <div style="margin:10% 0 20% 0;display: flex; flex-direction: column;">
                
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
                    
            <div style="text-align: center" class="table-container">
                <table class="table table-striped">
                    <thead class="sticky-top">
                        <tr>
                            <th>OrderDob</th>
                            <th>Route</th>
                            <th>Departure Date</th>
                            <th>Departure Time</th>
                              <th>license plates</th>
                            <th>Station</th>
                             <th>Seat</th>
                            <th>ToTal Price</th>
                          
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${requestScope.list}" var="o">


                            <tr>
                           
                                <td>${o.orderDob}</td>
                      <td>${o.journeyID.routeID.routeStartLocation.locationCity} <i class="fa fa-arrow-right" aria-hidden="true"></i>  ${o.journeyID.routeID.routeDestLocation.locationCity}</td>
                               
                      <td>${o.journeyID.journeyDepDay}</td>
                        <td>${o.journeyID.depID.depTime}:00</td>
                        <td>${o.journeyID.carID.licensePlate}</td>
                         <td>${o.journeyID.routeID.routeStartLocation.locationStation}:00</td>
                      <td>
                          <a href="listseat?action=view&orderID=${o.orderID}&journeyID=${o.journeyID.journeyID}">
                              View
                          </a>
                                </td>
                        <fmt:formatNumber value="${o.totalPrice}" pattern="#,###" var="price" />
                        <td>${price}VND</td>

                 
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
   </div>
        </div>
             <jsp:include  page="footer.jsp"/>
        <script type="text/javascript">
            if (document.getElementById('check').value === 'exist') {
                window.alert('The Order can not be modify because it was in use');


            }
        </script>

    </body>
</html>
