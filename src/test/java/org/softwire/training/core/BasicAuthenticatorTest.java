package org.softwire.training.core;

import io.dropwizard.auth.basic.BasicCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.softwire.training.db.UserDao;
import org.softwire.training.models.User;
import org.softwire.training.models.UserPrincipal;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class BasicAuthenticatorTest {

    private BasicAuthenticator basicAuthenticator;

    @BeforeEach
    public void beforeEach() {
        UserDao userDao = mock(UserDao.class);
        basicAuthenticator = new BasicAuthenticator(userDao);
    }

    @Test
    public void handleCorrectPassword() {
        String username = "username";
        Optional<UserPrincipal> userPrincipal = basicAuthenticator.authenticate(
                new BasicCredentials(username, "secret"));

        assertThat(userPrincipal, equalTo(Optional.of(new UserPrincipal(new User(username)))));
    }

    @Test
    public void handleIncorrectPassword() {
        String username = "username";
        Optional<UserPrincipal> userPrincipal = basicAuthenticator.authenticate(
                new BasicCredentials(username, "incorrectpassword"));

        assertThat(userPrincipal, equalTo(Optional.empty()));
    }
}
