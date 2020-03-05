package com.sentisense.projectfy.Model;

public class AddPatient {
    private String id;
    private String username;
    private String phno;
    private String address;
    private String age;
    private String docid;

    public AddPatient(String id,String username, String phno, String address, String age,String docid) {
        this.id = id;
        this.username = username;
        this.phno = phno;
        this.address = address;
        this.age = age;
        this.docid=docid;
    }

    public AddPatient() {

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }
}

