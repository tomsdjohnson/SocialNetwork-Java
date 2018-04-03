package org.softwire.training.db;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.util.StringColumnMapper;
import org.softwire.training.models.SocialEvent;
import org.softwire.training.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The Social Events DAO (Data Access Object) provides an interface for interacting with Social Events in the database.
 *
 * In particular, users of this class don't need to know any details about the database itself.
 */
public class WallDao {

    private final DBI dbi;

    public WallDao(DBI dbi) {
        this.dbi = dbi;
    }

    public List<SocialEvent> readWall(User user) {
        try (Handle handle = dbi.open()) {
            return handle.createQuery("SELECT author, content FROM social_events WHERE user = :user")
                    .bind("user", user.getName())
                    .map(new SocialEventMapper())
                    .list();
        }
    }

    public List<User> getAllUsers() {
        try (Handle handle = dbi.open()) {
            List<String> userNames = handle.createQuery("SELECT DISTINCT user FROM social_events")
                    .map(StringColumnMapper.INSTANCE)
                    .list();

            List<User> users = new ArrayList<>();
            for (String userName: userNames) {
                users.add(new User(userName));
            }
            return users;
        }
    }

    public void writeOnWall(User user, SocialEvent socialEvent) {
        try (Handle handle = dbi.open()) {
            handle.createCall("INSERT INTO social_events (user, author, content) VALUES (:user, :author, :content)")
                    .bind("author", socialEvent.getAuthor().getName())
                    .bind("user", user.getName())
                    .bind("content", socialEvent.getContent())
                    .invoke();
        }
    }
}
