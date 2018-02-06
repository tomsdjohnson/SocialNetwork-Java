package org.softwire.training.resources;

import io.dropwizard.auth.Auth;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.db.UserDAO;
import org.softwire.training.db.WallDAO;
import org.softwire.training.models.SocialEvent;
import org.softwire.training.models.User;
import org.softwire.training.models.UserPrincipal;
import org.softwire.training.models.UserSummary;
import org.softwire.training.views.WallView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/wall")
public class WallResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WallResource.class);

    private final WallDAO wallDAO;
    private final UserDAO userDAO;

    public WallResource(WallDAO wallDAO, UserDAO userDAO) {
        this.wallDAO = wallDAO;
        this.userDAO = userDAO;
    }

    @GET
    @Path("{subjectName}")
    @Produces(MediaType.TEXT_HTML)
    public WallView get(
            @Auth UserPrincipal userPrincipal,
            @PathParam("subjectName")  @NotEmpty String subjectName) {
        LOGGER.info("Get wall. User: {} Subject: {}", userPrincipal, subjectName);

        Optional<User> subjectO = userDAO.getUser(subjectName);

        if (!subjectO.isPresent()) {
            LOGGER.debug("Subject not preset in database, returning 404: Subject: {}",
                    userPrincipal, subjectName);
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        UserSummary subject = subjectO.get();

        List<SocialEvent> socialEvents = wallDAO.readWall(subject.getUsername());
        LOGGER.debug("For user {} found socialEvents: {}", subject, socialEvents);

        return new WallView(socialEvents, subject, userPrincipal.getUser());
    }

    @POST
    @Path("{subjectUsername}/write")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response post(
            @Auth UserPrincipal userPrincipal,
            @PathParam("subjectUsername") @NotEmpty String subjectUsername,
            @FormParam("message") @NotEmpty String message) {

        LOGGER.info("Post to Wall. User: {} Subject: {} Message: {}",
                userPrincipal, subjectUsername, message);

        SocialEvent socialEvent = new SocialEvent(userPrincipal.getUser(), message);
        wallDAO.writeOnWall(subjectUsername, socialEvent);
        return Response.seeOther(URI.create("/wall/" + subjectUsername)).build();
    }
}
