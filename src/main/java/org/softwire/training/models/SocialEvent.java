package org.softwire.training.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * A Social Event represents a single item on a wall - i.e. a post
 */
public class SocialEvent {

    /**
     * The author of the Social Event
     */
    private final User author;

    /**
     * The text content of the Event
     */
    private final String content;

    public SocialEvent(User author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    /**
     * Below methods were automatically generated using IntelliJ and Guava.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialEvent that = (SocialEvent) o;
        return Objects.equal(author, that.author) &&
                Objects.equal(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(author, content);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("author", author)
                .add("content", content)
                .toString();
    }
}
