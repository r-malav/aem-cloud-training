package com.aem.cloud.training.core.models;


import com.aem.cloud.training.core.services.Activities;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ActivitesModel {

   @OSGiService
   private Activities activitiesService;

   private String  randomActivity;

    @PostConstruct
    protected void init(){
        randomActivity= activitiesService.getRandomActivity();
    }

    public String getRandomActivity() {
        return randomActivity;
    }
}
