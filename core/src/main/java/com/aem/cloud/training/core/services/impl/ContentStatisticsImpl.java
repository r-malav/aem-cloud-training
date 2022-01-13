package com.aem.cloud.training.core.services.impl;

import com.aem.cloud.training.core.services.ContentStatistics;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.query.Query;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

@Component(service = ContentStatistics.class)
public class ContentStatisticsImpl implements ContentStatistics{

    private final static Logger logger= LoggerFactory.getLogger(ContentStatisticsImpl.class);

    @Override
    public int getAssetsCount(final ResourceResolver resourceResolver) {
        return queryAndcountAssets(resourceResolver);
    }

    private int queryAndcountAssets(ResourceResolver resourceResolver) {

        final String ASSETS_QUERY = "SELECT * FROM [dam:Asset] WHERE isdescendantnode(\"/content/dam/training\")";
        Iterator<Resource> resources = resourceResolver.findResources(ASSETS_QUERY, Query.JCR_SQL2);
        logger.info("resources [{}] ",resources);
        int count=0;
        while(resources.hasNext()){
            count++;
            resources.next();
        }
        logger.info("user [{}] found[{}] asserts",resourceResolver.getUserID(),count);
        return count;
    }

    @Override
    public int getAllAssetsCount() {
        return -1;
    }

    @Activate
    protected void activate() {
        logger.info("ContentStatisticsImpl Service Activated!");
    }
}
