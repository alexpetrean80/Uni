package model.values;

import model.types.Type;

public interface Value<T>{
    Type getType();
    T getValue();
}
