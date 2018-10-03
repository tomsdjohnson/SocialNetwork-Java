package org.softwire.training.resources;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.db.UserDao;
import org.softwire.training.views.NewUserView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Resource for creating new users
 */
@Path("/signup")
public class NewUserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(WallResource.class);

    @GET
    @Produces(MediaType.TEXT_HTML)
    public NewUserView get() {
        return new NewUserView();
    }

    private final UserDao userDao;

    public NewUserResource(UserDao userDao) {
        this.userDao = userDao;
    }


    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response post(
            @FormParam("username") @NotEmpty String username,
            @FormParam("password") @NotEmpty String password,
            @FormParam("fullname") @NotEmpty String fullname) {

        userDao.addUser(username, password, fullname);

        // TODO: Implement this!
        LOGGER.error("This functionality is missing!  username: {} password: {} fullname: {}",
                username, password, fullname);

        return Response.seeOther(URI.create("/home")).build();
    }

}
