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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import journey.journeyDAO;
import journey.journeyDTO;
import location.locationDAO;
import location.locationDTO;
import order.orderDAO;
import order.orderDTO;
import route.routeDAO;
import route.routeDTO;

/**
 *
 * @author USER
 */
@MultipartConfig
@WebServlet(name = "adminCRUD_Edit", urlPatterns = {"/adminedit"})
public class adminCRUD_Edit extends HttpServlet {

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
            out.println("<title>Servlet adminCRUD_Edit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminCRUD_Edit at " + request.getContextPath() + "</h1>");
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

        locationDAO lDao = new locationDAO();
        String msg = null;
        String locationSearch = request.getParameter("locationSearch");
        String action = request.getParameter("action");

        if (action.equals("editRoute")) {
            int routeId = Integer.parseInt(request.getParameter("routeId"));
            journeyDAO jd = new journeyDAO();
            locationDAO l = new locationDAO();
            routeDAO rDAO = new routeDAO();
            deptimeDAO dDao = new deptimeDAO();
            List<deptimeDTO> depListDto = dDao.list(0, 0);
            routeDTO rDTO = rDAO.list(routeId, 0, 0, false).get(0);

            List<locationDTO> locListDto = l.list(0, false);
            List<journeyDTO> jListDto = jd.list(0, 0, 0, null, null, null, null, null);

            if (rDTO.getRouteStartLocation().isStatus() == false) {
                msg = "Can not edit " + rDTO.getRouteStartLocation().getLocationCity() + " please change location status to available in location management";
                request.setAttribute("errorValue1", "alert1");
                request.setAttribute("startError", msg);

            }

            if (rDTO.getRouteDestLocation().isStatus() == false) {
                msg = "Can not edit " + rDTO.getRouteDestLocation().getLocationCity() + " please change location status to available in location management";
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
                        request.getRequestDispatcher("adminlist?action=route").forward(request, response);
                    }
                    if (bool >= 0) {
                        msg = "exist";
                        request.setAttribute("existError", msg);
                        request.getRequestDispatcher("adminlist?action=route").forward(request, response);
                    }

                }
            }

            request.setAttribute("listlocation", locListDto);
            request.setAttribute("listdep", depListDto);
            request.setAttribute("routeDto", rDTO);
            request.getRequestDispatcher("editRoute.jsp").forward(request, response);

        }

        if (action.equals("journey")) {
            String journeyID_raw = request.getParameter("journeyID");

            try {

                journeyDAO jdao = new journeyDAO();
                routeDAO rdao = new routeDAO();
                deptimeDAO ddao = new deptimeDAO();
                carDAO cdao = new carDAO();
                orderDAO odao = new orderDAO();
                int journey = Integer.parseInt(journeyID_raw);

                for (orderDTO dTO : odao.list(0, 0, 0, 0, 0, 0, null, null)) {

                    if (dTO.getJourneyID().getJourneyID() == journey) {
                        msg = "exist";
                        request.setAttribute("check_exist", msg);
                        request.getRequestDispatcher("adminlist?action=journey").forward(request, response);
                    }

                }
                journeyDTO jdto = jdao.list(journey, 0, 0, null, null, null, null, null).get(0);

                List<routeDTO> rlist = rdao.list(0, 0, 0, true);
                List<deptimeDTO> dlist = ddao.list(0, 0);
                List<carDTO> clist = cdao.list(0, null, null, null, true);

                request.setAttribute("rlist", rlist);
                request.setAttribute("dlist", dlist);
                request.setAttribute("clist", clist);

                request.setAttribute("journeyID", journey);
                request.setAttribute("routeID", jdto.getRouteID().getRouteID());
                request.setAttribute("date", jdto.getJourneyDepDay());
                request.setAttribute("depID", jdto.getDepID().getDepID());
                request.setAttribute("carID", jdto.getCarID().getCarID());

                request.getRequestDispatcher("editJourney.jsp").forward(request, response);
            } catch (Exception e) {

            }
        }
        if (action.equals("editLocation")) {
            int locationID = Integer.parseInt(request.getParameter("locationID"));
            locationDAO locDao = new locationDAO();
            locationDTO locDto = locDao.list(locationID, false).get(0);
            journeyDAO jd = new journeyDAO();
            List<journeyDTO> jListDto = jd.list(0, 0, 0, null, null, null, null, null);
            int locId = Integer.parseInt(request.getParameter("locationID"));

            for (journeyDTO dTO : jListDto) {
                if (dTO.getRouteID().getRouteStartLocation().getLocationID() == locId || dTO.getRouteID().getRouteDestLocation().getLocationID() == locId) {
                    java.util.Date currentDate = new java.util.Date();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String dateString = dateFormat.format(dTO.getJourneyDepDay());
                    String dateString_current = dateFormat.format(currentDate);

                    int bool = dTO.getJourneyDepDay().compareTo(currentDate);

                    if (dateString.equals(dateString_current)) { // Use equals() for equality check
                        msg = "exist";
                        request.setAttribute("existError", msg);
                        request.getRequestDispatcher("adminlist?action=location").forward(request, response);
                    }

                    if (bool >= 0) { // Include the condition for equality
                        msg = "exist";
                        request.setAttribute("existError", msg);
                        request.getRequestDispatcher("adminlist?action=location").forward(request, response);
                    }
                    request.getRequestDispatcher("adminlist?action=location").forward(request, response);

                }
            }
            request.setAttribute("locEdit", locDto);
            request.getRequestDispatcher("editLocation.jsp").forward(request, response);
        }

        if (action.equals("car")) {
            int carId = Integer.parseInt(request.getParameter("carId"));
            carDAO carDao = new carDAO();
            carDTO carDto = carDao.list(carId, null, null, null, false).get(0);
            journeyDAO jd = new journeyDAO();

            List<journeyDTO> jListDto = jd.list(0, 0, 0, null, null, null, null, null);
            int carid = Integer.parseInt(request.getParameter("carId"));

            for (journeyDTO dTO : jListDto) {
                if (dTO.getCarID().getCarID() == carid) {
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

            request.setAttribute("carEdit", carDto);
            request.getRequestDispatcher("editCar.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        String msg = null;

        if (action.equals("editRoute")) {
            String userId_raw = request.getParameter("routeId");
            String start_raw = request.getParameter("routeStart");
            String dest_raw = request.getParameter("routeDest");
            String routeTime_raw = request.getParameter("routeTime");
            String routePrice_raw = request.getParameter("routePrice");
            String formattedPrice = routePrice_raw.replaceAll("\\.0$", "");
            String routeLenght_raw = request.getParameter("routeLength");
            try {
                int routeId = Integer.parseInt(userId_raw);
                int start = Integer.parseInt(start_raw);
                int dest = Integer.parseInt(dest_raw);
                int routeTime = Integer.parseInt(routeTime_raw);
                int routePrice = Integer.parseInt(formattedPrice);
                int routeLenght = Integer.parseInt(routeLenght_raw);
                routeDAO rDao = new routeDAO();
              
                rDao.updateRoute(start, dest, routeTime, routePrice, routeLenght, routeId);  
                  List<routeDTO> list = rDao.list(0, 0, 0, true);
                request.setAttribute("listroute", list);
                request.getRequestDispatcher("adminListRouteCRUD.jsp").forward(request, response);

            } catch (Exception e) {
                System.out.println("" + e.getMessage());
            }
        }
        if (action.equals("editLoc")) {
            locationDAO lDao = new locationDAO();
            journey.journeyDAO jd = new journeyDAO();
            List<journeyDTO> jListDto = jd.list(0, 0, 0, null, null, null, null, null);
            try {
                int locId = Integer.parseInt(request.getParameter("locId"));
                locationDTO lDto = lDao.list(locId, false).get(0);

                request.setAttribute("locEdit", lDto);

                for (journeyDTO dTO : jListDto) {
                    if (dTO.getRouteID().getRouteStartLocation().getLocationID() == locId || dTO.getRouteID().getRouteDestLocation().getLocationID() == locId) {
                        java.util.Date currentDate = new java.util.Date();
                        int bool = dTO.getJourneyDepDay().compareTo(currentDate);
                        if (bool > 0) {
                            msg = "Can't edit this station";
                            request.setAttribute("cantEditError", msg);
                            request.getRequestDispatcher("editLocation.jsp").forward(request, response);
                        }
                        request.getRequestDispatcher("editLocation.jsp").forward(request, response);

                    }
                }

                String locCity = request.getParameter("locCity");
                String locStation = request.getParameter("locStation");
//              Cai nay de lay object tu <input type="file"/>
                Part filePart = request.getPart("locImage");
//              Cai nay de lay cai value cua cai content-disposition cai content-disposition  la cai nay enctype="multipart/form-data" ben the input
                String contentDisposition = filePart.getHeader("content-disposition");
//                Cai nay de lay duoi file ra de check xem co hop le hay ko
                String fileName = getFileExtension(contentDisposition);
//                System.out.println("ten file :" + fileName);
                if (fileName == null || fileName.equals("")) {
                    lDao.updateLocation(locId, locCity, locStation, null);
                    request.getRequestDispatcher("listlocation").forward(request, response);
                }
//                Check neu nhu file nhap vao khong thuoc 1 trong 3 file duoi day thi bao loi
                if (fileName.contains("jpg") || fileName.contains("jpeg") || fileName.contains("png")) {
//                    Dong try nay la de lay doi tuong input stream tu cai doi tuong part de co the doc duoc cai noi dung cua cai part
                    try (InputStream fileInputStream = filePart.getInputStream()) {
//                        dong nay goi phuong thuc convertImageToVarbinary se convert cai doi tuong inputStream thanh Varbinary roi luu vao byteArray
                        byte[] imageData = convertImageToVarbinary(fileInputStream);
                        lDao.updateLocation(locId, locCity, locStation, imageData);
                        request.getRequestDispatcher("listlocation").forward(request, response);
                    } catch (Exception e) {
//                        In ra loi
                        System.out.println("Loi o trong editLoc " + e.getMessage());
                    }
                } else {
                    request.setAttribute("locEdit", lDto);
                    msg = "File not support only support jpeg jpg png";
                    request.setAttribute("notSupportErr", msg);
                    request.getRequestDispatcher("editLocation.jsp").forward(request, response);
                }

            } catch (Exception e) {
                // Log the exception stack trace using a logging framework
                System.out.println("Loi o editLoc adminCRUD_Edit" + e.getMessage());
            }
        }
        if (action.equals("journey")) {

            String journeyID_raw = request.getParameter("journeyID");
            String routeID_raw = request.getParameter("routeID");
            String date_raw = request.getParameter("date");
            String deptime_raw = request.getParameter("deptime");
            String carID_raw = request.getParameter("carID");

            try {

                journeyDAO jdao = new journeyDAO();
                routeDAO rdao = new routeDAO();
                deptimeDAO ddao = new deptimeDAO();
                carDAO cdao = new carDAO();
                orderDAO odao = new orderDAO();

                int journey = Integer.parseInt(journeyID_raw);
                int route = Integer.parseInt(routeID_raw);
                Date date = Date.valueOf(date_raw);
                int deptime = Integer.parseInt(deptime_raw);
                int carID = Integer.parseInt(carID_raw);
                double price = 0;

                carDTO cdto = cdao.list(carID, null, null, null, false).get(0);
                routeDTO rdto = rdao.list(route, 0, 0, false).get(0);
                price = rdto.getRoutePrice();

                List<journeyDTO> jList = jdao.list(0, 0, carID, date, null, null, null, null);
                List<journeyDTO> jdtol = jdao.list(0, 0, 0, null, null, null, null, null);
                List<routeDTO> rdtol = rdao.list(0, 0, 0, true);
                List<deptimeDTO> ddtol = ddao.list(0, 0);

                if (cdto.getCabin().equals("T")) {
                    price += 50000;
                }
                if (cdto.getWifi().equals("T")) {
                    price += 25000;
                }
                if (cdto.getCarType().equals("1F")) {
                    price += 25000;
                }
                if (cdto.getCarType().equals("2F")) {
                    price -= 25000;
                }

                if (jList.isEmpty()) {

                    jdao.update(journey, route, deptime, carID, price, date);

                    request.getRequestDispatcher("adminlist?action=journey").forward(request, response);

                } else {
                    if (jList.get(0).getJourneyID() == journey) {

                        jdao.update(journey, route, deptime, carID, price, date);

                        request.getRequestDispatcher("adminlist?action=journey").forward(request, response);

                    }

                    msg = "The car was taken in day";
                    request.setAttribute("error_car", msg);
                    request.setAttribute("jlist", jdtol);
                    request.setAttribute("rlist", rdtol);
                    request.setAttribute("dlist", ddtol);

                    request.getRequestDispatcher("editJourney.jsp").forward(request, response);
                }
            } catch (Exception e) {
            }
        }

        if (action.equals("car")) {
            String carType = request.getParameter("carType");
            String carCabin = request.getParameter("carCabin");
            String carWifi = request.getParameter("carWifi");
            int carID = Integer.parseInt(request.getParameter("carID"));
            if (carCabin != null) {
                carCabin = "T";
            } else {
                carCabin = "F";
            }
            if (carWifi != null) {
                carWifi = "T";
            } else {
                carWifi = "F";
            }
            carDAO cDao = new carDAO();
            cDao.carUpdate(carType, carCabin, carWifi, carID);
            carDAO c = new carDAO();
            List<carDTO> list = c.list(0, null, null, null, false);
            request.setAttribute("listcar", list);
            request.getRequestDispatcher("adminListCarCRUD.jsp").forward(request, response);
        }

    }

    public static byte[] convertImageToVarbinary(InputStream imageInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = imageInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static String getFileExtension(String fileName) {
        if (fileName != null && fileName.lastIndexOf(".") != -1) {
            return fileName.substring(fileName.lastIndexOf(".") + 1)
                    .toLowerCase()
                    .replaceAll("\"", "");
        }
        return "";
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
