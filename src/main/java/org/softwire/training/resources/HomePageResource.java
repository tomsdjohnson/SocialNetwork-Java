package org.softwire.training.resources;

import io.dropwizard.auth.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.db.UserDao;
import org.softwire.training.models.UserPrincipal;
import org.softwire.training.models.UserSummary;
import org.softwire.training.views.HomePageView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Resource serving the home page for a user
 */
@Path("/home")
@Produces(MediaType.TEXT_HTML)
public class HomePageResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePageResource.class);

    private final UserDao userDao;

    public HomePageResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @GET
    public HomePageView get(@Auth UserPrincipal userPrincipal) {
        List<UserSummary> allUsers = userDao.getUserSummaries();
        LOGGER.info("Get Homepage.  User: {}", userPrincipal);

        return new HomePageView(userPrincipal.getUser(), allUsers);
    }
}
