/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import car.carDAO;
import car.carDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import journey.journeyDAO;
import journey.journeyDTO;
import location.locationDAO;
import location.locationDTO;
import order.orderDAO;
import order.orderDTO;
import route.routeDAO;
import route.routeDTO;
import user.userDTO;

/**
 *
 * @author USER
 */
public class adminCRUD_Search extends HttpServlet {

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
            out.println("<title>Servlet adminCRUD_Search</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminCRUD_Search at " + request.getContextPath() + "</h1>");
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
        if (action.equals("searchCar")) {
            String cabin = request.getParameter("cabin");
            String wifi = request.getParameter("wifi");
            String cartype = request.getParameter("cartype");
            carDAO cDao = new carDAO();
            List<carDTO> carListDto = cDao.list(0, cartype, cabin, wifi, false);
            request.setAttribute("listcar", carListDto);
            request.setAttribute("carType", cartype);
            request.setAttribute("wifi", wifi);
            request.setAttribute("cabin", cabin);
            request.getRequestDispatcher("adminListCarCRUD.jsp").forward(request, response);
        }
        if (action.equals("searchLocation")) {
            String locationCity = request.getParameter("city");
            locationDAO lDao = new locationDAO();
            List<locationDTO> locationListDTO = lDao.search(locationCity);
            if (locationListDTO.isEmpty()) {
                request.getRequestDispatcher("adminlist?action=location").forward(request, response);
            }
            request.setAttribute("city", locationCity);
            request.setAttribute("listlocation", locationListDTO);
            request.getRequestDispatcher("adminListLocationCRUD.jsp").forward(request, response);
        }
        if (action.equals("searchRoute")) {
            String start_raw = request.getParameter("form_Search_Routes-Start");
            String dest_raw = request.getParameter("form_Search_Routes-Destination");
            routeDAO rDao = new routeDAO();
            try {
                int start = Integer.parseInt(start_raw);
                int dest = Integer.parseInt(dest_raw);
                request.setAttribute("startID", start);
                request.setAttribute("destID", dest);
                List<routeDTO> routeListDTO = rDao.list(0, start, dest, false);
                if (routeListDTO.isEmpty()) {
                    request.getRequestDispatcher("adminlist?action=route").forward(request, response);
                }
                locationDAO lDao = new locationDAO();
                List<locationDTO> locationListDTO = lDao.list(0, false);
                request.setAttribute("listlocation", locationListDTO);
                request.setAttribute("listroute", routeListDTO);
                request.getRequestDispatcher("adminListRouteCRUD.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println("" + e.getMessage());
            }
        }
        if (action.equals("searchJourney")) {
            journeyDAO jDao = new journeyDAO();
            String price = request.getParameter("form_Search_Routes-Price");
            String carType = request.getParameter("cartype");
            String time = request.getParameter("form_Search_Routes-Time");
            String routeId_raw = request.getParameter("routeid");
            
            request.setAttribute("carType", carType);
            request.setAttribute("price_raw", price);
            request.setAttribute("rId", routeId_raw);
            try {
                int routeId = Integer.parseInt(routeId_raw);
                List<journeyDTO> journeyListDTO = jDao.list(0, routeId, 0, null, null, price, time, carType);
                if (price == "") {
                    price = null;
                }
                if (carType == "") {
                    carType = null;
                }
                if (time == "") {
                    time = null;
                }
                if (journeyListDTO.isEmpty()) {
                    
                    request.getRequestDispatcher("adminlist?action=journey").forward(request, response);
                }
                request.setAttribute("time", time);
                
                routeDAO rDao = new routeDAO();
                List<routeDTO> list = rDao.list(0, 0, 0, false);
                request.setAttribute("listroute", list);
                request.setAttribute("list", journeyListDTO);
                request.getRequestDispatcher("adminListJourneyCRUD.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println("" + e.getMessage());
            }
        }
        if (action.equals("searchOrder")) {
            HttpSession session = request.getSession();
            String condition = request.getParameter("form_Search_Routes-Time");
            String routeID_raw = request.getParameter("form_Search_Routes-ID");
            try {
                int routeID = Integer.parseInt(routeID_raw);
                orderDAO oDao = new orderDAO();
                List<orderDTO> orderListDTO = oDao.listSort(condition, routeID);
                routeDAO rDao = new routeDAO();
                List<routeDTO> list = rDao.list(0, 0, 0, false);
                request.setAttribute("listroute", list);
                request.setAttribute("list", orderListDTO);
                request.setAttribute("condition", condition);
                request.setAttribute("routeID", routeID);
                if(session.getAttribute("account")!=null){
                    userDTO udto = (userDTO) session.getAttribute("account");
                    if(udto.getRoleID()==2){
                          request.getRequestDispatcher("history.jsp").forward(request, response);
                    }
                }
                
                request.getRequestDispatcher("adminListOrderCRUD.jsp").forward(request, response);
            } catch (Exception e) {
            }
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
