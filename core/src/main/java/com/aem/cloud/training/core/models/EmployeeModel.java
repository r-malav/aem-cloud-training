package com.aem.cloud.training.core.models;

import com.aem.cloud.training.core.beans.Address;
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
public class EmployeeModel {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeModel.class);

    @ValueMapValue
    private String companyname;

    @ValueMapValue
    private String employeename;

    @ValueMapValue
    private String designation;

    @ChildResource
    private Resource address;

    private List<Address> addressList;

    @PostConstruct
    protected void init() {
        logger.info("Employee Company Name :{}", companyname);
        logger.info("Employee Name :{}", employeename);
        logger.info("Employee Designation  :{}", designation);
        addressList = new ArrayList<>();
        if (address != null) {
            address.listChildren().forEachRemaining(item -> {
                ValueMap valueMap = item.getValueMap();
                String state = valueMap.get("state", StringUtils.EMPTY);
                String city = valueMap.get("city", StringUtils.EMPTY);
                String pincode = valueMap.get("pincode", StringUtils.EMPTY);
                Address address1 = new Address(state, city, pincode);
                addressList.add(address1);
            });
        }
    }

    public String getCompanyname() {
        return companyname;
    }
    public String getEmployeename() {
        return employeename;
    }
    public String getDesignation() {
        return designation;
    }
    public List<Address> getAddressList() {
        return addressList;
    }
}
