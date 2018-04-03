package org.softwire.training.models.events;

import org.softwire.training.models.user.UserSummary;

/**
 * A Social Event represents a single item on a wall - i.e. a post
 */
public interface SocialEvent {

    /**
     * The content of the item
     */
    String getContent();

    /**
     * The author of the item
     */
    UserSummary getAuthor();

    /**
     * Path to the icon to use for this item
     */
    String getIconPath();
}
