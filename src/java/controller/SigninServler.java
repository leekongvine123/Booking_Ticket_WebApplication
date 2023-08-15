/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.userDAO;
import user.userDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "signinServler", urlPatterns = {"/signin"})
public class SigninServler extends HttpServlet {

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
            out.println("<title>Servlet signinServler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet signinServler at " + request.getContextPath() + "</h1>");
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
        try {
            int role = 2;
            String username = request.getParameter("username");
            String firstname = request.getParameter("userfirstname");
            String lastname = request.getParameter("userlastname");
            String userDob_raw = request.getParameter("userdob");
            Date userDob = java.sql.Date.valueOf(userDob_raw);
            String gmail = request.getParameter("gmail");
            String phone = request.getParameter("phone");
            String usergender = request.getParameter("usergender");
            String password = request.getParameter("password");
            String password_confirm = request.getParameter("confirm-pass");
            String msg = "";
            userDAO dao = new userDAO();
            if (phone.length() < 10 || phone.length() > 12) {
                msg = "Please enter 10 - 12 digits phone number !";
                request.setAttribute("error_4", msg);
            }
            if (!dao.list(0, null, null, gmail,false).isEmpty()) {
                msg = "Gmail existed!";
                request.setAttribute("error_2", msg);
            }

            if (!password.equals(password_confirm)) {
                msg = "Confirm password not match!";
                request.setAttribute("error_3", msg);

            }
            if (!password.equals(password_confirm) || !dao.list(0, null, null, gmail,false).isEmpty() || phone.length() < 10 || phone.length() > 12) {
                request.getRequestDispatcher("signin.jsp").forward(request, response);
            }
                userDTO dto = userDTO.builder()
                        .roleID(role)
                        .userName(username)
                        .userPassword(password)
                        .userPhoneNumber(phone)
                        .userMail(gmail)
                        .userFirstName(firstname)
                        .userLastName(lastname)
                        .userDob(userDob)
                          .userGender(usergender)
                        .status(true).build();
                dao.insertUser(dto);

          

        } catch (Exception e) {
            System.out.println("Loi o signinServlet class:controller" + e.getMessage());
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);

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
