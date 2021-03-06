package com.aem.cloud.training.core.servlets;

import com.aem.cloud.training.core.services.TitleUpdateService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service = Servlet.class,
        property = {
                "sling.servlet.paths=" + "/bin/titleupdatecomp",
                "sling.servlet.methods=" + HttpConstants.METHOD_POST
        }
)
public class ChangeTitleServlet extends SlingAllMethodsServlet {

    private final static Logger logger = LoggerFactory.getLogger(ChangeTitleServlet.class);
    @Reference
    private TitleUpdateService titleUpdateService;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        logger.debug("hello::::::::::::::::::::::");
        String title = request.getParameter("Title");
        logger.info("title ::::{}", title);
     boolean status = titleUpdateService.updatedata(title);
        logger.info("status :{}", status);
        if (status != false) {
            out.println("title is updated");
        } else {
            out.println("title is not update");
        }
    }
}

