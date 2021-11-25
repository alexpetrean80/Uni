package model.type;


import model.value.Value;

public interface Type {
    boolean equals(Object otherObject);

    String toString();

    Type deepCopy();

    Value defaultValue();
}
