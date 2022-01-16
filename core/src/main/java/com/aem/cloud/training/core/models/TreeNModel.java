package com.aem.cloud.training.core.models;

import com.aem.cloud.training.core.beans.AddressT;
import com.aem.cloud.training.core.beans.District;
import com.aem.cloud.training.core.beans.Education;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TreeNModel {

    private final static Logger logger = LoggerFactory.getLogger(TreeNModel.class);
    @ValueMapValue
    private String fname;

    @ValueMapValue
    private String lname;

    @ChildResource
    private Resource address;

    @ChildResource
    private Resource education;


    @ValueMapValue
    private List<AddressT> addresslist;

    @ValueMapValue
    private List<Education> educationList;

    @PostConstruct
    protected void init() {
        logger.info("first Nmae :{}", fname);
        logger.info("Last Name :{}", lname);
        addresslist = new ArrayList<>();
        if (address != null) {
            address.listChildren().forEachRemaining(item -> {
                ValueMap valuemap = item.getValueMap();
                String State = valuemap.get("state", StringUtils.EMPTY);
                String city = valuemap.get("city", StringUtils.EMPTY);
                logger.info("State Nmae :{}", State);
                logger.info("city Name :{}", city);
                List<District> districtList = new ArrayList<>();
                Resource districtRsource = item.getChild("district");
                if (districtRsource != null) {
                    districtRsource.listChildren().forEachRemaining(ditem -> {
                        ValueMap valueMap1 = ditem.getValueMap();
                        String village = valueMap1.get("village", StringUtils.EMPTY);
                        String pincode = valueMap1.get("pincode", StringUtils.EMPTY);
                        logger.info("village Nmae :{}", village);
                        logger.info("pincode :{}", pincode);
                        District district = new District(village, pincode);
                        districtList.add(district);
                    });
                }
                AddressT address = new AddressT(State, city, districtList);
                addresslist.add(address);
            });
        }

        educationList = new ArrayList<>();
        if (education != null) {
            education.listChildren().forEachRemaining(item -> {
                ValueMap valueMap = item.getValueMap();
                String subject = valueMap.get("subject", StringUtils.EMPTY);
                List<String> courseList = new ArrayList<>();
                Resource courseResource = item.getChild("courses");
                if (courseResource != null) {
                    courseResource.listChildren().forEachRemaining(child -> {
                        ValueMap valuemapcourses = child.getValueMap();
                        String coursename = valuemapcourses.get("certificatecode", StringUtils.EMPTY);
                        logger.info("certificate:{}",coursename);
                        courseList.add(coursename);
                        logger.info("courseList :{}",courseList);
                    });
                }
                Education educationvalue = new Education(subject, courseList);
                educationList.add(educationvalue);
                logger.info("educationList :{}",educationList);
            });

        }
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public List<AddressT> getAddresslist() {
        return addresslist;
    }

    public List<Education> getEducationList() {
        return educationList;
    }
}
