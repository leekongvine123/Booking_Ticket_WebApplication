/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.Mail;
import user.userDAO;
import user.userDTO;

/**
 *
 * @author USER
 */
public class ForgotPassServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForgotPassServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPassServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String OTP_value = request.getParameter("otpValue");
        String OTP_input = request.getParameter("otp-input");
        String usId = request.getParameter("userIdValue");

        String msg = "";

        if (!OTP_input.equals(OTP_value)) {

            request.setAttribute("otpValue", OTP_value); // Store the previous OTP value
            request.getRequestDispatcher("EnterOTP.jsp").forward(request, response);
        }

        request.setAttribute("UserId", usId);
        // Redirect to the reset password JSP
        request.getRequestDispatcher("resetPass.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userDAO usdao = new userDAO();

        String forgMail = request.getParameter("email-forget");
        String msg = "";
        List<userDTO> userList = usdao.list(0, null, null, forgMail, true);

        if (userList.isEmpty()) {
            msg = "seem like your email dont exist in our system or your email is wrong, Please check again.";
            request.setAttribute("error_forg", msg);
            request.getRequestDispatcher("forgotPass.jsp").forward(request, response);
        } else {
            try {
                userDTO usDTO = usdao.list(0, null, null, forgMail, true).get(0);
                int userID = usDTO.getUserID();
                Mail mail = new Mail();
                int captcha = Integer.parseInt(generateRandomCaptcha(6));
                request.setAttribute("userID", userID);
                request.setAttribute("otpValue", captcha);
                String mailDetail = "Your OTP code is: " + captcha;
                mail.sendEmail(forgMail, "Nha xe NVT", "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "    <head>\n"
                        + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                        + "        <title>Nha xe NVT</title>\n"
                        + "        <style>\n"
                        + "            body { \n"
                        + "                font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;\n"
                        + "                background-color: lightgrey;\n"
                        + "                margin: 0px 150px 0px 150px;\n"
                        + "            }\n"
                        + "        </style>\n"
                        + "    </head>\n"
                        + "    <body>\n"
                        + "        <h1 style=\"color: green; \n"
                        + "            font-size: 50px; \n"
                        + "            text-align: center; \n"
                        + "            background:white;\n"
                        + "            border-radius:30px;\n"
                        + "            margin: 70px 550px 0 550px;\n"
                        + "            padding: 15px 0 15px 0;\"> Nha xe NVT\n"
                        + "        </h1>\n"
                        + "        \n"
                        + "        <div style=\"font-size: 30px;\n"
                        + "                text-align: center;\n"
                        + "                color: red;\n"
                        + "                padding: 15px 0 15px 0;\"> OTP reset password\n"
                        + "        </div>\n"
                        + "        <div style=\"display: flex; \n"
                        + "             padding: 0px 500px ;\n"
                        + "             line-height: 75px;\">\n"
                        + "            <div style=\"font-size: 25px;\n"
                        + "                    padding: 80px 0px 20px 50px;\">\n"
                        + "                <div style=\"margin-bottom: 35px;\n"
                        + "                                width: 100%;\n"
                        + "                \n"
                        + "                                float: right;\">                  \n"
                        + "                    <h2> Your OTP code is:" + captcha + "</h2>\n"
                        + "                </div>\n"
                        + "               \n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "        <div style = \"text-align: center;\n"
                        + "             font-size: 30px;\n"
                        + "             padding: 10px;\n"
                        + "             margin-bottom: 90px;\"> Thank you for using our services</div>\n"
                        + "            \n"
                        + "        \n"
                        + "    </body>\n"
                        + "\n"
                        + "    <footer>\n"
                        + "    <div style=\"font-size: 20px;\n"
                        + "            background-color: white;\n"
                        + "            border-radius: 50px;\n"
                        + "            \n"
                        + "            padding: 0px 50px 0px 50px;\">\n"
                        + "        <p>Phone: 094299999</p>\n"
                        + "        <p>Công ty cổ phần nhà xe Nam Vinh NaVi Travels</p>\n"
                        + "        <p>Email: NaVitravels@gmail.com</p>\n"
                        + "        <p>Địa chỉ: Lô E2a-7, Đường D1, Khu Công nghệ cao, P.Long Thạnh Mỹ, Tp. Thủ Đức, TP.HCM.</p>\n"
                        + "    </div> \n"
                        + "        \n"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Loi o ForgotPassServlet" + e.getMessage());
            }
            request.getRequestDispatcher("EnterOTP.jsp").forward(request, response);

        }

    }

    public static String generateRandomCaptcha(int length) {
        String allowedChars = "1234567890";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();
        // Generate random characters from the allowedChars string
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            captcha.append(allowedChars.charAt(index));
        }

        return captcha.toString();
    }

//    public static void main(String[] args) {
//        String captcha = generateRandomCaptcha(6);
//        System.out.println("" + captcha);
//    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
