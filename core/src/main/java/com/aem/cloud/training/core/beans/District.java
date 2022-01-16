package com.aem.cloud.training.core.beans;

public class District {

    private String village;
    private String pincode;

    public District(String village, String pincode) {
        this.village = village;
        this.pincode = pincode;
    }

    public String getVillage() {
        return village;
    }

    public String getPincode() {
        return pincode;
    }
}
