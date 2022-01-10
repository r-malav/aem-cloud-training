package com.aem.cloud.training.core.models.impl;

import com.aem.cloud.training.core.models.OSGiConfigDemo;
import com.aem.cloud.training.core.services.OsgiConfigDemo;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


@Model(adaptables = SlingHttpServletRequest.class,adapters = OSGiConfigDemo.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OSGiConfigDemoModels {

    @OSGiService
   OsgiConfigDemo OsgiConfigDemo;


    public String getServiceName() {
        return OsgiConfigDemo.getServiceName();
    }

    public int getServiceCount(){
        return OsgiConfigDemo.getServiceCount();
    };

    public boolean isLiveData(){
        return OsgiConfigDemo.isLiveData();
    };

    public String[] getCountries(){
        return OsgiConfigDemo.getCountries();
    };

    public String getRunModes(){
        return OsgiConfigDemo.getRunModes();
    };

}
