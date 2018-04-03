package org.softwire.training.models.events;

import org.softwire.training.models.user.UserSummary;

public class Like extends BaseEvent {

    public Like() {}

    public Like(UserSummary author) {
        super(author);
    }

    @Override
    public String getContent() {
        return null;
    }

    public void setContent(String content) {
        if (content != null) {
            throw new IllegalArgumentException("This type of event may not have content");
        }
    }

    @Override
    public String getIconPath() {
        return Icons.HEART;
    }
}
