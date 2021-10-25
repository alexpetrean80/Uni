package model.types;

import model.interfaces.Type;
import model.interfaces.Value;

public class Int implements Type {
    @Override
    public boolean isOf(Type other) {
        return other instanceof Int;    }

    @Override
    public Value instantiate() {
        return new model.values.Int(0);
    }

    public String toString() {
        return "Int";
    }
}
