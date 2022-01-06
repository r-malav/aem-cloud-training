package com.aem.cloud.training.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class StudentModel {

    private static final Logger logger = LoggerFactory.getLogger(StudentModel.class);

    @ValueMapValue
    private String firstname;

    @ValueMapValue
    private String lastname;

    @ValueMapValue
    private String city;

    @ValueMapValue
    private String pincode;

    @PostConstruct
    protected void init() {
        logger.info("student first name :{}", firstname);
        logger.info("student last name :{}", lastname);
        logger.info("student city name :{}", city);
        logger.info("student pincode :{}", pincode);
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getCity() {
        return city;
    }

    public String getPincode() {
        return pincode;
    }
}
