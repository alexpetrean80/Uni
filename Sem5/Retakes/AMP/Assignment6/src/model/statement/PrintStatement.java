package model.statement;

import model.ProgramState;
import model.adt.Dict;
import model.adt.Heap;
import model.adt.List;
import model.expression.Expression;
import model.type.Type;
import model.value.Value;

public class PrintStatement implements Statement {
    private final Expression expression;

    public PrintStatement(Expression expression){
        this.expression = expression;
    }

    public Expression getExpression() {
        return this.expression;
    }

    @Override
    public ProgramState execute(ProgramState programState) {
        List<Value> output = programState.getOutput();
        Dict<String, Value> symbolTable = programState.getSymbolTable();
        Heap<Value> heap = programState.getHeap();

        synchronized (programState.getOutput()) {
            output.addToEnd(this.expression.evaluate(symbolTable, heap));
        }
        return null;
    }

    @Override
    public Statement deepCopy() {
        return new PrintStatement(this.expression.deepCopy());
    }

    @Override
    public Dict<String, Type> typeCheck(Dict<String, Type> typeEnvironment) {
        this.expression.typeCheck(typeEnvironment);

        return typeEnvironment;
    }

    @Override
    public String toString(){
        return "print(" + this.expression.toString() + ")";
    }

}
