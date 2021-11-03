package model.statement;

import model.ProgramState;
import model.adt.IDictionary;
import model.type.Type;

public interface Statement {
    ProgramState execute(ProgramState programState);
    Statement deepCopy();
    IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnvironment);
}
