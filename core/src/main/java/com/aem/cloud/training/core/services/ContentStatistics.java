package com.aem.cloud.training.core.services;

import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface ContentStatistics {


        int getAssetsCount(final ResourceResolver resourceResolver);

        int getAllAssetsCount();
    }
