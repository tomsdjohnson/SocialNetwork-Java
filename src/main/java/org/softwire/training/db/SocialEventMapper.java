package org.softwire.training.db;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.softwire.training.models.SocialEvent;
import org.softwire.training.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SocialEventMapper implements ResultSetMapper<SocialEvent> {

    @Override
    public SocialEvent map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new SocialEvent(new User(r.getString("author")), r.getString("content"));
    }
}
