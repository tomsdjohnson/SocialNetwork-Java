package org.softwire.training;

import com.github.arteam.jdbi3.JdbiFactory;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.core.BasicAuthenticator;
import org.softwire.training.db.UserDAO;
import org.softwire.training.db.WallDAO;
import org.softwire.training.models.UserPrincipal;
import org.softwire.training.resources.HomePageResource;
import org.softwire.training.resources.LandingPageResource;
import org.softwire.training.resources.NewUserResource;
import org.softwire.training.resources.WallResource;

public class SocialNetworkApplication extends Application<SocialNetworkConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialNetworkApplication.class);

    public static void main(final String[] args) throws Exception {
        new SocialNetworkApplication().run(args);
    }

    @Override
    public String getName() {
        return "socialnetwork";
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initialize(final Bootstrap<SocialNetworkConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle());
    }

    @Override
    public void run(final SocialNetworkConfiguration configuration,
                    final Environment environment) {
        LOGGER.info("Starting MyFace Social Network");
        // Database setup
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final WallDAO wallDAO = new WallDAO(jdbi);
        final UserDAO userDAO = new UserDAO(jdbi);

        // Register Resources
        environment.jersey().register(new HomePageResource(userDAO));
        environment.jersey().register(new WallResource(wallDAO, userDAO));
        environment.jersey().register(new LandingPageResource());
        environment.jersey().register(new NewUserResource(userDAO));

        // HTTP Basic Auth setup
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<UserPrincipal>()
                        .setAuthenticator(new BasicAuthenticator(userDAO))
                        .setRealm("Super Secret Social Network")
                        .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(UserPrincipal.class));
    }
}
