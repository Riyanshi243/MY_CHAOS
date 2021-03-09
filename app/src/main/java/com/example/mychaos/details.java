package com.example.mychaos;

public class details {
    String name;
    String email;
    String phone;
    String username;
    String dob;
    String uid;
    String note_no;
    public details()
    {

    }

    public String getNote_no() {
        return note_no;
    }

    public details(String u, String aname, String aemail, String aphone, String ausername, String adob, String notes)
    {
        uid=u;
        name=aname;
        email=aemail;
        phone=aphone;
        username=ausername;
        dob=adob;
        note_no=notes;

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getUid() {
        return uid;
    }

    public String getDob() {
        return dob;
    }
}
