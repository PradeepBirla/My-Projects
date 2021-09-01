package com.example.watercomplaintsystem;

import android.widget.EditText;

public class UserHelperClass {

    String phoneNo,name,email,password,complaint;
    String text;
    public UserHelperClass(EditText text) {

    }

    public UserHelperClass(String phoneNo, String name, String email, String password,String comp) {
        this.phoneNo = phoneNo;
        this.name = name;
        this.email = email;
        this.password = password;
        this.complaint=comp;
    }

    public UserHelperClass(String text) {
        this.text = text;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
