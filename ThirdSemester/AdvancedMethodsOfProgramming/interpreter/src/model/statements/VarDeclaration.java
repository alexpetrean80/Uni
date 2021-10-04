package model.statements;

import model.interfaces.ProgramState;
import model.interfaces.Statement;
import model.interfaces.Type;

public class VarDeclaration implements Statement {
    final private String varName;
    final private Type type;

    public VarDeclaration(String varName, Type type) {
        this.varName = varName;
        this.type = type;
    }

    @Override
    public void execute(ProgramState state) throws Exception {
        var symbolTable = state.getSymbolTable();
        var value = type.instantiate();

        if (symbolTable.doesKeyExist(varName)) {
            throw new Exception("Variable name already taken.");
        }

        symbolTable.add(varName, value);
    }

    @Override
    public String toString() {
        return type.toString() + " " + varName;
    }

}
