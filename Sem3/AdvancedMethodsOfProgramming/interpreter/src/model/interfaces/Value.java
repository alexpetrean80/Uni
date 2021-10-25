package model.interfaces;

public interface Value {
    Type getType();
    boolean equals(Value other);
    String toString();
}
