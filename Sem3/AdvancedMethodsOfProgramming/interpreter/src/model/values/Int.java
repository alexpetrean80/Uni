package model.values;

import model.interfaces.Type;
import model.interfaces.Value;

public class Int implements Value {
    final int val;

    public Int(int val) {
        this.val = val;
    }

    public int getValue() {
        return val;
    }

    @Override
    public Type getType() {
        return new model.types.Int();
    }

    @Override
    public boolean equals(Value other) {
        return val == ((Int)other).val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
