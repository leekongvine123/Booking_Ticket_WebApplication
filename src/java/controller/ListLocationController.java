/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import location.locationDAO;
import location.locationDTO;
import order.orderDAO;
import order.orderDTO;
import route.routeChartDTO;
import route.routeDAO;
import user.userDTO;

/**
 *
 * @author Admin
 */
public class ListLocationController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListLocationController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListLocationController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        locationDAO l = new locationDAO();
        List<locationDTO> list = l.list(0, true);
        routeDAO rDao = new routeDAO();
        List<routeChartDTO> chartListDTO = rDao.listcharttop2();
        request.setAttribute("popularOne", chartListDTO.get(0).getRouteID());
        request.setAttribute("popularTwo", chartListDTO.get(1).getRouteID());
        request.setAttribute("listlocation", list);
        String start = request.getParameter("start");
        String dest = request.getParameter("dest");
        request.setAttribute("start", start);
        request.setAttribute("dest", dest);
        if (session.getAttribute("account") != null) {
            userDTO udto = (userDTO) session.getAttribute("account");
            if (udto.getRoleID() == 1) {
                if (request.getParameter("notfound") == null) {
                    request.getRequestDispatcher("editOrder.jsp").forward(request, response);
                }
                String orderID_raw = request.getParameter("orderID");
                int orderID = Integer.parseInt(orderID_raw);
                orderDAO odao = new orderDAO();
                orderDTO odto = odao.list(orderID, 0, 0, 0, 0, 0, null, null).get(0);
                session.setAttribute("orderDTO", odto);
                request.getRequestDispatcher("editOrder.jsp").forward(request, response);
            }

        }

        request.getRequestDispatcher("home.jsp").forward(request, response);
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
        locationDAO l = new locationDAO();
        List<locationDTO> list = l.list(0, false);
        request.setAttribute("listlocation", list);

        request.getRequestDispatcher("adminListLocationCRUD.jsp").forward(request, response);
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
