package com.aem.cloud.training.core.services.impl;
import com.aem.cloud.training.core.services.Activities;
import org.osgi.service.component.annotations.Component;

import java.util.Random;


@Component (service = Activities.class,immediate = true)
public class ActivitesImpl implements Activities {
    private static final String[] randomActivites = new String[]{
            "Camping", "skiing", "swmming", "hiking", "skateboarding"
    };

    private final int randomIndex = new Random().nextInt(randomActivites.length);


    public String getRandomActivity() {

        return randomActivites[randomIndex];
    }
}



