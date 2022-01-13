package com.aem.cloud.training.core.models;


import com.aem.cloud.training.core.beans.Address;
import com.aem.cloud.training.core.beans.Education;
import com.aem.cloud.training.core.beans.Sports;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TreeNModel {

    private final static Logger logger= LoggerFactory.getLogger(TreeNModel.class);
    @ValueMapValue
    private String firstname;

    @ValueMapValue
    private String lastname;

    @ChildResource
    Resource education;

    @ChildResource
    Resource Address;

    @ChildResource
    Resource sports;



    private List<Address> addressList;

    private List<Education>educationList;

    private List<Sports>sportsList;

   @PostConstruct
   protected void init(){
       logger.info("hello TREEnMODELS");
       logger.info("First Name :{}",firstname,"Last Nmae:{}",lastname);

       educationList=new ArrayList<>();
       if(education!=null)
       {
           education.listChildren().forEachRemaining(item->{
               ValueMap  valueMap= item.getValueMap();
               String SchoolName=valueMap.get("schoolname",StringUtils.EMPTY);
               List<String> CourseList= new ArrayList<>();
               Resource SubjectCourse=item.getChild("course");

               if(SubjectCourse!=null)
               {
                   SubjectCourse.listChildren().forEachRemaining(child->{
                       ValueMap childValueMap = child.getValueMap();
                       String subname1 = childValueMap.get("subname",StringUtils.EMPTY);
                       CourseList.add(subname1);
                   });

               }
           });

       }


   }



}
