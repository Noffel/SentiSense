package com.sentisense.projectfy.Model;

public class StatusModel {
    String id,status;

    public StatusModel(String id, String status) {
        this.id = id;
        this.status=status;

    }
    public StatusModel(){

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String id) {
        this.status = status;
    }
}
