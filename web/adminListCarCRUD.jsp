<%-- 
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
            .mycheckbox{
                /* Increase the size of the checkbox */
                transform: scale(1.5);

                /* Adjust the margin to align the checkbox with the label */

            }
            #search_button{ 
                font-size: 15px;
                border: none;
                border-radius: 20px;
                background-color:  whitesmoke;
                color: green;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                transition: all 0.3s ease;
                padding: 0 2% 0 2%;

            }

            #search_button:hover{
                box-shadow: 0px 8px 12px rgba(0,128, 0, 0.5);
                transform: translateY(-15%);
                cursor: pointer;
            }

            .form_Sort_Routes-container_small > select {
                font-size: 15px;
                cursor: pointer;

            }
        </style>
    </head>
    <body>
        <%@include file = "admin_header.jsp"%>

        <c:if test="${(requestScope.existError != null)}">
            <input value="${requestScope.existError}" id="check" hidden=""/>
        </c:if>

        <div class="container">

            <form action="adminsearch" class="form_Sort_Routes" method="GET"> 
                <input type="hidden" name="action" value="searchCar"/>
                <div class="form_Sort_Routes-container">

                    <div class="form_Sort_Routes-container_small">
                        <select id="cartype" name="cabin">
                            <option value="">Cabin</option>
                            <option  ${requestScope.cabin == "T" ? "selected" : ""} value="T">Available</option>
                            <option  ${requestScope.cabin == "F" ? "selected" : ""} value="F">Unavailable</option>
                        </select>
                    </div>

                    <div class="form_Sort_Routes-container_small">                        
                        <select id="cartype" name="wifi">
                            <option value="">Wifi</option>
                            <option  ${requestScope.wifi == "T" ? "selected" : ""}  value="T">Available</option>
                            <option  ${requestScope.wifi == "F" ? "selected" : ""}  value="F">Unavailable</option>
                        </select>
                    </div>

                    <div class="form_Sort_Routes-container_small">
                        <select id="cartype" name="cartype">
                            <option value="">Car type</option>
                            <option  ${requestScope.carType == "1F" ? "selected" : ""} value="1F">1F</option>
                            <option  ${requestScope.carType == "2F" ? "selected" : ""} value="2F">2F</option>
                        </select>
                    </div>


                    <input style="font-size: 19px;" type="submit" value="Search  " class="form_Sort_Routes-submit">
                </div>
            </form>







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
                            <th>
                                Edit
                            </th>
                            <th>
                                Change status
                            </th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${requestScope.listcar}" var="c">
                            <tr>
                                <td>${c.carID}</td>  
                                <td>${c.carType}</td>  

                                <c:if test="${c.cabin == 'T'}">
                                    <td style="color: blue;">Available</td>
                                </c:if>
                                <c:if test="${c.cabin == 'F'}">
                                    <td  style="color: red;">Unavailable</td>
                                </c:if>    

                                <c:if test="${c.wifi == 'T'}">
                                    <td style="color: blue;">Available</td>
                                </c:if>
                                <c:if test="${c.wifi == 'F'}">
                                    <td  style="color: red;">Unavailable</td>
                                </c:if>  

                                <td>${c.licensePlate}</td>
                                <c:if test="${c.status == true}">
                                    <td style="color: blue;">Available</td>
                                </c:if>

                                <c:if test="${c.status == false}">
                                    <td style="color: red;">Unavailable</td>
                                </c:if>

                                <!--Edit Car-->
                                <td><a href="adminedit?action=car&carId=${c.carID}" style="color: #008000;">Edit car: ${c.carID}</a></td>



                                <c:if test="${c.status == true}">
                                    <td><a href="admindelete?action=deleteCar&carId=${c.carID}&status=${c.status }">Change to: <span style="color: red;">Unavailable</span></a>  </td>
                                </c:if>

                                <c:if test="${c.status == false}">
                                    <td><a href="admindelete?action=deleteCar&carId=${c.carID}&status=${c.status }">Change to: <span style="color: #008000;">Available</span></a>  </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="add-location">
                <a style="text-decoration: none; color: white" href="adminadd?action=car">
                    Add car
                </a>
            </div>

        </div>

        <script type="text/javascript">
            if (document.getElementById('check').value === 'exist') {
                window.alert('The Location can not be change because it was in use');
            }

            var cabinCheckbox = document.getElementById("cabin");
            var wifiCheckbox = document.getElementById("wifi");
            var cartypeSelect = document.getElementById("cartype");
            var searchButton = document.getElementById("search_button");

            document.getElementById("cabin").value = !cabinCheckbox.checked ? cabinCheckbox.value : "T";
            document.getElementById("wifi").value = !wifiCheckbox.checked ? wifiCheckbox.value : "T";
            document.getElementById("cartype").value = cartypeSelect.value;
            var newHref = "adminsearch?action=searchCar&cabin=" + cabinValue + "&wifi=" + wifiValue + "&cartype=" + cartypeValue;
            searchButton.href = newHref;

            // Add event listeners to update the href
            cabinCheckbox.addEventListener("change", updateHref);
            wifiCheckbox.addEventListener("change", updateHref);
            cartypeSelect.addEventListener("change", updateHref);

            // Function to update the href
            function updateHref() {
                document.getElementById("cabin").value = !cabinCheckbox.checked ? cabinCheckbox.value : "T";
                document.getElementById("wifi").value = !wifiCheckbox.checked ? wifiCheckbox.value : "T";
                document.getElementById("cartype").value = cartypeSelect.value;

                newHref = "adminsearch?action=searchCar&cabin=" + cabinValue + "&wifi=" + wifiValue + "&cartype=" + cartypeValue;
                searchButton.href = newHref;
            }

// Get the current date
            var today = new Date();

// Set the minimum date to today
            var minDate = today.toISOString().split('T')[0];

// Calculate the maximum date (7 days from now)
            var maxDate = new Date(today.getTime() + (14 * 24 * 60 * 60 * 1000)).toISOString().split('T')[0];

// Set the min and max attributes of the date input field
            document.querySelector('.date_in').setAttribute('min', minDate);
            document.querySelector('.date_in').setAttribute('max', maxDate);

        </script>

    </body>
</html>
