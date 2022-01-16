package com.aem.cloud.training.core.beans;

import java.util.List;

public class AddressT {

    private String state;
    private String city;
    private List<District> districtList;

    public AddressT(String state, String city, List<District> districtList) {
        this.state = state;
        this.city = city;
        this.districtList = districtList;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public List<District> getDistrictList() {
        return districtList;
    }
}
