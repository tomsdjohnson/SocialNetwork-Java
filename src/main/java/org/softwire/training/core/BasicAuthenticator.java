package org.softwire.training.core;

import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.db.UserDao;
import org.softwire.training.models.User;
import org.softwire.training.models.UserPrincipal;

import java.util.Optional;

public class BasicAuthenticator implements Authenticator<BasicCredentials, UserPrincipal> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthenticator.class);

    private final UserDao userDao;

    public BasicAuthenticator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<UserPrincipal> authenticate(BasicCredentials credentials) {
        LOGGER.debug("Attempting to authenticate {}", credentials.getUsername());

        Optional<User> userOption = userDao.getUser(credentials.getUsername());
        if (!userOption.isPresent()) {
            LOGGER.debug("Authentication failed, user not present in database: {}", credentials.getUsername());
            return Optional.empty();
        }

        User user = userOption.get();
        if (user.getPassword().equals(credentials.getPassword())) {
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.debug("Successfully authenticated user: {}", userPrincipal);
            return Optional.of(userPrincipal);
        }

        LOGGER.debug("Failed to authenticate user, incorrect password.  Username: {}", credentials.getUsername());
        return Optional.empty();
    }
}
