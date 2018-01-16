package org.softwire.training.db;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.softwire.training.models.User;
import org.softwire.training.models.UserSummary;

import java.util.List;
import java.util.Optional;

/**
 * There are cleverer auto-magic ways of doing this, see http://jdbi.org/jdbi2/ in particular the section entitled SQL
 * Object API.  We've kept it simple here to avoid using fancy language features, but feel free to change that.
 */
public class UserDao {

    private final Jdbi jdbi;

    public UserDao(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public List<UserSummary> getUserSummaries() {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery("SELECT username, fullname FROM users")
                    .mapToBean(UserSummary.class)
                    .list();
        }
    }

    public Optional<User> getUser(String username) {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery("SELECT username, fullname, hashed_password FROM users WHERE username = :username")
                    .bind("username", username)
                    .mapToBean(User.class)
                    .findFirst();
        }
    }

    public void addUser(User user) {
        try (Handle handle = jdbi.open()) {
            handle.createCall("INSERT INTO users (username, fullname, hashed_password) VALUES (:username, :fullname, :hashedPassword)")
                    .bindBean(user)
                    .invoke();
        }
    }
}
