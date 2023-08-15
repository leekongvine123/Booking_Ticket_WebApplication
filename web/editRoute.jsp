<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                background: url(./img/background/background.png) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }

            .location-container{
                width: 100%;
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
                margin: 10% 0;

            }
            table {
                width: 100%;
                border-collapse: collapse;
                caption-side: top; /* Ensure caption appears at the top */
                text-align: center;
                align-items: center;    

            }

            th, td {
                padding: 8px;

                border-bottom: 1px solid #333;
            }
            td > input{
                width: 100%;
            }
            th {
                background-color: #f2f2f2;
            }

            img {
                max-width: 100px;
                max-height: 100px;
            }
            .location-edit-container{
                width: 50%;
                display: flex;  
                justify-content: center;
                text-align: center;
                align-items: center;

                flex-direction: column;
                box-shadow: 0 0 10px 5px #888888;

                background-color: white;
                color: green;
                text-align: center;
                text-decoration: none;
                font-weight: bold;
                box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
                transition: all 0.3s ease;


            }
            .location-before-container{
                width: 50%;
                display: flex;  
                justify-content: center;
                text-align: center;
                align-items: center;
                margin-top: 10%;
                flex-direction: column;
                box-shadow: 0 0 10px 5px #888888;


            }
            .location-edit-container > td > input{
                width: 100%;

            }
            .locSubmit:hover{
                opacity: 0.7;
            }
            .locSubmit{
                background-color: green;
                color: white;
                border: none;
                padding: 10px;

                width: 100%;
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
        <!-- Header -->
        <jsp:include page="admin_header.jsp" />
        <!-- End Header -->
        <div class="location-container">

            <form class="location-edit-container" action="adminedit" method="POST" >


                <c:set var="c" value="${requestScope.routeDto}"/>


                <input type="hidden" name="action" value="editRoute"/>                                
                <input type="hidden" name="routeId" value="${c.routeID}"/>   

                <table>
                    <h1 style="background-color: #f2f2f2;width: 100%; ">Edit Route</h1>
                    <tr>
                        <td>Route location:</td>

                        <td>
                            <div class="form_Search_Routes-container_small">
                                <h3>Departure Place</h3>
                                <select id="start" name="routeStart" id="" required onchange="updateDestinationOptions()">
                                    <option id="default_start" value="Start">Start</option>
                                    <c:forEach items="${requestScope.listlocation}" var="l">
                                        <option  value="${l.locationID}" ${(l.locationID == c.routeStartLocation.locationID)?"selected":""}>${l.locationCity}</option>
                                    </c:forEach>


                                </select>
                            </div>
                        </td>

                        <td>
                            <div class="form_Search_Routes-container_small">
                                <h3>Destination</h3>
                                <select id="dest" name="routeDest" id="" required onchange="updateStartOptions()"  >
                                    <option id="default_dest" value="Destination">Destination</option>
                                    <c:forEach items="${requestScope.listlocation}" var="l">
                                        <option value="${l.locationID}" ${l.locationID == c.routeDestLocation.locationID ? "selected":""}>${l.locationCity}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </td>

                    </tr>

                    <tr>

                        <td>Time</td>
                        <td>
                            <input type="number" value="${c.routeTime}" name="routeTime" min="1"  placeholder="Edit Time" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td>
                            <input type="number" value="${c.routePrice}" name="routePrice" min="1" placeholder="Edit Price"  required/>
                        </td>
                    </tr>
                    <tr>
                        <td>lenght</td>
                        <td>
                            <input type="number" value="${c.routeLength}" name="routeLength" min="10" placeholder="Edit Length"  required/>
                        </td>
                    </tr>
                </table>
                <input class="locSubmit" type="submit" value="Save" required/>
            </form>

            <div style="" class="add-location">
                <a style="text-decoration: none; color: white" href="adminlist?action=route">
                    Back
                </a>
            </div>



        </div>







        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <!----- js  --->
        <script type="text/javascript">
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