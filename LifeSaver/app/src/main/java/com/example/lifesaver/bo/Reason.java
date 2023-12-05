package com.example.lifesaver.bo;

import com.example.lifesaver.R;

public class Reason {

    private int id;

    String reason;

    int icon;

    private int sectionId;


    public Reason(int id, String reason, int sectionId) {
        this.id = id;
        this.reason = reason;
        this.icon = R.drawable.checked;
        this.sectionId = sectionId;
    }

    public Reason(String reason, int sectionId) {
        this.reason = reason;
        this.icon = R.drawable.checked;
        this.sectionId = sectionId;
    }

    public Reason(String reason) {
        this.reason = reason;
        this.icon = R.drawable.checked;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
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

    @Override
    public String toString() {
        return "Reason{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", icon=" + icon +
                ", sectionId=" + sectionId +
                '}';
    }
}
