package org.softwire.training.db;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import org.softwire.training.models.events.*;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The SocialEvent types used here should match:
 * * The event_type enum in bootstrapDatabase.sql
 * * The subclasses of SocialEvent
 *
 * This uses some slightly sophisticated stuff, but could be approached in a more direct way.
 */
public enum EventType {
    POST(Post.class),
    WAVE(Wave.class),
    LIKE(Like.class),
    FROWN(Frown.class);

    private final Class<? extends SocialEvent> klass;
    private final RowMapper<? extends SocialEvent> mapper;

    EventType(Class<? extends SocialEvent> klass) {
        this.klass = klass;
        this.mapper = BeanMapper.of(klass);
    }

    public RowMapper<? extends SocialEvent> getMapper() {
        return mapper;
    }

    private static final Map<Class, EventType> EVENT_TYPE_BY_CLASS = Arrays.stream(values())
            .collect(Collectors.toMap(e -> e.klass, Function.identity()));

    public static EventType getEventType(SocialEvent event) {
        return EVENT_TYPE_BY_CLASS.get(event.getClass());
    }
}
