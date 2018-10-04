package org.softwire.training.db;


import com.sun.corba.se.spi.servicecontext.UEInfoServiceContext;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.softwire.training.models.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


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

    public void checkUser (String username, String password){
        try {
            jdbi.withHandle(handle ->
                    handle.createUpdate("INSERT INTO socialnetwork.users (username, fullName, password) VALUES (:username, :fullName, :password)")
                            .bind("username", username)
                            .bind("password", password)
                            .execute()
            );
        } catch (Exception e) {}

    }

    public List<User> getAllUsers (){
        try (Handle handle = jdbi.open()) {
                    return handle.createQuery("SELECT * FROM users")
                    .mapToBean(User.class)
                    .list();
        }
    }



    public String hashString(String target, String alg){

        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance(alg);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        messageDigest.update(target.getBytes());
        return new String(messageDigest.digest());

    }



}


