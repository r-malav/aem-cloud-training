package com.aem.cloud.training.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.components.Component;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Model(adaptables ={SlingHttpServletRequest.class,Resource.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AdvanceModel {
    private static final Logger logger= LoggerFactory.getLogger(AdvanceModel.class);


    @ScriptVariable
    private Page currentPage;

    @ScriptVariable
    private Resource resource;

    @ScriptVariable
    private ValueMap pageProperties;

    @ScriptVariable
    private Component component;

    @SlingObject
    private ResourceResolver resourceResolver;

    @Self
    private  SlingHttpServletRequest request;

    @PostConstruct
    protected void init()
    {
        logger.info("hello advance model ");
        logger.info("current page path:{}",currentPage.getPath());
        logger.info("current page resource:{}",resource.getPath());
        logger.info("current page title:{}",pageProperties.get("jcr:title", StringUtils.EMPTY));
        logger.info("current page resourceType:{}",pageProperties.get("sling:resourceType", StringUtils.EMPTY));
        logger.info("current page template:{}",pageProperties.get("cq:template", StringUtils.EMPTY));
        logger.info("current page components Group:{}",component.getComponentGroup());
        logger.info("current page components name:{}",component.getName());

        Resource cardResource= resourceResolver.getResource("/content/AEM-Cloud-Training/us/en/first-page/advance-model/jcr:content/root/container/container/advancemodel");
        logger.info("current cardResource :{}",cardResource.getPath());

        logger.info("Sling SlingHttpServletRequest request path:{}",request.getResource().getPath());
        logger.info("Sling SlingHttpServletRequest request Name:{}",request.getResource().getName());
        logger.info("Sling SlingHttpServletRequest ResourceType :{}",request.getResource().getResourceType());

    }

}
