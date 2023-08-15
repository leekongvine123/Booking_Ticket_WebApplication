<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Fixed Header Table</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>
        <link rel="stylesheet" href="./css/route.css" />
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
        <c:if test="${(requestScope.check_exist != null)}">
            <input value="${requestScope.check_exist}" id="check" hidden=""/>
        </c:if>

        <form action="adminsearch" class="form_Sort_Routes" method="GET">
            <input type="hidden" name="action" value="searchJourney"/>
            <div class="form_Sort_Routes-container">
                <div class="form_Sort_Routes-container_small">
                    <!--<h3>Price</h3>-->
                    <select name="form_Search_Routes-Price">
                        <option value="">Price</option>
                        <option  ${requestScope.price_raw == "desc" ? "selected" : ""}    value="desc">High-Low</option>
                        <option  ${requestScope.price_raw == "asc" ? "selected" : ""} value="asc">Low-High</option>
                    </select>
                </div>

                <div class="form_Sort_Routes-container_small">
                    <select name="form_Search_Routes-Time" id=""  >
                        <option value="">Time</option>
                        <option  ${requestScope.time == "0:6" ? "selected" : ""} value="0:6">0h - 6h</option>
                        <option   ${requestScope.time == "6:12" ? "selected" : ""}value="6:12">6h - 12h</option>
                        <option  ${requestScope.time == "12:18" ? "selected" : ""}value="12:18">12h - 18h</option>
                        <option ${requestScope.time == "18:24" ? "selected" : ""} value="18:24">18h - 24h</option>
                    </select>
                </div>

                <div class="form_Sort_Routes-container_small">                                                                                        
                    <select name="cartype" id=""  >
                        <option value="">Car's Type</option>
                        <option ${requestScope.carType == "1F" ? "selected" : ""} value="1F">1 Floor</option>
                        <option ${requestScope.carType == "2F" ? "selected" : ""} value="2F">2 Floor</option>
                    </select>
                </div>

                <div class="form_Sort_Routes-container_small">
                    <select style="text-align: center; min-width: 10px; font-size: 20px;" name="routeid" >
                        <option value="0">Route</option>
                        <c:forEach items="${requestScope.listroute}" var="c">
                            <option style="font-size: 15px;" ${requestScope.rId == c.routeID ? "selected" : ""} value="${c.routeID}">${c.routeStartLocation.locationCity} - > ${c.routeDestLocation.locationCity}</option>
                        </c:forEach>
                    </select>
                </div>

                <input type="submit" value="Search" class="form_Sort_Routes-submit">
            </div>
        </form>
        <div class="container">
            <div style="text-align: center"><h1>Journey management</h1></div>
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
                            
                            <th>
                                Edit
                            </th>
                            <th>
                                Delete
                            </th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="c"  items="${requestScope.list}" >
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
                                    <a style="color: #088000" href="listseat?action=journey&journeyID=${journeyID}">View</a>  

                                </td>

                                <td>
                                    <a href="adminedit?action=journey&journeyID=${journeyID}">Edit journey :  ${journeyID}</a>  
                                </td>

                                <td>
                                    <a href="admindelete?action=journey&journeyID=${journeyID}">Delete :  ${journeyID}</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>

            <div class="add-location">
                <a style="text-decoration: none; color: white" href="adminadd?action=journey">
                    Add journey
                </a>
            </div>

        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">

            if (document.getElementById('check').value !== null) {
                window.alert('The Journey can not be edit because it was in use');
            }


        </script>
    </body>
</html>


