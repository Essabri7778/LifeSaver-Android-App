package com.example.lifesaver.bo;

public class OnlineSupportBo {

    private String name;
    private String description;
    private String email;
    private String website;
    private String logo;

    /** tack 2 values  2(urgent), 3(online) */
    private int type;
    private int booked;

    public OnlineSupportBo() {
    }

    public OnlineSupportBo(String name, String description, String email, String website, String logo, int type, int booked) {
        this.name = name;
        this.description = description;
        this.email = email;
        this.website = website;
        this.logo = logo;
        this.type = type;
        this.booked = booked;
    }

    @Override
    public String toString() {
        return "OnlineSupportBo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", logo='" + logo + '\'' +
                ", type=" + type +
                ", booked=" + booked +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }
}
