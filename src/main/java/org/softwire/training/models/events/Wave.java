package org.softwire.training.models.events;

import org.softwire.training.models.user.UserSummary;

public class Wave extends BaseEvent {

    public Wave() {}

    public Wave(UserSummary author) {
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
        return Icons.WAVE;
    }
}
