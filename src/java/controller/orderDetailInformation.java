/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import car.carDAO;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import journey.journeyDAO;
import user.userDAO;

/**
 *
 * @author Admin
 */
public class orderDetailInformation extends HttpServlet {

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
            out.println("<title>Servlet orderDetailInformation</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet orderDetailInformation at " + request.getContextPath() + "</h1>");
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
      String action = request.getParameter("action");
      if(action.equals("journey")){
          String journeyID_raw = request.getParameter("journeyID");
          journeyDAO jdao = new journeyDAO();
          int journeyID = Integer.parseInt(journeyID_raw);
          
          request.setAttribute("jdto",jdao.list(journeyID, 0, 0, null, null, null, null, null).get(0));
          request.getRequestDispatcher("orderDetail.jsp").forward(request, response);
          
          
      }
      if(action.equals("user")){
          String userID_raw = request.getParameter("userID");
          userDAO udao = new userDAO();
          int userID = Integer.parseInt(userID_raw);
          request.setAttribute("udto", udao.list(userID, null, null, null, false).get(0));
          request.getRequestDispatcher("orderDetail_user.jsp").forward(request, response);
          
      }

      if(action.equals("car")){
          String carID_raw = request.getParameter("carID");
          carDAO cdao = new carDAO();
          int carID = Integer.parseInt(carID_raw);
          request.setAttribute("listcar", cdao.list(carID, null, null, null, false));
                    request.getRequestDispatcher("orderDetail_car.jsp").forward(request, response);

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
