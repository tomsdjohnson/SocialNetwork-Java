package org.softwire.training.resources;

import io.dropwizard.auth.Auth;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.db.WallDAO;
import org.softwire.training.models.UserPrinciple;
import org.softwire.training.models.SocialEvent;
import org.softwire.training.models.User;
import org.softwire.training.views.WallView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/wall")
public class WallResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WallResource.class);

    private final WallDAO wallDAO;

    public WallResource(WallDAO wallDAO) {
        this.wallDAO = wallDAO;
    }

    @GET
    @Path("{subjectName}")
    @Produces(MediaType.TEXT_HTML)
    public WallView get(
            @Auth UserPrinciple userPrinciple,
            @PathParam("subjectName")  @NotEmpty String subjectName) {
        User subject = new User(subjectName);

        LOGGER.info("Get wall. User: {} Subject: {}", userPrinciple, subject);

        List<SocialEvent> socialEvents = wallDAO.readWall(subject);
        return new WallView(socialEvents, subject, userPrinciple.getUser());
    }

    @POST
    @Path("{subjectName}/write")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response post(
            @Auth UserPrinciple userPrinciple,
            @PathParam("subjectName") @NotEmpty String subjectName,
            @FormParam("message") @NotEmpty String message) {
        User subject = new User(subjectName);

        LOGGER.info("Post to Wall. User: {} Subject: {} Message: {}",
                userPrinciple, subject, message);

        SocialEvent socialEvent = new SocialEvent(userPrinciple.getUser(), message);
        wallDAO.writeOnWall(subject, socialEvent);
        return Response.seeOther(URI.create("/wall/" + subjectName)).build();
    }
}
