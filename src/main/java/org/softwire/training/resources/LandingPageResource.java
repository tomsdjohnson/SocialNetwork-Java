package org.softwire.training.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.views.LandingPageView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class LandingPageResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePageResource.class);

    @GET
    public LandingPageView get() {
        LOGGER.info("Get Landing Page");

        return new LandingPageView();
    }
}


