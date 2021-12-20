package model.statement;

import exception.TypeCheckException;
import exception.VariableUndefinedException;
import exception.WrongTypeAssignedException;
import model.ProgramState;
import model.adt.Dict;
import model.adt.Heap;
import model.adt.Stack;
import model.expression.Expression;
import model.type.Type;
import model.value.Value;

public class AssignmentStatement implements Statement{

    private final String variableName;
    private final Expression expression;

    public AssignmentStatement(String variableName, Expression expression){
        this.variableName = variableName;
        this.expression = expression;
    }

    public String getVariableName() {
        return variableName;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return this.variableName + " = " + this.expression.toString();
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        var symbolTable = programState.getSymbolTable();
        var heap = programState.getHeap();

        if (!(symbolTable.containsKey(this.variableName)))
            throw new VariableUndefinedException(this.variableName);

        var variableType = symbolTable.lookup(this.variableName).getType();
        var value = this.expression.evaluate(symbolTable, heap);

        if (!(value.getType().equals(variableType)))
            throw new WrongTypeAssignedException(this.variableName);

        symbolTable.update(this.variableName, value);
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new AssignmentStatement(this.variableName, this.expression.deepCopy());
    }

    @Override
    public Dict<String, Type> typeCheck(Dict<String, Type> typeEnvironment) {
        if (!(typeEnvironment.lookup(this.variableName).equals(this.expression.typeCheck(typeEnvironment)))){
            throw new TypeCheckException("Left hand side and right hand side have different types.");
        }

        return typeEnvironment;
    }

}
