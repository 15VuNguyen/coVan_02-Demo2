/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author acer
 */
public class Account implements Serializable{
    private int accid;
    private String fullname;
    private String email;
    private String password;
    private boolean status;
    private String role;

    public Account() {
        accid = 0;
        fullname = "";
        email = "";
        password = "";
        status = true;
        role="";
    }
    
    public Account(int accid, String fullname, String email, String password, String role) {
        this.accid = accid;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Account(int accid, String fullname, String email, String password,
            boolean status, String role) {
        this.accid = accid;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    public int getAccid() {
        return accid;
    }

    public void setAccid(int accid) {
        this.accid = accid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
