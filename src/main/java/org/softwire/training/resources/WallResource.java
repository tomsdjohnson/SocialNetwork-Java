package org.softwire.training.resources;

import io.dropwizard.auth.Auth;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.db.WallDao;
import org.softwire.training.models.UserPrincipal;
import org.softwire.training.models.SocialEvent;
import org.softwire.training.models.User;
import org.softwire.training.views.WallView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * Resource for viewing and writing to walls
 */
@Path("/wall")
public class WallResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WallResource.class);

    private final WallDao wallDao;

    public WallResource(WallDao wallDao) {
        this.wallDao = wallDao;
    }

    @GET
    @Path("{subjectName}")
    @Produces(MediaType.TEXT_HTML)
    public WallView get(
            @Auth UserPrincipal userPrincipal,
            @PathParam("subjectName")  @NotEmpty String subjectName) {
        User subject = new User(subjectName);

        LOGGER.info("Get wall. User: {} Subject: {}", userPrincipal, subject);

        List<SocialEvent> socialEvents = wallDao.readWall(subject);
        return new WallView(socialEvents, subject, userPrincipal.getUser());
    }

    @POST
    @Path("{subjectName}/write")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response post(
            @Auth UserPrincipal userPrincipal,
            @PathParam("subjectName") @NotEmpty String subjectName,
            @FormParam("message") @NotEmpty String message) {
        User subject = new User(subjectName);

        LOGGER.info("Post to Wall. User: {} Subject: {} Message: {}",
                userPrincipal, subject, message);

        SocialEvent socialEvent = new SocialEvent(userPrincipal.getUser(), message);
        wallDao.writeOnWall(subject, socialEvent);
        return Response.seeOther(URI.create("/wall/" + subjectName)).build();
    }

}
