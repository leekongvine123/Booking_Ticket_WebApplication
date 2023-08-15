<%-- 
    Document   : route
    Created on : Jun 18, 2023, 9:55:18 PM
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
        <link rel="stylesheet" href="./css/route.css" />
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

        <c:set var="listj" value="${requestScope.listj}"/>
        <c:if test="${(not empty listj)}">
            <c:if test="${requestScope.seatPicked!=null}">
                <input hidden id="seatPicked" type="text"   value="${requestScope.seatPicked}" />
            </c:if>




            <div class="route-date">

                <h1>ROUTE: ${listj.get(0).routeID.routeStartLocation.locationCity} <i class="fa fa-arrow-right" aria-hidden="true"></i> ${listj.get(0).routeID.routeDestLocation.locationCity}  </h1>
                <div class="date">
                    <fmt:parseDate value="${requestScope.depday_raw}" pattern="yyyy-MM-dd" var="parsedDate" />
                    <fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy" var="depday" />
                    <span>DATE: ${depday} </span>
                    <span id="date"></span>
                </div>
            </div>    
            <form action="findroute" class="form_Sort_Routes" method="POST">
                <input type="text" name="routeID_sort" value="${listj.get(0).routeID.routeID}" hidden/>
                <input type="text" name="depday_sort" value="${listj.get(0).journeyDepDay}" hidden/>
                <input type="text" name="depday_raw" value="${requestScope.depday_raw}" hidden/>

                <div class="form_Sort_Routes-container">
                    <div class="form_Sort_Routes-container_small">
                        <!--<h3>Price</h3>-->
                        <select name="form_Search_Routes-Price" id=""  >
                            <option value="">Price</option>
                            <option  ${requestScope.price_raw == "desc" ? "selected" : ""}    value="desc">High-Low</option>
                            <option  ${requestScope.price_raw == "asc" ? "selected" : ""} value="asc">Low-High</option>

                        </select>
                    </div>

                    <div class="form_Sort_Routes-container_small">
                        <!--h3>Time</h3-->
                        <!--                        <input type="time" name="form_Search_Routes-Time">-->
                        <select name="form_Search_Routes-Time" id=""  >

                            <option value="">Time</option>
                            <option  ${requestScope.time_raw == "0:6" ? "selected" : ""} value="0:6">0h - 6h</option>
                            <option   ${requestScope.time_raw == "6:12" ? "selected" : ""}value="6:12">6h - 12h</option>
                            <option  ${requestScope.time_raw == "12:18" ? "selected" : ""}value="12:18">12h - 18h</option>
                            <option ${requestScope.time_raw == "18:24" ? "selected" : ""} value="18:24">18h - 24h</option>

                        </select>

                        </select>
                    </div>

                    <div class="form_Sort_Routes-container_small">
                        <!--h3>Type of Coach</h3-->
                        <!--                        <select id="coach-type" name="coach-type">
                                                    <option value="Regular">Regular</option>
                                                    <option value="Double-deckers">Double-deckers</option>
                                                </select>-->
                        <select name="coach-type" id=""  >
                            <option value="">Car's Type</option>
                            <c:forEach items="${requestScope.clist}" var="c">
                                <option  ${requestScope.carType == c.carType ? "selected" : ""} value="${c.carType}">${c.carType}</option>

                            </c:forEach>


                        </select>

                    </div>
                    <input type="submit" value="Search  " class="form_Sort_Routes-submit">
                </div>
            </form>


            <c:set var="id" value="${1000}" />
            <c:forEach items="${requestScope.listj}" var="j" >


                <input type="text" value="" hidden  />
                <form action="order" method="GET">

                    <input  name="journeyID" type="text" value="${j.journeyID}" hidden/>  

                    <div class="route-inf">
                        <div  class="time">
                            <span id="">${j.depID.depTime}:00</span>

                        </div>

                        <div class="price-type-seat">
                            <div class ="route-inf-items text-container">
                                <fmt:formatNumber value="${j.price}" pattern="#,###" var="price" />
                                <span id="">${price}VND</span>
                            </div>
                            <div class ="text-container route-inf-items">
                                <span id="">${j.carID.carType}oor</span>
                            </div>    
                            <div class ="text-container">
                                <span id="">${j.seatLeft} available</span>
                            </div>    
                        </div>
                        <!--            <div class ="take-time">
                                        <span id="">run time</span>
                                    </div>-->
                        <div class="start-des">
                            <div class="text-container-small route-inf-items">
                                <span id="">${j.routeID.routeStartLocation.locationCity} <i class="fa fa-arrow-right" aria-hidden="true"></i>  ${j.routeID.routeDestLocation.locationCity} :${j.routeID.routeTime} Hours  </span>
                            </div>
                            <!--                <div class="dashed">
                                                
                                            </div>
                                            <div class="text-container-small">
                                                <span id=""></span>
                                            </div> -->
                            <div   id="${j.carID.carID+50}"    class="booking-button notselected" onclick="showseat('${j.carID.carID+100}', '${j.carID.carID+50}',${j.carID.carID+5392},${j.carID.carID+2935},${j.carID.carID+8112})"  >
                                Choose
                            </div>
                        </div>

                        <c:set value="${1}" var="count" />

                        <div id="${j.carID.carID+100}" class="container_seat" style="display: none; flex-direction: column; justify-content: center;" >

                            <div  style="display: flex;" class="pickseat_container " >
                                <div style="display: flex; justify-content: center; gap:200px;">

                                    <div id="1f" >
                                        <h1 style="text-align: center">Floor1</h1>

                                        <c:forEach items="${j.listSeat}" var="s">

                                            <c:if test="${ (count==19) }"> 
                                            </div>
                                            <div style="text-align: center" id="2f" style="">
                                                <h1>Floor2</h1>


                                            </c:if>


                                            <c:if test="${ (count==20) }">
                                                <div  class="seat" style="background-color: transparent!important;"  >  <input name="" accept=""style="display: none;"></div>

                                                <c:set value="${count+1}" var="count" />
                                            </c:if>
                                            <c:if test="${ (count==2) }">
                                                <div  class="seat" style="background-color: transparent!important;" > <input name="" accept=""style="display: none;"></div>

                                                <c:set value="${count+1}" var="count" />
                                            </c:if>

                                            <c:if test="${(s.seatNum)=='X'}">
                                                <div   style="background-color: grey;color:white" class="seat" id="x" ><i class="fa fa-times" aria-hidden="true"></i> <input name="" id="${id}" value="${s.seatID}" type="checkbox" hidden></div>
                                                    <c:set var="id" value="${id+1}" />
                                                </c:if>

                                            <c:if test="${(s.seatNum)!='X'}">
                                                <div  class="seat" id="${id}" onclick="pickingseat(${id},${id+999}, '${s.seatNum}',${j.carID.carID+5392},${j.carID.carID+2935},${j.carID.carID+8112},${j.price})">${s.seatNum} <input name="seatarr" id="${id+999}" value="${s.seatID}" type="checkbox" hidden ></div>
                                                    <c:set var="id" value="${id+1}" />
                                                </c:if>

                                            <c:if test="${ (count%3==0) && (count>0) }">
                                                <br>
                                            </c:if>


                                            <c:set value="${count+1}" var="count" />
                                        </c:forEach>
                                    </div>




                                </div>
                                <div class="guide_pickseat">
                                    <div class="seat_status" style="background-color: green"></div>Selected
                                    <div class="seat_status" style="background-color: white"></div>Avaiable
                                    <div class="seat_status" style="background-color: gray"></div>Unvaiable

                                </div>

                            </div>

                            <div class="footer_rout_inf" style="display: flex; margin-bottom: 3% 0; justify-content: center;">
                                <div class="calculate_ticket"  style="display: column; margin-top: -11%; margin-left: 1%; font-size: 25px">
                                    <div style="display: flex">  <input style="background-color: transparent; border-style: none;" class="pickdetail" id="${j.carID.carID+5392}"  disabled type="submit" value=""  /> picked: <input  style="background-color: transparent; border-style: none;" class="pickdetail" id="${j.carID.carID+2935}"  disabled type="submit" value=""  />  </div>
                                    <div>Total:<input disabled style="background-color: transparent; border-style: none;" id="${j.carID.carID+8112}" class="pickdetail" type="submit" value="" />VND</div>
                                </div>

                                <input style="height: 10%;" class="next_button" type="submit" value="Next"  />  
                                <i onclick="showseat('${j.carID.carID+100}', '${j.carID.carID+50}',${j.carID.carID+5392},${j.carID.carID+2935},${j.carID.carID+8112})" class="fa fa-arrow-up" aria-hidden="true">close</i>


                            </div>
                        </div>
                        <div class="option">
                            <c:if test="${(j.carID.wifi)=='T'}">
                                <i class="fa fa-wifi" aria-hidden="true"></i> 
                            </c:if>
                            <c:if test="${(j.carID.cabin)=='T'}">
                                <i class="fa fa-train" aria-hidden="true"></i>
                            </c:if>
                        </div>
                    </div>




                </form>
            </c:forEach>
        </c:if>
        <c:if test="${(empty listj)}">
            <h1>NotThing Matches</h1>
        </c:if>

        <!--footer-->
        <jsp:include  page="footer.jsp"/>
        <!--End footer-->
        <!--js-->
        <script type="text/javascript">
            var countseat = 1;
            var seatlist = '';
     
if(document.getElementById('seatPicked')!==null){
       var seatPicked =document.getElementById('seatPicked').value;
        if(seatPicked !==null){
            window.alert('Your choosen seat which was picked by another person first'+seatPicked);
        }
    }

            function paging(floor) {

                document.getElementById(floor).style.removeProperty('display');
                if (floor === '1f') {
                    document.getElementById('2f').style.display = 'none';
                }
                if (floor === '2f') {
                    document.getElementById('1f').style.display = 'none';
                }
            }


            function showseat(carID, choose, quantityin, seat_namein, totalin) {
                countseat = 0;
                document.getElementById(quantityin).value = 0;
                seatlist = '';
                document.getElementById(seat_namein).value = "";
                document.getElementById(totalin).value = 0;

                var containerothers = document.getElementsByClassName('container_seat');
                var seatDivs = document.querySelectorAll('.seat');
                var seatInputs = document.getElementsByName('seatarr');

                var container = document.getElementById(carID);
                var booking_buttons = document.getElementsByClassName('booking-button');


                if (document.getElementById(choose).style.backgroundColor === 'white' || document.getElementById(choose).style.backgroundColor === '')
                {
                    document.getElementById(choose).style.backgroundColor = 'green';
                    document.getElementById(choose).style.color = "white";
                } else {
                    document.getElementById(choose).style.backgroundColor = "white";
                    document.getElementById(choose).style.color = "green";
                }




                if (container.style.display === 'none') {
                    for (var i = 0; i < containerothers.length; i++) {
                        var containerother = containerothers[i];
                        var booking_button = booking_buttons[i];
                        if (containerother.style.display === 'flex') {
                            containerother.style.display = 'none';
                            booking_button.style.backgroundColor = 'white';
                            booking_button.style.color = 'green';

                        }
                    }



                    for (var i = 0; i < seatInputs.length; i++) {
                        seatInputs[i].checked = false;
                    }
                    for (var i = 0; i < seatDivs.length; i++) {
                        if (seatDivs[i].id !== 'x') {
                            seatDivs[i].style.backgroundColor = 'white';
                            seatDivs[i].style.color = 'black';
                        }
                    }
                    container.style.display = 'flex';
                    console.log(container.style.display);
                } else {


                    for (var i = 0; i < seatInputs.length; i++) {
                        seatInputs[i].checked = false;
                    }

                    for (var i = 0; i < seatDivs.length; i++) {
                        if (!seatDivs[i].id === 'x') {
                            seatDivs[i].style.backgroundColor = 'white';
                            seatDivs[i].style.color = 'black';
                        }
                    }
                    container.style.display = 'none';
                    console.log(container.style.display);
                }


            }


            function pickingseat(seatID, checkboxID, seatNum, quantityin, seat_namein, totalin, price) {


                var quantity = document.getElementById(quantityin);
                var seat_name = document.getElementById(seat_namein);
                var total = document.getElementById(totalin);
                var totalcal = 0;


//             

                if (document.getElementById(seatID).style.backgroundColor !== 'green')
                {
                    if (countseat === 4) {

                        alert("you can pick more than 4");

                        back();

                    }
                    document.getElementById(seatID).style.backgroundColor = 'green';
                    document.getElementById(seatID).style.color = 'white';
                    document.getElementById(checkboxID).checked = true;
                    console.log(document.getElementById(seatID).checked === true);

                    console.log(document.getElementById(seatID).id);
                    countseat++;
                    quantity.value = countseat;
                    if (seatlist === '') {
                        seatlist = seatNum;
                    } else {
                        seatlist = seatlist + ',' + seatNum;
                    }
                    seat_name.value = seatlist;
                    totalcal = (countseat * price * 10) / 10000;
                    total.value = totalcal.toLocaleString('en', {minimumFractionDigits: 3, maximumFractionDigits: 3});

                } else {
                    document.getElementById(seatID).style.backgroundColor = "white";
                    document.getElementById(seatID).style.color = 'green';
                    document.getElementById(checkboxID).checked = false;
                    console.log(document.getElementById(seatID).checked === true);
                    console.log(document.getElementById(seatID).value);

                    var namealias = '';
                    var seats = seatlist.split(",");
                    for (var i = 0; i < countseat; i++) {

                        if (seats[i] !== seatNum) {
                            if (i === 0) {
                                namealias = seats[i];
                            } else {
                                namealias = namealias + ',' + seats[i];
                            }

                        }


                    }
                    if (namealias.toLowerCase().startsWith(",") === true) {
                        seatlist = namealias.replace(/^,/, "");
                    } else {
                        seatlist = namealias;
                    }

                    seat_name.value = seatlist;






                    countseat--;

                    totalcal = (countseat * price * 10) / 10000;
                    total.value = totalcal.toLocaleString('en', {minimumFractionDigits: 3, maximumFractionDigits: 3});
                    if (countseat === 0) {
                        total.value = 0;
                    }
                    quantity.value = countseat;
                }
                console.log(countseat);
                //            window.location = "seat-session?seatID=" + seatID + "&showID=" + showID;
            }



        </script>
    </body>

</html>
