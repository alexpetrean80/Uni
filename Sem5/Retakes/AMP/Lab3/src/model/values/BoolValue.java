package model.values;

import model.types.Type;

public class BoolValue implements Value<Boolean> {
    private final boolean val;

    private BoolValue(boolean val) {
        this.val = val;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public Boolean getValue() {
        return val;
    }
}
