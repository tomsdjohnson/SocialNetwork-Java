package org.softwire.training.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.softwire.training.db.WallDAO;
import org.softwire.training.models.UserPrincipal;
import org.softwire.training.models.User;
import org.softwire.training.views.HomePageView;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HomePageResourceTest {
    private static final User LOGGED_IN_USER = new User("jerry");
    private static final UserPrincipal USER_PRINCIPLE = new UserPrincipal(LOGGED_IN_USER);
    private static final List<User> USERS = Arrays.asList(new User("rick"), new User("morty"));

    private final WallDAO wallDAO = mock(WallDAO.class);
    private final HomePageResource resource = new HomePageResource(wallDAO);

    @BeforeEach
    public void beforeAll() {
        when(wallDAO.getAllUsers()).thenReturn(USERS);
    }

    @Test
    public void displaysAllKnownUsers() {
        HomePageView homePageView = resource.get(USER_PRINCIPLE);

        assertThat(homePageView.getUsers(), equalTo(USERS));
    }

    @Test void displaysLoggedInUser() {
        HomePageView homePageView = resource.get(USER_PRINCIPLE);

        assertThat(homePageView.getLoggedInUser(), equalTo(LOGGED_IN_USER));
    }
}
