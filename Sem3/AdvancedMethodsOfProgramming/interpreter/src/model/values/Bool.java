package model.values;

import model.interfaces.Type;
import model.interfaces.Value;

public class Bool implements Value {
    final boolean val;

    public Bool(boolean val) {
        this.val = val;
    }

    public boolean getValue() {
        return val;
    }

    @Override
    public Type getType() {
        return new model.types.Bool();
    }

    @Override
    public boolean equals(Value other) {
        return val == ((Bool)other).val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
