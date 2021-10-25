package model.interfaces;

public interface Type {
    boolean isOf(Type other);
    Value instantiate();
    String toString();
}
