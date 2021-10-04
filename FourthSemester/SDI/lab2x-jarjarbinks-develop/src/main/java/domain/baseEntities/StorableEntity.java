package domain.baseEntities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class StorableEntity<ID, T> extends BaseEntity<ID> {
    public abstract PreparedStatement getPreparedStatement(PreparedStatement preparedStatement) throws SQLException;
    public abstract String getSqlInsertCommand();
    public abstract String getSqlDeleteCommand();
    public abstract String getSqlUpdateCommand();
    public abstract T getFromResultSet(ResultSet resultSet) throws SQLException;
}
