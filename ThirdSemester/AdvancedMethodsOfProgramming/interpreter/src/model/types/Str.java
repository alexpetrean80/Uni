package model.types;

import model.interfaces.Type;
import model.interfaces.Value;

public class Str implements Type {
    @Override
    public boolean isOf(Type other) {
        return other instanceof Str;
    }

    @Override
    public Value instantiate() {
        return new model.values.Str("");
    }

    public String toString() {
        return "Str";
    }
}
