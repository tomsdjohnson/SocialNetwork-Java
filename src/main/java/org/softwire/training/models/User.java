package org.softwire.training.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * A user of the social network.
 */
public class User {

    /**
     * The user name uniquely identifies a user
     */
    private final String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
                .toString();
    }
}
