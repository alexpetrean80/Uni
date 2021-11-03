package model.type;

import model.value.StringValue;
import model.value.Value;

public class StringType implements Type{

    @Override
    public boolean equals(Object otherObject){
        return otherObject instanceof StringType;
    }

    @Override
    public String toString(){
        return "string";
    }

    @Override
    public Type deepCopy() {
        return new StringType();
    }

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }
}
