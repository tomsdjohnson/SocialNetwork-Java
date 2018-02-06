package org.softwire.training.resources;

import org.junit.jupiter.api.Test;
import org.softwire.training.db.WallDAO;
import org.softwire.training.models.UserPrincipal;
import org.softwire.training.models.SocialEvent;
import org.softwire.training.views.WallView;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class WallResourceTest {
    private static final String LOGGED_IN_USER = "CharlieKelly";
    private static final UserPrincipal USER_PRINCIPLE = new UserPrincipal(LOGGED_IN_USER);
    private static final String USER = "FrankReynolds";

    private final WallDAO wallDAO = mock(WallDAO.class);
    private final WallResource resource = new WallResource(wallDAO);

    @Test
    public void getRequestDisplaysWallContents() {
        List<SocialEvent> events = Collections.singletonList(
                new SocialEvent("RonaldMcDonald", "What's up?"));
        when(wallDAO.readWall(USER)).thenReturn(events);

        WallView wallView = resource.get(USER_PRINCIPLE, USER);

        assertThat(wallView.getSocialEvents(), equalTo(events));
    }

    @Test void getRequestDisplaysLoggedInUser() {
        WallView wallView = resource.get(USER_PRINCIPLE, USER);

        assertThat(wallView.getLoggedInUser(), equalTo(LOGGED_IN_USER));
    }

    @Test void getRequestDisplaysUser() {
        WallView wallView = resource.get(USER_PRINCIPLE, USER);

        assertThat(wallView.getSubject(), equalTo(USER));
    }

    @Test void postRequestWritesToWall() {
        String content = "It's always sunny";
        resource.post(USER_PRINCIPLE, USER, content);

        verify(wallDAO, times(1))
                .writeOnWall(USER, new SocialEvent(LOGGED_IN_USER, content));
    }

    @Test void postRequestRedirectsBackToWall() {
        Response response = resource.post(USER_PRINCIPLE, USER, "Some random content");

        assertThat(response.getStatus(), equalTo(Response.Status.SEE_OTHER.getStatusCode()));
        assertThat(response.getLocation().getPath(), equalTo("/wall/" + USER));
    }
}
