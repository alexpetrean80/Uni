package model.dto;

import javafx.beans.property.SimpleStringProperty;
import model.value.Value;

public class HeapTableViewEntity {
    private final SimpleStringProperty addressProperty;
    private final SimpleStringProperty valueProperty;


    public HeapTableViewEntity(Integer address, Value value) {
        this.addressProperty = new SimpleStringProperty(address.toString());
        this.valueProperty = new SimpleStringProperty(value.toString());
    }
    public String getAddressProperty() {
        return this.addressProperty.get();
    }

    public String getValueProperty() {
        return this.valueProperty.get();
    }
}
