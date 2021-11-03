package model.dto;

import javafx.beans.property.SimpleStringProperty;
import model.value.Value;

public class SymbolTableViewEntity {
    private final SimpleStringProperty name;
    private final SimpleStringProperty value;

    public SymbolTableViewEntity(String variableName, Value value) {
        this.name = new SimpleStringProperty(variableName);
        this.value = new SimpleStringProperty(value.toString());
    }

    public String getName() {
        return this.name.get();
    }

    public String getValue() {
        return this.value.get();
    }
}
