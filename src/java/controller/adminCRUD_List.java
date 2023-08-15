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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import journey.journeyDAO;
import journey.journeyDTO;
import location.locationDAO;
import location.locationDTO;
import order.orderDAO;
import route.routeDAO;
import route.routeDTO;
import user.userDTO;

/**
 *
 * @author USER
 */
@WebServlet(name = "adminCRUD_List", urlPatterns = {"/adminlist"})
public class adminCRUD_List extends HttpServlet {

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
            out.println("<title>Servlet adminCRUD_List</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adminCRUD_List at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
          if(session.getAttribute("account")==null){
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }else{
              userDTO udto =(userDTO)session.getAttribute("account");
               if(udto.getRoleID() ==2){
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
          }
        if (action.equals("journey")) {
            journeyDAO dao = new journeyDAO();
            List<journeyDTO> dto = dao.list(0, 0, 0, null, null, null, null, null);
            routeDAO rDao = new routeDAO();
            List<routeDTO> list = rDao.list(0, 0, 0, false);
            request.setAttribute("listroute", list);
            request.setAttribute("list", dto);
            request.getRequestDispatcher("adminListJourneyCRUD.jsp").forward(request, response);
        }
        
        if (action.equals("location")) {
            locationDAO l = new locationDAO();
            
            List<locationDTO> list = l.list(0, false);
            request.setAttribute("listlocation", list);
            
            request.getRequestDispatcher("adminListLocationCRUD.jsp").forward(request, response);
        }
        
        if (action.equals("order")) {
            routeDAO rDao = new routeDAO();
            request.setAttribute("listroute", rDao.list(0, 0, 0, false));
            
            orderDAO odao = new orderDAO();            
            request.setAttribute("list", odao.list(0, 0, 0, 0, 0, 0, null, null));
            
            request.getRequestDispatcher("adminListOrderCRUD.jsp").forward(request, response);
            
        }
        
        if (action.equals("car")) {
            carDAO c = new carDAO();
            List<carDTO> list = c.list(0, null, null, null, false);
            request.setAttribute("listcar", list);
            request.getRequestDispatcher("adminListCarCRUD.jsp").forward(request, response);
        }
        
        if (action.equals("route")) {
            routeDAO rDao = new routeDAO();
            List<routeDTO> list = rDao.list(0, 0, 0, false);
            request.setAttribute("listroute", list);
            locationDAO l = new locationDAO();
            List<locationDTO> listLocation = l.list(0, false);
            request.setAttribute("listlocation", listLocation);
            request.getRequestDispatcher("adminListRouteCRUD.jsp").forward(request, response);
            
        }
        if (action.equals("chart")) {

            routeDAO  rdao = new routeDAO();
                  request.setAttribute("total", rdao.listchart());
       request.setAttribute("top3", rdao.listcharttop3());
       request.getRequestDispatcher("adminChart.jsp").forward(request, response);
           


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
        
        if (action.equals("journey")) {
            journeyDAO dao = new journeyDAO();
            List<journeyDTO> dto = dao.list(0, 0, 0, null, null, null, null, null);
            request.setAttribute("list", dto);
            request.getRequestDispatcher("adminListJourneyCRUD.jsp").forward(request, response);
        }
        if (action.equals("location")) {
            locationDAO l = new locationDAO();
            List<locationDTO> list = l.list(0, false);
            request.setAttribute("listlocation", list);
            request.getRequestDispatcher("adminListLocationCRUD.jsp").forward(request, response);
        }
        if (action.equals("car")) {
            carDAO c = new carDAO();
            List<carDTO> list = c.list(0, null, null, null, false);
            request.setAttribute("listcar", list);
            request.getRequestDispatcher("adminListCarCRUD.jsp").forward(request, response);
        }
        if (action.equals("route")) {
            routeDAO rDao = new routeDAO();
            List<routeDTO> list = rDao.list(0, 0, 0, false);
            request.setAttribute("listroute", list);
            request.getRequestDispatcher("adminListRouteCRUD.jsp").forward(request, response);
            
        }
        
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
