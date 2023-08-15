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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import journey.journeyDAO;
import journey.journeyDTO;
import order.orderDAO;
import order.orderDTO;
import route.routeDAO;
import route.routeDTO;
import seat.seatDTO;
import user.userDTO;

/**
 *
 * @author Admin
 */
public class FindRouteController extends HttpServlet {

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
            out.println("<title>Servlet FindRouteController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FindRouteController at " + request.getContextPath() + "</h1>");
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
//lay thong tin tu form o trang hom
        HttpSession session = request.getSession();
        if(session.getAttribute("account")==null){
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        String dest_raw = request.getParameter("form_Search_Routes-Destination");

        String start_raw = request.getParameter("form_Search_Routes-Start");
        String depday_raw = request.getParameter("form_Search_Routes-Date");

        //lay date
        Date depday = null;
        if (request.getAttribute("form_Search_Routes-Date") != null && request.getAttribute("form_Search_Routes-Date") != "") {
            String depdate = (String) request.getAttribute("form_Search_Routes-Date");
            depday = Date.valueOf(depdate);
        } else {
            depday = Date.valueOf(depday_raw);
        }
        //
        routeDAO l = new routeDAO();
        deptimeDAO d = new deptimeDAO();
        carDAO c = new carDAO();
//lay list price, time, car de them vao sort
        List<routeDTO> rlist = l.list(0, 0, 0, true);

        for (int i = 0; i < rlist.size(); i++) {
            if(i== rlist.size()-1){
                break;
            }
            rlist.remove(i + 1);
            
            
        }
        List<deptimeDTO> dlist = d.list(0, 0);
        List<carDTO> clist = c.list(0, null, null, null, false);
        request.setAttribute("rlist", rlist);
        request.setAttribute("dlist", dlist);

        // lay cartype
        carDTO cartype1 = clist.get(0);
        carDTO cartype2 = clist.get(clist.size() - 1);
        clist.removeAll(clist);
        clist.add(cartype1);
        clist.add(cartype2);
        request.setAttribute("clist", clist);

        journeyDAO jd = new journeyDAO();
        routeDAO r = new routeDAO();

        List<journeyDTO> listJ = new ArrayList<>();
        int start = 0;

        int dest = 0;
        int routeIDSort = 0;

        if (request.getAttribute("form_Search_Routes-Start") != null && request.getAttribute("form_Search_Routes-Start") != "") {
            start = Integer.parseInt(request.getAttribute("form_Search_Routes-Start").toString());
        } else {
            start = Integer.parseInt(start_raw);
        }

        if (request.getAttribute("form_Search_Routes-Destination") != null && request.getAttribute("form_Search_Routes-Destination") != "") {
            dest = Integer.parseInt(request.getAttribute("form_Search_Routes-Destination").toString());
        } else {
            dest = Integer.parseInt(dest_raw);
        }

        listJ = jd.list(0, r.list(0, start, dest, false).get(0).getRouteID(), 0, depday, null, null, null, null);

        request.setAttribute("depday_raw", depday_raw);

 

        int seatLeft = 0;
        int i = 0;
        orderDAO o = new orderDAO();
        userDTO udto = (userDTO) session.getAttribute("account");
        if (udto.getRoleID() == 1) {
            if(listJ.isEmpty()){
                request.setAttribute("notfound", "error");
    request.setAttribute("error_list", "empty");
      request.getRequestDispatcher("listlocation").forward(request, response);
}
            orderDTO osdto = (orderDTO) session.getAttribute("orderDTO");
            o.deleteOrderDetailM(osdto.getOrderID());

        }
        for (journeyDTO dTO : listJ) {

            seatLeft = dTO.getListSeat().size();
            for (seatDTO sdTO : dTO.getListSeat()) {

                if (!o.list(0, 0, 0, 0, dTO.getJourneyID(), sdTO.getSeatID(), null, null).isEmpty()) {
                    seatLeft--;
                    sdTO.setSeatNum("X");

                }
            }
            dTO.setSeatLeft(seatLeft);

        }
if(listJ.isEmpty()){
    request.setAttribute("error_list", "empty");
      request.getRequestDispatcher("listlocation").forward(request, response);
}
        //rq date
        request.setAttribute("listj", listJ);
        request.getRequestDispatcher("route.jsp").forward(request, response);

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
        String price_raw = request.getParameter("form_Search_Routes-Price");
        String time_raw = request.getParameter("form_Search_Routes-Time");
        String carType = request.getParameter("coach-type");
        String route_sort = request.getParameter("routeID_sort");
        String depday_sort = request.getParameter("deptime_sort");
        String depday_raw = request.getParameter("depday_raw");

        if (carType == "") {
            carType = null;
        }
        if (time_raw == "") {
            time_raw = null;
        }
        if (price_raw == "") {
            price_raw = null;
        }

        routeDAO l = new routeDAO();
        routeDTO r = new routeDTO();

        deptimeDAO d = new deptimeDAO();
        carDAO c = new carDAO();

        Date depday = Date.valueOf(depday_raw);

//lay list price, time, car de them vao sort
        List<routeDTO> rlist = l.list(0, 0, 0, true);

        for (int i = 0; i < rlist.size(); i++) {
               if(i== rlist.size()-1){
                break;
            }
            rlist.remove(i + 1);
        }
        List<deptimeDTO> dlist = d.list(0, 0);
        List<carDTO> clist = c.list(0, null, null, null, false);
        request.setAttribute("rlist", rlist);
        request.setAttribute("dlist", dlist);

        // lay cartype
        carDTO cartype1 = clist.get(0);
        carDTO cartype2 = clist.get(clist.size() - 1);
        clist.removeAll(clist);
        clist.add(cartype1);
        clist.add(cartype2);
        request.setAttribute("clist", clist);

        journeyDAO jd = new journeyDAO();

        List<journeyDTO> listJ = new ArrayList<>();

        int routeIDSort = Integer.parseInt(route_sort);

        listJ = jd.list(0, routeIDSort, 0, depday, null, price_raw, time_raw, carType);

        request.setAttribute("price_raw", price_raw);
        request.setAttribute("time_raw", time_raw);
        request.setAttribute("carType", carType);

        request.setAttribute("depday_raw", depday_raw);

        int seatLeft = 0;
        int i = 0;
        orderDAO o = new orderDAO();
        for (journeyDTO dTO : listJ) {

            seatLeft = dTO.getListSeat().size();
            for (seatDTO sdTO : dTO.getListSeat()) {

                if (!o.list(0, 0, 0, 0, dTO.getJourneyID(), sdTO.getSeatID(), null, null).isEmpty()) {
                    seatLeft--;
                    sdTO.setSeatNum("X");

                }

            }
            dTO.setSeatLeft(seatLeft);

        }

        request.setAttribute("listj", listJ);

        request.getRequestDispatcher("route.jsp").forward(request, response);

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
