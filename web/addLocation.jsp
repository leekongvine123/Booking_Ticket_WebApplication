<%-- 
    Document   : addLocation
    Created on : Jul 12, 2023, 9:15:37 PM
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
                width: 50%;
                display: flex;  
                justify-content: center;
                text-align: center;
                align-items: center;
                margin-top: 10%;
                flex-direction: column;
                box-shadow: 0 0 10px 5px #888888;
                padding-bottom: 0.5%;

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
                border-radius: 5px;
            }
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
                margin-right:  48%;
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
            <form class="location-edit-container" action="adminadd" method="POST" enctype="multipart/form-data">

                <input type="hidden" name="action" value="location"/>                
                <table>
                    <h1 style="background-color: #f2f2f2;width: 100%; ">Add location</h1>
                    <h3>${requestScope.cantEditError}</h3>

                    <tr>
                        <td>Location city</td>
                        <td><input  type="text"  name="locationCity" placeholder="Enter location city" required/></td>
                    </tr>
                    <tr>
                        <td>Location station</td>
                        <td><input  type="text" name="locationStation" placeholder="Enter location station" required=""/></td>
                    </tr>
                    <tr>
                        <td>Location image</td>
                        <td> 
                            <div style="display: flex; justify-content: center;">
                                <input required  name="locationImage" type="file" style="text-align: center;" />
                            </div>
                            <h4 style="color: red;">${requestScope.notSupportErr}</h4>
                        </td>
                    </tr>
                </table>
                <input class="locSubmit" type="submit" value="Add" required/>
            </form>
            <div style="" class="add-location">
                <a style="text-decoration: none; color: white" href="adminlist?action=location">
                    Back
                </a>
            </div>
        </div>
    </body>
</html>
