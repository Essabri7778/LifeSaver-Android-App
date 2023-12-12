package com.example.lifesaver.bo;

public class UrgentSupportBo {

    private String name;
    private String description;
    private String phone;
    private String sms;
    private String logo;

    /** tack 2 values  2(urgent), 3(online) */
    private int type;
    private int booked;

    @Override
    public String toString() {
        return "UrgentSupportBo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", sms='" + sms + '\'' +
                ", logo='" + logo + '\'' +
                ", type=" + type +
                ", booked=" + booked +
                '}';
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public UrgentSupportBo() {
    }

    public UrgentSupportBo(String name, String description, String phone, String sms,String logo, int type ,int booked) {
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.sms = sms;
        this.logo = logo;
        this.type = type;
        this.booked = booked;
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
}
