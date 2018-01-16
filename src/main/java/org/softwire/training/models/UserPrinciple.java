package org.softwire.training.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.security.Principal;

public class UserPrinciple implements Principal {

    private final User user;

    public UserPrinciple(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getName() {
        return getUser().getName();
    }

    /**
     * Below methods were automatically generated using intelliJ and guava.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrinciple that = (UserPrinciple) o;
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
