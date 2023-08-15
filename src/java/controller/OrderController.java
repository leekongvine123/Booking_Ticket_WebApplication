/*
 * To change this license header, choose License Headers in Project Properties.
Fhfh. * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import OrderDeletetion.OrderDeletion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import journey.journeyDAO;
import journey.journeyDTO;
import order.orderDAO;
import order.orderDTO;
import seat.seatDAO;
import seat.seatDTO;
import user.Mail;
import user.userDAO;
import user.userDTO;

/**
 *
 * @author Admin
 */
public class OrderController extends HttpServlet {

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
            out.println("<title>Servlet OrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderController at " + request.getContextPath() + "</h1>");
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

        String journeyID_raw = request.getParameter("journeyID");
        String[] seatarr = request.getParameterValues("seatarr");

        int count = 0;
        double totalPrice = 0;
        List<seatDTO> list = new ArrayList<>();
        List<orderDTO> oList = new ArrayList<>();
        List<seatDTO> dList = new ArrayList<>();
        seatDAO s = new seatDAO();

        int journeyID = Integer.parseInt(journeyID_raw);
        journeyDAO j = new journeyDAO();
        journeyDTO jdto = j.list(journeyID, 0, 0, null, null, null, null, null).get(0);
        List<journeyDTO> jlist = j.list(0, jdto.getRouteID().getRouteID(), 0, (Date) jdto.getJourneyDepDay(), null, null, null, null);
      
        if (seatarr== null) {
            request.setAttribute("depday_raw", jdto.getJourneyDepDay());
            request.setAttribute("listj", jlist);
            request.getRequestDispatcher("route.jsp").forward(request, response);
        }
        for (int i = 0; i < seatarr.length; i++) {
            count++;

            list.add(s.list(Integer.parseInt(seatarr[i])).get(0));
        }
  totalPrice = count * jdto.getPrice();
        userDTO udto = (userDTO) session.getAttribute("account");
        orderDAO o = new orderDAO();

        String seatPicked = "";
        for (seatDTO dTO : list) {

            if (!o.list(0, 0, 0, 0, journeyID, dTO.getSeatID(), null, null).isEmpty()) {

                seatPicked += ":" + dTO.getSeatNum();

            }
        }
        int seatLeft = 0;
        for (journeyDTO dTO : jlist) {

            seatLeft = dTO.getListSeat().size();
            for (seatDTO sdTO : dTO.getListSeat()) {

                if (!o.list(0, 0, 0, 0, dTO.getJourneyID(), sdTO.getSeatID(), null, null).isEmpty()) {
                    seatLeft--;
                    sdTO.setSeatNum("X");

                }

            }
            dTO.setSeatLeft(seatLeft);

        }
        if (!seatPicked.equals("")) {

            request.setAttribute("seatPicked", seatPicked);
            request.setAttribute("depday_raw", jdto.getJourneyDepDay());
            request.setAttribute("listj", jlist);
            request.getRequestDispatcher("route.jsp").forward(request, response);

        }

        LocalDate currentDate = LocalDate.now();

        orderDTO odto = orderDTO.builder()
                .userID(udto)
                .journeyID(jdto)
                .listSeat(list)
                .totalPrice(totalPrice)
                .quantity(count)
                .status("p")
                .orderDob(Date.valueOf(currentDate)).build();

        if (odto != null) {

            for (seatDTO dTO : list) {

                if (!o.list(0, 0, 0, 0, journeyID, dTO.getSeatID(), null, null).isEmpty()) {

                    dList.add(dTO);

                }
            }
            request.setAttribute("dList", dList);
            if (!dList.isEmpty()) {
                request.setAttribute("form_Search_Routes-Destination", jdto.getRouteID().getRouteDestLocation().getLocationID());
                request.setAttribute("form_Search_Routes-Start", jdto.getRouteID().getRouteStartLocation().getLocationID());
                request.setAttribute("form_Search_Routes-Date", jdto.getJourneyDepDay());

                request.getRequestDispatcher("findroute").forward(request, response);

            }
            if (udto.getRoleID() == 1) {
                userDAO udao = new userDAO();

                orderDTO osdto = (orderDTO) session.getAttribute("orderDTO");
                userDTO cdto = udao.list(osdto.getUserID().getUserID(), null, null, null, true).get(0);
                o.deleteOrderDetailM(osdto.getOrderID());
                for (seatDTO dTO : list) {
                    o.insertOrderDetail(osdto.getOrderID(), dTO.getSeatID());
                }
                o.updateOrderAdmin(osdto.getOrderID(), osdto.getJourneyID().getJourneyID(), totalPrice, count);
                Mail email = new Mail();
                email.sendEmail(cdto.getUserMail(), "Update Successful", "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "    <head>\n"
                        + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                        + "        <title>Nha xe NVT</title>\n"
                        + "        <style>\n"
                        + "            body { \n"
                        + "                font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;\n"
                        + "                background-color: yellow;\n"
                        + // Updated background color
                        "                margin: 0px 150px 0px 150px;\n"
                        + "            }\n"
                        + "        </style>\n"
                        + "    </head>\n"
                        + "    <body>\n"
                        + "        <h1 style=\"color: green; \n"
                        + "            font-size: 25px; \n"
                        + "            text-align: center; \n"
                        + "            background: white;\n"
                        + "            border-radius: 30px;\n"
                        + "            margin: 70px 550px 0 550px;\n"
                        + "            padding: 15px 0 15px 0;\"> Nha xe NVT\n"
                        + "        </h1>\n"
                        + "        \n"
                        + "        <div style=\"font-size: 15px;\n"
                        + "                text-align: center;\n"
                        + "                color: red;\n"
                        + "                padding: 15px 0 15px 0;\"> Update successfully\n"
                        + "        </div>\n"
                        + "        <div style=\"display: flex; \n"
                        + "             padding: 0px 500px ;\n"
                        + "             line-height: 75px;\">\n"
                        + "            <div style=\"font-size: 13px;\n"
                        + "                    padding: 80px 0 20px 0;\">\n"
                        + "                <div style=\"margin-bottom: 35px;\n"
                        + "                                width: 60%;\n"
                        + "                        \n"
                        + "                                float: left;\">                  \n"
                        + "                    <span> OrderDate:" + odto.getOrderDob() + " </span>\n"
                        + "                    <span> Route:" + odto.getJourneyID().getRouteID().getRouteStartLocation().getLocationCity() + "=>" + odto.getJourneyID().getRouteID().getRouteDestLocation().getLocationCity() + "</span>\n"
                        + "                    <span> Price: " + odto.getTotalPrice() + "  </span>  \n"
                        + "                </div>\n"
                        + "            </div>   \n"
                        + "            <div style=\"font-size: 13px;\n"
                        + "                    padding: 80px 0px 20px 50px;\">\n"
                        + "                <div style=\"margin-bottom: 35px;\n"
                        + "                                width: 60%;\n"
                        + "                \n"
                        + "                                float: right;\">                  \n"
                        + "                    <span> Departure date:" + odto.getJourneyID().getJourneyDepDay() + " </span>\n"
                        + "                    <span> Departure time: " + odto.getJourneyID().getDepID().getDepTime() + ":00 </span>\n"
                        + "                    <span> License plate: " + odto.getJourneyID().getCarID().getLicensePlate() + "  </span>   \n"
                        + "                </div>\n"
                        + "               \n"
                        + "            </div>\n"
                        + "        </div>\n"
                        + "        <div style = \"text-align: center;\n"
                        + "             font-size: 15px;\n"
                        + "             padding: 10px;\n"
                        + "             margin-bottom: 90px;\"> Thank you for using our services - Please take time to check your ticket information <a href=\"http://localhost:8080/A_PRJ301_GR10/history\">here</a> </div>\n"
                        + "            \n"
                        + "        \n"
                        + "    </body>\n"
                        + "\n"
                        + "    <footer>\n"
                        + "    <div style=\"font-size: 10px;\n"
                        + "            background-color: white;\n"
                        + "            border-radius: 50px;\n"
                        + "            \n"
                        + "            padding: 0px 50px 0px 50px;\">\n"
                        + "        <p>Phone: 094299999</p>\n"
                        + "        <p>Công ty cổ phần nhà xe Nam Vinh NaVi Travels</p>\n"
                        + "        <p>Email: NaVitravels@gmail.com</p>\n"
                        + "        <p>Địa chỉ: Lô E2a-7, Đường D1, Khu Công nghệ cao, P.Long Thạnh Mỹ, Tp. Thủ Đức, TP.HCM.</p>\n"
                        + "    </div> \n"
                        + "        \n"
                        + "</html>\n"
                        + "    ");

                request.getRequestDispatcher("adminlist?action=order").forward(request, response);

            } else {

                o.insertOrder(odto);
                request.setAttribute("listseat", odto.getListSeat());
                oList = o.list(0, 0, 0, 0, 0, 0, null, null);
                odto = oList.get(oList.size() - 1);
                OrderDeletion.scheduleOrderDeletion(odto, 30);
                for (seatDTO dTO : list) {
                    o.insertOrderDetail(odto.getOrderID(), dTO.getSeatID());
                }

                request.setAttribute("totalPrice", totalPrice);
                request.setAttribute("order", odto);

                request.getRequestDispatcher("ticketinf.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String orderID_raw = request.getParameter("orderID");
        userDTO udto = (userDTO) session.getAttribute("account");

        int orderID = Integer.parseInt(orderID_raw);

        orderDAO odao = new orderDAO();
        Mail email = new Mail();
        orderDTO odto = odao.list(orderID, 0, 0, 0, 0, 0, null, null).get(0);
        odao.updateOrder(odto);
        email.sendEmail(udto.getUserMail(), "Booking Sucessful", "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n"
                + "        <title>Nha xe NVT</title>\n"
                + "        <style>\n"
                + "            body { \n"
                + "                font-family:'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;\n"
                + "                background-color: lightgrey;\n"
                + "                margin: 0px 150px 0px 150px;\n"
                + "            }\n"
                + "        </style>\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <h1 style=\"color: green; \n"
                + "            font-size: 25px; \n"
                + "            text-align: center; \n"
                + "            background:white;\n"
                + "            border-radius:30px;\n"
                + "            margin: 70px 550px 0 550px;\n"
                + "            padding: 15px 0 15px 0;\"> Nha xe NVT\n"
                + "        </h1>\n"
                + "        \n"
                + "        <div style=\"font-size: 15px;\n"
                + "                text-align: center;\n"
                + "                color: red;\n"
                + "                padding: 15px 0 15px 0;\"> Booking successfully\n"
                + "        </div>\n"
                + "        <div style=\"display: flex; \n"
                + "             padding: 0px 500px ;\n"
                + "             line-height: 75px;\">\n"
                + "            <div style=\"font-size: 13px;\n"
                + "                    padding: 80px 0 20px 0;\">\n"
                + "                <div style=\"margin-bottom: 35px;\n"
                + "                                width: 60%;\n"
                + "                        \n"
                + "                                float: left;\">                  \n"
                + "                    <span> OrderDate:" + odto.getOrderDob() + " </span>\n"
                + "                    <span> Route:" + odto.getJourneyID().getRouteID().getRouteStartLocation().getLocationCity() + "=>" + odto.getJourneyID().getRouteID().getRouteDestLocation().getLocationCity() + "</span>\n"
                + "                    <span> Price: " + odto.getTotalPrice() + "  </span>  \n"
                + "                </div>\n"
                + "            </div>   \n"
                + "            <div style=\"font-size: 13px;\n"
                + "                    padding: 80px 0px 20px 50px;\">\n"
                + "                <div style=\"margin-bottom: 35px;\n"
                + "                                width: 60%;\n"
                + "                \n"
                + "                                float: right;\">                  \n"
                + "                    <span> Departure date:" + odto.getJourneyID().getJourneyDepDay() + " </span>\n"
                + "                    <span> Departure time: " + odto.getJourneyID().getDepID().getDepTime() + ":00 </span>\n"
                + "                    <span> License plate: " + odto.getJourneyID().getCarID().getLicensePlate() + "  </span>   \n"
                + "                </div>\n"
                + "               \n"
                + "            </div>\n"
                + "        </div>\n"
                + "        <div style = \"text-align: center;\n"
                + "             font-size: 15px;\n"
                + "             padding: 10px;\n"
                + "             margin-bottom: 90px;\"> Thank you for using our services - Please take time to check your ticket information <a href=\"http://localhost:8080/A_PRJ301_GR10/history\">here</a> </div>\n"
                + "            \n"
                + "        \n"
                + "    </body>\n"
                + "\n"
                + "    <footer>\n"
                + "    <div style=\"font-size: 10px;\n"
                + "            background-color: white;\n"
                + "            border-radius: 50px;\n"
                + "            \n"
                + "            padding: 0px 50px 0px 50px;\">\n"
                + "        <p>Phone: 094299999</p>\n"
                + "        <p>Công ty cổ phần nhà xe Nam Vinh NaVi Travels</p>\n"
                + "        <p>Email: NaVitravels@gmail.com</p>\n"
                + "        <p>Địa chỉ: Lô E2a-7, Đường D1, Khu Công nghệ cao, P.Long Thạnh Mỹ, Tp. Thủ Đức, TP.HCM.</p>\n"
                + "    </div> \n"
                + "        \n"
                + "</html>\n"
                + "    ");

        response.sendRedirect("listlocation");

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
