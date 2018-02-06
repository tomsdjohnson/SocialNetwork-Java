package org.softwire.training.views;

import io.dropwizard.views.View;
import org.softwire.training.models.SocialEvent;

import java.util.List;

public class WallView extends View {

    private final List<SocialEvent> socialEvents;

    private final String subject;
    private final String loggedInUser;

    public WallView(
            List<SocialEvent> socialEvents,
            String subject,
            String loggedInUser) {
        super("WallView.ftl");
        this.socialEvents = socialEvents;
        this.subject = subject;
        this.loggedInUser = loggedInUser;
    }

    public List<SocialEvent> getSocialEvents() {
        return socialEvents;
    }

    public String getSubject() {
        return subject;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }
}
