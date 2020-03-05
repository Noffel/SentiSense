package com.sentisense.projectfy.Model;

public class Doctor {
    private String id;
    private String username;
    private String phno;
    private String email;



        public Doctor(String id,String username,String phno,String email){
            this.id=id;
            this.username=username;
            this.phno=phno;
        }
        public Doctor(){

        }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
