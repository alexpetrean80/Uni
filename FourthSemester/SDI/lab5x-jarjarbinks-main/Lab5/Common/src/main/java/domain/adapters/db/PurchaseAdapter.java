package domain.adapters.db;

import domain.baseEntities.Purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PurchaseAdapter implements DbAdapter<Long, Purchase> {
    private Purchase p;

    public PurchaseAdapter() {
        this.p = new Purchase();
    }

    public PreparedStatement getSqlFindOneCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("SELECT * FROM \"Purchase\" WHERE id = ?");
        ps.setLong(1, this.p.getId());
        return ps;
    }

    public PreparedStatement getSqlFindManyCommand(Connection conn) throws SQLException {
        return conn.prepareStatement("SELECT * FROM \"Purchase\"");
    }

    public PreparedStatement getSqlInsertCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("INSERT INTO \"Purchase\" (client_id, droid_id, totalprice) values (?,?,?)");
        ps.setLong(1, this.p.getClientId());
        ps.setLong(2, this.p.getDroidId());
        ps.setDouble(3, this.p.getTotalPrice());
        return ps;
    }

    public PreparedStatement getSqlDeleteCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("DELETE FROM \"Purchase\" WHERE id = ?");
        ps.setLong(1, this.p.getId());
        return ps;
    }

    public PreparedStatement getSqlUpdateCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("UPDATE \"Purchase\" SET client_id = ?, droid_id = ?, totalprice = ? WHERE id = ?");
        ps.setLong(1, this.p.getClientId());
        ps.setLong(2, this.p.getDroidId());
        ps.setDouble(3, this.p.getTotalPrice());
        ps.setLong(4, this.p.getId());
        return ps;
    }

    public Purchase getFromResultSet(ResultSet set) throws SQLException {
        this.reset();
        this.p.setId(set.getLong("id"));
        this.p.setClientId(set.getLong("client_id"));
        this.p.setDroidId(set.getLong("droid_id"));
        this.p.setTotalPrice(set.getDouble("totalprice"));


        return this.p;
    }


    private void reset() {
        this.p = new Purchase();
    }

    public void setEntity(Purchase entity) {
        this.p = entity;
    }
}
