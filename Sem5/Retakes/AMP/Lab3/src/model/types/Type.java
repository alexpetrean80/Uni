package model.types;

import model.values.Value;

public interface Type {
    public boolean equals(Object another);
    public String toString();
    public Value defaultValue();
    public String getType();
}
