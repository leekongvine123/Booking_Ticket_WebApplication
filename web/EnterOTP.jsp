<%-- 
    Document   : EnterOTP
    Created on : Jul 7, 2023, 9:21:30 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>

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
            input::-webkit-outer-spin-button,
            input::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }

            .otp-container{
                display: flex;
                justify-content: center;
                flex-direction: column;
                align-items: center;                
                width: 100%;
                height: 1000px;
            }
            .forget-password-body{
                padding: 50px;
                width: 100%;
                margin: auto;
            }

            .otp-cont{
                padding: 20px;
                background-color: #fff; 
                width: 30%;
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
                box-shadow: 5px 10px 10px #888888;

            }
            .otp-submit {
                transition: transform 0.3s ease;
            }
            .otp-submit:hover {
                transform: translateY(-10%);
                cursor: pointer;
            }
            .otp-submit:not(:hover) {
                transform: translateY(0);
            }
            .otp-submit{
                border: 2px solid black;
                border-radius: 5px;
                padding: 10px;                
                color: white;
                background-color: #3366ff;
                box-shadow: 2px 5px 10px #888888;

            }
            .otp-input{
                height: 60%;
            }
            .timer {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 100px;
                height: 100px;
                border-radius: 50%;
                background-color: green;
                color: white;
                font-size: 24px;
                font-weight: bold;
            }

        </style>
    </head>
    <body>



        <div class="otp-container">
            <div class="otp-cont">
                <i style="font-size: 150px;" class="fa fa-lock" aria-hidden="true"></i>
                <h1>Enter OTP</h1>
                <p>OTP is sent to your email address</p>
                <div class="timer"><span id="timer"></span> s</div>
                <form class="forget-password-body" action="forgotpass" method="GET" onsubmit="return validateOTP()">

                    <input type="hidden" id="otpValue" name="otpValue" value="${requestScope.otpValue}" />
                    <input type="hidden"  name="userIdValue" value="${requestScope.userID}" />  

                    <input id="otp-input" class="otp-input" name="otp-input" style="width: 100%;" type="number" placeholder="Enter your OTP code" required />
                    <p id="error-message" style="color: red;">${requestScope.error_OTP}</p>
                    <input class="otp-submit" style="width: 100%; margin-top: 5%;" type="submit" value="Reset Password" />

                </form>
            </div>
        </div>

        <script>
            var initialTimer = 60; // Set the initial timer value
            var timer = initialTimer; // Initialize the timer variable
            var countdownInterval; // Store the interval reference

            function countdownTimer(redirectUrl) {
                countdownInterval = setInterval(function () {
                    // Display the remaining time
                    document.getElementById("timer").textContent = timer;

                    // Check if the time has reached zero
                    if (timer <= 0) {
                        clearInterval(countdownInterval); // Stop the countdown

                        // Redirect to the specified URL
                        window.location.href = redirectUrl;
                    }

                    timer--; // Decrease the timer by 1 second
                }, 1000); // Update the timer every second
            }

            // Example usage: countdown for 60 seconds and redirect to "login.jsp"
            countdownTimer("login.jsp");

            // OTP validation logic
            function validateOTP() {
                var otpInput = document.getElementById("otp-input").value;
                var otpValue = document.getElementById("otpValue").value;

                if (otpInput === otpValue) {
                    clearInterval(countdownInterval); // Stop the countdown
                    return true;
                } else {
                    document.getElementById("error-message").textContent = "Wrong OTP code, please check again!";
                    return false;
                }
            }
        </script>
    </body>
</html>
