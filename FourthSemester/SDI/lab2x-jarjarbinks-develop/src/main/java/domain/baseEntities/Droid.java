package domain.baseEntities;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 */
public class Droid extends StorableEntity<Long, Droid> implements XMLHandler {

    private final double powerUsage;
    private final double price;
    private final int batteryTime;
    private final String model;
    private final boolean driver;


    /**
     *
     * @param powerUsage - the power usage of the droid
     * @param price - the price of the droid
     * @param batteryTime - hours of usage of a droid on one full charge
     * @param model - the model of the droid
     * @param driver - wether or not the Droid can drive space ships or not
     */
    public Droid(double powerUsage, double price, int batteryTime, String model, boolean driver) {
        this.powerUsage = powerUsage;
        this.price = price;
        this.batteryTime = batteryTime;
        this.model = model;
        this.driver = driver;
    }

    public Droid(){
        this.powerUsage = 0;
        this.price = 0;
        this.batteryTime = 0;
        this.model = "";
        this.driver = false;
    }



//delete + update droid + implement 2 more filters
    /**
     *
     * @return int - hashCode of the droid entity
     */
    @Override
    public int hashCode() {
        return Objects.hash(powerUsage, price, batteryTime, model, driver);
    }


    /**
     *
     * @param o - object the droid is compared to
     * @return boolean - true if the droids are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Droid Droid = (Droid) o;
        return Double.compare(Droid.powerUsage, powerUsage) == 0 && Double.compare(Droid.price, price) == 0 && batteryTime == Droid.batteryTime && driver == Droid.driver && Objects.equals(model, Droid.model);
    }

    /**
     *
     * @return String - toString() of the Droid
     */
    @Override
    public String toString() {
        return "Droid{" +
                "powerUsage=" + powerUsage +
                ", price=" + price +
                ", batteryTime=" + batteryTime +
                ", model='" + model + '\'' +
                ", driver=" + driver +
                '}';
    }

    public boolean isDriver() {
        return driver;
    }


    /**
     *
     * @return double - the powerUsage of the droid
     */
    public double getPowerUsage() {
        return powerUsage;
    }

    /**
     *
     * @return double - the price of the droid
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @return int - returns the battery time of a droid
     */
    public int getBatteryTime() {
        return batteryTime;
    }

    /**
     *
     * @return String - returns the model of the droid
     */
    public String getModel() {
        return model;
    }

    /**
     *
     * @return bollean - return the driver of the droid
     */
    public boolean getDriver(){
        return this.driver;
    }


    /**
     *
     * @param e - Element object that is transformed into a Droid type
     * @return Droid - the resulting Droid
     */
    @Override
    public Droid fromNode(Element e){
        Long id = Long.parseLong(e.getElementsByTagName("id").item(0).getTextContent());
        double powerUsage = Double.parseDouble(e.getElementsByTagName("powerUsage").item(0).getTextContent());
        double price = Double.parseDouble(e.getElementsByTagName("price").item(0).getTextContent());
        int batteryTime = Integer.parseInt(e.getElementsByTagName("batteryTime").item(0).getTextContent());
        String model = e.getElementsByTagName("model").item(0).getTextContent();
        boolean driver = Boolean.parseBoolean(e.getElementsByTagName("driver").item(0).getTextContent());
        Droid droid = new Droid(powerUsage, price, batteryTime, model, driver);
        droid.setId(id);
        return droid;

    }


    /**
     *
     * @param document - Document object that is transformed into an XML Element object
     * @return Element - XML object that will be printed in the xml file
     */
    @Override
    public Element intoNode(Document document) {
        Element root = document.createElement("droid");

        Element idElement = document.createElement("id");
        idElement.appendChild(document.createTextNode(this.getId().toString()));
        root.appendChild(idElement);

        Element powerUsageElement = document.createElement("powerUsage");
        powerUsageElement.appendChild(document.createTextNode(String.valueOf(powerUsage)));
        root.appendChild(powerUsageElement);

        Element priceElement = document.createElement("price");
        priceElement.appendChild(document.createTextNode(String.valueOf(price)));
        root.appendChild(priceElement);

        Element batteryTimeElement = document.createElement("batteryTime");
        batteryTimeElement.appendChild(document.createTextNode(String.valueOf(batteryTime)));
        root.appendChild(batteryTimeElement);

        Element modelElement = document.createElement("model");
        modelElement.appendChild(document.createTextNode(model));
        root.appendChild(modelElement);

        Element driverElement = document.createElement("driver");
        driverElement.appendChild(document.createTextNode(String.valueOf(driver)));
        root.appendChild(driverElement);

        return root;
    }


    @Override
    public PreparedStatement getPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setDouble(2, this.powerUsage);
        preparedStatement.setDouble(3, this.price);
        preparedStatement.setDouble(4, this.batteryTime);
        preparedStatement.setString(5, this.model);
        preparedStatement.setBoolean(6, this.driver);
        preparedStatement.setLong(1, Long.parseLong(this.getId().toString()));
        return preparedStatement;
    }

    @Override
    public String getSqlInsertCommand() {
        return "insert into \"DroidDealership\".\"Droid\"  (id, powerUsage, price, batteryTime, model, \"driver\") values (" + this.getId() + "," +
                "'" + this.powerUsage + "'" + "," + "'" + this.price + "'" + "," + this.batteryTime + "'" + "," + "'" + this.model + "'" + "," + "'" +
                this.driver + ")";
    }

    @Override
    public String getSqlDeleteCommand() {
        return "delete from \"DroidDealership\".\"Droid\"  where id = ?";
    }

    @Override
    public String getSqlUpdateCommand() {
        return "update \"DroidDealership\".\"Droid\" set powerUsage=?, price = ?, batteryTime = ?, model =?, \"driver\"=? where id=?";
    }


    @Override
    public Droid getFromResultSet(ResultSet resultSet) throws SQLException {
        Long droidID = (long) resultSet.getInt("id");
        double powerUsage = (double) resultSet.getDouble("powerUsage");
        double price = (double) resultSet.getDouble("price");
        int batteryTime = (int) resultSet.getInt("batteryTime");
        String model = (String) resultSet.getString("model");
        boolean driver = (boolean) resultSet.getBoolean("driver");

        Droid droid = new Droid(powerUsage, price, batteryTime, model, driver);
        droid.setId(droidID);
        return droid;
    }
}