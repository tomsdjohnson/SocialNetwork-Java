package org.softwire.training.views;

import io.dropwizard.views.View;
import org.softwire.training.models.SocialEvent;
import org.softwire.training.models.User;

import java.util.List;

public class WallView extends View {

    private final List<SocialEvent> socialEvents;

    private final User subject;
    private final User loggedInUser;

    public WallView(
            List<SocialEvent> socialEvents,
            User subject,
            User loggedInUser) {
        super("WallView.mustache");
        this.socialEvents = socialEvents;
        this.subject = subject;
        this.loggedInUser = loggedInUser;
    }

    public List<SocialEvent> getSocialEvents() {
        return socialEvents;
    }

    public User getSubject() {
        return subject;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
