package com.aem.cloud.training.core.servlets;

import com.aem.cloud.training.core.services.Activities;
import com.day.cq.wcm.api.Page;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.SlingServletException;
import org.apache.sling.api.resource.*;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
        resourceTypes = "AEM-Cloud-Training/components/page",
        methods = HttpConstants.METHOD_POST,
        extensions = "json"
)
@ServiceDescription("resource type servlet")
public class PathServlet extends SlingAllMethodsServlet {

    @Reference
    private transient Activities activities;
    @Reference
    private transient ResourceResolver resource;

    @ScriptVariable
    private Page currentPage;

    @ScriptVariable
    private ValueMap pageProperties;

   @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String titleP=request.getParameter("title");
        String titlePjcr=request.getParameter("jcr:title");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("randam -Activities", activities.getRandomActivity());
        jsonObject.addProperty("first name ", firstname);
        jsonObject.addProperty("Last Name", lastname);

        Gson gson = new Gson();
        out.println(gson.toJson(jsonObject));
        String pagetitle=pageProperties.get("jcr:title", StringUtils.EMPTY);

/*
        ModifiableValueMap modifiableValueMap = resource.adaptTo(ModifiableValueMap.class);
        modifiableValueMap.put(currentPage.getTitle(), titleP); // Modify
        modifiableValueMap.put(pagetitle, titlePjcr);
*/

        response.flushBuffer();



    }
}
