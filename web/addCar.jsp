<%-- 
    Document   : addCar
    Created on : Jul 17, 2023, 12:30:55 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                width: 40%;
                display: flex;  
                justify-content: center;
                text-align: center;
                align-items: center;
                margin-top: 10%;
                flex-direction: column;
                box-shadow: 0 0 10px 5px #888888;


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
                width: 50%;

            }
            .locSubmit:hover{
                opacity: 0.7;
            }
            .locSubmit{
                width: 100%;
                background-color: green;
                color: white;
                border: none;
                padding: 5px;

            }
            .cartype-p{
                margin-bottom: 0;
            }
            .cartype-container{
                display: flex;
                justify-content: space-around;
            }
            .radio-container {
                display: flex;
                justify-content: space-around;
                align-items: stretch;
                gap: 10px;
            }
            .add-location:not(:hover) {
                transform: translateY(0);
            }
            .add-location{
                transition: transform 0.3s ease;
                margin-right:  38.4%;
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

        <div class="location-container">
            <form class="location-edit-container" action="adminadd" method="POST" >

                <input type="hidden" name="action" value="car"/>                
                <table>
                    <h1 style="background-color: #f2f2f2;width: 100%; ">Add Car</h1>

                    <tr>
                        <td>
                            Car Type
                        </td>
                        <td class="radio-container">
                            <label>1F <input type="radio" name="carType" value="1F" required/></label>
                            <label>2F <input type="radio" name="carType" value="2F"/></label>
                        </td>

                    </tr>

                    <tr>
                        <td>
                            Car Option
                        </td>
                        <td class="radio-container">
                            <label>Cabin <input type="checkbox" value="T" name="carCabin" value="1F" /></label>
                            <label>Wifi <input type="checkbox"  value="T" name="carWifi" value="2F"/></label>
                        </td>
                    </tr>

                </table>
                <input class="locSubmit" type="submit" value="Add" required/>
            </form>
            <div style="" class="add-location">
                <a style="text-decoration: none; color: white" href="adminlist?action=car">
                    Back
                </a>
            </div>
        </div>
    </body>
</html>
