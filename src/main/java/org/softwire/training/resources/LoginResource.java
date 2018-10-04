package org.softwire.training.resources;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import org.hibernate.validator.constraints.NotEmpty;
import org.softwire.training.core.BasicAuthenticator;
import org.softwire.training.db.UserDao;
import org.softwire.training.models.UserPrincipal;
import org.softwire.training.views.LoginView;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * Resource for creating new users
 */
@Path("/login")
public class LoginResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public LoginView get() {
        return new LoginView();
    }

    private final UserDao userDao;

    public LoginResource(UserDao userDao) {
        this.userDao = userDao;
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response post(
            @FormParam("username") @NotEmpty String username,
            @FormParam("password") @NotEmpty String password) {

        userDao.checkUser(username, password);


//        environment.jersey().register(new AuthDynamicFeature(
//                new BasicCredentialAuthFilter.Builder<UserPrincipal>()
//                        .setAuthenticator(new BasicAuthenticator())
//                        .setRealm("Super Secret Social Network")
//                        .buildAuthFilter()));
//        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(UserPrincipal.class));
//


        return Response.seeOther(URI.create("/home")).build();
    }

}
