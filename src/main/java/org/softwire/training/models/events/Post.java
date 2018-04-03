package org.softwire.training.models.events;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.softwire.training.models.user.UserSummary;

public class Post extends BaseEvent {

    private String content;

    public Post() {}

    public Post(UserSummary author, String content) {
        super(author);
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getIconPath() {
        return Icons.NOTE;
    }

    /**
     * Below methods were automatically generated using IntelliJ and guava.
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        if (!super.equals(o)) return false;
        Post post = (Post) o;
        return Objects.equal(content, post.content);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), content);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("content", content)
                .toString();
    }
}
