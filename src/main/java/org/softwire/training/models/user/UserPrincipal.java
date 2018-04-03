package org.softwire.training.models.user;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.security.Principal;

/**
 * In authentication, the 'Principal' is an entity which can be authenticated.
 *
 * In our case this is just a user, we use a separate class to distinguish users in an authentication context.
 */
public class UserPrincipal implements Principal {

    private final UserSummary user;

    public UserPrincipal(UserSummary user) {
        this.user = user;
    }

    public UserSummary getUser() {
        return user;
    }

    @Override
    public String getName() {
        return getUser().getUsername();
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
