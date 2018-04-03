package org.softwire.training.models.user;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * A user of the social network, this object represents a full user - including password details.
 *
 * This is used when handling users directly. For other uses, use UserSummary.
 */
public class User extends UserSummary {

    private String hashedPassword;

    public User() {
        super();
    }

    public User(String username, String fullname, String hashedPassword) {
        super(username, fullname);
        this.hashedPassword = hashedPassword;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
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
        return Objects.equal(hashedPassword, user.hashedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), hashedPassword);
    }
}
