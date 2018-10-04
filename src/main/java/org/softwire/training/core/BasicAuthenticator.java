package org.softwire.training.core;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softwire.training.db.UserDao;
import org.softwire.training.models.User;
import org.softwire.training.models.UserPrincipal;
import java.util.ArrayList;
import java.util.Optional;

public class BasicAuthenticator implements Authenticator<BasicCredentials, UserPrincipal> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicAuthenticator.class);
    private UserDao userDao;
    public BasicAuthenticator(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    public Optional<UserPrincipal> authenticate(BasicCredentials credentials) {
        boolean matched = false;
        ArrayList<User> allUsers = (ArrayList<User>) userDao.getAllUsers();

        String hashPassword = userDao.hashString(credentials.getPassword(), "SHA-256");

        for(User i: allUsers){
            if(credentials.getUsername().equals(i.getUsername()) && hashPassword.equals(i.getPassword())){
                matched = true;
            }
        }
        // TODO: Implement real authentication!
        if (matched) {
            UserPrincipal user = new UserPrincipal(new User(credentials.getUsername()));
            LOGGER.debug("Successfully authenticated user: {}", user);
            return Optional.of(user);
        } else {
            LOGGER.debug("Failed to authenticate user, incorrect password. Username: {}", credentials.getUsername());
            return Optional.empty();
        }
    }
}