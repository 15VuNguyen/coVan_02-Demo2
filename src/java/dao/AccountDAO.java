/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mylib.DBUtils;

/**
 *
 * @author acer
 */
public class AccountDAO {
    //ham nay de check email, password co trong bang Account hay khong
    public Account getAccount(String email, String password){
        Account result = null;
        Connection cn = null;
        try {
            //make connection giua backend va sqlserver
            cn = DBUtils.makeConnection();
            if(cn != null){
                //viet sql va execute
                String sql = "select [AccId], [Email], [FullName], [Password], [Role]\n" +
                            "from [dbo].[Accounts]\n" +
                            "where Status=1 and [Email] = ? and [Password] = ? COLLATE SQL_Latin1_General_CP1_CI_AS";
                PreparedStatement pst = cn.prepareStatement(sql);
                //lay email, password cua input param gan vao 2 cho "?"
                pst.setString(1, email);
                pst.setString(2, password);
                
                //run cau sql
                //executeQuery - cau select
                //executeUpdate - insert, delete, update
                ResultSet table = pst.executeQuery();
                
                //doc data trong table
                if(table != null && table.next()){
                    int m_accid = table.getInt("AccId");
                    String m_email = table.getString("Email");
                    String m_fullname = table.getString("FullName");
                    String m_password = table.getString("Password");
                    String m_role = table.getString("Role");
                    result = new Account(m_accid, m_fullname, m_email, m_password, m_role);
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
        return result;
    }

    //ham nay de lay tat ca cac dong trong bang account
    public ArrayList<Account> getAccounts(){
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "select AccId, Email, FullName, Password, Status, Role\n"
                        + "from dbo.Accounts";
                Statement st = cn.createStatement();
                ResultSet table = st.executeQuery(sql);
                if(table != null){
                    while(table.next()){
                        int m_accid = table.getInt("AccId");
                        String m_email = table.getString("Email");
                        String m_fullname = table.getString("FullName");
                        String m_password = table.getString("Password");
                        boolean m_status = table.getBoolean("Status");
                        String m_role=table.getString("Role");
                        Account acc = new Account(m_accid, m_fullname, m_email, m_password, m_status, m_role);
                        list.add(acc);
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
    
    
    //hàm này để tìm kiếm tất cả cac dong chứa tên cần tìm trong bang Account dua vao name
    public ArrayList<Account> getAccounts(String findname){
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "select AccId, Email, FullName, Password, Role\n"
                        + "from dbo.Accounts where FullName like ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, "%"+findname+"%");
                ResultSet table = st.executeQuery();
                if(table != null){
                    while(table.next()){
                        int m_accid = table.getInt("AccId");
                        String m_email = table.getString("Email");
                        String m_fullname = table.getString("FullName");
                        String m_password = table.getString("Password");
                        String m_role = table.getString("Role");
                        Account acc = new Account(m_accid, m_fullname, m_email, m_password, m_role);
                        list.add(acc);
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
    
    
    
    
    
    //ham nay de xoa 1 dong trong bang Account dua vao accid
    public int removeAccount(int accid){
        int result = 0;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "delete from [dbo].[Accounts]\n" +
                                "where AccId = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, accid);
                result = st.executeUpdate();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                
            } catch (Exception e) {
                e.printStackTrace();;
            }
        }
        return result;
    }
    
   
    //ham nay de reset cot password trong bang account dua vao accid
    public int resetPassword(int accid){
        int result = 0;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "UPDATE [dbo].[Accounts]\n" +
                            "set Password = ?\n" +
                            "where AccId = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setString(1, "" + (int)(Math.random() * 100000));
                st.setInt(2, accid);
                result = st.executeUpdate();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                
            } catch (Exception e) {
                e.printStackTrace();;
            }
        }
        return result;
    }
    
    
    //cập nhật status của account
    public int updateStatus(int accid){
        int result = 0;
        Connection cn = null;
        try {
            cn = DBUtils.makeConnection();
            if(cn != null){
                String sql = "update [dbo].[Accounts]\n"
                        + "set Status = ( 1 - (select Status\n"
                        + "				from [dbo].[Accounts]\n"
                        + "				where AccId = ?\n"
                        + "				))\n"
                        + "where AccId = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, accid);
                st.setInt(2, accid);
                result = st.executeUpdate();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                
            } catch (Exception e) {
                e.printStackTrace();;
            }
        }
        return result;
    }
    
    
    
    
    
    
}



