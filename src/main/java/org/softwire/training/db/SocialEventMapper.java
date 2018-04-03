package org.softwire.training.db;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.softwire.training.models.SocialEvent;
import org.softwire.training.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for mapping Database results into Social Events
 *
 * There are cleverer auto-magic ways of doing this, see http://jdbi.org/jdbi2/ in particular the section entitled SQL
 * Object API. We've kept it simple here, but feel free to use more sophisticated techniques if you prefer.
 */
public class SocialEventMapper implements ResultSetMapper<SocialEvent> {

    @Override
    public SocialEvent map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new SocialEvent(new User(r.getString("author")), r.getString("content"));
    }
}
