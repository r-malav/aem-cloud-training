package com.aem.cloud.training.core.servlets;

import com.aem.cloud.training.core.services.Activities;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service = {Servlet.class},
        property = {
                ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET,
                ServletResolverConstants.SLING_SERVLET_PATHS + "=" + "/bin/aemcloudtraining"
        })
public class ActivitiesServlet extends SlingAllMethodsServlet {

    @Reference
    private transient Activities activities;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {

        PrintWriter out=response.getWriter();
        response.setContentType("application/json");

        ResourceResolver resourceResolver=request.getResourceResolver();

        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Activities name",activities.getRandomActivity());
        jsonObject.addProperty("user id",resourceResolver.getUserID());

        Gson gson=new Gson();
        out.println((gson.toJson(jsonObject)));
        response.flushBuffer();

    }

}
