package com.example.lifesaver.bo;

public class ResourceBo {
    private int id;
    private String name;
    private String description;
    private String phone;
    private String sms;
    private String email;
    private String website;
    private String logo;

    /** tack 3 values 1(own), 2(urgent), 3(online) */
    private int type;

    public ResourceBo() {
    }

    public ResourceBo(String name, String description, String phone, String sms,
                      String email, String website, String logo, int type) {

        this.name = name;
        this.description = description;
        this.phone = phone;
        this.sms = sms;
        this.email = email;
        this.website = website;
        this.logo = logo;
        this.type = type;
    }

    public ResourceBo(int id, String name, String description, String phone, String sms,
                      String email, String website, String logo, int type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.sms = sms;
        this.email = email;
        this.website = website;
        this.logo = logo;
        this.type = type;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
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

    @Override
    public String toString() {
        return "ResourceBo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", sms='" + sms + '\'' +
                ", email='" + email + '\'' +
                ", website='" + website + '\'' +
                ", logo='" + logo + '\'' +
                ", type=" + type +
                '}';
    }
}
