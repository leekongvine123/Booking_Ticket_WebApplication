<%-- 
    Document   : adminListLocationCRUD
    Created on : Jul 9, 2023, 2:23:15 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Location Table</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
                padding-bottom: 0.5%;
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

        <div class="location-container">
            <div class="location-before-container">     
                <table>
                    <thead>
                        <tr>
                    <h1 style="background-color: #f2f2f2;width: 100%; margin: 0;">Location's Information</h1>
                    </tr>
                    <tr>
                        <th>Location ID</th>
                        <th>Location City</th>
                        <th>Location Station</th>
                        <th>Location Image</th>
                    </tr>
                    </thead>



                    <tbody>
                        <c:set var="c" value="${requestScope.locEdit}"/>
                        <tr>
                            <td>${c.locationID}</td>
                            <td>${c.locationCity}</td>
                            <td>${c.locationStation}</td>
                            <td><img class="locImg" src="data:image/jpg;base64,${c.locationImg}" /></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <form class="location-edit-container" action="adminedit" method="POST" enctype="multipart/form-data">

                <input type="hidden" name="action" value="editLoc"/>                
                <input type="hidden" name="locId" value="${c.locationID}"/>
                <h3>${requestScope.cantEditError}</h3>

                <table>
                    <h1 style="background-color: #f2f2f2;width: 100%; ">Edit location</h1>
                    <tr>
                        <td>Location city</td>
                        <td><input name="locCity" type="text"  value="${c.locationCity}" placeholder="Edit location city" required/></td>
                    </tr>
                    <tr>
                        <td>Location station</td>
                        <td><input name="locStation" type="text" value="${c.locationStation}" placeholder="Edit location station" required=""/></td>
                    </tr>
                    <tr>
                        <td>Location image</td>
                        <td> 
                            <div style="display: flex; justify-content: center;">
                                <input name="locImage" type="file" style="text-align: center;" />
                            </div>
                            <h4 style="color: red;">${requestScope.notSupportErr}</h4>
                        </td>
                    </tr>
                </table>
                <input class="locSubmit" type="submit" value="Save" required/>
            </form>
            <div style="" class="add-location">
                <a style="text-decoration: none; color: white" href="adminlist?action=location">
                    Back
                </a>
            </div>
        </div>

    </body>
</html>
