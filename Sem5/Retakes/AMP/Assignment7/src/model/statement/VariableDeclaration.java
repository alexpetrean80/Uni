package model.statement;

import exception.DefinedVariableException;
import exception.TypeCheckException;
import model.ProgramState;
import model.adt.IDictionary;
import model.type.Type;
import model.value.Value;

public class VariableDeclaration implements Statement{
    private final String variableName;
    private final Type type;

    public VariableDeclaration(Type type, String variableName){
        this.type = type;
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    public Type getType() {
        return type;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        IDictionary<String, Value> symbolTable =  programState.getSymbolTable();

        if (symbolTable.containsKey(variableName)) {
            throw new DefinedVariableException(this.variableName);
        }

        symbolTable.update(this.variableName, type.defaultValue());
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new VariableDeclaration(this.type.deepCopy(), this.variableName);
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> typeEnvironment) {
        if (typeEnvironment.containsKey(this.variableName)){
            throw new TypeCheckException("Variable already exists");
        }

        typeEnvironment.update(this.variableName, this.type);

        return typeEnvironment;
    }

    @Override
    public String toString() {
        return this.type.toString() + " " + this.variableName;
    }
}
