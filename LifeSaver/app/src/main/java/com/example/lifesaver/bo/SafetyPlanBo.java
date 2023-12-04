package com.example.lifesaver.bo;

public class SafetyPlanBo {

    private int id;
    private String response;

    public SafetyPlanBo(){

    }

    public SafetyPlanBo(int id, String response) {
        this.id = id;
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "SafetyPlanModel{" +
                "id=" + id +
                ", response='" + response + '\'' +
                '}';
    }
}
