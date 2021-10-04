package domain.adapters.db;

import domain.baseEntities.BaseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DbAdapter<ID, T extends BaseEntity<ID>> {
    PreparedStatement getSqlFindOneCommand(Connection conn) throws SQLException;

    PreparedStatement getSqlFindManyCommand(Connection conn) throws SQLException;

    PreparedStatement getSqlInsertCommand(Connection conn) throws SQLException;

    PreparedStatement getSqlDeleteCommand(Connection conn) throws SQLException;

    PreparedStatement getSqlUpdateCommand(Connection conn) throws SQLException;

    T getFromResultSet(ResultSet set) throws SQLException;

    private void reset() {

    }

    void setEntity(T entity);
}
