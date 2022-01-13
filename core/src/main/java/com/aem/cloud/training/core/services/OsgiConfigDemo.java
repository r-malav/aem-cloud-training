package com.aem.cloud.training.core.services;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface OsgiConfigDemo {
    public String getServiceName();
    public int getServiceCount();
    public boolean isLiveAvailable();
    public String[] getCountries() ;

}
