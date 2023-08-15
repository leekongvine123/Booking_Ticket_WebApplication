/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.userDAO;
import user.userDTO;

/**
 *
 * @author USER
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet loginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet loginServlet at " + request.getContextPath() + "</h1>");
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
        String logout = request.getParameter("logout");
        HttpSession session = request.getSession();
        if (logout != null) {
            session.invalidate();
            response.sendRedirect("listlocation");
        }
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
        HttpSession session = request.getSession();

        String username_raw = request.getParameter("username");
        String password_raw = request.getParameter("password");
        String captcha = request.getParameter("captcha");
        request.setAttribute("captcha", captcha);
        String captcha_confirm = request.getParameter("captcha_confirm");
        userDAO dao = new userDAO();
        List<userDTO> ulist = dao.list(0, username_raw, password_raw, null,true);

        if (!captcha_confirm.equals(captcha) || captcha_confirm.equals("")) {

            request.setAttribute("error_captcha", "Please enter correct captcha");
        }
        if (ulist.isEmpty()) {
            request.setAttribute("error", "Wrong Password or UserName");
        }

        if (ulist.isEmpty() || !captcha_confirm.equals(captcha)) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            userDTO dto = ulist.get(0);
            dto.getUserID();
            session.setAttribute("account", dto);
            if (dto.getRoleID() == 1) {
                response.sendRedirect("admin_homepage.jsp");

            } else {
                response.sendRedirect("listlocation");
            }
        }

    }

    public static String generateRandomCaptcha(int length) {
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();

        // Generate random characters from the allowedChars string
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            captcha.append(allowedChars.charAt(index));
        }

        return captcha.toString();
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
