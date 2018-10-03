package org.softwire.training.db;


import org.jdbi.v3.core.Jdbi;



/**
 * The Wall DAO (Data Access Object) provides an interface for interacting with Social Events in the database.
 *
 * In particular, users of this class don't need to know any details about the database itself.
 */
public class UserDao {

    private final Jdbi jdbi;

    public UserDao(Jdbi jdbi) {this.jdbi = jdbi;}

    public void addUser (String username, String password, String fullname){
        try {
            jdbi.withHandle(handle ->
                    handle.createUpdate("INSERT INTO socialnetwork.users (username, fullName, password) VALUES (:username, :fullName, :password)")
                            .bind("username", username)
                            .bind("password", password)
                            .bind("fullName", fullname)
                            .execute()
            );
        } catch (Exception e) {}

    }
}


