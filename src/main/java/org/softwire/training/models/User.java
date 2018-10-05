package org.softwire.training.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

/**
 * A user of the social network.
 */
public class User {

    /**
     * The user name uniquely identifies a user
     */
    private String name;
    private int id;
    private String username;
    private String password;



    public User() {}

    public User(String username) {
        this.username = username;
    }


    //getters//
    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }

    //setters//
    public void setId(int id) {
        this.id = id;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Below methods were automatically generated using IntelliJ and Guava.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("id", id)
                .add("username", username)
                .toString();
    }

}
