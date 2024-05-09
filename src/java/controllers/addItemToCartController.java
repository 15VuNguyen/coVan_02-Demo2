/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dao.ItemDAO;
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
public class addItemToCartController extends HttpServlet {

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
            //tạo giỏ hàng khi nút buy đuược nhấn
            //check xem nếu chưa có thì tạo, nếu có rồi thì khỏi tạo
            
            //lấy mã sp mã mình muốn mua
            String itemid = request.getParameter("txtItemId");
            
            //1. lấy giỏ hàng trong session memory
            HttpSession session = request.getSession();
            HashMap<Item, Integer> giohang = (HashMap<Item, Integer>) session.getAttribute("cart");
            
            
            //2. nếu giỏ hàng trống(null)
            //      thì tạo giỏ hàng mới
            //   ngược lại nếu khác null
            //      nếu kiểm tra nếu item chưa có trong giỏ
            //          thì sẽ add item vào giỏ với so luong bằng 1
            //      nếu item đã có trong giỏ
            //          thì update quantity tăng lên 1
            if(giohang == null){
                giohang = new HashMap<>();
            }
            //kiểm tra xem có itemID đó trong giỏ hay chưa
            Item FoundItem = null;
            for(Item it: giohang.keySet()){
                if(it.getItemid() == Integer.parseInt(itemid.trim())){
                    FoundItem = it;
                    break;
                }
            }
            //cập nhật nếu đã có trong gió
            if(FoundItem != null){
                int quantity = giohang.get(FoundItem);
                quantity++;
                giohang.put(FoundItem, quantity);
            }else{//nếu chưa có trong giỏ
                //lấy item trong db dua vao itemid
                ItemDAO d = new ItemDAO();
                Item item = d.getItem(Integer.parseInt(itemid));
                if(item != null) giohang.put(item, 1);

            }
            
            //3. lưu giỏ hàng vào session memory
            session.setAttribute("cart", giohang);
            
            
            //4. mở trang kế tiếp nếu cần
            request.getRequestDispatcher("mainController?action=home").forward(request, response);
            
            
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
