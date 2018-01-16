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
import static org.mockito.Mockito.when;

public class BasicAuthenticatorTest {

    private BasicAuthenticator basicAuthenticator;
    private UserDao userDao;

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final User USER = new User("username", "fullname", PASSWORD);

    @BeforeEach
    public void beforeEach() {
        userDao = mock(UserDao.class);
        when(userDao.getUser(USERNAME)).thenReturn(Optional.of(USER));
        basicAuthenticator = new BasicAuthenticator(userDao);
    }

    @Test
    public void handleCorrectPassword() {
        Optional<UserPrincipal> userPrincipal = basicAuthenticator.authenticate(
                new BasicCredentials(USERNAME, PASSWORD));

        assertThat(userPrincipal, equalTo(Optional.of(new UserPrincipal(USER))));
    }

    @Test
    public void handleNonexistentUser() {
        when(userDao.getUser(USERNAME)).thenReturn(Optional.empty());

        Optional<UserPrincipal> userPrincipal = basicAuthenticator.authenticate(
                new BasicCredentials(USERNAME, PASSWORD));

        assertThat(userPrincipal, equalTo(Optional.empty()));
    }

    @Test
    public void handleIncorrectPassword() {
        Optional<UserPrincipal> userPrincipal = basicAuthenticator.authenticate(
                new BasicCredentials(USERNAME, "incorrectpassword"));

        assertThat(userPrincipal, equalTo(Optional.empty()));
    }
}
