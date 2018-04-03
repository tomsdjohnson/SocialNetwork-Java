package org.softwire.training.resources;

import io.dropwizard.auth.Auth;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.db.UserDao;
import org.softwire.training.db.WallDao;
import org.softwire.training.models.events.*;
import org.softwire.training.models.user.User;
import org.softwire.training.models.user.UserPrincipal;
import org.softwire.training.models.user.UserSummary;
import org.softwire.training.views.WallView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Resource for viewing and writing to walls
 */
@Path("/wall")
public class WallResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WallResource.class);

    private final WallDao wallDao;
    private final UserDao userDao;

    public WallResource(WallDao wallDao, UserDao userDao) {
        this.wallDao = wallDao;
        this.userDao = userDao;
    }

    @GET
    @Path("{subjectName}")
    @Produces(MediaType.TEXT_HTML)
    public WallView get(
            @Auth UserPrincipal userPrincipal,
            @PathParam("subjectName")  @NotEmpty String subjectName) {
        LOGGER.info("Get wall. User: {} Subject: {}", userPrincipal, subjectName);

        Optional<User> subjectOption = userDao.getUser(subjectName);

        if (!subjectOption.isPresent()) {
            LOGGER.debug("Subject not preset in database, returning 404: Subject: {}",
                    userPrincipal, subjectName);
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }

        UserSummary subject = subjectOption.get();

        List<SocialEvent> socialEvents = wallDao.readWall(subject.getUsername());
        LOGGER.debug("For user {} found socialEvents: {}", subject, socialEvents);

        return new WallView(socialEvents, subject, userPrincipal.getUser());
    }

    @POST
    @Path("{subjectUsername}/write")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postWrite(
            @Auth UserPrincipal userPrincipal,
            @PathParam("subjectUsername") @NotEmpty String subjectUsername,
            @FormParam("message") @NotEmpty String message) {

        LOGGER.info("Post to Wall. User: {} Subject: {} Message: {}",
                userPrincipal, subjectUsername, message);

        Post post = new Post(userPrincipal.getUser(), message);
        wallDao.writeOnWall(subjectUsername, post);
        return Response.seeOther(URI.create("/wall/" + subjectUsername)).build();
    }

    @POST
    @Path("{subjectUsername}/wave")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postWave(
            @Auth UserPrincipal userPrinciple,
            @PathParam("subjectUsername") @NotEmpty String subjectUsername) {

        LOGGER.info("Wave Wall. User: {} Subject: {}", userPrinciple, subjectUsername);

        Wave wave = new Wave(userPrinciple.getUser());
        wallDao.writeOnWall(
                subjectUsername,
                wave);
        return Response.seeOther(URI.create("/wall/" + subjectUsername)).build();
    }

    @POST
    @Path("{subjectUsername}/frown")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postFrown(
            @Auth UserPrincipal userPrinciple,
            @PathParam("subjectUsername") @NotEmpty String subjectUsername) {

        LOGGER.info("Frown Wall. User: {} Subject: {}", userPrinciple, subjectUsername);

        Frown frown = new Frown(userPrinciple.getUser());
        wallDao.writeOnWall(
                subjectUsername,
                frown);
        return Response.seeOther(URI.create("/wall/" + subjectUsername)).build();
    }

    @POST
    @Path("{subjectUsername}/like")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response postLike(
            @Auth UserPrincipal userPrinciple,
            @PathParam("subjectUsername") @NotEmpty String subjectUsername) {

        LOGGER.info("Like Wall. User: {} Subject: {}", userPrinciple, subjectUsername);

        Like like = new Like(userPrinciple.getUser());
        wallDao.writeOnWall(
                subjectUsername,
                like);
        return Response.seeOther(URI.create("/wall/" + subjectUsername)).build();
    }
}
