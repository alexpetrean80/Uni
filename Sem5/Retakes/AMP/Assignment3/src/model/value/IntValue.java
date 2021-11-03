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

    public boolean equals(Object otherObject){
        if (this == otherObject)
            return true;
        if (otherObject == null || getClass() != otherObject.getClass())
            return false;
        IntValue that = (IntValue)otherObject;
        return value == that.value;
    }
}
