package com.aem.cloud.training.core.services.impl;

import com.aem.cloud.training.core.services.TitleUpdateService;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

@Component(service = TitleUpdateService.class, immediate = true)
@Designate(ocd = TitleUpdateServiceImpl.Configpath.class)
public class TitleUpdateServiceImpl implements TitleUpdateService {

    @ObjectClassDefinition(
            name = "Title path update config ", description = "Title path update config"
    )
    @interface Configpath {
        @AttributeDefinition(name = "Title path ", description = "Title path", type = AttributeType.STRING)
        String titlepath() default "/content/AEM-Cloud-Training/us/en/first-page/jcr:content/root/container/title";
    }

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    private final static Logger logger = LoggerFactory.getLogger(TitleUpdateServiceImpl.class);

    private String compPath;

    @Activate
    protected void activate(Configpath configpath) {

        this.compPath = configpath.titlepath();
        logger.debug("path :{}", compPath);
    }

    @Override
    public boolean updatedata(String title) {
        logger.debug("getResourceResolver :{}", getResourceResolver());
        ResourceResolver serviceResolver = getResourceResolver();
        if (serviceResolver != null) {
            Resource titleNode = serviceResolver.getResource(compPath);
            logger.debug("path :{}", compPath);
            try {
                if (titleNode != null) {
                    ModifiableValueMap modifiableValueMap = titleNode.adaptTo(ModifiableValueMap.class);
                    if (modifiableValueMap != null) {
                        modifiableValueMap.put("title", title);
                        serviceResolver.commit();
                        return true;
                    } else {
                        logger.debug("map value is not found");
                    }
                } else {
                    logger.info("Node is null");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.info("resourse resolver is null");
        }
        closeResourceResolver(serviceResolver);
        return false;
    }

    private ResourceResolver getResourceResolver() {
        final Map<String, Object> userauth = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE, "training-statistics-service");
        logger.debug("userauth ::::::::::::::::::{}", userauth);

        try {
            logger.debug("userauth ::::::::::::::::::{}", resourceResolverFactory.getServiceResourceResolver(userauth));
            return resourceResolverFactory.getServiceResourceResolver(userauth);
        } catch (LoginException e) {
            logger.warn("LoginException", e);
        }
        return null;
    }

    private void closeResourceResolver(final ResourceResolver resourceResolver) {
        if (resourceResolver != null) {
            resourceResolver.close();
        }
    }
}
