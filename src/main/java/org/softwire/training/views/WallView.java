package org.softwire.training.views;

import io.dropwizard.views.View;
import org.softwire.training.models.SocialEvent;
import org.softwire.training.models.UserSummary;

import java.util.List;

public class WallView extends View {

    private final List<SocialEvent> socialEvents;

    private final UserSummary subject;
    private final UserSummary loggedInUser;

    public WallView(
            List<SocialEvent> socialEvents,
            UserSummary subject,
            UserSummary loggedInUser) {
        super("WallView.mustache");
        this.socialEvents = socialEvents;
        this.subject = subject;
        this.loggedInUser = loggedInUser;
    }

    public List<SocialEvent> getSocialEvents() {
        return socialEvents;
    }

    public UserSummary getSubject() {
        return subject;
    }

    public UserSummary getLoggedInUser() {
        return loggedInUser;
    }
}
