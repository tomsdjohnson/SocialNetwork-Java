package org.softwire.training.core;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.models.User;
import org.softwire.training.models.UserPrincipal;

import java.util.Optional;

public class BasicAuthenticator implements Authenticator<BasicCredentials, UserPrincipal> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthenticator.class);

    @Override
    public Optional<UserPrincipal> authenticate(BasicCredentials credentials) {
        if ("secret".equals(credentials.getPassword())) {
            UserPrincipal user = new UserPrincipal(new User(credentials.getUsername()));
            LOGGER.debug("Successfully authenticated user: {}", user);
            return Optional.of(user);
        }
        LOGGER.debug("Failed to authenticate user, incorrect password.  Username: {}", credentials.getUsername());
        return Optional.empty();
    }
}
