package org.softwire.training.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class UserSummary {
    private String username;
    private String fullname;

    public UserSummary() {
    }

    public UserSummary(String username, String fullname) {
        this.username = username;
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * Below methods were automatically generated using intelliJ and guava.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSummary that = (UserSummary) o;
        return Objects.equal(username, that.username) &&
                Objects.equal(fullname, that.fullname);
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
