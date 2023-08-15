/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import journey.journeyDAO;
import journey.journeyDTO;
import order.orderDAO;
import seat.seatDAO;
import seat.seatDTO;
import user.userDTO;

/**
 *
 * @author Admin
 */
public class ListSeat extends HttpServlet {

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
            out.println("<title>Servlet ListSeat</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListSeat at " + request.getContextPath() + "</h1>");
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
        seatDAO s = new seatDAO();
        String action = request.getParameter("action");

        String journeyID_raw = request.getParameter("journeyID");

        int journeyID = Integer.parseInt(journeyID_raw);
        List<seatDTO> list = s.list(0);
        orderDAO o = new orderDAO();
        journeyDAO j = new journeyDAO();
        journeyDTO jdto = j.list(journeyID, 0, 0, null, null, null, null, null).get(0);
        List<seatDTO> list2 = new ArrayList<>();
        if (jdto.getCarID().getCarType().contains("1F")) {

            for (int i = 0; i < 17; i++) {
                list2.add(list.get(i));
            }
            list.removeAll(list);
            list = list2;
        }
        if (action.equals("view")) {
            HttpSession session = request.getSession();
            userDTO udto = (userDTO) session.getAttribute("account");
            String orderID_raw = request.getParameter("orderID");
            int orderID = Integer.parseInt(orderID_raw);
            List<seatDTO> lists = new ArrayList<>();
        
            for (seatDTO dTO : list) {

                if (!o.list(orderID, 0, 0, 0, journeyID, dTO.getSeatID(), null, null).isEmpty()) {

                dTO.setSeatNum(dTO.getSeatNum()+".");

                }
                if (!o.list(0, 0, 0, 0, journeyID, dTO.getSeatID(), null, null).isEmpty() && o.list(orderID, 0, 0, 0, journeyID, dTO.getSeatID(), null, null).isEmpty()) {

                    dTO.setSeatNum("X");

                }

            }
            request.setAttribute("list", list);
         request.setAttribute("lists", list);
            request.getRequestDispatcher("pickingseat.jsp").forward(request, response);

        }
        for (seatDTO dTO : list) {

            if (!o.list(0, 0, 0, 0, journeyID, dTO.getSeatID(), null, null).isEmpty()) {

                dTO.setSeatNum("X");

            }
        }

        request.setAttribute("list", list);
        request.getRequestDispatcher("pickingseat.jsp").forward(request, response);

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
