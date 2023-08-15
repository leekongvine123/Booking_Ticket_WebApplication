<%-- 
    Document   : login
    Created on : Jun 11, 2023, 9:55:18 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="./css/login_signin.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"/>

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
        <div class="login-container">

            <form class="login" style="border: none; border-radius: 5px;    box-shadow: 5px 5px 5px 9px #888888;" action="login" method="POST">

                <div class="login-header"><h1>Login</h1></div>

                <div class="login-form">


                    <div class="input-container">
                        <i class="fa fa-user-circle" aria-hidden="true"></i>
                        <input class="login-input" type="text" name="username" value="${sessionScope.account.userName}" placeholder="Username "/>
                    </div>

                    <div class="input-container">
                        <i class="fa fa-key" aria-hidden="true"></i>
                        <input class="login-input" type="password" name="password"  placeholder="Password"/>
                        <p style="color: red">${requestScope.error}</p>
                    </div>


                    <div class="input-capt">
                        <p class="capt-error"style="color: red">${requestScope.error_captcha}</p>

                        <input type="text" name = "captcha_confirm" class="captcha-input" placeholder="Please enter captcha" required>
                        <img class="captcha-img"  id="captchaImage" alt="Captcha Image">


                        <input  type="hidden" class="captcha-detail"  name="captcha" value="${requestScope.captcha}" readonly id="capt" >

                        <i  style="padding: 5px 7px; cursor: pointer;" class="fa fa-refresh" aria-hidden="true" onclick="cap()" ></i> 
                    </div>

                    <div class="input-container">
                        <i class="" aria-hidden="true"></i>
                        <input style="display: block; margin: 0 auto;" class="login-input " type="submit" value="LOGIN">
                    </div>


                    <div class="login-google-container"  onclick="redirectToGoogleLogin()"> 
                        <div style="" class="logo-wrapper">
                            <img style="" src="https://developers.google.com/identity/images/g-logo.png">
                        </div>  
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/A_PRJ301_GR10/LoginGoogleHandler&response_type=code
                           &client_id=1060166896011-l0dvnre3kaa47aeu31a1dphi56p50cg1.apps.googleusercontent.com&approval_prompt=force"> Login With Google</a>
                    </div>
                        
                        <a style="text-decoration: none; color: black;" href="forgotPass.jsp" class="forgot-pass-container">
                        <i style="background-color: white; padding: 2px; border-radius: 5px; margin-right: 5px; font-size: 18px;" class="fa fa-hand-o-right" aria-hidden="true"></i>
                        <span style="color: white;">Forget password ?</span>
                    </a>

                </div>  




                <div class="login-footer">
                    <div style="text-align: center; display: flex; align-items: center; ">                        
                        <a style="mdisplay: inline-block; color: black; font-size: 19px;" href="./signin.jsp">Sign in</a>                            
                    </div>

                </div> 

        </div>

        <!-- login form -->
    </form>
    <!-- login -->




    <!--footer-->
    <jsp:include  page="footer.jsp"/>
    <!--End footer-->
    <script type="text/javascript">

        cap();

        function cap() {
            var alpha = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
                'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '!', '@', '#', '$', '%', '^', '&', '*', '+'];
            var a = alpha[Math.floor(Math.random() * 71)];
            var b = alpha[Math.floor(Math.random() * 71)];
            var c = alpha[Math.floor(Math.random() * 71)];
            var d = alpha[Math.floor(Math.random() * 71)];
            var e = alpha[Math.floor(Math.random() * 71)];
            var f = alpha[Math.floor(Math.random() * 71)];

            var final = a + b + c + d + e + f;

            // Create a new canvas element
            var canvas = document.createElement('canvas');
            canvas.width = 250;
            canvas.height = 90;
            var ctx = canvas.getContext('2d');

            // Set the font and text style
            ctx.font = '48px Arial';
            ctx.fillStyle = 'black';

            // Draw the captcha text on the canvas
            ctx.fillText(final, 10, 60);

            // Convert the canvas to a base64 image
            var base64Image = canvas.toDataURL('image/png');

            // Set the captcha image source to the base64 image
            var captchaImage = document.getElementById('captchaImage');
            captchaImage.src = base64Image;

            // Set the captcha value in the hidden input field
            var captchaInput = document.getElementById('capt');
            captchaInput.value = final;
        }

        function redirectToGoogleLogin() {
            window.location.href = "https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:8080/A_PRJ301_GR10/LoginGoogleHandler&response_type=code&client_id=1060166896011-l0dvnre3kaa47aeu31a1dphi56p50cg1.apps.googleusercontent.com&approval_prompt=force";
        }
    </script>





</body>

</html>
