    <%-- 
    Document   : updateRoute
    Created on : Jun 16, 2023, 8:37:50 PM
    Author     : USER
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <link rel="stylesheet" href="./css/update.css" />
        <style>
                body { 
                background: url(img/background/background.png) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
                overflow-x: hidden;
            }
            table, th{
                border:1px solid black;
                border-collapse: collapse;
                text-align: center;


            }

            .table-container {
                max-height: 300px;
                overflow-y: scroll;
                border: none;
                border-radius: 20px;
                background-color:  white;
                color: green;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                transition: all 0.3s ease;
            }
            .sticky-top{

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
            .form_container{

                
            }
            .detail p {
                font-size: 30px;
                padding: 13%;

            }
            .detail-data {
                font-size: 35px;

            }
            #search_button{

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
            .journey_form{
                border: none;
                border-radius: 20px;
                background-color:  white;
                color: green;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                transition: all 0.3s ease;

                margin: 5% 0 5% 0;

            }
            .journeyList{
                margin: 5% 0 5% 0;
                border-radius: 20px;
                background-color:  white;
                color: green;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                transition: all 0.3s ease;

            }
            .submit-butn{
                margin: 5% 0 5% 0;
                border: none;
                border-radius: 20px;
                background-color:  whitesmoke;
                color: green;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                transition: all 0.3s ease;

            }
            .submit-butn:hover{


                box-shadow: 0px 8px 12px rgba(0,128, 0, 0.5);
                transform: translateY(-15%);
                cursor: pointer;

            }
            select{
                border: none;
                border-radius: 20px;
                background-color:  whitesmoke;
                color: green;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                transition: all 0.3s ease;
            }
            .detail-data input{
                border: none;
                border-radius: 20px;
                background-color:  whitesmoke;
                color: green;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                transition: all 0.3s ease;
            }
            .date_in:hover{
                box-shadow: 0px 8px 12px rgba(0,128, 0, 0.5);
                transform: translateY(-15%);
                cursor: pointer;
            }
            select:hover{
                box-shadow: 0px 8px 12px rgba(0,128, 0, 0.5);
                transform: translateY(-15%);
                cursor: pointer;
            }
            .form_container_over{
                margin-top:5%;
            }

            .mycheckbox{
                /* Increase the size of the checkbox */
                transform: scale(1.5);
            }
            /* Adjust the margin to align the checkbox with the label */
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




        <div class="form_container_over container">

            <div class="form_container container" style="padding: 0px;">

                <form action="adminedit" method="post" class="journey_form">
                    <input name="action" value="journey" hidden />
                    <input name="journeyID" hidden value="${requestScope.journeyID}" />
                    <div class="update-container">
                        <!-- Update header -->
                        <div class="update_header">
                            <h1>Edit journey</h1>
                        </div>
                        <!--End update header -->

                        <div  class="update-after">
                            <!-- update-after -->

                            <div class="update-detail">
                                <!-- Route -->
  <div style="border-bottom: 0.5px solid green;" class="detail">
                                    <p>Car ID</p>
                                </div>  
                                <div style="border-bottom: 0.5px solid green;" class="detail-data ">

                                    <select name="carID">
                                        <c:if test="${(requestScope.carID)!=null}">
                                            <option  value="${requestScope.carID}">${requestScope.carID}</option>
                                        </c:if>
                                        <c:forEach items="${requestScope.listcar}" var="lc">

                                            <option  value="${lc.carID}">${lc.carID}</option>
                                        </c:forEach>
                                    </select>




                                    Cabin<input id="cabin" class="mycheckbox" name="cabin" type="checkbox" value="F"/>

                                    Wifi<input name="wifi" id="wifi" class="mycheckbox" type="checkbox" value="F"/>


                                    <select id="cartype" name="cartype">
                                        <option value="1F">1F</option>
                                        <option value="2F">2F</option>
                                    </select>



                                    <a id="search_button" href=""><i class="fa fa-search" aria-hidden="true"></i>Search</a>






                                </div>

                                <div style="border-bottom: 0.5px solid green;" class="detail ">
                                    <p>Route ID</p>
                                </div>
                                <div style="border-bottom: 0.5px solid green;" class="detail-data ">

                                    <select required name="routeID">
                                        <c:forEach var="r"  items="${requestScope.rlist}">
                                            <option ${requestScope.routeID == r.routeID ? "selected" : ""}  value="${r.routeID}">
                                                ${r.routeID}|   ${r.getRouteStartLocation().getLocationCity()} -> ${r.getRouteDestLocation().getLocationCity() } 
                                            </option>
                                        </c:forEach>
                                    </select>

                                </div>
                                <!-- Date -->
                                <div style="border-bottom: 0.5px solid green;" class="detail">
                                    <p>Date</p>
                                </div>
                                <div style="border-bottom: 0.5px solid green;" class="detail-data">
                                    <input value="${requestScope.date}"  class="date_in" required="" type="date" name="date" />
                                </div>
                                <!-- Department time -->
                                <div style="border-bottom: 0.5px solid green;" class="detail">
                                    <p>Departure time</p>
                                </div>

                                <div style="border-bottom: 0.5px solid green;" class="detail-data ">
                                    <select name="deptime" required>
                                        <c:forEach var="d" items="${requestScope.dlist}">
                                            <option    ${requestScope.depID == d.depID ? "selected" : ""}  value="${d.depID}" >  ${d.depTime} </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- Car type -->


                                <!-- Car Id -->
                              
                            </div>
                        </div>
                    </div>
                    <!--End update body -->

                    <div class="save-add">
                        <div style="" class="add-location">
                            <a style="text-decoration: none; color: white" href="adminlist?action=journey">
                                Back
                            </a>
                        </div>
                        <input class="submit-butn" type="submit" value="Save" />

                    </div>

                </form>





            </div>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">

            var cabinCheckbox = document.getElementById("cabin");
            var wifiCheckbox = document.getElementById("wifi");
            var cartypeSelect = document.getElementById("cartype");
            var searchButton = document.getElementById("search_button");

            var cabinValue = !cabinCheckbox.checked ? cabinCheckbox.value : "T";
            var wifiValue = !wifiCheckbox.checked ? wifiCheckbox.value : "T";
            var cartypeValue = cartypeSelect.value;
            var newHref = "listcar?cabin=" + cabinValue + "&wifi=" + wifiValue + "&cartype=" + cartypeValue;
            searchButton.href = newHref;

            // Add event listeners to update the href
            cabinCheckbox.addEventListener("change", updateHref);
            wifiCheckbox.addEventListener("change", updateHref);
            cartypeSelect.addEventListener("change", updateHref);

            // Function to update the href
            function updateHref() {
                cabinValue = !cabinCheckbox.checked ? cabinCheckbox.value : "T";
                wifiValue = !wifiCheckbox.checked ? wifiCheckbox.value : "T";
                cartypeValue = cartypeSelect.value;

                newHref = "listcar?cabin=" + cabinValue + "&wifi=" + wifiValue + "&cartype=" + cartypeValue;
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

