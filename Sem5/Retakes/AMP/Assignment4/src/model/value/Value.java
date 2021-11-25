package model.value;

import exception.MyException;
import model.type.Type;

public abstract class Value {

    public static int toInteger(Value value) {
        if (!(value instanceof IntValue)) {
            throw new MyException("Not an integer!");
        }
        return ((IntValue) value).getValue();
    }

    public static Value fromInteger(int value) {
        return new IntValue(value);
    }

    public static boolean toBoolean(Value value) {
        if (!(value instanceof BoolValue)) {
            throw new MyException("Not a boolean!");
        }
        return ((BoolValue) value).getValue();
    }

    public static Value fromBoolean(boolean value) {
        return new BoolValue(value);
    }

    public static String toString(Value value) {
        if (!(value instanceof StringValue)) {
            throw new MyException("Not a boolean!");
        }
        return ((StringValue) value).getValue();
    }

    public static Value fromString(String value) {
        return new StringValue(value);
    }

    abstract public Type getType();

    abstract public Value deepCopy();
}
