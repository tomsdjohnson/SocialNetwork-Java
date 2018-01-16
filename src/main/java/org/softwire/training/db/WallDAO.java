package org.softwire.training.db;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.util.StringColumnMapper;
import org.softwire.training.models.UserPrinciple;
import org.softwire.training.models.SocialEvent;
import org.softwire.training.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * There are cleverer auto-magic ways of doing this, see http://jdbi.org/jdbi2/ in particular the section entitled SQL
 * Object API.  We've kept it simple here to avoid using fancy language features, but feel free to change that.
 */
public class WallDAO {

    private final DBI dbi;

    public WallDAO(DBI dbi) {
        this.dbi = dbi;
    }

    public List<SocialEvent> readWall(User user) {
        try (Handle handle = dbi.open()) {
            return handle.createQuery("SELECT author, content FROM walls WHERE user = :user")
                    .bind("user", user.getName())
                    .map(new SocialEventMapper())
                    .list();
        }
    }

    public List<User> getAllUsers() {
        try (Handle handle = dbi.open()) {
            List<String> userNames = handle.createQuery("SELECT DISTINCT user FROM walls")
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
            handle.createCall("INSERT INTO walls (user, author, content) VALUES (:user, :author, :content)")
                    .bind("author", socialEvent.getAuthor().getName())
                    .bind("user", user.getName())
                    .bind("content", socialEvent.getContent())
                    .invoke();
        }
    }
}
