package com.aem.cloud.training.core.services.impl;

import com.aem.cloud.training.core.services.Changetitleservice;
import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;


@Component(service = Changetitleservice.class)
public class Changetitleserviceimpl implements Changetitleservice {

    private final static Logger logger = LoggerFactory.getLogger(Changetitleserviceimpl.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    public boolean updatedata(String title) {
        ResourceResolver serviceResolver = getResourceResolver();
        if (serviceResolver != null) {
            Resource titleNode = serviceResolver.getResource("/content/AEM-Cloud-Training/us/en/first-page/jcr:content/root/container/title");
            try {
                if (titleNode != null) {
                    ModifiableValueMap modifiableValueMap = titleNode.adaptTo(ModifiableValueMap.class);
                    if (modifiableValueMap != null) {
                        modifiableValueMap.put("title", title);
                        serviceResolver.commit();
                        return true;
                    } else {
                        logger.info(" map value is not found");
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
        final Map<String, Object> userauth = Collections.singletonMap(ResourceResolverFactory.SUBSERVICE,"training-statistics");
        try {
            return resourceResolverFactory.getServiceResourceResolver(userauth);
        } catch (LoginException e) {
            logger.warn("printStackTrace", e);
        }
        return null;
    }

    private void closeResourceResolver(final ResourceResolver resourceResolver) {
        if (resourceResolver != null) {
            resourceResolver.close();
        }
    }


}
