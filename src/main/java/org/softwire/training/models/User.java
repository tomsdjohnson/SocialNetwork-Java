package org.softwire.training.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("username", getUsername())
                .add("fullname", getFullname())
                .toString();
    }

    /**
     * Below methods were automatically generated using intelliJ and guava.
     */

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
