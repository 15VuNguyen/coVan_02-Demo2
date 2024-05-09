/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.OrderDAO;
import dto.Account;
import dto.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author acer
 */
public class paymentController extends HttpServlet {

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
            /*
            1. lấy Accid cua user login
            2. insert 1 row vao bang order
            3. lay orderid vừa chèn ở bước 2
            4. insert các sản phâm trong giỏ hàng vào bảng order detail
            5. xóa giỏ hàng trong session
            6. chuyển sang trang index.jsp để mua mới nếu cần
            */
            
            //1. lấy Accid cua user login
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("loginedUser");
            if(acc != null){
                int accid = acc.getAccid();
                int totalmoney = Integer.parseInt(request.getParameter("txttotal"));
                HashMap<Item, Integer> giohang = (HashMap<Item, Integer>) session.getAttribute("cart");
                OrderDAO d = new OrderDAO();
                int result = d.insertOrder(accid, totalmoney, giohang);
                if(result > 0){
                    session.removeAttribute("cart");
                    //chỗ này nếu muốn thêm câu bạn đã mua hàng thì tryền vào request
                    request.getRequestDispatcher("mainController?action=home").forward(request, response);
                }else{
                    request.setAttribute("ERROR", "PARYMENT FAIL");
                    request.getRequestDispatcher("mainController?action=viewcart").forward(request, response);
                }
            }else{
                request.setAttribute("ERROR", "You need to login");
                request.getRequestDispatcher("mainController?action=home").forward(request, response);
                
                
                
                
            }
            
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
