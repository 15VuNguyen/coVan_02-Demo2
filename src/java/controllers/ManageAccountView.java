/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dto.Account;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author acer
 */
public class ManageAccountView extends HttpServlet {

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
            
            //lấy ListAccount do trang ManageAccountController pass qua day
            ArrayList<Account> list = (ArrayList<Account>) request.getAttribute("ListAccount");
            if(list==null || list.size() == 0){
                out.print("coming soon");
            }else{


                //xuat form search
                out.print("<form action='' method='post'>");
                out.print("<input type='text' name='txtsearch' placeholder='enter name' >");
                out.print("<input type='submit' value='GO' >");

                out.print("</form>");









                //xuat ds account ra man hinh
                out.print("<table>");
                    out.print("<tr>");
                        out.print("<th>acc id</th>");
                        out.print("<th>acc name</th>");
                        out.print("<th>acc email</th>");
                        out.print("<th>action</th>");
                    out.print("</tr>");
                    if(list != null && list.size() > 0){
                        //su dung btn remove thi phai co control input de gui
                        //data di de xoa khi nhan nut remove, vi khong the gui
                        //neu kh co input
                        //neu dung the a thi lam cach href de truyen data
                        for (Account acc : list) {
                            out.print("<form action='removeAccountController'>");
                                out.print("<input type='hidden' name='txtaccid' value='"+ acc.getAccid()+"'>");
                                out.print("<tr>");
                                    out.print("<td>" + acc.getAccid() + "</td>");
                                    out.print("<td>" + acc.getFullname()+ "</td>");
                                    out.print("<td>" + acc.getEmail()+ "</td>");
                                    out.print("<td><input type='submit' value='remove' onclick='return window.confirm(\"are u sure?\")' ></td>");
                                    //sau dau cham hoi la du lieu minh muon day di
                                    out.print("<td><a href='resetPasswordController?txtaccid="+ acc.getAccid() +"'>reset password</a></td>");
                                out.print("</tr>");
                            out.print("</form>");
                        }
                    }

                out.print("</table>");

            }//het else
            
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
