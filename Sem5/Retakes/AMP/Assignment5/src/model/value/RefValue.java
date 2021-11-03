package model.value;

import model.type.RefType;
import model.type.Type;

public class RefValue extends Value{
    private final Integer address;
    private final Type locationType;

    public RefValue(Integer address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public Integer getAddress() {
        return address;
    }

    public Type getLocationType() {
        return locationType;
    }

    @Override
    public String toString() {
        return "(" + this.address.toString() + ", " + this.locationType.toString() + ")";
    }

    @Override
    public Type getType() {
        return new RefType(this.locationType);
    }

    @Override
    public Value deepCopy() {
        return new RefValue(this.address, this.locationType.deepCopy());
    }
}
