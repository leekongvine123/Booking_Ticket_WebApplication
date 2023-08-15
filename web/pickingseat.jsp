<%-- 
    Document   : pickingseat
    Created on : Jun 21, 2023, 2:03:59 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/pickingseat.css">

        <style>

            body { 
                background: url(img/background/background.png) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;

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

                    margin-right:  30%;
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


            <c:if test="${sessionScope.account.roleID ==2}">
                <jsp:include  page="header.jsp"/>
            </c:if>
            <c:if test="${sessionScope.account.roleID ==1}">
                <jsp:include  page="admin_header.jsp"/>
            </c:if>
            <c:set value="${1}" var="count" />


            <div style="margin: 5%;">

                <div id="${j.carID.carID+100}" class="container_seat" style="display: flex; flex-direction: column; justify-content: center;" >

                    <div  style="display: flex;" class="pickseat_container " >
                        <div style="display: flex; justify-content: center; gap:200px;">

                            <div id="1f" >
                                <h1 style="text-align: center">Floor1</h1>

                                    <c:forEach items="${requestScope.list}" var="s">

                                        <c:if test="${ (count==19) }"> 
                                        </div>
                                        <div id="2f" style="">

                                        </c:if>


                                        <c:if test="${ (count==20) }">
                                            <div  class="seat"  > <input name="" accept=""style="display: none;"></div>

                                        <c:set value="${count+1}" var="count" />
                                    </c:if>
                                    <c:if test="${ (count==2) }">
                                        <div  class="seat"  > <input name="" accept=""style="display: none;"></div>

                                        <c:set value="${count+1}" var="count" />
                                    </c:if>

                                    <c:if test="${(s.seatNum)=='X'}">
                                        <div   style="background-color: grey;color:white" class="seat" id="x" ><i class="fa fa-times" aria-hidden="true"></i> <input name="" id="${id}" value="${s.seatID}" type="checkbox" hidden></div>
                                                <c:set var="id" value="${id+1}" />
                                            </c:if>


                                        <c:if test="${(s.seatNum)!='X' && s.seatNum.contains('.')}">
                                            <div style="background-color: green;color: white;" class="seat" id="${id}" >${s.seatNum} <input name="seatarr" id="${id+999}" value="${s.seatID}" type="checkbox" hidden ></div>
                                            <c:set var="id" value="${id+1}" />
                                        </c:if>
                                        <c:if test="${(s.seatNum)!='X' && !s.seatNum.contains('.')}">
                                        <div  class="seat" id="${id}" >${s.seatNum} <input name="seatarr" id="${id+999}" value="${s.seatID}" type="checkbox" hidden ></div>
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
                    <c:if test="${sessionScope.account.roleID ==2}">
                        <div style="margin-left: 50%;"class="add-location">
                        <a style="text-decoration: none; color: white;" href="history">
                            Back
                        </a>
                    </div>
                </c:if>
                <c:if test="${sessionScope.account.roleID ==1}">
                    <div  style="margin-left: 50%;"class="add-location">
                        <a style="text-decoration: none; color: white" href="adminlist?action=order">
                                Back
                            </a>
                        </div>
                    </c:if>

                </div>  












                <jsp:include  page="footer.jsp"/>


                <script type="text/javascript">


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<script type="text/javascr                    ipt">


    function paging(fl                                    oor) {

                            document.getElementById(floor).style.removeProperty('dis                            play');
                    if (floor === '1f') {
                    document.getElementById('2f').style.display = 'none';
        }
    if (floor ===                     '2f') {
                            document.getElementById('1f').style.display = 'none';
    }
        }
    
        function pickingseat(sea                    tID) {

                            if (document.getElementById(seatID).style.backgroundColor !== '                            black')
                    {
                    document.getElementById(seatID).style.backgroundColor = '                            black';
                    document.getElementById(seatID).style.color = 'cornflowe                            rblue';
                    console.log(document.getElementById(seatID).checked = !document.getElementById(seatID).ch                            ecked);
                    document.getElementById(seatID).checked = !document.getElementById(seatID).c                            hecked;
                    console.log(document.getElementById(seatID).name);
                    }                                    else {
                            document.getElementById(seatID).style.backgroundColor = "cornflowe                            rblue";
                    document.getElementById(seatID).style.color = '                            black';
                    console.log(document.getElementById(seatID).checked = !document.getElementById(seatID).ch                            ecked);
                    document.getElementById(seatID).checked = !document.getElementById(seatID).c                            hecked;
    console.log(document.getElementById(seatID).name);
            }
            
        //            window.location = "seat-session?seatID=" + seatID + "&showID=" +                                     showID;
            }
        </scr                ipt>



        </body>
            </html>
