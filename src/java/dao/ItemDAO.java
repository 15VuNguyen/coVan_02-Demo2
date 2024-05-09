/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author acer
 */
public class ItemDAO {
    public ArrayList<Item> getItems(String findName){
        ArrayList<Item> list = new ArrayList<>();
        Connection cn = null;
        try {
            //make connection giua backend va sqlserver
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "select ItemId,ItemName,Price,Status,Image\n"
                        + "from dbo.Items\n"
                        + "where ItemName like ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setString(1, "%"+findName+"%");
                ResultSet table = pst.executeQuery();
                if(table != null){
                    while(table.next()){
                        int itemid = table.getInt("ItemId");
                        String itemname = table.getString("ItemName");
                        int price = table.getInt("Price");
                        int status = table.getInt("Status");
                        String imageurl = table.getString("Image");
                        Item it = new Item(itemid, itemname, price, status, imageurl);
                        list.add(it);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    
    
    //lấy 1 sp dựa vào mã
    public Item getItem(int itemid){
        Item rs = null;
        Connection cn = null;
        try {
            //make connection giua backend va sqlserver
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "select ItemId,ItemName,Price,Status,Image\n"
                        + "from dbo.Items\n"
                        + "where ItemId = ?";
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, itemid);
                ResultSet table = pst.executeQuery();
                if(table != null){
                    while(table.next()){
//                        int itemid = table.getInt("ItemId");
                        String itemname = table.getString("ItemName");
                        int price = table.getInt("Price");
                        int status = table.getInt("Status");
                        String imageurl = table.getString("Image");
                        rs = new Item(itemid, itemname, price, status, imageurl);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(cn != null) cn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
    
}
