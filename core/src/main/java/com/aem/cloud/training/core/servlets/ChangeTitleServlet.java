package com.aem.cloud.training.core.servlets;


import com.aem.cloud.training.core.services.TitleUpdateService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(service = Servlet.class,
        property = {
                ServletResolverConstants.SLING_SERVLET_PATHS + "=" + "/bin/aemchangetitile",
                ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_POST
        }
)
public class ChangeTitleServlet extends SlingAllMethodsServlet {

    @Reference
    private TitleUpdateService titleUpdateService;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        String title = request.getParameter("title");
        boolean status = titleUpdateService.updatedata(title);
        if (status != false) {
            out.println("title is updated");
        } else {
            out.println("title is not update");
        }
    }
}

