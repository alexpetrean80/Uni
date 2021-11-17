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

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        var stringValue = (StringValue)other;
        return Objects.equals(this.value, stringValue.value);
    }
}
