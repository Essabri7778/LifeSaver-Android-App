package com.example.lifesaver.bo;


import android.net.Uri;

public class ContactBo {
    private int id;

    private String identifier;
    private String primaryName;
    private String alternativeName;
    private Uri photo;
    private String phoneNumber;

    public ContactBo() {
    }

    public ContactBo(int id,String identifier, String primaryName, String alternativeName, Uri photo, String phoneNumber) {
        this.id = id;
        this.identifier = identifier;
        this.primaryName = primaryName;
        this.alternativeName = alternativeName;
        this.photo = photo;
        this.phoneNumber = phoneNumber;
    }

    public ContactBo(String identifier, String primaryName, String alternativeName, Uri photo, String phoneNumber) {
        this.identifier = identifier;
        this.primaryName = primaryName;
        this.alternativeName = alternativeName;
        this.photo = photo;
        this.phoneNumber = phoneNumber;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    public String getAlternativeName() {
        return alternativeName;
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ContactBo{" +
                "identifier='" + identifier + '\'' +
                ", primaryName='" + primaryName + '\'' +
                ", alternativeName='" + alternativeName + '\'' +
                ", photo=" + photo +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }
}
