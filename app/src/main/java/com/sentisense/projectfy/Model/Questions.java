package com.sentisense.projectfy.Model;

public class Questions {
    String id,bring,viewpoint,wave,counselreviews,patname;

    public Questions(String id, String bring, String viewpoint, String wave, String counselreviews,String patname) {
        this.id = id;
        this.bring = bring;
        this.viewpoint = viewpoint;
        this.wave = wave;
        this.counselreviews = counselreviews;
        this.patname=patname;
    }

    public Questions() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBring() {
        return bring;
    }

    public void setBring(String bring) {
        this.bring = bring;
    }

    public String getPatname() {
        return patname;
    }

    public void setPatname(String patname) {
        this.patname = patname;
    }

    public String getViewpoint() {
        return viewpoint;
    }

    public void setViewpoint(String viewpoint) {
        this.viewpoint = viewpoint;
    }

    public String getWave() {
        return wave;
    }

    public void setWave(String wave) {
        this.wave = wave;
    }

    public String getCounselreviews() {
        return counselreviews;
    }

    public void setCounselreviews(String counselreviews) {
        this.counselreviews = counselreviews;
    }
}
