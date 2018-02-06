package org.softwire.training.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.security.Principal;

public class UserPrincipal implements Principal {

    private String user;

    public UserPrincipal() {
    }

    public UserPrincipal(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return getUser();
    }

    /**
     * Below methods were automatically generated using intelliJ and guava.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equal(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(user);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", user)
                .toString();
    }
}
