package com.aem.cloud.training.core.services.impl;

import com.aem.cloud.training.core.services.ContentStatistics;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;

@Component(service = ContentStatistics.class)
public class ContentStatisticsImpl implements ContentStatistics{
    @Override
    public int getAssetsCount(final ResourceResolver resourceResolver) {
        return queryAndcountAssets(resourceResolver);
    }

    private int queryAndcountAssets(ResourceResolver resourceResolver) {

        final String ASSETS_QUERY="";
        return 0;
    }

    @Override
    public int getAllAssetsCount() {
        return -1;
    }
}
