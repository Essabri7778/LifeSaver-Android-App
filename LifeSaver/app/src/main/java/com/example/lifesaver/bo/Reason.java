package com.example.lifesaver.bo;

public class Reason {
    String reason;

    int icon;

    public Reason() {
    }

    public Reason(String reason, int icon) {
        this.reason = reason;
        this.icon = icon;
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
}
