package org.softwire.training.resources;

import org.hibernate.validator.constraints.NotEmpty;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.db.UserDao;
import org.softwire.training.models.User;
import org.softwire.training.views.NewUserView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Resource for creating new users
 */
@Path("/signup")
public class NewUserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WallResource.class);

    private final UserDao userDao;

    public NewUserResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public NewUserView get() {
        LOGGER.info("Get new user view");
        return new NewUserView();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response post(
            @FormParam("username") @NotEmpty String username,
            @FormParam("fullname") @NotEmpty String fullname,
            @FormParam("password") @NotEmpty String password) {

        LOGGER.info("Create new user. User: {} Fullname: {}");
        User user = new User(username, fullname, password);
        try {
            userDao.addUser(user);
        } catch (UnableToExecuteStatementException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                return Response.ok(new NewUserView("That username is not available")).build();
            }
            throw e;
        }
        return Response.seeOther(URI.create("/home")).build();
    }
}
