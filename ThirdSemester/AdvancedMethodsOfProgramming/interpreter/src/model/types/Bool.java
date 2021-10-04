package model.types;

import model.interfaces.Type;
import model.interfaces.Value;


public class Bool implements Type {
    @Override
    public boolean isOf(Type other) {
        return other instanceof Bool;
    }

    @Override
    public Value instantiate() {
        return new model.values.Bool(false);
    }

    @Override
    public String toString() {
        return "Bool";
    }
}
