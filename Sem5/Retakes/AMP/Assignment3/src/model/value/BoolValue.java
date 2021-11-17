package model.value;

import model.type.BoolType;
import model.type.Type;

import java.util.Objects;

public class BoolValue extends Value {
    boolean value;

    public BoolValue(boolean value){
        this.value = value;
    }

    public boolean getValue(){
        return this.value;
    }

    @Override
    public Type getType(){
        return new BoolType();
    }

    public Value deepCopy(){
        return new BoolValue(this.value);
    }

    @Override
    public String toString(){
        return String.valueOf(this.value);
    }

    @Override
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        var that = (BoolValue)other;
        return Objects.equals(value, that.value);
    }
}
