package model.value;

import model.type.BoolType;
import model.type.Type;

import java.util.Objects;

public class BoolValue extends Value {
    boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    public Value deepCopy() {
        return new BoolValue(this.value);
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;
        BoolValue that = (BoolValue) otherObject;
        return Objects.equals(value, that.value);
    }
}
