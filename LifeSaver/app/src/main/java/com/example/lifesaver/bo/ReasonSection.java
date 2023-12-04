package com.example.lifesaver.bo;

import android.graphics.drawable.Drawable;

import java.util.List;

public class ReasonSection {
    private String title;
    private int icon;
    private List<Reason> reasons;

    public ReasonSection(String title, int icon, List<Reason> reasons) {
        this.title = title;
        this.icon = icon;
        this.reasons = reasons;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setReasons(List<Reason> reasons) {
        this.reasons = reasons;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }

    public List<Reason> getReasons() {
        return reasons;
    }
}
