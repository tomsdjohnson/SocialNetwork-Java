package org.softwire.training.db;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.softwire.training.models.events.SocialEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for mapping Database results into Social Events that delegates to a specific mapper based on the event type.
 */
public class SocialEventMapper implements RowMapper<SocialEvent> {
    @Override
    public SocialEvent map(ResultSet rs, StatementContext ctx) throws SQLException {
        RowMapper<? extends SocialEvent> mapper = EventType.valueOf(rs.getString("event_type")).getMapper();
        return mapper.map(rs, ctx);
    }
}
