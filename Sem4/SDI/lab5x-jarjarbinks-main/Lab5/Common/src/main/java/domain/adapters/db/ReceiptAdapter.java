package domain.adapters.db;

import domain.baseEntities.Receipt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReceiptAdapter implements DbAdapter<Long, Receipt> {
    private Receipt r;

    public ReceiptAdapter() {
            this.r = new Receipt();
    }

    public PreparedStatement getSqlFindOneCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("SELECT * FROM \"Receipt\" WHERE id = ?");
        ps.setLong(1, this.r.getId());
        return ps;
    }

    public PreparedStatement getSqlFindManyCommand(Connection conn) throws SQLException {
        return conn.prepareStatement("SELECT * FROM \"Receipt\"");
    }

    public PreparedStatement getSqlInsertCommand(Connection conn) throws SQLException {

        var ps = conn.prepareStatement("INSERT INTO \"Receipt\" ( purchase_id, totalprice) values (?,?)");
        ps.setLong(1, this.r.getPurchaseID());
        ps.setDouble(2, this.r.getTotalPrice());
        return ps;
    }

    /**
     * @return PreparedStatement
     */
    public PreparedStatement getSqlDeleteCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("DELETE FROM \"Receipt\" WHERE id = ?");
        ps.setLong(1, this.r.getId());
        return ps;

    }

    /**
     * @return PreparedStatement
     */
    public PreparedStatement getSqlUpdateCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("UPDATE \"Receipt\" SET purchase_id = ?, totalPrice = ? WHERE id = ?");
        ps.setLong(1, this.r.getPurchaseID());
        ps.setDouble(2, this.r.getTotalPrice());
        ps.setLong(3, this.r.getId());
        return ps;
    }

    /**
     * @param set ResultSet
     * @return PreparedStatement
     * @throws SQLException Exception
     */
    public Receipt getFromResultSet(ResultSet set) throws SQLException {
        this.reset();
        this.r.setId(set.getLong("id"));
        this.r.setPurchaseID(set.getLong("purchase_id"));
        this.r.setTotalPrice(set.getDouble("totalPrice"));

        return this.r;
    }


    private void reset() {
        this.r = new Receipt();
    }

    public void setEntity(Receipt entity) {
        this.r = entity;
    }


}
