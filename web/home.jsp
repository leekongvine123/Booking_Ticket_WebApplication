<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=0, initial-scale=1.0">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/home.css">
        <title>Document</title>

        <style>
            body { 
                background: url(img/background/background.png) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
            .form_Search_Routes {
                background-image: url(img/home_form/background_form.png)!important;
            }

        </style>
    </head>
    <body>
        <!-- Header -->
        <jsp:include page="header.jsp" />
        <!-- End Header -->
        <c:if test="${requestScope.error_list!=null}"  >
            <input hidden type="text" value="${requestScope.error_list}" id="empty" />
        </c:if>
        <div class="container">

            <!-- Slider -->
            <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="./img/slider/slider1.png" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="./img/slider/slider2(2).png" class="d-block w-100" alt="...">
                    </div>
                    <div class="carousel-item">
                        <img src="./img/slider/slider3(3).png" class="d-block w-100" alt="...">
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
            <!-- <div class="Form">-->
            <form action="findroute" method="get" class="form_Search_Routes">
                <div class="form_Search_Routes-container">
                    <div class="form_Search_Routes-container_small">
                        <h3>Departure Place</h3>
                        <select id="start" name="form_Search_Routes-Start" id="" required onchange="updateDestinationOptions()">
                            <option id="default_start" value="Start">Start</option>
                            <c:forEach items="${requestScope.listlocation}" var="l">
                                <option  ${requestScope.start == l.locationID ? "selected" : ""}  value="${l.locationID}">${l.locationCity}</option>
                            </c:forEach>


                        </select>
                    </div>

                    <div class="form_Search_Routes-container_small">
                        <h3>Destination</h3>
                        <select id="dest" name="form_Search_Routes-Destination" id="" required onchange="updateStartOptions()"  >
                            <option id="default_dest" value="Destination">Destination</option>
                            <c:forEach items="${requestScope.listlocation}" var="l">
                                <option  ${requestScope.dest == l.locationID ? "selected" : ""} value="${l.locationID}">${l.locationCity}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form_Search_Routes-container_small">
                        <h3>Departure Time</h3>
                        <input onclick="checkDuplicate(${2})" type="date" name="form_Search_Routes-Date" required >
                    </div>
                </div>
                <input  type="submit" value="Find Routes" class="form_Search_Routes-submit">

            </form>
            <!--   End Form -->


            <!-- Poupular Routes -->
            <div class="Popular_Routes">
                <div class="Popular_Routes-header">
                    <h1 class="text_center ">POPULAR ROUTES</h1>
                </div>


                <div class="row Popular_Routes_Container">
                    <c:set var="c1" value="${requestScope.popularOne}"/>
                    <a style="display: inline-block; text-decoration: none; "  href="listlocation?action=popularRoute&start=${c1.routeStartLocation.locationID}&dest=${c1.routeDestLocation.locationID}" class="col-md-6 ">
                        <div class="row Popular_Routes_Item">
                            <div class="Popular_Routes_Item-img col-md-3">
                                <img src="data:image/jpg;base64,${c1.routeStartLocation.locationImg}">
                            </div>
                            <div class="Popular_Routes_Item-content col-md-9">
                                <div class="Popular_Routes_Item_content-name">${c1.routeStartLocation.locationCity} <i class="fa fa-arrow-right" aria-hidden="true"></i> ${c1.routeDestLocation.locationCity}</div>
                                <div class="Popular_Routes_Item_content-detail">
                                    <div class="Popular_Routes_Item_content_detail-items">
                                        <img src="./img/Popular_Routes/Location-256.png"> ${c1.routeLength}KM </div>
                                    <div class="Popular_Routes_Item_content_detail-items">
                                        <img src="./img/Popular_Routes/Clock-32.png"> ${c1.routeTime}H</div>
                                    <div class="Popular_Routes_Item_content_detail-items">                                        
                                        <fmt:formatNumber value="${c1.routePrice}" pattern="#,###" var="price" />                                        
                                        <img src="./img/Popular_Routes/Ticket-256.png">${price}VND</div>
                                </div>

                            </div>
                        </div>
                    </a>


                    <c:set var="c2" value="${requestScope.popularTwo}"/>
                    <a style="display: inline-block; text-decoration: none; " href="listlocation?action=popularRoute&start=${c2.routeStartLocation.locationID}&dest=${c2.routeDestLocation.locationID}" class="col-md-6 ">
                        <div class="row Popular_Routes_Item">
                            <div class="Popular_Routes_Item-img col-md-3">
                                <img src="data:image/jpg;base64,${c2.routeStartLocation.locationImg}">
                            </div>
                            <div class="Popular_Routes_Item-content col-md-9">
                                <div class="Popular_Routes_Item_content-name">${c2.routeStartLocation.locationCity} <i class="fa fa-arrow-right" aria-hidden="true"></i> ${c2.routeDestLocation.locationCity}</div>
                                <div class="Popular_Routes_Item_content-detail">
                                    <div class="Popular_Routes_Item_content_detail-items">
                                        <img src="./img/Popular_Routes/Location-256.png"> ${c2.routeLength}KM </div>
                                    <div class="Popular_Routes_Item_content_detail-items">
                                        <img style="" src="./img/Popular_Routes/Clock-32.png"> ${c2.routeTime}H</div>
                                    <div class="Popular_Routes_Item_content_detail-items">                                        
                                        <fmt:formatNumber value="${c2.routePrice}" pattern="#,###" var="price" />                                        
                                        <span>${price}VND</span>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </a>


                </div>
            </div>
            <!--End Poupular Routes -->
        </div>
        <!--Start Footer -->
    </div>





    <jsp:include  page="footer.jsp"/>



    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <!----- js  --->
    <script type="text/javascript">
                            if (${requestScope.error_list!=null}) {

                                window.alert('The journey not found');

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