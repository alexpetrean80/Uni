package model.statement;

import model.ProgramState;
import model.adt.IDictionary;
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
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnvironment) {
        return typeEnvironment;
    }
}
