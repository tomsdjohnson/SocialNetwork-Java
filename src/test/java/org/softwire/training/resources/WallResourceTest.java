package org.softwire.training.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.softwire.training.db.UserDao;
import org.softwire.training.db.WallDao;
import org.softwire.training.models.User;
import org.softwire.training.models.SocialEvent;
import org.softwire.training.models.UserPrincipal;
import org.softwire.training.models.UserSummary;
import org.softwire.training.views.WallView;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class WallResourceTest {
    private static final UserSummary LOGGED_IN_USER = new UserSummary("CharlieKelly", "Charlie Kelly");
    private static final UserPrincipal USER_PRINCIPAL = new UserPrincipal(LOGGED_IN_USER);

    private static final String SUBJECT_USERNAME = "FrankReynolds";
    private static final User SUBJECT = new User(SUBJECT_USERNAME, "Frank Reynolds", "password");

    private WallDao wallDao;
    private UserDao userDao;
    private WallResource resource;

    @BeforeEach
    public void beforeEach() {
        wallDao = mock(WallDao.class);
        userDao = mock(UserDao.class);

        when(userDao.getUser(SUBJECT_USERNAME)).thenReturn(Optional.of(SUBJECT));

        resource = new WallResource(wallDao, userDao);
    }

    @Test
    public void getRequestDisplaysWallContents() {
        List<SocialEvent> events = Collections.singletonList(
                new SocialEvent(new UserSummary("RonaldMcDonald", "Ronald McDonald"), "What's up?"));
        when(wallDao.readWall(SUBJECT_USERNAME)).thenReturn(events);

        WallView wallView = resource.get(USER_PRINCIPAL, SUBJECT_USERNAME);

        assertThat(wallView.getSocialEvents(), equalTo(events));
    }

    @Test
    public void getRequestDisplaysLoggedInUser() {
        WallView wallView = resource.get(USER_PRINCIPAL, SUBJECT_USERNAME);

        assertThat(wallView.getLoggedInUser(), equalTo(LOGGED_IN_USER));
    }

    @Test
    public void getRequestDisplaysUser() {
        WallView wallView = resource.get(USER_PRINCIPAL, SUBJECT_USERNAME);

        assertThat(wallView.getSubject(), equalTo(SUBJECT));
    }

    @Test
    public void getReturns404OnNonexistentUser() {
        when(userDao.getUser(SUBJECT_USERNAME)).thenReturn(Optional.empty());

        try {
            resource.get(USER_PRINCIPAL, SUBJECT_USERNAME);
            fail("Expected WebApplicationException to be thrown");
        } catch (WebApplicationException e) {
            assertThat(e.getResponse().getStatus(), equalTo(404));
        }
    }

    @Test
    public void postRequestWritesToWall() {
        String content = "It's always sunny";
        resource.post(USER_PRINCIPAL, SUBJECT_USERNAME, content);

        verify(wallDao, times(1))
                .writeOnWall(SUBJECT_USERNAME, new SocialEvent(LOGGED_IN_USER, content));
    }

    @Test
    public void postRequestRedirectsBackToWall() {
        Response response = resource.post(USER_PRINCIPAL, SUBJECT_USERNAME, "Some random content");

        assertThat(response.getStatus(), equalTo(Response.Status.SEE_OTHER.getStatusCode()));
        assertThat(response.getLocation().getPath(), equalTo("/wall/" + SUBJECT_USERNAME));
    }
}
