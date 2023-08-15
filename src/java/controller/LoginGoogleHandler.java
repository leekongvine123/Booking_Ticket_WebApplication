/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import user.Mail;
import user.UserGoogleDTO;
import user.userDAO;
import user.userDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "LoginGoogleHandler", urlPatterns = {"/LoginGoogleHandler"})
public class LoginGoogleHandler extends HttpServlet {

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
            out.println("<title>Servlet UpdateProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfileController at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8"); //lệnh set tiếng việt cho servlet

        try {

            //        Lay doi tuong tu google
            String code = request.getParameter("code");
            String accessToken = getToken(code);
            UserGoogleDTO ugDTO = getUserInfo(accessToken);
            System.out.println("" + ugDTO);

//        Xu li login
            String ugMail = ugDTO.getEmail();
            String ugName = extractFirstWord(ugDTO.getEmail());
            String ugPass = ugDTO.getId();
            int ugRole = 2;

            Mail email = new Mail();
            userDAO usDAO = new userDAO();

            HttpSession session = request.getSession();

            if (usDAO.list(0, null, null, ugMail, true).isEmpty()) {
                userDTO dto = userDTO.builder()
                        .roleID(ugRole)
                        .userName(ugName)
                        .userPassword(ugPass)
                        .userMail(ugMail)
                        .status(true).build();
//                email.sendEmail(ugMail, "Nhà xe NVT", "Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi tài khoản của bạn là: TÀI KHOẢN: " + ugMail + " MẬT KHẨU: " + ugPass + " Bạn có thể đổi mật khẩu và thông tin cá nhân ở [Profile] -> [Change Password]");
                email.sendEmail(ugMail, "Nhà xe NVT", "<!DOCTYPE html>\n"
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
                        + "                padding: 15px 0 15px 0;\"> Thank you for using our service\n"
                        + "        </div>\n"
                        + "        <div style=\"display: flex; \n"
                        + "             padding: 0px 500px ;\n"
                        + "             line-height: 75px;\">\n"
                        + "            <div style=\"font-size: 20px;\n"
                        + "                    padding: 80px 0 20px 0;\">\n"
                        + "                <div style=\" margin-bottom: 35px;\n"
                        + "                                width: 100%;\n"
                        + "                                float: left;\">                  \n"
                        + "                    <h2> Username: " + ugName + " </h2>\n"
                        + "                    <h2> Password: " + "<span>" + ugPass + "</span>" + "  </h2>\n"
                        + "                </div>\n"
                        + "               \n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "        <div style = \"text-align: center;\n"
                        + "             font-size: 30px;\n"
                        + "             padding: 10px;\n"
                        + "             margin-bottom: 90px;\"> Thank you for using our services - You now can login with Google ,to change passsword click profile -> change password "
                        + "</div>\n"
                        + "    </body>\n"
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
                        + "</html>\n"
                        + "    ");

                usDAO.insertUser(dto);
            }

            List<userDTO> ulist = usDAO.list(0, null, null, ugMail, true);
            if (!ulist.isEmpty()) {
                userDTO dto = ulist.get(0);
                dto.getUserID();
                session.setAttribute("account", dto);
                if (dto.getRoleID() == 1) {
                    response.sendRedirect("adminlist");
                } else {
                    response.sendRedirect("listlocation");
                }
            }

        } catch (Exception e) {
            System.out.println("Loi o login googlehandler " + e.getMessage());
        }

    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();
        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDTO googlePojo = new Gson().fromJson(response, UserGoogleDTO.class);

        return googlePojo;
    }

    public static String extractFirstWord(String inputString) {
        String result = "";
        for (int i = 0; i <= 5; i++) {
            result += inputString.charAt(i);
        }
        return result;

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
        processRequest(request, response);
    }

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
