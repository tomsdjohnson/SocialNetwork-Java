package org.softwire.training.models.user;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * A UserSummary is a summary of a user, not including any sensitive information.
 *
 * Used e.g. when displaying social events.
 */
public class UserSummary {
    private String username;
    private String fullname;

    public UserSummary() {}

    public UserSummary(String username, String fullname) {
        this.username = username;
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * Below methods were automatically generated using IntelliJ and guava.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSummary)) return false;
        UserSummary user = (UserSummary) o;
        return Objects.equal(username, user.username) &&
                Objects.equal(fullname, user.fullname);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username, fullname);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("username", username)
                .add("fullname", fullname)
                .toString();
    }
}
