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
import javax.servlet.http.HttpSession;
import user.userDAO;
import user.userDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "UpdateProfileController", urlPatterns = {"/updateprofile"})
public class UpdateProfileController extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            userDAO dao = new userDAO();
            userDTO dto = (userDTO) session.getAttribute("account");

            int userid = dto.getUserID();
            String username = request.getParameter("update-name");
            String userfirstname = request.getParameter("update-firstname");
            String userlastname = request.getParameter("update-lastname");
            String updateuserdob_raw = request.getParameter("update-dob");
            Date updateuserdob = java.sql.Date.valueOf(updateuserdob_raw);
            String phone = request.getParameter("update-phone");
            String gender = request.getParameter("update-gender");
            String msg = "";

            if ((phone.length() < 10 || phone.length() > 12)) {
                msg = "Please enter 10 - 12 digits phone number !";
                request.setAttribute("error_5", msg);
                request.getRequestDispatcher("profileUpdate.jsp").forward(request, response);
            }

            if (phone.equals(dto.getUserPhoneNumber())) {
                phone = null;
            }

            if ((dto.getUserID() != 0)) {
                dao.updateUser(username, null, phone, userid, userfirstname, userlastname, (java.sql.Date) updateuserdob, gender);
            }

            dao.list(dto.getUserID(), null, null, null,true);
            session.setAttribute("account", dao.list(dto.getUserID(), null, null, null,true).get(0));
        } catch (NumberFormatException e) {
            System.out.println("Loi o UpdateProfileController " + e.getMessage());
        }

        request.getRequestDispatcher("profile.jsp").forward(request, response);

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
        try {
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            userDAO dao = new userDAO();
            userDTO dto = (userDTO) session.getAttribute("account");
            String password = request.getParameter("cur_pass");
            String newpass = request.getParameter("new_pass");
            String conf_newpass = request.getParameter("conf_new_pass");
            String msg = "";
            List<userDTO> udto = dao.list(dto.getUserID(), null, password, null,true);
            if (udto.isEmpty()) {
                msg = "Password not found try again";
                request.setAttribute("error_7", msg);
            }

            if (!conf_newpass.equals(newpass)) {
                msg = "Confirm password not match!";
                request.setAttribute("error_8", msg);
                request.getRequestDispatcher("changePass.jsp").forward(request, response);
            }

            if (dto.getUserID() != 0) {
                dao.updateUser(null, newpass, null, dto.getUserID(), null, null, null, null);
            }
            dao.list(dto.getUserID(), null, null, null,true);
            session.setAttribute("account", dao.list(dto.getUserID(), null, null, null,true).get(0));
        } catch (Exception e) {
        }
        request.getRequestDispatcher("home.jsp").forward(request, response);

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
