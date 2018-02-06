package org.softwire.training.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

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
        return Objects.equal(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), password);
    }
}
