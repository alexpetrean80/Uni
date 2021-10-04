package domain.baseEntities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 *
 */
@javax.persistence.Entity
public class Droid extends BaseEntity<Long>  {

    private double powerUsage;
    private double price;
    private int batteryTime;
    private String model;
    private boolean driver;




    /**
     * @param powerUsage  - the power usage of the droid
     * @param price       - the price of the droid
     * @param batteryTime - hours of usage of a droid on one full charge
     * @param model       - the model of the droid
     * @param driver      - whether or not the Droid can drive space ships or not
     */
    public Droid(double powerUsage, double price, int batteryTime, String model, boolean driver) {
        this.powerUsage = powerUsage;
        this.price = price;
        this.batteryTime = batteryTime;
        this.model = model;
        this.driver = driver;
    }

    public Droid(Long id, double powerUsage, double price, int batteryTime, String model, boolean driver) {
        this.setId(id);
        this.powerUsage = powerUsage;
        this.price = price;
        this.batteryTime = batteryTime;
        this.model = model;
        this.driver = driver;
    }

    public Droid() {
        this.setId(0L);
        this.powerUsage = 0;
        this.price = 0;
        this.batteryTime = 0;
        this.model = "";
        this.driver = false;
    }

    /**
     * @return double - the powerUsage of the droid
     */
    public double getPowerUsage() {
        return powerUsage;
    }

    public void setPowerUsage(double powerUsage) {
        this.powerUsage = powerUsage;
    }

    /**
     * @return double - the price of the droid
     */
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return int - returns the battery time of a droid
     */
    public int getBatteryTime() {
        return batteryTime;
    }

    public void setBatteryTime(int batteryTime) {
        this.batteryTime = batteryTime;
    }

    /**
     * @return String - returns the model of the droid
     */
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return boolean - return the driver of the droid
     */
    public boolean getDriver() {
        return this.driver;
    }

    public void setDriver(boolean driver) {
        this.driver = driver;
    }


//delete + update droid + implement 2 more filters

    /**
     * @return int - hashCode of the droid entity
     */
    @Override
    public int hashCode() {
        return Objects.hash(powerUsage, price, batteryTime, model, driver);
    }


    /**
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
     * @return String - toString() of the Droid
     */
    @Override
    public String toString() {
        return "Droid{" +
                "id=" +this.getId() +
                "powerUsage=" + powerUsage +
                ", price=" + price +
                ", batteryTime=" + batteryTime +
                ", model='" + model + '\'' +
                ", driver=" + driver +
                '}';
    }


}
