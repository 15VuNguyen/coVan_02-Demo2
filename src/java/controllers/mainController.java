/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author acer
 */
public class mainController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            //target là gọi các controller khác để xử lý
            //lấy data có tên là action do các trang pass vào đây
            String ac = request.getParameter("action");
            String url ="index.jsp";
            if(ac == null) ac = "getQC";
//            if(ac != null){
                switch (ac) {   
                    case "getQC":
                        url = "readCookieController";
                        break;
                    case "login":
                        url = "LoginController";
                        break;
                    case "adminhome":
                        url = "adminhome.jsp";
                        break;
                    case "home":
                        url = "index.jsp";
                        break;
                    case "logout":
                        url = "logoutController";
                        break;
                    case "getAccounts":
                        url = "manageAccountController";
                        break; 
                    case "accountpage":
                        url = "accountpage.jsp";
                        break;
                    case "removeaccount":
                        url = "removeAccountController";
                        break;
                    case "searchaccount":
                        url = "searchAccountController";
                        break;
                    case "block/unblock":
                        url = "updateStatusAccountController";
                        break;   
                    case "find":
                        url = "findItemsController";
                        break;
                    case "buy":
                        url = "addItemToCartController";
                        break;
                    case "viewcart":
                        url = "ViewCart.jsp";
                        break;   
                    case "update":
                        url = "updateCartController";
                        break;
                    
                    case "remove":
                        url = "removeCartController";
                        break;
                    case "payment":
                        url = "paymentController";
                        break;
                    case "history":
                        url = "orderHistoryController";
                        break;
                    case "viewhistory":
                        url = "viewHistory.jsp";
                        break;
                    
                }
                
                request.getRequestDispatcher(url).forward(request, response);
//            }
            
            
            //vùng này đang test lúc chưa chạy đc
//            if(ac == null) ac = "home";
//            switch (ac) {   
//            case "login":
//                url = "LoginController";
//                break;
//            case "adminhome":
//                url = "adminhome.jsp";
//                break;
//            case "home":
//                url = "index.jsp";
//                break;
//            case "logout":
//                url = "logoutController";
//                break;
//            case "getAccounts":
//                url = "manageAccountController";
//                break;   
//            case "accountpage":
//                url = "accountpage.jsp";
//                break;  
//            case "accountpage":
//                url = "removeAccountController";
//                break;  
//                
//                    
//            }
            //vùng này đang test lúc chưa chạy đc


                //chuyển trang qua url
                //forward vì chuyển data về trang đích không cần phải
                //chuyển lại trang này để trang trí v.v
// comment lúc 9h02 27/01                request.getRequestDispatcher(url).forward(request, response);
                
            
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
        processRequest(request, response);
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
