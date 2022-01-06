package com.aem.cloud.training.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CardModel {

    private static Logger logger = LoggerFactory.getLogger(CardModel.class);

    @ScriptVariable
    private Page currentPage;

    @ValueMapValue
    private String cardimage;

    @ValueMapValue
    @Default(values = "enter name")
    private String fullname;

    @ValueMapValue
    private String discription;


     @ValueMapValue
     @Named("sling:resourceType")
     private String resourceType;

    @PostConstruct
    protected void init() {
        logger.info("hello advance model ");
        logger.info("current page path:{}" ,currentPage.getPath());
        logger.info("Card image :{}", cardimage);
        logger.info("Card fullname :{}", fullname);
        logger.info("Card discription :{}", discription);
    }


    public String getCardimage() {
        return cardimage;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getFullname() {
        return fullname;
    }

    public String getDiscription() {
        return discription;
    }
}
