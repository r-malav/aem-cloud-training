package com.aem.cloud.training.core.services.impl;


import com.aem.cloud.training.core.services.OsgiConfigDemo;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.*;

@Component(service = OsgiConfigDemo.class, immediate = true)
@Designate(ocd = OsgiConfigDemoimpl.serviceConfig.class)
public class OsgiConfigDemoimpl implements OsgiConfigDemo{


    @ObjectClassDefinition(
            name = "Ritesh osgi demo -first",
            description = "my osgi dem0 first"
    )
    public @interface serviceConfig {
        @AttributeDefinition(
                name = "serviceConfig first field",
                description = "serviceConfig first field",
                type = AttributeType.STRING)
        public String serviceName() default "osgi demo firsdt";

        @AttributeDefinition(
                name = "serviceConfig first int field",
                description = "serviceConfig first int field",
                type = AttributeType.INTEGER
        )
        public int getServiceCount() default 5;

        @AttributeDefinition(
                name = "Live Data",
                description = "Check this to  Available data or not.",
                type = AttributeType.BOOLEAN)
        boolean isLiveAvailable() default false;

        @AttributeDefinition(
                name = "Countries",
                description = "Add countries locales for which you want to run this service.",
                type = AttributeType.STRING
        )
        String[] getCountries() default {"de", "in"};

        @AttributeDefinition(
                name = "Run Modes",
                description = "Select Run Mode.",
                options = {
                        @Option(label = "Author", value = "author"),
                        @Option(label = "Publish", value = "publish"),
                        @Option(label = "Both", value = "both")
                },
                type = AttributeType.STRING)
        String getRunMode() default "both";
    }
    private String serviceName;
    private int serviceCount;
    private boolean liveData;
    private String[] countries;




    @Activate
    protected void activates(serviceConfig serviceConfig)
    {
        serviceName=serviceConfig.serviceName();
        serviceCount=serviceConfig.getServiceCount();
        liveData=serviceConfig.isLiveAvailable();
        countries=serviceConfig.getCountries();
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public int getServiceCount() {
        return serviceCount;
    }

    @Override
    public boolean isLiveAvailable() {
        return liveData;
    }

    @Override
    public String[] getCountries() {
        return  countries;
    }


}
