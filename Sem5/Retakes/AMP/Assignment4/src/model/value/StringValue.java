package model.value;

import model.type.StringType;
import model.type.Type;

import java.util.Objects;

public class StringValue extends Value{
    private final String value;

    public StringValue(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    @Override
    public Value deepCopy() {
        return new StringValue(this.value);
    }

    @Override
    public String toString(){
        return this.value;
    }

    public boolean equals(Object otherObject){
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;
        StringValue that = (StringValue)otherObject;
        return Objects.equals(value, that.value);
    }
}
