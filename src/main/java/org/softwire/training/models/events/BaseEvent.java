package org.softwire.training.models.events;

import com.google.common.base.Objects;
import org.jdbi.v3.core.mapper.Nested;
import org.softwire.training.models.user.UserSummary;

/**
 * Common base class for Social Events which have an author
 */
abstract class BaseEvent implements SocialEvent {

    private UserSummary author;

    protected BaseEvent() {}

    protected BaseEvent(UserSummary author) {
        this.author = author;
    }

    @Override
    @Nested
    public UserSummary getAuthor() {
        return author;
    }

    public void setAuthor(UserSummary author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEvent event = (BaseEvent) o;
        return Objects.equal(getAuthor(), event.getAuthor()) &&
                Objects.equal(getContent(), event.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                getAuthor(),
                getContent());
    }
}
