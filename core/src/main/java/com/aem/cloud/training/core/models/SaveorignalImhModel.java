package com.aem.cloud.training.core.models;

import com.adobe.cq.wcm.core.components.models.Image;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.via.ResourceSuperType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Model(adaptables = SlingHttpServletRequest.class,
  adapters = Image.class,
  resourceType = "AEM-Cloud-Training/components/image",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class SaveorignalImhModel implements  Image {


    private static final Logger logger= LoggerFactory.getLogger(SaveorignalImhModel.class);
    @ScriptVariable
    private ValueMap pageProperties;
   @Self
    @Via(type= ResourceSuperType.class)
    private Image dalegate;

   @ValueMapValue
    private boolean checkboximageset;

   @ValueMapValue
    private String fileReference;

    @Override
    public String getSrc()
   {

       String newImage=checkboximageset ?fileReference:dalegate.getSrc();
       logger.info("image ",pageProperties.get(newImage,StringUtils.EMPTY));
       return newImage;

   }

}
