package org.softwire.training.db;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.models.User;
import org.softwire.training.models.UserSummary;

import java.util.List;
import java.util.Optional;

public class UserDAO {

    private final Jdbi jdbi;

    public UserDAO(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public List<UserSummary> getUserSummaries() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT username, fullname FROM users")
                        .mapToBean(UserSummary.class)
                        .list());
    }

    public Optional<User> getUser(String username) {
        return jdbi.withHandle(handle ->
            handle.createQuery("SELECT username, fullname, hashedpassword " +
                                    "FROM users WHERE username = :username")
                    .bind("username", username)
                    .mapToBean(User.class)
                    .findFirst());
    }

    public void addUser(User user) {
        jdbi.useHandle(handle ->
            handle.createCall("INSERT INTO users (username, fullname, hashedpassword) " +
                                   "VALUES (:username, :fullname, :hashedPassword)")
                    .bindBean(user)
                    .invoke());
    }
}
