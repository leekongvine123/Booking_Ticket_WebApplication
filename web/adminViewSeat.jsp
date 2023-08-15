<%-- 
    Document   : adminViewSeat
    Created on : Jul 16, 2023, 9:43:27 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>


            .route-date{
                margin: 2% 20% 0% 20%;   
                padding: 1vw;
                font-size:3vw;
            }
            .date{
                font-size: 32px;
            }
            .form_Sort_Routes{

                min-width: 30px;
                margin: 0% 20% 0% 20%;
                font-size: 200%;  
            }


            .form_Sort_Routes-container{
                border-radius: 50px    ;
                display: flex;
                background-color: #E7F7FF;
                border: 1px solid black;
                flex-wrap: wrap;
                min-width: 30px;
                margin: 0% 0% 6% 0%;  
                align-items: center;  
                justify-content: space-around;
            }
            .form_Sort_Routes-container_small{
                display: flex;
                justify-content: space-between;
                margin: 0% 0% 0% 0%;
                padding: 1vw 2.5vw 1vw 2.5vw; /* using vw (viewport width) units for padding */
                align-items: center; 
                align-content: center;  
                font-size: 1.2vw; /* using vw units for font size */
                text-align: center; 
            }
            .form_Sort_Routes-container_small select{

                display: inline-block;
                padding: 5% 10%;    
                border: none;
                border-radius: 50px;
                background-color: white;
                color: green;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                transition: all 0.3s ease;


            }
            .form_Sort_Routes-submit {

                font-size: 1.1vw; 
                display: inline-block;

                border: none;
                border-radius: 50px;
                background-color: white;
                color: green;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                transition: all 0.3s ease;
                height: 5%;

            }
            .form_Sort_Routes-container_small select:hover {
                box-shadow: 0px 8px 12px rgba(0, 128, 0, 0.5);
                transform: translateY(-2px);    
            }
            .form_Sort_Routes-submit:hover{
                box-shadow: 0px 8px 12px rgba(0, 128, 0, 0.5);
                transform: translateY(-2px);
            }

            .route-inf{

                color: rgb(41 108 22);;
                display: flex;
                position: relative;
                background:#E7F7FF;
                border: 0.000000000000000000000000000000000000001px solid #333;
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
                border-radius: 20px;
                min-width: 30px;
                margin: 2% 20% 2% 20%;
                font-size: 200%;  
                flex-direction: column;


            }

            .time{
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                display: flex;

                background-color: white;
                justify-content: center;
                align-items: center;
                align-content: center;
                border-radius: 40px;
                margin: 1% 20% 3% 20%;
                padding: 1vw 3vw 1vw 3vw;
                font-size: 2vw;
            }
            .price-type-seat {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin: 0% 0% 3% 0%;
            }


            .take-time{
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                display: flex;
                background-color: #C5C5C5;
                justify-content: center;
                align-items: center;
                align-content: center;
                border: 2px solid black;
                margin: 0% 61% 0% 23%;
                border-radius: 40px;
                padding: 0.8vw 2vw 0.8vw 2vw;
                font-size: 0.75vw;
            }
            .start-des{

                display: flex;
                justify-content: space-between;
                margin: 0% 35% 0% 2%;
                align-items: center;
            }

            .text-container{
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                justify-content: space-between;
                background-color: white;
                border-radius: 40px;
                margin: 0% 5% 5% 5%;   
                padding: 1vw 3vw 1vw 3vw;
                font-size: 1.25vw;
                align-items: center; 
                align-content: center;
                text-align: center;
            }
            .text-container-small{
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                display: flex;
                justify-content: space-between;
                background-color: white;
                border-radius: 40px;
                margin: 0% 10% 6% 10%;
                padding: 1vw 2.5vw 1vw 2.5vw; /* using vw (viewport width) units for padding */
                align-items: center; 
                align-content: center;  
                font-size: 1vw; /* using vw units for font size */
                text-align: center;    
            }
            .dashed{
                margin: 0% -10% 6% -10%;
                padding: -1vw -2vw -1vw -2vw;
                font-size: 1vw;
            }
            .booking-button:hover{
                cursor: pointer;
            }


            button {

            }

            .booking-button:hover {
                box-shadow: 0px 8px 12px rgba(0, 128, 0, 0.5);
                transform: translateY(-2px);
            } 


            .booking-button{


                display: inline-block;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                background-color: white;
                color: green;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                transition: all 0.3s ease;


                margin: 0% -45% 6% 30%;
                border-radius: 20px;
                padding: 0.5%;
                height: 3vw;
                font-size: 2vw;
            }
            .guide_pickseat{
                font-size: 15px;
                display:flex; 
                align-items: center;
                justify-content: center;
                gap:30px;
                margin-bottom: 20px;



            }
            .seat_status{






                font-size: 10px;
                width: 2%;
                border-radius: 30%;    
                padding: 20px;



            }



            .next_button:hover {
                box-shadow: 0px 8px 12px rgba(0, 128, 0, 0.5);
                transform: translateY(-2px);
            } 

            .next_button{



                display: inline-block;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                background-color: white;
                color: green;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                transition: all 0.3s ease;




                width: 40%;
                margin: 0 auto;
                margin-top: -10%;
                border-radius: 50px;
                margin-right: 3%;
            }
            .option{
                opacity: 0.8;
                display: flex;
                width: 3%;
                gap:80%;
                margin-top: 1%;
                margin-right: 8%;
                position: absolute;
                top:0;
                right: 0;
            }


            .seat{





                padding: 10px 20px;
                border: none;

                background-color: white;
                color: green;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                transition: all 0.3s ease;


                border-radius: 25%;
                font-size: 15px;
                font-weight: bold;
                display: inline-block;
                padding: 20px;
                margin: 10px;
                min-width:  70px;
                text-align: center;


            }

            .seat:hover{
                box-shadow: 0px 8px 12px rgba(0,128, 0, 0.5);
                transform: translateY(-15%);
                cursor: pointer;
            }

            .notselected{
                -webkit-user-select: none; /* Safari */
                -moz-user-select: none; /* Firefox */
                -ms-user-select: none; /* IE 10+ */
                user-select: none; /* Standard syntax */
            }
            .container_seat{
                gap:100px;
            }

            .pickseat_container{
                display: flex;
                flex-direction: column;
                background-color:rgba(135, 206, 235,0.3);
            }

            .footer_rout_inf{
                margin: 2% 0;   


            }
            .calculate_ticket{
                margin-top: -10%;
            }
            .pickdetail{
                color:black;
            }
            #total{
                background-color: transparent;
                border-style: none;
            }

        </style>
    </head>
    <body>
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
    </body>
</html>
