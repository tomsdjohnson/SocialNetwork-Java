package org.softwire.training;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.core.BasicAuthenticator;
import org.softwire.training.db.WallDao;
import org.softwire.training.models.UserPrincipal;
import org.softwire.training.resources.HomePageResource;
import org.softwire.training.resources.LandingPageResource;
import org.softwire.training.resources.NewUserResource;
import org.softwire.training.resources.WallResource;

/**
 * Main MyFace application
 *
 * Run with no arguments to use the default config file, or 'server myconfig.yml' to use a custom configuration.
 */
public class SocialNetworkApplication extends Application<SocialNetworkConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocialNetworkApplication.class);

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            args = new String[]{"server", "config.yml"};
        }
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
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final WallDao dao = new WallDao(jdbi);

        // Register Resources
        environment.jersey().register(new HomePageResource(dao));
        environment.jersey().register(new WallResource(dao));
        environment.jersey().register(new LandingPageResource());
        environment.jersey().register(new NewUserResource());

        // HTTP Basic Auth setup
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<UserPrincipal>()
                        .setAuthenticator(new BasicAuthenticator())
                        .setRealm("Super Secret Social Network")
                        .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(UserPrincipal.class));
    }
}
