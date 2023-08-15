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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">        
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>
        <title>JSP Page</title>
        <style>
            body { 
                background: url(img/background/background.png) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
                overflow-x: hidden;
            }
            .table-container {
                   border-radius: 25px;
                max-height: 300px;
                overflow-y: scroll;
            }
            .sticky-top{
                color:  black !important; 
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

            .locImg{
                width: 30%;
                height: 30%;

            }
            .add-location:hover{
                opacity: 0.7;
            }
            .add-location{
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
        <%@include file = "admin_header.jsp"%>
        <c:if test="${(requestScope.existError != null)}">


            <input value="${requestScope.existError}" id="check" hidden=""/>
        </c:if>

        <div class="container">
            <form action="adminsearch">
                <input type="hidden" name="action" value="searchLocation"/>
                <input type="text" name="city" value="${requestScope.city}" placeholder="Search by location city"/>
                <input type="submit" value="Search  " class="form_Sort_Routes-submit">

            </form>
            <div style="text-align: center; " ><h1 style="margin-bottom: 0;">Location management</h1></div>
            <div style="text-align: center" class="table-container">
                <table style="background-color: white;" class="table table-striped">
                    <thead class="sticky-top">
                        <tr>
                            <th>
                                Location Id
                            </th>
                            <th>
                                Location city   
                            </th>
                            <th>
                                Location station
                            </th>
                            <th>
                                Location image
                            </th>  
                            <th>
                                Status
                            </th>  
                            <th>
                                Edit
                            </th>
                            <th>
                                Change status
                            </th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${requestScope.listlocation}" var="c">
                            <tr>
                                <td>${c.locationID}</td>  
                                <td>${c.locationCity}</td>  
                                <td>${c.locationStation}</td>
                                <td><img class="locImg" src="data:image/jpg;base64,${c.locationImg}" /></td>

                                <c:if test="${c.status == true}">
                                    <td style="color: #088000;">Available</td>
                                </c:if>

                                <c:if test="${c.status == false}">
                                    <td style="color: red;">Unavailable</td>
                                </c:if>

                                <td><a href="adminedit?action=editLocation&locationID=${c.locationID}">Edit location :  ${c.locationID}</a></td>
                                <c:if test="${c.status == true}">
                                    <td><a href="admindelete?action=delete&locationID=${c.locationID}&status=${c.status }">Change to: <span style="color: red;">Unavailable</span></a>  </td>
                                </c:if>

                                <c:if test="${c.status == false}">
                                    <td><a href="admindelete?action=delete&locationID=${c.locationID}&status=${c.status }">Change to: <span style="color: #088000;">Available</span></a></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="add-location">
                <a style="text-decoration: none; color: white" href="adminadd?action=location">
                    Add location
                </a>
            </div>
        </div>

        <script type="text/javascript">
            if (${requestScope.existError !=null}) {
                window.alert('The Location can not be change because it was in use');
            }
        </script>

    </body>
</html>
