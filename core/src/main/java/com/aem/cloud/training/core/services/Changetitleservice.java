package com.aem.cloud.training.core.services;

import org.osgi.annotation.versioning.ProviderType;

@ProviderType
public interface Changetitleservice {
    boolean updatedata(String title);
}