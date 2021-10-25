package domain.adapters.db;

import domain.baseEntities.Droid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DroidAdapter implements DbAdapter<Long, Droid> {
    private Droid d;

    public DroidAdapter() {
        this.d = new Droid();
    }

    public PreparedStatement getSqlFindOneCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("SELECT * FROM \"Droid\" WHERE id = ?");
        ps.setLong(1, this.d.getId());
        return ps;
    }

    public PreparedStatement getSqlFindManyCommand(Connection conn) throws SQLException {
        return conn.prepareStatement("SELECT * FROM \"Droid\"");
    }

    public PreparedStatement getSqlInsertCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("INSERT INTO \"Droid\" (\"powerUsage\", price, \"batteryTime\", model, driver) VALUES (?,?,?,?,?)");
        ps.setDouble(1, this.d.getPowerUsage());
        ps.setDouble(2, this.d.getPrice());
        ps.setInt(3, this.d.getBatteryTime());
        ps.setString(4, this.d.getModel());
        ps.setBoolean(5, this.d.getDriver());
        return ps;
    }

    public PreparedStatement getSqlDeleteCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("DELETE FROM \"Droid\" WHERE id = ?");
        ps.setLong(1, this.d.getId());
        return ps;
    }

    public PreparedStatement getSqlUpdateCommand(Connection conn) throws SQLException {
        var ps = conn.prepareStatement("UPDATE \"Droid\" SET \"powerUsage\" = ?, price = ?, \"batteryTime\" = ?, model = ?, driver = ? WHERE id = ?");
        ps.setDouble(1, this.d.getPowerUsage());
        ps.setDouble(2, this.d.getPrice());
        ps.setInt(3, this.d.getBatteryTime());
        ps.setString(4, this.d.getModel());
        ps.setBoolean(5, this.d.getDriver());
        ps.setLong(6, this.d.getId());

        return ps;
    }

    private void reset(){
        this.d = new Droid();
    }

    public Droid getFromResultSet(ResultSet set) throws SQLException {

        this.reset();
        this.d.setPowerUsage(set.getDouble("powerUsage"));
        this.d.setId(set.getLong("id"));
        this.d.setPrice(set.getDouble("price"));
        this.d.setBatteryTime(set.getInt("batteryTime"));
        this.d.setModel(set.getString("model"));
        this.d.setDriver(set.getBoolean("driver"));
        return this.d;
    }

    public void setEntity(Droid entity) {
        this.d = entity;
    }

}
