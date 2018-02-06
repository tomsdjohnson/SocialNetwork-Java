package org.softwire.training.db;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.models.SocialEvent;

import java.util.List;

public class WallDAO {

    private final Jdbi jdbi;

    public WallDAO(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public List<SocialEvent> readWall(String user) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT author, content FROM walls WHERE user = :user")
                        .bind("user", user)
                        .mapToBean(SocialEvent.class)
                        .list());
    }

    public List<String> getAllUsers() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT DISTINCT user FROM walls")
                        .mapTo(String.class)
                        .list());

    }

    public void writeOnWall(String user, SocialEvent socialEvent) {
        jdbi.useHandle(handle ->
                handle.createCall("INSERT INTO walls (user, author, content) VALUES (:user, :author, :content)")
                        .bindBean(socialEvent)
                        .bind("user", user)
                        .invoke());
    }
}
