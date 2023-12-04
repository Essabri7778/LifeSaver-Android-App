package com.example.lifesaver.bo;

import com.example.lifesaver.R;

public class Reason {
    String reason;

    int icon;

    public Reason() {
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
}
