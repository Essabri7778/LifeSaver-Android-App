package com.example.lifesaver.bo;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

public class ReasonSection {

    private int id;
    private String title;
    private int icon;
    private List<Reason> reasons;

    public ReasonSection(int id, String title, int icon, List<Reason> reasons) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.reasons = reasons;
    }

    public ReasonSection(String title, int icon, List<Reason> reasons) {
        this.title = title;
        this.icon = icon;
        this.reasons = reasons;
    }

    public ReasonSection() {

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

    public List<Reason> getReasonsChecked() {
        List<Reason> checkeds = new ArrayList<>();
        for(Reason r : getReasons()){
            if(r.isCheked()==1) checkeds.add(r);
        }
        return checkeds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ReasonSection{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", icon=" + icon +
                ", reasons=" + reasons +
                '}';
    }
}
