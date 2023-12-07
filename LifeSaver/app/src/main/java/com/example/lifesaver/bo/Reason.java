package com.example.lifesaver.bo;

import com.example.lifesaver.R;

public class Reason {

    private int id;

    String reason;

    private int sectionId;

    int isCheked;


    public Reason(int id, String reason, int isCheked, int sectionId) {
        this.id = id;
        this.reason = reason;
        this.sectionId = sectionId;
        this.isCheked =isCheked;
    }

    public Reason(String reason, int isCheked, int sectionId) {
        this.reason = reason;
        this.sectionId = sectionId;
        this.isCheked =isCheked;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public int isCheked() {
        return isCheked;
    }

    public void setCheked(int cheked) {
        isCheked = cheked;
    }



}
