<%-- 
    Document   : adminListRouteCRUD
    Created on : Jul 17, 2023, 1:53:19 PM
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
        </style>    
    </head>
    <body>
        <%@include file = "admin_header.jsp"%>
        <c:if test="${(requestScope.existError != null)}">
            <input type="text" value="${requestScope.existError}" id="check" />
        </c:if>


        <c:if test="${(requestScope.errorValue1 != null)}">
            <input type="hidden" value="${requestScope.errorValue1}" id="checkStart" />
            <input type="hidden" value="${requestScope.startError}" id="error1" />

        </c:if>

        <c:if test="${(requestScope.errorValue2 != null)}">
            <input type="hidden" value="${requestScope.errorValue2}" id="checkDest" />
            <input type="hidden" value="${requestScope.destError}" id="error2" />
        </c:if>
        <form action="adminsearch" class="form_Sort_Routes" method="GET">
            <input type="hidden" name="action" value="searchRoute"/>
            <div class="form_Sort_Routes-container">
                <div class="form_Sort_Routes-container_small">

                    <select id="start" name="form_Search_Routes-Start" id="" onchange="updateDestinationOptions()">
                        <option id="default_start" value="0">Start</option>
                        <c:forEach items="${requestScope.listlocation}" var="l">
                            <option  ${requestScope.startID == l.locationID ? "selected" : ""} value="${l.locationID}">${l.locationCity}</option>
                        </c:forEach>
                    </select>

                </div>
                <div class="form_Sort_Routes-container_small">
                    <select id="dest" name="form_Search_Routes-Destination" id=""  onchange="updateStartOptions()"  >
                        <option id="default_dest" value="0">Destination</option>
                        <c:forEach items="${requestScope.listlocation}" var="l">
                            <option ${requestScope.destID == l.locationID ? "selected" : ""} value="${l.locationID}">${l.locationCity}</option>
                        </c:forEach>
                    </select>

                </div>
                <input type="submit" value="Search  " class="form_Sort_Routes-submit">
            </div>
        </form>
        <div class="container">

            <div style="text-align: center"><h1>Route management</h1></div>
            <div style="text-align: center" class="table-container">
                <table style="background-color: white;" class="table table-striped">
                    <thead class="sticky-top">
                        <tr>
                            <th>
                                Route Id
                            </th>

                            <th>
                                Route Start
                            </th>
                            <th>
                                Route Destination
                            </th>
                            <th>
                                Route Time
                            </th>  
                            <th>
                                Route Price
                            </th>  
                            <th>
                                Route Lenght
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
                        <c:forEach items="${requestScope.listroute}" var="c">
                            <tr>
                                <td>${c.routeID}</td>
                                <c:if test="${(c.routeStartLocation.status == true) && (c.routeDestLocation.status == false) }">
                                    <td style="color: #088000;">${c.routeStartLocation.locationCity}</td>  
                                    <td style="color: red;">${c.routeDestLocation.locationCity}</td>
                                </c:if>

                                <c:if test="${(c.routeStartLocation.status == false) && (c.routeDestLocation.status == true)}">
                                    <td style="color: red;">${c.routeStartLocation.locationCity}</td>  
                                    <td style="color: #088000;">${c.routeDestLocation.locationCity}</td>
                                </c:if>

                                <c:if test="${ (c.routeDestLocation.status == true) && (c.routeStartLocation.status == true)}">
                                    <td style="color: #088000;"">${c.routeStartLocation.locationCity}</td>  
                                    <td style="color: #088000;">${c.routeDestLocation.locationCity}</td>
                                </c:if>
                                <c:if test="${ (c.routeDestLocation.status == false) && (c.routeStartLocation.status == false)}">
                                    <td style="color: red;"">${c.routeStartLocation.locationCity}</td>  
                                    <td style="color: red;">${c.routeDestLocation.locationCity}</td>
                                </c:if>

                                <td>${c.routeTime}</td>
                                <td>${c.routePrice}</td>
                                <td>${c.routeLength}KM</td>



                                <!--Route Status-->
                                <c:if test="${(c.routeStartLocation.status == false) || (c.routeDestLocation.status == false)}">
                                    <td style="color: red;">Unavailable</td>
                                </c:if>

                                <c:if test="${(c.routeStartLocation.status == true) && (c.routeDestLocation.status == true)}">
                                    <c:if test="${(c.status == true)}">
                                        <td style="color: #088000;">Available</td>

                                    </c:if>
                                </c:if>
                                <c:if test="${(c.routeStartLocation.status == true) && (c.routeDestLocation.status == true)}">
                                    <c:if test="${(c.status == false)}">
                                        <td style="color: red;">Unavailable</td>

                                    </c:if>
                                </c:if>
                                <!-- End Route Status-->



                                <td><a href="adminedit?action=editRoute&routeId=${c.routeID}">Edit location :${c.routeID}</a></td>





                                <!--Route delete-->
                                <c:if test="${c.status == true}">
                                    <td><a href="admindelete?action=deleteRoute&routeId=${c.routeID}&status=${c.status }">Change to: <span style="color: red;">Unavailable</span></a>  </td>
                                </c:if>

                                <c:if test="${c.status == false}">
                                    <td><a href="admindelete?action=deleteRoute&routeId=${c.routeID}&status=${c.status }">Change to: <span style="color:#088000;">Available</span></a></td>
                                </c:if>
                                <!--End Route delete-->

                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="add-location">
                <a style="text-decoration: none; color: white" href="adminadd?action=route">
                    Add Route
                </a>
            </div>
        </div>
        <!--    </div>
        
        </div>-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script type="text/javascript">
                        var checkElement = document.getElementById('check');
                        if (checkElement && checkElement.value === 'exist') {
                            window.alert('The Location cannot be changed because it is in use');
                        }

                        var checkStartElement = document.getElementById('checkStart');
                        if (checkStartElement && checkStartElement.value === 'alert1') {
                            var startErrorValue = document.getElementById('error1').value;
                            window.alert('Cannot edit: ' + startErrorValue);
                        }

                        if (document.getElementById('checkDest').value === 'alert2') {
                            window.alert('' + document.getElementById('error2').value);
                        }



                        function updateDestinationOptions() {
                            var startSelect = document.getElementById('start');
                            var destSelect = document.getElementById('dest');
                            var selectedValue = startSelect.value;

                            // Enable all options in the destination select
                            for (var i = 0; i < destSelect.options.length; i++) {
                                destSelect.options[i].style.display = 'inline-block';
                            }

                            // Disable the selected option in the start select from the destination select
                            if (selectedValue !== '') {
                                var selectedOption = destSelect.querySelector('option[value="' + selectedValue + '"]');
                                if (selectedOption) {
                                    selectedOption.style.display = 'none';
                                }
                            }
                        }

                        function updateStartOptions() {
                            var startSelect = document.getElementById('start');
                            var destSelect = document.getElementById('dest');
                            var selectedValue = destSelect.value;

                            // Enable all options in the start select
                            for (var i = 0; i < startSelect.options.length; i++) {
                                startSelect.options[i].style.display = 'inline-block';
                            }

                            // Disable the selected option in the destination select from the start select
                            if (selectedValue !== '') {
                                var selectedOption = startSelect.querySelector('option[value="' + selectedValue + '"]');
                                if (selectedOption) {
                                    selectedOption.style.display = 'none';
                                }
                            }
                        }

                        function checkDuplicate(f2) {

                            var start = document.getElementById('start').value;
                            var dest = document.getElementById('dest').value;



                            console.log(start);
                            console.log(dest);

                            if (start === dest) {
                                //window.location = "listlocation";
                                document.getElementById('start').value = document.getElementById('default_start').value;
                                document.getElementById('dest').value = document.getElementById('default_dest').value;
                                window.alert(" Departure place and Destionation can not be same ");


                            }
                            if (((start === "Start" && dest === "Destination") || (start === "Start") || (dest === "Destination")) && f2 === 2) {
                                window.alert(" Please choose Departure place and Destionation before you choose Date ");
                            }


                            //          window.location ="findroute?form_Search_Routes-Start="+start+"&form_Search_Routes-Destination="+dest+"&form_Search_Routes-Date="+day;

                        }
                        // Get the current date
                        // Get the current date
                        var today = new Date();

                        // Set the minimum date to today
                        var minDate = today.toISOString().split('T')[0];

                        // Calculate the maximum date (7 days from now)
                        var maxDate = new Date(today.getTime() + (14 * 24 * 60 * 60 * 1000)).toISOString().split('T')[0];
                        // Set the min and max attributes of the date input field
                        document.querySelector('input[name="form_Search_Routes-Date"]').setAttribute('min', minDate);
                        document.querySelector('input[name="form_Search_Routes-Date"]').setAttribute('max', maxDate);

                        // Call the checkDuplicate function



        </script>
    </body>
</html>