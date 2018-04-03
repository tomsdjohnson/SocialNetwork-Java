package org.softwire.training.models.events;

import org.junit.jupiter.api.Test;
import org.softwire.training.db.EventType;
import org.softwire.training.models.user.UserSummary;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class EventTypeTest {

    @Test
    public void mapsPost() {
        UserSummary userSummary = new UserSummary("username", "fullname");
        String content = "content";
        Post post = new Post(userSummary, content);

        EventType eventType = EventType.getEventType(post);

        assertThat(eventType, equalTo(EventType.POST));
    }
}
