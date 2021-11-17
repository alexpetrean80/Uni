package model.value;

import model.type.IntType;
import model.type.Type;

public class IntValue extends Value {
    private final int value;

    public IntValue(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    @Override
   public Type getType(){
        return new IntType();
   }

   @Override
    public Value deepCopy() {
        return  new IntValue(this.value);
    }

    @Override
    public String toString(){
        return String.valueOf(this.value);
    }

    public boolean equals(Object other){
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        var that = (IntValue)other;
        return value == that.value;
    }
}
