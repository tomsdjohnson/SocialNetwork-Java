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
                handle.createQuery("SELECT users.username, users.fullname, walls.content " +
                        "FROM walls " +
                        "JOIN users " +
                        "ON walls.author = users.username " +
                        "WHERE walls.username = :username")
                        .bind("username", user)
                        .mapToBean(SocialEvent.class)
                        .list());
    }

    public void writeOnWall(String user, SocialEvent socialEvent) {
        jdbi.useHandle(handle ->
            handle.createCall("INSERT INTO walls (username, author, content) " +
                        "VALUES (:username, :author.username, :content)")
                    .bindBean(socialEvent)
                    .bind("username", user)
                    .invoke());
    }
}
