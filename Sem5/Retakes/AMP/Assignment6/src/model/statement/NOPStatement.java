package model.statement;

import model.ProgramState;
import model.adt.Dict;
import model.type.Type;

public class NOPStatement implements Statement{

    @Override
    public ProgramState execute(ProgramState programState) {
        return null;
    }

    @Override
    public String toString(){
        return "";
    }

    @Override
    public Statement deepCopy() {
        return new NOPStatement();
    }

    @Override
    public Dict<String, Type> typeCheck(Dict<String, Type> typeEnvironment) {
        return typeEnvironment;
    }
}
