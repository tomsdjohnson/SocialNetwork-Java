package org.softwire.training.resources;

import com.muquit.libsodiumjna.exceptions.SodiumLibraryException;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.softwire.training.core.PasswordHasher;
import org.softwire.training.db.UserDAO;
import org.softwire.training.models.User;
import org.softwire.training.views.NewUserView;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.sql.SQLIntegrityConstraintViolationException;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class NewUserResourceTest {

    private static final String USERNAME = "username";
    private static final String FULLNAME = "fullname";
    private static final String PASSWORD = "password";
    private static final String HASHED_PASSWORD = "hashedpassword";

    private UserDAO userDAO;
    private PasswordHasher passwordHasher;
    private NewUserResource resource;

    @BeforeEach
    public void beforeEach() throws Exception {
        userDAO = mock(UserDAO.class);
        passwordHasher = mock(PasswordHasher.class);

        when(passwordHasher.hash(PASSWORD)).thenReturn(HASHED_PASSWORD);

        resource = new NewUserResource(userDAO, passwordHasher);
    }

    @Test
    public void getReturnsNewUserView() {
        NewUserView newUserView = resource.get();

        assertThat(newUserView.getError(), nullValue());
    }

    @Test
    public void postCreatesNewUser() {
        resource.post(USERNAME, FULLNAME, PASSWORD);

        verify(userDAO, times(1))
                .addUser(new User(USERNAME, FULLNAME, HASHED_PASSWORD));
    }

    @Test
    public void postRedirectsToHomePage() {
        Response response = resource.post(USERNAME, FULLNAME, PASSWORD);

        assertThat(response.getStatus(), equalTo(Response.Status.SEE_OTHER.getStatusCode()));
        assertThat(response.getLocation().getPath(), equalTo("/home"));
    }

    @Test
    public void returnsErrorOnSQLIntegrityConstraintViolationException() {
        UnableToExecuteStatementException exception = new UnableToExecuteStatementException(
                new SQLIntegrityConstraintViolationException(), mock(StatementContext.class));
        doThrow(exception)
                .when(userDAO)
                .addUser(new User(USERNAME, FULLNAME, HASHED_PASSWORD));

        Response response = resource.post(USERNAME, FULLNAME, PASSWORD);

        assertThat(response.getEntity(), instanceOf(NewUserView.class));
        NewUserView newUserView = (NewUserView) response.getEntity();
        assertThat(newUserView.getError(), notNullValue());
    }

    @Test
    public void reThrowsErrorOnGenericUnableToExecuteStatementException() {
        UnableToExecuteStatementException exception = new UnableToExecuteStatementException(
                new Exception(), mock(StatementContext.class));
        doThrow(exception)
                .when(userDAO)
                .addUser(new User(USERNAME, FULLNAME, HASHED_PASSWORD));

        try {
            resource.post(USERNAME, FULLNAME, PASSWORD);
            fail("Expected exception to be thrown");
        } catch (Exception e) {
            assertThat(e, equalTo(exception));
        }
    }

    @Test
    public void returns500OnLibsodiumError() throws Exception {
        when(passwordHasher.hash(PASSWORD)).thenThrow(new SodiumLibraryException("Oh dear"));

        try {
            resource.post(USERNAME, FULLNAME, PASSWORD);
            fail("Expected WebApplicationException to be thrown");
        } catch (WebApplicationException e) {
            assertThat(e.getResponse().getStatus(), equalTo(500));
        }
    }
}
