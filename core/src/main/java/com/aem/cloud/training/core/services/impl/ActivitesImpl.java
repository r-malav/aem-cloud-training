package com.aem.cloud.training.core.services.impl;

import com.aem.cloud.training.core.services.Activities;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;


@Component(service = Activities.class, immediate = true)
@Designate(ocd = ActivitesImpl.Config.class)
public class ActivitesImpl implements Activities {

    private static final Logger logger = LoggerFactory.getLogger(ActivitesImpl.class);

    @ObjectClassDefinition(
            name = "Training Examples Randam Activites config ", description = "Randam Activites config"
    )
    @interface Config {

        @AttributeDefinition(name = "List of Activites ", description = "Activites", type = AttributeType.STRING)
        String[] randomActivites() default {"Camping", "skiing", "swmming", "hiking", "skateboarding"};


        @AttributeDefinition(name = "Randamseeds", description = "Randamseeds", type = AttributeType.INTEGER)
        int randamseeds() default 20;
    }

    private String[] randomActivites;
    private int randamseeds;
    private final Random random = new Random();


    @Activate
    protected void activate(Config config) {
       /* this.randomActivites = new String[]{
                "Camping", "skiing", "swmming", "hiking", "skateboarding"
        };*/
        this.randomActivites = config.randomActivites();
        logger.info("ActivitesImpl services randomActivites  [{}]", String.join(",", this.randomActivites));
        logger.info("Random seed:{}", randamseeds);
    }

    @Override
    public String getRandomActivity() {
        int randomIndex = random.nextInt(randomActivites.length);
        return randomActivites[randomIndex];
    }


    @Deactivate
    protected void deactivate() {

        logger.info("deactivate services ActivitesImpl");
    }

}



