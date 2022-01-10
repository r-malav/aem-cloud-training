package com.aem.cloud.training.core.services;

public interface OsgiConfigDemo {
    public String getServiceName();
    public int getServiceCount();
    public boolean isLiveData();
    public String[] getCountries() ;
    public String getRunModes();
}
