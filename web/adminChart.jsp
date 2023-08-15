<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Dashboard</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>

        <link rel="stylesheet" href="dashboard.css">
        <style>
  body { 
                background: url(img/background/background.png) no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px 0;
            }

            th, td {
                border: 1px solid #ccc;
                padding: 10px;
                text-align: center;
            }

            /* Add some styles for the table header (thead) */
            thead {
                background-color: #f2f2f2;
                font-weight: bold;
            }

            /* Apply alternating background color for table rows in the tbody */
            tbody tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            /* Hover effect for table rows */
            tbody tr:hover {
                background-color: #e6e6e6;
            }
            #myChart{
                width: 30%!important;
                height: 30%!important;
            }
            .container {
         
        }
        
        h2 {
            margin: 0;
        }
        
        .widget {
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 10px;
            margin-top: 20px;
            background-color: #fff;
             
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

        }
        
        .widget table {
            width: 100%;
            border-collapse: collapse;
        }
        
        .widget th, .widget td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        
        .widget th {
            background-color: #f2f2f2;
        }
        
        .widget tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        
        .widget tbody tr:hover {
            background-color: #e6e6e6;
        }
        
        /* Styles for the top routes */
        .widget .top-routes {
            display: flex;
            gap: 30%;
        }
        
        .widget .top-routes canvas {
            width: 70%;
        }
        
        .widget .top-routes table {
            flex: 1;
        }
        
        .widget .top-routes h2 {
            margin-bottom: 10px;
        }
        </style>
    </head>
    <body>
        <jsp:include page="admin_header.jsp" />
             <c:set value="${0}" var="number" />
            <c:forEach items="${requestScope.top3}" var="t">
                <c:set value="${number+t.number}" var="number" />
            </c:forEach>
        <div class="container">
       
         
            <div  style="display: flex;text-align: center;justify-content: center;gap:20%;justify-content: space-between  ;border: none;
    border-radius: 5px;
    background-color: white;
    color: green;
    text-align: center;
    text-decoration: none;
    font-weight: bold;
    box-shadow: 0px 4px 6px rgba(0, 128, 0, 0.3);
    transition: all 0.3s ease;">
                <Div style="margin-left:3%;"> <h2>Total money:<input disabled="" style="border:none;margin:0;" value="" id="total"  /></h2></Div>
                <h2 style="margin-right:19%;">Total Order:${number}  </h2>
            </div>
                <div class="widget" style="">
                <div style="display: flex;gap:30%;">
                    <canvas id="myChart"></canvas>

                    <div style="display: flex;flex-direction: column;align-items: center;">

                        <h2>TOP 3 ROUTES</h2>
                        <table>
                            <thead>



                                <tr>
                                    <th>Name</th>
                                    <th>Number Of Order</th>

                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.top3}" var="top">


                                    <tr>
                                        <td>${top.routeID.routeStartLocation.locationCity} <i class="fa fa-arrow-right" aria-hidden="true">${top.routeID.routeDestLocation.locationCity}</i></td>
                                        <td>${top.number}</td>

                                    </tr>
                                </c:forEach>

                                <!-- Add more rows for additional employees -->
                            </tbody>
                        </table>
                    </div>

                </div>

            </div>
          
        </div>


        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script>
            // Retrieve chart data from your data source or generate it dynamically

            var labels = [];
            var data = [];
            <c:forEach items="${requestScope.total}" var="t">
            labels.push(`${t.routeID.routeStartLocation.locationCity} => ${t.routeID.routeDestLocation.locationCity}`);

            </c:forEach>
            <c:forEach items="${requestScope.total}" var="t">
                data.push(${t.total});

            </c:forEach>
                var total = 0; // Initialize the total variable before the loop

                for (let i = 0; i < data.length; i++) {
                    total += data[i];
                }

var formattedNumber = total.toLocaleString('en-US', { maximumFractionDigits: 0 });
                document.getElementById('total').value = formattedNumber+'VND';



                // Create the chart
                var ctx = document.getElementById('myChart').getContext('2d');
                var myChart = new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: labels,
                        datasets: [{
                                data: data,
                                backgroundColor: ['rgb(75, 192, 192)', 'rgb(255, 99, 132)', 'rgb(54, 162, 235)']
                            }]
                    },
                    options: {
                        responsive: true
                    }
                });
        </script>

    </body>
</html>
