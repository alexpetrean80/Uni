package model.values;

import model.types.Type;

public class IntValue implements Value<Integer> {
    private final int val;

    public IntValue(int val) {
        this.val = val;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public Integer getValue() {
        return val;
    }
}
