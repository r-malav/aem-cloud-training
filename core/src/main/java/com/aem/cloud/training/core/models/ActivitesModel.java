package com.aem.cloud.training.core.models;


import com.aem.cloud.training.core.services.Activities;
import com.aem.cloud.training.core.services.ContentStatistics;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ActivitesModel {

   @OSGiService
   private Activities activitiesService;

    @OSGiService
    private ContentStatistics contentStatistics;

   @SlingObject
   private ResourceResolver resource;

   private String  randomActivity;

   private int assetsCount;

    @PostConstruct
    protected void init(){
        randomActivity= activitiesService.getRandomActivity();
        assetsCount=contentStatistics.getAssetsCount(resource);
    }

    public int getAssetsCount() {
        return assetsCount;
    }

    public String getRandomActivity() {
        return randomActivity;
    }

}
