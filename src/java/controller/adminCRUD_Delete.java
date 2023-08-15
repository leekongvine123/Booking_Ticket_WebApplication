/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import car.carDAO;
import deptime.deptimeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import journey.journeyDAO;
import journey.journeyDTO;
import order.orderDAO;
import order.orderDTO;
import location.locationDAO;
import location.locationDTO;
import route.routeDAO;
import route.routeDTO;

/**
 *
 * @author USER
 */
public class adminCRUD_Delete extends HttpServlet {

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
            out.println("<title>Servlet adminCRUD_Delete</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminCRUD_Delete at " + request.getContextPath() + "</h1>");
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

        String msg = null;
        if (action.equals("journey")) {
            String journeyID_raw = request.getParameter("journeyID");
            try {
                int journeyID = Integer.parseInt(journeyID_raw);
                journeyDAO jdao = new journeyDAO();

                orderDAO odao = new orderDAO();

                for (orderDTO dTO : odao.list(0, 0, 0, 0, 0, 0, null, null)) {
                    if (dTO.getJourneyID().getJourneyID() == journeyID) {
                        msg = "exist";
                        request.setAttribute("check_exist", msg);
                        request.getRequestDispatcher("adminlist?action=journey").forward(request, response);
                    }

                }

                jdao.delete(journeyID);

                request.getRequestDispatcher("adminlist?action=journey").forward(request, response);

            } catch (Exception e) {
            }

        }
        if (action.equals("order")) {
            String orderID_raw = request.getParameter("orderID");
            try {
                int orderID = Integer.parseInt(orderID_raw);
                orderDAO odao = new orderDAO();
                journeyDAO jdao = new journeyDAO();

                orderDTO odto = odao.list(orderID, 0, 0, 0, 0, 0, null, null).get(0);
                for (journeyDTO dTO : jdao.list(0, 0, 0, null, null, null, null, null)) {
                    if (odto.getJourneyID().getJourneyID() == dTO.getJourneyID()) {
                        java.util.Date currentDate = new java.util.Date();

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = dateFormat.format(dTO.getJourneyDepDay());
                        String dateString_current = dateFormat.format(currentDate);

                        int bool = dTO.getJourneyDepDay().compareTo(currentDate);

                        if (dateString.equals(dateString_current)) { // Use equals() for equality check
                            msg = "exist";
                            request.setAttribute("existError", msg);
                            request.getRequestDispatcher("adminlist?action=order").forward(request, response);
                        }

                        if (bool < 0) {
                            msg = "exist";
                            request.setAttribute("existError", msg);
                            request.getRequestDispatcher("adminlist?action=order").forward(request, response);
                        }

                    }
                }
                odao.deleteOrderDetailM(orderID);
                odao.deleteOrderM(orderID);
                request.getRequestDispatcher("adminlist?action=order").forward(request, response);

            } catch (Exception e) {
            }

        }

        if (action.equals("delete")) {
            try {
                locationDAO lDao = new locationDAO();
                int locationId = Integer.parseInt(request.getParameter("locationID"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                journeyDAO jDao = new journeyDAO();
                routeDAO rDao = new routeDAO();
                List<journeyDTO> jListDto = jDao.list(0, 0, 0, null, null, null, null, null);
                List<routeDTO> rListDto = rDao.list(0, 0, 0, false);
                for (journeyDTO dTO : jListDto) {
                    if (dTO.getRouteID().getRouteDestLocation().getLocationID() == locationId || dTO.getRouteID().getRouteStartLocation().getLocationID() == locationId) {
                        java.util.Date currentDate = new java.util.Date();
                        int bool = dTO.getJourneyDepDay().compareTo(currentDate);
                        if (bool >= 0) {
                            msg = "exist";
                            request.setAttribute("existError", msg);
                            request.getRequestDispatcher("adminlist?action=location").forward(request, response);
                        }

                    }

                }
                if (status == true) {
                    lDao.locationStatusUpdate(false, locationId);
                }
                if (status == false) {
                    lDao.locationStatusUpdate(true, locationId);
                }

                for (routeDTO dTO : rListDto) {
                    if (dTO.getRouteDestLocation().getLocationID() == locationId || dTO.getRouteStartLocation().getLocationID() == locationId) {
                        rDao.updateRouteStatus(locationId, locationId, status);
                    }
                }
                request.getRequestDispatcher("adminlist?action=location").forward(request, response);
            } catch (Exception e) {
                System.out.println("" + e.getMessage());
            }

        }

        if (action.equals("deleteCar")) {
            try {
                int carId = Integer.parseInt(request.getParameter("carId"));
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                carDAO cDao = new carDAO();
                journeyDAO jDao = new journeyDAO();

                List<journeyDTO> jListDto = jDao.list(0, 0, 0, null, null, null, null, null);

                for (journeyDTO dTO : jListDto) {
                    if (dTO.getCarID().getCarID() == carId) {
                        java.util.Date currentDate = new java.util.Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = dateFormat.format(dTO.getJourneyDepDay());
                        String dateString_current = dateFormat.format(currentDate);
                        int bool = dTO.getJourneyDepDay().compareTo(currentDate);
                        if (dateString.equals(dateString_current)) { // Use equals() for equality check
                            msg = "exist";
                            request.setAttribute("existError", msg);
                            request.getRequestDispatcher("adminlist?action=car").forward(request, response);
                        }
                        if (bool >= 0) {
                            msg = "exist";
                            request.setAttribute("existError", msg);
                            request.getRequestDispatcher("adminlist?action=car").forward(request, response);
                        }
                    }
                }

                if (status == true) {
                    cDao.carUpdateStatus(carId, false);
                }
                if (status == false) {
                    cDao.carUpdateStatus(carId, true);

                }
                request.getRequestDispatcher("adminlist?action=car").forward(request, response);
            } catch (Exception e) {
                System.out.println("" + e.getMessage());
            }

        }

        if (action.equals("deleteRoute")) {
            int routeId = Integer.parseInt(request.getParameter("routeId"));
            boolean status = Boolean.parseBoolean(request.getParameter("status"));

            journeyDAO jd = new journeyDAO();
            locationDAO l = new locationDAO();
            routeDAO rDAO = new routeDAO();
            deptimeDAO dDao = new deptimeDAO();
            routeDTO rDTO = rDAO.list(routeId, 0, 0, false).get(0);

            List<locationDTO> locListDto = l.list(0, false);
            List<journeyDTO> jListDto = jd.list(0, 0, 0, null, null, null, null, null);

            locationDAO lDao = new locationDAO();
            List<locationDTO> locationListDTO = lDao.list(0, false);
            request.setAttribute("listlocation", locationListDTO);

            if (rDTO.getRouteStartLocation().isStatus() == false) {
                msg = "Can not delete " + rDTO.getRouteStartLocation().getLocationCity() + " please change location status to available in location management";
                request.setAttribute("errorValue1", "alert1");
                request.setAttribute("startError", msg);

            }

            if (rDTO.getRouteDestLocation().isStatus() == false) {
                msg = "Can not delete " + rDTO.getRouteDestLocation().getLocationCity() + " please change location status to available in location management";
                request.setAttribute("errorValue2", "alert2");
                request.setAttribute("destError", msg);
            }

            if (rDTO.getRouteStartLocation().isStatus() == false || rDTO.getRouteDestLocation().isStatus() == false) {
                request.getRequestDispatcher("adminlist?action=route").forward(request, response);
            }

            for (journeyDTO dTO : jListDto) {
                if (dTO.getRouteID().getRouteID() == routeId) {
                    java.util.Date currentDate = new java.util.Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dateString = dateFormat.format(dTO.getJourneyDepDay());
                    String dateString_current = dateFormat.format(currentDate);

                    int bool = dTO.getJourneyDepDay().compareTo(currentDate);

                    if (dateString.equals(dateString_current)) { // Use equals() for equality check
                        msg = "exist";
                        request.setAttribute("existError", msg);
                        routeDAO rDAO_test = new routeDAO();
                        List<routeDTO> routeListDTO = rDAO.list(0, 0, 0, false);
                        request.setAttribute("listroute", routeListDTO);
//                        request.getRequestDispatcher("adminlist?action=route").forward(request, response);
                        request.getRequestDispatcher("adminListRouteCRUD.jsp").forward(request, response);
                    }
                    if (bool >= 0) {
                        msg = "exist";
                        request.setAttribute("existError", msg);
                        List<routeDTO> routeListDTO = rDAO.list(0, 0, 0, false);
                        request.setAttribute("listroute", routeListDTO);
//                        request.getRequestDispatcher("adminlist?action=route").forward(request, response);
                        request.getRequestDispatcher("adminListRouteCRUD.jsp").forward(request, response);
                    }

                    request.getRequestDispatcher("adminListRouteCRUD.jsp").forward(request, response);
                }
            }

            if (status == true) {
                rDAO.updateListRouteStatus(routeId, false);
            }
            if (status == false) {
                rDAO.updateListRouteStatus(routeId, true);
            }

            request.getRequestDispatcher("adminlist?action=route").forward(request, response);
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
