package model.values;

import model.interfaces.Type;
import model.interfaces.Value;

public class Str implements Value {
    final String val;

    public Str(String val) {
        this.val = val;
    }

    public String getValue() {
        return val;
    }

    @Override
    public Type getType() {
        return new model.types.Str();
    }

    @Override
    public boolean equals(Value other) {
        return val.equals(((Str)other).val);
    }

    public String toString() {
        return val;
    }
}
