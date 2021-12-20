package model.statement;

import model.ProgramState;
import model.adt.Dict;
import model.type.Type;

public interface Statement {
    ProgramState execute(ProgramState programState);
    Statement deepCopy();
    Dict<String, Type> typeCheck(Dict<String, Type> typeEnvironment);
}
