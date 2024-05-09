/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class Order {
    private int orderid;
    private Date orderdate;
    private int status;
    private int total;
    private int accid;
    private ArrayList<OrderDetail> list;

    public Order(int orderid, Date orderdate, int status, int total, int accid, ArrayList<OrderDetail> list) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.status = status;
        this.total = total;
        this.accid = accid;
        this.list = list;
    }

    public Order(int orderid, Date orderdate, int status, int total, int accid) {
        this.orderid = orderid;
        this.orderdate = orderdate;
        this.status = status;
        this.total = total;
        this.accid = accid;
        list = new ArrayList<>();
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAccid() {
        return accid;
    }

    public void setAccid(int accid) {
        this.accid = accid;
    }

    public ArrayList<OrderDetail> getList() {
        return list;
    }

    public void setList(ArrayList<OrderDetail> list) {
        this.list = list;
    }
    
    
    
    
}
