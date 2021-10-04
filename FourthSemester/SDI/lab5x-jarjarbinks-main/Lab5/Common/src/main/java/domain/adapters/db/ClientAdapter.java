package domain.adapters.db;

import domain.baseEntities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientAdapter implements DbAdapter<Long, Client> {
    private Client c;

    public ClientAdapter() {
        this.c = new Client();
    }

    public PreparedStatement getSqlFindOneCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("SELECT * FROM \"Client\" WHERE id = ?");
        ps.setLong(1, this.c.getId());
        return ps;
    }

    public PreparedStatement getSqlFindManyCommand(Connection conn) throws SQLException {
        return conn.prepareStatement("SELECT * FROM \"Client\"");
    }

    public PreparedStatement getSqlInsertCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("INSERT INTO \"Client\" (name, address, phoneNumber) VALUES (?,?,?)");
        ps.setString(1, this.c.getName());
        ps.setString(2, this.c.getAddress());
        ps.setString(3, this.c.getPhoneNumber());
        return ps;
    }

    public PreparedStatement getSqlDeleteCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("DELETE FROM \"Client\"  WHERE id = ?");
        ps.setLong(1, this.c.getId());
        return ps;
    }

    public PreparedStatement getSqlUpdateCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("UPDATE \"Client\" SET name = ?, address = ?, phoneNumber = ? where id = ?");
        ps.setString(1, this.c.getName());
        ps.setString(2, this.c.getAddress());
        ps.setString(3, c.getPhoneNumber());
        ps.setLong(4, this.c.getId());

        return ps;
    }

    public Client getFromResultSet(ResultSet set) throws SQLException {
        this.reset();
        this.c.setId(set.getLong("id"));
        this.c.setName(set.getString("name"));
        this.c.setAddress(set.getString("address"));
        this.c.setPhoneNumber(set.getString("phoneNumber"));

        return this.c;
    }


    private void reset() {
        this.c = new Client();
    }

    public void setEntity(Client entity) {
        this.c = entity;
    }
}
