<%-- 
    Document   : ticketinf
    Created on : Jun 15, 2023, 9:55:18 PM
    Author     : USER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>
        <link rel="stylesheet" href="./css/ticket.css" />
        <style>
            body { 
                background: url(./img/background/background.png) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
        </style>

    </head>

    <body>        
        <!-- Header -->
        <jsp:include page="header.jsp" />
        <!-- End Header -->
        <c:set value="${requestScope.order}"  var="o" />
        <c:set value="${requestScope.totalPrice}"  var="t"/>


        <!-- User(Booker) Infomation -->
        <div class="ticket-inf">
            <h1>Order's Information</h1>


        </div>
        <div>


        </div>
        <form style="margin-bottom: 5%;"action="order" method="post">
            <input hidden value="${o.orderID}" name="orderID"  />
            <div class="cus">
                <h1>Customer's Information</h1>
            </div>
            <div class="cus-inf">
                <div class="name">
                    <span>Name:</span>
                    <span id="name">${o.userID.userName}</span>
                </div>
                <div class="phone-number">
                    <span>Phone number:</span>
                    <span id="phone-number">${o.userID.userPhoneNumber}</span>
                </div>
                <div class="email">
                    <span>Email:</span>
                    <span id="email">${o.userID.userMail}</span>
                </div>
            </div>
            <!-- End User(Booker)'s Infomation -->

            <!-- Trip's Infomation -->
        </div>
        <div class="trip">
            <h1>Trip's Information</h1>
        </div>
        <div class="trip-inf">
            <div class="route-total">
                <div class="route">
                    <span>Route: </span>
                    <span id="route">&nbsp${o.journeyID.routeID.routeStartLocation.locationCity} <i class="fa fa-arrow-right" aria-hidden="true"></i>  ${o.journeyID.routeID.routeDestLocation.locationCity}</span>
                </div>
                <div class="total">
                    <span>Total:</span>
                    <fmt:formatNumber value="${t}" pattern="#,###" var="total" />
                    <span id="total">${total}</span>
                </div>
            </div>          
            <div class="departure-time">
                <span>Departure Time: </span>
                <span id="departure-time">${o.journeyID.depID.depTime}:00</span>
            </div>
            <div class="seat-number">
                <span>Seat Number:  </span>

                <span id="seat-number">
                    <c:set value="${0}" var="count"   />
                    <c:forEach items="${requestScope.listseat}" var="ls">
                        <c:if test="${(count!=0)}">
                            ,&nbsp${ls.seatNum}
                        </c:if>
                        <c:if test="${(count==0)}">
                            &nbsp${ls.seatNum}
                        </c:if>

                        <c:set value="${count+1}" var="count"   />
                    </c:forEach>
                </span>
            </div>
            <div class="seat-number">
                <span>Station: ${o.journeyID.routeID.routeStartLocation.locationStation}  </span> 
            </div>
            <div class="license-cart-pay">
                <div class="license-plate">
                    <span>License plate: </span>
                    <span id="license-plate">&nbsp${o.journeyID.carID.licensePlate}</span>
                </div>


                <!-- Add to cart - pay button-->
                <div>
                    <h1 id="counthold-text" style="display: inline-block;" >Hold Time:</h1>  <h1 id="counthold" style="color:red;display:  inline-block;">30</h1>
                </div>
                <div id="countdown-container" class="cart-pay-buttons">

                    <button id="pay-button">Pay</button>
                </div>
                <!-- End Add to cart - pay button-->
            </div>
        </div>
        <input hidden id="start_b"type="text" value="${o.journeyID.routeID.routeStartLocation.locationID}"/>
        <input  hidden id="dest_b"type="text" value="${o.journeyID.routeID.routeDestLocation.locationID}"/>
        <input hidden id="day_b"type="text" value="${o.journeyID.journeyDepDay}"/>

    </form>
    <!-- End Trip's Infomation -->
    <!--footer-->
    <jsp:include  page="footer.jsp"/>
    <!--End footer-->
    <script type="text/javascript">


window.alert('Your order will invalid in 30s, please pay!');

        window.addEventListener('DOMContentLoaded', function () {
            var routeStartLocation = document.getElementById('start_b').value;
            var routeDestLocation = document.getElementById('dest_b').value;
            var journeyDepday = document.getElementById('day_b').value;

            var redirectURL = "findroute?form_Search_Routes-Start=" + routeStartLocation + "&form_Search_Routes-Destination=" + routeDestLocation + "&form_Search_Routes-Date=" + journeyDepday;

            console.log(routeDestLocation);
            console.log(journeyDepday);
            console.log(routeStartLocation);
            const countdownContainer = document.getElementById('countdown-container');
            let currentSecond = 30;

            const countdownInterval = setInterval(() => {
                currentSecond--;

                if (currentSecond < 0) {
                    clearInterval(countdownInterval);
                    countdownContainer.innerHTML = '<a id="countdown-link" href="' + redirectURL + '">Go Back</a>';
                }
            }, 1000);
        });




        window.addEventListener('DOMContentLoaded', function () {
            const countdownElement = document.getElementById('counthold');
            let currentSecond = parseInt(countdownElement.textContent);

            const countdownInterval = setInterval(() => {
                countdownElement.textContent = currentSecond;
                currentSecond--;

                if (currentSecond < 0) {
                    clearInterval(countdownInterval);
                    document.getElementById('counthold-text').style.display = 'none';
                    countdownElement.textContent = "Your order now invalid";
                }
            }, 1000);
        });


    </script>
</body>

</html>
