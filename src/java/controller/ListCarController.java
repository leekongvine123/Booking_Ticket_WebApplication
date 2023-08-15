/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import car.carDAO;
import car.carDTO;
import deptime.deptimeDAO;
import deptime.deptimeDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import journey.journeyDAO;
import journey.journeyDTO;
import route.routeDAO;
import route.routeDTO;

/**
 *
 * @author Admin
 */
public class ListCarController extends HttpServlet {

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
            out.println("<title>Servlet ListCarController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListCarController at " + request.getContextPath() + "</h1>");
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
        String cabin = request.getParameter("cabin");
        String wifi = request.getParameter("wifi");
        String cartype = request.getParameter("cartype");

        carDAO cdao = new carDAO();

        journeyDAO jdao = new journeyDAO();
        routeDAO rdao = new routeDAO();
        deptimeDAO ddao = new deptimeDAO();

        List<carDTO> list = new ArrayList<>();
        List<journeyDTO> listj = jdao.list(0, 0, 0, null, null, null, null, null);
        List<routeDTO> rlist = rdao.list(0, 0, 0, true);
        List<deptimeDTO> dlist = ddao.list(0, 0);
        list = cdao.list(0, cartype, cabin, wifi, true);

        request.setAttribute("listcar", list);
        request.setAttribute("jlist", listj);
        request.setAttribute("rlist", rlist);
        request.setAttribute("dlist", dlist);

        request.getRequestDispatcher("addJourney.jsp").forward(request, response);
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
