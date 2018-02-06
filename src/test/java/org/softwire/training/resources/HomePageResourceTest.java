package org.softwire.training.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.softwire.training.db.UserDAO;
import org.softwire.training.models.UserPrincipal;
import org.softwire.training.models.UserSummary;
import org.softwire.training.views.HomePageView;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HomePageResourceTest {
    private static final UserSummary LOGGED_IN_USER = new UserSummary("jerry", "Jerry Smith");
    private static final UserPrincipal USER_PRINCIPAL = new UserPrincipal(LOGGED_IN_USER);
    private static final List<UserSummary> USERS = Arrays.asList(
            new UserSummary("rick", "Rick Sanchez"), new UserSummary("morty", "Morty Smith"));

    private final UserDAO userDAO = mock(UserDAO.class);
    private final HomePageResource resource = new HomePageResource(userDAO);

    @BeforeEach
    public void beforeAll() {
        when(userDAO.getUserSummaries()).thenReturn(USERS);
    }

    @Test
    public void displaysAllKnownUsers() {
        HomePageView homePageView = resource.get(USER_PRINCIPAL);

        assertThat(homePageView.getUsers(), equalTo(USERS));
    }

    @Test void displaysLoggedInUser() {
        HomePageView homePageView = resource.get(USER_PRINCIPAL);

        assertThat(homePageView.getLoggedInUser(), equalTo(LOGGED_IN_USER));
    }
}
