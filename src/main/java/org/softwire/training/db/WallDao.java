package org.softwire.training.db;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.softwire.training.models.SocialEvent;

import java.util.List;

/**
 * The Wall DAO (Data Access Object) provides an interface for interacting with Social Events in the database.
 *
 * In particular, users of this class don't need to know any details about the database itself.
 */
public class WallDao {

    private final Jdbi jdbi;

    public WallDao(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public List<SocialEvent> readWall(String user) {
        try (Handle handle = jdbi.open()) {
            return handle.createQuery(
                       "SELECT users.username, users.fullname, social_events.content " +
                        "FROM social_events " +
                        "JOIN users " +
                        "ON social_events.author = users.username " +
                        "WHERE social_events.user = :username")
                    .bind("username", user)
                    .mapToBean(SocialEvent.class)
                    .list();
        }
    }

    public void writeOnWall(String user, SocialEvent socialEvent) {
        try (Handle handle = jdbi.open()) {
            handle.createCall("INSERT INTO social_events (user, author, content) VALUES (:username, :author.username, :content)")
                    .bindBean(socialEvent)
                    .bind("username", user)
                    .invoke();
        }
    }
}
