package org.softwire.training.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.jdbi.v3.core.mapper.Nested;

public class SocialEvent {
    private String author;
    private String content;

    public SocialEvent()
    {
    }

    public SocialEvent(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     * Below methods were automatically generated using intelliJ and guava.
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
