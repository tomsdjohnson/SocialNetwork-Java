package org.softwire.training.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * A user of the social network, this object represents a full user - including password details.
 *
 * This is used when handling users directly. For other uses, use UserSummary.
 */
public class User extends UserSummary {

    private String password;

    public User() {
        super();
    }

    public User(String username, String fullname, String password) {
        super(username, fullname);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Below methods were automatically generated using IntelliJ and Guava.
     */

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("username", getUsername())
                .add("fullname", getFullname())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equal(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), password);
    }
}
