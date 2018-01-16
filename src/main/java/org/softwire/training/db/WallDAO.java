package org.softwire.training.db;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.models.SocialEvent;
import org.softwire.training.models.User;

import java.util.List;

public class WallDAO {

    private final Jdbi jdbi;

    public WallDAO(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public List<SocialEvent> readWall(User user) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT author, content FROM walls WHERE user = :user")
                        .bind("user", user.getName())
                        .mapToBean(SocialEvent.class)
                        .list());
    }

    public List<User> getAllUsers() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT DISTINCT user FROM walls")
                        .mapToBean(User.class)
                        .list());

    }

    public void writeOnWall(User user, SocialEvent socialEvent) {
        jdbi.useHandle(handle ->
                handle.createCall("INSERT INTO walls (user, author, content) VALUES (:user, :author, :content)")
                        .bind("author", socialEvent.getAuthor().getName())
                        .bind("user", user.getName())
                        .bind("content", socialEvent.getContent())
                        .invoke());
    }
}
