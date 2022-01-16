package com.aem.cloud.training.core.models;

import com.aem.cloud.training.core.beans.Address;
import com.aem.cloud.training.core.beans.AddressT;
import com.aem.cloud.training.core.beans.District;
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
    private Resource district;

    @ValueMapValue
    private List<AddressT> addresslist;


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

                List<District> districtList = new ArrayList<>();
                Resource districtRsource = item.getChild("district");
                if (districtRsource != null) {
                    districtRsource.listChildren().forEachRemaining(ditem -> {
                        ValueMap valueMap1 = ditem.getValueMap();
                        String village = valueMap1.get("village", StringUtils.EMPTY);
                        String pincode = valueMap1.get("pincode", StringUtils.EMPTY);

                        District district = new District(village, pincode);
                        districtList.add(district);
                    });
                }
                AddressT address = new AddressT(State, city, districtList);
                addresslist.add(address);
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
}
