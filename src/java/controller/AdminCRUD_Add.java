/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import car.carDAO;
import car.carDTO;
import static controller.adminCRUD_Edit.convertImageToVarbinary;
import deptime.deptimeDAO;
import deptime.deptimeDTO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
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
import route.routeDAO;
import route.routeDTO;

/**
 *
 * @author USER
 */
@MultipartConfig
@WebServlet(name = "AdminCRUD_Add", urlPatterns = {"/adminadd"})
public class AdminCRUD_Add extends HttpServlet {

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
            out.println("<title>Servlet adminCRUD_AddRoute</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminCRUD_AddRoute at " + request.getContextPath() + "</h1>");
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

        if (action.equals("journey")) {
            journeyDAO jdao = new journeyDAO();
            routeDAO rdao = new routeDAO();
            deptimeDAO ddao = new deptimeDAO();

            List<journeyDTO> jdto = jdao.list(0, 0, 0, null, null, null, null, null);
            List<routeDTO> rdto = rdao.list(0, 0, 0, true);
            List<deptimeDTO> ddto = ddao.list(0, 0);

            request.setAttribute("jlist", jdto);
            request.setAttribute("rlist", rdto);
            request.setAttribute("dlist", ddto);

            request.getRequestDispatcher("addJourney.jsp").forward(request, response);
        }
        if (action.equals("location")) {
            request.getRequestDispatcher("addLocation.jsp").forward(request, response);

        }
        if (action.equals("car")) {
            request.getRequestDispatcher("addCar.jsp").forward(request, response);

        }
        if (action.equals("route")) {

            locationDAO l = new locationDAO();
            List<locationDTO> locListDto = l.list(0, false);
            request.setAttribute("listlocation", locListDto);
            request.getRequestDispatcher("addRoute.jsp").forward(request, response);
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
        String msg = null;
        String action = request.getParameter("action");

        if (action.equals("route")) {
            String start_raw = request.getParameter("routeStart");
            String dest_raw = request.getParameter("routeDest");
            String time_raw = request.getParameter("routeTime");
            String price_raw = request.getParameter("routePrice");
            String length_raw = request.getParameter("routeLength");
            locationDAO l = new locationDAO();
            List<locationDTO> locListDto = l.list(0, false);
 
            request.setAttribute("listlocation", locListDto);
            routeDAO rDao = new routeDAO();
            int start = Integer.parseInt(start_raw);
            int dest = Integer.parseInt(dest_raw);
            int time = Integer.parseInt(time_raw);
            int price = Integer.parseInt(price_raw);
            int length = Integer.parseInt(length_raw);

            if (!rDao.list(0, start, dest, false).isEmpty()) {
                msg = "Route Start and Destination already exist !!";
                request.setAttribute("routeExist", msg);
                request.setAttribute("start", start);
                request.setAttribute("dest", dest);
                request.setAttribute("listlocation", locListDto);

                List<routeDTO> list = rDao.list(0, 0, 0, false);
                request.setAttribute("listroute", list);

                request.getRequestDispatcher("addRoute.jsp").forward(request, response);

            }

            rDao.insertRoute(start, dest, time, price, length);
            List<routeDTO> list = rDao.list(0, 0, 0, false);
            request.setAttribute("listroute", list);
            request.setAttribute("routeStart", start);
            request.setAttribute("routeDest", dest);
            request.getRequestDispatcher("adminListRouteCRUD.jsp").forward(request, response);

        }

        if (action.equals("journey")) {
            String routeID_raw = request.getParameter("routeID");
            String date_raw = request.getParameter("date");
            String deptime_raw = request.getParameter("deptime");
            String carID_raw = request.getParameter("carID");

            try {
                journeyDAO jdao = new journeyDAO();
                routeDAO rdao = new routeDAO();
                deptimeDAO ddao = new deptimeDAO();
                carDAO cdao = new carDAO();

                int route = Integer.parseInt(routeID_raw);
                Date date = Date.valueOf(date_raw);
                int deptime = Integer.parseInt(deptime_raw);
                int carID = Integer.parseInt(carID_raw);
                double price = 0;

                carDTO cdto = cdao.list(carID, null, null, null, false).get(0);
                routeDTO rdto = rdao.list(route, 0, 0, true).get(0);
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
                    journeyDTO jdto = journeyDTO.builder()
                            .routeID(rdto)
                            .carID(cdto)
                            .depID(ddao.list(deptime, 0).get(0))
                            .journeyDepDay(date)
                            .journeyStatus("T")
                            .Price(price).build();

                    jdao.insert(jdto);

                    List<journeyDTO> jList1 = jdao.list(0, 0, 0, null, null, null, null, null);
                    request.setAttribute("list", jList1);
                    request.getRequestDispatcher("adminListJourneyCRUD.jsp").forward(request, response);

                } else {
                    msg = "The car was taken in day";
                    request.setAttribute("error_car", msg);
                    request.setAttribute("jlist", jdtol);
                    request.setAttribute("rlist", rdtol);
                    request.setAttribute("dlist", ddtol);

                    request.getRequestDispatcher("addJourney.jsp").forward(request, response);
                }
            } catch (Exception e) {
                System.out.println("Loi o AdminCRUD action journey" + e.getMessage());
            }

        }

        if (action.equals("location")) {
            locationDAO lDao = new locationDAO();
            try {
                String locationCity = request.getParameter("locationCity");
                String locationStation = request.getParameter("locationStation");
                //              Cai nay de lay object tu <input type="file"/>
                Part filePart = request.getPart("locationImage");
//              Cai nay de lay cai value cua cai content-disposition cai content-disposition  la cai nay enctype="multipart/form-data" ben the input
                String contentDisposition = filePart.getHeader("content-disposition");
//                Cai nay de lay duoi file ra de check xem co hop le hay ko
                String fileName = getFileExtension(contentDisposition);
//                System.out.println("ten file :" + fileName);
                if (fileName == null || fileName.equals("")) {

                    request.getRequestDispatcher("listlocation").forward(request, response);
                }
//                Check neu nhu file nhap vao khong thuoc 1 trong 3 file duoi day thi bao loi
                if (fileName.contains("jpg") || fileName.contains("jpeg") || fileName.contains("png")) {
//                    Dong try nay la de lay doi tuong input stream tu cai doi tuong part de co the doc duoc cai noi dung cua cai part
                    try (InputStream fileInputStream = filePart.getInputStream()) {
//                        dong nay goi phuong thuc convertImageToVarbinary se convert cai doi tuong inputStream thanh Varbinary roi luu vao byteArray
                        byte[] imageData = convertImageToVarbinary(fileInputStream);
                        lDao.insertLocation(locationCity, locationStation, imageData);
                        request.getRequestDispatcher("listlocation").forward(request, response);
                    } catch (Exception e) {
//                        In ra loi
                        System.out.println("Loi o trong editLoc " + e.getMessage());
                    }
                } else {

                    msg = "File not support only support jpeg jpg png";
                    request.setAttribute("notSupportErr", msg);
                    request.getRequestDispatcher("editLocation.jsp").forward(request, response);
                }
            } catch (Exception e) {
                System.out.println("Loi o AdminCRUD_Add location" + e.getMessage());
            }

        }
        if (action.equals("car")) {
            String carType = request.getParameter("carType");
            String carWifi = request.getParameter("carWifi");
            String carCabin = request.getParameter("carCabin");
            carDAO cDao = new carDAO();
            List<carDTO> cListDto = cDao.list(0, null, null, null, false);
            carDTO cDto = cListDto.get(cListDto.size() - 1);

            String licensePlate = "XNNVT" + (cDto.getCarID() + 1);
            if (carWifi != null) {
                carWifi = "T";
            } else {
                carWifi = "F";
            }
            if (carCabin != null) {
                carCabin = "T";
            } else {
                carCabin = "F";
            }
            cDao.carInsert(carType, carCabin, carWifi, licensePlate, true);

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
