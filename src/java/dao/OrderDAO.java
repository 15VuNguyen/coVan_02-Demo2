/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Item;
import dto.Order;
import dto.OrderDetail;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import mylib.DBUtils;

/**
 *
 * @author acer
 */
public class OrderDAO {
    //hàm này để chèn 1 dòng vào bảng order 
    //VÀ các item trong gio hang vao order detail
    public int insertOrder(int accid, int totalmoney,
            HashMap<Item, Integer> giohang){
        int result = 0;
        Connection cn = null;
        try{
            cn = DBUtils.makeConnection();
            if(cn != null){
                cn.setAutoCommit(false);
                //insert order 
                String sql = "insert [dbo].[Orders] values(?,?,?,?)";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setDate(1, new Date(System.currentTimeMillis()));
                pst.setInt(2, 1);   //1 là status = 'new'
                pst.setInt(3, totalmoney);
                pst.setInt(4, accid);
                result = pst.executeUpdate();
                if(result > 0){
                    //rồi lấy orderid vua chèn
                    sql = "select top 1 [OrderID]\n"
                            + "from [dbo].[Orders]\n"
                            + "order by [OrderID] desc";
                    pst = cn.prepareStatement(sql);
                    ResultSet table = pst.executeQuery();
                    if(table != null && table.next()){
                        int orderid = table.getInt("OrderID");
                        if(orderid > 0){
                            //insert cac items vao orderdetail
                            for(Item it: giohang.keySet()){
                                sql = "insert [dbo].[OrderDetails] values(?,?,?,?)";
                                pst = cn.prepareStatement(sql);
                                pst.setInt(1, it.getItemid());
                                pst.setInt(2, orderid);
                                pst.setInt(3, giohang.get(it));
                                pst.setInt(4, it.getPrice());
                                result = pst.executeUpdate();
                            }
                            
                        }
                    }
                    
                    
                }
                cn.commit();
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(cn!=null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    public ArrayList<Order> getOrders(int accid){
        ArrayList<Order> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "select [OrderID],[OrderDate],[Status],\n"
                        + "[Total], [AccID] \n"
                        + "from [dbo].[Orders]\n"
                        + "where [AccID] = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, accid);
                ResultSet table = pst.executeQuery();
                if(table != null){
                    while(table.next()){
                        int orderid = table.getInt("OrderID");
                        Date date = table.getDate("OrderDate");
                        int status = table.getInt("Status");
                        int total = table.getInt("Total");
                        
                        //lấy orderdetail của orderid
                        
                        //SỬA CHỖ này thêm Price vào SQL rồi mới thêm câu query
                        sql = "select [DetailID], [ItemID], [OrderID],[Quantity],[Price]\n"
                                + "from [dbo].[OrderDetails]\n"
                                + "where [OrderID] = ?";
                        
                        pst = cn.prepareStatement(sql);
                        pst.setInt(1, orderid);
                        ResultSet table2 = pst.executeQuery();
                        if(table2 != null){
                            ArrayList<OrderDetail> listdetail = new ArrayList<>();
                            while(table2.next()){
                                int detailid = table2.getInt("DetailID");
                                int itemid = table2.getInt("ItemID");
                                int quantity = table2.getInt("Quantity");
                                int price = table2.getInt("Price");
                                OrderDetail detail = new OrderDetail(detailid, itemid, orderid, quantity, price);
                                listdetail.add(detail);
                            }
                            Order order = new Order(orderid, date, status, total, accid, listdetail);
                            list.add(order);
                        }
                        
                        
                    }
                }
                
                
                
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return list;
    }
    
    
}
