package org.softwire.training.resources;

import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.junit.jupiter.api.Test;
import org.softwire.training.db.UserDao;
import org.softwire.training.models.User;
import org.softwire.training.views.NewUserView;

import javax.ws.rs.core.Response;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class NewUserResourceTest {

    private final UserDao userDao = mock(UserDao.class);
    private final NewUserResource resource = new NewUserResource(userDao);

    @Test
    public void getReturnsNewUserView() {
        NewUserView newUserView = resource.get();

        assertThat(newUserView.getError(), nullValue());
    }

    @Test
    public void postCreatesNewUser() {
        resource.post("username", "fullname", "password");

        verify(userDao, times(1))
                .addUser(new User("username", "fullname", "password"));
    }

    @Test
    public void postRedirectsToHomePage() {
        Response response = resource.post("username", "fullname", "password");

        assertThat(response.getStatus(), equalTo(Response.Status.SEE_OTHER.getStatusCode()));
        assertThat(response.getLocation().getPath(), equalTo("/home"));
    }

    @Test
    public void returnsErrorOnSQLIntegrityConstraintViolationException() {
        UnableToExecuteStatementException exception = new UnableToExecuteStatementException(
                new SQLIntegrityConstraintViolationException(), mock(StatementContext.class));
        doThrow(exception)
                .when(userDao)
                .addUser(new User("username", "fullname", "password"));

        Response response = resource.post("username", "fullname", "password");

        assertThat(response.getEntity(), instanceOf(NewUserView.class));
        NewUserView newUserView = (NewUserView) response.getEntity();
        assertThat(newUserView.getError(), notNullValue());
    }

    @Test
    public void reThrowsErrorOnGenericUnableToExecuteStatementException() {
        UnableToExecuteStatementException exception = new UnableToExecuteStatementException(
                new Exception(), mock(StatementContext.class));
        doThrow(exception)
                .when(userDao)
                .addUser(new User("username", "fullname", "password"));

        try {
            resource.post("username", "fullname", "password");
            fail("Expected exception to be thrown");
        } catch (Exception e) {
            assertThat(e, equalTo(exception));
        }
    }
}
