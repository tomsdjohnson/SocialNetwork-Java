package org.softwire.training.core;

import io.dropwizard.auth.basic.BasicCredentials;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.softwire.training.models.UserPrincipal;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class BasicAuthenticatorTest {

    private BasicAuthenticator basicAuthenticator;

    @BeforeEach
    public void beforeEach() {
        basicAuthenticator = new BasicAuthenticator();
    }

    @Test
    public void handleCorrectPassword() {
        String username = "username";
        Optional<UserPrincipal> userPrinciple = basicAuthenticator.authenticate(
                new BasicCredentials(username, "secret"));

        assertThat(userPrinciple, equalTo(Optional.of(new UserPrincipal(username))));
    }

    @Test
    public void handleIncorrectPassword() {
        String username = "username";
        Optional<UserPrincipal> userPrinciple = basicAuthenticator.authenticate(
                new BasicCredentials(username, "incorrectpassword"));

        assertThat(userPrinciple, equalTo(Optional.empty()));
    }
}
