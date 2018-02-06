package org.softwire.training.resources;

import io.dropwizard.auth.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.db.WallDAO;
import org.softwire.training.models.UserPrincipal;
import org.softwire.training.views.HomePageView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/home")
@Produces(MediaType.TEXT_HTML)
public class HomePageResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePageResource.class);

    private final WallDAO wallDAO;

    public HomePageResource(WallDAO wallDAO) {
        this.wallDAO = wallDAO;
    }

    @GET
    public HomePageView get(@Auth UserPrincipal userPrincipal) {
        List<String> allUsers = wallDAO.getAllUsers();
        LOGGER.info("Get Homepage.  User: {}", userPrincipal);

        return new HomePageView(userPrincipal.getUser(), allUsers);
    }
}
